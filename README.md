conniption4s
============

A missing TradeKing API for Scala

## Getting an API Key from TradeKing

* [Visit the TradeKing Developers Website](https://developers.tradeking.com/applications/)  
* Fill in their info and you will get 4 important values needed for [OAuth](http://oauth.net/)
    * An API Key
    * An API Secret Key
    * An Access Token
    * A Secret Access Token
* [Go over the TradeKing API Docs](https://developers.tradeking.com/documentation/getting-started) 

## Installing

### Linux

Put these lines in */etc/environment* or */etc/profile*.

    API_KEY=<YOUR API KEY HERE>
    API_SECRET=<YOUR API SECRET HERE>
    ACCESS_TOKEN=<YOUR ACCESS TOKEN HERE>
    ACCESS_TOKEN_SECRET=<YOUR SECRET ACCESS TOKEN HERE>


### Mac

Put these lines in */etc/launchd.conf*

    setenv API_KEY <YOUR API KEY HERE>
    setenv API_SECRET <YOUR API SECRET HERE>
    setenv ACCESS_TOKEN <YOUR ACCESS TOKEN HERE>
    setenv ACCESS_TOKEN_SECRET <YOUR SECRET ACCESS TOKEN HERE>
    
    
Restart your computer.

Use the maven clean install directive to compile and make sure everythng works. I highly recommend you do not use -DskipTests. The tests will check your connection to TradeKing

## Maven / SBT

```xml
<dependency>
   <groupId>com.celexus</groupId>
   <artifactId>conniption4s</artifactId>
   <version>1.0.1</version>
</dependency>
```

## Usage

### [ConniptionService](https://github.com/Ccook/conniption4s/blob/master/src/main/scala/com/celexus/conniption/ConniptionService.scala)

The [ConniptionService](https://github.com/Ccook/conniption4s/blob/master/src/main/scala/com/celexus/conniption/ConniptionService.scala) is your goto for access to the TradeKing API. Most methods will return a single, sequence or set of given model elements defined below

#### Examples

Access your TradeKing account

```scala
val srv: ConniptionService = new ConniptionService
val acc: Account = srv.getAccount("<accid>")
//OR
val accounts: Set[Account] = srv.getAccounts
```

Access account values

```scala
acc.id -> "1234567"
acc.value -> 0.0
acc.fedcall -> 0.0
acc.housecall -> 0.0
```
Access account buying power

```scala
val power: BuyingPower = acc.buyingPower
power.cashAvailableForWithdrawl -> 0.0
power.dayTrading -> 0.0
power.equityPercentage -> 0.0
power.options -> 0.0
power.dayStartDayTrading -> 0.0
power.dayStartStock -> 0.0
power.stock -> 0.0
```

Access account funds

```scala
val funds: Funds = acc.funds
funds.accruedInterest -> 0.0
funds.cashTotal -> 0.0
funds.cashAvailable -> 0.0
funds.marginBalance -> 0.0
funds.moneyMarketFundBalance -> 0.0
funds.total -> 0.0
funds.uncleareedDeposits -> 0.0
funds.unsettled -> 0.0
funds.fundsYield  -> 0.0
```
      
... And so on for Holdings and Securities

Search News

```scala
val keywords = Set("debt")
val symbols = Set("APPL") //optional
val maxhits = 10 //optional 
val articles: Set[ArticleId] = srv.searchNews(keywords,symbols,maxhits) -> Set[ArticleId]
```

Inflate a News Article

```scala
articles.foreach {
   id: ArticleId =>
      srv.news(id)
}
```

Get Member Profile Info

```scala
val pro: Profile = srv.profile
pro.id -> "1234567"
pro.fundtrading -> true
pro.ira -> false
pro.marginTrading -> false
pro.nickName -> "Individual Account"
      ....
```

Get Quotes

```scala
val symbols: Set[String] = Set("IBM")
val quotes: Set[Quote] = srv.quotes(symbols)
```
   
Get History

```scala
val hist :Seq[Transactions] = srv.history("1234567")
```

Place an Order

```scala
   // User FIXMLOrder object
   // Buying : Place a day order to buy 1 share of F at market price on account 12345678.
     val o: String = new FIXMLOrder("12345678", "F").timInForce(TimeInForce.dayOrder).typ(OrderType.market).side(Side.buy).qty(1).toString
     srv.placeOrder(acc.id, o)
     
  // Selling: Place a day order to sell 1 share of F at market price on account 12345678.
   val o: String = new FIXMLOrder("12345678", "IBM").timInForce(TimeInForce.dayOrder).typ(OrderType.market).side(Side.sell).qty(1).toString
    srv.placeOrder(acc.id, o)
    
  // Sell Short:Place a day order to sell short 1 share of F at $22.00 on account 12345678.
    val o: String = new FIXMLOrder("12345678", "F").timInForce(TimeInForce.dayOrder).typ(OrderType.limit).side(Side.sellShort).qty(1).price(22).toString
     srv.placeOrder(acc.id, o)
     
 // Buy to Cover : Place a day order to buy to cover 1 share of F at $13 on account 12345678.
    val o: String = new FIXMLOrder("12345678", "F").timInForce(TimeInForce.dayOrder).typ(OrderType.limit).side(Side.buyToCover).qty(1).price(13).toString
    srv.placeOrder(acc.id, o)
    
 // Buy Trailing Stop (with dollar value): Place a .50 buy trailing stop on 1 share of IBM (market order will trigger if current price of IBM rises by .50 for current price. If IBM moves down, trigger price will also move down.*/
    val o: String = new FIXMLOrder("12345678", "IBM").timInForce(TimeInForce.dayOrder).typ(OrderType.trailingStop).stopAt(StopType.dollarValue(0.50)).side(Side.buy).qty(1).to
   srv.placeOrder(acc.id, o)
   
 // Buy Trailing Stop (with percentage):  Place a 5% buy trailing stop on 1 share of COST (Note: OfstTyp="1" for percentage trailing stop vs. OfstTyp="0" for price value trailing stop).
    val o: String = new FIXMLOrder("12345678", "COST").timInForce(TimeInForce.dayOrder).typ(OrderType.trailingStop).stopAt(StopType.percentage(5)).side(Side.buy).qty(1).toString
    srv.placeOrder(acc.id, o)
    
 // Sell Trailing Stop (with dollar value):  Place a 2.00 sell trailing stop on 1 share of F. (NOTICE THE NEGATIVE)
    val o: String = new FIXMLOrder("12345678", "F").timInForce(TimeInForce.dayOrder).typ(OrderType.trailingStop).stopAt(StopType.dollarValue(-2.0)).side(Side.sell).qty(1).toString
    srv.placeOrder(acc.id, o)
  
  
 
    
```
   
.... Plus many more! Checkout the TradeKing documentation, I've implemented what is reasonable...

## [Models](https://github.com/Ccook/conniption4s/tree/master/src/main/scala/com/celexus/conniption/model)

* [Account](https://github.com/Ccook/conniption4s/blob/master/src/main/scala/com/celexus/conniption/model/Account.scala)
   * [BuyingPower](https://github.com/Ccook/conniption4s/blob/master/src/main/scala/com/celexus/conniption/model/BuyingPower.scala)
   * [Funds](https://github.com/Ccook/conniption4s/blob/master/src/main/scala/com/celexus/conniption/model/Funds.scala)
   * [Holding](https://github.com/Ccook/conniption4s/blob/master/src/main/scala/com/celexus/conniption/model/Holding.scala)
   * [Securities](https://github.com/Ccook/conniption4s/blob/master/src/main/scala/com/celexus/conniption/model/Securities.scala)
* [Article](https://github.com/Ccook/conniption4s/blob/master/src/main/scala/com/celexus/conniption/model/Article.scala) / [ArticleId](https://github.com/Ccook/conniption4s/blob/master/src/main/scala/com/celexus/conniption/model/ArticleId.scala)
* [Order](https://github.com/Ccook/conniption4s/blob/master/src/main/scala/com/celexus/conniption/model/Order.scala)
* [Profile](https://github.com/Ccook/conniption4s/blob/master/src/main/scala/com/celexus/conniption/model/Profile.scala)
* [Quote](https://github.com/Ccook/conniption4s/blob/master/src/main/scala/com/celexus/conniption/model/Quote.scala)
* [Transaction](https://github.com/Ccook/conniption4s/blob/master/src/main/scala/com/celexus/conniption/model/Transaction.scala)

## License, Attribution, etc


Conniption is licensed under the Apache License, version 2. It is in no way associated with TradeKing or TradeKing Group, Inc.

Please read TradeKing's documentation carefully! Use only as they suggest.


I use [scribe-java](https://github.com/fernandezpablo85/scribe-java) for OAuth. It's an awesome project. I recommend you check it out.


![Powered by TradeKing](https://developers.tradeking.com/images/logos/PB-TK-small-Blue.gif)



