function licenseCertificateHidden(value)
   {
       if(value == true)
       {
           // disable
           document.getElementById("license_group").hidden = true;
       }else {
           // enable
           document.getElementById("license_group").hidden = false;
       }
   }