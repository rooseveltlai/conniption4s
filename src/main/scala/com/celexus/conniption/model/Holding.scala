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

class Holding(xml: NodeSeq, res: org.scribe.model.Response) extends TKResponse(xml: NodeSeq, res: org.scribe.model.Response, format = "xml") {

  /**
   * Holdings attribute for where asset as held, "1"= cash, "2"= margin long, "5"=margin short
   * @return
   */
  //TODO FIXME
  def holdingType: String = ends("holding/accounttype")

  /**
   * Holding asset class type
   * @return
   */
  def assetClass: String = ends("holding/instrument/sectyp")

  /**
   * Instrument description
   * @return
   */
  def description: String = ends("holding/instrument/desc")

  /**
   * Holding underlying symbol
   * @return
   */
  def symbol: String = ends("holding/instrument/sym")

  /**
   * Holding cost basis
   * @return
   */
  def costBasis: Double = toDouble(ends("holding/costbasis"))

  /**
   * Holding gain/loss overall
   * @return
   */
  def gainLoss: Double = toDouble(ends("holding/gainloss"))

  /**
   * Holding market value
   * @return
   */
  def marketValue: Double = toDouble(ends("holding/marketvalue"))

  /**
   * Holding market value change
   * @return
   */
  def marketValueChange: Double = toDouble(ends("holding/marketvaluechange"))

  /**
   * Instrument price
   * @return
   */
  def price: Double = toDouble(ends("holding/price"))

  /**
   * Holding purchase price
   * @return
   */
  def purchasePrice: Double = toDouble(ends("holding/purchaseprice"))

  /**
   * Holding quantity
   * @return
   */
  def quanity: Double = toDouble(ends("holding/qty"))
}
