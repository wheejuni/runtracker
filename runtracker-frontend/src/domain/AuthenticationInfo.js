const SIGNED_OUT_TOKEN = 'not logged in';

class AuthenticationInfo {
    constructor(username, token) {
        this.username = username;
        if(token.length < 1) {
            this.token = SIGNED_OUT_TOKEN;
            this.isAuthenticated = this.isAuthenticated.bind(this);

            return;
        }
        this.token = token;
        this.isAuthenticated = this.isAuthenticated.bind(this);
    }

    isAuthenticated() {
        console.log(this.token);
        return this.token != SIGNED_OUT_TOKEN;
    }
}

exports.AuthenticationInfo = AuthenticationInfo;
