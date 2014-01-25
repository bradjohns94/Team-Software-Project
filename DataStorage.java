import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Data Storage Format: [Fields][2] <Description>(Value)
 */

public class DataStorage {
	Scanner file;
	ArrayList<EventData> events = new ArrayList<EventData>();
	
	public DataStorage(){
		try {
			file = new Scanner(new File("./CalendarData.data"));
			loadData();
		} catch (FileNotFoundException e) {
			//File doesn't exist, create it.
			File file = new File("./CalendarData.data");
			try {
				FileWriter out = new FileWriter(file);
				out.close();
			} catch (IOException e1) {
				//Why would this happen?
				System.out.println("Error in data storage.");
				e1.printStackTrace();
			}
		}
	}

	private void loadData() {
		// TODO Auto-generated method stub
		
	}
	
	protected ArrayList<EventData> getData() {
		return events;
	}
	
	protected void saveData(ArrayList<EventData> e){
		//TODO
		File file = new File("./CalendarData.data");
		try{
			FileWriter out = new FileWriter(file);
			for(EventData t : e){
				//Write out individual events
			}
			out.close();
		} catch (IOException e1){
			e1.printStackTrace();
		}
	}
	
}
