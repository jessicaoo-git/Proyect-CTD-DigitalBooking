import React from "react";

import { faWifi, faPersonSwimming } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import "./Iconos-listado.css";

const IconosListado = ({caracteristicas}) => {
  return (
    <div className="iconos-listado">
      {caracteristicas.map(({id,titulo,urlImagen},index)=>{        
        return(
        <div key={index} style={{backgroundImage: `url(${urlImagen})`}} className="icono-listado-consumido" >.</div>
        )
      })}
    </div>
  );
};

export default IconosListado;
