import React from "react";
import Select from "react-select";

import vector from "./img/Vector.png";
import OpcionCiudad from "./OpcionCiudad";
import localizador from "./img/localizador.png";

import "./bloque-buscador.css";
import { useState } from "react";
import { useEffect } from "react";
import GetCiudad from "../../service/ciudadService";

function SelectCity({handleChangeCiudadId}) {
  const customStyles = {
    option: () => ({
      textAlign: "left",
      borderBottom: "solid 2px #F0572D",
      padding: "0.5rem 2.5rem",
      zIndex: "10",
      width: "100%",
      position: "relative",
      fontWeight: 500,
      ":after": {
        backgroundImage: `url(${vector})`,
        backgroundRepeat: "no-repeat",
        borderRadius: "10px",
        content: '" "',
        display: "block",
        marginRight: "8px",
        height: "25px",
        width: "25px",
        position: "absolute",
        left: "12px",
        top: "16px",
      },
    }),
    indicatorsContainer: () => ({
      display: "none",
    }),

    valueContainer: () => ({
      display: "flex",
      alignItems: "center",
      backgroundColor: "#FFFFFF",
      borderRadius: "5px",
      width: "100%",
      height: "100%",
      margin: "0",
      padding: "0 0 0 0%",
      border: "none",
      textAlign: "left",
    }),

    control: (styles) => ({
      ...styles,
      height: "40px",
      maxWidth: "100%",
      whiteSpace: "nowrap",
      overflow: "hidden",
      backgroundColor: "#FFFFFF",
      fontWeight: 500,
      boxShadow: "0px 1px 5px rgba(0, 0, 0, 0.15)",
      padding: "0.6rem 1rem 0.6rem 2.5rem",
      display: "inline-block",
      width: "100%",
      ":before": {
        backgroundImage: `url(${localizador})`,
        backgroundRepeat: "no-repeat",
        borderRadius: "10px",
        content: '" "',
        display: "block",
        marginRight: "8px",
        height: "25px",
        width: "25px",
        position: "absolute",
        left: "10px",
        top: "5px",
      },
    }),
    container: () => ({
      boxShadow: "0px 1px 5px rgb(0 0 0 / 15%)",
    }),

    placeholder: () => ({
      color: "#7F7F7F",
      fontSize: "14px",
      fontWeight: 500,
      width: "100%",
      lineHeight: "16px",
    }),
    singleValue: () => ({
      display: "flex",
    }),
  };
   
  const [card, setCard] = useState([]);
  useEffect(() => {
     GetCiudad(setCard)
  }, []);
  const options = card.map(data =>{
    return {
      value: `${data.id}`,
      label: <OpcionCiudad ciudad={data.nombre} pais='Argentina' />
    }
  })

  return (
    <Select
      placeholder="¿A dónde vamos?"
      styles={customStyles}
      options={options}
      isSearchable
      isClearable
      getOptionValue={(option) => `${option.value}:`}
      onChange={handleChangeCiudadId}
      
    />
  );
}

export default SelectCity;
