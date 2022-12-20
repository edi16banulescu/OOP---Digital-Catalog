public class UserFactory {

    public static User getUser(String UserName, String firstName, String lastName) {
        if(UserName.equals("Parent")) {
            return new Parent(firstName, lastName);
        } else if(UserName.equals("Student")) {
            return new Student(firstName, lastName);
        } else if(UserName.equals("Assistant")) {
            return new Assistant(firstName, lastName);
        } else if(UserName.equals("Teacher")) {
            return new Teacher(firstName, lastName);
        }

        return null;
    }
}
