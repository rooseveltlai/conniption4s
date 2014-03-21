package com.celexus.conniption

import org.scalatest.junit.AssertionsForJUnit
import org.junit.Test
import com.celexus.conniption.model._
import org.junit.Assert._

/**
 * Created by cam on 3/21/14.
 */
class FIXMLOrderTest extends AssertionsForJUnit {

  @Test def buyOrder = {
    //Example Place a day order to buy 1 share of F at market price on account 12345678.
    val o: String = new FIXMLOrder("12345678", "F").timInForce(TimeInForce.dayOrder).typ(OrderType.market).side(Side.buy).qty(1).toString
    assertTrue(o.contains("xmlns"))
    assertTrue(o.contains("TmInForce=\"0\""))
    assertTrue(o.contains("Typ=\"1\""))
    assertTrue(o.contains("Side=\"1\""))
    assertTrue(o.contains("Acct=\"12345678\""))
    assertTrue(o.contains("Sym=\"F\""))
    assertTrue(o.contains("<OrdQty Qty=\"1\"/>"))
  }

  @Test def sellOrder = {
    //Example Place a day order to sell 1 share of F at market price on account 12345678.
    val o: String = new FIXMLOrder("12345678", "IBM").timInForce(TimeInForce.dayOrder).typ(OrderType.market).side(Side.sell).qty(1).toString
    assertTrue(o.contains("xmlns"))
    assertTrue(o.contains("TmInForce=\"0\""))
    assertTrue(o.contains("Typ=\"1\""))
    assertTrue(o.contains("Side=\"2\""))
    assertTrue(o.contains("Acct=\"12345678\""))
    assertTrue(o.contains("Sym=\"IBM\""))
    assertTrue(o.contains("<OrdQty Qty=\"1\"/>"))
  }

  @Test def sellShort = {
    //Example Place a day order to sell short 1 share of F at $22.00 on account 12345678.
    val o: String = new FIXMLOrder("12345678", "F").timInForce(TimeInForce.dayOrder).typ(OrderType.limit).side(Side.sellShort).qty(1).price(22).toString
    assertTrue(o.contains("xmlns"))
    assertTrue(o.contains("TmInForce=\"0\""))
    assertTrue(o.contains("Typ=\"2\""))
    assertTrue(o.contains("Side=\"5\""))
    assertTrue(o.contains("Acct=\"12345678\""))
    assertTrue(o.contains("Sym=\"F\""))
    assertTrue(o.contains("Px=\"22.0\""))
    assertTrue(o.contains("<OrdQty Qty=\"1\"/>"))
  }

  @Test def buyToCover = {
    //Example Place a day order to buy to cover 1 share of F at $13 on account 12345678.
    val o: String = new FIXMLOrder("12345678", "F").timInForce(TimeInForce.dayOrder).typ(OrderType.limit).side(Side.buyToCover).qty(1).price(13).toString
    assertTrue(o.contains("xmlns"))
    assertTrue(o.contains("TmInForce=\"0\""))
    assertTrue(o.contains("Typ=\"2\""))
    assertTrue(o.contains("Side=\"1\""))
    assertTrue(o.contains("Acct=\"12345678\""))
    assertTrue(o.contains("Sym=\"F\""))
    assertTrue(o.contains("Px=\"13.0\""))
    assertTrue(o.contains("AcctTyp=\"5\""))
    assertTrue(o.contains("<OrdQty Qty=\"1\"/>"))
  }

  @Test def buyTStopDollar = {
    /*Example Place a .50 buy trailing stop on 1 share of IBM (market order will trigger if current price of IBM rises by .50 for current price. If IBM moves down, trigger price will also move down.*/
    val o: String = new FIXMLOrder("12345678", "IBM").timInForce(TimeInForce.dayOrder).typ(OrderType.trailingStop).stopAt(StopType.dollarValue(0.50)).side(Side.buy).qty(1).toString
    assertTrue(o.contains("xmlns"))
    assertTrue(o.contains("TmInForce=\"0\""))
    assertTrue(o.contains("Typ=\"P\""))
    assertTrue(o.contains("Side=\"1\""))
    assertTrue(o.contains("Acct=\"12345678\""))
    assertTrue(o.contains("OfstVal=\"0.5\""))
    assertTrue(o.contains("PegPxTyp=\"1\""))
    assertTrue(o.contains("OfstType=\"0\""))
    assertTrue(o.contains("Sym=\"IBM\""))
    assertTrue(o.contains("<OrdQty Qty=\"1\"/>"))
  }

  @Test def buyTStopPercentage = {
    //Example Place a 5% buy trailing stop on 1 share of COST (Note: OfstTyp="1" for percentage trailing stop vs. OfstTyp="0" for price value trailing stop).
    val o: String = new FIXMLOrder("12345678", "COST").timInForce(TimeInForce.dayOrder).typ(OrderType.trailingStop).stopAt(StopType.percentage(5)).side(Side.buy).qty(1).toString
    assertTrue(o.contains("xmlns"))
    assertTrue(o.contains("TmInForce=\"0\""))
    assertTrue(o.contains("Typ=\"P\""))
    assertTrue(o.contains("Side=\"1\""))
    assertTrue(o.contains("Acct=\"12345678\""))
    assertTrue(o.contains("OfstVal=\"5.0\""))
    assertTrue(o.contains("PegPxTyp=\"1\""))
    assertTrue(o.contains("OfstType=\"1\""))
    assertTrue(o.contains("Sym=\"COST\""))
    assertTrue(o.contains("<OrdQty Qty=\"1\"/>"))
  }

  @Test def sellTStopPercentage = {
    //Example Place a 2.00 sell trailing stop on 1 share of F.
    val o: String = new FIXMLOrder("12345678", "F").timInForce(TimeInForce.dayOrder).typ(OrderType.trailingStop).stopAt(StopType.dollarValue(-2.0)).side(Side.sell).qty(1).toString
    assertTrue(o.contains("xmlns"))
    assertTrue(o.contains("TmInForce=\"0\""))
    assertTrue(o.contains("Typ=\"P\""))
    assertTrue(o.contains("Side=\"2\""))
    assertTrue(o.contains("Acct=\"12345678\""))
    assertTrue(o.contains("OfstVal=\"-2.0\""))
    assertTrue(o.contains("PegPxTyp=\"1\""))
    assertTrue(o.contains("OfstType=\"0\""))
    assertTrue(o.contains("Sym=\"F\""))
    assertTrue(o.contains("<OrdQty Qty=\"1\"/>"))
  }

}
