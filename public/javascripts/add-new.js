function addNew(){
    $('body').on( "click", ".add-new", function() {
        var notEmptyFields = false;
        //Removing new section
        if($(this).attr('src') == '/assets/images/orientation/ic_trash2.png'){
            $(this).parent().parent().parent().parent().parent().remove();
        }else{ //Adding new section
            $('.form-control').each(function(){
                if($(this).attr('type') === 'text'){
                    if($(this).val() == ''){
                        notEmptyFields = true;
                    }
                }
            })

            if(notEmptyFields == false){
                var clone = $(this).parent().parent().parent().parent().parent().clone();
                clone.find('.form-control').each(function(){
                    $(this).val('');
                });
                clone.appendTo('.main-div-sections');
                $(this).attr('src', '/assets/images/orientation/ic_trash2.png');
            }

            loadRecognition();
        }
    });
}