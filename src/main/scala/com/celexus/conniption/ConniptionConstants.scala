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

/**
 * Access to external enviroment variables. Used during OAUth
 */
object ConniptionConstants {

  val API_KEY: String = System.getenv("API_KEY")
  val API_SECRET: String = System.getenv("API_SECRET")
  val ACCESS_TOKEN: String = System.getenv("ACCESS_TOKEN")
  val ACCESS_TOKEN_SECRET: String = System.getenv("ACCESS_TOKEN_SECRET")
}
