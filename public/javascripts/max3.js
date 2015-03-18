/*$( document ).ready(function() {
    console.log( "ready!" );

    $( "#check_studies" ).click(function() {
        alert( "Handler for .click() called." );
    });
});

/* This script is used to select at most 3 options in "Intereses Profesionales:" */

/*
var num_options = 3;
var numcheckbox = Array ();
numcheckbox["interest"] = 0;
numcheckbox["personal"] = 0;

function _enable_c1b1(t) {
    checkboxes = document.getElementsByName(t.name);
    if (t.checked) {
        if (numcheckbox[t.name] >= num_options-1) {
            numcheckbox[t.name]++;
            for (i=0; i<checkboxes.length; i++) {
                if (checkboxes[i].checked == true) {
                    // enable
                    checkboxes[i].disabled = false;
                } else if (checkboxes[i].checked == false) {
                    // disable
                    checkboxes[i].disabled = true;
                }
            }
        } else
            numcheckbox[t.name]++;
    } else {
        numcheckbox[t.name]--;
        for (i=0; i<checkboxes.length; i++) {
            if (checkboxes[i].checked == false) {
                // enable
                checkboxes[i].disabled = false;
            }
        }
    }
}
*/

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
