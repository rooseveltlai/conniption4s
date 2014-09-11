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
package com.celexus.conniption;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

public class TradeKingAPI extends DefaultApi10a {
    @Override
    public String getRequestTokenEndpoint() {
        return "https://developers.tradeking.com/oauth/request_token";
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://developers.tradeking.com/oauth/access_token";
    }

    @Override
    public String getAuthorizationUrl(Token requestToken) {
        return "https://developers.tradeking.com/oauth/authorize?oauth_token=%s";
    }

}
