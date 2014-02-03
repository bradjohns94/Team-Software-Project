@echo compiling program
javac *.java
@echo running program
appletviewer -J-Djava.security.policy=java.policy.applet CalendarInterface.java
