package actors

import akka.actor.Actor
import akka.actor.ActorRef

import models.Room

class RoomActor private[actors] (val room: Room, val chatRef: ActorRef) extends ChatBaseActor
