package controllers;

import org.joda.time.Days;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static play.data.Form.form;

/**
 * Created by Victor on 12/03/2015.
 */
public class TestFormController extends Controller {

    public static Result dateForm(){
        DynamicForm form = form().bindFromRequest();

        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");
        Date inputDate = null;
        Date inputDate2 = null;

        try {
            inputDate = inputDateFormat.parse(form.get("date1").toString() +" "+form.get("hour1").toString());
            inputDate2 = inputDateFormat.parse(form.get("date2").toString() +" "+form.get("hour2").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-mm-yyyy HH:mm");



        long diff = (inputDate2.getTime() - inputDate.getTime());
        String result = ""+TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        return ok("Hey! "+result);
    }
}
