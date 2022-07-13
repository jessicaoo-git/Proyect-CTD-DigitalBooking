import React, { useContext, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { userContext } from "../../context/UserContext";
import { GetProductById } from "../../service/productoService";

export default function BotonReserva( ) {
  const navigate = useNavigate()
  const user  = localStorage.getItem("userName");
  const [producto, setProducto] = useState([]);
  const { id } = useParams();

  useEffect(() => {
    GetProductById(id, setProducto);
  }, [id]);

  function RedireccionReserva() {
  
    user?
      navigate(`/producto/${producto.id}/reserva`)
    :
     navigate("/login?error=" + producto.id);
  }

  return (
    <div className="boton-reserva">
      <button onClick={RedireccionReserva}>Iniciar reserva</button>
    </div>
  );
}
