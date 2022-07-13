import React from 'react'

const AgregarAtributos = (nombreCaracteristica,iconoCaracteristica,) => {
  return (
    <div className="creacionProducto__contenedor__75_25">
                  <div className="input-box2">
                    <label htmlFor="nombre">Nombre</label>
                    <input
                      className="box-direccion"
                      type="text"
                      name="nombre"
                      id="nombre"
                      placeholder="Wifi"
                      value={nombre}
                      onChange={handleChangeNombre}
                      required
                    />
                  </div>
                  <div className="input-box2">
                    <label htmlFor="icono">icono</label>
                    <input
                      className="box-direccion"
                      type="icono"
                      name="icono"
                      id="icono"
                      placeholder="fa-Wifi"
                      value={nombre}
                      onChange={handleChangeNombre}
                      required
                    />
                  </div>
                </div>
  )
}

export default AgregarAtributos