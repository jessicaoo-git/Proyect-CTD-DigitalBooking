import React from "react";
import { Link } from "react-router-dom";

import "./boton.css";

const Boton = ({ link, titulo, clase }) => {
  return (
    <div className="botones">
      <Link to={link}>
        <button className={clase}>{titulo}</button>
      </Link>
    </div>
  );
};

export default Boton;
