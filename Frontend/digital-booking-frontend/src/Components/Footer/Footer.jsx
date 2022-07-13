import React from "react";

import IconosSociales from "../IconosSociales/IconosSociales";

import "./footer.css";

const Footer = () => {
  return (
    <div className="footer">
      <div className="contenedor-footer">
        <div className="parrafo-footer">
          <p>©2021 Digital Booking</p>
        </div>
        <div className="iconos-sociales">
          <IconosSociales />
        </div>
      </div>
    </div>
  );
};

export default Footer;
