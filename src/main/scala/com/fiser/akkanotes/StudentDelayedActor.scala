package com.fiser.akkanotes

import akka.actor.{Actor, ActorLogging, ActorRef}
import com.fiser.akkanotes.protocols.StudentProtocol.InitSignal
import com.fiser.akkanotes.protocols.TeacherProtocol.{QuoteRequest, QuoteResponse}

import scala.concurrent.duration._

class StudentDelayedActor(teacherActorRef: ActorRef) extends Actor with ActorLogging {
  def receive = {
    case InitSignal =>
      import context.dispatcher
      context.system.scheduler.scheduleOnce(5 seconds, teacherActorRef, QuoteRequest)

    case QuoteResponse(quoteString) =>
      log.info("Received QuoteResponse from Teacher")
      log.info(s"Printing from Student Actor $quoteString")

  }
}
