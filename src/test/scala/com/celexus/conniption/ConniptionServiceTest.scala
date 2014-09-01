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
    var id = ""
    accounts.foreach {
      a: Account =>
        assertValued("Account ID", a.id)
        id = a.id
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
    val acOption: Option[Account] = srv.account(id)
    if (acOption.isDefined) {
      val acc: Account = acOption.get
      assertNotNull(acc.buyingPower)
      assertNotNull(acc.fedcall)
      assertNotNull(acc.funds)
      assertNotNull(acc.holdings)
      assertNotNull(acc.housecall)
      assertNotNull(acc.securities)
      assertNotNull(acc.value)
    }
    assertNotNull(srv.balance(id))
    assertNotNull(srv.status)
    val transactions: Set[Transaction] = srv.history(id)
    transactions.foreach {
      t: Transaction =>
        assertNotNull(t.activity)
        assertNotNull(t.amount)
        assertNotNull(t.comission)
        assertNotNull(t.date)
        assertNotNull(t.description)
        assertNotNull(t.fee)
        assertNotNull(t.price)
        assertNotNull(t.quantity)
        assertNotNull(t.secfee)
        assertNotNull(t.symbol)
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
        assertValued("Histories Activity", t.activity)
        assertValued("Histories Amouunt", t.amount)
        assertValued("Histories Commission", t.comission)
        assertValued("Histories Date", t.date)
        assertValued("Histories Description", t.description)
        assertValued("Histories Fee", t.fee)
        assertValued("Histories Price", t.price)
        assertValued("Histories Quantity", t.quantity)
        assertValued("Histories Sec Fee", t.secfee)
    }
  }

  @Test def holdings = {
    val holdings: Set[Holding] = srv.holdings
    assertValued("", holdings)
    holdings.foreach {
      h: Holding =>
        assertValued("Holding Asset Clss", h.assetClass)
        assertValued("Holding Cost Basis", h.costBasis)
        assertValued("Holding Description", h.description)
        assertValued("Holding gain/loss", h.gainLoss)
        assertValued("Holding Type", h.holdingType)
        assertValued("Holding Market Value", h.marketValue)
        assertValued("Holding Market Value Change", h.marketValueChange)
        assertValued("Holding Price", h.price)
        assertValued("Holding Purchase Price", h.purchasePrice)
        assertValued("Holding Quantity", h.quanity)
        assertValued("Holding Symbol", h.symbol)
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

  @Test def clock = assertValued("Market Clock", srv.clock)

  @Test def quotes = {
    val qs: Set[Quote] = srv.quotes(Set("IBM"))
    assertValued("", qs)
    qs.foreach {
      q: Quote =>
        assertValued("Quote Annual Dividend", q.annualDividend)
        assertValued("Quote Ask", q.ask)
        assertValued("Quote Ask Size", q.askSize)
        assertValued("Quote Ask Time", q.askTime)
        assertValued("Quote Avg 100 Day", q.avg100Day)
        assertValued("Quote Avg 200 Day", q.avg200Day)
        assertValued("Quote Avg 21 Vol", q.avg21Volume)
        assertValued("Quote Avg 30 Volume", q.avg30Volume)
        assertValued("Quote Avg 50 Day", q.avg50Day)
        assertValued("Quote Avg 90 Vol", q.avg90Volumne)
        assertValued("Quote Beta", q.beta)
        assertValued("Quote Bid", q.bid)
        assertValued("Quote Bid Size", q.bidSize)
        assertValued("Quote Bid Tick", q.bidTick)
        assertValued("Quote Bid Time", q.bidTime)
        assertValued("Quote Book Value", q.bookValue)
        assertTrue("Quote Can Trade", q.canTrade)
        assertValued("Quote Change", q.change)
        assertValued("Quote Change Sign", q.changeSign)
        assertValued("Quote Change Text", q.changeText)
        assertValued("Quote Close", q.close)
        assertValued("Quote Cusip", q.cusip)
        assertValued("Quote Date", q.date)
        assertValued("Quote Divexdate", q.divexdate)
    }
  }

  @Test def timesales = {
    val sales: Seq[Quote] = srv.timesales(Set("IBM"))
    assertValued("Timesales", sales)
    sales.foreach {
      q: Quote =>
        assertValued("Timesales Quote", q)
    }
  }

  @Test def list = {
    val l: Seq[Quote] = srv.list()
    assertValued("List", l)
    l.foreach {
      q: Quote =>
        assertValued("List Quote", q)
    }
  }

  @Test def profile = {
    val p: Profile = srv.profile
    assertValued("Profile", p)
    assertTrue("Profile Disabled", !p.disabled)
    assertTrue("Profile Fund Trading", p.fundtrading)
    assertTrue("Profile Stock Trading", p.stockTrading)
  }

  @Test def version = assertEquals("Version", srv.version, "1.0-RC1")

  private def assertValued(err: String, a: Any) = {
    assertNotNull(err + " (is null)", a)
    assertTrue(err + " (is empty)", a.toString nonEmpty)
  }
}
