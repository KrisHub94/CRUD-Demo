function Login() {
  return (
    <div className="Login-container">
      <div className="Login-title">
        <h1>Please Login</h1>
      </div>
      <div className="Login-form-wrapper">

      <div className="Login-form-container">
        <form>
          <div className="Username-container">
            <label>Username:</label>
            <input></input>
          </div>
          <div className="Password-container">
            <label>Password:</label>
            <input type="password"></input>
          </div>
          <div className="Login-button-container">
            <button className="Login-button">Login</button>
          </div>
        </form>
      </div>
      <p>
        Not a user yet? <a href="/register">Register</a> here !
      </p>
      </div>
    </div>
  );
}

export default Login;
