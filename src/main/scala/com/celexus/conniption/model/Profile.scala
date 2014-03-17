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
 * Model representing a TradeKing profile
 * @param xml Underlying format, default XML
 */
class Profile(xml: NodeSeq) extends TKResponse(xml: NodeSeq, format = "xml") {

  def id: String = ends("/userdata/account/account")

  def fundtrading: Boolean = ends("/userdata/account/fundtrading") == "true"

  def ira: Boolean = ends("/userdata/account/ira") == "true"

  def marginTrading: Boolean = ends("/userdata/account/margintrading") == "true"

  def nickName: String = ends("/userdata/account/nickname")

  def optionLevel: String = ends("/userdata/account/optionlevel")

  def shared: Boolean = ends("/userdata/account/shared") == "true"

  def stockTrading: Boolean = ends("/userdata/account/stocktrading") == "true"

  def disabled: Boolean = ends("/userdata/disabled") == "true"

  def resetPassword: Boolean = ends("/userdata/ressetpassword") == "true"

  def resetTradingPassword: Boolean = ends("/userdata/resettradingpassword") == "true"

  def userProperties: Map[String, String] = {
    val propertyXML: NodeSeq = xml \ "userprofile" \ "entry"
    var props: Map[String, String] = Map()
    propertyXML.foreach {
      n: NodeSeq => props += ((n \ "name").text -> (n \ "value").text)
    }
    props
  }
}
