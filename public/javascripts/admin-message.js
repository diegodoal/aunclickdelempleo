function sendDeleteMsgRequest(id, deletedBy){
	

	$.ajax({
            type: 'post',
            data: JSON.stringify(result),
            url: "/admin/options/newadmin",
            contentType: 'application/json',
            success: function(){
                $('#newUserModal').modal('hide');
                alert("Usuario añadido correctamente");
                window.location.replace('/admin/options');
            },
            error: function(){
                alert("No se ha podido crear el usuario. Por favor, inténtelo de nuevo");
            }
            });

}

function sendReadMsgRequest(id){
	$.ajax({
        type: 'post',
        data: id,
        url: "/admin/messages/read",
        contentType: 'text/plain',
        success: function(){
            alert("Marcado como leido");
            
            //window.location.replace('/admin/options');
        },
        error: function(){
            alert("No se ha podido marcar como leido");
        }
});
}