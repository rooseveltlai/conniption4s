/**
 * Copyright 2014 Cameron Cook
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.celexus.conniption.model

import scala.xml.{Node, NodeSeq}
import com.celexus.conniption.XMLPathMap
import org.scribe.model.Response

/**
 * Most model elements extend TKResponse, it contains a Map [path, value] for XML responses
 * @param xml the incoming XML
 * @param format underlying format, XML supported
 */
class TKResponse(val xml: NodeSeq, val res: Response, val format: String = "xml") {

  var properties: Map[String, String] = Map()

  if (format eq "xml") xml.foreach {
    n: Node => val m = new XMLPathMap(n)
      properties = properties ++ m.properties
  }

  def toDouble(str: String): Double = {
    if (str isEmpty) 0.0
    else str.toDouble
  }

  def ends(endsWith: String): String = {
    val filter: Map[String, String] = properties.filter {
      kv => kv._1.endsWith(endsWith)
    }
    if (filter isEmpty) ""
    else filter.head._2
  }

  def limitUsed: Int = res.getHeader("X-RateLimit-Used").toInt

  def limitTotal: Int = res.getHeader("X-RateLimit-Limit").toInt

  def limitRemaining: Int = res.getHeader("X-RateLimit-Remaining").toInt

  def limitExpire: java.util.Date = new java.util.Date(res.getHeader("X-RateLimit-Expire").toLong)
}
