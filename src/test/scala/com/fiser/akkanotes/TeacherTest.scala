package com.fiser.akkanotes

import akka.actor.ActorSystem
import akka.testkit.{EventFilter, TestActorRef, TestKit}
import com.fiser.akkanotes.protocols.TeacherProtocol.QuoteRequest
import com.typesafe.config.ConfigFactory
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpecLike}

class TeacherTest extends TestKit(ActorSystem("UniversityMessageSystem", ConfigFactory.parseString( """akka.loggers = ["akka.testkit.TestEventListener"]""")))
with WordSpecLike
with MustMatchers
with BeforeAndAfterAll {

  "A teacher" must {
    "be verifiable via EventFilter in response to QuoteRequest that is sent" in {
      val teacherRef = TestActorRef[TeacherLogActor]
      EventFilter.info(pattern = "QuoteResponse*", occurrences = 2) intercept {
        teacherRef ! QuoteRequest
      }
    }
  }
}
