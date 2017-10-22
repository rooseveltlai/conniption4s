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

class FIXMLOrder(val accId: String, val symbol: String) {
  private[this] var s: Side = null
  private[this] var o: OrderType = null
  private[this] var tif: TIF = null
  private[this] var qty: Int = 0
  private[this] var price: Double = 0.0
  private[this] var trail: TrailingStopValue = null


  def side(s: Side): FIXMLOrder = {
    this.s = s
    this
  }

  def typ(o: OrderType): FIXMLOrder = {
    this.o = o
    this
  }

  def timInForce(t: TIF): FIXMLOrder = {
    this.tif = t
    this
  }

  def qty(i: Int): FIXMLOrder = {
    this.qty = i
    this
  }

  def price(d: Double): FIXMLOrder = {
    this.price = d
    this
  }

  def stopAt(st: TrailingStopValue): FIXMLOrder = {
    trail = st
    this
  }

  def isValid: Boolean = {
    //required
    var req: Boolean = o != null && s != null && accId.nonEmpty && symbol.nonEmpty
    if (trail != null) {
      req = req && (trail.x != 0.0)
    }
    req
  }

  private[this] def orderParameters: Map[String, String] = {
    var m: Map[String, String] = Map()
    m += "TmInForce" -> tif.fixml
    m += "Typ" -> o.fixml
    m += "Side" -> s.fixml
    m += "Acct" -> accId
    if (price != 0.0) m += "Px" -> price.toString
    if (s == Side.buyToCover) m += "AcctTyp" -> 5.toString
    if (o == OrderType.trailingStop) m += "ExecInst" -> "a"
    m
  }

  private[this] def instrumentParameters: Map[String, String] = {
    var m: Map[String, String] = Map()
    m += "SecTyp" -> "CS"
    m += "Sym" -> symbol.toUpperCase
    m
  }

  private[this] def trailingStopParameters: Map[String, String] = {
    //<PegInstr OfstTyp="0" PegPxTyp="1" OfstVal=".50"/>
    var m: Map[String, String] = Map()
    m += "OfstVal" -> trail.x.toString
    m += "PegPxTyp" -> "1"
    trail match {
      case _: Percentage => m += "OfstType" -> "1"
      case _: DollarValue => m += "OfstType" -> "0"
    }
    m
  }

  override def toString: String = {
    var fixml: String = "<FIXML xmlns=\"http://www.fixprotocol.org/FIXML-5-0-SP2\">"
    if (isValid) {
      fixml += "<Order " + mapToXMLValues(orderParameters) + ">"
      if (o == OrderType.trailingStop) fixml += "<PegInstr " + mapToXMLValues(trailingStopParameters) + "/>"
      fixml += "<Instrmt " + mapToXMLValues(instrumentParameters) + "/>"
      fixml += "<OrdQty Qty=\"" + qty + "\"/>"
      fixml += "</Order>"
    }
    fixml += "</FIXML>"
    fixml
  }

  private def mapToXMLValues(m: Map[String, String]): String = {
    var xmlValues: String = ""
    m.foreach {
      p =>
        xmlValues += p._1 + "=\"" + p._2 + "\" "
    }
    xmlValues.trim
  }
}


object StopType {
  def percentage(x: Double): Percentage = new Percentage(x)

  def dollarValue(x: Double): DollarValue = new DollarValue(x)
}

object Side {
  def buy: Buy = new Buy

  def buyToCover: BuyToCover = new BuyToCover

  def sell: Sell = new Sell

  def sellShort: SellShort = new SellShort
}

object OrderType {
  def market: Market = new Market

  def limit: Limit = new Limit

  def stop: Stop = new Stop

  def stopLimit: StopLimit = new StopLimit

  def trailingStop: TrailingStop = new TrailingStop

}

object TimeInForce {

  def dayOrder: DayOrder = new DayOrder

  def goodTilCanelled: GTC = new GTC

  def marketClose: MarketClose = new MarketClose
}

abstract class TIF(val fixml: String = "")

case class DayOrder(override val fixml: String = "0") extends TIF

case class GTC(override val fixml: String = "1") extends TIF

case class MarketClose(override val fixml: String = "2") extends TIF

abstract class TrailingStopValue(val x: Double = 0.0)

case class Percentage(override val x: Double) extends TrailingStopValue

case class DollarValue(override val x: Double) extends TrailingStopValue

abstract class Side(val fixml: String = "")

case class Buy(override val fixml: String = "1") extends Side

case class BuyToCover(override val fixml: String = "1") extends Side

case class Sell(override val fixml: String = "2") extends Side

case class SellShort(override val fixml: String = "5") extends Side

abstract class OrderType(val fixml: String = "")

case class Market(override val fixml: String = "1") extends OrderType

case class Limit(override val fixml: String = "2") extends OrderType

case class Stop(override val fixml: String = "3") extends OrderType

case class StopLimit(override val fixml: String = "4") extends OrderType

case class TrailingStop(override val fixml: String = "P") extends OrderType
