This is a simple Java-based maze-solving program that reads maze data from a file and finds the shortest path to the goal using a depth-first search algorithm.

How It Works
- The program loads the maze into a 2D array

- It locates the start and uses DFS to search for the goal

- Prints visited coordinates

- The first line in the map file defines the number of rows, columns, and mazes

Maps use the following characters:

@ = Walls    
. = Traversable paths  
W = Start position  
$ = Goal position  

Example  
6 5 1  
@@@@@  
W...@  
@@..@  
@.@.@  
@...$  
@@@@@  

Instructions for Use
1. Clone (or download as .zip): git clone https://github.com/yourusername/mazescanner.git
2. Navigate to project folder: cd mazescanner
3. Compile program: javac Maze.java
4. Run the program with desired maze file (easyMap1, easyMap2, mediumMap, hardMap): java Maze easyMap1.txt




