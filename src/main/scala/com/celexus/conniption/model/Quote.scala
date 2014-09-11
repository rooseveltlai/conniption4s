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
import java.text.{SimpleDateFormat, DateFormat}

class Quote(xml: NodeSeq, res: org.scribe.model.Response) extends TKResponse(xml: NodeSeq, res: org.scribe.model.Response, format = "xml") {

  def avg100Day: Double = toDouble(ends("quote/adp_100"))

  def avg200Day: Double = toDouble(ends("quote/adp_200"))

  def avg50Day: Double = toDouble(ends("quote/adp_50"))

  def ask: Double = toDouble(ends("quote/ask"))

  def askTime: String = ends("quote/ask_time")

  def askSize: Double = toDouble(ends("quote/asksz"))

  def precision: Int = toDouble(ends("quote/basis")).toInt

  def bid: Double = toDouble(ends("quote/bid"))

  def bidTime: String = ends("quote/bid_time")

  def bidSize: Double = toDouble(ends("quote/bidsz"))

  def bidTick: Int = toDouble(ends("quote/bidtick")).toInt

  def change: Double = toDouble(ends("quote/chg"))

  def changeSign: String = ends("quote/chg_sign")

  def changeText: String = ends("quote/chg_t")

  def close: Double = toDouble(ends("quote/cl"))

  def cusip: String = ends("quote/cusip")

  def date: java.util.Date = {
    val dateString: String = ends("quote/datetime")
    val format: DateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    format.parse(dateString)
  }

  def dividend: Double = toDouble(ends("quote/div"))

  def divexdate: java.util.Date = {
    val dateString: String = ends("quote/divexdate")
    val format: DateFormat = new SimpleDateFormat("yyyyMMdd")
    format.parse(dateString)
  }

  def dividendPayDate: java.util.Date = {
    val dateString: String = ends("quote/divpaydt")
    val format: DateFormat = new SimpleDateFormat("yyyyMMdd")
    format.parse(dateString)
  }

  def dollarValue: Double = toDouble(ends("quote/dollar_value"))

  def earningsPerShare: Double = toDouble(ends("quote/eps"))

  def exchange: String = ends("quote/exch")

  def exchangeDescription: String = ends("quote/exch_desc")

  def high: Double = toDouble(ends("quote/hi"))

  def annualDividend: Double = toDouble(ends("quote/iad"))

  def last: Double = toDouble(ends("quote/last"))

  def low: Double = toDouble(ends("quote/lo"))

  def name: String = ends("quote/name")

  def hasOptions: Boolean = {
    ends("quote/op_flag") == "1"
  }

  def openTradePrice: Double = toDouble(ends("quote/opn"))

  def percentChange: String = ends("quote/pchg")

  def percentChangeSign: String = ends("quote/pchg_sign")

  def prevClose: Double = toDouble(ends("quote/pcls"))

  def priceToEarnings: Double = toDouble(ends("quote/pe"))

  def prevHigh: Double = toDouble(ends("quote/phi"))

  def prevLow: Double = toDouble(ends("quote/plo"))

  def prevOpen: Double = toDouble(ends("quote/popn"))

  def prevAvg100Day: Double = toDouble(ends("quote/pr_adp_100"))

  def prevAvg200Day: Double = toDouble(ends("quote/pr_adp_200"))

  def prevAvg50Day: Double = toDouble(ends("quote/pr_adp_50"))

  def prevTradeDate: java.util.Date = {
    val dateString: String = ends("quote/pr_date")
    val format: DateFormat = new SimpleDateFormat("yyyy-MM-dd")
    format.parse(dateString)
  }

  def bookValue: Double = toDouble(ends("quote/prbook"))

  def prevChange: Double = toDouble(ends("quote/prchg"))

  def securityClass: String = {
    ends("quote/secclass") match {
      case "0" => "STOCK"
      case "1" => "OPTION"
    }
  }

  def tradingSession: String = ends("quote/sesn")

  def outstanding: Int = ends("quote/sho").replace(",", "").toInt

  def symbol: String = ends("quote/symbol")

  def canTrade: Boolean = {
    val tcond: String = ends("quote/tcond")
    println(tcond)
    tcond == "R"
  }

  def timestamp: java.util.Date = {
    val timeStamp: Long = ends("quote/timestamp").toLong
    new java.util.Date(timeStamp * 1000)
  }

  def tradesToday: Long = toDouble(ends("quote/tr_num")).toLong

  def tradeTick: String = ends("quote/tradetick")

  def trend: Seq[Char] = {
    var trendSet: Seq[Char] = Seq()
    ends("quote/trend").foreach {
      c: Char => trendSet += c + ""
    }
    trendSet
  }

  def high52Week: Double = toDouble(ends("quote/wk52hi"))

  def low52Week: Double = toDouble(ends("quote/wk52lo"))

  def low52WeekDate: java.util.Date = {
    val dateString: String = ends("quote/wk52lodate")
    val format: DateFormat = new SimpleDateFormat("yyyyMMdd")
    format.parse(dateString)
  }

  def avg21Volume: Double = toDouble(ends("quote/adv_21"))

  def avg30Volume: Double = toDouble(ends("quote/adv_30"))

  def avg90Volumne: Double = toDouble(ends("quote/adv_90"))

  def beta: Double = toDouble(ends("quote/beta"))

  def lastTradeVolume: Double = toDouble(ends("quote/incr_vl"))

  def prevVolume: Double = toDouble(ends("quote/pvol"))

  def totalVolume: Double = toDouble(ends("quote/vl"))

  def volatility12: Double = toDouble(ends("quote/volatility12"))

  def weightedAvgPrice: Double = toDouble(ends("quote/vwap"))

}
