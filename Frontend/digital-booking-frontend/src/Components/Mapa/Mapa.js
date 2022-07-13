import React, { Component } from "react";
import Infowindow from "./infoWindow";
import "./index.css";
const googleKey = "AIzaSyCvvF3lmmyGlWP-W_nVABH17n7bBlFj4is";
const lines = require("./lines.js").lines;
const points = require("./poins").points;

class Mapa extends Component {
  constructor(props) {
    super(props);
    this.state = {
      map: "",
      markers: [],
      lines: [],
      boxOpened: false,
      nowmarker: {},
      information: "",
    };
  }

  componentDidMount() {
    window.gm_authFailure = this.gm_authFailure;
    window.initMap = this.initMap;
    loadJS(
      "https://maps.googleapis.com/maps/api/js?key=" +
        googleKey +
        "&callback=initMap"
    );
  }

  initMap = () => {
    let updateThis = this;

    let map = new window.google.maps.Map(document.getElementById("map"), {
      zoom: 14,
      center: { lat: -34.5920470627147, lng: -58.391167433160675 },
      mapTypeId: "satellite",
    });
    this.setState({
      map,
    });

    map.addListener("click", function () {
      updateThis.closeBox();
    });
  };

  createLines = () => {
    const colorGenerator = () => {
      var letters = "0123456789ABCDEF";
      var color = "#";
      for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
      }
      return color;
    };

    if (this.state.map && lines().length) {
      var map = this.state.map;
      let bounds = new window.google.maps.LatLngBounds();

      lines().forEach((l) => {
        let line = new window.google.maps.Polyline({
          path: l,
          strokeWeight: 3,
          strokeColor: colorGenerator(),
        });
        l.forEach((ll) => bounds.extend(ll));
        line.setMap(map);
      });

      map.fitBounds(bounds);
    }
  };

  createPoints = () => {
    if (this.state.map && points().length) {
      var map = this.state.map;
      let bounds = new window.google.maps.LatLngBounds();

      points().forEach((p) => {
        new window.google.maps.Marker({
          position: p,
          map,
          strokeWeight: 3,
        });

        bounds.extend(p);
      });

      map.fitBounds(bounds);
    }
  };

  closeBox = () => {
    this.setState({
      boxOpened: false,
      nowmarker: {},
    });
  };

  render() {
    this.createLines();
    this.createPoints();
    return (
      <div className="contetedor__mapa">
        <div className="mapa">
          {this.state.boxOpened && (
            <Infowindow
              nowmarker={this.state.nowmarker}
              information={this.state.information}
            />
          )}
          <div id="map" role="application" />
        </div>
      </div>
    );
  }
}

export default Mapa;

function loadJS(src) {
  let ref = window.document.getElementsByTagName("script")[0];
  let script = window.document.createElement("script");

  script.src = src;
  script.async = true;
  ref.parentNode.insertBefore(script, ref);

  script.onerror = function () {
    document.write("Load error: Google Maps");
  };
}
