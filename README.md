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

Asteroids is a component-based arcade-style game developed using Java and JavaFX as part of a university course. The game challenges players to survive in an alien infested space in a spaceship through an asteroid field. The challenge for this project was using ServiceLoaders to dynamically load game components. I used a cool free asset-packs made by [Foozle](https://foozlecc.itch.io/). I had a blast making this game!

<div align="center">
<img src="https://github.com/user-attachments/assets/6879d906-f0f3-4989-8a0d-5fda8ae7eb85" width="500">
</div>

## Requirements

1. The game must include Player, Enemy, Asteroids, Weapon/Bullet and Rendering/Drawing (Core) components.
2. The Player, Enemy and Weapon components must implement service provided interfaces that allow the components to be updated and removed without recompilation.

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
