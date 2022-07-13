import React from "react";

import { UseModal } from "../../hook/UseModal";
import { faShareNodes } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Modal from "./Modal";
import {
  faFacebook,
  faWhatsapp,
  faLinkedinIn,
  faTwitter,
} from "@fortawesome/free-brands-svg-icons";


const Modals = () => {
  const [isOpen1, openModal1, closeModal1] = UseModal(false);

  return (
    <div className="modalOpen">
      <button onClick={openModal1}>
        <FontAwesomeIcon icon={faShareNodes} className="icono__compartir" />
      </button>
      <Modal isOpen={isOpen1} closeModal={closeModal1}>
        <div className="container-style-modal">
          <h2>Comparte este hotel</h2>
          <div className="contenedor__compartir">
            <a
              href="https://api.whatsapp.com/send?text=www.mipagina.com"
              target="_blank"
            >
              <FontAwesomeIcon icon={faWhatsapp} className="ws" />
            </a>
            <a
              href="http://www.facebook.com/sharer.php?u=www.digitalbooking/hermitagehotel.com&t=pagina de desarrollo web"
              target="_blank"
            >
              <FontAwesomeIcon icon={faFacebook} className="fc" />
            </a>
            <a
              href="https://twitter.com/intent/tweet?text=MIEpresa&url=www.digitalbooking/hermitagehotel.com&via=Empresa&hashtags=#miempresa"
              target="_blank"
            >
              <FontAwesomeIcon icon={faTwitter} className="tw" />
            </a>
            <a
              href="http://www.linkedin.com/shareArticle?url=www.digitalbooking/hermitagehotel.com"
              target="_blank"
            >
              <FontAwesomeIcon icon={faLinkedinIn} className="ln" />
            </a>
          </div>
        </div>
      </Modal>
    </div>
  );
};

export default Modals;
