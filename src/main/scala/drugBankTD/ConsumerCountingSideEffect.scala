package drugBankTD

import com.twitter.bijection.Injection
import com.twitter.bijection.avro.GenericAvroCodecs
import org.apache.avro.Schema
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}

import java.nio.file.{Files, Paths}
import java.util


object ConsumerCountingSideEffect extends App {
  import java.util.Properties

  val TOPIC="tp1"

  val  props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")

  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer")
  props.put("group.id", "something")

  val consumer = new KafkaConsumer[String, Array[Byte]](props)

  consumer.subscribe(util.Arrays.asList(TOPIC))
  val file: String = new String(Files.readAllBytes(Paths.get(LabelBase.SCHEMA)))
  val parser: Schema.Parser = new Schema.Parser
  val schema: Schema = parser.parse(file)
  val recordInjection: Injection[GenericRecord, Array[Byte]] = GenericAvroCodecs.toBinary(schema)
  val countingSideEffect: scala.collection.mutable.Map[String, Long] = scala.collection.mutable.Map[String, Long]()
  while(true){
    var records: ConsumerRecords[String, Array[Byte]] = consumer.poll(100)
    records.forEach(r => {
      val siderCode = recordInjection.invert(r.value()).get.get("siderCode").toString
      if(!countingSideEffect.contains(siderCode)) {
        countingSideEffect(siderCode) = 0
      }
      countingSideEffect(siderCode) = countingSideEffect(siderCode) + 1
    })
    if(!records.isEmpty){
      System.out.println("total side effects: " + countingSideEffect.toString())
      records = consumer.poll(100)
    }
  }
}
