var CANVAS_WIDTH = 137;
var CANVAS_HEIGHT = 162;
var ACCEPTED_WEB_CAM_ACCESS = false;
var ADDED_PHOTO = false;
var MADE_PHOTO = false;

function drawFromInput(){
    var input = document.getElementById('upload_photo');
    input.addEventListener('change', handleFiles);
    var canvas = document.getElementById('mainCanvas');
    var ctx = canvas.getContext('2d');

    function handleFiles(e) {
        ctx.clearRect (0, 0, 137, 162);
        var img = new Image;
        img.src = URL.createObjectURL(e.target.files[0]);
        img.onload = function() {
            ctx.drawImage(img, 0, 0, 137, 162);
            ADDED_PHOTO = true;
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
      finishButton = document.querySelector('#finish_btn'),
      width = 137,
      height = 162;

      canvasModal.getContext('2d').clearRect(0, 0, 137, 162);

  if(ACCEPTED_WEB_CAM_ACCESS == false){
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
            ACCEPTED_WEB_CAM_ACCESS = true;
        },
        function(err) {
            console.log("An error occured! " + err);
        }
      );
  }

  video.addEventListener('canplay', function(ev){
      if (!streaming) {
      CANVAS_HEIGHT = video.videoHeight / (video.videoWidth/CANVAS_WIDTH);
      video.setAttribute('width', 200);
      video.setAttribute('height', 200);
      streaming = true;
      }
      }, false
  );

  takePhotoButton.addEventListener('click', function(ev){
      canvasModal.width = 137;
      canvasModal.height = 162;
      canvasModal.getContext('2d').drawImage(video, 0, 0, 137, 162);
      MADE_PHOTO = true;
      ev.preventDefault();
      }, false
  );

  acceptPhotoButton.addEventListener('click', function(ev){
        if(MADE_PHOTO == true){
            canvasMain.width = 137;
            canvasMain.height = 162;
            canvasMain.getContext('2d').drawImage(canvasModal, 0, 0, 137, 162);
            ADDED_PHOTO = true;
        }
        ev.preventDefault();
        }, false
    );


}

function finishPhoto(){
    var canvasMain = document.querySelector('#mainCanvas');
    var dataurl = canvasMain.toDataURL('image/png');

    if(ADDED_PHOTO == false){
        window.location.assign("/orientation");
    }else{
        $('#photoUploadingModal').modal('show');

        $.ajax({
              type: "POST",
              url: "/orientation/photo",
              data: {
                 imgBase64: dataurl
              }
            }).done(function(o) {
              document.getElementById("photoUploadingText").innerHTML = ("Foto subida con éxito");
              //$('#photoUploadingModal').modal('hide');
              window.location.assign("/orientation")
            });
    }
}

function downloadPhoto(){
    if(ADDED_PHOTO == true){
        var link = document.getElementById('mainCanvas').toDataURL();
        link.download = 'photo.png';
        window.open(link);
    }
}

function dragPhoto() {
    var canvas = document.querySelector('#mainCanvas'),
        container = document.getElementById("drag-drop-input"),
        context = canvas.getContext("2d"),
        img = document.createElement("img"),
        clearCanvas = function () {
            context.clearRect(0, 0, canvas.width, canvas.height);
        };
        
    
    // Image for loading    
    img.addEventListener("load", function () {
        clearCanvas();
        context.drawImage(img, 0, 0, 137, 162);
    }, false);


    // To enable drag and drop
    container.addEventListener("dragover", function (evt) {
        evt.preventDefault();
    }, false);

    // Handle dropped image file - only Firefox and Google Chrome
    container.addEventListener("drop", function (evt) {
        var files = evt.dataTransfer.files;
        if (files.length > 0) {
            var file = files[0];
            if (typeof FileReader !== "undefined" && file.type.indexOf("image") != -1) {
                var reader = new FileReader();
                // Note: addEventListener doesn't work in Google Chrome for this event
                reader.onload = function (evt) {
                    img.src = evt.target.result;
                };
                reader.readAsDataURL(file);
            }
        }
        evt.preventDefault();
    }, false);   
}

function cleanCanvas(){
        var canvas = document.getElementById('mainCanvas');
        var ctx = canvas.getContext('2d');
        var image = new Image();
        image.src = '/assets/images/orientation/photo/ic_profile.png';
        ctx.drawImage(image, 0, 0, 137, 162);
        ADDED_PHOTO = false;
    }