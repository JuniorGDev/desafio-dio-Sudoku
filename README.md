
# 🧩 Sudoku Game - Java GUI & CLI

[![Java](https://img.shields.io/badge/Java-17-blue.svg)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

## 🌍 English

This project is a Java-based implementation of the classic **Sudoku game**, featuring both **graphical user interface (GUI)** and **command-line interface (CLI)** modes.

You can run the game using:
- `Main.java` (terminal/CLI mode)
- `UIMain.java` (graphical mode)

### 🧠 How it Works

The project accepts input data via command-line arguments in the format:

```
x,y;value,isFixed
```

Example:
```
0,0;4,false 1,0;7,false 2,0;9,true ...
```

### ▶️ How to Run

```bash
javac -d ./bin ./src/br/com/dio/**/*.java
java -cp ./bin br.com.dio.UIMain 0,0;4,false 1,0;7,false ...
```

---

## 🇧🇷 Português

Este projeto é uma implementação em Java do clássico jogo **Sudoku**, com duas formas de execução:

- Pelo terminal com a classe `Main.java`
- Com interface gráfica via `UIMain.java`

### 💡 Como Funciona

Os dados do tabuleiro são passados como argumentos da linha de comando, no seguinte formato:

```
x,y;valor,fixo
```

Exemplo:
```
0,0;4,false 1,0;7,false 2,0;9,true ...
```

### ▶️ Como Executar

1. Compile o projeto:

```bash
javac -d ./bin ./src/br/com/dio/**/*.java
```

2. Execute a versão gráfica:

```bash
java -cp ./bin br.com.dio.UIMain 0,0;4,false 1,0;7,false ...
```

---

## 🗂 Estrutura do Projeto

```
src/
└── br/com/dio/
    ├── models/
    ├── service/
    ├── ui/
    │   └── custom/
    │       ├── button/
    │       ├── frame/
    │       ├── input/
    │       ├── panel/
    │       └── screen/
    ├── utils/
    ├── Main.java      ← CLI (linha de comando)
    └── UIMain.java    ← Interface gráfica
```

---

## 👤 Desenvolvido por | Developed by

**Junior G**
