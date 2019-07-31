package logic;

import Model.Holiday;
import Model.States;
import com.sun.deploy.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //as arg you need to pass the db uri where your holiday collection is located like: "localhost:3306/erp_base?user=root&password="
        String dbUri;
        int year;
        if (args.length == 0) {
            dbUri = "localhost:3306/erp_base?user=root&password=";
        } else {
            dbUri = args[0];
        }
        if (args.length<=1) {
            Date date = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            year = calendar.get(Calendar.YEAR);
        } else {
            year = Integer.parseInt(args[1]);
        }
        List<Holiday> holidays = NewYearService.getHolidays(year, States.HE);
        List<Holiday> weekends= NewYearService.getAllWeekends(year);
        NewYearService.writeHolidaysToDB(holidays, dbUri);
        NewYearService.writeHolidaysToDB(weekends, dbUri);

    }
}
