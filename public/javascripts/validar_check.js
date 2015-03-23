function ValidarCheck(){

$('#finalizar').click(function() {
	var next_step = null;
    //Se verifica si alguno de los checks esta seleccionado
    if ($('input[name="studies"]').is(':checked')) {
    	next_step = "si";
    	$('#next_step').val(next_step);
        
    }
    else {
    	next_step = "no";
    	$('#next_step').val(next_step);
    }
});
}