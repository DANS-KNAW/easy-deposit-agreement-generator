package nl.knaw.dans.easy.agreement

import nl.knaw.dans.easy.agreement.fixture.TestSupportFixture
import org.joda.time.{ DateTime, DateTimeZone }

import scala.util.Success

class AgreementInputSpec extends TestSupportFixture {

  "fromJSON" should "parse a JSON representation of AgreementInput" in {
    val json =
      """{
        |  "depositor": {
        |    "name": "my name",
        |    "address": "my address",
        |    "zipcode": "my zipcode",
        |    "city": "my city",
        |    "country": "my country",
        |    "organisation": "my organisation",
        |    "phone": "my phone",
        |    "email": "my email"
        |  },
        |  "doi": "10.17026/test-dans-2xg-umq8",
        |  "title": "my first dataset",
        |  "dateSubmitted": "2019-12-06T10:02:00Z",
        |  "dateAvailable": "2019-11-06T10:02:00Z",
        |  "accessCategory": "OPEN_ACCESS",
        |  "license": "http://creativecommons.org/licenses/by-nc-sa/4.0/",
        |  "sample": true,
        |  "agreementVersion": "4.0",
        |  "agreementLanguage": "EN"
        |}""".stripMargin
    val expectedOutput = AgreementInput(
      depositor = Depositor(
        name = "my name",
        address = "my address",
        zipcode = "my zipcode",
        city = "my city",
        country = "my country",
        organisation = "my organisation",
        phone = "my phone",
        email = "my email",
      ),
      doi = "10.17026/test-dans-2xg-umq8",
      title = "my first dataset",
      dateSubmitted = new DateTime(2019, 12, 6, 10, 2, 0, DateTimeZone.UTC),
      dateAvailable = new DateTime(2019, 11, 6, 10, 2, 0, DateTimeZone.UTC),
      accessCategory = AccessCategory.OPEN_ACCESS,
      license = "http://creativecommons.org/licenses/by-nc-sa/4.0/",
      sample = true,
      agreementVersion = AgreementVersion.FOUR_ZERO,
      agreementLanguage = AgreementLanguage.EN,
    )
    AgreementInput.fromJSON(json) should matchPattern { case Success(expectedOutput) => }
  }
}
