function readMsg(modalType, id, fromUser, subject, message, date, toUser){
  sendReadMsgRequest(id);
  $('#readMsgModal').modal('show');
  $('#readMsgModal .modal-title').html('<b>Mensaje de: '+fromUser+'</b>  ['+date+']<br><b>Asunto:</b> '+subject);
  $('#readMsgModal .modal-body').html(message);

  if(modalType == 'sent'){
  	$('#readMsgModal #responseMsg').hide();
  }

  if(modalType == 'inboxDeleted' || modalType == 'sentDeleted'){
  	$('#readMsgModal #deleteMsg').hide();
  	$('#readMsgModal #responseMsg').hide();
  }

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
                alert("Mensaje archivado");
                $('#readMsgModal').modal('hide');
            },
            error: function(){
                alert("No se ha podido archivar el mensaje. Por favor, inténtelo de nuevo");
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

function sendMsg(){
  var toUser = substringEmail($('#myTypeahead').val());
  var subject = $('#newMsg_subject').val();
  var message = $('#newMsg_content').val();

  if(toUser.trim() == "" || subject.trim() == "" || message.trim() == ""){
    $('#newMsgAlert').show();
  }

  var result = [];
  result[0] = toUser;
  result[1] = subject;
  result[2] = message;
  $.ajax({
            type: 'post',
            data: JSON.stringify(result),
            url: "/admin/messages/send",
            contentType: 'application/json',
            success: function(){
                alert("Mensaje enviado");
                $('#newMsgModal').modal('hide');
            },
            error: function(){
                alert("No se ha podido enviar el mensaje. Por favor, inténtelo de nuevo");
            }
            });

}

function substringEmail(nameWithEmail){
  return(nameWithEmail.substring(nameWithEmail.indexOf("(")+1, nameWithEmail.length-1));
}