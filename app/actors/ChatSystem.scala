package actors

import play.api.libs.iteratee.{Iteratee, Enumerator}
import play.api.libs.json.JsValue

import akka.actor.ActorSystem
import akka.actor.ActorRef
import akka.actor.Props

import com.typesafe.config._

import models.User
import models.Room
import models.Robot

import providers.{AuthorizationProvider, AuthorizationStatus, Authorized, Unauthorized}

trait ChatSystem { self => 
  val name: String
  val authorizationProvider: AuthorizationProvider
  val config: Config = ConfigFactory.load()
  
  type websocketIn = Iteratee[JsValue, Unit]
  type websocketOut = Enumerator[JsValue]
  /**
   * Join a user to the chat
   */
  final def joinUser(user: User): Either[AuthorizationStatus, (websocketIn, websocketOut)] = authorizationProvider.canJoinChat(user) match {
    case Unauthorized => Left(Unauthorized)
    case Authorized => ??? // TODO Right(system.actorOf(Props[UserActor]))
  }
  
  final def createRoom(room: Room, authorizationProvider: AuthorizationProvider = self.authorizationProvider): ActorRef = {
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
