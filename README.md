# Java Project – Geometric Shapes, SVG and JUnit with Ant

> 🎓 **Academic project**: this repository contains a project completed as part of a **Java course** (object-oriented programming). It is published as part of my portfolio to showcase my skills in Java, OOP, Ant and JUnit.

This Java project was completed over two days with the goal of discovering **Apache Ant** and **JUnit**, while testing our Java knowledge and our understanding of OOP. It implements several features around manipulating geometric shapes, visualizing them, and running automated tests.

## 📦 Project Structure

- `src/`: Java source code
- `output/`: exported SVG files
- `docs/javadoc/`: Javadoc documentation (after generation)
- `libs/`: JUnit library used
- `build.xml`: Ant build configuration file

## 🧰 Prerequisites

- A recent JDK (Java 8 or higher)
- [Apache Ant](https://ant.apache.org/)

## 🚀 Build & Run

Everything is handled through **Ant** using the `build.xml` file.

### 🔧 Compile the project

```bash
ant compile
```

### ▶️ Run one of the targets

The `build.xml` file contains **three main targets**:

#### 1. `run.gui` – Interactive graphical interface

```bash
ant run.gui
```

- Launches a graphical interface.
- Lets you draw **geometric shapes**.
- Lets you apply **gravity** to the objects.
- Lets you **export the scene as SVG**.

![GUI screenshot](images/capture_gui.png)

#### 2. `run.jo` – Generate an SVG drawing of the Olympic rings

```bash
ant run.jo
```

- Generates an SVG illustration of the **Olympic flag**.
- The file is exported to `output/anneaux.svg`.

![Olympic rings SVG screenshot](images/anneaux.svg)

#### 3. `run.trier` – Circle sorting

```bash
ant run.trier
```

- Runs a program that sorts **circles** according to a given criterion using a bubble sort.

## ✅ Unit Tests

```bash
ant test
```

- Runs the **automated JUnit tests**.
- The tests are simple and few in number (educational goal: getting started with JUnit).

## 📚 Javadoc Generation

```bash
ant javadoc
```

- Generates the **HTML documentation** in the `docs/javadoc/` folder.

## ⚠️ Notes

- This project was completed in a fast-paced learning context.
- The tests are **limited** and have an **educational** purpose rather than full coverage.
- The main goal was to **discover Ant and JUnit** in a real-world context.

---

## 👨‍💻 Author

Baptiste JOUBERT
