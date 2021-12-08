const self = {
    markers: [],
    map: null,
    poly: null,
    polygons: [],
    counter: 0,
};

function initMap() {
    console.log("initMap");
    var map = new google.maps.Map(document.getElementById("map"), {
        zoom: 15,
        center: {
            lat: 51.038200,
            lng: 13.763133
        },
        mapTypeId: "satellite",
        disableDefaultUI: true,
    });
    self.map = map;

    google.maps.event.addListener(self.map, "click", (event) => {
        create_polygon(event.latLng);
    });
}