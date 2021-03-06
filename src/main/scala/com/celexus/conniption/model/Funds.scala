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

import scala.xml.NodeSeq

/**
 * Model representing all Funds for an Account
 * @param xml Underlying format, default XML
 */
class Funds(xml: NodeSeq, res: org.scribe.model.Response) extends TKResponse(xml: NodeSeq, res: org.scribe.model.Response, format = "xml") {

  /**
   * Amount of any accrued interest on the account
   * @return
   */
  def accruedInterest: Double = toDouble(ends("money/accruedinterest"))

  /**
   * Cash
   * @return
   */
  def cashTotal: Double = toDouble(ends("money/cash"))

  /**
   * Cash available
   * @return
   */
  def cashAvailable: Double = toDouble(ends("money/cashavailable"))

  /**
   * Margin balance (- indicates debit balance, + indicates credit balance)
   * @return
   */
  def marginBalance: Double = toDouble(ends("money/marginbalance"))

  /**
   * Money market fund
   * @return
   */
  def moneyMarketFundBalance: Double = toDouble(ends("money/mmf"))

  /**
   * Total cash balance
   * @return
   */
  def total: Double = toDouble(ends("money/total"))

  /**
   * Uncleared deposits
   * @return
   */
  def unclearedDeposits: Double = toDouble(ends("money/uncleareddeposits"))

  /**
   * Unsettled funds
   * @return
   */
  def unsettled: Double = toDouble(ends("money/unsettledfunds"))

  /**
   * Yield
   * @return
   */
  def fundsYield: Double = toDouble(ends("money/yield"))
}
