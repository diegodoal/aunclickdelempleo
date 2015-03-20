/*$( document ).ready(function() {
    console.log( "ready!" );

    $( "#check_studies" ).click(function() {
        alert( "Handler for .click() called." );
    });
});

/* This script is used to select at most 5 options in "Intereses Profesionales:" */
function hide_studies(t) {
    checkboxes = document.getElementsByName("study");
    if (t.checked) {
        for (i=0; i<checkboxes.length; i++) {
            if (checkboxes[i].checked == true) {
                // disable
            	checkboxes[i].checked = false;
            	checkboxes[i].disabled = true;
            } else if (checkboxes[i].checked == false) {
                // disable
                checkboxes[i].disabled = true;
            }
        }
    } else {
        for (i=0; i<checkboxes.length; i++) {
            if (checkboxes[i].checked == false) {
                // enable
                checkboxes[i].disabled = false;
            }
        }
    }
}