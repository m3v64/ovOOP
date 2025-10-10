

# MVU Train Ticketing System 🚆

[![Java](https://img.shields.io/badge/Java-17+-blue)](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
[![License](https://img.shields.io/badge/License-MIT-green)](LICENSE)

A **console-based train ticket booking system** in Java using Object-Oriented Programming.
Book tickets, calculate travel costs dynamically, and save trip history in JSON files.

---

## **Features**

* 🎟 Book tickets with seat class and quantity.
* 🛤 Calculate costs based on distance, seat class, time, peak hours, and discounts.
* 💾 Save last origin city per passenger for future trips.
* 📄 Generate JSON coupons with trip details.
* 📋 Simple console menu for user interaction.

---

## **Installation**

1. Clone the repo:

```bash
git clone https://github.com/yourusername/MVU-Train.git
cd MVU-Train
```

2. Compile all Java files:

```bash
javac ovOOP/*.java
```

3. Run the program:

```bash
java ovOOP.Main
```

---

## **Usage Example**

```
----------------------------------------
Welcome to the MVU train company!
1. Book a Ticket
2. Add to saldo
3. Manage your OV account
4. Login / Change account
5. Exit
----------------------------------------
Please select an option: 1

Available cities: [dryard, timergulch, brittle, staglenhold, eldyard, trasin, swiftlec, lirongrale, ghostle, pearllows, irehole, lighthgro, stormwall, linere]
You are at Dryard
Enter destination city: Trasin
Distance from Dryard to Trasin is 200 km.
Total cost: €XX.XX
Coupon saved to coupon.json
```

---

## **File Structure (Outdated)**

```
ovOOP/
├─ Main.java
├─ Menu.java
├─ AskDestination.java
├─ TravelTo.java
├─ CalculateTravelCost.java
├─ OvTime.java
├─ Coupon.java
├─ SaveOrigin.java
└─ resources/
   ├─ origin.json
   └─ coupon.json
```

---

## **JSON Storage**

* **origin.json** – Tracks the last origin of each passenger.
* **coupon.json** – Stores all booked tickets with trip details.

---

## **Notes**

* Only **direct routes** between cities are supported.
* Ticket prices may vary due to random train conditions.
* JSON files are created automatically if they don’t exist.

---

## **Future Improvements**

* Add indirect routes and route suggestions.
* Implement user account and balance management.
* Improve error handling and input validation.

