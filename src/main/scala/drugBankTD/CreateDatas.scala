package drugBankTD

object CreateDatas extends App {
  val db = new LubmExtractor(LabelBase.INPUT_FILE, LabelBase.MALE_PERCENT, LabelBase.PEOPLE_VACCINATED_PERCENT, LabelBase.VACINES_PERCENT, LabelBase.VACCINES_NAMES, LabelBase.SUJECTS, LabelBase.SIDER_EFFECTS, LabelBase.DATE_START_VACCINATION_CAMPAIGN)

  db.load()
  print("creates data : ")
  db.extender()
  println("done.")

  print("creates vaccine data : ")
  db.extender_vaccine()
  println("done.")

  print("writes in file : ")
  db.toFile(db.model, LabelBase.OUTPUT_FILE, "RDF/XML-ABBREV")
  println("done")


}