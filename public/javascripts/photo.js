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