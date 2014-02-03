compile: data
	javac *.java
	
data:
	touch CalendarData.data
	chmod +x CalendarData.data

run: compile
	appletviewer CalendarInterface.java
