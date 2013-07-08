package sample

import akka.actor.{ActorSystem, Props, Extension}
import org.springframework.context.ApplicationContext
/**
 * The Extension implementation.
 */
class SpringExtentionImpl extends Extension {
  var applicationContext: ApplicationContext = _

  /**
   * Used to initialize the Spring application context for the extension.
   * @param applicationContext
   */
  def initialize(implicit applicationContext: ApplicationContext) = {
    this.applicationContext = applicationContext
    this
  }

  /**
   * Create a Props for the specified actorBeanName using the
   * SpringActorProducer class.
   *
   * @param actorBeanName  The name of the actor bean to create Props for
   * @return a Props that will create the named actor bean using Spring
   */
  def props(actorBeanName: String): Props =
    Props(classOf[SpringActorProducer], applicationContext, actorBeanName)

}

object SpringExtentionImpl {

  def apply(system : ActorSystem) (implicit ctx: ApplicationContext ) :  SpringExtentionImpl =  SpringExtension().get(system).initialize
}

