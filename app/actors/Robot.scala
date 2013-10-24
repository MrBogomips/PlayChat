package actors

import play.api.Play.current
import play.api.libs.iteratee.{Iteratee, Concurrent, Enumerator}
import play.api.libs.json.JsValue

import akka.actor.Actor
import akka.actor.ActorRef

import com.typesafe.config._

import models.User
import models.Robot

/**
 * Robot actor mimic a user actor in the sense it's honor the
 * UserActor protocol but provide automated services.
 * 
 * Examples: translators, os shell, db shell, room ciceros etc.
 */
abstract class RobotActor (val robot: Robot, val chatRef: ActorRef, val conf: Option[Config]) extends UserActor