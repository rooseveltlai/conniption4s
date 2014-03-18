package com.celexus.conniption

import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert._
import org.junit._

/**
 * Created by cam on 3/17/14.
 */
class ConniptionConstantsTest extends AssertionsForJUnit {

  @Test def test = {
    assertTrue("Constant Empty:ACCESS_TOKEN_SECRET", ConniptionConstants.ACCESS_TOKEN_SECRET nonEmpty)
    assertTrue("Constant Empty:ACCESS_TOKEN", ConniptionConstants.ACCESS_TOKEN nonEmpty)
    assertTrue("Constant Empty: API_KEY", ConniptionConstants.API_KEY nonEmpty)
    assertTrue("Constant Empty: API_SECRET", ConniptionConstants.API_SECRET nonEmpty)
  }
}
