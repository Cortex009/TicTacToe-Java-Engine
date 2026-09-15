# 🎮 Tic-Tac-Toe Game Engine (Java OOP)

A highly scalable, object-oriented Tic-Tac-Toe game engine built entirely in Java. This project demonstrates core backend engineering principles, focusing on **SOLID design principles**, **clean architecture**, and practical implementations of **Gang of Four (GoF) Design Patterns**.

## 🚀 Key Features
*   **Dynamic Board Sizing:** Supports standard 3x3 grids as well as scalable NxN dimensions.
*   **Polymorphic Entities:** Seamlessly supports both `Human` and `Bot` players via an abstract base class.
*   **Smart Bot Opponents:** Bots adapt their gameplay based on `EASY`, `MEDIUM`, and `HARD` difficulty settings.
*   **Move Management:** Includes full game-state tracking and the ability to undo moves securely.
*   **Optimized Algorithms:** Replaces inefficient $O(N^2)$ full-board win scanning with targeted $O(N)$ checks based on the last move's coordinates.

## 🛠️ Architecture & Design Patterns Used
This engine was architected to be highly extensible and decouple the game logic from the execution environment. 

*   **Strategy Pattern:** 
    *   **Winning Strategies:** Decouples the win-checking logic into `RowWinningStrategy`, `ColumnWinningStrategy`, and `DiagonalWinningStrategy`. 
    *   **Bot Behaviors:** Allows bot algorithms to be swapped at runtime without modifying the core `Game` object.
*   **Factory Pattern:** 
    *   Utilizes a `BotPlayingStrategyFactory` to dynamically instantiate the correct algorithmic behavior based on the chosen difficulty level enum.
*   **Builder Pattern:** 
    *   The `Game` object uses a static nested `Builder` to ensure secure instantiation. The builder validates game integrity (e.g., matching player counts to board size, enforcing unique symbols, restricting bot counts) before allowing the game state to exist.
*   **MVC Inspiration:** 
    *   Strict separation of concerns between `GameService` (business logic/state mutation) and `GameController` (request delegation).

## 💻 Tech Stack
*   **Language:** Java (Core SE)
*   **Paradigms:** Object-Oriented Programming (OOP)

## ⚙️ How to Run Locally
1. Clone the repository:
   ```bash
   git clone [https://github.com/YOUR_USERNAME/TicTacToe-Java-Engine.git](https://github.com/YOUR_USERNAME/TicTacToe-Java-Engine.git)
