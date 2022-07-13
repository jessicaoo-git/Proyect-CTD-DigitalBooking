import React from "react";

import "./bloque-ubicacion.css";

const DatosUbicacion = ({ ciudad, provincia, pais, localizacion }) => {
  return (
    <>
      <p>
        {ciudad}, {provincia}, {pais}
      </p>
      <p className="distancia">{localizacion}</p>
    </>
  );
};

export default DatosUbicacion;
