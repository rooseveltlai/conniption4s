/**
 * Copyright 2014 Cameron Cook
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.celexus.conniption

import org.scribe.builder._
import org.scribe.oauth.OAuthService
import org.scribe.model._

/**
 * A Helper class to perform OAuth transactions to Tradeking (TradeKingAPI is in src/java)
 */
class TKService {
  val service: OAuthService = new ServiceBuilder().provider(new com.celexus.conniption.TradeKingAPI().getClass())
    .apiKey(ConniptionConstants.API_KEY).apiSecret(ConniptionConstants.API_SECRET).signatureType(SignatureType.Header).build()

  /**
   * The Auth Token
   * @return
   */
  def token: Token = new Token(ConniptionConstants.ACCESS_TOKEN, ConniptionConstants.ACCESS_TOKEN_SECRET)

  /**
   * The Authorization URL
   * @return
   */
  def authURL: String = service.getAuthorizationUrl(token)

  /**
   * Make a OAuth request
   * @param v Verb (GET or POST, usually
   * @param url url to access
   * @param parameters query string parameters
   * @param payload body payload
   * @return OAuth Response
   */
  def request(v: Verb, url: String, parameters: Map[String, String] = Map(), payload: String = null): Response = {
    val req: OAuthRequest = new OAuthRequest(v, url)
    parameters.foreach(p => req.addQuerystringParameter(p._1, p._2))
    if (!payload.eq(null)) {
      req.addHeader("Content-Length", payload.length.toString)
      req.addHeader("Content-Type", "text/xml")
      req.addHeader("TKI_OVERRIDE","true")
      req.addPayload(payload)
    }
    service.signRequest(token, req)
    req.send
  }
}
