/* eslint-disable no-unused-expressions */
import axios from "axios";
import instanceApi from "./instanceApi";

function GetProducto(setProducto) {
  axios;
  instanceApi
    .get("/productos?pagina=0")
    .then(({ data: response }) => {
      const { resultados } = response;
      setProducto(resultados);
    })
    .catch((error) => {
      throw new Error(`the products could not be returned correctly: ${error}`);
    });
}

function GetProductById(
  id,
  setProduct,
  setCategoria = null,
  setImagenes = null,
  setCiudades= null
) {
  axios;
  instanceApi
    .get(`/productos/${id}`)
    .then(({ data: response }) => {
      const dat = response;
      setProduct(dat);
      if (setCategoria != null) setCategoria(dat.categoria);
      if (setImagenes != null) setImagenes(dat.imagenes[0]);
      if (setCiudades != null) setCiudades(dat.ciudad);
    })
    .catch((error) => {
      throw new Error(`the products could not be returned correctly: ${error}`);
    });
}

function GetProductById2(id) {
  axios;
  return instanceApi
    .get(`/productos/${id}`)
    .then(({ data: response }) => {
      const dat = response;
      return dat
    })
    .catch((error) => {
      throw new Error(`the products could not be returned correctly: ${error}`);
    });
}


function Paginacion(page, setProducto, ciudadIdYFecha=null, categoria_id=null) {
  let uri=`/productos/?pagina=${page}`
  if(ciudadIdYFecha!=null){
    let ciudad_id= ciudadIdYFecha.ciudadId
    let fecha1= ciudadIdYFecha.fecha1
    let fecha2= ciudadIdYFecha.fecha2
    ciudad_id!=null? 
      uri+=`&&ciudad_id=${ciudad_id}`:""
    fecha1!=null && fecha2!=null ?
       uri+=`&&fecha_inicio=${ciudadIdYFecha.fecha1}`+`&&fecha_fin=${ciudadIdYFecha.fecha2}`:""
    
  }
  if(categoria_id!=null){
    uri+=`&&categoria_id=${categoria_id}`
  }

  axios;
  instanceApi
    .get(uri)
    .then(({ data: response }) => {
      const { resultados } = response;
      setProducto(resultados);
    })
    .catch((error) => {
      throw new Error(`the products could not be returned correctly: ${error}`);
    });
}

function FiltroCategoria(id, setCard) {
  axios;
  instanceApi
    .get(`productos?categoria_id=${id}`)
    .then(({ data: response }) => {
      const { resultados } = response;
      setCard(resultados);
    })
    .catch((error) => {
      throw new Error(`the products could not be returned correctly: ${error}`);
    });
}

function PostProducto(nuevoProducto) {
  let token=localStorage.getItem("token")
  const config = {
    headers: { Authorization: `Bearer ${token}` }
  };

  const bodyParameters = nuevoProducto;
  axios;
  instanceApi
    .post("/productos",bodyParameters,config
     )
    .then(({ data: response }) => {
      const { resultados } = response;
    })
    .catch((error) => {
      throw new Error(`the products could not be returned correctly: ${error}`);
    });
}


function cantidadProductosPorCategoria(categoria_id) {
  let uri=`/productos/?categoria_id=${categoria_id}`
  axios;
  return instanceApi
    .get(uri)
    .then(({ data: response }) => {
      return response
    })
    .catch((error) => {
      throw new Error(`the products could not be returned correctly: ${error}`);
    });
}

export { GetProducto, GetProductById, GetProductById2, Paginacion, FiltroCategoria, PostProducto, cantidadProductosPorCategoria };
