package com.fiser.akkanotes

import akka.actor.{Actor, ActorLogging, ActorRef}
import com.fiser.akkanotes.protocols.StudentProtocol.InitSignal
import com.fiser.akkanotes.protocols.TeacherProtocol.{QuoteRequest, QuoteResponse}

class StudentActor(ref: ActorRef) extends Actor with ActorLogging {

  def receive = {
    case InitSignal => ref ! QuoteRequest
    case QuoteResponse(quoteString) =>
      log.info("Received QuoteResponse from Teacher")
      log.info(s"Printing from Student Actor $quoteString")
  }

}
