<div align="center">
<pre>
 █████╗ ███████╗████████╗███████╗██████╗  ██████╗ ██╗██████╗ ███████╗
██╔══██╗██╔════╝╚══██╔══╝██╔════╝██╔══██╗██╔═══██╗██║██╔══██╗██╔════╝
███████║███████╗   ██║   █████╗  ██████╔╝██║   ██║██║██║  ██║███████╗
██╔══██║╚════██║   ██║   ██╔══╝  ██╔══██╗██║   ██║██║██║  ██║╚════██║
██║  ██║███████║   ██║   ███████╗██║  ██║╚██████╔╝██║██████╔╝███████║
╚═╝  ╚═╝╚══════╝   ╚═╝   ╚══════╝╚═╝  ╚═╝ ╚═════╝ ╚═╝╚═════╝ ╚══════╝
---------------------------------------------------------------------
Game made with java and javaFX
</pre>

[![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=java&logoColor=white)](https://www.java.com/en/)

</div>

## Description

Asteroids is a component-based arcade-style game developed using Java and JavaFX as part of a university course. The game demonstrates the application of software engineering principles, including interface contracts and component-based design. It features core components such as Player, Enemy, Asteroids, Weapon/Bullet, and Rendering/Drawing. The game challenges players to navigate a spaceship through an asteroid field, destroying asteroids and avoiding collisions.

## Features

-   **Component-Based Architecture:** The game is designed with a component-based architecture, promoting modularity and reusability.
-   **Service Provided Interfaces:** The Player, Enemy, and Weapon components implement service provided interfaces, allowing for updating and removing components without recompilation.
-   **Core Components:** Includes essential game components such as Player, Enemy, Asteroids, Weapon/Bullet, and Rendering/Drawing.
-   **Classic Gameplay:** Experience the timeless fun of the original Asteroids arcade game.
-   **Responsive Controls:** Smooth and precise controls for navigating your spaceship.
-   **Dynamic Asteroid Field:** Asteroids of varying sizes and speeds create a challenging and unpredictable environment.
-   **Score Tracking:** Keep track of your high scores and challenge yourself to improve.
-   **JavaFX Implementation:** Built using JavaFX for a modern and cross-platform experience.

## Running the Project

To run the Asteroids game, you need to have Java and Maven installed on your system. Follow these steps:

1.  **Clone the Repository:**

    ```bash
    git clone https://github.com/silasjul/Asteroids.git
    cd Asteroids
    ```

2.  **Install Dependencies and Build:**

    ```bash
    mvn install
    ```

3.  **Run the Game:**

    ```bash
    mvn exec:exec
    ```

**Note:** Ensure that Maven is correctly installed and configured.
