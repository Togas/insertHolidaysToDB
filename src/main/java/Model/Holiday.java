package Model;


public class Holiday {

    private String id;
    private String name;
    private String date;
    private int year;
    private int month;
    private States state;

    public Holiday(String name, String date, int year, int month, States state) {
        this.name = name;
        this.date = date;
        this.year = year;
        this.month = month;
        this.state= state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public States getState() {
        return state;
    }

    public void setState(States state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
