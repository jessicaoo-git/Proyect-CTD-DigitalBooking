/* eslint-disable no-unused-expressions */
import axios from "axios";
import instanceApi from "./instanceApi";

export default function GetPoliticasId(setIdsPoliticas) {
  axios;
  instanceApi
    .get("/titulos-politicas")
    .then(({ data: response }) => {
      const { resultados } = response;
      setIdsPoliticas(resultados);
    })
    .catch((error) => {
      throw new Error(`the ... could not be returned correctly: ${error}`);
    });
}
