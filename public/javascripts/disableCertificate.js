function disCertificateHidden(value)
    {
        if(value == true)
        {
            // disable
            document.getElementById("certificate_group").hidden = true;
        }else {
            // enable
            document.getElementById("certificate_group").hidden = false;
        }
    }
function disCertificateHiddenNo(value)
    {
        if(value == true)
        {
            // disable
            document.getElementById("certificate_group_no").hidden = true;
        }else {
            // enable
            document.getElementById("certificate_group_no").hidden = false;
        }
    }