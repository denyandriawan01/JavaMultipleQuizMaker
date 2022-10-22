import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {

    public static void Logo(){

        System.out.println("  __   _  _  __  ____     __    __  ____ ");
        System.out.println(" /  \\ / )( \\(  )(__  )   /  \\  /  \\(  _ \\");
        System.out.println("(  O )) \\/ ( )(  / _/   (  O )(  O )) __/");
        System.out.println(" \\__\\)\\____/(__)(____)   \\__/  \\__/(__)  ");
        System.out.println("===============================================");

    }

    public static void Menu(){
        System.out.println("[#] MENU: ");
        System.out.println(" | [1] Add quiz questions");
        System.out.println(" | [2] Delete quiz");
        System.out.println(" | [3] Update quiz(by number)");
        System.out.println(" | [4] View quiz");
        System.out.println(" | [5] Start quiz");
        System.out.println(" | [6] List correct answer");
        System.out.println(" | [7] Exit");
        System.out.print("[>] Input menu: ");
    }

    public static void pressEnterKeyToContinue(){
        System.out.print("[!] Press Enter to continue... [!]");
        Scanner s = new Scanner(System.in);
        s.nextLine();
    }

    public static void clearConsole(){
        try{
            if(System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else{
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException e) {
            System.out.print("");
        }
    }

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        int Row = 5, Column = 4;
        String[] Question = new String[Row];
        String[][] MultipleChoice = new String[Row][Column];
        char[] CorrectAnswer = new char[Row], AnswerInput = new char[Row];
        ArrayList<Character> CA = new ArrayList<>();
        int Menu,  Update, Temp = 0, Point = 0;

        do {
            clearConsole();
            Logo(); Menu();
            try {
                Menu = input.nextInt();
                input.nextLine();
            } catch (Exception e){
                input.nextLine();
                continue;
            }
            switch (Menu){
                case 1: // Add question
                    if (Temp == Row) {
                        System.out.println(" | [!] The maximum number of questions has been fulfilled [!]");
                    } else if (Temp <= Row + 1){
                        for (int i = Temp; i < Temp+1; i++) {
                            System.out.print(" | [>] Question number - " + (i + 1) + ": ");
                            Question[i] = input.nextLine();
                            for (int j = 0; j < 4; j++) {
                                switch (j) {
                                    case 0 -> System.out.print(" |    [>] Input multiple A: ");
                                    case 1 -> System.out.print(" |    [>] Input multiple B: ");
                                    case 2 -> System.out.print(" |    [>] Input multiple C: ");
                                    default -> System.out.print(" |    [>] Input multiple D: ");
                                }
                                MultipleChoice[i][j] = input.nextLine();
                            }
                            System.out.print(" |    [*] Correct answer  : ");
                            CorrectAnswer[i] = input.next().charAt(0);
                        }
                        Temp += 1;
                    }
                    pressEnterKeyToContinue();
                    break;
                case 2: // Delete quiz
                    if (Temp == 0){
                        System.out.println(" | [!] Empty array [!]");
                    } else if (Temp > 0) {
                        for (int i = 0; i < Temp + 1; i++) {
                            Question[i] = "";
                            for (int j = 0; j < 4; j++){
                                MultipleChoice[i][j] = "";
                            }
                        }
                        CA.clear();
                        System.out.println(" | [!] Delete complete");
                    }
                    Temp = 0;
                    pressEnterKeyToContinue();
                    break;
                case 3: // Update quiz
                    if (Temp == 0){
                        System.out.println(" | [!] Empty array [!]");
                    } else {
                        System.out.print(" | [>] Update at number-: ");
                        try {
                            Update = input.nextInt();
                            input.nextLine();
                        } catch (Exception e){
                            input.nextLine();
                            continue;
                        }
                        for (int i = 0; i < Temp; i++) {
                            if (Update - 1 == i) {
                                System.out.println(" |     [>] Number " + Update + " found");
                                System.out.println(" | ");
                                pressEnterKeyToContinue();
                                clearConsole();
                                // Before Update
                                System.out.print("===============================================\n");
                                System.out.println("Before edit");
                                System.out.println("-----------");
                                System.out.println("[" + (i + 1) + "] " + Question[i]);
                                for (int j = 0; j < 4; j++) {
                                    switch (j) {
                                        case 0 -> System.out.println(" | A. " + MultipleChoice[i][j]);
                                        case 1 -> System.out.println(" | B. " + MultipleChoice[i][j]);
                                        case 2 -> System.out.println(" | C. " + MultipleChoice[i][j]);
                                        default -> System.out.println(" | D. " + MultipleChoice[i][j]);
                                    }
                                }
                                System.out.print("===============================================\n");
                                // Going Update
                                System.out.println("Edit");
                                System.out.println("-----------");
                                System.out.print(" | [>] Question number - " + (i + 1) + ": ");
                                Question[i] = input.nextLine();
                                for (int j = 0; j < 4; j++) {
                                    switch (j) {
                                        case 0 -> System.out.print(" |    [>] Input multiple A: ");
                                        case 1 -> System.out.print(" |    [>] Input multiple B: ");
                                        case 2 -> System.out.print(" |    [>] Input multiple C: ");
                                        default -> System.out.print(" |    [>] Input multiple D: ");
                                    }
                                    MultipleChoice[i][j] = input.nextLine();
                                }
                                System.out.print(" |    [*] Correct answer  : ");
                                CorrectAnswer[i] = input.next().charAt(0);
                            }
                        }
                    }
                    pressEnterKeyToContinue();
                    break;
                case 4: // Show quiz
                    if (Temp == 0){
                        System.out.println(" | [!] Empty array [!]");
                    } else {
                        clearConsole();
                        System.out.println("Preview Quiz");
                        System.out.print("===============================================\n");
                        for (int i = 0; i < Temp; i++) {
                            System.out.println("[" + (i + 1) + "] " + Question[i]);
                            for (int j = 0; j < 4; j++) {
                                switch (j) {
                                    case 0 -> System.out.println(" | A. " + MultipleChoice[i][j]);
                                    case 1 -> System.out.println(" | B. " + MultipleChoice[i][j]);
                                    case 2 -> System.out.println(" | C. " + MultipleChoice[i][j]);
                                    default -> System.out.println(" | D. " + MultipleChoice[i][j]);
                                }
                            }
                            System.out.print("===============================================\n");
                        }
                    }
                    pressEnterKeyToContinue();
                    break;
                case 5: // Start Quiz
                    if (Temp == 0){
                        System.out.println(" | [!] Empty array [!]");
                    } else {
                        clearConsole();
                        System.out.print("===============================================\n");
                        for (int i = 0; i < Temp; i++) {
                            System.out.println("Correct " + Point + "/" + Temp);
                            System.out.println("[" + (i + 1) + "] " + Question[i]);
                            for (int j = 0; j < 4; j++) {
                                switch (j) {
                                    case 0 -> System.out.println(" | A. " + MultipleChoice[i][j]);
                                    case 1 -> System.out.println(" | B. " + MultipleChoice[i][j]);
                                    case 2 -> System.out.println(" | C. " + MultipleChoice[i][j]);
                                    default -> System.out.println(" | D. " + MultipleChoice[i][j]);
                                }
                            }
                            System.out.print("[>] Your answer: ");
                            AnswerInput[i] = input.next().charAt(0);
                            char Upper1 = Character.toUpperCase(CorrectAnswer[i]);
                            char Upper2 = Character.toUpperCase(AnswerInput[i]);
                            if (Upper1 == Upper2){
                                Point += 1;
                            } else {
                                Point += 0;
                            }
                            System.out.print("===============================================\n");
                        }
                        System.out.println("Your final point is: " + Point + "/" + Temp);
                        int Final = (100*Point)/Temp;
                        System.out.println("Score: " + Final);
                        System.out.print("===============================================\n");
                    }
                    Point = 0;
                    pressEnterKeyToContinue();
                    break;
                case 6: // List answer with arraylist
                    if (Temp == 0){
                        System.out.println(" | [!] Empty array [!]");
                    } else {
                        CA.clear();
                        for (int i = 0; i < Temp; i++){
                            CA.add(Character.toUpperCase(CorrectAnswer[i]));
                        }
                        System.out.println(" | Correct Answer: " + CA);
                    }
                    pressEnterKeyToContinue();
                    break;
                case 7: exit(0);
                default:
                    System.out.println(" | [!] Wrong input [!]");
                    pressEnterKeyToContinue();
                    break;
            }
        } while (true);

    }

}
