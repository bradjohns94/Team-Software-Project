/**Event.java
 * @author Bradley Johns
 * @author Nathan Jackels
 * A class that holds the basic information of an event object
 * to be used by the HAL-endar. Information held includes the
 * event's description, location, and the date/time in which
 * the event takes place
 */

public class Event {
    
    private String title;
    private String desc; //The Description of the event
    private String date;
    private String startTime;
    private String endTime;
    private String location;

    /**Event constructor 1
     * Creates a blank event with no inition information
     */

    public Event() {
        title = "";
        desc = "";
        date = "";
        startTime = "";
        endTime = "";
        location = "";
    }

    /**Event constructor 2
     * Creates an event given the full amount of information
     * @param eventTitle the name of the event
     * @param description a description of the event
     * @param eventDate the date on which the event takes place
     * @param eventStart the time at which the event starts
     * @param eventEnd the time at which the event ends
     * @param eLocation the location of the event
     */

    public Event(String eventTitle, String description, String eventDate,
                 String eventStart, String eventEnd, String eLocation) {
        title = eventTitle;
        desc = description;
        date = eventDate;
        startTime = eventStart;
        endTime = eventEnd;
        location = eLocation;
    }

    /**changeTitle
     * Changes the title of the event
     * @param newTitle the new title of the event
     */

    public void changeTitle(String newTitle) {
        title = newTitle;
    }

    /**changeDesc
     * Changes the description of the event
     * @param newDesc the new description for the event
     */

    public void changeDesc(String newDesc) {
        desc = newDesc;
    }

    /**changeDate
     * Changes the date of the event
     * @param newDate the new date of the event
     */

    public void changeDate(String newDate) {
        date = newDate;
    }

    /**changeStart
     * Changes the start time of the event
     * @param newStart the new start time of the event
     */

    public void changeStart(String newStart) {
        startTime = newStart;
    }

    /**changeEnd
     * Changes the end time of the event
     * @param newEnd the new end time of the event
     */

    public void changeEnd(String newEnd) {
        endTime = newEnd;
    }

    /**changeLocation
     * Changes the location of the event
     * @param newLocation the new location of the event
     */

    public void changeLocation(String newLocation) {
        location = newLocation;
    }

    /**getTitle
     * Returns the title of the event
     * @return the event title
     */

    public String getTitle() {
        return title;
    }

    /**getDesc
     * Returns a description of the event
     * @return the event description
     */

    public String getDesc() {
        return desc;
    }

    /**getDate
     * Returns the date of the event
     * @return the event date
     */

    public String getDate() {
        return date;
    }

    /**getStart
     * Returns the start time of the event
     * @return the event start time
     */

    public String getStart() {
        return startTime;
    }

    /**getEnd
     * Returns the end time of the event
     * @return the event end time
     */

    public String getEnd() {
        return startTime;
    }

    /**getLocation
     * Returns the location of the event
     * @return the event location
     */

    public String getLocation() {
        return location;
    }
}
