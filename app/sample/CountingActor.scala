package sample

import akka.actor.Actor
import sample.SpringExtension._


object CountingActor {

  object COUNT

  object GET

}

class CountingActor extends Actor {

  import CountingActor._

  var countingService: CountingService = _
  private var count = 0

  def receive = {
    case COUNT => count = countingService.increment(count)
    case GET => sender ! count
  }
}
