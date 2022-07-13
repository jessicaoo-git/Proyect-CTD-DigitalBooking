import React, { useState, useEffect, useContext } from "react";
import { Link, useParams } from "react-router-dom";

import { DateObject } from "react-multi-date-picker";

import gregorian_en_lowercase from "../../utils/calendar.locale";
import Flecha from "../FlechaCalendario/FlechaCalendario";
import Calendario from "../Calendario/Calendario";

import "./bloque_calendario.css";
import { GetProductById } from "../../service/productoService";
import Boton from "../Boton/Boton";
import BotonReserva from "../BotonReserva/BotonReserva";
import { userContext } from "../../context/UserContext";

export default function BloqueCalendario() {
  const [values, setValues] = useState([new DateObject()]);
  let filtro = new DateObject().subtract(0, "days");
  const { estado, setEstado } = useContext(userContext);

  const [producto, setProducto] = useState([]);
  const { id } = useParams();

  useEffect(() => {
    GetProductById(id, setProducto);
  }, [id]);

  let arrow = (direction, handleClick) => (
    <Flecha direction={direction} click={handleClick} />
  );
    //estado para setear el tamanio
  const [width, setWidth] = useState(window.innerWidth);
    //logica para manejar el cambio de tamanio de la pantalla responsive //
  const handleResize = () => setWidth(window.innerWidth);
  useEffect(() => {
    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);
  // Función que a partir de dos fechas me genera un array con los días del mismo
  const getDaysArray = function (s, e) {
    for (var a = [], d = s; d.toDays() <= e.toDays(); d.add(1, "days")) {
      a.push(d.toDays());
    }
    return a;
  };
  let reservadas = [{fechaInicial:estado?.reserva?.fechaInicial},{fechaFinal: estado?.reserva?.fechaFinal}];
    // Obtiene un array con el rango de dias pro cada reserva
  const rangos = reservadas.map((reservada) => {
    return getDaysArray(
      new DateObject(reservada.fechaInicial),
      new DateObject(reservada.fechaFinal)
    );
  });
  // Obtiene un array con todos los días que está reservado el hotelo (sin duplicado)

  let dias = rangos;
  if (rangos.length !== 0) {
    dias = rangos.reduce((a, b) => {
      return [...new Set([...a, ...b])];
    });
  }

  let calendario;
  if (width < 768) {
    const PropiedadesCalendario = {
      readOnly: true,
      range: false,
      value: values,
      onChange: { setValues },
      numberOfMonths: 1,
      locale: gregorian_en_lowercase,
      renderButton: arrow,
      className: "detalles",
      minDate: new DateObject().subtract(60, "days"),
      maxDate: new DateObject().add(364, "days"),
      mapDays: ({ date, today }) => {
        let props = {};
        let result = date.toDays() - today.toDays();
        if (result < 0) props.disabled = true;

        const isReserved = dias.includes(date.toDays());
        if (isReserved) {
          props.disabled = true;
        }

        return props;
      },
    };
    calendario = <Calendario {...PropiedadesCalendario} />;
  } else {
    const PropiedadesCalendarioDesktop = {
      readOnly: true,
      range: false,
      value: values,
      onChange: { setValues },
      minDate: filtro,
      numberOfMonths: 2,
      locale: gregorian_en_lowercase,
      renderButton: arrow,
      className: "detalles",
      minDate: new DateObject().subtract(60, "days"),
      maxDate: new DateObject().add(364, "days"),
      mapDays: ({ date, today }) => {
        let props = {};
        let result = date.toDays() - today.toDays();
        if (result < 0) props.disabled = true;

        const isReserved = dias.includes(date.toDays());
        if (isReserved) {
          props.disabled = true;
        }

        return props;
      },
    };
    calendario = <Calendario {...PropiedadesCalendarioDesktop} />;
  }
 
 



  return (
    <>
      <div className="fila_calendario">
        <div className="calendarioDet">{calendario}</div>
        <div className="toreserva">
          <div className="pReserva">
            <p>Agregá tus fechas de viaje para obtener precios exactos</p>
          </div>
          <div className="boton-reserva">
              <BotonReserva/>
          </div>
        </div>
      </div>
    </>
  );
}
