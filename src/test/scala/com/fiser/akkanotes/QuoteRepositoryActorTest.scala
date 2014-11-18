package com.fiser.akkanotes

import akka.actor.ActorSystem
import akka.testkit._
import com.fiser.akkanotes.protocols.QuoteRepositoryProtocol.{QuoteRepositoryRequest, QuoteRepositoryResponse}
import com.fiser.akkanotes.protocols.TeacherProtocol.QuoteRequest
import com.typesafe.config.ConfigFactory
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpecLike}

import scala.concurrent.duration._

class QuoteRepositoryActorTest extends TestKit(ActorSystem("TestUniversityMessageSystem", ConfigFactory.parseString( """
                                            akka{
                                              loggers = ["akka.testkit.TestEventListener"]
                                              test{
                                                  filter-leeway = 7s
                                              }
                                            }
                                                                                                                     """)))
with WordSpecLike
with MustMatchers
with BeforeAndAfterAll
with ImplicitSender {

  "A QuoteRepositoryActor" must {

    "send back a termination message to the watcher after the 4th message" in {
      val quoteRepository = TestActorRef[QuoteRepositoryActor]
      val testProbe = TestProbe()
      testProbe.watch(quoteRepository)

      within(1000 millis) {
        var receivedQuotes = List[String]()

        (1 to 3).foreach(_ => quoteRepository ! QuoteRepositoryRequest)
        receiveWhile() {
          case QuoteRepositoryResponse(quoteString) =>
            receivedQuotes = receivedQuotes :+ quoteString
        }

        receivedQuotes.size must be(3)
        quoteRepository ! QuoteRepositoryRequest
        testProbe.expectTerminated(quoteRepository)
      }
    }
    " end back a termination message to the watcher on 4th message to the TeacherActor" in {

      val teacherActor = TestActorRef[TeacherActorWatcher]

      within(1000 millis) {
        (1 to 3).foreach(_ => teacherActor ! QuoteRequest)

        EventFilter.error(pattern = """Child Actor .* Terminated""", occurrences = 1).intercept {
          teacherActor ! QuoteRequest //Send the dangerous 4th message
        }
      }


    }
  }
}
