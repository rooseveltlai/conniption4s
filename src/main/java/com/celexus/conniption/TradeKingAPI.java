package com.celexus.conniption;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

/**
 * Created by cam on 3/12/14.
 */
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
