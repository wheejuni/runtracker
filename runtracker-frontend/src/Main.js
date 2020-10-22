import React from 'react'
import LoginView from './view/LoginView';
import App from './App'

class MainApp extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
        this.setAuthenticationInfo = this.setAuthenticationInfo.bind(this);
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

    render() {
        return(
            this.state.auth == undefined ?
            <LoginView authFunction={this.setAuthenticationInfo}/> : <App />
        )
    }
}

export default MainApp;
