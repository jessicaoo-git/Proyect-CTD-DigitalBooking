import React from "react";

const corazonFull = "/assets/corazon-full.svg";
const corazonAzul = "/assets/corazon.png";

const Corazon = (props) => {
  const onClick = () => {
    props.onClick(props.id);
  };

  return (
    <div className="corazon-container" onClick={onClick}>
      <img
        height="40px"
        src={props.roto ? corazonFull : corazonAzul}
        alt={"corazon " + props.roto ? "No me gusta" : "Me gusta"}
      />
    </div>
  );
};

export { Corazon };
