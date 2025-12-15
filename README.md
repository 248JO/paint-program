# Paint Program

A simple, interactive drawing application built in Java, demonstrating object-oriented design principles and graphical user interface (GUI) development. This program allows users to draw various 2D shapes.

## Features

* **Interactive Drawing:** Users can draw and manipulate shapes directly on the canvas using the mouse.
* **Object-Oriented Design (OOD):** The program uses a structured OOD approach, defining base classes for shapes and specialized classes for specific drawing elements (lines, ovals, rectangles).
* **Supported Shapes:**
    * Lines
    * Ovals
    * Rectangles
* **GUI Tools:** Implements a user interface with dedicated tool buttons for selecting shapes, colors, and fill options.
* **Extensible Architecture:** Designed using object-oriented principles to make it easy to add new shape types in the future.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine.

### Prerequisites
You will need the Java Development Kit (JDK) installed on your system to compile and run the application.

### Installation and Execution
1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/248JO/paint-program.git](https://github.com/248JO/paint-program.git)
    cd paint-program
    ```

2.  **Compile the Java files:**
    Assuming all `.java` files and image assets are in the root directory:
    ```bash
    javac *.java
    ```

3.  **Run the application:**
    The main class that starts the GUI application is `PaintProgram.java`.
    ```bash
    java PaintProgram
    ```

## Project Structure
The core functionality is split into classes following the object-oriented structure:

| File Name | Description |
| :--- | :--- |
| `PaintProgram.java` | The main class containing the `main` method for the application. |
| `PaintFrame.java` | Manages the main application window, menu, and layout. |
| `PaintPanel.java` | The custom drawing panel component where all shapes are rendered and mouse events are handled. |
| `Shape.java` / `Shape2D.java` | Abstract or base classes that define the common interface and properties for all drawable shapes. |
| `Line.java` | Defines the behavior and drawing logic for line objects. |
| `Oval.java` | Defines the behavior and drawing logic for oval objects. |
| `Rectangle.java` | Defines the behavior and drawing logic for rectangle objects. |
| `*Icon.png` | Image assets used for the toolbar buttons (e.g., color, fill, line, oval, rectangle). |

## Built With
* **Java**
* **Java Swing/AWT** - For the graphical user interface components.
