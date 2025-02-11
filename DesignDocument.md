# Payroll Generator Design Document


This document is meant to provide a tool for you to demonstrate the design process. You need to work on this before you code, and after have a finished product. That way you can compare the changes, and changes in design are normal as you work through a project. It is contrary to popular belief, but we are not perfect our first attempt. We need to iterate on our designs to make them better. This document is a tool to help you do that.


## (INITIAL DESIGN): Class Diagram

Place your class diagram below. Make sure you check the fil in the browser on github.com to make sure it is rendering correctly. If it is not, you will need to fix it. As a reminder, here is a link to tools that can help you create a class diagram: [Class Resources: Class Design Tools](https://github.com/CS5004-khoury-lionelle/Resources?tab=readme-ov-file#uml-design-tools)

```mermaid

classDiagram
    direction TB

    class IEmployee {
        <<interface>>
        +String getName()
        +String getId()
        +double getPayRate()
        +double getYTDEarnings()
        +double getYTDTaxesPaid()
        +double getPreTaxDeductions()
        +double calculatePay()
    }

    class Employee {
        -String name
        -String id
        -double payRate
        -double ytdEarnings
        -double ytdTaxesPaid
        -double preTaxDeductions
    }

    class HourlyEmployee {
        -double hoursWorked
        +double calculatePay()
    }

    class SalaryEmployee {
        +double calculatePay()
    }

    class IPayStub {
        <<interface>>
        +String generatePayStub()
    }

    class PayStub {
        -IEmployee employee
        -double grossPay
        -double netPay
        -double taxAmount
        +String generatePayStub()
    }

    class ITimeCard {
        <<interface>>
        +double getHoursWorked()
        +String getDate()
    }

    class TimeCard {
        -String date
        -double hoursWorked
        +double getHoursWorked()
        +String getDate()
    }

    class FileUtil {
        +List<String> readFile(String filename)
        +void writeFile(String filename, List<String> data)
    }

    class Builder {
        +IEmployee buildEmployeeData(String line)
        +ITimeCard buildTimeCardData(String line)
    }

    class PayrollGenerator {
        +static void main(String[] args)
    }

%% Relationships
    Employee <|-- HourlyEmployee
    Employee <|-- SalaryEmployee
    IEmployee <|-- Employee
    IPayStub <|-- PayStub
    ITimeCard <|-- TimeCard
    PayStub --> IEmployee : references
    PayrollGenerator --> FileUtil : uses
    PayrollGenerator --> Builder : uses


```

## (INITIAL DESIGN): Tests to Write - Brainstorm

Write a test (in english) that you can picture for the class diagram you have created. This is the brainstorming stage in the TDD process. 

> [!TIP]
> As a reminder, this is the TDD process we are following:
> 1. Figure out a number of tests by brainstorming (this step)
> 2. Write **one** test
> 3. Write **just enough** code to make that test pass
> 4. Refactor/update  as you go along
> 5. Repeat steps 2-4 until you have all the tests passing/fully built program

You should feel free to number your brainstorm. 

1. Test that the `Employee` class properly returns `name` from `getName()`
2. Test that the `Employee` class properly returns `id` from `getId()`
3. continue to add your brainstorm here (you don't need to super formal - this is a brainstorm) - yes, you can change the bullets above to something that fits your design.

General Employee Tests
1. Test that getName() properly returns the employee’s name.
2. Test that getID() properly returns the employee’s ID.
3. Test that toCSV() properly converts an employee to the expected CSV format.
4. Test that attempting to create an employee with an invalid ID (e.g., empty string) throws an exception.

SalaryEmployee Tests
5. Test that getEmployeeType() returns "SALARY".

6. Test that calculateGrossPay() always returns the correct bi-weekly salary.

7. Test that runPayroll() always creates a PayStub with the correct pay, regardless of hours worked.

8. Test that runPayroll() returns null when given negative hours.

PayStub Tests
9. Test that getPay() returns the correct pay.

10. Test that getTaxesPaid() returns the correct tax deduction.

11. Test that toCSV() properly formats the pay stub data.

12. Test that PayStub correctly stores and retrieves historical earnings.

Builder Tests
13. Test that buildEmployeeFromCSV() correctly constructs an HourlyEmployee from valid CSV input.

14. Test that buildEmployeeFromCSV() correctly constructs a SalaryEmployee from valid CSV input.

15. Test that buildEmployeeFromCSV() returns null for invalid CSV input.

16. Test that buildTimeCardFromCSV() correctly constructs a TimeCard from valid CSV input.

17. Test that buildTimeCardFromCSV() returns null for malformed CSV input.


## (FINAL DESIGN): Class Diagram

Go through your completed code, and update your class diagram to reflect the final design. Make sure you check the file in the browser on github.com to make sure it is rendering correctly. It is normal that the two diagrams don't match! Rarely (though possible) is your initial design perfect. 

> [!WARNING]
> If you resubmit your assignment for manual grading, this is a section that often needs updating. You should double check with every resubmit to make sure it is up to date.
```mermaid
classDiagram
    direction TB

%% Interfaces
    class IEmployee {
        <<interface>>
        + getName() : String
        + getID() : String
        + getPayRate() : double
        + getEmployeeType() : String
        + getYTDEarnings() : double
        + getYTDTaxesPaid() : double
        + getPretaxDeductions() : double
        + runPayroll(hoursWorked : double) : IPayStub
        + toCSV() : String
    }

    class IPayStub {
        <<interface>>
        + getPay() : double
        + getTaxesPaid() : double
        + toCSV() : String
    }

    class ITimeCard {
        <<interface>>
        + getEmployeeID() : String
        + getHoursWorked() : double
    }

%% Abstract Class
    class Employee {
        <<abstract>>
        - name : String
        - id : String
        - payRate : double
        - ytdEarnings : double
        - ytdTaxesPaid : double
        - pretaxDeductions : double
        + Employee(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions)
        + getName() : String
        + getID() : String
        + getPayRate() : double
        + getYTDEarnings() : double
        + getYTDTaxesPaid() : double
        + getPretaxDeductions() : double
        + runPayroll(hoursWorked : double) : IPayStub
        + toCSV() : String
        # calculateGrossPay(hoursWorked : double) : double
    }

%% Concrete Employee Classes
    class HourlyEmployee {
        + HourlyEmployee(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions)
        + getEmployeeType() : String
        # calculateGrossPay(hoursWorked : double) : double
    }

    class SalaryEmployee {
        + SalaryEmployee(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions)
        + getEmployeeType() : String
        # calculateGrossPay(hoursWorked : double) : double
    }

%% PayStub Class
    class PayStub {
        - employee : IEmployee
        - netPay : double
        - taxesPaid : double
        - ytdEarnings : double
        - ytdTaxesPaid : double
        + PayStub(employee : IEmployee, netPay : double, taxesPaid : double, ytdEarnings : double, ytdTaxesPaid : double)
        + getPay() : double
        + getTaxesPaid() : double
        + toCSV() : String
    }

%% Builder Class
    class Builder {
        + buildEmployeeFromCSV(csv : String) : IEmployee
        + buildTimeCardFromCSV(csv : String) : ITimeCard
    }

%% File Utility Class
    class FileUtil {
        + readFileToList(file : String) : List~String~
        + writeFile(outFile : String, lines : List~String~)
    }

%% Payroll Generator Class
    class PayrollGenerator {
        + main(args : String[])
    }

%% "Anonymous" TimeCard Class Renamed
    class TimeCardFromBuilder {
        <<anonymous>>
        + getEmployeeID() : String
        + getHoursWorked() : double
    }

%% Relationships
    IEmployee <|.. Employee
    Employee <|-- HourlyEmployee
    Employee <|-- SalaryEmployee

    IPayStub <|.. PayStub
    PayStub --> IEmployee : holds reference

    ITimeCard <|.. TimeCardFromBuilder
    Builder --> IEmployee : builds
    Builder --> ITimeCard : builds

    PayrollGenerator --> FileUtil : uses
    PayrollGenerator --> Builder : uses
    FileUtil
```




## (FINAL DESIGN): Reflection/Retrospective

> [!IMPORTANT]
> The value of reflective writing has been highly researched and documented within computer science, from learning new information to showing higher salaries in the workplace. For this next part, we encourage you to take time, and truly focus on your retrospective.

Take time to reflect on how your design has changed. Write in *prose* (i.e. do not bullet point your answers - it matters in how our brain processes the information). Make sure to include what were some major changes, and why you made them. What did you learn from this process? What would you do differently next time? What was the most challenging part of this process? For most students, it will be a paragraph or two. 

One of the biggest lessons I learned from this process was the importance of iteration in design. The initial plan, while useful as a starting point, needed refinement as I encountered real-world constraints and edge cases during implementation. If I were to approach this project again, I would focus more on early-stage prototyping and testing to identify structural issues sooner. Additionally, I found that writing thorough tests alongside development was invaluable, as it helped catch inconsistencies and ensured that each component functioned as intended.

The most challenging part of this process was managing dependencies between different components while maintaining modularity. For example, ensuring that PayrollGenerator interacted efficiently with FileUtil and Builder required careful planning to prevent unnecessary coupling. Overall, this project reinforced the importance of designing with flexibility in mind, as real-world constraints often necessitate changes that a rigid design would struggle to accommodate.