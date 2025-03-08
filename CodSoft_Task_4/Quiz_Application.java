import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

abstract class QuizApplication {
    static String[] questions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "Who wrote 'Hamlet'?",
            "What is the largest ocean on Earth?",
            "Which is the smallest prime number?"
    };

    static String[][] options = {
            {"A. Paris", "B. London", "C. Berlin", "D. Madrid"},
            {"A. Earth", "B. Mars", "C. Jupiter", "D. Venus"},
            {"A. Charles Dickens", "B. William Shakespeare", "C. Mark Twain", "D. Jane Austen"},
            {"A. Atlantic", "B. Indian", "C. Arctic", "D. Pacific"},
            {"A. 0", "B. 1", "C. 2", "D. 3"}
    };

    static char[] answers = {'A', 'B', 'B', 'D', 'C'}; // Correct answers
    static boolean timeUp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        final int TIME_LIMIT = 10; // Time limit in seconds

        System.out.println("Welcome to the Quiz Application!");
        System.out.println("You have " + TIME_LIMIT + " seconds to answer each question. Choose the correct option (A, B, C, or D).\n");

        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + questions[i]);
            for (String option : options[i]) {
                System.out.println(option);
            }

            timeUp = false;
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                int countdown = TIME_LIMIT;
                public void run() {
                    if (countdown > 0) {
                        System.out.print("\rTime remaining: " + countdown + " seconds ");
                        countdown--;
                    } else {
                        timeUp = true;
                        System.out.println("\nTime's up! Moving to the next question.\n");
                        timer.cancel();
                    }
                }
            };
            timer.scheduleAtFixedRate(task, 0, 1000);

            System.out.print("Your answer: ");
            String userInput = scanner.nextLine();
            timer.cancel();

            if (timeUp) {
                continue;
            }

            if (userInput.length() > 0) {
                char userAnswer = Character.toUpperCase(userInput.charAt(0));

                if (userAnswer == answers[i]) {
                    System.out.println("Correct!\n");
                    score++;
                } else {
                    System.out.println("Wrong! The correct answer was: " + answers[i] + "\n");
                }
            } else {
                System.out.println("No answer provided. Moving to the next question.\n");
            }
        }

        System.out.println("Quiz Over! Your final score is: " + score + " out of " + questions.length);
        scanner.close();
    }
}
