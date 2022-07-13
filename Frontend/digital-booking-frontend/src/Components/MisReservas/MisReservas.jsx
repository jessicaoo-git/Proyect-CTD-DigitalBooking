import { React, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import { FaMapMarkerAlt, FaChevronLeft } from "react-icons/fa";
import { FiAlertTriangle } from "react-icons/fi";

import Header from "../../Components/Header/Header";
import Boton from "../Boton/Boton";
import Footer from "../../Components/Footer/Footer";

import "./misReservas.css";
import GetReservas from "../../service/reservasService";


const MisReservas = () => {
  const [dataReservas, setDataReservas] = useState();

  useEffect(() => {
    const idUsuario = localStorage.getItem("id");
    console.log(idUsuario);
    GetReservas(idUsuario).then((e) => {
      setDataReservas(e);
      console.log(e);
    });
  }, []);

  const navigate = useNavigate()
  const handleClick = () => {
    navigate(-1)
  }


  return dataReservas ? (
    <div>
      <Header />
      <div className="cont-BloqueHeader">
      <div className="contenedor-info">
     
      <div className="InfoHeader-reservas">
        <h2>Mis reservas</h2>
    </div>
       
      </div>

      <div className="flecha-home">
      
          <button onClick={handleClick}>
            <FaChevronLeft />
          </button>
      
      </div>
    </div>

      <div className="contenedor-card-Reservas">
        {dataReservas.resultados.map((resultado) => {
          return (
            <div className="contenedor-Reserva">
              <div className="imgReserva">
                <img
                  src={resultado.producto.imagenes[0].url}
                  alt={resultado.producto.titulo}
                />
              </div>

              <div className="infoReserva">
                <h3>{resultado.producto.categoria.titulo}</h3>
                <h2>{resultado.producto.titulo}</h2>
                <div className="ubicacion">
                  <FaMapMarkerAlt />
                  <p>{resultado.producto.ciudad.nombre}</p>
                </div>

                <div className="fecha">
                  <hr />
                  <h4>
                    Check in:{" "}
                    {resultado.fechaInicial.split("T")[0]}
                  </h4>
                  <hr />
                  <h4>
                    Check out:{" "}
                    {resultado.fechaFinal.split("T")[0]}
                  </h4>
                  <hr />
                </div>
                <div className="buton-verProducto">
                  <Boton
                    link={`/producto/${resultado.producto.id}`}
                    titulo="Ver producto"
                    clase="ver"
                  />
                </div>
              </div>
            </div>
          );
        })}
      </div>

      <Footer />
    </div>
  ) : (
    <div>
      <Header />
      <div className="mensaje-Alerta ">
        <FiAlertTriangle/>
        <h2>¡No se encontro ninguna reserva!</h2>
      </div>

      <Footer />
    </div>
  );
};

export default MisReservas;
