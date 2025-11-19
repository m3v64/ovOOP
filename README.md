# **🚂 ovOOP – Train Travel Simulation System**

[![Java](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-Custom-blue.svg)](LICENSE)
[![Status](https://img.shields.io/badge/Status-Active-success.svg)]()

A fully terminal-based Java project featuring **account management**, **train routing**, **dynamic pricing**, **ASCII travel maps**, **color-themed UI**, and persistent **JSON-based data storage**.

---

## 📋 **Table of Contents**

- [Overview](#-overview)
- [Features](#-features)
- [Prerequisites](#-prerequisites)
- [Installation](#-installation)
- [How to Run](#-how-to-run)
- [Project Structure](#-project-structure)
- [Usage Guide](#-usage-guide)
- [Technical Details](#-technical-details)
- [JSON Data Files](#-json-data-files)
- [Screenshots](#-screenshots)
- [Troubleshooting](#-troubleshooting)
- [Contributing](#-contributing)
- [Technologies Used](#-technologies-used)
- [Credits](#-credits)
- [License](#-license)

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

### **✨ Why ovOOP?**

- **Fully Object-Oriented Design**: Clean separation of concerns with modular systems
- **No GUI Framework Required**: Pure terminal-based interface with ANSI color support
- **Smart Pathfinding**: Implements Dijkstra-style algorithm for optimal route calculation
- **Realistic Pricing**: Dynamic fare calculation considering multiple real-world factors
- **Persistent Storage**: All user data and preferences saved automatically
- **Customizable Experience**: Personalize your UI with 16 different color themes

---

## 🗂 **Project Structure**

```
ovOOP/
│
├── src/
│   └── ovOOP/
│       ├── AccountSystem.java          # User authentication and account management
│       ├── BalanceSystem.java          # Financial transactions and balance tracking
│       ├── ColorSystem.java            # ANSI color theme management
│       ├── DataSystem.java             # JSON data persistence layer
│       ├── Main.java                   # Application entry point
│       ├── MapGenerationSystem.java    # ASCII map rendering engine
│       ├── MenuSystem.java             # UI navigation and menu displays
│       ├── OptionsSystem.java          # User preferences and settings
│       └── TravelSystem.java           # Route calculation and travel logic
│
├── data/
│   ├── AccountInfo.json                # User accounts and preferences
│   ├── Cities.json                     # City information and metadata
│   └── TrainLines.json                 # Station graph and distances
│
├── lib/
│   └── gson-2.13.2.jar                 # JSON parsing library
│
├── LICENSE                             # License file
└── README.md                           # This file
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

## 📦 **Prerequisites**

Before running ovOOP, ensure you have the following installed:

- **Java Development Kit (JDK) 17 or higher**
  - Check version: `java -version` and `javac -version`
  - Download from: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)

- **Terminal with ANSI color support**
  - ✅ Linux/Unix terminals (built-in)
  - ✅ macOS Terminal or iTerm2
  - ✅ Windows 10+ Command Prompt or PowerShell
  - ✅ Windows Terminal (recommended for best experience)

- **Gson Library** (included in `lib/` directory)

---

## 💾 **Installation**

### **Step 1: Clone the Repository**

```bash
git clone https://github.com/m3v64/ovOOP.git
cd ovOOP
```

### **Step 2: Verify Java Installation**

```bash
java -version
javac -version
```

Both commands should show version 17 or higher.

### **Step 3: Verify Gson Library**

Ensure the Gson library is present:

```bash
ls -l lib/gson-2.13.2.jar
```

If missing, download from [Gson GitHub Releases](https://github.com/google/gson/releases) and place it in the `lib/` directory.

### **Step 4: Create Binary Directory**

```bash
mkdir -p bin
```

---

## 🚀 **How to Run**

### **Method 1: Compile and Run (Recommended)**

```bash
# Compile all Java files
javac -cp "lib/*:src" src/ovOOP/*.java -d bin/

# Run the application
java -cp "bin:lib/*" ovOOP.Main
```

**Note for Windows users:** Replace colons (`:`) with semicolons (`;`) in classpath:

```cmd
javac -cp "lib/*;src" src/ovOOP/*.java -d bin/
java -cp "bin;lib/*" ovOOP.Main
```

### **Method 2: Using Source Directory**

```bash
# Compile directly in source directory
javac -cp "lib/*" src/ovOOP/*.java

# Run from source
java -cp "src:lib/*" ovOOP.Main
```

### **Quick Start Script (Linux/Mac)**

Create a `run.sh` file:

```bash
#!/bin/bash
mkdir -p bin
javac -cp "lib/*:src" src/ovOOP/*.java -d bin/
java -cp "bin:lib/*" ovOOP.Main
```

Make it executable and run:

```bash
chmod +x run.sh
./run.sh
```

---

## 🔧 **Technical Details**

### **Architecture Overview**

ovOOP follows a **modular, object-oriented architecture** with clear separation of concerns:

```
┌─────────────────┐
│   Main.java     │  Entry point
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  MenuSystem     │  UI Controller
└────────┬────────┘
         │
    ┌────┴────┬──────────┬──────────┬──────────┐
    ▼         ▼          ▼          ▼          ▼
┌─────────┐ ┌──────┐ ┌────────┐ ┌───────┐ ┌────────┐
│ Account │ │Travel│ │Balance │ │Options│ │ Color  │
│ System  │ │System│ │ System │ │System │ │ System │
└────┬────┘ └───┬──┘ └────┬───┘ └───┬───┘ └────┬───┘
     │          │         │         │         │
     └──────────┴─────────┴─────────┴─────────┘
                        │
                        ▼
                ┌──────────────┐
                │ DataSystem   │  JSON Persistence
                └──────────────┘
```

### **Pathfinding Algorithm**

The routing system implements a **modified Dijkstra's algorithm**:

1. **Graph Representation**: Cities are nodes, train lines are edges with distance weights
2. **Path Discovery**: Finds shortest path considering line transfers
3. **Distance Calculation**: Uses sequential JSON data for accurate measurements
4. **Route Optimization**: Minimizes both distance and number of transfers

**Key Features:**
- O(V²) time complexity for small graphs (suitable for city networks)
- Handles multiple train lines per station
- Supports bidirectional travel
- Identifies necessary line transfers

### **Dynamic Pricing Engine**

The fare calculation system considers multiple factors:

```java
// Simplified pricing formula
basePrice = distance × FUEL_COST_PER_KM
randomFactor = 0.7 to 1.3 (±30% variation)
classMultiplier = businessClass ? 1.7 : 0.9
VAT = 1.09 (9%)
MARGIN = 1.20 (20%)
holidayDiscount = isHoliday() ? 0.9 : 1.0
timeMultiplier = isPeakHour() ? 1.2 : 0.9

finalPrice = basePrice × randomFactor × classMultiplier 
             × VAT × MARGIN × holidayDiscount × timeMultiplier
```

### **Data Persistence**

Uses **Gson** for JSON serialization/deserialization:

- **AccountInfo.json**: Stores user objects with preferences
- **TrainLines.json**: Graph structure with stations and distances
- **Cities.json**: City metadata and information

**Advantages:**
- Human-readable format
- Easy to modify and debug
- No database setup required
- Cross-platform compatibility

### **ASCII Map Generation**

The map rendering system:

1. Calculates coordinate grid based on city positions
2. Renders cities as colored points
3. Draws connecting lines for train routes
4. Animates travel by sequentially highlighting route segments
5. Uses ANSI escape codes for colors

**Color System:**
- Supports 16 standard ANSI colors
- User-customizable primary and secondary palettes
- Persistent color preferences per account

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

**Example Structure:**
```json
{
  "lines": [
    {
      "name": "Blue Line",
      "stations": ["CityA", "CityB", "CityC"],
      "distances": [25, 30]
    }
  ]
}
```

### **Cities.json**

Contains metadata about each city in the network.

---

## 📸 **Screenshots**

> **Note**: This is a terminal-based application. Screenshots show the colorful ASCII interface.

### Main Menu
```
╔═══════════════════════════════════════╗
║         OVOOP TRAVEL SYSTEM          ║
╚═══════════════════════════════════════╝

  [1] Travel
  [2] Settings
  [3] Balance
  [4] Credits
  [0] Logout

Current Location: Amsterdam
Balance: €482.50
```

### Travel Map Animation
```
    ○ Utrecht
    │
    ├──[Blue Line]──┐
    │               │
    ○ Amsterdam  ○ Rotterdam
                    │
                    └──[Red Line]──○ Den Haag
```

> 💡 **Demo Video**: Coming soon!

---

## 🔍 **Troubleshooting**

### **Problem: "Class not found" error**

**Solution:**
- Ensure you're running from the correct directory
- Check that the classpath includes both `bin` and `lib/*`
- Verify compilation was successful

```bash
# Re-compile
rm -rf bin/*
mkdir -p bin
javac -cp "lib/*:src" src/ovOOP/*.java -d bin/
```

### **Problem: Colors not displaying correctly**

**Solution:**
- **Windows**: Use Windows Terminal instead of legacy Command Prompt
- **Linux/Mac**: Most terminals support ANSI by default
- Check terminal settings for color support

### **Problem: JSON parsing errors**

**Solution:**
- Verify JSON files in `data/` directory are valid
- Check file permissions (read/write access needed)
- Restore backup or create fresh JSON files

```bash
# Validate JSON syntax
python -m json.tool data/AccountInfo.json
```

### **Problem: "Gson not found" error**

**Solution:**
- Verify `lib/gson-2.13.2.jar` exists
- Download Gson if missing:
```bash
cd lib
wget https://repo1.maven.org/maven2/com/google/code/gson/gson/2.13.2/gson-2.13.2.jar
```

### **Problem: Cannot compile on Windows**

**Solution:**
Use semicolons instead of colons in classpath:
```cmd
javac -cp "lib/*;src" src/ovOOP/*.java -d bin/
java -cp "bin;lib/*" ovOOP.Main
```

### **Problem: Route not found between cities**

**Solution:**
- Verify both cities exist in `TrainLines.json`
- Check that there's a connected path between cities
- Ensure train line connections are properly defined

---

## 🤝 **Contributing**

Contributions are welcome! Here's how you can help:

### **Reporting Bugs**

1. Check existing issues first
2. Create a new issue with:
   - Clear description of the problem
   - Steps to reproduce
   - Expected vs actual behavior
   - System information (OS, Java version)

### **Suggesting Features**

1. Open an issue with the `enhancement` label
2. Describe the feature and its benefits
3. Provide examples if possible

### **Code Contributions**

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature-name`
3. Follow existing code style and conventions
4. Test your changes thoroughly
5. Commit with clear messages: `git commit -m "Add: feature description"`
6. Push to your fork: `git push origin feature/your-feature-name`
7. Open a Pull Request

### **Code Style Guidelines**

- Follow Java naming conventions (camelCase for methods, PascalCase for classes)
- Add comments for complex logic
- Keep methods focused and concise
- Update documentation for new features
- Ensure backward compatibility with existing data files

---

## 📌 **Usage Guide**

### **First Time Setup**

1. **Launch the application**
   ```bash
   java -cp "bin:lib/*" ovOOP.Main
   ```

2. **Create your account**
   - Select "Sign Up" from the start menu
   - Enter a unique username
   - Set a secure password
   - Your account will be automatically created and saved

3. **Login**
   - Enter your username and password
   - You'll be taken to the main menu

### **Main Menu Options**

Once logged in, you have access to:

#### **🚆 Travel**
- Select your destination from available cities
- View the calculated route with all transfers
- See the total distance and dynamic fare
- Confirm travel to update your location
- Watch an ASCII animation of your journey

#### **⚙️ Settings**
- Change your UI color scheme (primary and secondary colors)
- Adjust travel class preference (Economy/Business)
- Set currency conversion rate
- Customize your experience

#### **💰 Balance Management**
- View current balance
- Deposit funds
- Withdraw funds (with balance validation)
- Track transaction history

#### **ℹ️ Credits**
- View developer information
- See project details
- Access GitHub repository link

### **Example Travel Session**

```
1. Login as user
2. Check current balance: €500
3. Select "Travel" from main menu
4. Choose destination: "Amsterdam"
5. System calculates:
   - Route: Utrecht → Amsterdam
   - Distance: 45 km
   - Base fare: €15.30
   - Time multiplier: 1.2× (peak hour)
   - Final price: €18.36
6. Confirm travel
7. Watch ASCII map animation
8. New balance: €481.64
9. Current location updated to Amsterdam
```

### **Travel Pricing Example**

For a 100 km journey:
```
Base calculation:
- Distance: 100 km
- Fuel cost: 100 × €0.15 = €15
- Random variation: ±30%
- Class modifier: Business 1.7× or Economy 0.9×
- VAT (9%): +€1.35
- Margin (20%): +€3.00
- Holiday discount: -10% (if applicable)
- Time of day: Peak +20% or Off-peak -10%

Final price: ~€18-32 (varies based on factors)
```

---

## 🧩 **Technologies Used**

* **Java 17+** - Core programming language
* **Gson 2.13.2** - JSON parsing and serialization
* **ANSI Escape Codes** - Terminal color rendering
* **Java Collections Framework** - Data structure management
* **Object-Oriented Programming** - Modular system design
* **Dijkstra's Algorithm** - Shortest path calculation
* **Scanner Class** - User input handling
* **File I/O** - Persistent data storage

### **Key Design Patterns**

- **Singleton Pattern**: Used for system managers
- **Strategy Pattern**: Dynamic pricing calculations
- **Factory Pattern**: Object creation from JSON
- **MVC Pattern**: Separation of UI and business logic

---

## 👥 **Credits**

* **Developers:** Morris van Uden, Max Viehöfer
* **Teacher:** Erik Seldenthuis
* **Class:** TIA4V1B
* **Institution:** [Add institution name]
* **Project Year:** [Add year]
* **Website:** Coming soon
* **GitHub:** [https://github.com/m3v64/ovOOP](https://github.com/m3v64/ovOOP)

### **Acknowledgments**

- Thanks to the Java community for excellent documentation
- Gson library by Google for JSON handling
- All contributors and testers

---

## 📄 **License**

This project is licensed under a custom license. See the [LICENSE](LICENSE) file for details.

---

## 📞 **Contact & Support**

- **GitHub Issues**: [Report bugs or request features](https://github.com/m3v64/ovOOP/issues)
- **GitHub Discussions**: [Ask questions or share ideas](https://github.com/m3v64/ovOOP/discussions)
- **Repository**: [https://github.com/m3v64/ovOOP](https://github.com/m3v64/ovOOP)

---

## 🎯 **Future Enhancements**

Planned features and improvements:

- [ ] Web-based GUI version
- [ ] Real-time multiplayer travel
- [ ] Achievement system
- [ ] Save/load game states
- [ ] Multiple currency support
- [ ] Weather effects on pricing
- [ ] Train delay simulation
- [ ] Loyalty program system
- [ ] Route recommendations
- [ ] Statistics and analytics dashboard

---

## ⭐ **Star History**

If you find this project useful, please consider giving it a star on GitHub!

---

<div align="center">

**Made with ❤️ by Morris van Uden and Max Viehöfer**

*Happy Traveling! 🚂*

</div>
