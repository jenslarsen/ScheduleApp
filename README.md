# ScheduleApp

  Datasource.java holds static methods to manipulate the database and was created to
  isolate the database code from the rest of the package
 
  User names and passwords to access the application:
 
  User: jens Password: panda
 
  User: jamie Password: hippo
 
  User: test Password: test
 
  Some assumptions about the project, database, and its structure were made:
 
  - main method is in FXMLLoginController.java
 
  - commented lines exist in the main method to change the locale and time
  zone.
 
  - ID fields were set to auto-increment starting at ID 100.
 
  - The user that is logging into the application (consultant) is the same as
  the contact listed on the appointment. Therefore, only appointments for the
  current logged in user are shown in the appointment list.
 
  - The reminder table is not used - it doesn't seem necessary for this
  application.
 
  - The address table does not have a state field so none of the address have
  one.
 
  - Business hours are between 8am and 5pm local time.
 
  - Logins are logged to a file in the base directory called logins.log. There
  is also a button on the reports screen to open this file with the default
  text editor.