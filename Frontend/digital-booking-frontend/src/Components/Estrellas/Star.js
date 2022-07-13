import React from "react";

import { FaStar } from "react-icons/fa";

export default function Star({ selected = false, onSelect = (f) => f }) {
  return (
    <FaStar color={selected ? "#F0572D " : "#f0572dbb"} onClick={onSelect} />
  );
}
