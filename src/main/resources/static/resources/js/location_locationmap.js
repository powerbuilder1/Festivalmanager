const self = {
    markers: [],
    map: null,
    poly: null,
    polygons: [],
    counter: 0,

    getPolygonCenter: function(polygon) {
        var x1, y1, x2, y2 = null;
        polygon.getPath().getArray().forEach((point) => {
            if (x1 == null) {
                x1 = point.lat();
                y1 = point.lng();
                x2 = point.lat();
                y2 = point.lng();
            }
            if (point.lat() < x1) {
                x1 = point.lat();
            }
            if (point.lat() > x2) {
                x2 = point.lat();
            }
            if (point.lng() < y1) {
                y1 = point.lng();
            }
            if (point.lng() > y2) {
                y2 = point.lng();
            }
        });
        return {
            lat: x1 + (x2 - x1) / 2,
            lng: y1 + (y2 - y1) / 2
        };
    },
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