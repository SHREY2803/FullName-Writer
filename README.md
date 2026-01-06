# Full Name Printer (Multithreading Project)

This project reads first names and last names from files using
two separate threads, and prints full names using a producer–consumer
BlockingQueue model.

The program ensures:
- Thread-safe execution
- Correct pairing of names
- Human-readable output timing

## How to Run

1. Place input files in `resources/`
2. Run `ConcatEmployeeNames.java`
3. Names will print one by one (1 sec delay)

## Technologies Used
- Java
- Multithreading
- BlockingQueue
