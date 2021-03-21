package drugBankTD

import com.github.javafaker.Faker
import com.twitter.bijection.Injection
import com.twitter.bijection.avro.GenericAvroCodecs
import drugBankTD.ConsumerAVROAge.recordInjection
import org.apache.avro.Schema
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}

import java.nio.file.{Files, Paths}
import java.time.Instant
import java.util
import java.util.{ArrayList, Date}


object ConsumerAVROProducerAnonymous extends App {
  import java.util.Properties

  val TOPIC="tp1"

  val  props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")

  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer")
  props.put("group.id", "something")

  val consumer = new KafkaConsumer[String, Array[Byte]](props)

  consumer.subscribe(util.Arrays.asList(TOPIC))
  val file: String = new String(Files.readAllBytes(Paths.get("/Users/louis_billaut/Desktop/M2/data_engineer/project2/TP2_Data_Engineering/schema.avsc")))
  val parser: Schema.Parser = new Schema.Parser
  val schema: Schema = parser.parse(file)
  val recordInjection: Injection[GenericRecord, Array[Byte]] = GenericAvroCodecs.toBinary(schema)
  var seq_records : ArrayList[util.Map[String, String]] = new ArrayList()
  while(true){
    val records : ConsumerRecords[String, Array[Byte]] = consumer.poll(100);
    records.forEach(r => {
      val personSiderInfo = util.Map.of(
        "id" , recordInjection.invert(r.value()).get.get("id").toString,
        "vaccine" , recordInjection.invert(r.value()).get.get("vaccine").toString,
        "date" , recordInjection.invert(r.value()).get.get("date").toString,
        "siderCode" , recordInjection.invert(r.value()).get.get("siderCode").toString
      )

      // Save the map in a Seq
      seq_records.add(personSiderInfo)
    })
    if(seq_records.size() > 0) {
      System.out.println("data received ! Anonymizing...")
      val res = new AvroConvertor().launchAnonymousProducer(seq_records)

      val map = Map(
        "bootstrap.servers" ->  "localhost:9092",
        "key.serializer" -> "org.apache.kafka.common.serialization.StringSerializer",
        "value.serializer" ->  "org.apache.kafka.common.serialization.ByteArraySerializer"
      )
      new Producer(map).startAVROAnonymousProducer(res)
      seq_records = new ArrayList()
    }
  }


}
