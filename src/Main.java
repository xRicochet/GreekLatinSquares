import java.util.Random;

public class Main {
    /*
      Write a Java application that generates and prints on the screen Graeco-Latin squares. A Graeco-Latin Square is a n x n matrix. Each cell of the matrix is a pair of elements taken from the carthesian product of two given sets: S X T; for example, S may be a subset of the Latin alphabet and T a subset of the Greek alphabet.
      The constraints are:
      In each row, and in each column, all the 2n elements are different.
      No two pairs are the same in the matrix.
     Input: [5] [ABCDE] [\u03B1\u03B2\u03B3\u03B4\u03B5]
    */
    public static void showExample(){
        StringBuilder[][] example=new StringBuilder[3][3];
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                example[i][j]=new StringBuilder();

        example[0][0].insert(0,'A').append('\u03B1');
        example[0][1].insert(0,'B').append('\u03B3');
        example[0][2].insert(0,'C').append('\u03B2');

        example[1][0].insert(0,'B').append('\u03B2');
        example[1][1].insert(0,'C').append('\u03B1');
        example[1][2].insert(0,'A').append('\u03B3');

        example[2][0].insert(0,'C').append('\u03B3');
        example[2][1].insert(0,'A').append('\u03B2');
        example[2][2].insert(0,'B').append('\u03B1');

        System.out.println("Example:");
        for(int i=0 ; i<3 ; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(" "+example[i][j]);
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {

        final double startTime=System.currentTimeMillis();

        showExample();

        CommandLineArguments CMD=new CommandLineArguments(args);
        CMD.ShowArguments();

        AllLatinSquares X=new AllLatinSquares(CMD.getNumberOfElements());
        int[][] grid=new int[CMD.getNumberOfElements()][CMD.getNumberOfElements()];   //replace with .getNumberOfElements()
        X.GenerateAllLatinSquares(grid);
        //X.showAll();
        X.showCount();  //# of Latin Squares is ...

        GreekLatinSquares XX=new GreekLatinSquares(CMD.getNumberOfElements());
        XX.GenerateGreekSquare(X.getAllLatinSquares());

        Random randomGenerator = new Random();
        int diez=randomGenerator.nextInt(XX.getCount());
        System.out.println("\nPrint #"+diez+" GreekLatinSquare:");


        XX.showOne(diez,CMD.getFirstSet(),CMD.getSecondSet());
        //XX.showAll();
        XX.showCount(); //# of GreekLatin Squares is ...

        final double duration=(System.currentTimeMillis()-startTime)/1000;
        System.out.println("\nRunning time of the app:"+duration+" secs.");
    }
}
