function next(){
var next = "";
     $('body').on( "click", ".next", function() {
     next = document.getElementById('next-step').value;
     if(next != "next"){
         $('#cv2_alert').show();
         window.scrollTo(0,0);
     }else{
     window.location.assign("/orientation/gettools/cv3");
     }
     });
}