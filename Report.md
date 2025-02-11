# Report for Payroll Generator

This report helps you demonstrate your understanding of the concepts. You should write this report after you have completed the project. 

## Technical Questions

1. What does CSV stand for? 

    It stands for Comma-separated values.

2. Why would you declare `List<IEmployee>` instead of `ArrayList<HourlyEmployee>`?

    Because 'List<IEmployee>' can code to an interface rather than a specific implementation. `List<IEmployee>` can hold any object that implements the `IEmployee` interface—not just HourlyEmployee objects.

3. When you have one class referencing another object, such as storing that object as one of the attributes of the first class - what type of relationship is that called (between has-a and is-a)?

    It's a `has-a` relationship.

4. Can you provide an example of a has-a relationship in your code (if one exists)?

   The PayStub object has-a reference to an IEmployee.

5. Can you provide an example of an is-a relationship in your code (if one exists)?

   HourlyEmployee is-a subtype of Employee.

6. What is the difference between an interface and an abstract class?

   Interfaces define a contract that multiple classes can implement but don’t provide any default behavior. Abstract classes act as a blueprint for subclasses, allowing both abstract and concrete methods.

7. What is the advantage of using an interface over an abstract class?

   The situation needs multiple classes to share a common contract but they are otherwise unrelated. Or the situation needs multiple inheritance

8. Is the following code valid or not? `List<int> numbers = new ArrayList<int>();`, explain why or why not. If not, explain how you can fix it. 
   
   No. Because java generics only support reference types, not primitive types like int, double, char, etc.

   List<Integer> numbers = new ArrayList<>();

9. Which class/method is described as the "driver" for your application? 

   The "driver" class or method is typically the main entry point that initiates the program's execution.

10. How do you create a temporary folder for JUnit Testing?
   
   To create a temporary folder in JUnit tests, we can use JUnit 5's @TempDir annotation.

## Deeper Thinking 

Salary Inequality is a major issue in the United States. Even in STEM fields, women are often paid less for [entry level positions](https://www.gsb.stanford.edu/insights/whats-behind-pay-gap-stem-jobs). However, not paying equal salary can hurt representation in the field, and looking from a business perspective, can hurt the company's bottom line has diversity improves innovation and innovation drives profits. 

Having heard these facts, your employer would like data about their salaries to ensure that they are paying their employees fairly. While this is often done 'after pay' by employee surveys and feedback, they have the idea that maybe the payroll system can help them ensure that they are paying their employees fairly. They have given you free reign to explore this idea.

Think through the issue / making sure to cite any resources you use to help you better understand the topic. Then write a paragraph on what changes you would need to make to the system. For example, would there be any additional data points you would need to store in the employee file? Why? Consider what point in the payroll process you may want to look at the data, as different people could have different pretax benefits and highlight that. 

The answer to this is mostly open. We ask that you cite at least two sources to show your understanding of the issue. The TAs will also give feedback on your answer, though will be liberal in grading as long as you show a good faith effort to understand the issue and making an effort to think about how your design to could help meet your employer's goals of salary equity. 

Salary inequality remains a persistent issue in the United States, even in STEM fields where objective skills assessments should ideally lead to equitable pay. Studies have shown that women in STEM earn, on average, 89 cents for every dollar earned by men in the same roles (Glass et al., 2013). Additionally, diversity in the workplace has been linked to increased innovation and profitability, with one study by McKinsey & Company (2020) indicating that companies in the top quartile for gender diversity were 25% more likely to outperform their peers in profitability. Given this, integrating salary equity analysis into a payroll system could provide proactive insights rather than relying solely on employee feedback or post-hoc surveys.

To implement this within the payroll system, additional data points would need to be collected and stored. One critical piece of information would be job role and experience level, ensuring that pay comparisons are made between employees with similar qualifications. Additionally, demographic data (such as gender and race) would be necessary for identifying disparities, though it should be stored securely to comply with privacy regulations. The payroll system could also track salary progression over time to detect biases in raises and promotions. The best point in the payroll process to analyze this data would likely be at the time of salary determination and during annual reviews, before any deductions or benefits are applied, as these can vary between employees. The system could then generate reports flagging potential pay disparities based on comparable employees, allowing HR to take corrective actions before inequities become systemic. By leveraging the payroll system as a tool for salary equity, employers can proactively work toward fair compensation practices while also fostering a more diverse and innovative workforce.

Citations:

Glass, J. L., Sassler, S., Levitte, Y., & Michelmore, K. M. (2013). What's So Special about STEM? A Comparison of Women's Retention in STEM and Professional Occupations. Social Forces, 92(2), 723–756.

McKinsey & Company. (2020). Diversity wins: How inclusion matters. Retrieved from https://www.mckinsey.com