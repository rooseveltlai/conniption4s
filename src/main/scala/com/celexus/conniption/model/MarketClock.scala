package com.celexus.conniption.model

import scala.xml.NodeSeq
import java.util.Date

class MarketClock(xml: NodeSeq, res: org.scribe.model.Response) extends TKResponse(xml: NodeSeq, res: org.scribe.model.Response, format = "xml") {

  def unixTime: Date = {
    val timeStamp = toDouble(ends("unixtime")).toLong
    new java.util.Date(timeStamp * 1000)
  }

  def status: String = {
    ends("current")
  }
}
