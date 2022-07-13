import React from "react";
import { Link } from "react-router-dom";

import "./logo.css";

const ImagenLogo = "/assets/logo.png";

const Logo = () => {
  return (
    <div className="logo">
      <Link to="/">
        <img src={ImagenLogo} alt="Logo" />
      </Link>
    </div>
  );
};

export default Logo;
