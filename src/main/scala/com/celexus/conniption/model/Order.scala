package com.celexus.conniption.model

import scala.xml.NodeSeq

/**
 * Created by cam on 3/16/14.
 */
class Order(xml: NodeSeq, res: org.scribe.model.Response) extends TKResponse(xml: NodeSeq, res: org.scribe.model.Response, format = "xml") {

  def commission: Double = toDouble(ends("estcommission"))

  def marginRequirement: Double = toDouble(ends("marginrequirement"))

  def principal: Double = toDouble(ends("principal"))

  def secfee: Double = toDouble(ends("secfee"))

  //EXPERIMENTAL!!
  /**
  <quotes>
    <instrumentquote>
      <displaydata>
        <askprice>1.79</askprice>
        <asksize>6,814</asksize>
        <bidprice>1.78</bidprice>
        <bidsize>3,375</bidsize>
        <change>0.04</change>
        <contracthigh/>
        <contractlow/>
        <contractsize/>
        <delta/>
        <desc>SIRIUS XM RADIO INC</desc>
        <dividendamount>0.00</dividendamount>
        <dividendexdate>2005-06-01</dividendexdate>
        <dividendpaydate/>
        <dividendyield>0.00%</dividendyield>
        <earningspershare>-0.03</earningspershare>
        <exch>NSD</exch>
        <expiry/>
        <gamma/>
        <high52price>1.80</high52price>
        <highprice>1.80</highprice>
        <impvolatility/>
        <lastclosingprice>1.75</lastclosingprice>
        <lastprice>1.78</lastprice>
        <low52price>0.79</low52price>
        <lowprice>1.76</lowprice>
        <nav/>
        <openinterest/>
        <openprice>1.77</openprice>
        <optval/>
        <optiontype/>
        <pctchange>2.00%</pctchange>
        <priceearningsratio>0.00</priceearningsratio>
        <rho/>
        <sessionvolume>20,845,145</sessionvolume>
        <strike/>
        <symbol>SIRI</symbol>
        <theta/>
        <unknownsymbol>false</unknownsymbol>
        <vega/>
        <volatility/>
        <volume>21,261,045</volume>
      </displaydata>
      <greeks/>
      <instrument>
        <desc>SIRIUS XM RADIO INC</desc>
        <exch>NSD</exch>
        <sectyp>CS</sectyp>
        <sym>SIRI</sym>
      </instrument>
      <quote>
        <askprice>1.79</askprice>
        <bidprice>1.78</bidprice>
        <change>0.035</change>
        <extendedquote>
          <asksize>6814</asksize>
          <bidsize>3375</bidsize>
          <dividenddata>
            <amt>0.0</amt>
            <exdate>2005-06-01T00:00:00-04:00</exdate>
            <yield>4.9E-324</yield>
          </dividenddata>
          <earningspershare>-0.03</earningspershare>
          <high52price>1.8</high52price>
          <highprice>1.8</highprice>
          <low52price>0.79</low52price>
          <lowprice>1.76</lowprice>
          <openprice>1.77</openprice>
          <prevclose>1.75</prevclose>
          <priceearningsratio>0.0</priceearningsratio>
          <sessionvolume>20845145</sessionvolume>
          <volume>21261045</volume>
        </extendedquote>
        <lastprice>1.785</lastprice>
        <pctchange>2.0</pctchange>
      </quote>
      <unknownsymbol>false</unknownsymbol>
    </instrumentquote>
  </quotes>
  <secfee>0.0</secfee>
</response>
    */
}
