# FBCTF (AKA FBMCCTF)
## A Flashback to MCCTF

This project is a complete remake of MCCTF based on my own knowledge of the gamemode and with help from others.
This is currently being made as a spigot plugin for 1.8.8/1.8.9 and is being completed slowly in my own time, so it may be a while before its complete.
If you are interested in helping contribute to this project, let me know! (My discord is [@fivervalley](https://discordapp.com/users/583603987379519528))

## What was MCCTF
Capture the Flag (CTF) is a fast paced gamemode with community made maps and 14 different classes with unique abilities to choose from. The goal is to capture the enemy's flag while protecting your own. You'll need to capture, defend, and support your team to win.

Brawl MCCTF Official webpage: https://www.mcbrawl.com/games/capturetheflag/


## Introduction
This is the source of the CTF plugin, utilising the Spigot 1.8.8 API.

The Eclispe project files are included to enable easy setup for your own personal use.

## Features

### Gamemodes
**Capture the Flag**

Standard mode, as described above, set number of steak per kit use, which heals 4 hearts instantly upon use

**Soup PvP**

Additional custom mode, implements all the classic CTF kits, however replaces steak with soup which heals 3 hears upon use, and allows the CTF kits to be played in a free for all PvP environment.

### CTF Kits
All original kits will aim to be implemented by the time this plugin is complete, but there are some kits which will be prioritised. This is based on a number of factors, primarily being their individual complexity and my personal interest to each kit.

The full list of the kits are displayed below, all kits with a tick are implemented and working, and all kits with a cross have not yet been implemented.

- [x] Heavy
- [x] Soldier
- [x] Medic
- [x] Archer
- [ ] Assassin
- [ ] Chemist
- [ ] Dwarf
- [ ] Elf
- [ ] Engineer
- [ ] Mage
- [ ] Necro
- [ ] Ninja (WIP)
- [ ] Pyro
- [ ] Scout
- [ ] Fashionista

### In-game commands
Commands for using the plugin can be found with descriptions in the `plugin.yml`, which is located under the path: `./src/main/resources/plugin.yml`.

## How to Compile
This plugin uses Apache Maven to build the source code using the required dependencies. 
You will need to have JDK 1.8 and Maven 3.x

In the top directory (Containing the `pom.xml`), run `mvn clean install`.
Maven should generate a jar file which will be located within `./target`

Place this jar file within the `plugins` folder and then startup your server, the plugin should then work


# TODO
## Next Features
- Allow ninja to heal by sneaking - copy medic heal mechanic, but make it only work when sneaking
## Bugs to Fix
- When switching from a class with armour to one without, the armour will remain equipped. e.g. switching from medic to ninja, the ninja will still have golden armour
- Make Ninja flashbomb not affect the thrower
- Sometimes soup doesn't use itself up.