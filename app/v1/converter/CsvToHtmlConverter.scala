package v1.converter

object CsvToHtmlConverter extends App with HtmlConverter {

  def convertToHtml(path: String): List[HtmlRow] = {
    convertFileLinesToHtmlRow(path)
  }

  def convertFileLinesToHtmlRow(path: String): List[HtmlRow] = {
    val lines = readLines(path)
    val tableHeadRow = createHeadOfHtmlRow
    val tableDataRows = lines.tail.map(convertSingleLineToHtmlRow)
    List(tableHeadRow) ++ tableDataRows
  }

  def convertHeadLineToHtmlRow(line: String): HtmlRow = {
    val regExpr = ","
    HtmlRow(line.strip().split(regExpr).map(HtmlData).toList)
  }

  def convertSingleLineToHtmlRow(line: String): HtmlRow = {
    val regExpr = ","
    val dataList = line.strip()
      .split("\"").drop(1).toSeq
    val dataHead = List(HtmlData(dataList.head))
    val dataOthers = dataList.tail.flatMap(s => {
      val lst = s.split(regExpr).drop(1)
      lst.map(HtmlData)
    })
    HtmlRow(dataHead ++ dataOthers)
  }
}
