package com.celexus.conniption

import org.scalatest.junit.AssertionsForJUnit
import org.junit.Test
import org.junit.Assert._
import com.celexus.conniption.model.Quote

class QuoteTest extends AssertionsForJUnit {

  val srv: ConniptionService = new ConniptionService()

  @Test
  def quote = {
    val quotes = srv.quotes(Set("IBM"))
    quotes.foreach {
      q: Quote =>
        assertNotNull(q.annualDividend)
        assertNotNull(q.ask)
        assertNotNull(q.askSize)
        assertNotNull(q.askTime)
        assertNotNull(q.avg100Day)
        assertNotNull(q.avg200Day)
        assertNotNull(q.avg21Volume)
        assertNotNull(q.avg30Volume)
        assertNotNull(q.avg50Day)
        assertNotNull(q.avg90Volumne)
        assertNotNull(q.beta)
        assertNotNull(q.bid)
        assertNotNull(q.bidSize)
        assertNotNull(q.bidTick)
        assertNotNull(q.bidTime)
        assertNotNull(q.bookValue)
        assertNotNull(q.canTrade)
        assertNotNull(q.change)
        assertNotNull(q.changeSign)
        assertNotNull(q.changeText)
        assertNotNull(q.close)
        assertNotNull(q.cusip)
        assertNotNull(q.date)
        assertNotNull(q.divexdate)
        assertNotNull(q.dividend)
        assertNotNull(q.dividendPayDate)
        assertNotNull(q.dollarValue)
        assertNotNull(q.earningsPerShare)
        assertNotNull(q.exchange)
        assertNotNull(q.exchangeDescription)
        assertNotNull(q.hasOptions)
        assertNotNull(q.high)
        assertNotNull(q.high52Week)
        assertNotNull(q.last)
        assertNotNull(q.lastTradeVolume)
        assertNotNull(q.low)
        assertNotNull(q.low52Week)
        assertNotNull(q.low52WeekDate)
        assertNotNull(q.name)
        assertNotNull(q.openTradePrice)
        assertNotNull(q.outstanding)
        assertNotNull(q.percentChange)
        assertNotNull(q.percentChangeSign)
        assertNotNull(q.precision)
        assertNotNull(q.prevAvg100Day)
        assertNotNull(q.prevAvg200Day)
        assertNotNull(q.prevAvg50Day)
        assertNotNull(q.prevChange)
        assertNotNull(q.prevClose)
        assertNotNull(q.prevHigh)
        assertNotNull(q.prevLow)
        assertNotNull(q.prevOpen)
        assertNotNull(q.prevTradeDate)
        assertNotNull(q.prevVolume)
        assertNotNull(q.securityClass)
        assertNotNull(q.symbol)
        assertNotNull(q.timestamp)
        assertNotNull(q.totalVolume)
        assertNotNull(q.tradesToday)
        assertNotNull(q.tradeTick)
        assertNotNull(q.tradingSession)
        assertNotNull(q.trend)
        assertNotNull(q.volatility12)
        assertNotNull(q.weightedAvgPrice)
    }
  }
}
