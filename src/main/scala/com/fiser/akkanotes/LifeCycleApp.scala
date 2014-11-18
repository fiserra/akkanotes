package com.fiser.akkanotes

import akka.actor.{ActorSystem, DeadLetter, PoisonPill, Props}

object LifeCycleApp extends App {
  val actorSystem = ActorSystem("LifeCycleActorSystem")

  val lifeCycleActor = actorSystem.actorOf(Props[BasicLifecycleLoggingActor], "lifeCycleActor")

  println(lifeCycleActor.path)

  val deadLetterListener = actorSystem.actorOf(Props[MyCustomDeadLetterListener], "deadLetterListener")
  println(deadLetterListener.path)
  actorSystem.eventStream.subscribe(deadLetterListener, classOf[DeadLetter])

  lifeCycleActor ! "hello"
  lifeCycleActor ! PoisonPill
  lifeCycleActor ! "stop"

  Thread.sleep(2000)

  actorSystem.shutdown()
}
