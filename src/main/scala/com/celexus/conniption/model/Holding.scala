package com.celexus.conniption.model

import scala.xml.NodeSeq

/**
 * Created by cam on 3/16/14.
 */
class Holding(xml: NodeSeq) extends TKResponse(xml: NodeSeq, format = "xml") {

  def holdingType: String = ends("holding/displaydata/accounttype")

  def assetClass: String = ends("holding/displaydata/assetclass")

  def description: String = ends("holding/displaydata/desc")

  def symbol: String = ends("holding/displaydata/symbol")

  def costBasis: Double = toDouble(ends("holding/costbasis"))

  def gainLoss: Double = toDouble(ends("holding/gainloss"))

  def marketValue: Double = toDouble(ends("holding/marketvalue"))

  def marketValueChange: Double = toDouble(ends("holding/marketvaluechange"))

  def price: Double = toDouble(ends("holding/price"))

  def purchasePrice: Double = toDouble(ends("holding/purchaseprice"))

  def quanity: Double = toDouble(ends("holding/qty"))
}
