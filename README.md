# OOP---Digital-Catalog
My project tries to create an electronic catalog for use in modern schools in java ( Intelij ).
Firstly, we use the Singleton Pattern to create a unique catalog wit multiple courses. A course should
have multiple semi-groups and an assistant must be assigned to each semi-group. Class course is an
abstract class which has getters and setters, function to get all students grades, make backup (
Memento pattern) for grades, and of course, a tutor and a name. A course can be "Full" or "Partial",
this means the promotion method, "Full" must have partial score => 3 and exam score => 2,
respectively "Partial" must have (partial + exam) => 5. I created special classes fr grades and
groups to be more easier to handle. You can get best student for exam, partial or total with the help
of BestExamScore, BestPartialSore or BestTotalScore( which are implemented with the help of Strategy
pattern).
In the first phase, the grades are not passed in the catalog, they are passed in a helper class
called "ScoreVisitor". Each assistant and teacher will at some point pass the notes in the catalog
and they will be displayed to the students and parents. The catalog implements the Visitor design
template that allows parents to receive a notification when their child receives a grade.
The catalog comes with a graphic interface divided into several categories of users: Student,
Teacher, Assistant, Parent. After you select your category, you have to introduce your name, in case
the name is entered incorrectly or does not exist, it will display an error. After you have entered
your name correctly, the catalog will display the related information: for Student => his grades and
his teacher and assistant; for Teacher => grades to be assigned; for Assistant => grades to be
assigned; for Parent => your notifications about your children's grades.
You also have a "test" class with a parser to add more courses and some tests created by me
(You may need to enter the file path).

The parser has the form :
Teacher's first name, Teacher's last name
credits
GroupID Assistant's first name, Assistant's last name
Student's first name and last name, Father's first name, Mother's first name, partial grade, exam grade
Student's first name and last name, Father's first name, Mother's first name, partial grade, exam grade
Student's first name and last name, Father's first name, Mother's first name, partial grade, exam grade
GroupID Assistant's first name, Assistant's last name
Student's first name and last name, Father's first name, Mother's first name, partial grade, exam grade
Student's first name and last name, Father's first name, Mother's first name, partial grade, exam grade
Student's first name and last name, Father's first name, Mother's first name, partial grade, exam grade
GroupID Assistant's first name, Assistant's last name
Student's first name and last name, Father's first name, Mother's first name, partial grade, exam grade
Student's first name and last name, Father's first name, Mother's first name, partial grade, exam grade
Student's first name and last name, Father's first name, Mother's first name, partial grade, exam grade
etc ....
