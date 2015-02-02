

function hide_job_experience(value)
{
    if(value == true)
    {
        // disable
        document.getElementById("job_experience_group").hidden = true;
        document.getElementById("job_experience_group_1").hidden = true;
        document.getElementById("job_experience_group_2").hidden = true;
        document.getElementById("job_experience_group_3").hidden = true;
        document.getElementById("job_experience_group_4").hidden = true;
        document.getElementById("job_experience_group_5").hidden = true;

    }else {
        // enable
        document.getElementById("job_experience_group").hidden = false;
        document.getElementById("job_experience_group_1").hidden = false;
        document.getElementById("job_experience_group_2").hidden = false;
        document.getElementById("job_experience_group_3").hidden = false;
        document.getElementById("job_experience_group_4").hidden = false;
        document.getElementById("job_experience_group_5").hidden = false;
    }
}
