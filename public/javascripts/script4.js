



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