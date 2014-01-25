/**Day.java
 * @author Bradley Johns
 * @author Nathan Jackels
 * A class that holds the basic information of a day object
 * to be used in the HAL-calendar. Information held includes the
 * day number, month, year, and any events that may be taking place
 * on the given day.
 * 
 * This Class has been replaced by java.util.Date;
 */

import java.util.ArrayList;
import java.util.Date;

public class Day {
    
    private int number;
    private int month;
    private int year;
    private ArrayList<EventData> events;

    /**Day constructor 1
     * Creates a blank Day with no initial information
     */

    public Day() {
        events = new ArrayList<EventData>();
    }

    /**Day constructor 2
     * Creates a day object given the full amount of information
     * @param dayNumber the number of the day in the month
     * @param dayMonth the month in which the day takes place
     * @param dayYear the year in which the day takes place
     */

    public Day(int dayNumber, int dayMonth, int dayYear) {
        number = dayNumber;
        month = dayMonth;
        year = dayYear;
        events = new ArrayList<EventData>();
    }

    /**parseTime
     * gives a minute value of a time recognition string
     * formated as "XX:XXAM"
     * @param date the string to be parsed
     * @return an integer representing minutes into the day
     */

    private int parseTime(Date date) {
        int val = 0;  
        String hour = "" + date.getHours();
        String minute = "" + date.getMinutes();
        String dayHalf = "";
        if(date.getHours() > 12){
        	dayHalf += "PM";
        } else {
        	dayHalf += "AM";
        }
        val = 60 * Integer.parseInt(hour); //Represent hours in minutes 
        val += Integer.parseInt(minute);
        if (dayHalf.equals("PM")) val += (60 * 12); //Account for the half of day 
        return val;
    }

    /**addEvent
     * Adds a new event to the events list
     * @param newEvent the event to be added
     * @return true or false depending if there was a time conflict
     */

    public boolean addEvent(EventData newEvent) {
        for (EventData e : events) {
            if ((parseTime(newEvent.getStart()) > parseTime(e.getStart())
                 && parseTime(newEvent.getEnd()) < parseTime(e.getEnd())) ||
                (parseTime(newEvent.getEnd()) > parseTime(e.getStart())
                 && parseTime(newEvent.getEnd()) < parseTime(e.getEnd()))) {
                return false;
            } //This is a hideous if block... it checks if the times overlap
        }
        events.add(newEvent);
        return true;
    } 
        
    /**removeEvent 
     * Removes a specific event assuming the event exists in the list 
     * @param Event toRemove 
     * @return true or false depending if the event was removed 
     */ 
    
    public boolean removeEvent(EventData toRemove) { 
        return events.remove(toRemove); 
    } 
        
    /**clearSchedule 
     * Removes all events currently scheduled for the day 
     */ 
        
    public void clearSchedule() { 
        events = new ArrayList<EventData>();
    }

    /**changeNumber
     * changes the day number of the day
     * @param newNumber the new number of the day
     */

    public void changeNumber(int newNumber) {
        number = newNumber;
    }

    /**changeMonth
     * changes the month that the day is in
     * @param newMonth the new month for the day
     */

    public void changeMonth(int newMonth) {
        month = newMonth;
    }

    /**changeYear
     * changes the year that the day is in
     * @param newYear the new year for the day
     */

    public void changeYear(int newYear) {
        year = newYear; //HAPPY NEW YEAR!
    }

    /**getNumber
     * Returns the day number
     * @return the number of the day in the month
     */

    public int getNumber() {
        return number;
    }

    /**getMonth
     * Returns the month that the day is in
     * @return the month the day is in
     */

    public int getMonth() {
        return month;
    }

    /**getYear
     * Returns the year that the day is in
     * @return the year the day is in
     */

    public int getYear() {
        return year;
    }
    
    /**getEvents
     * Returns the list of events occuring on the day
     * @return the list of events
     */

    public ArrayList<EventData> getEvents() {
        return events;
    }
}
