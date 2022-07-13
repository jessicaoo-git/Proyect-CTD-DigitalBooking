/* eslint-disable no-unused-expressions */
import axios from "axios";
import instanceApi from "./instanceApi";
function agregarFavorito(id, setBandera=null) {
  axios;
  instanceApi
  .post(`/auth/favoritos`, {
    usuario_id: localStorage.getItem("id"),
    producto_id: {id: id}
  })
    .then(({data: response}) => {
      console.log(response);
      if (setBandera != null) {
        if (response > 0) {
          setBandera(true);
        } else{
          setBandera(false);
        }}
    })
    .catch((error) => {
      throw new Error(`the verification could not be returned correctly: ${error}`);
    });
}

function borrarFavorito(usuario_id, producto_id, setBandera=null) {
    axios;
    instanceApi
    .delete(`/auth/favoritos?usuario_id=${usuario_id}&producto_id=${producto_id}`)
        .then(({data: response}) => {
          if (setBandera != null) {
            if (response > 0) {
              setBandera(false);
            } else{
              setBandera(true);
            }}
          })
        .catch((error) => {
        throw new Error(`the verification could not be returned correctly: ${error}`);
        });
}

function MostrarFavorito(usuario_id,producto_id, setBandera) {
  axios;
  instanceApi
  .get(`/auth/favorito?usuario_id=${usuario_id}&producto_id=${producto_id}`)
    .then(({data: response}) => {
      if (response > 0) {
        setBandera(true);
      } else {
        setBandera(false);
      }
    })
    .catch((error) => {
      throw new Error(`the verification could not be returned correctly: ${error}`);
    });
}

function PaginacionFavoritos(page, setFavorito, id) {
  let uri=`auth/favoritos/${id}?pagina=${page}`    
  axios;
  instanceApi
    .get(uri)
    .then(({ data: response }) => {
      const { resultados } = response;
      if (resultados.length > 0) {
        setFavorito(resultados);
      } else {
        setFavorito(false)
      }
        
    })
    .catch((error) => {
      throw new Error(`the products could not be returned correctly: ${error}`);
    });
}

export {agregarFavorito, borrarFavorito, MostrarFavorito, PaginacionFavoritos};