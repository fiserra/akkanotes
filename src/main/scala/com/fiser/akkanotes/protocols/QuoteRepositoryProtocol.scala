package com.fiser.akkanotes.protocols

object QuoteRepositoryProtocol {

  case class QuoteRepositoryRequest()

  case class QuoteRepositoryResponse(quotedString: String)

}
