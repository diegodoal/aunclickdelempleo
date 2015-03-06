function autoChangeIcon(){
    $('.panel-heading a').click(function() {
        if($(this).children('img').attr('src') == '/assets/images/orientation/ic_leermenos.png'){
            $(this).children('img').attr('src', '/assets/images/orientation/ic_leermas.png');
        }else{
            $(this).children('img').attr('src', '/assets/images/orientation/ic_leermenos.png');
        }
    });
}
