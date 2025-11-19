
# **ovOOP – Train Travel Simulation System**

A fully terminal-based Java project featuring **account management**, **train routing**, **dynamic pricing**, **ASCII travel maps**, **color-themed UI**, and persistent **JSON-based data storage**.

---

## 🚆 **Overview**

**ovOOP** is an object-oriented travel simulation system that allows users to:

* Create and manage accounts
* Log in and log out
* Travel between fictional cities
* Automatically calculate routes and distances
* Receive dynamic fare prices based on:

  * Time of day
  * Holidays
  * Class type
  * Currency conversion
* Visualize routes using ASCII-generated maps
* Manage personal balance (deposit/withdrawal)
* Customize UI color themes

All data—including accounts, balances, and travel lines—is stored in external JSON files.

---

## 🗂 **Project Structure**

```
ovOOP/
│
├── AccountSystem.java
├── BalanceSystem.java
├── ColorSystem.java
├── DataSystem.java
├── Main.java
├── MapGenerationSystem.java
├── MenuSystem.java
├── OptionsSystem.java
└── TravelSystem.java
```

### **Key Directories**

```
data/
│
├── AccountInfo.json      # User accounts + preferences
└── TrainLines.json       # Station graph + distances
```

---

## 🔑 **Features**

### **🧍 Account System**

* Create accounts with unique usernames
* Login/logout
* User preferences saved to JSON:

  * Color palette
  * Current location
  * Balance
  * Travel class
  * Currency conversion

### **💸 Balance Management**

* Deposit or withdraw funds
* Prevents negative balances
* Live balance formatting

### **🗺 Travel & Routing**

* Implementation of a Dijkstra-style shortest path algorithm
* Each city belongs to one or more train lines
* Distance calculated using sequential JSON data
* Displays:

  * Route path
  * Line transfers
  * Total distance

### **💵 Dynamic Price Calculation**

Price depends on:

| Factor                 | Influence                                        |
| ---------------------- | ------------------------------------------------ |
| Distance               | Base fuel cost                                   |
| Random factor          | ±30% variation                                   |
| Business/Economy class | 1.7× or 0.9×                                     |
| VAT (9%)               | Included                                         |
| Margin (20%)           | Included                                         |
| Holidays               | Special discounts (Christmas, Sinterklaas, etc.) |
| Time of day            | Peak/off-peak                                    |

### **🖼 Terminal Map Renderer**

* ASCII-based visual map
* Colored cities
* Colored roads
* Shows sequential movement along route

### **🎨 Custom UI Colors**

Users can select between 16 terminal colors for primary & secondary themes.

---

## 🚀 **How to Run**

### **Requirements**

* Java 17+
* Gson (included via import, add to your classpath if needed)

### **Running**

```bash
javac ovOOP/*.java
java ovOOP.Main
```

---

## 📁 **JSON Data Files**

### **AccountInfo.json**

Stores all user data.
Created automatically if missing.

### **TrainLines.json**

Defines:

* Train lines
* Stations
* Distances between them

Required for routing to function properly.

---

## 📌 **Controls / Usage**

1. Launch application
2. Log in or sign up
3. Access the main menu:

   * Travel
   * Settings
   * Balance
   * Credits
4. Select destination
5. View route, distance, and fare
6. Optionally print invoice
7. Visual travel map is generated

---

## 🧩 **Technologies Used**

* **Java OOP**
* **Gson** for JSON parsing
* **ANSI escape color codes** for UI
* **Custom pathfinding logic**
* **ASCII graphics rendering**

---

## 👥 **Credits**

* **Developers:** Morris van Uden, Max Viehöfer
* **Teacher:** Erik Seldenthuis
* **Class:** TIA4V1B
* **Website:** Coming soon
* **GitHub:** [https://github.com/m3v64/ovOOP](https://github.com/m3v64/ovOOP)


