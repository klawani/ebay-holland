package v1.converter

import java.io.File
import v1.converter.Control._

import scala.io.Source
import scala.util.Try

trait HtmlConverter {

  def createHeadOfHtmlRow: HtmlRow = {
    val rows = List(HtmlData("Name"),
                    HtmlData("Address"),
                    HtmlData("Postcode"),
                    HtmlData("Phone"),
                    HtmlData("Credit Limit"),
                    HtmlData("Birthday"))
    HtmlRow(rows)
  }

  def readLines(path: String): List[String] = {
    val result: Try[File] = Try { new File(path) }
    result.fold(
      ex => {
        "HtmlConverter failed with " + ex
        List.empty[String]
      },
      file => using(Source.fromFile(file)){
        source => source.getLines().toList
      }
    )
  }
}
