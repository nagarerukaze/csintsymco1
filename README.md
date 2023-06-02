# Maze Bot
## Major Course Output for CSINTSY Course
#### Professor: Thomas Tiam-Lee

## Project Description
Our machine course output for CSINTSY for Term 2 AY2022-2023 involves implementing a 
Maze Bot, using Artificial Intelligence concepts such as search algorithms. This project
makes use of a Depth-First-Search Algorithm. The Maze Bot has a GUI that is able to display
the search path of the bot, as well as the total number of states or maze cells explored.

## Compiling & Running the Program
	> Java must be installed in your computer
- Open Terminal (in MacOS)
- Compile Java classes
	```javac *.java```
- Run View
	```java View```
  
## Preparing Maze File
- n x n (size) mazes are read through text files.
- maze text files should be formatted as:
  | Symbol      | Description                  |
  | ----------- | ---------------------------- |
  | #           | wall                         |
  | . (period   | an empty space               |
  | S           | starting location of the bot |
  | G           | target location of the bot   |

- the first line should be an integer indicating the size of the maze (n x n), where the integer is from 3 to 64
- sample .txt file:
  > 5\
  > ....G\
  > .####\
  > ...#S\
  > .#.#.\
  > .#...

- the maze .txt file should be placed in the same folder as the source code and renamed as
  ```maze.txt```
