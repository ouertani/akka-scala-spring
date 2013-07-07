package sample

import akka.actor.{Actor, IndirectActorProducer}
import org.springframework.context.ApplicationContext


class SpringActorProducer( applicationContext : ApplicationContext,actorBeanName : String )  extends IndirectActorProducer {

def  produce() : Actor =
  applicationContext.getBean(actorBeanName).asInstanceOf[Actor]


def actorClass()  : Class[_ <: Actor]=
    return  applicationContext.getType(actorBeanName).asInstanceOf[Class[_ <: Actor]]

}
