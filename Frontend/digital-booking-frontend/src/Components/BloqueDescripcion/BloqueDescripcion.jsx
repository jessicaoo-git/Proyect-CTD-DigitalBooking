import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { GetProductById } from "../../service/productoService";

import "./bloque-descripcion.css";

const BloqueDescripcion = ({descripcion}) => {
  const { id } = useParams();

  const [product, setProduct] = useState([]);
  const [imagenes, setImagenes] = useState([]);
  const [categoria, setcategoria] = useState([]);
  const [ciudades, setCiudades] = useState([]);

  useEffect(() => {
    GetProductById(id, setProduct, setcategoria, setImagenes, setCiudades);
  }, [id]);
  return (
    <div className="bloque__descripcion">
      <p>{descripcion}</p>
    </div>
  );
};

export default BloqueDescripcion;
