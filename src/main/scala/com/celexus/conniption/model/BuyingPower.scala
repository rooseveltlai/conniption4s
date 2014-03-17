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
 * A model representing an Account's buying power generalizations
 * @param xml
 */
class BuyingPower(xml: NodeSeq) extends TKResponse(xml: NodeSeq, format = "xml") {

  /**
   * @return Cash Available for Withdrawl
   */
  def cashAvailableForWithdrawl: Double = toDouble(ends("buyingpower/cashavailableforwithdrawal"))

  /**
   * @return Day Trading Buying Power
    */
  def daytrading: Double = toDouble(ends("buyingpower/daytading"))

  /**
   * @return Percentage of Equity available
   */
  def equityPercentage: Double = toDouble(ends("buyingpower/equitypercentage"))

  /**
   * @return Options buying power
   */
  def options: Double = toDouble(ends("buyingpower/options"))

  /**
   * @return day trading buying power at the start of the day
   */
  def dayStartDayTrading: Double = toDouble(ends("buyingpower/soddaytrading"))

  /**
   * @return stock buying power at the start of the day
   */
  def dayStartStock: Double = toDouble(ends("buyingpower/sodsock"))

  /**
   * @return stock buying power
   */
  def stock: Double = toDouble(ends("buyingpower/stock"))
}
