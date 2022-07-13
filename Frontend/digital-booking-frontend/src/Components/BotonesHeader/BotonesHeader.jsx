import React from "react";

import Boton from "../Boton/Boton";

const BotonesHeader = () => {
  return (
    <>
      <Boton link="/registro" titulo="Crear cuenta" clase="registro" />
      <Boton link="/login" titulo="Iniciar sesión" clase="login" />
    </>
  );
};

export default BotonesHeader;
