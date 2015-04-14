function disableOtherChecks(){

    $('.orientation-points input[name="study"]').each(function(){
    if($(this).val()=="No tengo estudios"){
    if($(this).prop('checked')){
        $('.orientation-points input[name="study"]').each(function(){
            if($(this).prop('checked') == false){
                $(this).prop('disabled', true);
            }
        });
    }
    }

     });
}
