import React, { useState } from "react";

import SeleccionarCiudad from "./SeleccionarCiudad";
import BuscadorCalendario from "../BuscadorCalendario/BuscadorCalendario";

import "./bloque-buscador.css";

function SearchForm({handleChangeCiudadIdYFecha}) {

  const [fecha1, setFecha1] = useState();
  const [fecha2, setFecha2] = useState();

  function handleChangeCalendario(e) {
    let fecha1 = e[0].format("YYYY-MM-DD");
    switch (e.length) {
      case 1:
        setFecha1(fecha1);
        break;
      case 2:
        let fecha2 = e[1].format("YYYY-MM-DD");
        const d1 = new Date(e[0].format("YYYY-MM-DD"));
        const d2 = new Date(e[1].format("YYYY-MM-DD"));
        if (d1 > d2) {
          setFecha1(fecha2);
          setFecha2(fecha1);
        } else {
          setFecha2(fecha2);
        }
        break;
      default:
        break;
    }
  }

   //Al seleccionar 1 vez no se setea el id de la ciudad,solo al hacerlo por segunda vez
   const [ciudadId, setCiudadId] = useState([]);
   function handleChangeCiudadId(e){
     setCiudadId(e.value)
   }

   function handleSubmit(e){
      e.preventDefault();
      handleChangeCiudadIdYFecha(ciudadId,fecha1,fecha2)
   }
  return (
    <form className="grid-container" onSubmit={handleSubmit}>
      <div className="icon-select-box grid-item">
        <SeleccionarCiudad handleChangeCiudadId={handleChangeCiudadId}/>
      </div>
      <div className="icon-select-box grid-item">
        <BuscadorCalendario onChange={handleChangeCalendario}/>
      </div>
      <button type="submit" className="button-search limitation">Buscar</button>
    </form>
  );
}

export default SearchForm;
