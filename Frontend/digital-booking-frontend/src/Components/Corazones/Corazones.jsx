import React, { useState, useEffect } from "react";
import { Corazon } from "./components/Corazon";
import { corazones } from "../../data/data";
import { useParams } from "react-router-dom";
import {MostrarFavorito, borrarFavorito, agregarFavorito} from "../../service/favoritosService"

import "./corazon.css";

export default function Corazones() {
  const [corazonesLista, setCorazonesLista] = useState(corazones);
  const [bandera, setBandera] = useState(false);
  const {id} = useParams();

  useEffect(() => {
    MostrarFavorito(localStorage.getItem("id"),id, setBandera);
  }, [])

  const handleClickCorazon = (idCorazon) => {
    let nuevaListaDeCorazones = corazonesLista.map((corazon) => {
      if (corazon.id === idCorazon) {
        setBandera(!bandera);
        console.log(bandera);
        if (bandera) {
          borrarFavorito(localStorage.getItem("id"),id)
        } else {
          agregarFavorito(id)
        }
        return {
          id: corazon.id,
          roto: !corazon.roto,
        };
      }
      return corazon;
    });
    setCorazonesLista(nuevaListaDeCorazones);
  };

  return (
    <div className="corazon_me_gusta">
      <div className="content">
        {corazonesLista.map((corazon) => (
          <Corazon
            key={corazon.id}
            id={corazon.id}
            roto={bandera}
            onClick={handleClickCorazon}
          />
        ))}
      </div>
    </div>
  );
}
