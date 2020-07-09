package com.dtdhehe.auth.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2020/7/9 17:47
 * @description
 **/
@Component
public class JwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String,Object> infoMap = new HashMap<>(16);
        infoMap.put("riqi","0709");
        ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(infoMap);
        return oAuth2AccessToken;
    }
}
