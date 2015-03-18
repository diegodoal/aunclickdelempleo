/*$( document ).ready(function() {
    console.log( "ready!" );

    $( "#check_studies" ).click(function() {
        alert( "Handler for .click() called." );
    });
});

/* This script is used to select at most 5 options in "Intereses Profesionales:" */
var num_options = 5;
var numcheckbox = Array ();
numcheckbox["profession"] = 0;
numcheckbox["personal"] = 0;

function enable_asdfadsfcb(t) {
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