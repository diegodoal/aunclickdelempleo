function loadRecognition(){
// Test browser support
      window.SpeechRecognition = window.SpeechRecognition       ||
                                 window.webkitSpeechRecognition ||
                                 null;

      //If not supported, hide button
      if(window.SpeechRecognition === null) {
        $('.input-mic-container img').attr('class', 'hidden');
      }else{
        var recognizer = new window.SpeechRecognition();
        var input_name;

        recognizer.continuous = false;
        recognizer.lang = 'es-ES';
        recognizer.interimResults = false; //Constantly refreshes text while recognition is active

        $('.input-mic-container img').click(function() {
          //alert($(this).attr('id'));
          input_name = $(this).parent().children('input');
          //alert(input_name.val());
          try{
            recognizer.start();
          }catch(ex){
            alert("Error al iniciar el reconocimiento de voz");
          }
        });

        recognizer.onresult = function(event){
          input_name.value = '';
          for(var i = event.resultIndex; i < event.results.length; i++) {
            if(event.results[i].isFinal) {
              input_name.val(event.results[i][0].transcript);
            }
          }
        };

        recognizer.onerror = function(event) {
          alert("Error en el reconocimiento de voz. Inténtelo de nuevo.);
        };
      }
}