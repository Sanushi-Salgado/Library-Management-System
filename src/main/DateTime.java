package main;

public class DateTime {

    private int day;
    private int month;
    private int year;
    private int hours;
    private int minutes;
    private int seconds;
    

    public DateTime(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public DateTime(int day, int month, int year, int hours, int minutes, int seconds) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

	public int getDay() {
        return this.day;
    }

    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getDate() {
        // adding a leading zero if the day or month is below 10
        if (this.day < 10)
            return "0" + this.getDay() + "/" + this.getMonth() + "/" + this.getYear();
        else if (this.month < 10)
            return this.getDay() + "/0" + this.getMonth() + "/" + this.getYear();
        else if (this.day < 10 && this.month < 10)
            return "0" + this.getDay() + "/0" + this.getMonth() + "/" + this.getYear();
        else
            return this.getDay() + "/" + this.getMonth() + "/" + this.getYear();
    }


    public String getTime() {
        // adding a leading zero if the day or month is below 10
        if (this.hours < 10)
            return "0" + this.getHours() + ":" + this.getMinutes() + ":" + this.getSeconds();
        else if (this.minutes < 10)
            return this.getHours() + ":0" + this.getMinutes() + ":" + this.getSeconds();
        else if (this.seconds < 10)
            return this.getHours() + ":" + this.getMinutes() + ":0" + this.getSeconds();

        else if (this.hours < 10 || this.minutes < 10 )
            return "0" + this.getHours() + ":0" + this.getMinutes() + ":" + this.getSeconds();
        else if (this.hours < 10 || this.seconds < 10 )
            return "0" + this.getHours() + ":" + this.getMinutes() + ":0" + this.getSeconds();
        else if (this.minutes < 10 || this.seconds < 10 )
            return this.getHours() + ":0" + this.getMinutes() + ":0" + this.getSeconds();


        else if (this.minutes == 60 && this.seconds == 60) {
            return (this.getHours() + 01) + "00:00";
        } else if (this.seconds == 60) {
            return this.getHours() + (this.getMinutes() + 01) + "00";
        } else
            return this.getHours() + ":" + this.getMinutes() + ":" + this.getSeconds();

    }

    public void setTime(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return "The Date is: " + this.getDate();
    }

}
