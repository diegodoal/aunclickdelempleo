     function saveStudies(){
      $('body').on( "click", ".save-studies", function() {
        var studies = [];
        var counter = 0;

        if($('#level_studies_group input[name="title"]').val() != "" && $(' #level_studies_group input[name="location"]').val() != ""){
           $('#level_studies_group').find('input').each(function(){

               studies[counter] = $(this).val();
               counter++;
           });
         HtmlRedirect = "/orientation/gettools/cv2 ";
         URLroutes = "/orientation/gettools/cv2/AddStudies ";
        }else{
            $('input[name="check_no_studies"]').each(function () {
              if(this.checked){
                  studies[counter] = $(this).val();
                  counter++;
                  HtmlRedirect = "/orientation/gettools/cv2 ";
                  URLroutes = "/orientation/gettools/cv2/AddNoStudies ";
              }
        });
        }
      alert("URL"+URLroutes);
      alert("studies:"+studies);
           //Pasar a la base de datos
                       $.ajax({
                       type: 'post',
                       data: JSON.stringify(studies), //variable que vamos a pasar a la base de datos
                       url: URLroutes, //direccion del routes al controlador
                       contentType: 'application/json'
                   }).done(function(result) {
                       console.log(result); //Mostrar en la consola el resultado que se ha guardado en la base de datos
                       window.location.assign(HtmlRedirect); //Redireccionar
                   });
      });


        $('body').on( "click", ".save-studies-no", function() {
              var studies = [];
              var counter = 0;

              if($('#level_studies_group_no input[name="title"]').val() != "" && $(' #level_studies_group input[name="location"]').val() != ""){
                 $('#level_studies_group_no').find('input').each(function(){

                     studies[counter] = $(this).val();
                     counter++;
                 });
               HtmlRedirect = "/orientation/gettools/cv2 ";
               URLroutes = "/orientation/gettools/cv2/AddStudies ";
              }else{
                  $('input[name="check_no_studies"]').each(function () {
                    if(this.checked){
                        studies[counter] = $(this).val();
                        counter++;
                        HtmlRedirect = "/orientation/gettools/cv2 ";
                        URLroutes = "/orientation/gettools/cv2/AddNoStudies ";
                    }
              });
              }
            alert("URL"+URLroutes);
            alert("studies:"+studies);
                 //Pasar a la base de datos
                             $.ajax({
                             type: 'post',
                             data: JSON.stringify(studies), //variable que vamos a pasar a la base de datos
                             url: URLroutes, //direccion del routes al controlador
                             contentType: 'application/json'
                         }).done(function(result) {
                             console.log(result); //Mostrar en la consola el resultado que se ha guardado en la base de datos
                             window.location.assign(HtmlRedirect); //Redireccionar
                         });
            });
      }