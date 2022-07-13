import React from "react";

import "./titulos-principales.css";

const TitulosPrincipales = ({ titulo, clase }) => {
  return (
    <div className="titulos-principales">
      <h2 className={clase}>{titulo}</h2>
    </div>
  );
};

export default TitulosPrincipales;
