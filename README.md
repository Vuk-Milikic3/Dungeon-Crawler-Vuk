
Dungeon Crawler – User Stories

Title

Introduction to the Game as a New Player

Description

As a new player, I want to be greeted with a welcome message and a short story so that I understand the premise of the game and my goal.

When the game is started for the first time, the user is shown a greeting message and a narrative.

The narrative must include:
•	The name of the place (e.g., “The Sunken Labyrinth”).
•	The role or purpose of the player (e.g., “defeat the evil”).
•	The main goal (e.g., “traverse the labyrinth, defeat the evil”).

After the narrative is displayed, the player is prompted to enter their first command.
The greeting text should be clearly separated from subsequent game prompts.

⸻

Title

Show Game Status

Description

As a player, I want to see my status (HP, attack, inventory count, current room) at any time so that I can track my progress.
As a player, I want to see a list of my items with the command inventory.

Acceptance Criteria
•	Command status shows current/max HP, attack, room name, inventory count.
•	After each action, a one-line summary (HP, room) is displayed.
•	Output for items: line by line → name, type (weapon/potion/key), effect.

⸻

Title

Communicate End of Game

Description

As a player, I want to know when I have won or lost so that I understand when the story ends.

Acceptance Criteria
•	At HP ≤ 0: “You have fallen … (restart hint)”.
•	At victory condition: “You have won … (restart hint)”.
•	After this, no further commands are processed except restart / quit.

⸻

Title

Movement Between Rooms

Description

As a player, I want to move using n/s/e/w to explore the dungeon.

Acceptance Criteria
•	Valid exits: movement succeeds, new room confirmed.
•	Invalid direction: “There is a wall.”
•	Map is an internal graph; no diagonal movement.

⸻

Title

Describe Room

Description

As a player, I want to see a room description when entering or using look, including visible enemies and items.

Acceptance Criteria
•	Show: room name, flavor text, list of enemies (name, HP), list of items (name, short).
•	Command look repeats the current description.

⸻

Title

Find & Pick Up Items

Description

As a player, I want to pick up items in the room (take <item>) so they move into my inventory.

Acceptance Criteria
•	If item is present: it goes into inventory, disappears from room.
•	If not present: clear error message.
•	Inventory capacity configurable (e.g., 10).

⸻

Title

Use Item (Potion)

Description

As a player, I want to use a potion (use potion / use <item>) to restore HP.

Acceptance Criteria
•	Heals a defined amount (e.g., +20, capped at max HP).
•	One-time items are removed after use.
•	Error message if item is not in inventory.

⸻

Title

Equip Weapon

Description

As a player, I want to equip a weapon (equip <weapon>) to increase my attack.

Acceptance Criteria
•	Exactly one active weapon; switching resets attack.
•	status shows active weapon and resulting attack.

⸻

Title

Drop / Remove Items

Description

As a player, I want to remove items from my inventory (drop <item>) to free up space.

Acceptance Criteria
•	Item disappears (or optionally placed on the floor).
•	Confirmation text; error if item does not exist.

⸻

Title

Initiate Combat & Attack

Description

As a player, I want to attack a visible enemy (attack <enemy>) so that damage is dealt based on my attack value.

Acceptance Criteria
•	Damage = base attack + weapon bonus ± configurable randomness.
•	If enemy not present: error message.
•	Combat only if enemy is alive in the room.

⸻

Title

Enemy Counterattack & Turn Flow

Description

As a player, I want the enemy to counterattack after my attack so combat feels dynamic.

Acceptance Criteria
•	After the player’s move: enemy move (damage to player HP).
•	Each round ends with a summary: damage dealt, remaining HP.
•	Correct combat technique required for success (blocking and countering enemy attacks).
•	If one side dies, combat ends immediately with a fitting message.

⸻

Title

Show HP During Combat

Description

As a player, I want to see both my HP and the enemy’s HP during combat.

Acceptance Criteria
•	Each round shows: “You: X/Y HP | : A/B HP”.

⸻

Title

Defeat Enemy & Loot

Description

As a player, I want defeated enemies to visibly die and possibly drop loot.

Acceptance Criteria
•	At 0 HP: “ is defeated.”
•	Drop table: 0..N items appear in the room or go directly to inventory (consistent design).

⸻

Title

Victory Condition

Description

As a player, I want a clear victory condition (e.g., reach end room or defeat boss) so the goal is clear.

Acceptance Criteria
•	Victory is triggered by exactly one defined condition.
•	A message announces the ending and displays stats (fights, items used).

⸻

Title

Error Feedback

Description

As a player, I want clear error messages for invalid actions so I understand what is possible.

Acceptance Criteria
•	Each error message suggests 1–2 valid alternatives (e.g., “Type help”).

⸻

Title (Optional)

Artifacts

Description

As a player, I want to find artifacts that act as keys to unlock new rooms and discover new techniques and weapons.

Acceptance Criteria
•	Artifacts can be found in the dungeon.
•	Artifacts can open doors.

⸻

Title (Optional)

Team

Description

As a player, I want to meet companions on my journey who can help me and trade weapons.

Acceptance Criteria
•	I can find or free friends along the way.
•	I can trade items with them.

