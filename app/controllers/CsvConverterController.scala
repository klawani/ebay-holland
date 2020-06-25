package controllers

import javax.inject._
import play.api.mvc._
import v1.converter.CsvToHtmlConverter._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
class CsvConverterController @Inject()(cc: ControllerComponents)
  extends AbstractController(cc) {

  def csv = Action {
    val htmlRows = convertToHtml("Workbook2.csv")
    Ok(views.html.csv.render(htmlRows))
  }
}
