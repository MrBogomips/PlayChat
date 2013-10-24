package actors

import play.api.Play.current
import play.api.libs.iteratee.{Iteratee, Concurrent, Enumerator}
import play.api.libs.json.JsValue

import akka.actor.Actor
import akka.actor.ActorRef

import models.User
import models.Robot

abstract class UserActor extends ChatBaseActor

/**
 * EndUserActor is the User gateway between the client app (RESTful JSON API driven) and
 * the server side environment
 *  
 * It's responsible of:
 * - decoding and validatio of json requests
 * - dispatch messages to the proper actor
 * - send back json responses
 */
class EndUserActor private[actors] (val user: User, val chatRef: ActorRef) extends UserActor {
  import play.api.libs.concurrent.Execution.Implicits._
  
  var rooms = Set.empty[ActorRef]
  val (outEnumerator, outChannel) = Concurrent.broadcast[JsValue]
  val inIteratee = Iteratee.foreach[JsValue] { event =>
          // default ! Talk(username, (event \ "text").as[String])
    	  ???
        }.map { _ =>
          // default ! Quit(username)
          ???
        }
}
object EndUserActor {
  private[actors] def join(user: User, chatRef: ActorRef) = {
    new EndUserActor(user, chatRef)
  }
}
