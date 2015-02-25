var CANVAS_WIDTH = 200;
var CANVAS_HEIGHT = 200;

function drawFromInput(){
    var input = document.getElementById('upload_photo');
    input.addEventListener('change', handleFiles);
    var canvas = document.getElementById('mainCanvas');
    var ctx = canvas.getContext('2d');

    function handleFiles(e) {
        ctx.clearRect ( 0 , 0 , canvas.width, canvas.height);
        var img = new Image;
        img.src = URL.createObjectURL(e.target.files[0]);
        img.onload = function() {
            ctx.drawImage(img, 0,0, CANVAS_WIDTH, CANVAS_HEIGHT);
        }
    }
}

function takePhoto(){
  var streaming = false,
      video        = document.querySelector('#video'),
      canvasModal  = document.querySelector('#canvasModal'),
      canvasMain   = document.querySelector('#mainCanvas'),
      takePhotoButton  = document.querySelector('#takePhotoButton'),
      acceptPhotoButton = document.querySelector('#acceptPhotoButton'),
      width = CANVAS_WIDTH,
      height = 0;

  navigator.getMedia = ( navigator.getUserMedia ||
                     navigator.webkitGetUserMedia ||
                     navigator.mozGetUserMedia ||
                     navigator.msGetUserMedia);

  navigator.getMedia(
    {video: true, audio: false},
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
      CANVAS_HEIGHT = video.videoHeight / (video.videoWidth/CANVAS_WIDTH);
      video.setAttribute('width', CANVAS_WIDTH);
      video.setAttribute('height', CANVAS_HEIGHT);
      streaming = true;
      }
      }, false
  );

  takePhotoButton.addEventListener('click', function(ev){
      canvasModal.width = CANVAS_WIDTH;
      canvasModal.height = CANVAS_HEIGHT;
      canvasModal.getContext('2d').drawImage(video, 0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
      ev.preventDefault();
      }, false
  );


}