# Answer for Question 2

## How will you tackle the challenge?
1. **Read the CSV File**: Open the CSV file and identify whether each cell contains a value (like `5` or `3`), or a formula (like `=5+A1`).
2. **Process Formulas**: For cells with formulas:
   - Break down the formula (e.g., `=5+A1` means `5` plus the value in cell `A1`).
   - Use other cells' values to calculate the result.
3. **Order of Calculation**: Make sure to calculate dependent cells first (e.g., calculate `A1` before `C1` if `C1` depends on `A1`).
4. **Save the Results**: Write the final values into a new CSV file, replacing formulas with their results.

---

## What type of errors would you check for?
1. **Circular References**: When formulas refer to each other in a loop (e.g., `A1 = B1`, and `B1 = A1`).
2. **Invalid Formulas**: Check for incorrect syntax or unsupported operations in formulas.
3. **Missing References**: Handle cases where a formula refers to a cell that doesn’t exist.
4. **Calculation Errors**: Catch errors like dividing by zero.

---

## How might a user break your code?
1. **Provide Invalid Data**: The CSV file may contain incorrect or incomplete information.
2. **Use Unsupported Formulas**: Include complex or unsupported formulas that your program cannot understand.
3. **Circular Dependencies**: Create formulas that loop endlessly, causing the program to crash or hang.
4. **Large Datasets**: Input an extremely large file that overwhelms the program's memory or processing power.
