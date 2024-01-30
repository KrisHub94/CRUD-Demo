import logo from "../img/check-list.png";
import loginLogout from "../img/enter.png";

function Header() {
  return (
    <div className="Header-container">
      <header className="Header">
        <img src={logo} className="Logo" />
        <p className="Header-p">Simple Tasking</p>
        <img src={loginLogout} className="Login-Logout" />
      </header>
    </div>
  );
}

export default Header;
