package com.celexus.conniption.model

import scala.xml.NodeSeq

/**
 * Created by cam on 3/16/14.
 */
class Order(xml: NodeSeq, res: org.scribe.model.Response) extends TKResponse(xml: NodeSeq, res: org.scribe.model.Response, format = "xml") {

  //EXPERIMENTAL!!
}
