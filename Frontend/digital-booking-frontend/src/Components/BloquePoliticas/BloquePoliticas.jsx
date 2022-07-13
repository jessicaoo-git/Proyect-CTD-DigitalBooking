import React from "react";
import "./bloque-politicas.css";

const BloquePoliticas = ({politicas}) => {

  const capitalize= (word)=> {
    return word[0].toUpperCase() + word.slice(1).toLowerCase();
  }


  return (
    <section className="bloque_politicas">
      {politicas.map(({id,titulo,descripcion})=>{
        let title= capitalize(titulo.tituloPolitica.replaceAll("_"," ").toLowerCase())
        return(
        <div key={id} className="politicaIdividual-container">
          <h3>{title}</h3>
          {descripcion.split(".")
          .map((e,i)=><p key={i}>{e}</p>)}
        </div>
        )
      })}

    </section>
  );
};

export default BloquePoliticas;
