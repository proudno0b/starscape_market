# BUILD INSTRUCTIONS:

1. Create a new java file in the com/market/app/ directory named Secrets.java
2. The Secrets class must contain your VIO api key and have a method called protected String getAPIKey() that returns your API key as a string to be passed with the HttpRequest.
3. The Secrets class must have a method called protected String getDirectory() that returns the path to the directory containing Main.java (i.e "C:\\Users\\USERNAME\\cool_folder\\starscape_market\\maven_directory\\src\\main\\com\\market\\app")
Backslashes must be escaped on windows.
