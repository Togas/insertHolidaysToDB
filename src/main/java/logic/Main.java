package logic;

import Model.Holiday;
import Model.States;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        //as arg you need to pass the db uri where your holiday collection is located like: "localhost:3306/erp_base?user=root&password="
        String dbUri;
        int year = 2019;
        List<Holiday> holidays=NewYearService.getHolidays(year, States.HE);
        if(args.length ==0) {
            dbUri="localhost:3306/erp_base?user=root&password=";
        } else {
            dbUri=args[0];
        }
        NewYearService.writeHolidaysToDB(holidays, dbUri);
    }
}
