import React, { useContext, useState } from "react";
import { Link, useNavigate } from "react-router-dom";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faFacebook,
  faInstagram,
  faLinkedinIn,
  faTwitter,
} from "@fortawesome/free-brands-svg-icons";
import { AiOutlineClose } from "react-icons/ai";
import Swal from "sweetalert2";

import { userContext } from "../../context/UserContext";
import Avatar from "../Avatar/Avatar";

import "./burguer.css";

const ImagenBurger = "/assets/menu.png";

function Burguer() {
  const { user, setUser } = useContext(userContext);

  const [mostrar, setmostrar] = useState(false);
  const [hidden, setHidden] = useState(true);
  const handleMostrar = () => {
    hidden ? setHidden(false) : setHidden(true);
    setmostrar(!mostrar);
  };
  const navigate = useNavigate();

const sesion = () => {
     Swal.fire({
      title: "Cerrar sesión?",
      imageWidth: 100,
      imageHeight: 100,
      imageAlt: "Custom image",
      showCancelButton: true,
      confirmButtonColor: "#ff7059",
      cancelButtonColor: "var(--color-dos)",
      confirmButtonText: "Confirmar",
      cancelButtonText: "Cancelar",
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire("Vuelve Pronto!", "", "success");
        localStorage.clear()
        setUser(false);
        navigate("/login", {
          replace: true,
        });
      }
    });

}
  return (
    <div className="contenedor-burger">
      <div className="icono" onClick={handleMostrar}>
        <img src={ImagenBurger} alt="IconoBurguer" />
      </div>

      {hidden ? null : (
        <div className={`emergente mostrar`}>
          <div className="contenedorNaranja">
            <div className="cerrar" onClick={handleMostrar}>
              <AiOutlineClose />
            </div>
            {user === true ? (
              <nav>
                <Avatar />
              </nav>
            ) : (
              <nav>MENÚ</nav>
            )}
          </div>

          {user === false && (
            <div className="contenedorBlanco">
              <Link to="/registro" className="registro-burger">
                Crear cuenta
              </Link>
              <hr />
              <Link to="/login" className="login-burger">
                Iniciar sesión
              </Link>
            </div>
          )}

          <div className="socialMidia">
            {user === true && (
              <Link to="/login">
                {" "}
                <button onClick={sesion}>
                  ¿Deseas <small>cerrar sesión?</small>{" "}
                </button>
              </Link>
            )}
            <div className="logos-espacio">
              <FontAwesomeIcon icon={faFacebook} />
              <FontAwesomeIcon icon={faLinkedinIn} />
              <FontAwesomeIcon icon={faTwitter} />
              <FontAwesomeIcon icon={faInstagram} />
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default Burguer;
