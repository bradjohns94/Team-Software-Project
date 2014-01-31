import java.util.ArrayList;
import java.util.Date;

public class Control {
	private ArrayList<EventData> events = new ArrayList<EventData>();
	private CalendarInterface calendar;

	/**Constructor 1
	 * loads event entries from file, while checking for collisions through the add method.
	 * @param calendar The calendar that will be partially controled by the new Control object.
	 */
	public Control(CalendarInterface calendar){
		this.calendar = calendar;
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
		for(EventData temp : events){
			if(/*Case*/false){
				//Ignore
				return;
			}
		}
		this.events.add(e);
	}
	
	/**changeCalendarView
	 * @param view an integer representation of the calendar view:
	 * Used by Control Panel
	 */
	public void changeCalendarView(int view){
		calendar.changeViewStyle(view);			//TODO Create Method changeViewStyle(int) in Calendar Interface
	}
	
	/**
	 * Used by Control Panel? Needed?
	 */
	public void redrawCalendar(){
		changeCalendarView(calendar.getViewStyle()); 	//TODO Create Method int getViewStyle() in Calendar Interace
	}
	
	/**
	 * Used by Control Panel on close.
	 */
	public boolean saveEvents(){
		DataStorage storage = new DataStorage();
		return storage.saveData(events);
	}
	
	/**
	 * Used by Control Panel
	 */
	public boolean deleteEvent(EventData e){
		String[] eData = e.dataOrdered();
		for(EventData temp : events){
			String[] tempData = temp.dataOrdered();
			boolean sameEvent = true;
			for(int i = 0; ((i < tempData.length) && (i < eData.length)); i++){
				if(!eData[i].equals(tempData[i]){
					//If any of the data doesn't match, return.
					sameEvent = false;
				}
			}
			if(sameEvent){
				//Search for Event, remove from arrayList:
				for(int i = 0; i < events.size(); i++){
					if(event.get(i).equals(temp)){
						events.remove(i);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Used by Event Interface
	 */
	public void editEvent(EventData original, EventData replacement){
		deleteEvent(original);
		add(replacement);
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