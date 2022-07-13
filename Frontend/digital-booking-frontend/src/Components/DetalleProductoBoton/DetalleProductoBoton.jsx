import React, { useContext } from "react";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import { userContext } from "../../context/UserContext";
import { PostReservas } from "../../service/reservasService";
import { useParams } from "react-router-dom";

import "./detalle-producto-boton.css";

const DetalleProductoBoton = () => {
  const {id}= useParams()

  const { estado, setEstado } = useContext(userContext);
  const navigate = useNavigate();
  const handleSubmit = async (e) => {
    e.preventDefault();
    if (
     estado?.reserva?.fechaFinal.length === 10 &&
      estado?.reserva?.fechaInicial.length === 10
    ) {
      PostReservas(
        {
          "horaComienzo":"14",
          "fechaInicial":estado.reserva.fechaInicial,
          "fechaFinal":estado.reserva.fechaFinal,
          "producto":{
              "id":id
          },
          "usuario":
          {
              "id":localStorage.getItem("id")
          }
        }
      )
      Swal.fire({
        position: "Center",
        title: "¡Muchas gracias!",
        text: "Su reserva se ha realizado con éxito!",
      });
      setTimeout(() => {
        navigate("/");
      }, 4000);
      
    } else {
      Swal.fire("", "Debes seleccionar checkIn checkOut Hora", "error");
    }
  };

  return (
    <div className="contenedor__boton">
      <form onSubmit={handleSubmit}>
        <button type="submit">Confirmar Reserva</button>
      </form>
    </div>
  );
};

export default DetalleProductoBoton;
