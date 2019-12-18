## Overview
This solution represents classic paper-rock-scissors for two people with possible scalability. 
By input from console, user can choice what symbol to display: P - paper, R - rock, S - scissors   

## Technology and structure overview 
In this assignment I used Guice: a lightweight DI framework from google. 

Starting class is `com.imc.kabanov.PaperRockScissorGameStarter` that combines required game configurations, 
binds all dependencies and start `PaperRockScissorGame` that represents a series of N PaperRockScissor games.
Number of games `N` is configured in `game.properties` in resources.
 
### Packages:
#### Configuration
All configurations are located in package `configuration`.
 - `ConsoleInputOutputModule` - configures all input and output goes to console
 - `GamePropertiesModule` - configures the loading of game properties from resources
 - `TwoPlayersGameModule` - configures game and strategy for two players
 - `PlayerComputerModule` - configures one player to be a human and another - a computer 

#### Data
Contains data game objects

#### Game
Contains logic for a Human and Computer. Additionally it has the logic of the single game for two players 

#### IO
Contains abstractions for input/output operations, and implementation of console IO operations. 
Class `GameEventNotificator` contains all the actions that should be displayed in UI, what allows to 
easily test what going to be shown in UI

#### Strategy 
Contains the strategy of calculating the winner from several game symbols.

#### Validation
Contains methods to validate game parameters 

 
## How to run 
Game can be started with a single command: 
```
gradlew runApplication -q --console=plain
``` 
flags: "-q --console=plain" hides gradle build status from console that interferes with user input

