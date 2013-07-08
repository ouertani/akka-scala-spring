package sample

import akka.actor.ActorSystem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scala.context.function.FunctionalConfiguration
import SpringExtension.springExtProvider
import org.springframework.beans.factory.config.BeanDefinition

class AppConfiguration extends FunctionalConfiguration {
  val ctx = beanFactory.asInstanceOf[ApplicationContext]

  /**
   * Actor system singleton for this application.
   */
  val actorSystem = bean() {
    val system = ActorSystem("AkkaScalaSpring")
    // initialize the application context in the Akka Spring Extension
    springExtProvider.get(system).initialize(ctx)
    system
  }

  val countingService = bean() {
    new CountingService
  }

  val countingActor = bean("countingActor", aliases = Seq("CountingActor"), scope = BeanDefinition.SCOPE_PROTOTYPE) {
    val ca = new CountingActor
    ca.countingService = countingService()
    ca
  }
}