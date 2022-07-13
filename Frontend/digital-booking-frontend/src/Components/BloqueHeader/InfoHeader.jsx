import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { GetProductById } from "../../service/productoService";

import "./bloque-header.css";

const InfoHeader = () => {

    const { id } = useParams();
    const [product, setProduct] = useState(
      []);
  
    useEffect(() => {
      GetProductById(id, setProduct);
    }, [id]);


  return (
    <div className="InfoHeader">
      <h5>{product.categoria.titulo}</h5>
      <h3>{product.titulo}</h3>
    </div>
  );
};

export default InfoHeader;
