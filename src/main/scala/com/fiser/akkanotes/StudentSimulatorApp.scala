package com.fiser.akkanotes

import akka.actor.{ActorSystem, Props}
import com.fiser.akkanotes.protocols.TeacherProtocol._

object StudentSimulatorApp extends App {
  val actorSystem = ActorSystem("UniversityMessageSystem")

  val teacherActorRef = actorSystem.actorOf(Props[TeacherActor])

  teacherActorRef ! QuoteRequest

  Thread.sleep(2000)

  actorSystem.shutdown()
}
