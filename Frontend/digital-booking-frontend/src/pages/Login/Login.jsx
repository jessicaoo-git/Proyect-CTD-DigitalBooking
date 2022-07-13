import React, { useContext, useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";

import { FaRegEye, FaRegEyeSlash } from "react-icons/fa";



import { userContext } from "../../context/UserContext";
import Header from "../../Components/Header/Header";
import Footer from "../../Components/Footer/Footer";
import LoginError from "../../Components/LoginError/LoginError";
import { GetJWT } from "../../service/loginService";

import "./login.css";
import useQuery from "../../hook/useQuery";

const Login = () => {
  const { user, setUser } = useContext(userContext);
  const userName = localStorage.getItem("userName");
  const [showError, setShowError] = useState(false);

  const [email, setEmail] = useState("");
  const handleChangeEmail = (event) => {
    setEmail(event.target.value);
  };

  const [password, setPassword] = useState("");
  const handleChangePassword = (event) => {
    setPassword(event.target.value);
  };

  const [errores, setErrores] = useState([]);
  const navigat = useNavigate();
  const query = useQuery();


  useEffect(() => {
    if (user) navigate();
  }, []);


  function navigate() {
    const id = query.get("error");
    if (id) {
      navigat(`/producto/${query.get("error")}/reserva`);
    } else {
      navigat(`/`);
    }
  }
  useEffect(() => {
    if (query.get("error"))
      setShowError("Para realizar una reserva necesitas estar logueado");
  }, [query]);


  const handleSubmit = (e) => {
    e.preventDefault();

    GetJWT(email, password, setUser, setErrores);
    navigat("/");
  };

  const [isShowPassword, setIsShowPassword] = useState(false);
  return (
    <div className="login-contenedor">
      <Header />
      <div className="formulario-login">
        {showError && <LoginError>{showError}</LoginError>}
        <h2>Iniciar sesión</h2>
        <form onSubmit={handleSubmit}>
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

          <p className="error">{errores}</p>
          <button type="submit">Ingresar</button>
          <div className="registrate">
            <p>¿Aún no tenes cuenta?</p>
            <Link to="/registro">Registrate</Link>
          </div>
        </form>
      </div>
      <Footer />
    </div>
  );
};

export default Login;
