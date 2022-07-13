import React from "react";
import { Routes, Route, BrowserRouter } from "react-router-dom";

import Home from "../pages/Home/Home";
import Login from "../pages/Login/Login";
import Registro from "../pages/Registro/Registro.jsx";
import DetalleProducto from "../pages/DetalleProducto/DetalleProducto";
import Reserva from "../pages/Reserva/Reserva";
import MisReservas from "../Components/MisReservas/MisReservas";
import CreacionProducto from "../pages/CreacionProducto/CreacionProducto";
import Favoritos from "../pages/Favoritos/Favoritos";
import { Verify } from "../pages/Verify/Verify";

const AdminRoutes = () => {

  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/*" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/registro" element={<Registro />} />
          <Route path="/producto/:id" element={<DetalleProducto />} />
          <Route path="/producto/:id/reserva" element={<Reserva />} /> 
          <Route path="/reservas" element={<MisReservas />} /> 
          <Route path="/administracion/crear-producto" element={<CreacionProducto />} /> 
          <Route path="/producto/:id/reserva" element={<Reserva />} />
          <Route path="/verify" element={<Verify />} />
          <Route path="/favoritos" element={<Favoritos />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
};

export default AdminRoutes;
