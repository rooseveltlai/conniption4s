package com.celexus.conniption

import org.scalatest.junit.AssertionsForJUnit
import org.junit.Test
import org.junit.Assert._

class TradeKingAPITest extends AssertionsForJUnit {

  @Test
  def params = {
    val tk = new TradeKingAPI
    assertEquals(tk.getAccessTokenEndpoint, "https://developers.tradeking.com/oauth/access_token")
    assertEquals(tk.getAuthorizationUrl(null), "https://developers.tradeking.com/oauth/authorize?oauth_token=%s")
    assertEquals(tk.getRequestTokenEndpoint, "https://developers.tradeking.com/oauth/request_token")
  }
}
