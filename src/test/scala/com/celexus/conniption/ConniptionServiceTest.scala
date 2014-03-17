package com.celexus.conniption

import org.scalatest.junit.AssertionsForJUnit
import org.junit._

/**
 * Created by cam on 3/12/14.
 */
class ConniptionServiceTest extends AssertionsForJUnit {

  @Test def stuff() = {
    println(new ConniptionService().accounts)
    //println(new ConniptionService().balances())
    //println(new ConniptionService().account("38580744").properties)
  }
}
