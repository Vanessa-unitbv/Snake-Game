# Snake Game
A classic Snake game implementation in Java with additional features for player profiles and score tracking.

## Description
This project is a fully-functional Snake game built using Java and Swing. The player controls a snake that grows by eating apples. The game features player profiles, score persistence, and a high-score leaderboard system.

## Features
- Classic Snake gameplay with intuitive controls
- Player profile system for personalized gaming experience
- Persistent score tracking using JSON file storage
- High-score leaderboard to compete with friends
- Special game modes including starting from your highest score
- Clean object-oriented design with interface-based architecture

## Technologies
- Java as the main programming language
- Java Swing and AWT for the graphical user interface
- JSON for data storage and persistence using Jackson library

## Game Controls
- Arrow keys: Control the direction of the snake
- R: Reset the game
- M: Start game from your maximum score
- T: Show high-score leaderboard

## Architecture
The game follows object-oriented design principles with a clear separation of concerns:
- GameObject class as the base for all game elements
- Specialized classes for SnakeHead and SnakeBody
- Board class managing the game logic and rendering
- Interface-based design for better maintainability

## Future Improvements
- Additional power-ups and obstacles
- Different difficulty levels
- Custom snake skins
- Sound effects and background music
