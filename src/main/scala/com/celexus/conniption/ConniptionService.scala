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
package com.celexus.conniption

import org.scribe.model.{Response, Verb}
import xml._
import com.celexus.conniption.model._

/**
 * Your connection to the TradeKing API
 */
class ConniptionService(format: String = "xml") {

  private[this] val tk: TKService = new TKService
  private[this] val URL_ROOT: String = "https://api.tradeking.com/v1/"

  /**
   * Access to all Accounts for this User
   * @return
   */
  def accounts: Set[Account] = {
    val res: Response = tk.request(Verb.GET, buildURL("accounts"))
    val xml: NodeSeq = XML.loadString(res.getBody)
    val accountsXML = xml \ "accounts" \ "accountsummary" \ "accountbalance"
    var accounts: Set[Account] = Set[Account]()
    accountsXML.foreach(part => accounts += new Account(accountsXML, res))
    accounts
  }

  /**
   * Acces to a particular Account
   * @param id The Account ID
   * @return
   */
  def account(id: String): Option[Account] = {
    accounts.filter {
      acc => acc.id == id
    }.headOption
  }

  /**
   * Returns all the balances for each Account
   * @return A map (accountId -> account balance)
   */
  def balances: Map[String, Double] = {
    val res: Response = tk.request(Verb.GET, buildURL("accounts/balances"))
    val xml: NodeSeq = XML.loadString(res.getBody)
    val balanceXML: NodeSeq = xml \ "accountbalance"
    var balanceMap: Map[String, Double] = Map()
    balanceXML.foreach {
      n: Node => balanceMap = balanceMap ++ Map[String, Double]((n \ "account").text -> (n \ "accountvalue").text.toDouble)
    }
    balanceMap
  }

  /**
   * The Account balance for a particular Account
   * @param id Account Id
   * @return The Account Balance
   */
  def balance(id: String): Double = {
    balances.filter {
      entry => entry._1 == id
    }.head._2
  }

  def histories(range: String = "all", transactions: String = "all"): Set[Transaction] = {
    var histories: Set[Transaction] = Set()
    accounts.foreach {
      a: Account =>
        histories = histories ++ history(a.id, range, transactions)
    }
    histories
  }

  /**
   * Access to previous transactions
   * @param id The account Id
   * @param range A date range of requested activity, values: all, today, current_week, current_month, last_month
   * @param transactions A filter to only show this particular transaction history, values: all, bookkeeping, trade
   * @return Set of Transactions for the given history parameters
   */
  def history(id: String, range: String = "all", transactions: String = "all"): Set[Transaction] = {
    val res: Response = tk.request(Verb.GET, buildURL("accounts/___/history", id), Map("range" -> range, "transactions" -> transactions))
    val transactionXML: NodeSeq = XML.loadString(res.getBody) \ "transactions" \ "transaction"
    var transacts: Set[Transaction] = Set()
    transactionXML.foreach {
      n: NodeSeq => transacts += new Transaction(n, res)
    }
    transacts
  }

  def holdings: Set[Holding] = {
    var holds: Set[Holding] = Set()
    accounts.foreach {
      a: Account =>
        holds = holds ++ holdings(a.id)
    }
    holds
  }

  /**
   * Returns all holdings of a particular Account
   * @param id Account Id
   * @return Set of Account Holdings
   */
  def holdings(id: String): Set[Holding] = {
    val res: Response = tk.request(Verb.GET, buildURL("accounts/___/holdings", id))
    val xml: Node = XML.loadString(res.getBody)
    val holdingsXML: NodeSeq = (xml \ "accountholdings" \ "holding")
    var holdings = Set[Holding]()
    holdingsXML.foreach {
      n: Node => holdings += new Holding(n, res)
    }
    holdings
  }

  def orders: Set[Order] = {
    var ords: Set[Order] = Set()
    accounts.foreach {
      a: Account =>
        ords = ords ++ orders(a.id)
    }
    ords
  }

