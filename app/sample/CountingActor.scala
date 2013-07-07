package sample

import akka.actor.Actor
import sample.SpringExtension._

object COUNT
object GET


class CountingActor extends Actor  {
    var countingService: CountingService = _
    private var count = 0
    def receive = {
      case COUNT =>   count = countingService.increment(count)
      case GET => sender ! count
    }
}
