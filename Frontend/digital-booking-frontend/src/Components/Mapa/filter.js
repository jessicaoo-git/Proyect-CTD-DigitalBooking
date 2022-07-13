import React, { Component } from "react";
import escapeRegExp from "escape-string-regexp";
import currentLocations from "./locations.json";

class Filter extends Component {
  constructor(props) {
    super(props);
    this.state = {
      query: "",
      locationsFiltered: currentLocations,
      markersFiltered: [],
      nowmarker: {},
      listOpened: true
    };
  }

  componentDidMount() {
    this.setState({
      markersFiltered: this.props.markers
    });
  }

  handleChangedQuery = query => {
    this.setState({
      query,
      listOpened: true
    });

    if (query === "") {
      this.setState({
        listOpened: false
      });
    }
    this.updateLocations(query);
  };

  updateList = () => {
    this.setState(prevState => ({
      listOpened: !prevState.listOpened
    }));
  };

  updateLocations = query => {
    let updateThis = this;
    let fLocations;
    let fMarkers;

    if (query) {
      const match = new RegExp(escapeRegExp(query), "i");

      fLocations = this.props.list.filter(location =>
        match.test(location.type)
      );

      fMarkers = this.props.markers.filter(marker => match.test(marker.type));

      this.setState({
        locationsFiltered: fLocations,
        markersFiltered: fMarkers
      });
    } else {
      this.setState({
        locationsFiltered: this.props.list,
        markersFiltered: this.props.markers
      });
    }

    this.props.markers.map(marker => marker.setVisible(false));
    setTimeout(function() {
      updateThis.props.markers.map(marker => updateThis.markerVisible(marker));
    }, 1);
  };

  markerVisible = marker => {
    this.state.markersFiltered.map(
      markerFiltered =>
        markerFiltered.id === marker.id && marker.setVisible(true)
    );
  };

  markerClicked = location => {
    let updateThis = this;

    this.removeMarker();
    this.addMarker(location);
    setTimeout(function() {
      updateThis.removeMarker();
    }, 1250);

    this.getMarkerNow(location);

    setTimeout(function() {
      updateThis.props.openBox(updateThis.state.nowmarker);
      updateThis.props.openBox(location);
    }, 1);
  };

  removeMarker = () => {
    this.state.markersFiltered.map(markerFiltered =>
      markerFiltered.setAnimation(null)
    );
  };

  addMarker = location => {
    this.state.markersFiltered.map(
      markerFiltered =>
        markerFiltered.id === location.key &&
        markerFiltered.setAnimation(window.google.maps.Animation.BOUNCE)
    );
  };

  getMarkerNow = location => {
    this.state.markersFiltered.map(
      markerFiltered =>
        markerFiltered.id === location.key &&
        this.setState({
          nowmarker: markerFiltered
        })
    );
  };

  render() {
    const { query, locationsFiltered, listOpened } = this.state;

    return (
      <section className="list-box">
        <form className="list-form" onSubmit={event => event.preventDefault()}>
          <button className="list-btn" onClick={() => this.updateList()}>
            List
          </button>

          <input
            className="list-input"
            aria-labelledby="filter"
            type="text"
            placeholder="Search for locations..."
            value={query}
            onChange={event => this.handleChangedQuery(event.target.value)}
          />
        </form>

        {listOpened && (
          <ul className="locations-list">
            {locationsFiltered.map(location => (
              <li
                tabIndex={0}
                role="button"
                className="location-item"
                key={location.key}
                onClick={() => this.markerClicked(location)}
                onKeyPress={() => this.markerClicked(location)}
              >
                {location.name}
              </li>
            ))}
          </ul>
        )}
      </section>
    );
  }
}

export default Filter;
