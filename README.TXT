Title: seanthompsonC195 - 
Purpose: Application to Schedule appoints between internal and external persons. Utilizing this application may add new customers adding their address, phone number, country, first level division(state or province) and edit all existing customer date. Utilizing customer records and internal contact records you  may schedule new appoints for customers with contacts. The applicatiton will automatically convert all time choices for appointments into EST where the main place of business exists such that they may select appointment times based on their local time and see the conversion real-time. Language resources have been included in both french and spanish to assist in utilizatiton of program. This function will take place automatically based on language of computer utilizing thhe program.

Development Platform Versions:
Application Version: 1.0
Apache Netbeans ID 12.6
Java SE 17.0.1
Version 17.0.0
mysql-connector-java-8.0.25


Running The Application:
The current easiest method to run the application is as follows
1. Unzip the files into a directory of your local computer.
2. Open the  unzip project file via Netbeans.
3. After full application loads please add any missing libraries as needed. As Javafx was heavily relied upon it may be necessary to add this library to the project if it is missing or deprecated.
4. Go to "Run > Run Projects seanthompsonC195"
5. Upon application opening you will see the login screen with your computers local time and language. If the language or time is incorrect please change your computer language and timezone and restart the application.
6. To login the application the following passwords and usernames have been provided.

List of usernames and passwords:
userName	password
test		test

7. After successful login you may select one of the 4 menu options too proceed to different parts of the application or logout.

Reports:
Two additional reports outside of standard ask have been provided.
1. A Tally report of current customers ordered by Country and Region.
	The intention of this report is for deciding in future if moving operations to another location would be beneficial based  on clientel available.

2. A list of all appointments that have passed is provided.
	Intentioned for possible Key Performancne Indicator(KPI) in the future.


MySQL Database Connection Information:
MySQL Connector Driver:mysql-connector-java-8.0.25
Database name: client_schedule
Username: sqlUser
Password: Passw0rd!