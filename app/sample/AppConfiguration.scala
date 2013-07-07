package sample

import akka.actor.ActorSystem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scala.context.function.FunctionalConfiguration
class AppConfiguration extends FunctionalConfiguration {



  /**
   * Actor system singleton for this application.
   */
 def actorSystem():ActorSystem = {
    ActorSystem system = ActorSystem.create("AkkaJavaSpring");
    // initialize the application context in the Akka Spring Extension
    SpringExtProvider.get(system).initialize(applicationContext);
    return system;
  }
}