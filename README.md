This works as a user guide for the repository. 
This Project uses RestAssured and integrated with testNG and this is develoepd using java.
#Steps to use
• For cloning the repo Please use -> git clone https://github.com/srmishraqa/spaceXAPITests.git 
• Once Cloning is done use -> mvn clean install
• It will automatically download all the jars defined in POM.xml file, build the project, run the test cases and will generate a report
• To rerun again first -> clean the target folder by running -> mvn clean
• And then run -> mvn clean install (or) go to src/main/resources folder and run the textng.xml file
• after each run need to clean the target folder by earlier mentioned command
• To find out the default report -> go to tagert/surefire-reports and open index.html file (pre-requisite: always should have been run by command -> mvn clean install)
• Base configuration found inside src/main/java/
• Tests can be found in src/test folder
• All the test data are kept in config.properties
• For any queries please contact: soumya.qe@gmail.com

