import React from "react";

const CheckBoxPolitica = ({id,titulo,setPoliticas}) => {
    const [politica, setPolitica] = useState("");
  const handleChangePolitica = (event) => {
    setPolitica(event.target.value);
  };
  return (
    <div>
      <h5 className="creacionProducto__subtitulo">{titulo}</h5>
      <div className="input-box2">
        <label htmlFor="descripcion1">Descripcion</label>
        <textarea
          name="descripcion1"
          id="descripcion1"
          placeholder="Escribir aquí"
          cols="100"
          rows="10"
          value={politica}
          onChange={handleChangePolitica}
        />
      </div>
    </div>
  );
};

export default CheckBoxPolitica;
