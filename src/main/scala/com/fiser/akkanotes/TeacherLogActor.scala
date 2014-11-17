package com.fiser.akkanotes

import akka.actor.{Actor, ActorLogging}
import com.fiser.akkanotes.protocols.TeacherProtocol.{QuoteRequest, QuoteResponse}

import scala.util.Random

class TeacherLogActor extends Actor with ActorLogging {

  val quotes = List(
    "Moderation is for cowards",
    "Anything worth doing is worth overdoing",
    "The trouble is you think you have time",
    "You never gonna know if you never even try")

  override def receive: Receive = {
    case QuoteRequest =>
      val quoteResponse = QuoteResponse(quotes(Random.nextInt(quotes.size)))
      log.info(quoteResponse.toString)
      sender() ! quoteResponse
  }

  def quoteList = quotes
}
