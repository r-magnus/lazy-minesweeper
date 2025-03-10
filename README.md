# lazy-minesweeper
#### Ryan Magnuson

This is my own implementation of 1990's Minesweeper, made from scratch.
For funsies, I've used absolutely **zero** AI in the making of this project.
No ChatGPT, no Copilot, nothing. Take that, modern day and age! Now go have fun
with an old game.

---

## Index
* [Environment](#environment)
* [Codebase](#codebase)
* [Misc](#misc)

---

## Environment
I'm running this on `Java 21`. You can currently run the game
by simply running `Main`. You can change the game specifications in that file
to fit what you want to play.

---

## Codebase
Most of the classes just deal with the data structure of the game. The
`VisualGrid` class, however, deals with all the visuals, still handled in 
Java. The sprites for these visuals can be found under the `sprites` directory. 
These assets have existed for a while, so I think I'm at a decent liberty to use
them ethically myself.

---

## Misc
It's called "**lazy**" because I haven't really perfected the runtime of the
algorithms I've written (myself) for this project. Maybe I'll get around
to that.

Final Notes: Don't `$ rm -rf` my code. 

