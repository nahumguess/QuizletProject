import java.util.*;

public class QuizletV2 {
    //Creation of linked hashmap used to represent a study-set.
    public static LinkedHashMap<String, String> studySet = new LinkedHashMap<>();

    /**
     * The addTerm method simply adds terms with corresponding definitions into a linked hash map, studySet. The terms and
     * definitions are acquired by the user.
     *
     * @param newSet
     */
    public static void addTerms(HashMap<String, String> newSet){
        Scanner scanner = new Scanner(System.in);

        //Term of set. First part of map.
        String term;
        //Definition of set. Second part of map.
        String definition;
        //Variable used to check if user wants to continue.
        String carryOn;

        //While user wants to continue to add terms
        do {
            System.out.println("Term?");
            //Get term from user.
            term = scanner.nextLine();
            System.out.println("Definition?");
            //Get definition from user.
            definition = scanner.nextLine();
            //Place term and definition in the map.
            newSet.put(term,definition);

            //Ask user if they wish to continue to add more terms. If yes, the loop will continue, if not the loop will end.
            System.out.println("Press \"Y\"/\"y\" to add more terms to the set.");
            carryOn = scanner.nextLine();
        }while (carryOn.equals("Y") || carryOn.equals("y"));
    }

    /**
     * The mapTermsToArray functions takes the terms from studySet, and places them into a String array. That string array
     * is returned.
     *
     * This String array from this method is used in other methods to easier navigate through studySet.
     *
     * @return arrayTerms
     */
    public static String[] mapTermsToArray(){
        Set<String> setTerms = studySet.keySet();
        String[] arrayTerms = setTerms.toArray( new String[setTerms.size()] );
        return arrayTerms;
    }
    /**
     * The mapDefinitionsToArray functions takes the definitions from studySet, and places them into a String array. That
     * string array is returned.
     *
     * This String array from this method is used in other methods to easier navigate through studySet.
     *
     * @return arrayDefinitions
     */
    public static String[] mapDefinitionsToArray(){
        Collection<String> values = studySet.values();
        String[] arrayDefinitions = values.toArray( new String[values.size()] );
        return arrayDefinitions;
    }

    /**
     * The flashcards method mimics real flashcards. A term is displayed and the user is asked if they wish to flip the
     * card/reveal the definition. When revealed, the user is asked to proceed to the next card until there is no more
     * cards in the set.
     */
    public static void flashcards(){
        Scanner scanner = new Scanner(System.in);
        //Declare flip String. Later used to confirm whether user wishes to flip the card or not.
       String flip;
       //Declare goToNext String. Later used to confirm whether user wishes to proceed to the next card or not.
       String goToNext;

       //This loop iterate as long as the amount of terms in studySet.
       for(int i = 0; i < mapTermsToArray().length; i++){
           //Display the term corresponding to the current element in the loop.
           System.out.println(mapTermsToArray()[i] + ": ");
           //Ask the user if they wish to flip the card.
           System.out.println("Enter \"Flip\" to display the definition.");
           flip = scanner.nextLine();
           //If the user does not want to flip the card, loop until they wish to flip.
           while(!flip.equals("Flip")){
               System.out.println("Enter \"Flip\" to flip the card.");
               flip = scanner.nextLine();
           }
           //If the user flipped the card, display the term and definition.
           System.out.println(mapTermsToArray()[i] + " : " + mapDefinitionsToArray()[i]);
           //If the card being looked at is not the last card...
           if (i != mapTermsToArray().length - 1){
               //Ask the user if they want to go to the next card.
               System.out.println("Enter \"Next\" to move onto the next card.");
               goToNext = scanner.nextLine();
               //If the user doesn't want to go to the next card, ask them until they wish to go to the next card.
               while(!goToNext.equals("Next")){
                   System.out.println("Enter \"Next\" to move onto the next card.");
                   goToNext = scanner.nextLine();
               }
           }
       }
    }

