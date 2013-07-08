package sample

import akka.actor.ActorSystem
import org.springframework.context.ApplicationContext
import org.springframework.scala.context.function.FunctionalConfiguration
import org.springframework.beans.factory.config.BeanDefinition



class AppConfiguration extends FunctionalConfiguration {
  val ctx = beanFactory.asInstanceOf[ApplicationContext]

  /**
   * Actor system singleton for this application.
   */
  val actorSystem = bean() {
    val system = ActorSystem("AkkaScalaSpring")
    // initialize the application context in the Akka Spring Extension
    SpringExtension().get(system).initialize(ctx)
    system
  }

  val countingService = bean() {
    new CountingService
  }

  val countingActor = bean("countingActor",  scope = BeanDefinition.SCOPE_PROTOTYPE) {
    val ca = new CountingActor
    ca.countingService = countingService()
    ca
  }
}