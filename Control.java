
import java.util.ArrayList;
import java.util.Date;

public class Control {
	private ArrayList<EventData> events = new ArrayList<EventData>();

	public Control(){
		//load data
	}

	public void add(EventData e){
		//Check collisions with current events
		//Add to events
	}

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
