public class Notification {
    final String message;

    public Notification(Grade grade) {
        message = "New grade: " + grade;
    }

    public String getMessageContent() {
        return message;
    }

    public String toString() {
        return message;
    }
}