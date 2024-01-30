import "./App.css";

import { Routes, Route } from "react-router-dom";

import Header from "./components/Header";
import Login from "./components/Login";
import List from "./components/List";
import Register from "./components/Register";

import { useState } from "react";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  return (
    <div className="App">
      <Header />
      <Routes>
      {!isLoggedIn ? (
        <>
          <Route path="/" element={<Login />} />
        </>
      ) : (
        <Route path="/" element={<List />} />
      )}
        <Route path="/register" element={<Register />} />
      </Routes>
    </div>
  );
}

export default App;
