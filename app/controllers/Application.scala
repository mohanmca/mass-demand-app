package controllers

import play.api.mvc._

object Application extends Controller {

  def index() = Action {
    Ok(views.html.index())
  }

  def debug() = Action {
    Ok(views.html.debug.render())
  }
}