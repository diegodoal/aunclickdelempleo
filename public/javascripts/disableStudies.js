

function levelStudiesSetHidden(value)
{
    if(value == true)
    {
        // disable
        //$("#level_studies_group").hide();
        document.getElementById("level_studies_group").hidden = true;
    }else {
        // enable
        document.getElementById("level_studies_group").hidden = false;
    }
}

function levelStudiesSetHiddenNo(value)
{
    if(value == true)
    {
        // disable
        //$("#level_studies_group").hide();
        document.getElementById("level_studies_group_no").hidden = true;
    }else {
        // enable
        document.getElementById("level_studies_group_no").hidden = false;
    }
}