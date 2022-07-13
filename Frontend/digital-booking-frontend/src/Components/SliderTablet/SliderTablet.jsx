import React from "react";

import "react-responsive-carousel/lib/styles/carousel.min.css";
import { Carousel } from "react-responsive-carousel";

import "./slider-tablet.css";

function SliderTablet() {
  return (
    <div className="carousel__tablet">
      <Carousel
        showThumbs={true}
        showStatus={true}
        infiniteLoop
        autoPlay
        useKeyboardArrows
        transitionTime={3000}
        width="100%"
      >
        <div className="slide-holder">
          <img
            alt="carousel de fotos"
            src="https://cdn.pixabay.com/photo/2018/08/06/19/49/design-3588214__340.jpg"
          />
        </div>
        <div className="slide-holder">
          <img
            alt="carousel de fotos"
            src="https://cdn.pixabay.com/photo/2017/08/06/14/56/people-2593251__340.jpg"
          />
        </div>
        <div className="slide-holder">
          <img
            alt="carousel de fotos"
            src="https://cdn.pixabay.com/photo/2016/11/17/09/28/hotel-1831072__340.jpg"
          />
        </div>
        <div className="slide-holder">
          <img
            alt="carousel de fotos"
            src="https://cdn.pixabay.com/photo/2021/08/27/01/33/bedroom-6577523__340.jpg"
          />
        </div>
        <div className="slide-holder">
          <img
            alt="carousel de fotos"
            src="https://cdn.pixabay.com/photo/2017/01/14/12/48/hotel-1979406__340.jpg"
          />
        </div>
      </Carousel>
    </div>
  );
}

export default SliderTablet;
