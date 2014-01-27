import java.util.ArrayList;
import java.util.Date;

public class Control {
	private ArrayList<EventData> events = new ArrayList<EventData>();

	/**Constructor 1
	 * loads event entries from file, while checking for collisions through the add method.
	 */
	public Control(){
		//load data
		DataStorage data = new DataStorage();
		ArrayList<EventData> temp = data.loadData();
		for(EventData t : temp){
			add(t);
		}
	}

	/**add
	 * Checks for collisions with other events (such as same name), and adds if no collisions
	 * @param e The event entry to be added
	 */
	public void add(EventData e){
		//Check collisions with current events
		//Add to events
		//TODO Complete
	}

	/**getEvents
	 * Returns all event entries that end after the begindate and start before the endDate.
	 * @param beginDate the beginning of the period
	 * @param endDate the ending of the period
	 * @return All the events within the period starting with beginDate and ending with endDate
	 * TODO: Check if events that end at exact time of beginDate are included (same for endDate)
	 */
	public ArrayList<EventData> getEvents(Date beginDate, Date endDate){
		ArrayList<EventData> results = new ArrayList<EventData>();
		for(EventData e : events){
			if((e.getEnd().after(beginDate)) && (e.getStart().before(endDate))){
				results.add(e);
			} else {
				//Not in requested date range.
			}
		}
		return results;
	}
}
