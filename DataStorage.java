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
	
	/**Constructor 1
	 * This constructor loads data if the file is present, and creates the file otherwise.
	 */
	public DataStorage(){
		Scanner file;
		try {
			file = new Scanner(new File("./CalendarData.data"));
		} catch (FileNotFoundException e) {
			//File doesn't exist, create it.
			File newFile = new File("./CalendarData.data");
			try {
				FileWriter out = new FileWriter(newFile);
				out.close();
			} catch (IOException e1) {
				//Why would this happen?
				System.out.println("Error in data storage.");
				//e1.printStackTrace();
			}
		}
	}

	/**loadData
	 * This method loads data from the storage file, then returns it for use.
	 * @return The loaded EventData.
	 */
	public ArrayList<EventData> loadData() {
		ArrayList<EventData> result = new ArrayList<EventData>();
        Scanner file;
        try {
            file = new Scanner(new File("./CalendarData.data"));
        } catch (Exception e) {}
		String input = "";
		String[] parameters = EventData.parameterOrder();
		while(file.hasNext()){
			input = file.nextLine();
			if((input.toCharArray()[0] == '@')){
				String[] data = new String[parameters.length];	//Retrieve Data parameters
				for(int i = 0; i < data.length; i++){
					data[i] = null;		//Used to check for valid entrys later
				}
				for(int i = 0; i < parameters.length; i++){	//Data is loaded from file
					String temp = file.nextLine();
					String param = temp.substring(temp.indexOf('<')+1,temp.indexOf('>'));
					for(int j = 0; j < parameters.length; j++){
						if(parameters[j].equals(param)){
							data[j] = temp.substring(temp.indexOf('(') + 1, temp.indexOf(')'));
						}
					}
				}
				boolean validData = true;
				for(int i = 0; i < data.length; i++){	//Data is checked for missing information
					if(data[i] == null){
						validData = false;
					}
				}
				if(validData){		//If entry is complete, it is recorded for later use
					result.add(new EventData(data));
				}
			}
		}
		return result;
	}
	
	/**saveData
	 * A method that saves all data passed to it.
	 * @param e The ArrayList of EventData objects that need to be saved.
	 * Note: This only saves the EventData objects passed to it.
	 */
	protected boolean saveData(ArrayList<EventData> e){
		File file = new File("./CalendarData.data");
		try{
			FileWriter out = new FileWriter(file);
			String[] parameters = EventData.parameterOrder();
			for(EventData t : e){
				//Write out individual events
				String[] values = t.dataOrdered();
				out.write("@\n");
				for(int i = 0; i < values.length; i++){
					//Write each individual parameter for the event
					String outLine = "<" + parameters[i] + ">(" + values[i] + ")\n";
					out.write(outLine);
				}
			}
			out.close();
		} catch (IOException e1){
			System.out.println("Unable to save events on close.");
			return false;
		}
		return true;
	}
	
}
