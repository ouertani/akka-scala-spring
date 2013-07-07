package sample

import akka.actor.{ExtendedActorSystem, Props, Extension, AbstractExtensionId}

import org.springframework.context.ApplicationContext


object SpringExtension {
  /**
   * The identifier used to access the SpringExtension.
   */
  def springExtProvider(implicit applicationContext: ApplicationContext) = new SpringExtension(applicationContext)
}

class SpringExtension(applicationContext: ApplicationContext) extends AbstractExtensionId[SpringExt] {
    import  SpringExtension._

  /**
   * Is used by Akka to instantiate the Extension identified by this
   * ExtensionId, internal use only.
   */
  def createExtension(system: ExtendedActorSystem) = new SpringExt(applicationContext)

}

/**
 * The Extension implementation.
 */
class SpringExt(applicationContext: ApplicationContext) extends Extension {

  /**
   * Create a Props for the specified actorBeanName using the
   * SpringActorProducer class.
   *
   * @param actorBeanName  The name of the actor bean to create Props for
   * @return a Props that will create the named actor bean using Spring
   */
  def props(actorBeanName: String): Props = {
    return Props(classOf[SpringActorProducer], applicationContext, actorBeanName)
  }
}
