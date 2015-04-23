

function unir(){
	$(".focuss").focusin(function() {
		$("#mydiv").css("border","0.1em solid #66afe9");
});
		$(".focuss").focusout(function() {
			$("#mydiv").css("border","0.1em solid #ccc ");
			$("#mydiv").css("outline","0 ");
			$("#mydiv").css("box-shadow:"," inset 0 1px 1px rgba(0,0,0,.075), 0 0 8px rgba(102, 175, 233, .6); ");

			
});


	$('body').on( "click", ".concatenar", function() {
	var mes_inicio=document.getElementById('mes_inicio').value;
	var mes_fin=document.getElementById('mes_fin').value;
	var año_inicio=document.getElementById('año_inicio').value;
	var año_fin=document.getElementById('año_fin').value;

	var startDate = mes_inicio + " / " + año_inicio;
	var endDate = mes_fin + " / " + año_fin;

		$('#startDate').val(startDate);
	    $('#endDate').val(endDate);

	    sendForm();
	});

}
