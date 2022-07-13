import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

import { FaRegEye, FaRegEyeSlash } from "react-icons/fa";

import Footer from "../../Components/Footer/Footer";
import Header from "../../Components/Header/Header";

import { Registrar } from "../../service/registroService";

import "./registro.css";

const Registro = () => {
  const [name, setName] = useState("");
  const handleChangeName = (event) => {
    setName(event.target.value);
  };

  const [surname, setSurname] = useState("");
  const handleChangeSurname = (e) => {
    setSurname(e.target.value);
  };

  const [email, setEmail] = useState("");
  const handleChangeEmail = (event) => {
    setEmail(event.target.value);
  };

  const [password, setPassword] = useState("");
  const handleChangePassword = (event) => {
    setPassword(event.target.value);
  };

  const [confirmPassword, setConfirmPassword] = useState("");
  const handleChangeConfirmPassword = (event) => {
    setConfirmPassword(event.target.value);
  };

  const [error, setError] = useState("");

  const credencialesValidas = {
    email: "felipe.erira@hotmail.com",
    password: "1234567",
  };

  const validarEmail = () => {
    let emailValido = true;
    let regEx =
      /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
    if (!regEx.test(email)) {
      setError("El email es invalido");
      emailValido = false;
    }
    if (email.length === 0) {
      setError("El campo email no puede estar vacio");
      emailValido = false;
    }
    return emailValido;
  };

  const validarPassword = () => {
    let passwordValida = true;
    if (password.length <= 6) {
      setError("La contraseña debe tener más de 6 caracteres");
      passwordValida = false;
    }
    if (confirmPassword.length === 0) {
      setError("El campo password no puede estar vacio");
      passwordValida = false;
    }
    if (confirmPassword.length > 0 && password !== confirmPassword) {
      setError("Las contraseñas deben ser iguales");
      passwordValida = false;
    }

    return passwordValida;
  };

  const validarCredenciales = () => {
    let existenCredenciales = false;
    if (email === credencialesValidas.email) {
      existenCredenciales = true;
    }
    return existenCredenciales;
  };
  const navigate = useNavigate();

  const sendData = (event) => {
    if (!validarPassword()) {
      event.preventDefault();
    } else if (!validarEmail()) {
      event.preventDefault();
    } else if (validarCredenciales()) {
      event.preventDefault();
      setError(["El usuario ya existe."]);
    } else {
      event.preventDefault();
      Registrar(name,surname,email,password,navigate,setError);            
    }
  };

  const [isShowPassword, setIsShowPassword] = useState(false);
  return (
    <div className="registro">
      <Header />
      <div className="formulario-registro">
        <h2>Crear cuenta</h2>
        <form onSubmit={sendData}>
          <div className="nom-apell">
            <div className="nombre-registro">
              <label htmlFor="nombre">Nombre</label>
              <input
                type="text"
                name="nombre"
                id="nombre"
                value={name}
                onChange={handleChangeName}
              />
            </div>
            <div className="apellido-registro">
              <label htmlFor="nombre">Apellido</label>
              <input
                type="text"
                name="apellido"
                id="apellido"
                value={surname}
                onChange={handleChangeSurname}
              />
            </div>
          </div>
          <label htmlFor="email">Correo electrónico</label>
          <input
            type="email"
            name="email"
            id="email"
            value={email}
            onChange={handleChangeEmail}
          />

          <label htmlFor="password">Contraseña</label>
          <div className="input-icon">
            <input
              type={isShowPassword ? "text" : "password"}
              name="password"
              id="password"
              value={password}
              onChange={handleChangePassword}
            />
            <div
              className="icon-container"
              onClick={() => setIsShowPassword(!isShowPassword)}
            >
              {isShowPassword ? (
                <FaRegEyeSlash className="icon" />
              ) : (
                <FaRegEye className="icon" />
              )}
            </div>
          </div>

          <label className="confirmar" htmlFor="password">
            Confirmar contraseña
          </label>
          <input
            type="password"
            name="password"
            id="password"
            value={confirmPassword}
            onChange={handleChangeConfirmPassword}
          />
          <div>{error !== "" ? <p className="error">{error}</p> : null}</div>

          <button type="submit">Crear cuenta</button>
          <div className="registrate">
            <p>¿Ya tienes una cuenta?</p>
            <Link to="/login">Iniciar sesión</Link>
          </div>
        </form>
      </div>
      <Footer />
    </div>
  );
};

export default Registro;
