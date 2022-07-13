/* eslint-disable no-unused-expressions */
import axios from "axios";
import instanceApi from "./instanceApi";

export default function GetCiudades(setCiudades,setCiudadId=null) {
  axios;
  instanceApi
    .get("/ciudades")
    .then(({ data: response }) => {
      const { resultados } = response;
      setCiudades(resultados);
      if(setCiudadId!==null)setCiudadId(resultados[0].id)
    })
    .catch((error) => {
      throw new Error(`the cities could not be returned correctly: ${error}`);
    });
}
