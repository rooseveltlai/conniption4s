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
 *  Model representing all Funds for an Account
 * @param xml Underlying format, default XML
 */
class Funds(xml: NodeSeq) extends TKResponse(xml: NodeSeq, format = "xml") {

  def accruedInterest: Double = toDouble(ends("money/accruedinterest"))

  def cashTotal: Double = toDouble(ends("money/cash"))

  def cashAvailable: Double = toDouble(ends("money/cashavailable"))

  def marginBalance: Double = toDouble(ends("money/marginbalance"))

  def moneyMarketFundBalance: Double = toDouble(ends("money/mmf"))

  def total: Double = toDouble(ends("money/total"))

  def unclearedDeposits: Double = toDouble(ends("money/uncleareddeposits"))

  def unsettled: Double = toDouble(ends("money/unsettledfunds"))

  def fundsYield: Double = toDouble(ends("money/yield"))
}
