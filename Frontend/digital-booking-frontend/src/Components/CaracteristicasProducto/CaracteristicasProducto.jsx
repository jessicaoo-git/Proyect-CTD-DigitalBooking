import React from "react";

/*
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faWifi,
  faCar,
  faTv,
  faPaw,
  faSink,
  faClock,
  faFan,
  faBanSmoking,
} from "@fortawesome/free-solid-svg-icons";
*/

import "./caracteristicas.css";

const CaracteristicasProducto = ({ caracteristicas }) => {
  return (
    
      <ul className="detalleProducto__contenedor__caracteristicas">
        {caracteristicas.map(({ id, titulo, urlImagen }, index) => {
          return (
            <li key={index}>
              <div className="detalleProducto__contenedor__caracteristicaIndividual">
                <div
                  className="detalleProducto__caracteristica__img"
                  style={{ backgroundImage: `url(${urlImagen})` }}
                ></div>
                <h4 >{titulo}</h4>
              </div>
            </li>
          );
        })}
      </ul>
  );
};

export default CaracteristicasProducto;
