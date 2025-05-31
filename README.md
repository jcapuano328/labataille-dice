# labataille-dice
A simple Android app for La Bataille

## Purpose
After several variations on this theme, I eventually decided to "return to formula" and focus an app on the "simple" things. This app is a very spare "automation" of the morale, fire combat and melee combat "charts" for La Bataille: these are the same across all titles (and even rule systems) in the La Bataille ecosystem. Gone are the details of the Terrain Effects, Carre Realization and Morale Effects charts: use the app to "roll the dice" and then refer to the game specific charts. 

## Why this approach?
I was tired of transposing all of the cumbersome (and sometimes voluminous) information from "charts" to "data" that could be used by the original app. I was tired of re-releasing the app with new titles (though those are in short supply) or changes to game specific charts. 

And more practically, the tech stack I used to build the original La Bataille apps aged out of existence: that meant a re-write which led to a re-evaluation which led to this.

Also, I didn't want to detract from the "game on the table", so no sound effects or animations to slow things down: tap, glance and your focus is back on the table, where it belongs. (Watch your flanks!)

# Interface
The app will assist with the resolution of the 3 core features of La Bataille: fire, melee and morale.

## Navigation
Pretty simple: a "bottom bar" navigation scheme for the 4 activities:
- Fire
- Melee
- Morale
- General

Tap the nav icon to view the activity.

## Roll Dice Button
In the middle of the nav bar is a "button" that will roll the dice and apply the results to the current activity. Tap the button to roll all of the dice for the activity.

## Dice
Each activity has at least 1 set of dice. 

Each die can be tapped to increase it's value by 1. There is a "rollover" but there is no "carry the one". Meaning, when a die of 6 is tapped the value only "rolls over" to 1; the corresponding "left die", if there is one, is not incremented by 1 (the "carry").

When a die changes for any reason, the results are determined and displayed anew.

### Dice Groups
Much of La Bataille is resolved using two 6 sided dice: fire, melee and morale. The 2 dice comprise a "group" that represents a single "dice value" which can be modified as a whole.

### Dice Modifiers
Under a set of dice there is at least one row of "modifier buttons". Tapping the button will adjust a "dice group" by the selected value: increase or decrease.

In general, the "blue buttons" will adjust the "combat dice" while the "purple buttons" will adjust the "morale" dice.

## Fire
The "fire combat" chart is represented here, along with leader casualty and morale results. The idea is to present the possible outcomes based on the dice rolled.

### Dice
- The first 2 dice are the "combat dice".
- The middle 3 dice are the "leader casualty dice":
  - First die is the Casualty type (head, chest, etc)
  - Second 2 dice are the Casualty duration (if applicable)
 - The last 2 dice are the "morale dice".

### Results
The possible combat results are presented, with the odds and losses that are possible for the combat dice roll. The player will "calculate" (or estimate) the odds if the dice suggest it is necessary. (Lots of fire combat dice rolling in La Bataille with no result).

The possible morale results are presented, with the morale value required to pass the morale dice roll and the list of results for typical morale adjustments.

The leader casualty is the only thing that is "certain": if the combat dice call for a casualty icons will indicate the severity and duration the leader (counter) is out of the game.

## Melee
This is a little more involved, but not much. It is divided into an "odds determination" section and a "resolution" section. 

### Odds Determination
At the top is the "combat odds calculator", since determining melee odds in La Bataille is a bit of a chore. This is just a convenience for the player; the odds do not affect the behavior of the app in any way.

Melee Values for the attacker and defender may be entered directly into the boxes and the odds will be calculated. Also, the "calculator" button may be used to bring up the "proportional melee value" calculator to determine both attacker and defender melee values.

#### Proportional Melee Value Calculator
- Tap the numbers to enter the value for the unit size: Tap the SIZE button.
- Tap the numbers to enter the value for the losses the unit has incurred: Tap the LOSS button.
- Tap the numbers to enter the value of the unit melee (original): Tap the STR (strength) button.

The top left will present the calculated proportional melee value. 

- Tap the numbers to enter a melee value: Tap the + (plus) button.

The top left will adjust calculated proportional melee value by the entered value. (This is a convenience to allow quick entry of an unmodified unit melee value).

- Tap a modifier button, x1/3, x1/2, etc as necessary to represent Disorder, Tired, etc.

The top left will adjust the calculated proportional melee value accordingly.

- Tap the ATT or DEF button to "add" the calculated proportional melee value to either the attacker or defender total on the Melee screen.

Repeat as necessary to determine the full melee value for the attackers and defenders involved in the combat.

### Resolution
A "tabbed navigator" permits switching between the Pre-Melee Morale Check resolution and the Melee Combat Resolution activities.

#### Pre Melee Morale Check

##### Dice
There are 2 groups of dice: Attacker morale and Defender morale. The blue modifier buttons adjust the Attacker morale dice while the purple modifier buttons adjust the Defender morale dice.

The possible morale results for each side are presented, with the morale value required to pass the morale dice roll and the list of results for typical morale adjustments.

#### Melee Combat
This behaves exactly the same as Fire Combat, except for the differences in the available combat results. (Though for some reason that escapes me now I opted not to include morale modifier buttons).

## Morale
We've addressed the presentation of Morale dice and results 3 times now: this is exactly the same. 

The purpose of this screen is assist with all of the ancillary morale checks that occur, such as:
- Stand before Charge
- Rally
- Etc, Etc, Etc (lots of morale checks in La Bataille)

## General
This is just a set of dice that can be used for "other" purposes, such as:
- Cavalry recall
- Artillery limber
- Etc, Etc, Etc (lots of dice rolling in La Bataille)