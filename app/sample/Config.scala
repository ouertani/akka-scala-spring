package sample

import scala.concurrent.ExecutionContext
import akka.util.Timeout
import scala.concurrent.duration._

object Config {

  implicit val ec = ExecutionContext.Implicits.global
  implicit val timeOut = Timeout(3 seconds)
  implicit val duration = timeOut.duration
}
