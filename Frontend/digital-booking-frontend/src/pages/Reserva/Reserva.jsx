import React, { useContext, useEffect, useState} from "react";

import { DateObject } from "react-multi-date-picker";

import Header from "../../Components/Header/Header";
import Footer from "../../Components/Footer/Footer";
import { userContext } from "../../context/UserContext";
import Calendario from "../../Components/Calendario/Calendario";
import gregorian_en_lowercase from "../../utils/calendar.locale";
import FormReserva from "../../Components/FormReserva/FormReserva";
import BloqueHeader from "../../Components/BloqueHeader/BloqueHeader";
import Flecha from "../../Components/FlechaCalendario/FlechaCalendario";
import DetalleReserva from "../../Components/DetalleReserva/DetalleReserva";
import BloquePoliticas from "../../Components/BloquePoliticas/BloquePoliticas";
import DetalleReservaSelect from "../../Components/DetalleReservaSelect/DetalleReservaSelect";
import { GetProductById2 } from "../../service/productoService";
import { useParams } from "react-router-dom";
import "./reserva.css";

const Reserva = () => {
  const [width, setWidth] = useState(window.innerWidth);
  const { estado, setEstado } = useContext(userContext);
  const { id } = useParams();

  const [product, setProduct] = useState();

  useEffect(() => {
    GetProductById2(id).then((p)=>setProduct(p))
  }, [id]);


  function handleChange(e) {
    switch (e.length) {
      case 1:
        const fecha = e[0].format("YYYY-MM-DD");
        let { reserva } = estado;
        let { fechaFinal, ...nuevaReserva } = reserva || {};
        nuevaReserva.fechaInicial = fecha;
        //FECHA INICIAL
        // console.log(fecha);
        setEstado((estado) => ({
          ...estado,
          reserva: nuevaReserva,
        }));
        break;
      case 2:
        let fecha1 = e[0].format("YYYY-MM-DD");
        let fecha2 = e[1].format("YYYY-MM-DD");
      
        const d1 = new Date(fecha1);
        const d2 = new Date(fecha2);
        if (d1 > d2) {
          const temp = fecha2;
          fecha2 = fecha1;
          fecha1 = temp;
        }

        setEstado((estado) => ({
          ...estado,
          reserva: {
            ...estado.reserva,
            fechaInicial: fecha1,
            fechaFinal: fecha2,
          },
        }));
        break;
      default:
        break;
    }
  }

  const getDaysArray = function (s, e) {
    for (var a = [], d = s; d.toDays() <= e.toDays(); d.add(1, "days")) {
      a.push(d.toDays());
    }
    return a;
  };
  const listadereservas = [{fechaInicial:estado?.reserva?.fechaInicial},{fechaFinal: estado?.reserva?.fechaFinal}];

    // Obtiene un array con el rango de dias pro cada reserva
  const rangos = listadereservas.map((reservada) => {
    return getDaysArray(
      new DateObject(reservada.fechaInicial),
      new DateObject(reservada.fechaFinal)
    );
  });

  let dias = rangos;
  if (rangos.length !== 0) {
    dias = rangos.reduce((a, b) => {
      return [...new Set([...a, ...b])];
    });
  }
  //propiedades del calendario propias de la reserva

  let filtro = new DateObject().subtract(0, "days");
  let arrow = (direction, handleClick) => (
    <Flecha direction={direction} click={handleClick} />
  );
  let calReserva;
  if (width < 768) {
    const PropiedadesCal = {
      readOnly: false,
      range: true,
      onChange: handleChange,
      minDate: filtro,
      numberOfMonths: 1,
      locale: gregorian_en_lowercase,
      renderButton: arrow,
      className: "reserva",
      minDate: new DateObject().subtract(60, "days"),
      maxDate: new DateObject().add(364, "days"),
       /*
      mapDays: ({ date, today }) => {
        let props = {};
        let result = date.toDays() - today.toDays();
        if (result < 0) props.disabled = true;

        const isReserved = dias.includes(date.toDays());
        if (isReserved) {
          props.disabled = true;
        }
        return props;
      }*/
      
    };
    calReserva = <Calendario {...PropiedadesCal} />;
  } else {
    const PropiedadesCal = {
      readOnly: false,
      range: true,
      onChange: handleChange,
      minDate: filtro,
      numberOfMonths: 2,
      locale: gregorian_en_lowercase,
      renderButton: arrow,
      className: "reserva",
      minDate: new DateObject().subtract(60, "days"),
      maxDate: new DateObject().add(364, "days"),
       /*
      mapDays: ({ date, today }) => {
        let props = {};
        let result = date.toDays() - today.toDays();
        if (result < 0) props.disabled = true;

        const isReserved = dias.includes(date.toDays());
        if (isReserved) {
          props.disabled = true;
        }
        return props;
      }*/
      
    };
    calReserva = <Calendario {...PropiedadesCal} />;
  }

  return (
    product?(
    <div className="pantalla__reserva">
     <Header/>
    
     <BloqueHeader/>
      <h3 className="reserva__titulo">Completá tus datos</h3>
      <div className="pantalla__reserva--contenedor">
        <div className="pantalla__reserva--contenedor__principal">
          <FormReserva />
          <h3 className="reserva__titulo--calendario">
            Seleccioná tu fecha de reserva
          </h3>
          <div className="reserva__calendario">{calReserva}</div>

          <h3 className="reserva__horario">Tu horario de llegada</h3>

          <DetalleReservaSelect />
        </div>
        <div className="pantalla__reserva--contenedor__secundario">
          <DetalleReserva />
        </div>
      </div>
      <div className="reserva__politicas">
        <h3>Què tenès que saber</h3>
        <hr />
      <BloquePoliticas politicas={product.politicas}/>
      </div>

      <Footer />
    </div>):<div></div>
  );
};

export default Reserva;
