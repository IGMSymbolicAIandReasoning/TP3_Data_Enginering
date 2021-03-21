package drugBankTD

import com.twitter.bijection.Injection
import org.apache.avro.generic.GenericRecord

import java.util

class Producer(val prop: Map[String, String]) {

  import java.util.Properties

  import org.apache.kafka.clients.producer._

  def startJSONProducer(seq_records : Seq[String]): Unit ={
    val  props = new Properties()
    prop.foreach(a => props.put(a._1, a._2))

    val producer = new KafkaProducer[String, String](props)

    val TOPIC="tp1"

    seq_records.foreach(s => {
      val record = new ProducerRecord(TOPIC, "jsonLubm", s)
      producer.send(record)
    })

  }

  def startAVROroducer(lst : util.List[Array[Byte]]): Unit ={
    val  props = new Properties()
    prop.foreach(a => props.put(a._1, a._2))
    val producer = new KafkaProducer[String, Array[Byte]](props)

    val TOPIC="tp1"

    lst.forEach(s => {
      val record = new ProducerRecord(TOPIC, "avroSend", s)
      producer.send(record)
    })

  }

  def startAVROAnonymousProducer(lst : util.List[Array[Byte]]): Unit ={
    val  props = new Properties()
    prop.foreach(a => props.put(a._1, a._2))
    val producer = new KafkaProducer[String, Array[Byte]](props)

    val TOPIC="tp2"

    lst.forEach(s => {
      val record = new ProducerRecord(TOPIC, "avroAnonymousSend", s)
      producer.send(record)
    })

  }





}