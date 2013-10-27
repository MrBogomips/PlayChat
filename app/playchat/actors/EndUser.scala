package playchat.actors

import scala.concurrent.Future

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.Props
import play.api.Play.current
import play.api.libs.iteratee.{ Iteratee, Concurrent, Enumerator }
import play.api.libs.json.JsValue

import playchat.api._
import playchat.models._

/**
  * EndUserActor is the User gateway between the client app (RESTful JSON API driven) and
  * the server side environment
  *
  * It's responsible of:
  * - decoding and validation of json requests
  * - dispatch messages to the proper actor
  * - encoding and send back json responses
  */
class EndUserActor private[actors] (val user: User, val chatRef: ActorRef, val outChannel: Concurrent.Channel[JsValue]) extends UserActor {
  /**
   * All the room the user is subscribed to
   */
  private[this] var rooms = Map.empty[Room, ActorRef]
  
  def receive = {
    case json :  JsValue => outChannel push json
  }
}

