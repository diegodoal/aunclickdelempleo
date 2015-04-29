function addIntesetGenerate(){
      $('body').on( "click", ".add-new-interest", function() {
      var checkboxArray = [];
          var counter = 0;
          $('.checkbox-group input[type=checkbox]').each(function () {
              if(this.checked){
                  checkboxArray[counter] = $(this).val();
                  counter++;
              }
          });

          $.ajax({
              type: 'post',
              data: JSON.stringify(checkboxArray),
              url: "/orientation/interestidentification",
              contentType: 'application/json'
          }).done(function(o) {
              window.location.assign("/orientation/gettools/cv2");
          });
      });
  }