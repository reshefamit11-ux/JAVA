# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project context

This is a student repository for a semester-long "Java & OOP Adventure" workshop course. It starts from an instructor kit that deliberately has no game engine, combatant hierarchy or solution; the student builds and evolves their own design week by week (per a workshop PDF that isn't in the repo) in this one Git repo. Respect the student's design choices. Don't introduce architecture, class hierarchies or "future" features the student hasn't asked for.

## Commands

Requires JDK 25 (`maven.compiler.release=25`) and Maven. Tests use JUnit 5 through Surefire.

```bash
mvn test                                              # run all tests
mvn test -Dtest=StarterTest                           # one test class
mvn test -Dtest=StarterTest#clampingIsCorrect         # one test method
mvn exec:java -Dexec.mainClass=edu.starter.Main       # run the game (interactive, reads stdin)
```

The exec plugin is not declared in `pom.xml`. If it isn't available, compile and run directly:

```bash
mkdir -p build
javac -d build src/main/java/edu/starter/*.java
java -cp build edu.starter.Main
```

## Architecture

Everything is in the single package `edu.starter`.

- **Starter utilities (from the kit):** `ConsoleChoice.read(Scanner, min, max)` reads a line safely and returns `-1` for invalid or out-of-range input. Its own comment says battle rules must never go in it. `Checks.that()` and `Checks.within()` are small assertion and clamp helpers. `StarterTest` covers only these two utilities.
- **Current game:** `Main` creates a `Game2` and calls `play(Scanner)`. `Game2` is a self-contained turn-based battle loop: the player picks Attack, Defend, Special or Recover, then the enemy counterattacks if the action was valid. All player and enemy state lives in `Game2` as primitive fields.
- **In-progress OOP refactor (not wired in yet):** `Player`, `Enemy` and `Battle` pull that state and those rules into objects. `Player` owns HP, the resource pool (capped at 10), defending and damage reduction. `Enemy` owns its name and HP. `Battle` holds action logic such as `special()`. `Game2` doesn't use these classes yet, so the same rules (damage 10/15, special cost 3, recover +3, defend −5) are currently duplicated. When changing game rules, check both places, or finish moving `Game2` onto the new classes.
