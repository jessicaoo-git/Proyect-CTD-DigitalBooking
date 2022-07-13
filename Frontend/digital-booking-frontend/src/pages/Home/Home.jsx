import React, { useEffect, useState }  from "react";
import BloqueDeBusqueda from "../../Components/BloqueBuscador/BloqueDeBusqueda";
import BloqueListado from "../../Components/BloqueListado/CardListado";
import Footer from "../../Components/Footer/Footer";
import Header from "../../Components/Header/Header";

import { GetProducto, Paginacion } from "../../service/productoService";
import UseCounter from "../../hook/useCounter";

import GetCategoria from "../../service/categoriaService";

import "./home.css";

const Home = () => {
  const [ciudadIdYFecha, setCiudadIdYFecha] =useState([])
  function handleChangeCiudadIdYFecha(ciudadId,fecha1,fecha2)
   {
      setCiudadIdYFecha({
        "ciudadId":ciudadId,
        "fecha1":fecha1,
        "fecha2":fecha2
      })
   }
  //--------------------------------------
 
  const [producto, setProducto] = useState([]);
  const [categoriaId, setcategoriaId] = useState(null);
  const [card, setCard] = useState([]);
  const { page, decremento, incremento } = UseCounter();

  useEffect(() => {
    Paginacion(page, setProducto, ciudadIdYFecha,categoriaId);
  }, []);
  useEffect(() => {
    GetCategoria(setCard);
  }, []);
  useEffect(() => {
    Paginacion(page, setProducto, ciudadIdYFecha,categoriaId);
  }, [page]);
  useEffect(() => {
    Paginacion(page, setProducto, ciudadIdYFecha,categoriaId);
  }, [ciudadIdYFecha]);
  
  useEffect(() => {
    Paginacion(page, setProducto, ciudadIdYFecha,categoriaId);
  }, [categoriaId]);
  
  return (
    <div className="home">
      <Header />
      <BloqueDeBusqueda handleChangeCiudadIdYFecha={handleChangeCiudadIdYFecha}/>
      <BloqueListado producto={producto} setcategoriaId={setcategoriaId}
       card={card} page={page} incremento={incremento} decremento={decremento}/>
      <Footer />
    </div>
  );
};

export default Home;
