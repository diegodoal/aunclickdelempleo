function deleteExperience(){

    $('body').on( "click", ".delete-experience", function() {
    var idExperience = "";
            $(this).find('input').each(function(){
                idExperience = $(this).val();
            });



//Pasar a la base de datos
        $.ajax({
        type: 'post',
        data: JSON.stringify(idExperience), //variable que vamos a pasar a la base de datos
        url: "/orientation/currentsituation/Delete" , //direccion del routes al controlador
        contentType: 'application/json'
    }).done(function(result) {
        console.log(result); //Mostrar en la consola el resultado que se ha guardado en la base de datos
        window.location.assign("/orientation/currentsituation"); //Redireccionar
    });


   });

}