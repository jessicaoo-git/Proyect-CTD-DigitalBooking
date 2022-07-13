import React from "react";

import "./buscador-selector.css";

function opcionCiudad({ ciudad, pais }) {
  return (
    <>
      <h3 className="titulo-ciudad">{ciudad}, </h3>
      <h3 className="titulo-pais"> {pais}</h3>
    </>
  );
}

export default opcionCiudad;
