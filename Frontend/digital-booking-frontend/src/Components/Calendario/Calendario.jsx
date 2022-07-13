import React from "react";
import { Calendar } from "react-multi-date-picker";
import "./calendario-detalles.css";

export default function Calendario(props) {
  return (
    <>
      <div className="contCalendario">
        <Calendar {...props} />
      </div>
    </>
  );
}


