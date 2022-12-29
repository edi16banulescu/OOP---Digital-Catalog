import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("D:\\IdeaProjects\\OOP\\src\\Math");
        Scanner input = new Scanner(file);

        UserFactory factory = new UserFactory();
        Visitor scoreVisitor = new ScoreVisitor();
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

            Assistant assistant = (Assistant) factory.getUser("Assistant", assistantFirstName, assistantLastName);

            myCourse.addGroup(ID, assistant);

            for(int i = 0; i < 3; i ++) {
                String firstName = input.next();
                String lastName = input.next();
                Student student = new Student(firstName, lastName);

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

        System.out.println(myCourse.getAllStudents());
        Catalog.getInstance().addCourse(myCourse);
        HashMap<String, Group> groups = Catalog.getInstance().courses.get(0).getGroups();
        for (Map.Entry<String, Group> entry : groups.entrySet()) {
            entry.getValue().getAssistant().accept(scoreVisitor);
        }
        tutor.accept(scoreVisitor);


        BestExamScore bestExamScore = new BestExamScore();
        BestPartialScore bestPartialScore = new BestPartialScore();
        BestTotalScore bestTotalScore = new BestTotalScore();


    }
}
