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
package com.celexus.conniption.model

import scala.xml._

/**
 * Model for TradeKing Accounts
 * @param xml underlying format to use, by default XML
 */
class Account(xml: NodeSeq, res: org.scribe.model.Response) extends TKResponse(xml: NodeSeq, res: org.scribe.model.Response, format = "xml") {

  /**
   * @return The ID of this Account
   */
  def id: String = ends("accountbalance/account")

  /**
   * @return The total Account Value
   */
  def value: Double = ends("accountbalance/accountvalue").toDouble

  /**
   * @return A BuyingPower object which contains what types of usuable funds there are
   */
  def buyingPower: BuyingPower = {
    val buyingPowerXML: NodeSeq = (xml \ "buyingpower")
    new BuyingPower(buyingPowerXML,res)
  }

  /**
   * A special type of margin call requiring a trader to deposit sufficient cash in order to meet federal requirements on the amount of credit that brokers may extend. These margin requirements are set by Regulation T of the Code of Federal Regulations, Title 12 - Banks and Banking. Currently the margin requirements are 50% for equities. For short sales, the margin requirement is between 100% and 150% of the current market value of the security being sold short. Regulatory authorities has the power to change these margin requirements as they deem necessary.
   * @return
   */
  def fedcall: Double = ends("accountbalance/fedcall").toDouble

  /**
   * A brokerage house notification that the customer's equity in a margin account has fallen below the maintenance requirement level. If the client fails to immediately deliver the required margin by depositing more funds or securities into the account, his or her position will be liquidated. Also known as a "margin call"
   * @return
   */
  def housecall: Double = ends("accountbalance/housecall").toDouble

  /**
   * @return A funds object which returns account balances, currently accessible or not
   */
  def funds: Funds = {
    val moneyXML: NodeSeq = (xml \ "money")
    new Funds(moneyXML,res)
  }

  /**
   * @return A securities object which generalizes info about all the Securities held
   */
  def securities: Securities = {
    val securitiesXML: NodeSeq = (xml \ "securities")
    new Securities(securitiesXML,res)
  }

  /**
   * @return A Set of Holdings object to describe holdings the account has
   */
  def holdings: Set[Holding] = {
    val holdingsXML: NodeSeq = (xml \ "accountholdings" \ "holding")
    var holds: Set[Holding] = Set()
    holdingsXML.foreach {
      n: NodeSeq => holds += new Holding(n,res)
    }
    holds
  }

}