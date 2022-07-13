import React, {useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { IoIosArrowDropleft, IoIosArrowDropright } from "react-icons/io";
import { FaChevronLeft } from "react-icons/fa";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faLocationDot } from "@fortawesome/free-solid-svg-icons";

import IconosListado from "../../Components/IconosListado/IconosListado";
import { PaginacionFavoritos } from "../../service/favoritosService";
import StarRating from "../../Components/Estrellas/StarRating";
import Header from "../../Components/Header/Header";
import Footer from "../../Components/Footer/Footer";
import UseCounter from "../../hook/useCounter";

import './Favoritos.css'

// import { FaChevronLeft } from "react-icons/fa";
import { FiAlertTriangle } from "react-icons/fi";
const Favoritos = () => {
  const { page, setDecremento, setIncremento } = UseCounter();
  const [favorito, setFavorito] = useState(false);
  const [selectedStars, setSelectedStars] = useState(4);

  useEffect(() => {
    PaginacionFavoritos(page, setFavorito, localStorage.getItem("id"));
  }, []);
  useEffect(() => {
    PaginacionFavoritos(page, setFavorito, localStorage.getItem("id"));
  }, [page]);
  const navigate = useNavigate();

  const handleClick = () => {
    navigate(-1);
  };

  return favorito ? (
    <div className="favoritos">
      <Header />

      <div className="cont-BloqueHeader">
        <div className="contenedor-info">
          <div className="InfoHeader-reservas">
            <h2>Mis favoritos</h2>
          </div>
        </div>

        <div className="flecha-home">
          <button onClick={handleClick}>
            <FaChevronLeft />
          </button>
        </div>
      </div>
     <div className="favoritos__listado">
      <div className="contenedor-bloque-listado ">
        {favorito.map((data) => (
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
                    <StarRating
                      selectedStars={selectedStars}
                      setSelectedStars={setSelectedStars}
                    />
                  </div>
                </div>
                <div className="calificacion">
                  <h3>{data.titulo}</h3>
                  {selectedStars >= 4 ? (
                    <p>Muy bueno</p>
                  ) : selectedStars >= 2 ? (
                    <p>Regular</p>
                  ) : (
                    <p>Malo</p>
                  )}
                </div>
              </div>
              <div className="location">
                <div className="mapa">
                  <FontAwesomeIcon icon={faLocationDot} />
                  <p>
                    A 940 m del centro <span>- MOSTRAR EN EL MAPA</span>
                  </p>
                </div>
              </div>
              <div>
                <IconosListado caracteristicas={data.caracteristicas} />
                <p className="descripcion-listado">
                  {data.descripcion.slice(0, 255)} <span>...más</span>
                </p>
              </div>
              <Link to={`/producto/${data.id}`}>
                <button className="boton-listado">ver más</button>
              </Link>
            </div>
          </div>
        ))}
      </div>
      </div>
      <div className="contador">
        <button onClick={setDecremento}>
          <IoIosArrowDropleft className="siguiente" />
        </button>
        <h3>{page}</h3>
        <button onClick={setIncremento}>
          <IoIosArrowDropright className="siguiente" />
        </button>
      </div>
        <Footer />
    </div>):
    <div>
        <Header />
      <div className="mensaje-Alerta ">
        <FiAlertTriangle/>
        <h2>¡No se encontro ningun favorito!</h2>
      </div>

      <Footer />
    </div>
    
}

export default Favoritos;
