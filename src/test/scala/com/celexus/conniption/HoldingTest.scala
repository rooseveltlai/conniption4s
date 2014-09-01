package com.celexus.conniption

import org.scalatest.junit.AssertionsForJUnit
import org.junit.Test
import org.junit.Assert._
import com.celexus.conniption.model.Holding

class HoldingTest extends AssertionsForJUnit {

  @Test
  def holding = {
    val srv = new ConniptionService()
    val holdings: Set[Holding] = srv.holdings
    holdings.foreach {
      h: Holding =>
        assertNotNull(h.assetClass)
        assertNotNull(h.costBasis)
        assertNotNull(h.description)
        assertNotNull(h.gainLoss)
        assertNotNull(h.holdingType)
        assertNotNull(h.marketValue)
        assertNotNull(h.marketValueChange)
        assertNotNull(h.price)
        assertNotNull(h.purchasePrice)
        assertNotNull(h.quanity)
        assertNotNull(h.symbol)
    }
  }
}
