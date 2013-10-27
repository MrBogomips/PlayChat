package playchat.models

trait User
case class EndUser(username: String) extends User
case object Anonymous extends User 