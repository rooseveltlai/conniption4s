package com.celexus.conniption

import org.scalatest.junit.AssertionsForJUnit
import org.junit.Test
import com.celexus.conniption.model.Profile
import org.junit.Assert._

class ProfileTest extends AssertionsForJUnit {

  @Test
  def profile = {
    val srv = new ConniptionService()
    val pro: Profile = srv.profile
    assertNotNull(pro.disabled)
    assertNotNull(pro.fundtrading)
    assertNotNull(pro.id)
    assertNotNull(pro.ira)
    assertNotNull(pro.marginTrading)
    assertNotNull(pro.nickName)
    assertNotNull(pro.optionLevel)
    assertNotNull(pro.resetPassword)
    assertNotNull(pro.resetTradingPassword)
    assertNotNull(pro.shared)
    assertNotNull(pro.stockTrading)
    assertNotNull(pro.userProperties)
  }
}
