const assert = require('assert');
const AuthenticationInfo = require('../../src/domain/AuthenticationInfo').AuthenticationInfo;

describe('authentication info 객체 로딩', () => {
    it('객체 생성 테스트', () => {
        let authObject = new AuthenticationInfo('hellow', 'test token');

        assert.equal('hellow', authObject.username);
        assert.equal('test token', authObject.token);
    })
});

describe('authentication info 객체 기능 테스트', () => {
    it('로그인 여부 판정 로직 테스트', () => {
        let authObject = new AuthenticationInfo('unsigned', '');

        assert.equal(authObject.isAuthenticated(), false);
    })
});
