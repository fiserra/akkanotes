package com.fiser.akkanotes

import akka.actor.{Actor, DeadLetter}

class MyCustomDeadLetterListener extends Actor {
  def receive = {
    case deadLetter: DeadLetter => println(s"FROM CUSTOM LISTENER $deadLetter")
  }
}
