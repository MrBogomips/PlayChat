package playchat.api

import playchat.models._

trait AuthorizationStatus extends OperationStatus
case object Authorized extends AuthorizationStatus with Success
case object Unauthorized extends AuthorizationStatus with Error

trait AuthorizationProvider extends Provider {
  def canJoinChat(user: User): AuthorizationStatus
  def canJoinRoom(user: User, room: Room): AuthorizationStatus
  def canCreateRoom(user: User, room: Room): AuthorizationStatus
  def canDestroyRoom(user: User, room: Room): AuthorizationStatus
  def canCreateSystemRobot(user: User, robot: Robot): AuthorizationStatus
  def canDestroySystemRobot(user: User, robot: Robot): AuthorizationStatus
  def canCreateRoomRobot(user: User, room: Room, robot: Robot): AuthorizationStatus
  def canDestroyRoomRobot(user: User, room: Room, robot: Robot): AuthorizationStatus
  def canShutdownChat(user: User): AuthorizationStatus
}

trait PublicChat extends AuthorizationProvider {
  def canJoinChat(user: User): AuthorizationStatus = Authorized
}

trait PublicRooms extends AuthorizationProvider {
  def canJoinRoom(user: User, room: Room): AuthorizationStatus = Authorized
}

object AllowEverything extends AuthorizationProvider with PublicChat with PublicRooms {
  def canCreateRoom(user: User, room: Room): playchat.api.AuthorizationStatus = Authorized
  def canCreateRoomRobot(user: User, room: Room, robot: Robot): playchat.api.AuthorizationStatus = Authorized
  def canCreateSystemRobot(user: User, robot: Robot): playchat.api.AuthorizationStatus = Authorized
  def canDestroySystemRobot(user: User, robot: Robot): AuthorizationStatus = Authorized
  def canDestroyRoom(user: User, room: Room): playchat.api.AuthorizationStatus = Authorized
  def canDestroyRoomRobot(user: User, room: Room, robot: Robot): playchat.api.AuthorizationStatus = Authorized
  def canShutdownChat(user: User): AuthorizationStatus = Authorized
}