function addNewExp(){
var counter = 0;
var clonar = 0;
var campos = 0;
    $('body').on( "click", ".add-new", function() {
    alert("Funciona");

    $(this).closest('.main-div-sections .form-horizontal').each(function(){
            $(this).find('input').each(function(){
            clonar = clonar +1;
               if($(this).val() != ""){
                campos++;
               }
               });
            });
            campos=campos+2;
alert("Campos"+ campos);
alert("Clonar"+ clonar);
            if(clonar == campos){
            alert("dentro");
             if($(this).attr('src') == '/assets/images/orientation/ic_trash2.png'){
                  $(this).parent().parent().parent().parent().remove();
                  counter = counter -1;
             }else{ //Adding new section
                  var clone = $(this).parent().parent().parent().parent().parent().clone();
                  clone.find('.form-control').each(function(){
                      $(this).val('');
                  });
                  clone.find('.sombra').each(function(){
                        $(this).val('');
                  });
                  clone.appendTo($(this).parent().parent().parent().parent().parent());
                  $(this).attr('src', '/assets/images/orientation/ic_trash2.png');
                  counter = counter +1;
             }
            }else{
              campos = 0;
              clonar = 0;
              if($(this).attr('src') == '/assets/images/orientation/ic_trash2.png'){
                    $(this).parent().parent().parent().parent().remove();
                    counter = counter -1;
               }

            }
       // alert("Contador" +counter);
        loadRecognition();
        var check = document.getElementById("check_exp_cb");
        if(check != null){
          if(counter == 0){
            check.hidden = false;
          }else{
            check.hidden = true;
          }
        }
    });
}