package v1.converter

import org.scalatest.{FlatSpec, Matchers}

class PrnToHtmlConverterSpec extends FlatSpec with Matchers {

  "PrnToHtmlConverter" should "read lines in file" in {
    val expectedLine = "Anderson, Paul  Dorpsplein 3A         4532 AA  030 3458986       10909300 19651203"
    val actualLines = PrnToHtmlConverter.readLines("test/resources/test1.prn")
    actualLines should not be (List.empty[String])
    actualLines should contain (expectedLine)
  }

  "PrnToHtmlConverter" should "read a line into html table data" in {
    val lineToConvert = "Smith, John     Børkestraße 32        87823    +44 728 889838      989830 19990920"
    val expectedHtmlRow = createTableDataRow

    val actualHtmlRow = PrnToHtmlConverter.convertSingleLineToHtmlRow(lineToConvert)
    actualHtmlRow shouldEqual (expectedHtmlRow)
  }

  "PrnToHtmlConverter" should "read a prn file into html table data" in {
    val expectedTableHead = createTableHeadRow
    val expectedTableDataRow = createTableDataRow

    val actualHtmlRows = PrnToHtmlConverter.convertToHtml("test/resources/test2.prn")
    actualHtmlRows.size shouldEqual (2)
    actualHtmlRows should contain (expectedTableHead)
    actualHtmlRows should contain (expectedTableDataRow)
  }

  private def createTableDataRow: HtmlRow = {
    val name = HtmlData("Smith, John")
    val address = HtmlData("Børkestraße 32")
    val postCode = HtmlData("87823")
    val telephone = HtmlData("+44 728 889838")
    val credit = HtmlData("989830")
    val birthday = HtmlData("19990920")
    val rows = List(name, address, postCode, telephone, credit, birthday)
    HtmlRow(rows)
  }

  private def createTableHeadRow: HtmlRow = {
    val name = HtmlData("Name")
    val address = HtmlData("Address")
    val postCode = HtmlData("Postcode")
    val telephone = HtmlData("Phone")
    val credit = HtmlData("Credit Limit")
    val birthday = HtmlData("Birthday")
    val rows = List(name, address, postCode, telephone, credit, birthday)
    HtmlRow(rows)
  }
}
