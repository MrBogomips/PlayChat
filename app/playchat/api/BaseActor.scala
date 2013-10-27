package playchat.api

import akka.actor.Actor
import akka.actor.ActorRef

/**
 * Base class for all the actors within the system
 */
abstract class BaseActor extends Actor {
  protected val chatRef: ActorRef
  
}