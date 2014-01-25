import java.util.Date;

/**Event.java
 * @author Bradley Johns
 * @author Nathan Jackels
 * A class that holds the basic information of an event object
 * to be used by the HAL-endar. Information held includes the
 * event's description, location, and the date/time in which
 * the event takes place
 */

public class EventData {
    
    private String title;
    private String desc; //The Description of the event
    private Date startTime;
    private Date endTime;
    private String location;

    /**Event constructor 1
     * Creates a blank event with no initial information
     */

    public EventData() {
        title = "";
        desc = "";
        startTime = new Date();
        endTime = new Date();
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

    public EventData(String eventTitle, String description, Date eventStart, Date eventEnd, String eLocation) {
        title = eventTitle;
        desc = description;
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

    /**changeStart
     * Changes the start time of the event
     * @param newStart the new start time of the event
     */

    public void changeStart(Date newStart) {
        startTime = newStart;
    }

    /**changeEnd
     * Changes the end time of the event
     * @param newEnd the new end time of the event
     */

    public void changeEnd(Date newEnd) {
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

    /**getStart
     * Returns the start time of the event
     * @return the event start time
     */

    public Date getStart() {
        return startTime;
    }

    /**getEnd
     * Returns the end time of the event
     * @return the event end time
     */

    public Date getEnd() {
        return endTime;
    }

    /**getLocation
     * Returns the location of the event
     * @return the event location
     */

    public String getLocation() {
        return location;
    }
    
    /** getSaveData
     * Creates flexibility in expanding EventData by saving data with the description.
     * @return An array of DataDescription, and it's corresponding Data.
     */
    public String[] getSaveData(){
    	String[] result = new String[5];
    	result[0] = "<Desc>(" + this.getDesc() + ")";
    	result[1] = "<" + this.getLocation() + ")";
    	result[2] = "<" + this.getTitle() + ")";
    }
}
