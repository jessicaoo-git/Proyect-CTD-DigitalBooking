import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { GetProductById } from "../../service/productoService";
import Modals from "../Slider/Modals/ModalCarousel";

import "./bloque-imagenes.css";

const BloqueImagenes = ({imagenes}) => {
  return (
    <div className="contenedor__imagenes">
      <div className="contenedor__imagenes--principal">
        <img src={imagenes[0].url} alt="imagen principal" />
      </div>
      <div className="contenedor__imagenes--secuendaria">
        <div className="bloque__uno">
          <img
            src={imagenes[1].url}
            alt={imagenes[1].titulo}
          />
          <img
            src={imagenes[2].url}
            alt={imagenes[2].titulo}
          />
        </div>
        <div className="bloque__dos">
          <img
            src={imagenes[3].url}
            alt={imagenes[3].titulo}
          />
          <img
            src={imagenes[4].url}
            alt={imagenes[4].titulo}
          />
        </div>
        <div className="vermas_modal">
          <Modals imagenes={imagenes} />
        </div>
      </div>
    </div>
  );
};

export default BloqueImagenes;
