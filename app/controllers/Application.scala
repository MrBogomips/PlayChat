package controllers

import play.api._
import play.api.mvc._
import play.api.libs.iteratee._
import play.api.libs.json._

import playchat.api._
import playchat.actors._
import playchat.models._

object Application extends Controller {

  lazy val chat = new ChatSystem {
    lazy val name = "mychat"
    val authorizationProvider = AllowEverything
  }

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def mychat(username: String) = WebSocket.using[JsValue] { implicit request =>
    chat.joinUser(EndUser(username))
  }

}