  /**
   * Access to previous Orders
   * @param id Account Id
   * @return A set of Account Orders
   */
  def orders(id: String): Set[Order] = {
    val res: Response = tk.request(Verb.GET, buildURL("accounts/___/orders", id))
    val xml: Node = XML.loadString(res.getBody)
    val orderXML: NodeSeq = (xml \ "orderstatus" \ "order")
    var orders = Set[Order]()
    orderXML.foreach {
      n: NodeSeq => orders += new Order(n, res)
    }
    orders
  }

  /**
   * EXPERIMENTAL: Requires a FIXML model. Access to place an order
   * @param id Account Id
   * @param fixml String representation of the FIXML representing the Order
   * @return A TradeKing Response
   */
  def placeOrder(id: String, fixml: String): TKResponse = {
    val res: Response = tk.request(Verb.POST, buildURL("accounts/___/orders", id), payload = fixml)
    new TKResponse(XML.loadString(res.getBody), res)
  }

  /**
   * EXPERIMENTAL: Requires FIXML model.
   * @param id AccountId
   * @param fixml String representation of FIXML of the Order
   * @return a TradeKing response
   */
  def placePreview(id: String, fixml: String): TKResponse = {
    val res: Response = tk.request(Verb.POST, buildURL("accounts/___/orders/preview", id), payload = fixml)
    println(res.getBody)
    new TKResponse(XML.loadString(res.getBody), res)
  }

  /**
   * Access to the Market Clock
   * @return Java date for the current Market Clock
   */
  def clock: java.util.Date = {
    val res: Response = tk.request(Verb.GET, buildURL("market/clock"))
    val xml: NodeSeq = XML.loadString(res.getBody)
    val timeStamp: Long = (xml \ "unixtime").text.toLong
    new java.util.Date(timeStamp * 1000);
  }

  def marketClock: MarketClock = {
    val res: Response = tk.request(Verb.GET, buildURL("market/clock"))
    val xml: NodeSeq = XML.loadString(res.getBody)
    new MarketClock(xml, res)
  }

  /**
   * Access to Quotes
   * @param symbols A set of symbols to get a quote for
   * @param fids A set of field Ids (look at the TradeKing documentation) to grab
   * @return A set of quotes based on the query
   */
  def quotes(symbols: Set[String], fids: Set[String] = Set()): Set[Quote] = {
    var map: Map[String, String] = Map("symbols" -> concat(symbols))
    if (fids nonEmpty) map += ("fids" -> concat(fids))
    val res: Response = tk.request(Verb.POST, buildURL("market/ext/quotes"), map)
    val xml: NodeSeq = XML.loadString(res.getBody)
    var quotes: Set[Quote] = Set()
    val quotesXML: NodeSeq = (xml \ "quotes" \ "quote")
    quotesXML.foreach {
      n: NodeSeq => quotes += new Quote(n, res)
    }
    quotes
  }

  /**
   * Access to news articles
   * @param keywords A set of keywords
   * @param symbols Relevant Symbols
   * @param maxhits Maximum Hits, by default 10
   * @param startdate Unknown format
   * @param enddate Unknown format
   * @return A set of ArticleIds based on the query
   */
  def searchNews(keywords: Set[String], symbols: Set[String] = Set(), maxhits: Int = 10, startdate: String = "", enddate: String = ""): Set[ArticleId] = {
    var params: Map[String, String] = Map("keywords" -> concat(keywords), "maxhits" -> maxhits.toString)
    if (symbols nonEmpty) params += ("symbols" -> concat(symbols))
    if (startdate nonEmpty) params += ("startdate" -> startdate)
    if (enddate nonEmpty) params += ("enddate" -> enddate)
    val res: Response = tk.request(Verb.GET, buildURL("market/news/search"), params)
    val xml: NodeSeq = XML.loadString(res.getBody)
    var articleIds: Set[ArticleId] = Set()
    val idsXML: NodeSeq = (xml \ "articles" \ "article")
    idsXML.foreach {
      n: NodeSeq => articleIds += new ArticleId(n, res)
    }
    articleIds
  }

  /**
   * Convience method to grab a full Article from an ArticleId
   * @param id The ArticleId to retrieve
   * @return The Article from the ArticleId
   */
  def news(id: ArticleId): Article = news(idString = id.id)

