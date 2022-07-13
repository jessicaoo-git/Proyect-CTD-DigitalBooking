import React from "react";
import { useParams } from "react-router-dom";
import VerifyFail from "../../Components/VerifyFail/VerifyFail";
import VerifySuccess from "../../Components/VerifySuccess/VerifySuccess";
import GetVerificacion from "../../service/verifyService";
import { useEffect, } from "react";
import useQuery from "../../hook/useQuery";
import Header from "../../Components/Header/Header";
import Footer from "../../Components/Footer/Footer";
import "./verify.css";
import Boton from "../../Components/Boton/Boton";

export const Verify = () => {
    const [verify, setVerify] = React.useState(false);
    const {code} = useParams()
    const query = useQuery();
    useEffect(() => {
        GetVerificacion(setVerify, query.get("code"));
    },[])
    return (
        <div className="verify">
            <Header/>
        <div class="container__fallido text-center">
            {verify ? <VerifySuccess/> : <VerifyFail/>}
        </div>
        <Boton link="/home" titulo="Volver al Inicio" clase="botones" />
            <Footer/>
        </div>
    )
}