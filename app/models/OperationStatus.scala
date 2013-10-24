package models

trait OperationStatus
trait Success extends OperationStatus
trait Warning extends OperationStatus
trait Error extends OperationStatus

abstract class DescriptiveWarning(description: String) extends Warning
abstract class DescriptiveError(description: String) extends Error