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

import scala.xml.NodeSeq

/**
 * Model representing an Account's generalizations for Securities
 * @param xml
 */
class Securities(xml: NodeSeq, res: org.scribe.model.Response) extends TKResponse(xml: NodeSeq, res: org.scribe.model.Response, format = "xml") {

  def longOptions: Double = toDouble(ends("securities/longoptions"))

  def longStocks: Double = toDouble(ends("securities/longstocks"))

  def options: Double = toDouble(ends("securities/options"))

  def shortoptions: Double = toDouble(ends("securities/shortoptions"))

  def shortstocks: Double = toDouble(ends("securities/shortstocks"))

  def stocks: Double = toDouble(ends("securities/stocks"))

  def total: Double = toDouble(ends("securities/total"))

}
