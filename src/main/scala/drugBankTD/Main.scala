package drugBankTD

import java.text.SimpleDateFormat
import java.util


object Main extends App {

  val db = new LubmExtractor(LabelBase.INPUT_FILE, LabelBase.MALE_PERCENT, LabelBase.PEOPLE_VACCINATED_PERCENT, LabelBase.VACINES_PERCENT, LabelBase.VACCINES_NAMES, LabelBase.SUJECTS, LabelBase.SIDER_EFFECTS, LabelBase.DATE_START_VACCINATION_CAMPAIGN)

  db.load()
  print("creates data : ")
  db.extender()
  println("done.")

  print("creates vaccine data : ")
  db.extender_vaccine()
  println("done.")

  //db.extract_json_sider_records()
  //print("producer avro data : ")
  //db.extract_avro_sider_records()
  //println("done avro.")

  print("producer avro sider code data : ")
  db.extract_avro_sider_records_by_group()
  println("done avro.")
  //print("writes in file : ")
  //db.toFile(db.model, LabelBase.OUTPUT_FILE, "RDF/XML-ABBREV")
  println("done")


}

