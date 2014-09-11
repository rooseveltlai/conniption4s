package com.celexus.conniption.model

import scala.xml.NodeSeq

class Holding(xml: NodeSeq, res: org.scribe.model.Response) extends TKResponse(xml: NodeSeq, res: org.scribe.model.Response, format = "xml") {

  /**
   * Holdings attribute for where asset as held, "1"= cash, "2"= margin long, "5"=margin short
   * @return
   */
  //TODO FIXME
  def holdingType: String = ends("holding/displaydata/accounttype")

  /**
   * Holding asset class type
   * @return
   */
  def assetClass: String = ends("holding/displaydata/assetclass")

  /**
   * Instrument description
   * @return
   */
  def description: String = ends("holding/displaydata/desc")

  /**
   * Holding underlying symbol
   * @return
   */
  def symbol: String = ends("holding/displaydata/symbol")

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
