import React, { useEffect, useState } from "react";
import {  useNavigate, useParams } from "react-router-dom";

import { FaChevronLeft } from "react-icons/fa";


import "./bloque-header.css";
import { GetProductById } from "../../service/productoService";

const BloqueHeader = () => {
  const { id } = useParams();
  const navigate = useNavigate()

  const [product, setProduct] = useState(
    []);
  const [categoria, setcategoria] = useState(
    []);

  useEffect(() => {
    GetProductById(id, setProduct, setcategoria);
  }, [id]);

  const handleClick = () => {
    navigate(-1)
  }
  
  return (
    <div className="cont-BloqueHeader">
      <div className="contenedor-info">
     
      <div className="InfoHeader">
      <p>{categoria.titulo}</p>
      <h3>{product.titulo}</h3> 
    </div>
       
      </div>

      <div className="flecha-home">
      
          <button onClick={handleClick}>
            <FaChevronLeft />
          </button>
      
      </div>
    </div>
  );
};

export default BloqueHeader;
