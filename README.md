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

Asteroids is a component-based arcade-style game i developed using Java and JavaFX for a university course. The game challenges players to survive in an alien infested space in a spaceship through an asteroid field. The challenge for this project was using ServiceLoaders to dynamically load game components. I used cool free asset-packs made by [Foozle](https://foozlecc.itch.io/). I had a blast making this game!

<div align="center">
 <img src="https://github.com/user-attachments/assets/f106795b-9884-4df3-ba7a-c3480254afd2" width="600"/>
</div>

## Requirements
These were the must-have requirements for the game, given by our teacher:
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
    mvn clean install
    ```

3.  **Run the Game:**

    ```bash
    mvn exec:exec
    ```

**Note:** Ensure that Maven is correctly installed and configured.
