function uploadPhoto(){
var input = document.getElementById('input');
input.addEventListener('change', handleFiles);

function handleFiles(e) {
    var ctx = document.getElementById('canvas').getContext('2d');
    var img = new Image;
    img.src = URL.createObjectURL(e.target.files[0]);
    img.onload = function() {
        ctx.drawImage(img, 0,0, 200, 200);
    }
}
}

function takePhotoModal(){
var streaming = false,
  video        = document.querySelector('#video'),
  cover        = document.querySelector('#cover'),
  canvas       = document.querySelector('#canvas2'),
  canvasmain   = document.querySelector('#canvas'),
  photo        = document.querySelector('#photo'),
  startbutton  = document.querySelector('#startbutton'),
  acceptbutton = document.querySelector('#acceptbutton'),
  width = 200,
  height = 0;

  navigator.getMedia = ( navigator.getUserMedia ||
                     navigator.webkitGetUserMedia ||
                     navigator.mozGetUserMedia ||
                     navigator.msGetUserMedia);

  navigator.getMedia(
  {
  video: true,
  audio: false
  },
  function(stream) {
  if (navigator.mozGetUserMedia) {
    video.mozSrcObject = stream;
  } else {
    var vendorURL = window.URL || window.webkitURL;
    video.src = vendorURL ? vendorURL.createObjectURL(stream) : stream;
  }
  video.play();
  },
  function(err) {
  console.log("An error occured! " + err);
  }
  );

  video.addEventListener('canplay', function(ev){
  if (!streaming) {
  height = video.videoHeight / (video.videoWidth/width);
  video.setAttribute('width', width);
  video.setAttribute('height', height);
  canvas.setAttribute('width', width);
  canvas.setAttribute('height', height);
  streaming = true;
  }
  }, false);


startbutton.addEventListener('click', function(ev){
canvas.width = width;
canvas.height = height;
canvas.getContext('2d').drawImage(video, 0, 0, width, height);
var data = canvas.toDataURL('image/png');
photo.setAttribute('src', data);
startbutton.disabled = true;
ev.preventDefault();
}, false);

acceptbutton.addEventListener('click', function(ev){
canvasmain.width = width;
canvasmain.height = height;
canvasmain.getContext('2d').drawImage(video, 0, 0, width, height);
var data = canvas.toDataURL('image/png');
photo.setAttribute('src', data);
ev.preventDefault();
}, false);
}


function sendPhoto(){
}