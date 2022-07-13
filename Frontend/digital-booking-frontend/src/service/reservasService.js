/* eslint-disable no-unused-expressions */
import axios from "axios";
import instanceApi from "./instanceApi";

export default function GetReservas(idUsuario) {
  axios;
  return instanceApi
    .get(`/reservas?usuario_id=${idUsuario}`)
    .then(({ data: response }) => {
      //const { resultados } = response;
      return response;
    })
    .catch((error) => {
      throw new Error(`the reservations could not be returned correctly: ${error}`);
    });
}

export function PostReservas(nuevaReserva) {
    console.log(nuevaReserva)
  const bodyParameters = nuevaReserva;

  let token=localStorage.getItem("token")
    const config = {
      headers: { Authorization: `Bearer ${token}` }
    };
  axios;
  instanceApi
    .post("/reservas",bodyParameters,config
      )
    .then(({ data: response }) => {
      const { resultados } = response;
    })
    .catch((error) => {
      throw new Error(`La reserva no fue retornada correctamente: ${error}`);
    });
}
