function addNewBbdd(){
 $('body').on( "click", ".add-new-bbdd", function() {
 var mes_inicio=document.getElementById('mes_inicio').value;
 	var mes_fin=document.getElementById('mes_fin').value;
 	var año_inicio=document.getElementById('año_inicio').value;
 	var año_fin=document.getElementById('año_fin').value;

 	var startDate = mes_inicio + " / " + año_inicio;
 	var endDate = mes_fin + " / " + año_fin;

 		$('#startDate').val(startDate);
 	    $('#endDate').val(endDate);

     var HtmlRedirect = "";
     var checkboxArray = [];
     var studyChecked = 0;

     //Comprobar y guardar los checkbox en un array checkboxArray

     if ($('input[name="study"]').is(':checked')) {
     studyChecked++;
     $("#validate_cb").hide();
         //Checkboxes
          checkboxArray = [];
         var counter = 0;
         $('input[name="study"]').each(function () {
             if(this.checked){
                 checkboxArray[counter] = $(this).val();
                 counter++;
             }
         });
         HtmlRedirect = "/orientation";
     }else{
         HtmlRedirect = "/orientation/currentsituation";
     }

     //Comprobar y guardar los campos de experiencia en un array experienceArray
         var experienceArray = [];
         var counter = 0;
         var URLroutes = "";

     if( $(' #experience_group_new input[name="company"]').val() != "" && $('#experience_group_new input[name="job"]').val() != "" && $('#experience_group_new input[name="startDate"]').val() != "" && $(' #experience_group_new input[name="endDate"]').val() != ""){
         $("#validate_exp_cb").hide();
         $('.main-div-sections #experience_group_new').each(function(){
             var auxExperience = [];
             $('.exp').find('input').each(function(){
                 auxExperience[counter] = $(this).val();
                 counter++;
             });

             auxExperience[counter] = $(this).find('select option:selected').text();
             counter = 0;
             experienceArray.push(auxExperience);
         });
           HtmlRedirect = "/orientation/currentsituation";
           URLroutes = "/orientation/currentsituation";
     }else{
          HtmlRedirect = "/orientation/currentsituation";
          URLroutes = "";
     }
     var result = checkboxArray.concat(JSON.stringify(experienceArray));

     if(studyChecked == 0){
         $('#validate_cb').show();
         window.scrollTo(0,300);
     }
      else if( URLroutes == ""){
             $('#validate_exp_cb').show();
             window.scrollTo(0,1000);
         }else{

     //Pasar a la base de datos
             $.ajax({
             type: 'post',
             data: JSON.stringify(result), //variable que vamos a pasar a la base de datos
             url: URLroutes, //direccion del routes al controlador
             contentType: 'application/json'
         }).done(function(result) {
             console.log(result); //Mostrar en la consola el resultado que se ha guardado en la base de datos
             window.location.assign(HtmlRedirect); //Redireccionar
         });
         }
 });

}