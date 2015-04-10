
function ExperienceHidden(value)
{
    if(value == true)
    {
        // disable
        document.getElementById("experience_group").hidden = true;
    }else {
        // enable
        document.getElementById("experience_group").hidden = false;
    }
}