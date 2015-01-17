/* This script is used to select at most 3 options in "Intereses Profesionales:" */
var num_options = 3;
var numcheckbox = Array ();
numcheckbox["profession"] = 0;
function enable_cb(t) {
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

function hide_job_experience(value)
{
    if(value == true)
    {
        // disable
        document.getElementById("job_experience").hidden = true;
    }else {
        // enable
        document.getElementById("job_experience").hidden = false;
    }
}

function zoom_font(action) {
	var obj = document.getElementsByClassName('size_font');
	var max = 200	// max size of font size
	var min = 70	// min size of font size
	for(i = 0; i < obj.length; i++) {
		if (obj[i].style.fontSize == "") {
			obj[i].style.fontSize = "100%";
		}
		current = parseInt(obj[i].style.fontSize); // current value of font size 
		inc = 10; 

		if(action == "reset") {
		obj[i].style.fontSize = "100%";
		}
		if(action == "increase" && ((current + inc) <= max)){
		val = current + inc;
		obj[i].style.fontSize = val + "%";
		}
		if(action == "reduce" && ((current + inc) >= min)){
		val = current - inc;
		obj[i].style.fontSize = val + "%";
		}
	}
}