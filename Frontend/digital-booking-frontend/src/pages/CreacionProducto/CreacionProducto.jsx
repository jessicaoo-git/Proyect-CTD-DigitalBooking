import React, { useEffect, useState } from "react";

import Footer from "../../Components/Footer/Footer";
import Header from "../../Components/Header/Header";

import GetCiudades from "../../service/ciudadService";
import GetCategoria from "../../service/categoriaService";
import GetCaracteristicas from "../../service/caracteristicaService";
import {PostProducto} from "../../service/productoService";
import { FaChevronLeft } from "react-icons/fa";
import "./creacionProducto.css";
import { useNavigate } from "react-router-dom";
import GetPoliticasId from "../../service/politicasService";
import Swal from "sweetalert2";

const CreacionProducto = () => {

  //Estados de los primeros campos del form nombre,categoria,ciudad,direccion
  //Las ciudades y las categorias son traidas con el hook useEffect a traves de servicios que consumen la api

  const [nombrePropiedad, setNombrePropiedad] = useState("");
  const handleChangeNombrePropiedad = (event) => {
    setNombrePropiedad(event.target.value);
  };

  const [categorias, setCategorias] = useState([]);
  const [categoriaId, setCategoriaId] = useState("");
  const handleChangeCategoriaId = (event) => {
    setCategoriaId(event.target.value);
  };

  const [direccion, setDireccion] = useState("");
  const handleChangeDireccion = (event) => {
    setDireccion(event.target.value);
  };

  const [ciudades, setCiudades] = useState([]);
  const [ciudadId, setCiudadId] = useState("");
  const handleChangeCiudadId = (event) => {
    setCiudadId(event.target.value);
  };

  const [descripcion, setDescripcion] = useState("");
  const handleChangeDescripcion = (event) => {
    setDescripcion(event.target.value);
  };

  //estados de Agregar Politica
  const [normasDeCasa, setNormasDeCasa] = useState("");
  const handleChangeNormasDeCasa = (event) => {
    setNormasDeCasa(event.target.value);
  };
  const [saludYSeguridad, setSaludYSeguridad] = useState("");
  const handleChangeSaludYSeguridad = (event) => {
    setSaludYSeguridad(event.target.value);
  };
  const [politicasDeCancelacion, setPoliticasDeCancelacion] = useState("");
  const handleChangePoliticasDeCancelacion = (event) => {
    setPoliticasDeCancelacion(event.target.value);
  };

  const [politicasId, setPoliticasId] = useState([]);


  //Logica y estados de Checkbox de caracteristicas
  //Las caracteristicas y sus imagenes son traidas con el hook useEffect a traves de servicios que consumen la api

  const [caracteristicas, setCaracteristicas] = useState([]);
  const [checkedState, setCheckedState] = useState(
    [1]
  );
  const handleChangeCaracteristicas = (position) => {
    const indexPosition=position
    const updatedCheckedState = checkedState.map((item, index) =>
      index === (indexPosition) ? !item : item
    );
    setCheckedState(updatedCheckedState);
    
  };


  //Logica y estados de Agregar Imagenes
 
  const [imagenUrl, setImagenUrl] = useState("");
  const handleChangeImagenUrl = (event) => {
    setImagenUrl(event.target.value);
  };

  const [imagenes, setImagenes] = useState([]);

  const[contadorIdImg,setContadorIdImg]= useState(1)

  const agregarImagen = () => {
    if (imagenUrl.length !== "") {
      setImagenes([...imagenes, {
        id:contadorIdImg,
        url:imagenUrl
      }]);
      setContadorIdImg(contadorIdImg+1);
      setImagenUrl("");
    }
  };

  const quitarImagen = (id) => {
    let auxImg= [...imagenes].filter((e)=>e.id!==id)
    setImagenes(auxImg);    
  };  

  
  //UseEffect para traer ciudades, categorias y caracteristicas
  
  useEffect(() => {
    GetCiudades(setCiudades,setCiudadId);
    GetCategoria(setCategorias,setCategoriaId);
    GetCaracteristicas(setCaracteristicas,setCheckedState);
    GetPoliticasId(setPoliticasId);
  }, []);


  //Manejador para enviar el formulario
  const handleSubmit = (e) => {
    e.preventDefault();

    const nuevoProducto={
      "titulo": nombrePropiedad,
      "descripcion": descripcion,
      "ciudad": {
          "id": ciudadId
      },
      "categoria": {
          "id": categoriaId
      },
      "disponible": true,
      "imagenes":
        imagenes.map((imgObj)=>{
          return({
            "titulo":imgObj.url,
            "url":imgObj.url
          })
        })
        ,
      "caracteristicas":
      caracteristicas.map((caracteristica,index)=>{
        if(checkedState[index]==true){        
        return({
          "id":caracteristica.id          
        })}
      }),
      "politicas":[
          {
          "titulo":{
              "id":politicasId[0].id
          },
          "descripcion": normasDeCasa
          },
          {
            "titulo":{
                "id":politicasId[1].id
            },
            "descripcion": saludYSeguridad
          },
          {
            "titulo":{
                "id":politicasId[2].id
            },
            "descripcion": politicasDeCancelacion
            }

      ]
            
    };
    PostProducto(nuevoProducto)
  };
  const navigate = useNavigate()
  const handleClick = () => {
    navigate(-1)
  }


  const confirmCrear = () => {
    Swal.fire({
      title: "¿Está seguro que desea guardar el producto?",
      imageWidth: 200,
      imageHeight: 100,
      showCancelButton: true,
      confirmButtonColor: "#ff7059",
      cancelButtonColor: "var(--color-dos)",
      confirmButtonText: "Si",
      cancelButtonText: "No",
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire("Tu propiedad se ha creado con éxito!", "", "success");
        navigate('/')
      }
    });
  };

  


  return (
    <div>
      <Header />
      <div className="cont-BloqueHeader">
      <div className="contenedor-info">
     
      <div className="InfoHeader">
        <h2>Administración</h2>
    </div>
       
      </div>

      <div className="flecha-home">
      
          <button onClick={handleClick}>
            <FaChevronLeft />
          </button>
      
      </div>
    </div>
      <h2 className="creacionProducto__titulo">Crear Propiedad</h2>
      <div className="pantalla__creacionProducto--contenedor">
        <div className="pantalla__creacionProducto--contenedor__principal">
          <div className="creacionProducto__form">
            <form onSubmit={handleSubmit}>
              <div className="formPrincipal">
                <div className="input-box">
                  <label htmlFor="nombrePropiedad">
                    Nombre de la propiedad
                  </label>
                  <input
                    type="text"
                    name="nombrePropiedad"
                    id="nombrePropiedad"
                    placeholder="Hotel Hermirage"
                    value={nombrePropiedad}
                    onChange={handleChangeNombrePropiedad}
                    required
                  />
                </div>

                <div className="input-box">
                  <label htmlFor="categoria">Categoria</label>
                  <select
                    type="text"
                    name="categoria"
                    id="categoria"
                    placeholder="Hotel"
                    value={categoriaId}
                    onChange={handleChangeCategoriaId}
                    required
                  >
                    {categorias.map((categoriasMap,index) => {                     
                      return(
                      <option value={categoriasMap.id} key={categoriasMap.id}>
                        {categoriasMap.titulo}
                      </option>)
                      }
                    )}
                  </select>
                </div>

                <div className="input-box">
                  <label htmlFor="direccion">Direccion</label>
                  <input
                    className="box-direccion"
                    type="text"
                    name="direccion"
                    id="direccion"
                    placeholder="Av. Colon 1643"
                    value={direccion}
                    onChange={handleChangeDireccion}
                    required
                  />
                </div>

                <div className="input-box">
                  <label htmlFor="ciudad">Ciudad</label>
                  <select
                    type="text"
                    name="ciudad"
                    id="ciudad"
                    placeholder="Ciudad"
                    required
                    value={ciudadId}                    
                    onChange={handleChangeCiudadId}
                  >
                    {ciudades.map((ciudadMap) => (
                      <option value={ciudadMap.id} key={ciudadMap.id}>
                        {ciudadMap.nombre}
                      </option>
                    ))}
                  </select>
                </div>
              </div>

              <div className="input-box2">
                <label htmlFor="descripcion">Descripcion</label>
                <textarea
                  name="descripcion"
                  id="descripcion"
                  placeholder="Escribir aquí"
                  cols="200"
                  rows="5"
                  value={descripcion}
                  onChange={handleChangeDescripcion}
                />
              </div>
              <h4 className="creacionProducto__subtitulo">Agregar Atributos</h4>

              <div className="creacionProducto__contenedor__contraste">
                <ul className="creacionProducto_contenedor_checkboxes">
                  {caracteristicas.map(({ id, titulo, urlImagen },index) => {
                    
                    return (
                      <li key={id}>
                        <div className="input-checkbox">
                          <div
                            className="img-checkbox"
                            style={{ backgroundImage: `url(${urlImagen})` }}
                          ></div>
                          <input
                            type="checkbox"
                            name={titulo}
                            id={index}
                            value={id}
                            onChange={() => handleChangeCaracteristicas(index)}
                            checked={checkedState[index]}
                          />
                          <label htmlFor={`custom-checkbox-${id}`}>
                            {titulo}
                          </label>
                        </div>
                      </li>
                    );
                  })}
                </ul>
              </div>

              <h4 className="creacionProducto__subtitulo">
                Politicas del Producto
              </h4>
              <div className="creacionProducto__contenedor_politicas">
                <div>
                  <h5 className="creacionProducto__subtitulo_inde">
                    Normas de la casa
                  </h5>
                  <div className="input-box2">
                    <label htmlFor="descripcion1">Descripcion</label>
                    <textarea
                      name="descripcion1"
                      id="descripcion1"
                      placeholder="Escribir aquí"
                      cols="100"
                      rows="10"
                      value={normasDeCasa}
                      onChange={handleChangeNormasDeCasa}
                    />
                  </div>
                </div>
                <div>
                  <h5 className="creacionProducto__subtitulo_inde">
                    Salud y seguridad
                  </h5>
                  <div className="input-box2">
                    <label htmlFor="descripcion2">Descripcion</label>
                    <textarea
                      name="descripcion2"
                      id="descripcion2"
                      placeholder="Escribir aquí"
                      cols="100"
                      rows="10"
                      value={saludYSeguridad}
                      onChange={handleChangeSaludYSeguridad}
                    />
                  </div>
                </div>
                <div>
                  <h5 className="creacionProducto__subtitulo_inde">
                    Políticas de cancelación
                  </h5>
                  <div className="input-box2">
                    <label htmlFor="descripcion3">Descripcion</label>
                    <textarea
                      name="descripcion3"
                      id="descripcion3"
                      placeholder="Escribir aquí"
                      cols="100"
                      rows="10"
                      value={politicasDeCancelacion}
                      onChange={handleChangePoliticasDeCancelacion}
                    />
                  </div>
                </div>
              </div>

              <h4 className="creacionProducto__subtitulo">Cargar Imagenes</h4>
              <div className="creacionProducto__contenedor__contraste">
                {imagenes.map(({id,url}) => {
                  return (
                    <div key={id} className="creacionProducto__felexContainer">
                      <div className="input-box2">
                        <input
                          className="box-elemento-imagen"
                          type="text"
                          name="imagenUrl"
                          id={`imgurl-${id}`}
                          placeholder="Insertar https://"
                          value={url}
                          disabled
                        />
                      </div>
                      <input type="button" className="botonQuitar" onClick={()=>quitarImagen(id)} />
                    </div>
                  );
                })}
                <div className="creacionProducto__felexContainer">
                  <div className="input-box2" style={{ marginTop: "15px" }}>
                    <input
                      
                      type="text"
                      name="imagenUrl"
                      id="imagenUrl"
                      placeholder="Insertar https://"
                      value={imagenUrl}
                      onChange={handleChangeImagenUrl}
                    />
                  </div>
                  <input
                    type="button"
                    className="botonAgregar"
                    onClick={agregarImagen}
                  />
                </div>
              </div>

              <div className="contenedor_centrado">
                <button type="submit" className="crearProducto" onClick={()=>confirmCrear()}>
                  Crear
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default CreacionProducto;
