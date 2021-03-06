import React from 'react'
import LoginView from './view/LoginView';
import App from './App'
import MapView from './view/MapView';

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
                console.log(`${JSON.stringify(this.state.auth.response)}`)
            }
        );
    }

    render() {
        return(
            this.state.auth == undefined ?
            <LoginView authFunction={this.setAuthenticationInfo}/> : <MapView />
        )
    }
}

export default MainApp;
