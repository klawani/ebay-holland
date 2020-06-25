package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.Helpers._
import play.api.test._

class CsvConverterControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "CsvConverterController" should {
    "render the csv page" in {
      val request = FakeRequest(GET, "/csv").withHeaders(HOST -> "localhost:9000")
      val csv    = route(app, request).get

      contentAsString(csv) must include("Csv to Html Converter")
    }
  }

  "CsvConverterController" should {
    "render the csv page with data" in {
      val request = FakeRequest(GET, "/csv").withHeaders(HOST -> "localhost:9000")
      val csv    = route(app, request).get

      contentAsString(csv) must include("Gibson, Mal")
    }
  }
}
