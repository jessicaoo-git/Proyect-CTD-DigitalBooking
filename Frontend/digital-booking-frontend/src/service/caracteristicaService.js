/* eslint-disable no-unused-expressions */
import axios from "axios";
import instanceApi from "./instanceApi";

export default function GetCaracteristicas(setCaracteristicas,setCheckState=null) {
  axios;
  instanceApi
    .get("/caracteristicas")
    .then(({ data: response }) => {
      const { resultados } = response;
      setCaracteristicas(resultados);
      if(setCheckState!=null) setCheckState(new Array(resultados.length).fill(false))
    })
    .catch((error) => {
      throw new Error(`the cities could not be returned correctly: ${error}`);
    });
}
