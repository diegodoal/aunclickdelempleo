function levelExperiencesSetHidden(value)
{
    if(value == true)
    {
        // disable
        //$("#level_experience").hide();
        document.getElementById("level_experince").hidden = true;
    }else {
        // enable
        document.getElementById("level_experience").hidden = false;
    }
}