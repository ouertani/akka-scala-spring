package sample

import akka.actor._

import org.springframework.context.ApplicationContext




object SpringExtension {
  /**
   * The identifier used to access the SpringExtension.
   */
  def apply() : SpringExtension= new SpringExtension
}

class SpringExtension extends AbstractExtensionId[SpringExtentionImpl] {
    import SpringExtension._
  /**
   * Is used by Akka to instantiate the Extension identified by this
   * ExtensionId, internal use only.
   */
  override def createExtension(system: ExtendedActorSystem) = new SpringExtentionImpl

  /**
   * Java API: retrieve the SpringExt extension for the given system.
   */
  override def get(system: ActorSystem): SpringExtentionImpl = super.get(system)

}

