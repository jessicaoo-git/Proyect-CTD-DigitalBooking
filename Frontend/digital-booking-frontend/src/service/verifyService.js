/* eslint-disable no-unused-expressions */
import axios from "axios";
import instanceApi from "./instanceApi";
function GetVerificacion(setVerificacion, code) {
  axios;
  instanceApi
  .get(`/auth/verify?code=${code}`)
    .then(({ data: response }) => {
      const { resultados } = response;
      console.log(resultados);
      setVerificacion(()=> true);
    })
    .catch((error) => {
      throw new Error(`the verification could not be returned correctly: ${error}`);
    });
}

export default GetVerificacion