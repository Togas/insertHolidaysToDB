package logic;

import Model.Holiday;
import Model.States;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        int year = 2019;
        List<Holiday> holidays=NewYearService.getHolidays(year, States.HE);
        NewYearService.writeHolidaysToDB(holidays);
    }
}
