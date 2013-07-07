package sample

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import akka.actor.{ActorRef, ActorSystem}
import sample.SpringExtension._
import scala.concurrent.{ future, promise }
import scala.concurrent.duration._
import akka.util.Timeout
import akka.pattern.ask
import scala.concurrent._
import scala.util._
import SpringExtension._


object Main extends App {
  // create a spring context and scan the classes
  val ctx: AnnotationConfigApplicationContext = new AnnotationConfigApplicationContext
  ctx.scan("sample")


  ctx.refresh
  // get hold of the actor system
  val system: ActorSystem = ctx.getBean(classOf[ActorSystem])
  // use the Spring Extension to create props for a named actor bean
  val counter: ActorRef = system.actorOf(springExtProvider.get(system).props("CountingActor"), "counter")
  // tell it to count three times


  counter ! COUNT
  counter ! COUNT
  counter ! COUNT

  implicit val ec = ExecutionContext.Implicits.global

  // print the result
  implicit  val duration = Timeout(3 seconds)
  val result = (counter ? GET ).mapTo[Int]

  result onComplete {
    case Success(result) => println("Got back " + result)
    case Failure(failure)  => println(" Got Exception")
  }
    system.shutdown
    system.awaitTermination

}
