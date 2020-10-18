import React from 'react'
import LoginView from './view/LoginView';
import App from './App'

class MainApp extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
        this.setAuthenticationInfo = this.setAuthenticationInfo.bind(this);
        this.onKakaoLoginPress = this.onKakaoLoginPress.bind(this);
    }

    setAuthenticationInfo(authenticationInfo) {
        this.setState(
            {
                auth: authenticationInfo
            }, () => {
                console.log(`${this.state.auth}`)
            }
        );
    }

    componentDidMount() {
        window.Kakao.init('d165f64ec65c5acdd68df8798bc3b3e9');
        let init = window.Kakao.isInitialized();

        console.log(`kakao initialized: ${init}`);
    }

    onKakaoLoginPress() {
        window.Kakao.Auth.login({
            success: function(authObj) {
                console.log('login success');
              },
            fail: function(err) {
              console.log(JSON.stringify(err))
            }
        })
    }

    render() {
        return(
            this.state.auth == undefined ?
            <LoginView authFunction={this.setAuthenticationInfo} loginFunction={this.onKakaoLoginPress}/> : <App />
        )
    }
}

export default MainApp;
