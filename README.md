# Java & OOP Adventure — Student Starter

**Start with your own design.** The files here deliberately do not contain a game engine, combatant hierarchy, future rules, or an instructor solution.

Prerequisites: JDK 25, Maven, Git, terminal/IDE.

```bash
java -version
mvn test
mvn exec:java -Dexec.mainClass=edu.starter.Main
```

If the Maven exec plugin is unavailable, compile the starter directly:

```bash
mkdir -p build
javac -d build src/main/java/edu/starter/*.java
java -cp build edu.starter.Main
```

Choose one theme: Fantasy Dungeon, Space Skirmish, Robot Arena or Creature Tournament.
Read the workshop PDF for the current week and maintain one evolving Git repository all semester.

Utilities: `ConsoleChoice.read()` for safe numeric input, `Checks.that()` and `Checks.within()` for early tests. There are no hidden future requirements in this kit.

## My Project - Java Battle Game
I developed a Java console battle game.
The player must defeat two enemies to win the game.

### Game Rules
The player starts with 100 HP and 5 resources.
There are four possible actions:
1. Attack - Deal 10 damage.
2. Defend - Reduce enemy damage by 5.
3. Special - Deal 15 damage using 3 resources.
4. Recover - Restore 3 resources, up to 10.

After winning the first battle, the player
chooses one reward:

- RECOVERY: Restore 25 HP (maximum 100).
- POWER: Increase basic attack damage by 2.

The player's HP and resources are carried
over to the second battle.

### Project Structure

- Main.java - Controls the game and rewards.
- Battle.java - Manages the battles and actions.
- Player.java - Stores the player's state.
- Enemy.java - Represents the enemies.
- Reward.java - Defines the reward options.

### Testing

I tested the following scenarios:

- Winning the first battle.
- Choosing the RECOVERY reward.
- Choosing the POWER reward.
- Entering an invalid reward choice.
- Losing the first battle.
- Winning the second battle.

Invalid input is rejected and the player
is asked to choose again.

If the player loses the first battle,
the game ends without offering a reward.

### How to Run My Game

Open the project in VS Code.

Run the Main.java file located in:
src/main/java/edu/starter/Main.java
# Design Note – Java Battle Game

I designed my game using several classes, each with a clear responsibility.

The `Player` class stores the player's HP, resources, attack damage, and defense status. The `Enemy` class represents an enemy and manages its HP and attacks. The `Battle` class controls the actions during each battle, while the `Main` class manages the overall game flow, including the two battles and the reward selection.

I chose to use the same `Player` object in both battles. This allows the player's HP, resources, and attack damage to remain consistent throughout the game. The second battle begins only if the player wins the first one.

After the first victory, the player chooses between two rewards. The RECOVERY reward restores HP, while the POWER reward increases basic attack damage. I used an enum called `Reward` to represent these options clearly.

I also used Design by Contract in methods where conditions are important, such as taking damage and applying rewards. These contracts help explain the expected behavior and protect the logic of the game.

I kept the design simple and avoided unnecessary inheritance or interfaces because the game does not require them.