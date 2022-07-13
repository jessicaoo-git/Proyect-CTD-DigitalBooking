import React, { useContext } from "react";
import { useNavigate } from "react-router-dom";

import Swal from "sweetalert2";

import { userContext } from "../../context/UserContext";

import "./logout.css";

const Logout = () => {
  const { setUser } = useContext(userContext);
  const navigate = useNavigate();
  const logout = () => {
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
  };

  return (
    <div>
      <button className="boton__logout" onClick={logout}>X</button>
    </div>
  );
};

export default Logout;
