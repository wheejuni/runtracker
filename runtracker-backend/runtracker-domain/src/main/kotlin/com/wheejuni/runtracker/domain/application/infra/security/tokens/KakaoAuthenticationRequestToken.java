package com.wheejuni.runtracker.domain.application.infra.security.tokens;

import com.wheejuni.runtracker.domain.application.model.auth.SocialProvider;

/**
 * Created by quo.barlow@kakaocorp.com(정휘준)
 * 2021/03/14
 */
public class KakaoAuthenticationRequestToken extends SocialProviderLoginAuthenticationToken {

    public KakaoAuthenticationRequestToken(String token) {
        super(token);
    }

    @Override
    public SocialProvider getRequestedAuthenticationProvider() {
        return SocialProvider.KAKAO;
    }
}
