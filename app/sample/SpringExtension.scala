package sample

import akka.actor._

import org.springframework.context.ApplicationContext




/**
 * The Extension implementation.
 */
class SpringExt  extends Extension   {
  var applicationContext: ApplicationContext = _
  /**
   * Used to initialize the Spring application context for the extension.
   * @param applicationContext
   */
  def initialize( applicationContext: ApplicationContext) = {
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

object SpringExtension {
  /**
   * The identifier used to access the SpringExtension.
   */
  def springExtProvider  = new SpringExtension
}

class SpringExtension extends AbstractExtensionId[SpringExt] {
    import  SpringExtension._

  /**
   * Is used by Akka to instantiate the Extension identified by this
   * ExtensionId, internal use only.
   */
  override def createExtension(system: ExtendedActorSystem) = new SpringExt

  /**
   * Java API: retrieve the SpringExt extension for the given system.
   */
  override def get(system: ActorSystem): SpringExt = super.get(system)

}

