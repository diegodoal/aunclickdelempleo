function readMsg(modalType, id, fromUser, subject, message, date, toUser){
  sendReadMsgRequest(id);
  $('#readMsgModal').modal('show');
  $('#readMsgModal .modal-title').html('<b>Mensaje de: '+fromUser+'</b>  ['+date+']<br><b>Asunto:</b> '+subject);
  $('#readMsgModal .modal-body').html(message);

  $('#readMsgModal #responseMsg').click(function(){
    alert("Response msg...");
  });

  $('#readMsgModal #deleteMsg').click(function(){
  	sendDeleteMsgRequest(id, toUser);
  });
}

function sendDeleteMsgRequest(id, deletedBy){
	var result = [];
	result[0] = id;
	result[1] = deletedBy;
	$.ajax({
            type: 'post',
            data: JSON.stringify(result),
            url: "/admin/messages/delete",
            contentType: 'application/json',
            success: function(){
                alert("Mensaje eliminado");
                $('#readMsgModal').modal('hide');
            },
            error: function(){
                alert("No se ha podido eliminar el mensaje. Por favor, inténtelo de nuevo");
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
        },
        error: function(){
            alert("No se ha podido marcar como leido");
        }
});
}