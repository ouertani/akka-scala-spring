package sample

import scala.concurrent.{Await, ExecutionContext, future, promise}

import akka.actor.{ActorRef, ActorSystem}
import org.springframework.scala.context.function.FunctionalConfigApplicationContext

import akka.actor.{ActorRef, ActorSystem}
import sample.SpringExtension._

import akka.pattern.ask
import scala.concurrent._
import scala.util._
import org.springframework.scala.context.function._
import org.specs2.mutable._
import org.junit.runner._
import org.specs2.runner._
import scala.concurrent.Await
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import  CountingActor._

@RunWith(classOf[JUnitRunner])
class SpringTest extends Specification{


   "Simple test" should {

      "Fire 3 COUNT "    in  {
        // create a spring context
        implicit val ctx = FunctionalConfigApplicationContext(classOf[TestAppConfiguration])

        import Config._
        // get hold of the actor system
        val system = ctx.getBean(classOf[ActorSystem])

        val prop = SpringExtentionImpl(system).props("countingActor")

        // use the Spring Extension to create props for a named actor bean
        val counter: ActorRef = system.actorOf(prop, "counter")

        // tell it to count three times
        counter ! COUNT
        counter ! COUNT
        counter ! COUNT

        // check the result
        val result = counter ? GET
        Await.result(result,  duration) .asInstanceOf[Int] must beEqualTo(3)
        val  testService = ctx.getBean(classOf[TestCountingService])
        testService.getNumberOfCalls must beEqualTo(3)

        // shut down the actor system
        system.shutdown();
        system.awaitTermination();
      }
   }

}
