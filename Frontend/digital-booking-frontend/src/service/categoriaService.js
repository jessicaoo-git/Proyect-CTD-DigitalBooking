import axios from "axios";
import instanceApi from "./instanceApi";

function GetCategoria(setCard, setCategoriaId=null) {
  // eslint-disable-next-line no-unused-expressions
  axios;
  instanceApi
    .get("/categorias?pagina=0")
    .then(({ data: response }) => {
      const { resultados } = response;
      setCard(resultados);
      if(setCategoriaId!==null)setCategoriaId(resultados[0].id)
    })
    .catch((error) => {
      throw new Error(`the products could not be returned correctly: ${error}`);
    });
}

export default GetCategoria;
