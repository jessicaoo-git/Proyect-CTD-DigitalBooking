import React from "react";

import "./detalle-reserva-check.css";

export default function DetalleReservaCheck(props) {
  const { check, fecha } = props;
  return (
    <div className="check">
      <h5>{check}</h5>
      <h5>{fecha === undefined ? "__/__/__" : fecha}</h5>
    </div>
  );
}
