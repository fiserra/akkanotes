package com.fiser.akkanotes

import akka.actor.{Actor, ActorLogging, Props, Terminated}
import com.fiser.akkanotes.protocols.QuoteRepositoryProtocol.QuoteRepositoryRequest
import com.fiser.akkanotes.protocols.TeacherProtocol.QuoteRequest

class TeacherActorWatcher extends Actor with ActorLogging {

  val quoteRepositoryActor = context.actorOf(Props[QuoteRepositoryActor], "quoteRepositoryActor")
  context.watch(quoteRepositoryActor)


  def receive = {
    case QuoteRequest =>
      quoteRepositoryActor ! QuoteRepositoryRequest

    case Terminated(terminatedActorRef) =>
      log.error(s"Child Actor {$terminatedActorRef} Terminated")
  }
}
