import React, { useContext } from "react";
import Select from "react-select";

import { IoIosCheckmarkCircleOutline } from "react-icons/io";

import "./detalle-reserva-select.css";
import { userContext } from "../../context/UserContext";

export default function DetalleReservaSelect() {

  const { estado, setEstado } = useContext(userContext);

  const options = [

    { value: 10, label: "10:00 AM" },
    { value: 11, label: "11:00 AM" },
    { value: 12, label: "12:00 PM" },
    { value: 13, label: "1:00 PM" },
    { value: 14, label: "2:00 PM" },
    { value: 15, label: "3:00 PM" },
    { value: 16, label: "4:00 PM" },
    { value: 17, label: "5:00 PM" },
    { value: 18, label: "6:00 PM" },
    { value: 19, label: "7:00 PM" },
    { value: 20, label: "8:00 PM" },
    { value: 21, label: "9:00 PM" },
    { value: 22, label: "10:00 PM" },
    { value: 23, label: "11:00 PM" },
  ];
  function handleChangeHora(e) {
    setEstado({
      ...estado,
      reserva: {
        ...estado.reserva,
        hora: e.value,
      },
    });
  }
  return (
    <div className="select_contenedor">
      <div className="select__reserva--ok">
        <IoIosCheckmarkCircleOutline className="ok" />
        <h3>
          Tu habitación va a estar lista para el check-in entre las 10:00 AM y
          las 11:00 PM
        </h3>
      </div>
      <p>Indicá tu horario estimado de llegada </p>

      <div className="select__horario">
        <Select
          options={options}
          className="select"
          placeholder="Seleccionar hora de llegada"
          name="horaReserva"
          onChange={handleChangeHora}

        />
      </div>
    </div>
  );
}
