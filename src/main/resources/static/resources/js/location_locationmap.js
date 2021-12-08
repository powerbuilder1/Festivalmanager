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
    createPolygon: function(paths, color, name) {
        var poly = new google.maps.Polygon({
            paths: paths,
            strokeColor: color,
            strokeOpacity: 0.8,
            strokeWeight: 2,
            fillColor: color,
            fillOpacity: 0.35,
            editable: true,
            draggable: true,
        });
        // set extra attributes
        poly.id = self.counter++;
        poly.name = (name) ? name : "Polygon[" + poly.id + "]";

        // add marker
        poly.marker = new google.maps.Marker({
            position: this.getPolygonCenter(poly),
            map: self.map,
            draggable: false,
            label: {
                text: poly.name,
                color: "#FFFFFF",
            },
            icon: {
                url: "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAA1JREFUGFdjyHQt+g8ABFsCIF75EPIAAAAASUVORK5CYII="
            }
        });

        // set event listeners
        poly.addListener('rightclick', function(mev) {
            if (mev.vertex != null && this.getPath().getLength() > 3) {
                this.getPath().removeAt(mev.vertex);
            }
        });
        poly.addListener('click', function() {
            selectPolygon(null, poly.id);
        })
        poly.addListener('mouseup', function() {
            this.marker.setPosition(self.getPolygonCenter(this));
        })
        return poly;
    },

    addPolygon: function(paths, color, name) {
        var poly = this.createPolygon(paths, color, name);
        poly.setMap(this.map);
        this.polygons.push(poly);
        addPolygonControl(poly.id);
    },

    findPolygon: function(id) {
        return this.polygons.filter((poly) => {
            return poly.id == id;
        })[0];
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