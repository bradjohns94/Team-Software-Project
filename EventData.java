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
    
    /**EventData Constructor 3
     * Creates an event, specifically used by the DataStorage class.
     * Designed to allow changing of event parameters without having to modify DataStorage.java
     * @param data The array of data that will define the new event. For order of data, see parameterOrder()
     */
    public EventData(String[] data){
    	int size = 0;
    	if(data.length > parameterOrder().length){
    		size = parameterOrder().length;
    	} else {
    		size = data.length;
    	}
    	switch(size){
    	case 5:
    		this.title = data[4];
    	case 4:
    		this.desc = data[3];
    	case 3:
    		this.startTime = toDate(data[2]);
    	case 2:
    		this.endTime = toDate(data[1]);
    	case 1:
    		this.location = data[0];
    	case 0:
    	default:
    	}
    }
    
    /**toDate
     * Converts the String representation generated by java.util.date.toString() into a new Date object.
     * @param date The String representation... ...toString()
     * 
     */
    @SuppressWarnings("deprecation")
	private Date toDate(String date) {
    	Date result = new Date();
		result.setHours(toInt(date.substring(11,13)));
		result.setMinutes(toInt(date.substring(14,16)));
		result.setDate(toInt(date.substring(8,10)));
		result.setYear(toInt(date.substring(24,28)) - 1900);
		String month = date.substring(4,7);
		switch(month){
		case("Jan"):
			result.setMonth(0);
			break;
		case("Feb"):
			result.setMonth(1);
			break;
		case("Mar"):
			result.setMonth(2);
			break;
		case("Apr"):
			result.setMonth(3);
			break;
		case("May"):
			result.setMonth(4);
			break;
		case("Jun"):
			result.setMonth(5);
			break;
		case("Jul"):
			result.setMonth(6);
			break;
		case("Aug"):
			result.setMonth(7);
			break;
		case("Sep"):
			result.setMonth(8);
			break;
		case("Oct"):
			result.setMonth(9);
			break;
		case("Nov"):
			result.setMonth(10);
			break;
		case("Dec"):
			result.setMonth(11);
			break;
		default:
			result.setMonth(0);
		}
		result.setSeconds(0);
		return result;
	}
    
    /**toInt
     * A method that converts the last 4 characters in a given string into an integer.
     * @param e The string that will be converted
     * @return The integer conversion of the string parameter e.
     * Notes: Any invalid characters will be treated as a '0'.
     */
    public static int toInt(String e){
		char[] input = e.toCharArray();
		char[] word = {'0','0','0','0'};
		int inputIndex = input.length-1;
		int wordIndex = 3;
		while((wordIndex >= 0) && (inputIndex >= 0)){
			word[wordIndex] = input[inputIndex];
			wordIndex--;
			inputIndex--;
		}
		int result = 0;
		int[] temp = new int[4];
		for(int i = 0; i < 4; i++){
			switch(word[i]){
			case('9'):
				temp[i] = 9;
			    break;
			case('8'):
				temp[i] = 8;
		    	break;
			case('7'):
				temp[i] = 7;
		    	break;
			case('6'):
				temp[i] = 6;
		    	break;
			case('5'):
				temp[i] = 5;
		    	break;
			case('4'):
				temp[i] = 4;
		    	break;
			case('3'):
				temp[i] = 3;
		    	break;
			case('2'):
				temp[i] = 2;
		    	break;
			case('1'):
				temp[i] = 1;
		    	break;
			case('0'):
				temp[i] = 0;
			default:
			}
		}
		result += temp[3];
		result += (10*temp[2]);
		result += (100*temp[1]);
		result += (1000*temp[0]);
		return result;
	}

    /**parameterOrder
     * This method assists the DataStorage class by giving it the order of the parameters being saved.
     * @return An ordered list of short representations of the data associated with an Event being stored.
     */
	protected static String[] parameterOrder(){
    	String[] result = {"Title","Desc","StartT","EndT","Loc"};
    	return result;
    }
    
    /**dataOrdered
     * This method is specifically used by the DataStorage class to get all the data associated with an Event that needs to be stored.
     * @return An ordered list of string representations of the data associated with an Event.
     */
    protected String[] dataOrdered(){	//TODO ADD
    	String[] result = {title, desc, startTime.toString(), endTime.toString(), location};
    	return result;
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
    
    
    public void changeStart(String newStart){
    	startTime = toDate(newStart);
    }
    
    /**toDate
     * some of the accepted formats:
     * MM/DD/YYYY HH:MMAM
     * MM/DD/YYYY HH:MM AM
     * M/D/YY H:M:AM
     * HH:MMAM MM/DD/YYYY
     * H:MM: AM M/DD/YY
     * 
     */
    private void toDate(String e){
    	//TODO--------------------------------------------
    	
    	
    }

    /**changeEnd
     * Changes the end time of the event
     * @param newEnd the new end time of the event
     */

    public void changeEnd(Date newEnd) {
        endTime = newEnd;
    }
    
    public void changeEnd(String newEnd){
    	endTime = toDate(newEnd);
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
