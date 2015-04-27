     function addNewBbddExp(){
      $('body').on( "click", ".add-new-bbdd", function() {
      var mes_inicio=document.getElementById('mes_inicio').value;
      	var mes_fin=document.getElementById('mes_fin').value;
      	var año_inicio=document.getElementById('año_inicio').value;
      	var año_fin=document.getElementById('año_fin').value;

      	var startDate = mes_inicio + " / " + año_inicio;
      	var endDate = mes_fin + " / " + año_fin;

      		$('#startDate').val(startDate);
      	    $('#endDate').val(endDate);

      var experienceArray = [];
      var counter = 0;
      var URLroutes = "";
      var auxExperience = [];
      var HtmlRedirect = "";

     if( $(' #experience_group_new input[name="company"]').val() != "" && $('#experience_group_new input[name="job"]').val() != "" && $('#experience_group_new input[name="startDate"]').val() != "" && $(' #experience_group_new input[name="endDate"]').val() != ""){
         $("#validate_exp_cb").hide();
         $('.main-div-sections #experience_group_new').each(function(){
             $('.exp').find('input').each(function(){
                 auxExperience[counter] = $(this).val();
                 counter++;
             });
         });
           HtmlRedirect = "/orientation/gettools/cv2 ";
           URLroutes = "/orientation/gettools/cv2/Add ";
     }else{
          HtmlRedirect = "/orientation/gettools/cv2 ";
          URLroutes = "";
     }
     //Pasar a la base de datos
             $.ajax({
             type: 'post',
             data: JSON.stringify(auxExperience), //variable que vamos a pasar a la base de datos
             url: URLroutes, //direccion del routes al controlador
             contentType: 'application/json'
         }).done(function(result) {
             console.log(result); //Mostrar en la consola el resultado que se ha guardado en la base de datos
             window.location.assign(HtmlRedirect); //Redireccionar
         });

 });

}