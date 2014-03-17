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

I'm still new to SBT and deploying scala projects to maven central, check back when I get this sorted!

## Usage

### [ConniptionService](https://github.com/Ccook/conniption4s/blob/master/src/main/scala/com/celexus/conniption/ConniptionService.scala)

The [ConniptionService](https://github.com/Ccook/conniption4s/blob/master/src/main/scala/com/celexus/conniption/ConniptionService.scala) is your goto for access to the TradeKing API. Most methods will return a single, sequence or set of given model elements defined beloy


#### [Models](https://github.com/Ccook/conniption4s/tree/master/src/main/scala/com/celexus/conniption/model)

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


