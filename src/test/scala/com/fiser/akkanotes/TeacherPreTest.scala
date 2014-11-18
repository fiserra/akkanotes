package com.fiser.akkanotes

import akka.actor.ActorSystem
import akka.testkit.{TestActorRef, TestKit}
import com.fiser.akkanotes.protocols.TeacherProtocol.QuoteRequest
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpecLike}

class TeacherPreTest extends TestKit(ActorSystem("UniversityMessageSystem"))
with WordSpecLike
with MustMatchers
with BeforeAndAfterAll {

  "A teacher" must {
    "print a quote when a QuoteRequest is sent" in {
      val teacherRef = TestActorRef[TeacherActor]
      teacherRef ! QuoteRequest
    }
  }

  "A teacher with ActorLogging" must {
    "print a quote when a QuoteRequest is sent" in {
      val teacherRef = TestActorRef[TeacherLogActor]
      teacherRef ! QuoteRequest
    }
  }

  "have a quote list of size 4" in {
    val teacherRef = TestActorRef[TeacherLogActor]
    teacherRef.underlyingActor.quoteList must have size (4)
  }
}
