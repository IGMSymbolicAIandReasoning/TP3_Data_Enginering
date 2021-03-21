package drugBankTD

import java.text.SimpleDateFormat
import java.time.Instant
import java.util
import java.util.Date
import util.ArrayList
import util.Arrays

object LabelBase {

  val INPUT_FILE : String = "file:lubm1.ttl"
  val OUTPUT_FILE : String = "lubm1_extended.rdf"

  val SUJECTS = new util.ArrayList[String](util.Arrays.asList("http://swat.cse.lehigh.edu/onto/univ-bench.owl#AssistantProfessor", "http://swat.cse.lehigh.edu/onto/univ-bench.owl#AssociateProfessor","http://swat.cse.lehigh.edu/onto/univ-bench.owl#FullProfessor", "http://swat.cse.lehigh.edu/onto/univ-bench.owl#GraduateStudent","http://swat.cse.lehigh.edu/onto/univ-bench.owl#Lecturer", "http://swat.cse.lehigh.edu/onto/univ-bench.owl#UndergraduateStudent"))
  val VACCINES_NAMES = new util.ArrayList[String](util.Arrays.asList("Pfizer", "Moderna","AstraZeneca", "SpoutnikV", "CanSinoBi"))
  val VACINES_PERCENT = new util.ArrayList[Int](util.Arrays.asList(20, 20, 30, 10, 20))

  val MALE_PERCENT : Int = 40
  val PEOPLE_VACCINATED_PERCENT : Int = 70

  val SIDER_EFFECTS = new util.ArrayList[String](util.Arrays.asList("C0151828", "C0015672", "C0018681", "C0231528", "C0085593", "C0003862", "C0015967", "C0151605", "C0852625", "C0027497", "C0231218", "C0497156", "C0863083"))
  val DATE_START_VACCINATION_CAMPAIGN = Instant.parse("2020-12-27T10:00:00.00Z")
}
