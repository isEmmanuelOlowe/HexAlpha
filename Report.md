# Report: HexAlpha

## Overview

The specification requires that a game of Hex be implemented. Which matches the rules and specifications provided by:`https://en.wikipedia.org/wiki/Hex_(board_game)`  This game of hex would require:

* Allow players to play over a network.
* Allow for player vs AI
* Allow for AI vs AI.
* Metrics for testing the effectiveness of6 AI.

### Problem Decomposition

To allow for the program to meet the functionality required, The following problems must be solved:

* A GUI to obtain input
* Algorithm to determine when a game of Hex is complete.
* Use of sockets to allow for network communication.
* AI algorithm design.

## Design

### GUI

The GUI of the application was design using JavaFX. This allowed for the creation of aesthetic interactive UI which would have it easy for users to easily navigate the Application.

#### Game Controller

The pieces of the game board were designed in `Inkscape` and then exported to `.png` then to actually. 3 Hex pieces were designed (`RED`, `BLUE`, `EMPTY`) and when they are changed on the board only the link to the Image View is changed. Designing the board in the way it was allowed for the game to be more interactive making it easier for the user to actually input into the program and it also made it easier to test as the positions on the board can be visually seen. A look up was used to find the specific image view that corresponded to the location on the hex board.

### Game Mechanics

The board was stored as an `11 x 11` 2D array of `Enum` s. All empty pieces were donated by `EMPTY` .  When a player places a piece at a location there the location is represented in the 2D array by the name of the player. This allowed for fast access of different locations on the board. Allowing for instant piece placement.  

#### Determining the End of a Game

The method used to determine if a game of hex was complete was as follows:

* Determine if the player has a piece of their own on their side.
* find the neighbours of that piece and the neighbours of neighbours until there are no more neighbours or until there is a path of neighbours to the other side of players.

To increase the speed of this process and ensure the program does not get stuck in a loop of neighbours when a neighbour has been search it is not searched again. This method was used as the game board was implemented as a 2D array so there were no explicit link between each of the pieces, so the hexagonal structure was donated mathematically.

To determine if a game was complete as new class was created. This was implemented as its own class because it allows for all the code which relates to the completion of the game to be stored inside one location. It also allowed for the completion method to be reused by AIs. Also it decreases the size of the Game class. It also encapsulated variables which need to be set to default values every time it is wished to determine if the game is complete.

### Network

To allow the user to play with others over a network there was implementation of sockets. Since the game would have to be implemented as peer to peer. It would not be possible to create a lobby for users to find each other. So to allow for users to find each other there IP addresses have to be displayed. So it was decided to classes would be used for this (`Server`, `Client`). The server would be the person hosting the game displaying their IP address and the Client the person who connecting to the other persons IP address.  Communication between the Client and the Server followed the protocol specification provided by the practical.

To allow the user to both use the GUI and interact with the client at the same time multithreading was used. Receiving of moves was always carried out on another thread so the user could quit while the game was still in progress.

The Network Game was implemented as a sub-class of the game class since if carried out all the same functionality, so the network class was thought of as extending the functionality of the initial class.

### AI: Minimax with Alpha-Beta Pruning





## Testing

## Evaluation

It was found that the addition of “hello” between server and client was useless as it can be known that the connection has been established without actually requiring communication between both parties but to maintain “compatibility” with the specification protocols the initial communication was maintained.

## Conclusion

