package com.celexus.conniption

import org.scalatest.junit.AssertionsForJUnit
import org.junit._
import com.celexus.conniption.model._
import org.junit.Assert._

class ConniptionServiceTest extends AssertionsForJUnit {
  private val srv: ConniptionService = new ConniptionService()

  @Test def accounts = {
    val accounts: Set[Account] = srv.accounts
    assertValued("Accounts Set", accounts)
    accounts.foreach {
      a: Account =>
        assertValued("Account ID", a.id)
        assertValued("Account Fedcall", a.fedcall)
        assertValued("Account Housecall", a.housecall)
        assertValued("Account Value", a.value)
        val sec: Securities = a.securities
        assertValued("Account Securities Long Options", sec.longOptions)
        assertValued("Account Securities Long Stocks", sec.longStocks)
        assertValued("Account Securities Options", sec.options)
        assertValued("Account Securities Short Stocks", sec.shortstocks)
        assertValued("Account Securities Stocks", sec.stocks)
        assertValued("Account Securities Total", sec.total)
    }
  }

  @Test def balances = {
    val balances: Map[String, Double] = srv.balances
    assertValued("Balances", balances)
    balances.foreach {
      p =>
        assertValued("Key, Balances", p._1)
        assertValued("Value, Balances", p._2)
    }
  }

  @Test def histories = {
    val histories: Set[Transaction] = srv.histories()
    assertValued("Histories", histories)
    histories.foreach {
      t: Transaction =>
        assertValued("", t.activity)
        assertValued("", t.amount)
        assertValued("", t.comission)
        assertValued("", t.date)
        assertValued("", t.description)
        assertValued("", t.fee)
        assertValued("", t.price)
        assertValued("", t.quantity)
        assertValued("", t.secfee)
    }
  }

  @Test def holdings = {
    val holdings: Set[Holding] = srv.holdings
    assertValued("", holdings)
    holdings.foreach {
      h: Holding =>
        assertValued("", h.assetClass)
        assertValued("", h.costBasis)
        assertValued("", h.description)
        assertValued("", h.gainLoss)
        assertValued("", h.holdingType)
        assertValued("", h.marketValue)
        assertValued("", h.marketValueChange)
        assertValued("", h.price)
        assertValued("", h.purchasePrice)
        assertValued("", h.quanity)
        assertValued("", h.symbol)
    }
  }

  @Test def orders = {
    val ords: Set[Order] = srv.orders
    assertValued("", ords)
    ords.foreach {
      o: Order =>
      //TODO
    }
  }

  @Test def clock = assertValued("", srv.clock)

  @Test def quotes = {
    val qs: Set[Quote] = srv.quotes(Set("IBM"))
    assertValued("", qs)
    qs.foreach {
      q: Quote =>
        assertValued("", q.annualDividend)
        assertValued("", q.ask)
        assertValued("", q.askSize)
        assertValued("", q.askTime)
        assertValued("", q.avg100Day)
        assertValued("", q.avg200Day)
        assertValued("", q.avg21Volume)
        assertValued("", q.avg30Volume)
        assertValued("", q.avg50Day)
        assertValued("", q.avg90Volumne)
        assertValued("", q.beta)
        assertValued("", q.bid)
        assertValued("", q.bidSize)
        assertValued("", q.bidTick)
        assertValued("", q.bidTime)
        assertValued("", q.bookValue)
        assertTrue("", q.canTrade)
        assertValued("", q.change)
        assertValued("", q.changeSign)
        assertValued("", q.changeText)
        assertValued("", q.close)
        assertValued("", q.cusip)
        assertValued("", q.date)
        assertValued("", q.divexdate)
    }
  }

  @Test def timesales = {
    val sales: Seq[Quote] = srv.timesales(Set("IBM"))
    assertValued("", sales)
    sales.foreach {
      q: Quote =>
        assertValued("", q)
    }
  }

  @Test def list = {
    val l: Seq[Quote] = srv.list()
    assertValued("", l)
    l.foreach {
      q: Quote =>
        assertValued("", q)
    }
  }

  @Test def profile = {
    val p: Profile = srv.profile
    assertValued("", p)
    assertTrue(!p.disabled)
    assertTrue(p.fundtrading)
    assertTrue(p.stockTrading)
  }

  @Test def version = assertEquals(srv.version, "1.0-RC1")

  private def assertValued(err: String, a: Any) = {
    assertNotNull(err + " (is null)", a)
    assertTrue(err + " (is empty)", a.toString nonEmpty)
  }
}
