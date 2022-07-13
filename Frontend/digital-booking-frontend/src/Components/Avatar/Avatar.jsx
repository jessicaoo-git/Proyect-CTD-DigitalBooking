import React from "react";

import StackedAvatar from "./stacked-avatar";
import Logout from "../Logout/Logout";
import Boton from "../Boton/Boton";

import "./avatar.css";
import { Link } from "react-router-dom";

const Avatar = () => {
  const userName = (localStorage.getItem('userName'));
  const userLastName = (localStorage.getItem('userLastName'));
  const rolAdmin = (localStorage.getItem('rolAdmin'))

  const avatars = [
    {
      twitterHandle: " ",
      name: `${userName} ${userLastName}`,
    },
  ];

  return (
    <div className="avatar">
      
      {rolAdmin==true || rolAdmin=="true"?<Link to={`/administracion/crear-producto`}>
      <div className="administracion">
        <h3>Administración</h3>
        <div></div>
      </div>
      </Link>:
      <div className ="botonFav">
      <div className = "botonesAvatar">
        <Link to={`/favoritos`}>
        <div className="administracion">
        <h3>Favoritos</h3>
        <div></div>
      </div>
      </Link>
      </div>
      <div className="botonFav">
      <Link to={`/reservas`}>
        <div className="administracion">
        <h3>Reservas</h3>
        <div></div>
      </div>
      </Link>
      </div>
      </div>
        }
      <StackedAvatar maxAvatars={1} round={true} size={50} avatars={avatars} className='avatar_icono' />
      <div className="bienvenido">
        <div className="saludo">
          <p className="hola">Hola,</p>
          <Logout />
        </div>
        <p className="nombre-avatar">
          {" "}
          {`${userName} ${userLastName}`}
        </p>
      </div>
    </div>
  );
};

export default Avatar;
