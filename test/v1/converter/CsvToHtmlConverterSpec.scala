package v1.converter

import org.scalatest.{FlatSpec, Matchers}

class CsvToHtmlConverterSpec extends FlatSpec with Matchers {

  "CsvToHtmlConverter" should "return empty lines for non existing file" in {
    val actualLines = CsvToHtmlConverter.readLines("src/test/resources/nonExisting.csv")
    actualLines should be (List.empty[String])
  }

  "CsvToHtmlConverter" should "read lines in file" in {
    val expectedLine = "\"Wicket, Steve\",Mendelssohnstraat 54d,3423 ba,0313-398475,934,03/06/1964"
    val actualLines = CsvToHtmlConverter.readLines("test/resources/test1.csv")
    actualLines should not be (List.empty[String])
    actualLines should contain (expectedLine)
  }

  "CsvToHtmlConverter" should "read a line into html table data" in {
    val lineToConvert = "\"Benetar, Pat\",Driehoog 3zwart,2340 CC,06-28938945,54,04/09/1964"
    val expectedHtmlRow = createTableDataRow

    val actualHtmlRow = CsvToHtmlConverter.convertSingleLineToHtmlRow(lineToConvert)
    actualHtmlRow shouldEqual (expectedHtmlRow)
  }

  "CsvToHtmlConverter" should "read a csv file into html table data" in {
    val expectedTableHead = createTableHeadRow
    val expectedTableDataRow = createTableDataRow

    val actualHtmlRows = CsvToHtmlConverter.convertToHtml("test/resources/test2.csv")
    actualHtmlRows.size shouldEqual (2)
    actualHtmlRows should contain (expectedTableHead)
    actualHtmlRows should contain (expectedTableDataRow)
  }

  private def createTableDataRow: HtmlRow = {
    val name = HtmlData("Benetar, Pat")
    val address = HtmlData("Driehoog 3zwart")
    val postCode = HtmlData("2340 CC")
    val telephone = HtmlData("06-28938945")
    val credit = HtmlData("54")
    val birthday = HtmlData("04/09/1964")
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
