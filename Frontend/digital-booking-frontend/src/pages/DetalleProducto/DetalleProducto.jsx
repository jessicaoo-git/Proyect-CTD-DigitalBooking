import React, { useEffect, useState } from "react";

import Footer from "../../Components/Footer/Footer";
import Header from "../../Components/Header/Header";
import MapaGeneral from "../../Components/Mapa/MapaGeneral";
import BloqueHeader from "../../Components/BloqueHeader/BloqueHeader";
import BloqueUbicacion from "../../Components/DatosUbicacion/BloqueUbicacion";
import IconosCompartir from "../../Components/IconosCompartir/IconosCompartir";
import BloqueCalendario from "../../Components/BloqueCalendario/BloqueCalendario";
import BloqueDescripcion from "../../Components/BloqueDescripcion/BloqueDescripcion";
import CaracteristicasProducto from "../../Components/CaracteristicasProducto/CaracteristicasProducto";
import BloquePoliticas from "../../Components/BloquePoliticas/BloquePoliticas";
import BloqueImagenes from "../../Components/BloqueImagenes/BloqueImagenes";
import SliderTablet from "../../Components/SliderTablet/SliderTablet";

import "./detalleProducto.css";
import { GetProductById, GetProductById2 } from "../../service/productoService";
import { useParams } from "react-router-dom";

const DetalleProducto = () => {
  const { id } = useParams();

  const [product, setProduct] = useState();
 
  useEffect(() => {
    GetProductById2(id).then((p)=>setProduct(p))
  }, [id]);
  return (
    product?(
    <div className="detalle__producto">
      <Header />

      <BloqueHeader />
      <BloqueUbicacion ciudad={product.ciudad}/>
      <div className="compartir">
        <IconosCompartir />
      </div>
      <BloqueImagenes imagenes={product.imagenes}/>
      <SliderTablet />
      <h2 className="detalle__producto--titulo alojate">
        Alójate en el corazón de {product.nombre}
      </h2>
      <BloqueDescripcion descripcion={product.descripcion}/>
      <h2 className="detalle__producto--titulo">¿Que ofrece este lugar?</h2>
      <hr />
      <CaracteristicasProducto  caracteristicas={product.caracteristicas} />
      <h2 className="detalle__producto--titulo fechas">Fechas disponibles</h2>
      <BloqueCalendario />
      <h2 className="detalle__producto--titulo">¿Donde vas a estar?</h2>
      <hr />
      <p className="lugar">Buenos Aires, Argentina</p>
      <MapaGeneral />
      <h2 className="detalle__producto--titulo">Qué tenés que saber</h2>
      <hr />
      <BloquePoliticas politicas={product.politicas} />
      <small className="temporal"></small>
      <Footer />
    </div>)
    :<div></div>
  );
};

export default DetalleProducto;
