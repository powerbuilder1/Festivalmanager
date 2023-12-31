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
    randomColor: function() {
        var letters = '0123456789ABCDEF';
        var color = '#';
        for (var i = 0; i < 6; i++) {
            color += letters[Math.floor(Math.random() * 16)];
        }
        return color;
    },
    load: function(data) {
        data = JSON.parse(data);
        self.map.setCenter(data.map.center);
        self.map.setZoom(data.map.zoom);
        data.polygons.forEach(function(poly) {
            self.addPolygon(poly.paths, poly.color, poly.name);
        });
    },
    save: function() {
        var data = {
            map: {
                center: {
                    lat: self.map.getCenter().lat(),
                    lng: self.map.getCenter().lng()
                },
                zoom: self.map.getZoom()
            },
            polygons: [],
        };
        this.polygons.forEach(function(poly) {
            data.polygons.push({
                color: poly.fillColor,
                name: poly.name,
                paths: poly.getPath().getArray()
            });
        });
        return JSON.stringify(data);
    },
    toStatic: function() {
        var baseUrl = "https://maps.googleapis.com/maps/api/staticmap";
        baseUrl += "?center=" + this.map.getCenter().lat() + "," + this.map.getCenter().lng();
        baseUrl += "&zoom=" + this.map.getZoom();
        baseUrl += "&size=640x400";
        baseUrl += "&maptype=satellite";

        // add polygon data
        self.polygons.forEach(function(poly) {
            var path = poly.getPath().getArray();
            baseUrl += "&path=color:" + "0x1E" + poly.fillColor.substring(1, 7) + "|weight:1|fillcolor:" + "0x1E" + poly.fillColor.substring(1, 7);
            path.forEach(function(point) {
                baseUrl += "|" + point.lat() + "," + point.lng();
            });
        });

        // add polygon markers
        self.polygons.forEach(function(poly) {
            var url = "https://api.imgbun.com/png?key=a81cb2b882806de2fd480cb5a75b1c7a&text=" + poly.name + "&color=FFFFFF&size=12";
            var xmlHttp = new XMLHttpRequest();
            xmlHttp.open("GET", url, false);
            xmlHttp.send(null);
            var link = JSON.parse(xmlHttp.responseText).direct_link;

            // empty image: https://i.ibb.co/9vDKM7b/icon.png
            baseUrl += "&markers=anchor:center|icon:" + link;
            baseUrl += "|" + poly.marker.position.lat() + "," + poly.marker.position.lng();
        });

        baseUrl += "&key=AIzaSyAuAHl9sypEnysXjsS7SbNJ5e7x44kAFmY";

        return baseUrl;
    }
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
    // try loading data from location
    self.load(document.querySelector("#data").innerHTML);
}

function create_polygon(pos) {
    var paths = [];
    paths.push({
        lat: pos.lat() - 0.001,
        lng: pos.lng()
    });
    paths.push({
        lat: pos.lat(),
        lng: pos.lng() + 0.001
    });
    paths.push({
        lat: pos.lat() + 0.001,
        lng: pos.lng()
    });
    paths.push({
        lat: pos.lat(),
        lng: pos.lng() - 0.001
    });

    self.addPolygon(paths, self.randomColor(), null);
}

function addPolygonControl(index) {
    var control = document.querySelector("#control");
    var el = document.createElement("li");
    el.innerHTML = self.findPolygon(index).name;
    el.dataset.id = index;

    el.onclick = () => {
        selectPolygon(el, null);
    }

    control.appendChild(el);
}

function selectPolygon(el, id) {
    if (el == undefined || el == null) {
        el = document.querySelector("[data-id='" + id + "']");
    } else if (id == undefined || id == null) {
        id = el.dataset.id;
    }

    // deselect others
    if (document.querySelectorAll(".selected").length > 0) {
        document.querySelectorAll(".selected").forEach((element) => {
            if (element == undefined || self.findPolygon(element.dataset.id) == undefined) return;
            // reset stroke color
            var fillColor = self.findPolygon(element.dataset.id).fillColor;
            self.findPolygon(element.dataset.id).setOptions({
                strokeColor: fillColor
            });
            // remove class
            element.classList.remove("selected");
        });
    }

    // self select -> underline text & set stroke color to red
    el.classList.add("selected");
    self.findPolygon(id).setOptions({
        strokeColor: "#FF0000",
    });

    // load polygon data
    var poly = self.findPolygon(id);
    document.querySelector("#color_input").value = poly.fillColor;
    document.querySelector("#name_input").value = poly.name;
}

function changePolygonColor() {
    var poly = self.findPolygon(document.querySelector(".selected").dataset.id);
    poly.setOptions({
        fillColor: document.querySelector("#color_input").value,
        strokeColor: document.querySelector("#color_input").value
    });
}

function changePolygonName() {
    var el = document.querySelector(".selected");
    var poly = self.findPolygon(el.dataset.id);
    poly.name = document.querySelector("#name_input").value;
    var label = poly.marker.getLabel()
    label.text = poly.name;
    poly.marker.setLabel(label);
    el.innerHTML = poly.name;

}

function removePolygon() {
    var el = document.querySelector(".selected");
    var poly = self.findPolygon(el.dataset.id);
    poly.marker.setMap(null);
    poly.setMap(null);
    self.polygons = self.polygons.filter(poly => poly.id != el.dataset.id);
    document.querySelector("#control").removeChild(el);
}

function save() {
    document.querySelector("#form_data").value = self.save();
    document.querySelector("#form_static").value = self.toStatic();
    document.querySelector("#form").submit();
}