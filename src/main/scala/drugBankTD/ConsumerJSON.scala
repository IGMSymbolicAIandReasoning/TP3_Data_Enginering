package drugBankTD

import java.util

import org.apache.kafka.clients.consumer.KafkaConsumer

import scala.collection.JavaConverters._

object ConsumerJSON extends App {

  import java.util.Properties

  val TOPIC="tp1"

  val  props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")

  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("group.id", "something")

  val consumer = new KafkaConsumer[String, String](props)

  consumer.subscribe(util.Collections.singletonList(TOPIC))

  var res = new util.HashMap[String, String]()
  while(true){
    val records = consumer.poll(100)
    for (record<-records.asScala){
      if (record != null && record.value() != "") {
        val map = JsonUtil.toMap[String](record.value())
        if (map.contains("vaccine")){
          println("vaccine : " + map("vaccine"))
        }
      }
    }
  }
}