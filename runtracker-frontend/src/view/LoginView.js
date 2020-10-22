import React from 'react'
import KakaoLogin from 'react-kakao-login';
import './login.css';

const LoginView = (props) => {
  return(
    <div className="container-fluid vertical-center login-container">
        <div className="login-background" />
        <div className="login-form-area">
            <h4 className="text-center">RUNTRACKER Login</h4>
            <div className="login-input">
            <form>
              <div className="form-group">
                <label htmlFor="exampleInputEmail1">Email</label>
                <input type="email" className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" />
              </div>
              <div className="form-group">
                <label htmlFor="exampleInputPassword1">Password</label>
                <input type="password" className="form-control" id="exampleInputPassword1" />
              </div>
              <button type="submit" className="btn btn-primary login-button">로그인</button>
              <KakaoLogin jsKey={'d165f64ec65c5acdd68df8798bc3b3e9'} onSuccess={props.authFunction} onFailure={res => console.log(res)} className={'kakao-box'} />
            </form>
            </div>
        </div>
    </div>
  )
}



export default LoginView;
