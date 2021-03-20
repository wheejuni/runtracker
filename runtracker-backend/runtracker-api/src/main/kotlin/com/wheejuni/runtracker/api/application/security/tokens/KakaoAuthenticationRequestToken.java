package com.wheejuni.runtracker.api.application.security.tokens;

import com.wheejuni.runtracker.domain.application.model.auth.SocialProvider;

public class KakaoAuthenticationRequestToken extends SocialProviderLoginAuthenticationToken {

    public KakaoAuthenticationRequestToken(String token) {
        super(token);
    }

    @Override
    public SocialProvider getRequestedAuthenticationProvider() {
        return SocialProvider.KAKAO;
    }
}
