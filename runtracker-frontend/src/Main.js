import React from 'react'
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
        console.log('this is main app.');
        return(
            <App />
        )
    }
}

export default MainApp;
