package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.Helpers._
import play.api.test._

class PrnConverterControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "PrnConverterController" should {
    "render the prn page" in {
      val request = FakeRequest(GET, "/prn").withHeaders(HOST -> "localhost:9000")
      val prn    = route(app, request).get

      contentAsString(prn) must include("Prn to Html Converter")
    }
  }

  "PrnConverterController" should {
    "render the prn page with data" in {
      val request = FakeRequest(GET, "/prn").withHeaders(HOST -> "localhost:9000")
      val prn    = route(app, request).get

      contentAsString(prn) must include("9640904")
    }
  }
}
