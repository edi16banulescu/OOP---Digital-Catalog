import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        Visitor scoreVisitor = new ScoreVisitor();
        Test test = new Test();
        test.parser("src/OOP", scoreVisitor);
        test.parser("src/Math", scoreVisitor);

        /* For this course all grades are assigned by default */
        test.parser("src/Operating Systems", scoreVisitor);
        Catalog.getInstance().courses.get(2).getTutor().accept(scoreVisitor);
        for(Group g : Catalog.getInstance().courses.get(2).getGroups().values()) {
            g.getAssistant().accept(scoreVisitor);
        }

        DefaultPage initial = new DefaultPage(scoreVisitor);

        /* Testing other functionalities that I could not include in the graphic interface */
        BestTotalScore bestTotalScore = new BestTotalScore();
        BestPartialScore bestPartialScore = new BestPartialScore();
        BestExamScore bestExamScore = new BestExamScore();

        System.out.println("Best total score for OS : " + bestTotalScore.
                                        getBestStudent(Catalog.getInstance().courses.get(2)));
        System.out.println("Best partial score for OS : " + bestPartialScore.
                                        getBestStudent(Catalog.getInstance().courses.get(2)));
        System.out.println("Best exam score for OS : " + bestExamScore.
                                        getBestStudent(Catalog.getInstance().courses.get(2)));

        FullCourse os = (FullCourse) Catalog.getInstance().courses.get(2);
        System.out.println("All graduated students from OS : " + os.getGraduatedStudents());

        System.out.println("\n");
        os.makeBackup();
        System.out.println(os.getSnapshot());

    }


    /* A function used to parse MY type of files which contains the courses */
    public void parser(String fileName, Visitor scoreVisitor) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner input = new Scanner(file);

        UserFactory factory = new UserFactory();

        Course myCourse;

        String course = file.getName();

        String tutorFirstName = input.next();
        String tutorLastName = input.next();

        Teacher tutor = (Teacher)factory.getUser("Teacher", tutorFirstName, tutorLastName);

        int credits = input.nextInt();

        if(input.next().equals("Full")) {
            myCourse = new FullCourse.FullCourseBuilder()
                    .name(course)
                    .tutor(tutor)
                    .credit(credits)
                    .build();
        } else {
            myCourse = new PartialCourse.PartialCourseBuilder()
                    .name(course)
                    .tutor(tutor)
                    .credit(credits)
                    .build();
        }

        while(input.hasNext()) {
            String ID = input.next();
            String assistantFirstName = input.next();
            String assistantLastName = input.next();

            Assistant assistant = (Assistant) factory.getUser("Assistant",
                                                            assistantFirstName, assistantLastName);

            myCourse.addGroup(ID, assistant);

            for(int i = 0; i < 3; i ++) {
                String firstName = input.next();
                String lastName = input.next();
                Student student = (Student) factory.getUser("Student", firstName, lastName);

                String fatherFirstName = input.next();
                Parent father = (Parent) factory.getUser("Parent", fatherFirstName, lastName);

                String motherFirstName = input.next();
                Parent mother = (Parent) factory.getUser("Parent", motherFirstName, lastName);

                student.setFather(father);
                student.setMother(mother);
                myCourse.addStudent(ID, student);

                Catalog.getInstance().addObserver(mother);

                Double partialScore = input.nextDouble();
                Double examScore = input.nextDouble();

                ((ScoreVisitor) scoreVisitor).addPartialScore(assistant, student, course, partialScore);
                ((ScoreVisitor) scoreVisitor).addExamScore(tutor, student, course, examScore);
            }
        }

        Catalog.getInstance().addCourse(myCourse);
    }
}
