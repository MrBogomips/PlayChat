package playchat.api

// External imports
import akka.actor.Actor
import akka.actor.ActorRef

// Internal imports
import playchat.models._

/**
 * Represents an actor capable to originate and reply to «ham» messages
 */
abstract class UserActor extends BaseActor