  /**
   * Grab the full Article from an ArticleId
   * @param idString A string representation of the ArticleId
   * @return The Article from the ArticleId
   */
  def news(idString: String): Article = {
    val res: Response = tk.request(Verb.GET, buildURL("market/news/___", idString))
    val xml: NodeSeq = XML.loadString(res.getBody)
    new Article(xml \ "article", res)
  }

  /**
   * Access to timesales (snapshots) for a given Quote
   * @param symbols A set of Symbols to get timesales for
   * @param interval The requested interval of data to be returned: 5min, 1min, tick (default 5min)
   * @param rpp the number of requests per page (only valid for the tick interval, default 10)
   * @param startdate begin date for the range of data between this date and enddate (startdate parameter is required) (format: yyyy-MM-dd)
   * @param enddate end date for the range of data requested between start date and this date (enddate parameter is required) (format: yyyy-MM-dd)
   * @param starttime start time for intraday data requests
   * @return A Seq of Quotes based on the query
   */
  def timesales(symbols: Set[String], interval: String = "5min", rpp: Int = 10, startdate: String = "", enddate: String = "", starttime: String = ""): Seq[Quote] = {
    var params: Map[String, String] = Map("symbols" -> concat(symbols), "interval" -> interval, "rpp" -> rpp.toString)
    if (startdate nonEmpty) params += ("startdate" -> startdate)
    if (enddate nonEmpty) params += ("enddate" -> enddate)
    if (starttime nonEmpty) params += ("starttime" -> starttime)
    val res: Response = tk.request(Verb.GET, buildURL("market/timesales"), params)
    val xml: NodeSeq = XML.loadString(res.getBody)
    var quotes: Seq[Quote] = Seq()
    val quotesXML: NodeSeq = (xml \ "quotes" \ "quote")
    quotesXML.foreach {
      n: NodeSeq => quotes = quotes.+:(new Quote(n, res))
    }
    quotes
  }

  /**
   * A Seq of Quotes based on superlatives
   * toplosers	Top losers by dollar amount
   * toppctlosers	Top percentage losers
   * topvolume	Top volume
   * topactive	Top active
   * topgainers	Top gainers by dollar amount
   * toppctgainers	Top percentage gainers
   * topactivegainersbydollarvalue	Top active gainers by dollar value
   * @param typ a vailid superlative (toplosers,toppctlosers,topvolume,topactive,topgainers,toppctgainers,topactivegainersbydollarvalue)
   * @return
   */
  def list(typ: String = "topgainers"): Seq[Quote] = {
    val res: Response = tk.request(Verb.GET, buildURL("market/toplists/" + typ))
    val xml: NodeSeq = XML.loadString(res.getBody)
    var quotes: Seq[Quote] = Seq()
    val quotesXML: NodeSeq = (xml \ "quotes" \ "quote")
    quotesXML.foreach {
      n: NodeSeq => quotes = quotes.+:(new Quote(n, res))
    }
    quotes
  }

  /**
   * TradeKing info for the User
   * @return Tradeking data for the User
   */
  def profile: Profile = {
    val res: Response = tk.request(Verb.GET, buildURL("member/profile"))
    val xml: NodeSeq = XML.loadString(res.getBody)
    new Profile(xml \ "userdata", res)
  }

  /**
   * Returns true if the API is active, working
   * @return
   */
  def status: Boolean = {
    val xml: NodeSeq = (XML.loadString(tk.request(Verb.GET, buildURL("utility/status")).getBody) \ "time")
    xml.text.nonEmpty
  }

  /**
   * Returns the API verions, assumes 1.0 - 1.1-RC1
   * @return
   */
  def version: String = {
    val xml: NodeSeq = XML.loadString(tk.request(Verb.GET, buildURL("utility/version")).getBody)
    (xml \ "version").text
  }

  private[this] def concat(set: Set[String], delimit: String = ","): String = set.mkString(delimit)

  private[this] def buildURL(whole: String, parts: String*): String = {
    var replaced: String = whole.toString
    parts.foreach {
      s: String => replaced = replaced.replaceFirst("___", s)
    }
    URL_ROOT + replaced + "." + format
  }

}
