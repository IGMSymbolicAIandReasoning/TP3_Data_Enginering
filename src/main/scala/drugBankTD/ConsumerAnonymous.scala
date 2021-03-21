package drugBankTD

import com.twitter.bijection.Injection
import com.twitter.bijection.avro.GenericAvroCodecs
import org.apache.avro.Schema
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}

import java.nio.file.{Files, Paths}
import java.util


object ConsumerAnonymous extends App {
  import java.util.Properties

  val TOPIC="tp2"

  val  props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")

  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer")
  props.put("group.id", "something")

  val consumer = new KafkaConsumer[String, Array[Byte]](props)

  consumer.subscribe(util.Arrays.asList(TOPIC))
  val file: String = new String(Files.readAllBytes(Paths.get("/Users/louis_billaut/Desktop/M2/data_engineer/project2/TP2_Data_Engineering/schemaAnonymous.avsc")))
  val parser: Schema.Parser = new Schema.Parser
  val schema: Schema = parser.parse(file)
  val recordInjection: Injection[GenericRecord, Array[Byte]] = GenericAvroCodecs.toBinary(schema)
  while(true){
    val records : ConsumerRecords[String, Array[Byte]] = consumer.poll(100);
    records.forEach(r => {
      val siderCode = recordInjection.invert(r.value()).get.get("siderCode").toString
      if(siderCode == "C0027497"){
        System.out.println("id=" + recordInjection.invert(r.value()).get.get("id") + ", vaccine=" + recordInjection.invert(r.value()).get.get("vaccine") + ", date=" + recordInjection.invert(r.value()).get.get("date") + ", siderCode=" + siderCode)
      }
    })
  }


}
