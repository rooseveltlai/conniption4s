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

import xml._
import java.text.{SimpleDateFormat, DateFormat}

/**
 * Model representing a Transaction for History
 * @param xml Underlying format, default XML
 */
class Transaction(xml: NodeSeq, res: org.scribe.model.Response) extends TKResponse(xml: NodeSeq, res: org.scribe.model.Response, format = "xml") {

  def activity: String = ends("transaction/activity")

  def amount: Double = toDouble(ends("transaction/amount"))

  def date: java.util.Date = {
    val dateString: String = ends("transaction/date")
    val format: DateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    format.parse(dateString)
  }

  def description: String = ends("transaction/desc")

  def symbol: String = ends("transaction/symbol")

  def comission: Double = toDouble(ends("transaction/transaction/comission"))

  def fee: Double = toDouble(ends("transaction/transaction/fee"))

  def price: Double = toDouble(ends("transaction/transaction/price"))

  def quantity: Double = toDouble(ends("transaction/transaction/quantity"))

  def secfee: Double = toDouble(ends("transaction/transactions/secfee"))

}
