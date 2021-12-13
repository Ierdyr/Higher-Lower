import java.util.Random;
import java.util.HashMap;
import java.util.Scanner;

public class App {
    private static Scanner sc;
    private static int Max = 500;
    private static int Min = 1;
    private static int Target;
    private static int Guesses = 0;


    
    private static HashMap<Grade, Integer> GradeGuessesLookup = new HashMap<Grade, Integer>();

    enum Grade {
        WORST_EVER,
        REALLY_BAD,
        QUITE_BAD,
        BAD,
        KINDA_SHIT,
        MEH,
        OK,
        FINE,
        ALRIGHT,
        KINDA_GOOD,
        GOOD,
        QUITE_GOOD,
        REALLY_GOOD,
        REMARKABLE,
        EXTRAORDINARY,
        THE_BEST,
        ARE_YOU_CHEATING
    }


    public static void main(String[] args) throws Exception {
        init();
        gameStart();

    }

    private static void initGradeGuessesLookup() { // Int is minimum guesses you have to do to get a certian score. 
        GradeGuessesLookup.put(Grade.WORST_EVER, 50);
        GradeGuessesLookup.put(Grade.REALLY_BAD, 43);
        GradeGuessesLookup.put(Grade.QUITE_BAD, 40);
        GradeGuessesLookup.put(Grade.BAD, 35);
        GradeGuessesLookup.put(Grade.KINDA_SHIT, 30);
        GradeGuessesLookup.put(Grade.MEH, 27);
        GradeGuessesLookup.put(Grade.OK, 25);
        GradeGuessesLookup.put(Grade.FINE, 23);
        GradeGuessesLookup.put(Grade.ALRIGHT, 20);
        GradeGuessesLookup.put(Grade.KINDA_GOOD, 17);
        GradeGuessesLookup.put(Grade.GOOD, 15);
        GradeGuessesLookup.put(Grade.QUITE_GOOD, 13);
        GradeGuessesLookup.put(Grade.REALLY_GOOD, 10);
        GradeGuessesLookup.put(Grade.REMARKABLE, 6);
        GradeGuessesLookup.put(Grade.EXTRAORDINARY, 3);
        GradeGuessesLookup.put(Grade.THE_BEST, 1);
    }
    private static Grade getGrade() {
        // it gets exponentially easier to get a grade the futher down you go.
        
       
            Grade lastGrade = Grade.WORST_EVER;

            for (Grade g : Grade.values()) {
                
            int minGuesses = GradeGuessesLookup.get(g);

            if (Guesses < minGuesses) { // Is the guesses less than the minimum required guesses for the looped grade.
                lastGrade = g; // then set last grade to current grade
            }else {
                return lastGrade; // If not then the last grade must be the highest grade you could get!
            }
        }
        System.out.println("Could not find grade."); // If something fucks up then do this and return null
        return null;
    }

    private static String gradeToString(Grade g) {
        // a monster of switch statements I should make this alot better with some more advanced tactics that maps the grade to a string name and an int tries.
        
        switch (g) {
            case WORST_EVER:
                return "Worst ever!";
            case REALLY_BAD:
                return "Really bad!";
            case QUITE_BAD:
                return "Quite bad!";
            case BAD:
                return "Bad!";
            case KINDA_SHIT:
                return "Kinda shit!";
            case MEH:
                return "meh..";
            case OK:
                return "Okay..";
            case FINE:
                return "Fine I guess.";
            case ALRIGHT:
                return "Alright.";
            case KINDA_GOOD:
                return "Kinda good?";
            case GOOD:
                return "Good";
            case QUITE_GOOD:
                return "Quite good! Nice job :)";
            case REALLY_GOOD:
                return "Really good!";
            case REMARKABLE:
                return "Remarkable! :D";
            case EXTRAORDINARY:
                return "Extraordinary :O";
            case THE_BEST:
                return "The best! <3";
            case ARE_YOU_CHEATING:
                return "Are you cheating?";
            default:
                return "Grade not found.";
        }
    }

    private static void gameStart() {
        boolean running = true;

        while (running) {

            Boolean hasWon = guessNumber(); // Makes user guess a number and saves if he has won in variable hasWon
            Guesses++;

            if (hasWon) { // Have you won?
                // If yes, then...
                running = false; // Stop the game
                win(); // Call the win() function to win.
            }
        }

        // game ended
    }

    private static int generateRandomNumber(int max) {
        Random r = new Random();
        int randomNumber = r.nextInt(max);

        return randomNumber;
    }

    private static void init() {
        Target = generateRandomNumber(Max);
        initGradeGuessesLookup();
        sc = new Scanner(System.in);
    } 

    public static boolean guessNumber() { // returns if the number guessed was the target (if you won)

        System.out.println("("+Min+"-"+Max+") Guess number:");

        boolean isValid = false;
        int inputInt = -1;
        // Loop until valid input
        while (isValid == false) {
            //User input
            String inputStr = sc.nextLine();
            inputInt = parseInput(inputStr);
            // Error checking
            if (inputInt == -1) {System.out.println("Error on parsing input. Please input a number in range: " + "("+Min+"-"+Max+")");} // Checks error on parsing

            if ( intInRange(inputInt) ) {isValid = true;} // Checks if its in range
            else {System.out.println("Number out of range: " + "("+Min+"-"+Max+")");}
        }

        boolean hasWon = checkNumber(inputInt);

        return hasWon;
        

    }

    private static void win() {// calls when user wins 
        Grade g = getGrade();

        System.out.println("Congrats! You won with stats:");
        System.out.println("Guesses: " + Guesses);
        System.out.println("Grade: " + gradeToString(g));
        
    }

    private static boolean checkNumber(int number) { // Checks if number is target and does different things



        if (number == Target) {return true;}

        else if (Target < number) {System.out.println("Number is less than " + number); return false;} // Less than, returns false
        else if (Target > number) {System.out.println("Number is greater than " + number); return false;} // Greater than returns true
        
        else {System.out.println("Your number is so special :O"); return false;} // This should'nt happen. I could make the second else/if to an else to avoid this, but i'd argue that the code would be less readable.
    }
    
    private static boolean intInRange(int number) {

        if (number >= Min && number <= Max) {//Hvis tallet er i range
            return true;
        }else {return false;}
    }

    private static int parseInput(String inputStr) {
        
        int returnInt; 

        try {
        returnInt = Integer.parseInt(inputStr); // Hvis er tal gå vidre og sæt returnInt til tallet.
        if (returnInt == -1) {System.out.println("OBS: -1 er forbeholdt fejl!"); returnInt = 0;} // Advarer om at input giver fejl.
        } catch (Exception e) // Hvis der er en fejl, så set returnInt til -1
            {returnInt = -1; } // -1 = fejl

        return returnInt; // Return resultat eller fejl. (kommer an på hvordan parsing gik)

    }
}
