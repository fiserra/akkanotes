package com.fiser.akkanotes

import akka.actor.{Actor, ActorLogging, PoisonPill}
import com.fiser.akkanotes.protocols.QuoteRepositoryProtocol._

import scala.util.Random

class QuoteRepositoryActor() extends Actor with ActorLogging {

  val quotes = List(
    "Moderation is for cowards",
    "Anything worth doing is worth overdoing",
    "The trouble is you think you have time",
    "You never gonna know if you never even try")

  var requestCount = 1

  def receive = {
    case QuoteRepositoryRequest =>
      if (requestCount > 3) {
        self ! PoisonPill
      } else {
        val quoteRepositoryResponse = QuoteRepositoryResponse(quotes(Random.nextInt(quotes.size)))
        log.info(s"QuoteRequest received in QuoteRepositoryActor. Sending response to Teacher Actor $quoteRepositoryResponse")
        requestCount = requestCount + 1
        sender() ! quoteRepositoryResponse
      }
  }
}
