# 1.1.0
Released the source code of the game under AGPL 3.0, making it a FOSS project! Builds are available on GitHub as JAR files, and as Flatpak through Flathub.

Features:
- added TLS to the sockets, along with key+cert generation scripts
- improved security of password storage
- added standalone mode
- added server choice dialog
- removed MySQL as DB backend and replaced it with SQLite, allowing local gameplay without having to run DB service
- created new `Cheat` utility, which gives a lot of gold if the player owns the server

Gameplay:
- rebalanced the rewards towards less grind for units

Fixes:
- fixed the graphical bug making the game unusable on newer Java
- fixed some typo-like bugs, e.g. wrong AI scaling

Cleanups/Development Improvements:
- added Maven build config together with dependencies
- extracted certain amount of constants in order to enable better configurability
- changed a lot of `byte` types across the code to `short`, mostly to allow for a wider range of IDs
- cleaned up repasted code and replaced it with a dependency
- reorganized the code packages
- removed lots of old data
- removed parts of the code related to the applet mode
- removed some incomplete features
- removed existing cheats
