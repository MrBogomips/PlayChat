package playchat.actors

import scala.concurrent.Future
import play.api.libs.iteratee._
import play.api.libs.json._
import akka.actor.ActorSystem
import akka.actor.ActorRef
import akka.actor.Props
import com.typesafe.config._
import playchat.api._
import playchat.models._
import playchat.protocols.JoinChatProcotol._
import playchat.protocols.JoinChatProcotol

trait ChatSystem { self =>
  val name: String
  val authorizationProvider: AuthorizationProvider
  val config: Config = ConfigFactory.load()

  final type websocketIn = Iteratee[JsValue, Unit]
  final type websocketOut = Enumerator[JsValue]
  /**
    * Join a user to the chat
    */
  final def joinUser(user: User): (Iteratee[JsValue, Unit], Enumerator[JsValue]) = authorizationProvider.canJoinChat(user) match {
    case Unauthorized =>
      val iteratee = Done[JsValue, Unit]((), Input.EOF)
      val enumerator = Enumerator[JsValue](JsObject(Seq("error" -> JsString(Unauthorized.toString())))).andThen(Enumerator.enumInput(Input.EOF))
      (iteratee, enumerator)
    case Authorized =>
      import play.api.libs.concurrent.Execution.Implicits._
      val (outEnumerator, outChannel) = Concurrent.broadcast[JsValue]
      val userProps = Props(classOf[EndUserActor], user, chatRef, outChannel)
      val userRef = system.actorOf(userProps)
      val inIteratee = Iteratee.foreach[JsValue] { message =>
        userRef ! message
      }.map { _ =>
        chatRef ! JoinChatProcotol.Quit(user)
      }
      chatRef ! JoinChatProcotol.Joined(user, userRef)
      (inIteratee, outEnumerator)
  }

  final def createRoom(room: Room): ActorRef = {
    ???
  }
  /**
    * Create a system robot
    */
  final def createSystemRobot[T <: RobotActor](robot: Robot, config: Option[Config]): ActorRef = {
    // TODO: inject the construct by reflection
    ???
  }
  /**
    * Create a room robot
    */
  final def createRoomRobot[T <: RobotActor](room: Room, robot: Robot, config: Option[Config] = Some(self.config)): ActorRef = {
    // TODO: inject the construct by reflection
    ???
  }

  lazy val system: ActorSystem = ActorSystem(name)
  val chatRef = system.actorOf(Props[Chat]) 
}
