import { Carousel } from "../lib";

import "./carousel-principal.css";

function Slider({imagenes}) {
  const data = imagenes

  const captionStyle = {
    fontSize: "2em",
    fontWeight: "bold",
  };
  const slideNumberStyle = {
    fontSize: "20px",
    fontWeight: "bold",
  };
  return (
    <div className="carousel__principal">
      <div style={{ textAlign: "center" }}>
        <div
          style={{
            padding: "0 0px",
          }}
        >
          <Carousel
            data={data}
            time={2000}
            width="850px"
            height="450px"
            captionStyle={captionStyle}
            radius="10px"
            slideNumber={true}
            slideNumberStyle={slideNumberStyle}
            captionPosition="bottom"
            automatic={true}
            dots={true}
            pauseIconColor="white"
            pauseIconSize="40px"
            slideBackgroundColor="darkgrey"
            slideImageFit="cover"
            thumbnails={true}
            thumbnailWidth="100px"
            showNavBtn={true}
            style={{
              textAlign: "center",
              maxWidth: "850px",
              margin: "40px auto",
              padding: "0px 0px",
            }}
          />
        </div>
      </div>
    </div>
  );
}

export default Slider;
