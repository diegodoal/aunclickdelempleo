function limitCheckboxes(max){
    var maxSelected = max;
    var currentSelected = 0;
    $('.checkbox-group input:checkbox').click(function(){
        if($(this).prop('checked') == true){
            currentSelected++;
                if(currentSelected == maxSelected){
                            $('.checkbox-group input:checkbox').each(function(){
                                if($(this).prop('checked') == false){
                                    $(this).prop('disabled', true);
                                }
                            });
                        }
        }else if(currentSelected == maxSelected){
            currentSelected--;
            $('.checkbox-group input:checkbox').each(function(){
                                if($(this).prop('disabled') == true){
                                    $(this).prop('disabled', false);
                                }
                            });
        }else{
            currentSelected--;
        }
    });
}
