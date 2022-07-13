import React from "react";

import {
  faFacebook,
  faInstagram,
  faLinkedinIn,
  faTwitter,
} from "@fortawesome/free-brands-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import "./iconos-sociales.css";

const IconosSociales = () => {
  return (
    <div className="iconos-sociales">
      <FontAwesomeIcon icon={faFacebook} />
      <FontAwesomeIcon icon={faLinkedinIn} />
      <FontAwesomeIcon icon={faTwitter} />
      <FontAwesomeIcon icon={faInstagram} />
    </div>
  );
};

export default IconosSociales;
