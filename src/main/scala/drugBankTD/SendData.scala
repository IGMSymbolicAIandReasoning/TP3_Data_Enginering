package drugBankTD

object SendData extends App {

  val db = new LubmExtractor(LabelBase.INPUT_FILE, LabelBase.MALE_PERCENT, LabelBase.PEOPLE_VACCINATED_PERCENT, LabelBase.VACINES_PERCENT, LabelBase.VACCINES_NAMES, LabelBase.SUJECTS, LabelBase.SIDER_EFFECTS, LabelBase.DATE_START_VACCINATION_CAMPAIGN)

  print("load data : ")
  db.loadRdf(LabelBase.INPUT_EXTENDED_FILE)
  println("done.")
  print("producer avro sider code data : ")
  db.extract_avro_sider_records()
  println("done")
}
