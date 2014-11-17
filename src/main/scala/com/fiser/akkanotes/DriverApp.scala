package com.fiser.akkanotes

import akka.actor.{Props, ActorSystem}
import com.fiser.akkanotes.protocols.StudentProtocol._

object DriverApp extends App {
  val actorSystem = ActorSystem("UniversityMessageSystem")

  val teacherActorRef = actorSystem.actorOf(Props[TeacherActor], "teacherActor")

  val studentActorRef = actorSystem.actorOf(Props(new StudentActor(teacherActorRef)), "studentActor")

  studentActorRef ! InitSignal

  Thread.sleep(2000)

  actorSystem.shutdown()
}
