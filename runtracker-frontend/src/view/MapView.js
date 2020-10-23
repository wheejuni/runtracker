/*global kakao*/
import React from 'react';
import './map.css';

class MapView extends React.Component {
    
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        const script = document.createElement('script');

        script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?appkey=d165f64ec65c5acdd68df8798bc3b3e9&autoload=false'
        script.async = true;

        script.onload = () => {
            kakao.maps.load(() => {
              let container = document.getElementById("map-element");
              let options = {
                center: new kakao.maps.LatLng(37.506502, 127.053617),
                level: 7
              };
      
              const map = new window.kakao.maps.Map(container, options);
           
            });
        }

        document.head.appendChild(script);
    }

    render() {
        return(
            <div className="map-view" id="map-element">
            </div>
        )
    }

}

export default MapView;
