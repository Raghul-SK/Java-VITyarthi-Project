# Java-VITyarthi-Project
Overview of the Project: 
The Multi-Unit Command-Line Converter is a lightweight, cross-category conversion utility developed in Java. It allows users to rapidly convert values across common measurement systems—including physical units (length, mass, time, speed, temperature), digital data storage, and baseline currency estimates.

The application offers dual execution modes:
1. Direct CLI Execution: Pass arguments directly via the terminal for single-step conversions.
2. Interactive REPL Shell: An interactive console interface for executing multiple conversions without restarting the program.

Features: 
Multi-Category Conversion: Supports 7 distinct unit domains:
Length: Millimeters, centimeters, meters, kilometers, inches, feet, yards, miles
Mass: Milligrams, grams, kilograms, metric tons, ounces, pounds
Time: Seconds, minutes, hours, days, weeks
Speed: Meters per second ($m/s$), kilometers per hour ($km/h$), miles per hour ($mph$), knots
Temperature: Celsius ($C$), Fahrenheit ($F$), Kelvin ($K$) via affine transformations
Digital Storage: Bytes ($B$), Kilobytes ($KB$), Megabytes ($MB$), Gigabytes ($GB$), Terabytes ($TB$) using standard binary scaling ($1024^n$)
Currencies: Static conversions between USD, EUR, INR, GBP, JPY, CAD, and AUD

Flexible Natural Input Parsing: Handles variations such as 10 km m, 10 kilometers to meters, or colloquial terms like bucks and rupees.
Category Safety Enforcement: Validates that source and target units belong to the same category to prevent invalid cross-domain conversions (e.g., length to time).
Input Validation & Error Handling: Gracefully handles non-numeric inputs, incorrect parameter counts, and unsupported unit identifiers.

Technical Specifications:
Language: Java (JDK 14+)
Primary class: Java
Core methods: interactiveShell(), Process(), normalize(), runTemp(), getFactor(), getGroup()
Data Structures & Concepts: Switch Expressions, Regular Expressions (matches), Exception Handling (try-catch), Tokenization (split), Scanner I/O

How It Works:
Input Normalization: The input unit strings are passed through normalize(), stripping trailing whitespace, converting to lowercase, and mapping plurals or aliases (e.g., "miles" $\rightarrow$ "mi", "celsius" $\rightarrow$ "c").
Unit Isolation & Domain Check:
Temperatures are routed through runTemp(), converting first to Celsius before reaching the final target unit.
Linear Units retrieve baseline scale factors from getFactor(). The system verifies domain equivalence using getGroup(), then evaluates the conversion:

$$\text{Result} = \frac{\text{Input Value} \times \text{Source Factor}}{\text{Target Factor}}$$

Formatted Output: Outputs values with 4-decimal precision for general units and 2-decimal precision for temperature.

Execution Guide
Compilation:
Compile the source file using standard javac Java.java
Direct CLI Mode:
Run one-off conversions by passing 3 arguments (<value> <from> <to>): 
java Java 100 km mi
java Java 98.6 f c

Supported interactive syntax:
<value> <from> <to> (e.g., 50 km m)
<value> <from> to <to> (e.g., 10 miles to km)
help or -l to print the complete list of supported units
quit or exit to terminate the shell session

Known Edge Case & Quick Fix
In getGroup(), the regex for length is:

Java
u.matches("mm | cm | m | km | in | ft | yd")
It currently omits mi (miles), even though mi is present in getFactor() and normalize(). To allow conversions involving miles, update that line to:

Java
if (u.matches("mm | cm | m | km | in | ft | yd | mi")) return "len";

Conclusion
The Multi-Unit Command-Line Converter serves as an efficient, lightweight utility designed to simplify complex unit conversions across multiple measurement systems into a single command-line interface. Through this project, core Java principles—such as modular method design, regular expressions, modern switch expressions, stream-based string parsing, and robust error handling—were directly applied to solve common everyday conversion challenges. Building both direct argument parsing and an interactive REPL shell provided practical experience in handling diverse user inputs, data validation, and programmatic conversion logic. Moving forward, potential enhancements include dynamic API integration for real-time currency rates and expanded unit categories.
