# Packman_Game
Ex3- ReadMe
Chen Ostrovski 
Ester Reznikov 
This project was done as part of assignment number 3. In fact, this assignment is a continuation of Task 2, and will be the basis for Task 4. In this task we will play a Pacman-style game. The game board will be a map, on which fruits will be placed and Packmans. The goal of the game: Eat all the fruits on the board. Each packman has a speed of movement (in meters per second) and an eating radius (in meters). The game configuration will be determined according to the information given in the CSV file or by clicking on the map. The user has a menu that allows him to upload a "game" file, save a game file, and run the game. After the run, the running result can be saved as a KML file with time signatures to allow easy conversion to Google Earth. The simulation will display the panel update in "real time". Which mean that in each real second we see the progression of the Packmans (according to their speed and location data). 
Buttons explanation: 
To use the buttons correctly, please read the following explanation:
AddMenu
Packman: By clicking the Packman option, the user can add a Packman wherever it chooses on the map.
Fruit: By clicking the Fruit option, the user can add a Fruit wherever it chooses on the map.
Show point: By clicking this option, the user can click map wherever it chooses and see the point location in pixel.
FileMenu
Open file: By clicking this option the user can chose CSV file on its computer and add the info of the file to the game.
Save csv: By clicking this option the user can save its game in CSV file.
Save kml: By clicking this option the user can save its game in KML file.
GameMenu
Play: By clicking this option the user can start the game itself, after clicking this option the Packman start to eat all the Fruits on the map, it stops only after the Packmans eat all the Fruits.
Restart: By clicking this option the user delete all the info of the game, clear the map from any Packman or Fruits, of course the user can click again one of the above buttons and start a new game!.
Good Luck!!! 
Package Algorithms:
Map: This class contains all the data of the map. In this class the user can find the map size and all the images of the map itself, the Packmen and Fruits. Also, it contains the conversion between coordinate to pixel and pixel to coordinate, and calculates the distance, elevation and azimuth of two pixel points.\
ShortestPathAlgo: This class contains the algorithm of the shortest path to every Packman. The shortest path mean the fastest time it will take to all the Packmen to eat all the Fruits.
File Format:
Path2KML: This class converts a path into KML file. The class allows the user to keep track of the movement of a Packman. The track should be kept in a way that will allow it to run on GoogleEarth and see the movement of Packmans and the "eating" of Fruits.
GIS:
Fruit: This class represents Fruits and all the data that they contain.
Packman: This class represents Packmans and all the data that they contain.
Path: This class represents the path of every Packman. 
GUI:
Game Frame: This class represents all the info of the Jframe and creates it. It prints to the screen all the elements of the game: Packmans, Fruits, Lines and ECT. Also, it contains the images of the maps itself and the images of the elements. 




