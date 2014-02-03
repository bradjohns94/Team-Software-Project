compile:
	javac *.java
	
data: compile
	touch CalendarData.data

run: data
	appletviewer -J-Djava.security.policy=java.policy.applet CalendarInterface.java
	
reset:
	rm CalendarData.data
	touch CalendarData.data
