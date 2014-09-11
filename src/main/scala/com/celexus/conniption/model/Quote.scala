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

  /**
   * Average Daily Price - 100 day
   * @return
   */
  def avg100Day: Double = toDouble(ends("quote/adp_100"))

  /**
   * Average Daily Price - 200 day
   * @return
   */
  def avg200Day: Double = toDouble(ends("quote/adp_200"))

  /**
   * Average Daily Price - 50 day
   * @return
   */
  def avg50Day: Double = toDouble(ends("quote/adp_50"))


  /**
   * Ask price
   * @return
   */
  def ask: Double = toDouble(ends("quote/ask"))

  /**
   * Time of latest ask
   * @return
   */
  def askTime: String = ends("quote/ask_time")

  /**
   * Size of latest ask (in 100's)
   * @return
   */
  //TODO FIXME
  def askSize: Double = toDouble(ends("quote/asksz"))

  /**
   * Reported precision (quotation decimal places)
   * @return
   */
  def precision: Int = toDouble(ends("quote/basis")).toInt

  /**
   * Bid price
   * @return
   */
  def bid: Double = toDouble(ends("quote/bid"))

  /**
   * Time of latest bid
   * @return
   */
  def bidTime: String = ends("quote/bid_time")

  /**
   * Size of latest bid (in 100's)
   * @return
   */
  //TODO FIXME
  def bidSize: Double = toDouble(ends("quote/bidsz"))

  /**
   * Tick direction since last bid
   * @return
   */
  def bidTick: Int = toDouble(ends("quote/bidtick")).toInt

  /**
   * Change since prior day close (cl)
   * @return
   */
  def change: Double = toDouble(ends("quote/chg"))

  /**
   * Change sign (e, u, d) as even, up, down
   * @return
   */
  def changeSign: String = ends("quote/chg_sign")

  /**
   * change in text format
   * @return
   */
  def changeText: String = ends("quote/chg_t")

  /**
   * previous close
   * @return
   */
  def close: Double = toDouble(ends("quote/cl"))

  /**
   * Cusip
   * @return
   */
  def cusip: String = ends("quote/cusip")

  /**
   * Date and time of last trade
   * @return
   */
  def date: java.util.Date = {
    val dateString: String = ends("quote/datetime")
    val format: DateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    format.parse(dateString)
  }

  /**
   * Latest announced cash dividend
   * @return
   */
  def dividend: Double = toDouble(ends("quote/div"))

  /**
   * Ex-dividend date of div
   * @return
   */
  def divexdate: java.util.Date = {
    val dateString: String = ends("quote/divexdate")
    val format: DateFormat = new SimpleDateFormat("yyyyMMdd")
    format.parse(dateString)
  }

  /**
   * Dividend pay date of last announced div
   * @return
   */
  def dividendPayDate: java.util.Date = {
    val dateString: String = ends("quote/divpaydt")
    val format: DateFormat = new SimpleDateFormat("yyyyMMdd")
    format.parse(dateString)
  }

  /**
   * Total dollar value of shares traded today
   * @return
   */
  def dollarValue: Double = toDouble(ends("quote/dollar_value"))

  /**
   * Earnings per share
   * @return
   */
  def earningsPerShare: Double = toDouble(ends("quote/eps"))

  /**
   * exchange code
   * @return
   */
  def exchange: String = ends("quote/exch")

  /**
   * exchange description
   * @return
   */
  def exchangeDescription: String = ends("quote/exch_desc")

  /**
   * High Trade Price for the trading day
   * @return
   */
  def high: Double = toDouble(ends("quote/hi"))

  /**
   * Indicated annual dividend
   * @return
   */
  def annualDividend: Double = toDouble(ends("quote/iad"))

  /**
   * Last trade price
   * @return
   */
  def last: Double = toDouble(ends("quote/last"))

  /**
   * Low Trade Price for the trading day
   * @return
   */
  def low: Double = toDouble(ends("quote/lo"))

  /**
   * Company name
   * @return
   */
  def name: String = ends("quote/name")

  /**
   * Security has options
   * @return
   */
  def hasOptions: Boolean = {
    ends("quote/op_flag") == "1"
  }

  /**
   * Open trade price
   * @return
   */
  def openTradePrice: Double = toDouble(ends("quote/opn"))

  /**
   * percentage change from prior day close
   * @return
   */
  def percentChange: String = ends("quote/pchg")

  /**
   * prchg sign (e, u, d) as even, up, down
   * @return
   */
  def percentChangeSign: String = ends("quote/pchg_sign")

  /**
   * Prior day close
   * @return
   */
  def prevClose: Double = toDouble(ends("quote/pcls"))

  /**
   * Price earnings ratio
   * @return
   */
  def priceToEarnings: Double = toDouble(ends("quote/pe"))

  /**
   * Prior day high value
   * @return
   */
  def prevHigh: Double = toDouble(ends("quote/phi"))

  /**
   * Prior day low value
   * @return
   */
  def prevLow: Double = toDouble(ends("quote/plo"))

  /**
   * Prior day open
   * @return
   */
  def prevOpen: Double = toDouble(ends("quote/popn"))

  /**
   * Prior Average Daily Price 100 trade days
   * @return
   */
  def prevAvg100Day: Double = toDouble(ends("quote/pr_adp_100"))

  /**
   * Prior Average Daily Price 200 trade days
   * @return
   */
  def prevAvg200Day: Double = toDouble(ends("quote/pr_adp_200"))

  /**
   * Prior Average Daily Price 50 trade days
   * @return
   */
  def prevAvg50Day: Double = toDouble(ends("quote/pr_adp_50"))

  /**
   * Trade Date of Prior Last
   * @return
   */
  def prevTradeDate: java.util.Date = {
    val dateString: String = ends("quote/pr_date")
    val format: DateFormat = new SimpleDateFormat("yyyy-MM-dd")
    format.parse(dateString)
  }

  /**
   * Book Value Price
   * @return
   */
  def bookValue: Double = toDouble(ends("quote/prbook"))

  /**
   * Prior day change
   * @return
   */
  def prevChange: Double = toDouble(ends("quote/prchg"))

  /**
   * Security class, STOCK OR OPTION
   * @return
   */
  def securityClass: String = {
    ends("quote/secclass") match {
      case "0" => "STOCK"
      case "1" => "OPTION"
    }
  }

  /**
   * Trading session as (pre, regular, & post)
   * @return
   */
  def tradingSession: String = ends("quote/sesn")

  /**
   * Shares Outstanding
   * @return
   */
  def outstanding: Int = ends("quote/sho").replace(",", "").toInt

  /**
   * Symbol from data provider
   * @return
   */
  def symbol: String = ends("quote/symbol")

  /**
   * Trade condition code – (H) halted or (R) resumed
   * @return
   */
  def canTrade: Boolean = {
    val tcond: String = ends("quote/tcond")
    println(tcond)
    tcond == "R"
  }

  /**
   * Timestamp
   * @return
   */
  def timestamp: java.util.Date = {
    val timeStamp: Long = ends("quote/timestamp").toLong
    new java.util.Date(timeStamp * 1000)
  }

  /**
   * Number of trades since market open
   * @return
   */
  def tradesToday: Long = toDouble(ends("quote/tr_num")).toLong

  /**
   * Tick direction from prior trade – (e,u,d) even, up, down)
   * @return
   */
  def tradeTick: String = ends("quote/tradetick")

  /**
   * Trend based on 10 prior ticks (e,u,d) even, up, down
   * @return
   */
  def trend: Seq[Char] = {
    var trendSet: Seq[Char] = Seq()
    ends("quote/trend").foreach {
      c: Char => trendSet += c + ""
    }
    trendSet
  }

  /**
   * 52 week high
   * @return
   */
  def high52Week: Double = toDouble(ends("quote/wk52hi"))

  /**
   * 52 week low
   * @return
   */
  def low52Week: Double = toDouble(ends("quote/wk52lo"))

  /**
   * 52 week low date
   * @return
   */
  def low52WeekDate: java.util.Date = {
    val dateString: String = ends("quote/wk52lodate")
    val format: DateFormat = new SimpleDateFormat("yyyyMMdd")
    format.parse(dateString)
  }

  /**
   * Average Daily Volume - 21 day
   * @return
   */
  def avg21Volume: Double = toDouble(ends("quote/adv_21"))

  /**
   * Average Daily Volume - 30 day
   * @return
   */
  def avg30Volume: Double = toDouble(ends("quote/adv_30"))

  /**
   * Average Daily Volume - 90 day
   * @return
   */
  def avg90Volumne: Double = toDouble(ends("quote/adv_90"))

  /**
   * Beta volatility measure
   * @return
   */
  def beta: Double = toDouble(ends("quote/beta"))

  /**
   * Volume of last trade
   * @return
   */
  def lastTradeVolume: Double = toDouble(ends("quote/incr_vl"))

  /**
   * Prior day total volume
   * @return
   */
  def prevTotalVolume: Double = toDouble(ends("quote/pvol"))

  /**
   * Cumulative volume
   * @return
   */
  def totalVolume: Double = toDouble(ends("quote/vl"))

  /**
   * One year volatility measure
   * @return
   */
  def volatility12: Double = toDouble(ends("quote/volatility12"))

  /**
   * Volume weighted average price
   * @return
   */
  def weightedAvgPrice: Double = toDouble(ends("quote/vwap"))

}
