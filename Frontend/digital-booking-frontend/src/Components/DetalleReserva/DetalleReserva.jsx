import React, { useContext, useEffect, useState } from "react";
import { useParams } from "react-router-dom";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faLocationDot } from "@fortawesome/free-solid-svg-icons";

import StarRating from "../Estrellas/StarRating";
import { GetProductById } from "../../service/productoService";
import DetalleReservaCheck from "../DetalleReservaCheck/DetalleReservaCheck";
import DetalleProductoBoton from "../DetalleProductoBoton/DetalleProductoBoton";

import "./detalle-reserva.css";
import { userContext } from "../../context/UserContext";

const checkIn = "Check in";
const checkOut = "Check out";

const DetalleReserva = () => {
  const { id } = useParams();
  const { estado, setEstado } = useContext(userContext);

  const [product, setProduct] = useState([]);
  const [imagenes, setImagenes] = useState([]);
  const [categoria, setcategoria] = useState([]);
  const [ciudades, setCiudades] = useState([]);

 
  useEffect(() => {
    GetProductById(id, setProduct, setcategoria, setImagenes, setCiudades);
  }, [id]);
  return (
    <div className="detalle">
      <div className="detalle_contenedro--uno">
        <h2 className="detalle__titulo">Detalle de la reserva</h2>
        <img
          src={imagenes.url}
          alt="fondo"
        />
      </div>
      <div className="detalle_contenedro--dos">
        <div className="detalle__datos">
          <p className="detalle__categoria">Hoteles</p>
          <h3>{product.titulo}</h3>
          <div className="detalle_estrellas">
            <StarRating />
          </div>
          <div className="detalle_ubicacion">
            <FontAwesomeIcon icon={faLocationDot} />
            <p>
              Av. Colón 1111, {ciudades.nombre}, Ciudad Autónoma de {ciudades.nombre},
              Argentina
            </p>
          </div>
          <hr />
          <DetalleReservaCheck
            check={checkIn}
            fecha={estado?.reserva?.fechaInicial}
          />
          <hr />
          <DetalleReservaCheck
            check={checkOut}
            fecha={estado?.reserva?.fechaFinal}
          />
          <hr />
          <DetalleProductoBoton />
        </div>
      </div>
    </div>
  );
};

export default DetalleReserva;
