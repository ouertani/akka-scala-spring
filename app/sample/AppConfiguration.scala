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

  /**
   * Actor system singleton for this application.
   */
 val actorSystem = bean() {
    val system = ActorSystem.create("AkkaJavaSpring")
    // initialize the application context in the Akka Spring Extension
    springExtProvider(beanFactory.asInstanceOf[ApplicationContext]).get(system)
    system
  }

  val countingService = bean () {
    new CountingService
  }
  val countingActor = bean(BeanDefinition.SCOPE_PROTOTYPE) {
    val ca = new CountingActor
    ca.countingService = countingService()
    ca
  }
}