package drugBankTD

import com.twitter.bijection.Injection
import com.twitter.bijection.avro.GenericAvroCodecs
import drugBankTD.LabelBase.VACCINES_NAMES
import drugBankTD.SendDataByGroup.db
import org.apache.avro.Schema
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}

import java.nio.file.{Files, Paths}
import java.util
import java.util.Properties
import scala.collection.mutable

class ConsumerByGroupAndAge(topic: String) {
  val  props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")

  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer")
  props.put("group.id", "something")

  val consumer = new KafkaConsumer[String, Array[Byte]](props)

  consumer.subscribe(util.Arrays.asList(topic))

  def consume(IdAgeMap: mutable.Map[String, Int]): Unit = {
    val thread = new Thread {
      override def run {
        System.out.println("started on: " + topic)
        val file: String = new String(Files.readAllBytes(Paths.get(LabelBase.SCHEMA)))
        val parser: Schema.Parser = new Schema.Parser
        val schema: Schema = parser.parse(file)
        val recordInjection: Injection[GenericRecord, Array[Byte]] = GenericAvroCodecs.toBinary(schema)
        val countingSideEffect: scala.collection.mutable.Map[Int, Long] = scala.collection.mutable.Map[Int, Long]()
        val resMap: scala.collection.mutable.Map[String, scala.collection.mutable.Map[Int, Long]] = scala.collection.mutable.Map[String, scala.collection.mutable.Map[Int, Long]]()
        while(true){
          var records: ConsumerRecords[String, Array[Byte]] = consumer.poll(100);
          records.forEach(r => {
            val siderCode = recordInjection.invert(r.value()).get.get("siderCode").toString
            val id = recordInjection.invert(r.value()).get.get("id").toString
            val age = IdAgeMap.get(id).get
            if(!resMap.contains(siderCode)) {
              resMap(siderCode) = scala.collection.mutable.Map[Int, Long]()
            }
            val mapAge = (age / 10)* 10
            if(!resMap.get(siderCode).contains(mapAge)) {
              val map = resMap.get(siderCode).get
              map(mapAge) = 0
            }
            val map = resMap.get(siderCode).get
            map(mapAge) = map(mapAge) + 1
          })
          if(!records.isEmpty){
            System.out.println("total side effects for vaccine " + topic + ": " + resMap.toString())
          }
        }
      }
    }
    thread.start
  }
}

object ConsumerByGroupAndAge extends App {
  import java.util.Properties

  val TOPICS=VACCINES_NAMES
  val consumerList = new util.ArrayList[ConsumerByGroupAndAge]
  val db = new LubmExtractor(LabelBase.INPUT_FILE, LabelBase.MALE_PERCENT, LabelBase.PEOPLE_VACCINATED_PERCENT, LabelBase.VACINES_PERCENT, LabelBase.VACCINES_NAMES, LabelBase.SUJECTS, LabelBase.SIDER_EFFECTS, LabelBase.DATE_START_VACCINATION_CAMPAIGN)
  TOPICS.forEach(t => consumerList.add(new ConsumerByGroupAndAge(t)))
  print("load data : ")
  db.loadRdf(LabelBase.INPUT_EXTENDED_FILE)
  println("done.")
  print("getting all ages : ")
  val IdAgeMap = db.getAllAges()
  println("done.")
  consumerList.forEach(x => x.consume(IdAgeMap))


}
