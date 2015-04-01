

function jobExperienceHidden(value)
{
    if(value == true)
    {
        // disable
        document.getElementById("job_experience_group").hidden = true;
    }else {
        // enable
        document.getElementById("job_experience_group").hidden = false;
    }
}
