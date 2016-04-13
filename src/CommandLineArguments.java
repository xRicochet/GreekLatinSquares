import java.util.Random;

/**
 * Created by xRicochet on 27-Feb-16.
 */
public class CommandLineArguments {
    private int numberOfElements;
    private StringBuilder firstSet,secondSet;

    public CommandLineArguments(String[] args){
        firstSet=new StringBuilder();
        secondSet=new StringBuilder();
        if(args.length==3)  parseArguments(args);
        else {
            System.out.println("Argumentele nu respecta formatul.Vor fi generate automat.");
            generateArguments();
        }
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public StringBuilder getFirstSet() {
        return firstSet;
    }

    public StringBuilder getSecondSet() {
        return secondSet;
    }

    private void parseArguments(String[] args){
        String[] X=new String[3];
        String delimiters="[\\[\\]\"]+";
        for (int i=0 ; i<3 ; i++) {
            String[] splittedArgs = args[i].split(delimiters);
            try{    X[i] = splittedArgs[1]; }
            catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("Argument invalid.Vor fi generate automat");
                generateArguments();
            }
        }
        numberOfElements=Integer.parseInt(X[0]);
        firstSet.insert(0,X[1]);
        X[2]=X[2].replace("\\","");
        String[] greekAlphabet=X[2].split("u");
        for (int i = 1; i < greekAlphabet.length; i++) {
            int hexVal = Integer.parseInt(greekAlphabet[i], 16);
            secondSet.append((char) hexVal);
        }
    }

    private void generateArguments(){
        Random randomGenerator = new Random();
        numberOfElements=randomGenerator.nextInt(24);

        char[] latinAlphabet={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        char[] greekAlphabet={'\u03b1','\u03b2','\u03b3','\u03b4','\u03b5','\u03b6','\u03b7','\u03b8',
                '\u03b9','\u03ba','\u03bb','\u03bc','\u03bd','\u03be','\u03bf','\u03c0','\u03c1','\u03c3',
                '\u03c4','\u03c5','\u03c6','\u03c7','\u03c8','\u03c9'};
        for(int i=0; i<numberOfElements; i++) {
            firstSet.append(latinAlphabet[i]);
            secondSet.append(greekAlphabet[i]);
        }
    }

    public void ShowArguments() {
        System.out.println("# of elements:"+numberOfElements);
        System.out.println("First Set:"+firstSet+"\nSecond Set:"+secondSet);
    }
}
