package com.fiser.akkanotes.protocols

object TeacherProtocol {

  case class QuoteRequest()

  case class QuoteResponse(quotedString: String)

}
