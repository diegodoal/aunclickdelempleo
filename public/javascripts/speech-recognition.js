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
          input_name = $(this).parent().children('input');
          try{
            recognizer.start();
            $(this).parent().children('img').attr('src', '/assets/images/speech/dynamic-mic.gif');
          }catch(ex){
            $(input_name).parent().children('img').attr('src', '/assets/images/speech/static-mic.png');
            alert("Error al iniciar el reconocimiento de voz");
          }
        });

        recognizer.onend = function(){
            $(input_name).parent().children('img').attr('src', '/assets/images/speech/static-mic.png');
        }
        recognizer.onresult = function(event){
          $(input_name).parent().children('img').attr('src', '/assets/images/speech/static-mic.png');
          input_name.value = '';
          for(var i = event.resultIndex; i < event.results.length; i++) {
            if(event.results[i].isFinal) {
              input_name.val(event.results[i][0].transcript.capitalizeFirstLetter());
            }
          }
        };

        recognizer.onerror = function(event) {
          $(input_name).parent().children('img').attr('src', '/assets/images/speech/static-mic.png');
          alert("Error en el reconocimiento de voz. Inténtelo de nuevo.");
        };
      }
}

//Capitalize first letter of a String
String.prototype.capitalizeFirstLetter = function() {
    return this.charAt(0).toUpperCase() + this.slice(1);
}