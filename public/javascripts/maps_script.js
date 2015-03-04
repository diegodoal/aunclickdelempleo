

var directionsDisplay;
var directionsService = new google.maps.DirectionsService();
var map;

function initialize(){
  directionsDisplay = new google.maps.DirectionsRenderer();
  //MOSTRAR MAPA CON PROPIEDADES
  var mapProp = {
  panControl: true,
  zoomControl: true,
  mapTypeControl: true,
  scaleControl: true,
  streetViewControl: true,
  overviewMapControl: true,
   zoom: 13,
    scrollwheel: false,
   center: new google.maps.LatLng(40.4266552,-3.6794646),
   mapTypeId: google.maps.MapTypeId.ROADMAP
  };
  map = new google.maps.Map(document.getElementById("div_mapa"),mapProp);
  directionsDisplay.setMap(map);
 }

  
 function calcRoute(){
  var selectMode = "ninguno";
  var porNombre=document.getElementsByName("selectMode");
  for(var i=0;i<porNombre.length;i++){
     if(porNombre[i].checked)
      selectMode=porNombre[i].value;
  }
  var start = document.getElementById('start').value;
  var end = document.getElementById('end').value;
  /*var selectMode = document.getElementById('selectMode').value;*/
  //AÑADIMOS RUTA
  var request = {  
  origin: start,
  destination: end,
  travelMode: google.maps.TravelMode[selectMode]
  };

  directionsService.route(request, function(result, status) {
  if (status == google.maps.DirectionsStatus.OK) {
    directionsDisplay.setDirections(result);
  }
  directionsDisplay.setPanel(document.getElementById("ruta"));
});
}

 function concatenar(){
    var calle1=document.getElementById('calle1').value;
    var localidad1=document.getElementById('localidad1').value;
    var provincia1=document.getElementById('provincia1').value;
    var pais1=document.getElementById('pais1').value;

    var calle2=document.getElementById('calle2').value;
    var localidad2=document.getElementById('localidad2').value;
    var provincia2=document.getElementById('provincia2').value;
    var pais2=document.getElementById('pais2').value;

 

    var start = calle1 + " , " + localidad1 + " , " + provincia1 + " , " +pais1;
    var end = calle2 + " , " + localidad2 + " , " + provincia2 + " , " +pais2;

    $('#start').val(start);
    $('#end').val(end);
    
    calcRoute();
   // obtenerRuta(this.desde.value, this.hasta.value); return false;

}
google.maps.event.addDomListener(window, 'load', initialize);
