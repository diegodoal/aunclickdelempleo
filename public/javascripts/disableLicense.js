function licenseCertificateHidden(value)
{
    if(value == true)
    {
        // disable
        document.getElementById("license_certificate_group").hidden = true;
    }else {
        // enable
        document.getElementById("license_certificate_group").hidden = false;
    }
}