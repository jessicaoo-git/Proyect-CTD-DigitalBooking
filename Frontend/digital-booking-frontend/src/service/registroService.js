/* eslint-disable no-unused-expressions */
import axios from "axios";
import instanceApi from "./instanceApi";
import Swal from "sweetalert2";
import { faSquareCaretLeft } from "@fortawesome/free-regular-svg-icons";

function Registrar(nombre,apellido,email,contrasena,navigate,setErrores) {
  axios;
  instanceApi.
    
      post(`/auth/nuevoUsuario`,
       {
        nombre: nombre,
        apellido: apellido,
        nombreUsuario: email,
        email: email,
        password: contrasena,
      })
      .then(({ data: response }) => {
        const dat = response;
        Swal.fire({
            position: "Center",
            icon: "success",
            imageHeight: 150,
            imageAlt: "¡Te has registrado con exito!",
            text: "¡Te has registrado con exito!  Porfavor inicie sesión para continuar",
            showConfirmButton: true
        }).then((result) => {
            if (result.isConfirmed) {
                navigate("/login", {
                    replace: true,
                })
            }
        });        
      })
      .catch((error) => {
        setErrores([
          "Este usuario ya existe",
        ]);
        throw new Error(`the products could not be returned correctly: ${error}`);
      });
}

export { Registrar};
