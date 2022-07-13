/* eslint-disable no-unused-expressions */
import axios from "axios";
import instanceApi from "./instanceApi";

function GetJWT(usuario, contrasena, setUser, setErrores) {
 
  axios;
  instanceApi
    .post(`/auth/login`, {
      nombreUsuario: usuario,
      password: contrasena,
    })
    .then(({ data: response }) => {
      const dat = response;
      localStorage.setItem("userName", dat.nombre);
      localStorage.setItem("userEmail", dat.email);
      localStorage.setItem("userLastName", dat.apellido);
      localStorage.setItem("token", dat.token);
      localStorage.setItem("id", dat.id);
      let rolAdminOBJ=dat.authorities.filter((e)=>e.authority==="ROLE_ADMIN")
      let rolAdminBool=false
      rolAdminBool= rolAdminOBJ[0]?.authority==="ROLE_ADMIN"? true:false
      localStorage.setItem("rolAdmin",rolAdminBool);
      
      setUser(true);
    })
    .catch((error) => {
      setErrores([
        "Las credenciales son inválidas. Por favor intentalo nuevamente. ",
      ]);
      throw new Error(`the products could not be returned correctly: ${error}`);
    });
}

export { GetJWT };
