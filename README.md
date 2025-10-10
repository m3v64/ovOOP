
# ovOOP public transportation System 🚆

[![Java](https://img.shields.io/badge/Java-17+-blue)](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
[![License](https://img.shields.io/badge/License-MIT-green)](LICENSE)

A **console-based public transportation system** in Java using Object-Oriented Programming.
Book tickets, calculate travel costs dynamically, save trip history and so much more.

---

## **Features**

* 🎟 Book tickets with seat class and quantity.
* 🛤 Calculate costs based on distance, seat class, time, peak hours, and discounts.
* 💾 Save user data per passenger for future trips.
* 📋 Simple console menu for user interaction.

---

## **Installation**

1, Clone the repo:

```bash
git clone https://github.com/m3v64/ovOOP.git
```

2, Compile all Java files:

```bash
javac ovOOP/*.java
```

3, Run the program:

```bash
java ovOOP.Main
```

---

## **Usage Example (outdated)**

```txt
----------------------------------------
Welcome to the MVU train company!
1. Book a Ticket
2. Add to saldo
3. Manage your OV account
4. Login / Change account
5. Exit
----------------------------------------
Please select an option: 1

You are at Dryard
Enter destination city: Trasin
Distance from Dryard to Trasin is 200 km.
Total cost: €XX.XX
Coupon saved to coupon.json
```

---

## **File Structure**

```txt
ovOOP/
├─ src/ovOOP/
|     ├─ Main.java
|     ├─ Menu.java
|     ├─ AskDestination.java
|     ├─ TravelTo.java
|     ├─ CalculateTravelCost.java
|     ├─ OvTime.java
|     ├─ Coupon.java
|     └─ SaveOrigin.java
└─ data/
   ├─ Cities.json
   ├─ TrainLines.json
   └─ AccountInfo.json
```

---

## **JSON Storage**

* **Cities.json** – Stores a list of all cities with available connections.
* **TrainLines.json** – Stores all currentl active train lines.
* **AccountInfo.json** – Stores all user information such as, username and booking history.

---

## **Notes**

* Only **direct routes** between cities are supported.
* Ticket prices may vary due to random conditions.
* This proram has permission to create JSON files automatically if they don’t exist.

---

## **Future Improvements**

* Add indirect routes and route suggestions.
* Improve error handling and input validation.

---
