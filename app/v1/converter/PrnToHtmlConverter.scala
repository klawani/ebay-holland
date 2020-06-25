package v1.converter

object PrnToHtmlConverter extends App with HtmlConverter {

  def convertToHtml(path: String): List[HtmlRow] = {
    convertFileLinesToHtmlRow(path)
  }

  def convertFileLinesToHtmlRow(path: String): List[HtmlRow] = {
    val lines = readLines(path)
    val tableHeadRow = createHeadOfHtmlRow
    val tableDataRows = lines.tail.map(convertSingleLineToHtmlRow)
    List(tableHeadRow) ++ tableDataRows
  }

  def convertSingleLineToHtmlRow(line: String): HtmlRow = {
    val lines = line.strip().split("\\s{2,}").toList
    val allRows = lines.init.map(HtmlData) ++ lines.last.split("\\s").map(HtmlData).toList
    HtmlRow(allRows)
  }
}
