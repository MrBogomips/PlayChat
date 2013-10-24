package providers

import models.Robot
import models.Room
import models.{OperationStatus, Success, Error}
import models.User

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
  def canShutdownChat(user: User): Boolean
}

trait PublicChat extends AuthorizationProvider {
  def canJoinChat(user: User): AuthorizationStatus = Authorized
}

trait PublicRooms extends AuthorizationProvider {
  def canJoinRoom(user: User, room: Room): AuthorizationStatus = Authorized
}