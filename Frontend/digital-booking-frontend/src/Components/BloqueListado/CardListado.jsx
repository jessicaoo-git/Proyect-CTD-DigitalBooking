import React, { useState } from "react";
import { Link } from "react-router-dom";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faLocationDot } from "@fortawesome/free-solid-svg-icons";
import IconosListado from "../IconosListado/IconosListado";
import StarRating from "../Estrellas/StarRating";
import { IoIosArrowDropleft, IoIosArrowDropright } from "react-icons/io";

import "./bloque-listado.css";


import CardCategoria from "../BloqueCategorias/CardCategoria";
import TitulosPrincipales from "../TitulosPrincipales/TitulosPrincipales";

const CardListado = ({producto, setcategoriaId, card, page, incremento, decremento}) => {
  const [selectedStars, setSelectedStars] = useState(4);

  return (
    <div>
      <TitulosPrincipales titulo="Buscar por tipo de alojamiento" clase="uno" />
      <div className="contenedor-categoria">
        {card.map((data) => (
          <CardCategoria
            key={data.id}
            id={data.id}
            urlImagen={data.urlImagen}
            titulo={data.titulo}
            descripcion={data.descripcion}
            setcategoriaId={setcategoriaId}
          />
        ))}
      </div>
      <TitulosPrincipales titulo="Recomendaciones" clase="dos" />
      <div className="contenedor-bloque-listado">
        {producto.map((data) => (
          <div key={data.id} className="contenedor-card-list">
            <div className="img-list"
              style={{
            backgroundImage: `url(${data.imagenes[0].url})`,
          }}
          alt={data.titulo}
            >
            </div>
            <div className="card-list">
              <div className="estrella">
                <div className="hotel-estrellas">
                  <h3>{data.categoria.titulo}</h3>
                  <div className="rating">
                    <StarRating selectedStars={selectedStars} setSelectedStars={4} />
                  </div>
                </div>
                <div className="calificacion">
                  <h3>{data.titulo}</h3>
                  {/* {selectedStars >= 4 ? <p>Muy bueno</p> : selectedStars >=2 ? <p>Regular</p>  : <p>Malo</p> }  */}
                  <p>Muy bueno</p>
                </div>
              </div>
              <div className="location">
                <div className="mapa">
                  <FontAwesomeIcon icon={faLocationDot} />
                  <p>A 940 m del centro  <span>- MOSTRAR EN EL MAPA</span></p>
                </div>
               
              </div>
              <div>
              <IconosListado caracteristicas={data.caracteristicas}/>
                <p className="descripcion-listado">
                  {data.descripcion.slice(0,135)} <span>...más</span>
                </p>
              </div>
              <Link to={`/producto/${data.id}`}>
                <button className="boton-listado">ver más</button>
              </Link>
            </div>
          </div>
        ))}
      </div>
      <div className="contador">
        <button onClick={decremento}>
          <IoIosArrowDropleft className="siguiente" />
        </button>
        <h3>{page}</h3>
        <button onClick={incremento}>
          <IoIosArrowDropright className="siguiente" />
        </button>
      </div>
    </div>
  );
};

export default CardListado;
