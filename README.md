Overview of the project: The Multi-Unit Command Line Converter is a light utility written in Java for quickly converting values from one measurement system to another. The application is small and focused on the conversion process, supporting common physical (distance, mass, time, speed, temperature), digital (storage), and baseline currency rates.
The tool can be used in two modes:
Direct mode: The conversion process is initiated by supplying the parameters to the Java class in the command line.

Interactive mode: The utility runs in a REPl (read-evaluate-print-loop) shell, allowing the user to perform several consecutive operations without restarting the program.
The application supports the following categories: length, mass, time, speed, temperature, digital storage, and currencies. In length category, the supported units are millimeters, centimeters, meter, kilometers, inches, feet, yards, miles. Mass category contains milligrams, grams, kilograms, ton, ounce, pound. Time category has seconds, minutes, hours, days, weeks. Speed category: meter per second (
m
/
s
), kilometer per hour (
k
m
/
h
), mile per hour (
m
p
h
), and knots. Temperature category: Celsius (
C
), Fahrenheit (
F
), Kelvin (
K
) with affine transformations. Digital storage category: bytes (
B
), kilobytes (
K
B
), megabytes (
M
B
), gigabytes (
G
B
), terabytes (
T
B
) with binary-based (
1024
n
) conversions. The currencies are estimated in static rates against the US dollar: 1 USD is 0.89 EUR, 74.52 INR, 0.74 GBP, 107.64 JPY, 1.26 CAD, and 1.33 AUD.

The tool also supports flexible input parsing, such as 10 km m or 10 kilometers to meters, or even using slang terms like bucks and rupees. The input is checked for correctness and validity, such as ensuring that the source and target units are in the same category. Overall, the application gracefully handles incorrect input, such as wrong arguments count or invalid input parameters.

The implementation is written in Java (JDK 14+). The main entry point is the Java class, which runs two modes: direct (by arguments) and interactive (REPl). The key methods are interactiveShell(), Process(), normalize(), runTemp(), getFactor(), and getGroup() . The code utilizes switch expressions, regular expressions with matches(), try-catch blocks, tokenization with split() , and Scanner for reading input from the console.

The input value and units are normalized in the runTemp() method, where the target unit is extracted from the input string. The trailing whitespaces are removed by trim(), lower-case with toLowerCase() , and pluralization removed (e.g., miles
→
mi, celsius
→
c). The temperature values are processed in runTemp() , first converted to Celsius, and then transformed into a target unit. All other categories use linear transformations, for which the factor is extracted with getFactor() . The target category is determined with getGroup() , and the conversion is performed by multiplying the input value by the corresponding scale factor:

Result
=
Input Value
×
Source Factor
Target Factor

The result is printed with 4 decimal places for all categories, except the temperature, which is printed with two decimal places.

How to Execute the Code: The file can be compiled with javac Java.java . To execute the program, supply the conversion parameters as command line arguments ( ): java Java 100 km mi java Java 98.6 f c
The interactive mode allows to type the conversion requests in the shell with ~

(e.g., 50 km m) to ~
(e.g., 10 miles to km). The available units can be listed with help or -l command in the shell. To exit the shell, type quit or exit .

Known edge case and fix: In the getGroup() method, the length units are matched with the pattern:

Java u.matches("mm | cm | m | km | in | ft | yd")
However, the miles (mi) is not included in the list, even though it is processed in getFactor() and normalize() methods. To allow the conversion with the miles, change the line above to:
Java if (u.matches("mm | cm | m | km | in | ft | yd | mi")) return "len";

Conclusion: The Multi-Unit Command Line Conversion utility is a light application, which makes frequently used unit conversion easier and faster, as it does not require the user to switch between several websites or applications for different categories. By implementing this utility, I was able to practice and reinforce the Java basics, such as the modular code structure, regular expressions, switch expressions, stream processing of the input string, and exception handling. In particular, the implementation of the conversion process for the two modes demonstrated how the program can process the user input of different formats. For future development, it would be interesting to support dynamic exchange rates with the API call.
