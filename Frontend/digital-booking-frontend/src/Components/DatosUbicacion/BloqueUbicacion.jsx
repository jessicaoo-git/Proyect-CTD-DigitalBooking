import React, { useEffect, useState } from "react";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faLocationDot } from "@fortawesome/free-solid-svg-icons";

import StarRating from "../Estrellas/StarRating";

import "./bloque-ubicacion.css";
import { useParams } from "react-router-dom";
import { GetProductById } from "../../service/productoService";

const BloqueUbicacion = ({ciudad}) => {
  const [selectedStars, setSelectedStars] = useState(4);

  return (
    <div className="cont-ubicacion">
      <div className="cont-localizacion">
        <FontAwesomeIcon icon={faLocationDot} />
        <div className="localizacion">
          <p>
            {" "}
            {ciudad.nombre}, Ciudad Autónoma de {ciudad.nombre}, Argentina
          </p>
          <p> A 940 m del centro</p>
        </div>
      </div>

      <div className="cont-calificacion">
      {selectedStars >= 4 ? <p>Muy bueno</p> : selectedStars >=2 ? <p>Regular</p>  : <p>Malo</p> } 
        <div className="rating-ubicacion">
          <StarRating selectedStars={selectedStars} setSelectedStars={setSelectedStars} />
        </div>
      </div>
    </div>
  );
};

export default BloqueUbicacion;
