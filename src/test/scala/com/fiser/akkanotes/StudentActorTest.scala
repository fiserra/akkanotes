package com.fiser.akkanotes

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, EventFilter, TestKit}
import com.fiser.akkanotes.protocols.StudentProtocol.InitSignal
import com.typesafe.config.ConfigFactory
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpecLike}

class StudentActorTest extends TestKit(ActorSystem("TestUniversityMessageSystem", ConfigFactory.parseString("""
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

  "A student" must {

    "log a QuoteResponse eventually when an InitSignal is send to it" in {
      val teacherRef = system.actorOf(Props[TeacherActor], "teacherActor")
      val studentRef = system.actorOf(Props(new StudentActor(teacherRef)), "studentActor")

      EventFilter.info(start = "Printing from Student Actor", occurrences = 1).intercept {
        studentRef ! InitSignal
      }
    }
  }

  "A delayed student" must {
    "fire the QuoteResponse after 5 seconds when InitialSignal is sent to it" in {
      val teacherRef = system.actorOf(Props[TeacherActor], "teacherActorDelayed")
      val studentRef = system.actorOf(Props(new StudentDelayedActor(teacherRef)), "studentDelayedActor")

      EventFilter.info(start = "Printing from Student Actor", occurrences = 1).intercept {
        studentRef ! InitSignal
      }
    }
  }
}
