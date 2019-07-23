package logic;

import Model.Holiday;
import Model.States;

import javax.activation.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewYearService {

    //makes a rest call to https://feiertage-api.de/. You need to pass the year and Bundesland
    public static List<Holiday> getHolidays(int year, States state) {
        try {

            String urlParameter = "?jahr=" + year + "&nur_land=" + state;
            URL url = new URL("https://feiertage-api.de/api/" + urlParameter);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            ArrayList<String> holidaysAsString = new ArrayList<String>();
            while ((output = br.readLine()) != null) {
                String[] allHolidays = output.split(",");
                holidaysAsString.addAll(Arrays.asList(allHolidays));
            }
            conn.disconnect();
            List<Holiday> holidays = new ArrayList<Holiday>();
            for (int i = 0; i < holidaysAsString.size(); i++) {
                if (i % 2 != 0) {
                    continue;
                } else {
                    String holidayString = holidaysAsString.get(i).replace("\"", "").replace("{", "");
                    String[] holidayArray = holidayString.split(":");
                    String name = holidayArray[0];
                    String date = holidayArray[2];
                    int month = Integer.parseInt(date.split("-")[1]);

                    Holiday holiday = new Holiday(name, date, year, month, state);
                    holidays.add(holiday);
                }
            }

            return holidays;

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return null;
    }

    public static void writeHolidaysToDB(List<Holiday> holidays) {
        try {
            String url = "jdbc:mariadb://localhost:3306/erp-base?user=root&password=";
            Connection conn = DriverManager.getConnection(url);
            Statement st = conn.createStatement();
            // the mysql insert statement
            String query = " insert into holidays (year, month, date, name, state)"
                    + " values (?, ?, ?, ?, ?)";
            for(Holiday holiday: holidays) {
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, holiday.getYear());
                preparedStmt.setInt(2, holiday.getMonth());
                preparedStmt.setString(3, holiday.getDate());
                preparedStmt.setString(4, holiday.getName());
                preparedStmt.setString(5, holiday.getState().toString());

                preparedStmt.execute();
            }

            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

}

