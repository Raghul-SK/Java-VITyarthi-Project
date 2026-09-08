PROBLEM STATEMENT:
Engineering students, developers, and analysts often need to deal with numbers in various incompatible systems. From metric/imperial engineering systems to digital capacities and international currencies, the number of different units is overwhelming. Converting values between these units often requires a tedious process of searching in numerous reference tables or opening bulk website calculators. These extra steps, while unavoidable before this project, add complexity, time, and space to any technical task. This product solves this problem by providing an easy-to-access command line utility that performs all common unit conversion operations safely, accurately, and quickly with minimal overhead.

OBJECTIVES:
1. Design the program around a single Java function that handles 7 categories of measurement using a combined scalar and unique temperature algorithm.
2. Design a robust but simple natural language parser (normalize) that understands plurals, common abbreviations, and other paraphrasing techniques.
3. Enforce category-level constraints to avoid dangerous operations such as dividing by zero, mixing units, or using invalid input values.
4. Provide simple one-command use cases as well as an interactive mode with continuous user prompts, for maximum flexibility.
5. Use common exception handling mechanisms to safely halt and alert the user whenever invalid or incomplete data is provided.

SCOPE OF THE PROJECT:
Functional Scope
1. One-off and interactive modes: can be used for simple one-command conversions, but also offers a continuous REPL-like session with user prompts for repeated conversions.

2. Supports 7 types of measurement: handles everything from length/mass/time and speed to temperature, digital capacity, and currency.

3. Simple natural language parser: recognizes plurals and non-standard abbreviations (miles, bucks, kilos, km/h, celsius), normalizes them to short equivalents.

4. Enforces physical/mathematical constraints: double-checks category grouping regex to avoid impossible operations (accidentally mixing lengths with currency).

5. Performs ratio and linear transforms accurately: uses ratio reductions for all but temperature transforms. Calculations reduced to a common scalar ratio up to 4 decimal points or rounded to 2 for linear temperature ($C, F, K$).

6. Offers extensive exception checks for user input: handles missing or incorrect arguments gracefully and guides the user to the correct input.


Non-functional Scope: 

Very fast conversions ($\le 100\text{ ms}$). Interactive mode is immediate, while one-off calculations only take a moment to complete.
No external dependencies: written in standard java, runs on any platform with a java virtual machine.
Simple and intuitive: an in-session help menu (help, -l) reduces the learning and typing overhead for frequent users.

Target Audience: 
Engineering students and computer science students who need to complete or check homework problems in the terminal. Developers and system administrators who want to replace unwieldy website converters with a lightweight CLI utility. Electronics and physics enthusiasts who want to experiment with unit conversions in the shell.
