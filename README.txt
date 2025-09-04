# Group 32 Chips Challenge game

This project contains the source and fx files needed to compile and run the game. Please ensure all Files are copied in correctly.

## Installation folder for A2

In order to run the game, the user will be required to install the Group32 folder.
This folder should be downloaded, and placed in an easily accesible location.
The folder contains all the games core content, display methods and visuals, which will require select software.

### Prerequisites for A2

Any IDE with java installed.
NOTE: Intellij is recommended for this installation.
Any IDE with javafx installed.
NOTE: javafx must be set up within an easy access area.

### Installation Instructions for A2

Once an IDE has been established, the Group32 folder should be opened up as a project.

Once in project view, typically accesible through the "file" option in the hotbar, the user can open the folder with the game content.

The user should then set up javafx to be compatiable with the project.
The user will be required to go into "Files" -> "Project Structure" first to set up.
Once in "Project Structure", the user will need to ensure the "modules" feature the Group32 
folder and its location, and these respective titles:

.idea,actor,Fxml,game,item,level,levels,out,png and tile.

The "Libraries" section will need to feature a "Classes" title, followed by the javafx/lib location and folder.

In order to run the game, the user will need to run a test. 
To create a test if one isn't present, the user should go to "Run" -> "Edit configurations"
When in this banner, the user should ensure the module path is the same directory as their javafx file, and the modules include javafx.controls and javafx.fxml.
Under modify options, the add VM options should be ticked alongside environment variables and open/run debug. 

The test that comes with the package is MainMenuTest, and will need to be called this if a new is made following a faulty download. 

## How to play

Once in the game menu, a new user or existing user option is available.
The user should then proceed to fill out this information.
To play the game, the user should proceed to levels, and select level 1.

The user may control the character using ASWD, with the respective positions relating to direction of movement.
The goal for the user is to avoid each levels dangers, and proceed to the end zone.
The user will often require items such as keys or chips to progress, which ensures challenge in each level.
Highscore is generated for the user based on their time to completion, and how many, if any, chips were picked up and were not used come level end.

The user should then see their score in the highscore section, to rank themselves amoung over players for fastest time and score.


### Testing
To run the projects default test case, "MainMenuTest", the user will need to find this test at the bottom of the project, and run. IF any issues arise see "Installation Instructions" for assistance.

## Credits

Tomas Williams
Max Holloway
Carl Antill
Tanvir Ahmed
Shane Lee
Aeron Vergara
Ken Hung

## License

Group32 A2 University teaching project