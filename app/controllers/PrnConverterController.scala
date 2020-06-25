package controllers

import javax.inject._
import play.api.mvc._
import v1.converter.PrnToHtmlConverter._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
class PrnConverterController @Inject()(cc: ControllerComponents)
  extends AbstractController(cc) {

  def prn = Action {
    val htmlRows = convertToHtml("Workbook2.prn")
    Ok(views.html.prn.render(htmlRows))
  }
}
