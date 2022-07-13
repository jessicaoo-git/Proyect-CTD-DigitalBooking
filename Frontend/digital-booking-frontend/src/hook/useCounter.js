import { useState } from "react";

const UseCounter = (initialState = 0) => {
  const [page, setPage] = useState(initialState);
  const incremento = () => {
    if (page >= 1) {
      setPage(page);
    } else if (page >= 0) {
      setPage(page + 1);
    }
    return page;
  };
  const decremento = () => {
    if (page > 0) {
      setPage(page - 1);
    }
    return page;
  };

  return {
    page,
    incremento,
    decremento,
  };
};

export default UseCounter;
