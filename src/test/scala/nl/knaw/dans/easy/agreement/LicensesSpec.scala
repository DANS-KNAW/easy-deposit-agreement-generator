package nl.knaw.dans.easy.agreement

import nl.knaw.dans.easy.agreement.fixture.TestSupportFixture
import better.files.File.currentWorkingDirectory
import org.apache.commons.configuration.PropertiesConfiguration

import scala.util.{ Failure, Success }

class LicensesSpec extends TestSupportFixture {

  private val licensesProps = currentWorkingDirectory / "target" / "easy-licenses" / "licenses" / "licenses.properties"
  val licenses = new Licenses(new PropertiesConfiguration(licensesProps.toJava))

  "licenseLegalResource" should "map a URL to its legal resource" in {
    licenses.licenseLegalResource("http://opensource.org/licenses/MIT") should matchPattern {
      case Success("/licenses/MIT.txt") =>
    }
  }

  it should "map a URL if 'https' is used instead of 'http'" in {
    licenses.licenseLegalResource("https://opensource.org/licenses/BSD-2-Clause") should matchPattern {
      case Success("/licenses/BSD-2-Clause.txt") =>
    }
  }

  it should "fail if the URL does not occur in the listing" in {
    licenses.licenseLegalResource("http://www.invalid-license.org/licenses/LICENSE-1.0") should matchPattern {
      case Failure(InvalidLicenseException("No legal text found for http://www.invalid-license.org/licenses/LICENSE-1.0")) =>
    }
  }
}
