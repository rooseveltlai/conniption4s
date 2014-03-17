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

import scala.xml.Node

/**
 * Helper object to convert XML to a map of path -> value
 * @param xml underlying XML
 * @param path beginging path
 */
class XMLPathMap(xml: Node, path: String = "") {
  var properties: Map[String, String] = Map()
  parse(xml, path)

  def parse(xml: Node, path: String = ""): Unit = {
    //process this element
    properties += (path + "/" + xml.label -> xml.text.replace("\"", ""))
    // process all this element's children
    xml.child.foreach {
      child: Node => parse(child, path + "/" + xml.label)
    }
    //process all elements after this one
    xml.tail.foreach {
      next: Node => parse(next, path + "/" + xml.label)
    }
  }
}
