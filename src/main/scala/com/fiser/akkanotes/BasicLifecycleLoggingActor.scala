package com.fiser.akkanotes

import akka.actor.{Actor, ActorLogging}

class BasicLifecycleLoggingActor extends Actor with ActorLogging {

  log.info(s"Inside ${getClass.getSimpleName} constructor")
  log.info(context.self.toString())

  override def preStart(): Unit = {
    log.info(s"Inside preStart ${getClass.getSimpleName}")
  }

  def receive = {
    case "hello" => log.info("hello")
    case "stop" =>
      log.info("stop")
      context.stop(self)
  }

  override def postStop(): Unit = {
    log.info(s"Inside postStop ${getClass.getSimpleName}")
  }
}
