# insertHolidaysToDB
if main method is executed it sends rest call to feiertage api and inserts all holidays to db table
you have to execute jar in command line and pass as first argument your db-uri, example:java -jar newYearS
ervicePlainJava2.jar "localhost:3306/erp-base?user=root&password=" and as your 2nd argument the year.
if year is empty it takes current year.
