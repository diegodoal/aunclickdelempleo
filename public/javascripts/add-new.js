function addNew(){
    $('body').on( "click", ".add-new", function() {
        //Removing new section
        if($(this).attr('src') == '/assets/images/orientation/ic_trash2.png'){
            $(this).parent().parent().parent().parent().remove();
        }else{ //Adding new section
            var clone = $(this).parent().parent().parent().parent().parent().clone();
            clone.find('.form-control').each(function(){
                $(this).val('');
            });
            clone.appendTo($(this).parent().parent().parent().parent().parent());
            $(this).attr('src', '/assets/images/orientation/ic_trash2.png');
        }
        loadRecognition();
    });
}