import React, { useState } from "react";

import "./form-reserva.css";

const FormReserva = () => {
  const handleSubmit = (e) => {
    e.preventDefault();
  };

  const userName = localStorage.getItem("userName");
  const userLastName = localStorage.getItem("userLastName");
  const userEmail = localStorage.getItem("userEmail");

  const [vacuna, setVacuna] = useState("");

  return (
    <div className="contenedor-form">
      <div className="formulario">
        <form className="form" onSubmit={handleSubmit}>
          <div className="input-box">
            <label htmlFor="nombre">Nombre</label>
            <input
              type="text"
              name="nombre"
              id="nombre"
              placeholder={userName}
              disabled
            />
          </div>

          <div className="input-box">
            <label htmlFor="apellido">Apellido</label>
            <input
              type="text"
              name="apellido"
              id="Apellido"
              placeholder={userLastName}
              disabled
            />
          </div>

          <div className="input-box">
            <label htmlFor="email">Correo electronico</label>
            <input
              className="box-email"
              type="email"
              name="email"
              id="email"
              disabled
              placeholder={userEmail}
            />
          </div>

          <div className="input-box">
            <label htmlFor="ciudad">Ciudad</label>
            <input
              type="text"
              name="ciudad"
              id="ciudad"
              placeholder="Ciudad"
              required
            />
          </div>
        </form>
      </div>
    </div>
  );
};

export default FormReserva;
