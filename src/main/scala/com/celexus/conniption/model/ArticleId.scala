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

/**
 * Model representing an ArticleId
 * @param xml Underlying forrmat, default XML
 */
class ArticleId(xml: NodeSeq) extends TKResponse(xml: NodeSeq, format = "xml") {

  /**
   * @return Article's Date
   */
  def date: java.util.Date = {
    val dateString: String = ends("article/date")
    val format: DateFormat = new SimpleDateFormat("MM/dd HH:mm")
    val d: java.util.Date = format.parse(dateString)
    d.setYear(new java.util.Date().getYear)
    d
  }

  /**
   * @return Article's Headline
   */
  def headline: String = ends("article/headline")

  /**
   * @return Internal Article ID
   */
  def id: String = ends("article/id")
}