    /**
     * The learnDefinitions method is meant to teach the user to memorize the definitions in studySet.
     *
     * The user is asked to enter a definition based on the term displayed. If the user enters the definition correctly,
     * display a correct text indicating they got the answer correct. If the user enters the definition incorrectly,
     * display the correct definition and a text saying that they are wrong. Loop until all terms are evaluated.
     */
    public static void learnDefinitions(){
        Scanner scanner = new Scanner(System.in);
        int amountOfTerms = studySet.size();
        String answer;

        for(int i = 0; i < amountOfTerms; i++){
            System.out.println("Term: ");
            System.out.println(mapTermsToArray()[i]);
            System.out.println("\nWhat is the definition?");
            answer = scanner.nextLine();
            if(answer.equals(mapDefinitionsToArray()[i])){
                System.out.println("Correct!\n");
            }
            else{
                System.out.println("Not quite. The correct definition is\n" + mapDefinitionsToArray()[i]);
            }
        }
    }
    /**
     * The learnTerms method is meant to teach the user to memorize the terms in studySet.
     *
     * The user is asked to enter a terms based on the definition displayed. If the user enters the term correctly,
     * display a correct text indicating they got the answer correct. If the user enters the term incorrectly,
     * display the correct term and a text saying that they are wrong. Loop until all definitions are evaluated.
     */
    public static void learnTerms() {
        Scanner scanner = new Scanner(System.in);
        int amountOfTerms = studySet.size();
        String answer;

        for (int i = 0; i < amountOfTerms; i++) {
            System.out.println("Definition: ");
            System.out.println(mapDefinitionsToArray()[i]);
            System.out.println("\nWhat is the term?");
            answer = scanner.nextLine();
            if (answer.equals(mapTermsToArray()[i])) {
                System.out.println("Correct!\n");
            } else {
                System.out.println("Not quite. The correct term is\n" + mapTermsToArray()[i]);
            }
        }
    }
    /**
     * The examTerms method tests the user on definitions provided terms. If the correct definition is provided, the score
     * increases.
     *
     */
    public static void examTerms(){
        Scanner scanner = new Scanner(System.in);
        //Create a double out of the size of the studySet.
        double totalScore = (double) studySet.size();
        //Declare and initialize the score to 0.0;
        double score = 0.0;
        String answer;
        String wrongAnswers = "";

        //Declare and initialize questionNumber to 1 because it starts at question 1.
        int questionNumber = 1;

        //This loop should iterate as much as the total score and should increment i and the question number after each iteration.
        for(int i = 0; i < totalScore; i++, questionNumber++){
            //Display the question number.
            System.out.println("Question " + questionNumber);
            //Display the term.
            System.out.println("Term: ");
            System.out.println(mapTermsToArray()[i]);
            //Ask the user what is the definition of the term.
            System.out.println("\nWhat is the definition?");
            answer = scanner.nextLine();
            //If the user is right, increment the score.
            if(answer.equals(mapDefinitionsToArray()[i])){
                score += 1.0;
            }
            //If the user is wrong, add the question number they missed to a string.
            else{
                wrongAnswers += "You missed question " + Integer.toString(questionNumber) + "\n";
            }
        }
        //Display missed questions.
        System.out.println(wrongAnswers);
        //Display results.
        System.out.println("\nYou scored " + score + " out of " + totalScore + " or " + score/totalScore);
    }
    /**
     * The examDefinitions method tests the user on terms provided definitions. If the correct term is provided, the score
     * increases.
     *
     */
    public static void examDefinitions(){
        Scanner scanner = new Scanner(System.in);
        //Create a double out of the size of the studySet.
        double totalScore = (double) studySet.size();
        //Declare and initialize the score to 0.0;
        double score = 0.0;
        String answer;
        String wrongAnswers = "";

        //Declare and initialize questionNumber to 1 because it starts at question 1.
        int questionNumber = 1;

        //This loop should iterate as much as the total score and should increment i and the question number after each iteration.
        for(int i = 0; i < totalScore; i++, questionNumber++){
            //Display the question number.
            System.out.println("Question " + questionNumber);
            //Display the Definition.
            System.out.println("Definition: ");
            System.out.println(mapDefinitionsToArray()[i]);
            //Ask the user for the corresponding term.
            System.out.println("\nWhat is the term?");
            answer = scanner.nextLine();
            //If the user is right, increment the score.
            if(answer.equals(mapTermsToArray()[i])){
                score += 1.0;
            }
            //If the user is wrong, add the question number they missed to a string.
            else{
                wrongAnswers += "You missed question " + Integer.toString(questionNumber) + "\n";
            }
        }
        //Display missed questions.
        System.out.println(wrongAnswers);
        //Display results.
        System.out.println("\nYou scored " + score + " out of " + totalScore + " or " + score/totalScore);
    }



    public static void main(String[] args) {
        addTerms(studySet);
        flashcards();
        learnDefinitions();
        learnTerms();
        examTerms();
        examDefinitions();
    }
}
