package sample

import akka.actor.Actor

object COUNT
object GET
class CountingActor( countingService: CountingService) extends Actor  {
    private var count = 0
    def receive = {
      case COUNT =>   count = countingService.increment(count)
      case GET => sender ! count
    }
}
