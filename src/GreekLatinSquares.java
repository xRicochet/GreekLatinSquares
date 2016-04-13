import java.util.ArrayList;
import java.util.List;

/**
 * Created by xRicochet on 28-Feb-16.
 */
public class GreekLatinSquares {
    private List<int[][]> All=new ArrayList<int[][]>();// matrici de genul 11-23-32/22-31-13/33-12-21 #1 cifra reprezinta latina & #2 greaca
    public int size;

    public int getCount() {
        return All.size();
    }

    public GreekLatinSquares(int size) {
        this.size=size;
    }

    public void GenerateGreekSquare(List<int[][]> latinSquares){
        for(int[][] eachLatinSquare:latinSquares)
            {
                int[][] greekSquare=new int[size][size];
                findGreekSquares(eachLatinSquare,greekSquare);
                //System.gc();
            }
    }

    private boolean findGreekSquares(int[][] latinSquare,int[][] greekSquare) {
        int row,col;
        int[] X=FindUnnassignedLocation(greekSquare);
        if(X[0]==-1 && X[1]==-1)
        {
            this.addCombinedGrids(latinSquare,greekSquare);
            greekSquare=null;
            return true;
        }
        row=X[0];
        col=X[1];
        /*if(row==size-1){
            greekSquare=completeLastRow(greekSquare,col);
            this.addCombinedGrids(latinSquare,greekSquare);
            greekSquare=null;
            return true;
        }*/
        for(int num=1; num<=size; num++)
            if(isSafe(latinSquare,greekSquare,row,col,num))
            {
                greekSquare[row][col]=num;
                findGreekSquares(latinSquare,greekSquare);
                greekSquare[row][col]=0;
            }
        return false;   //triggers the BKT
    }

    private int[] FindUnnassignedLocation(int grid[][]){
        int[] toReturn=new int[2];
        for(int row=0; row<size; row++)
            for(int col=0; col<size; col++)
                if(grid[row][col]==0)   {
                    toReturn[0]=row;
                    toReturn[1]=col;
                    return toReturn;
                }
        toReturn[0]=-1;
        toReturn[1]=-1;
        return toReturn;
    }

    private boolean isSafe(int[][] latinSquare,int[][] greekSquare,int row,int col,int num){
        return !usedInCol(greekSquare,col,num) && !usedInRow(greekSquare,row,num)
                && noTwoPairsTheSame(latinSquare,greekSquare,row,col,num);
    }

    private boolean usedInRow(int grid[][],int row,int num){
        for(int col=0; col<size; col++)
            if(grid[row][col]==num) return true;
        return false;
    }

    private boolean usedInCol(int grid[][],int col,int num){
        for(int row=0; row<size; row++)
            if(grid[row][col]==num) return true;
        return false;
    }

    private boolean noTwoPairsTheSame(int[][] latinSquare,int[][]greekSquare,int row,int col,int num){
        for(int i=0 ; i<=row; i++)
            for(int j=0 ; j<size; j++)
                if(latinSquare[i][j]==latinSquare[row][col] && greekSquare[i][j]==num)  return false;
        return true;
    }

    private void addCombinedGrids(int[][] latinSquare,int[][] greekSquare){
        int[][] tmp=new int[size][size];
        for(int i=0 ; i<size; i++)
            for(int j=0; j<size; j++)
                tmp[i][j]=latinSquare[i][j]*10+greekSquare[i][j];
        All.add(tmp);
        tmp=null;
        if(All.size()%100000==0)   System.out.print("\n"+All.size());
        //showTest(tmp);
    }

    private int[][] completeLastRow(int[][] grid,int col){
        int sum=sumOneToSize();
            grid[size-1][col]=sum;
            for(int row=0; row<size-1;row++)
                grid[size-1][col]-=grid[row][col];
        return grid;
    }

    private int[][] completeLastCol(int[][] grid,int row){
        grid[row][size-1]=sumOneToSize();

        for(int col=0; col<size-1; col++)
            grid[row][size-1]-=grid[row][col];
        return grid;
    }

    private int sumOneToSize(){
        int sum=0;
        for(int i=1; i<=size; i++)  sum+=i;
        return sum;
    }

    public void showTest(int[][] print){
        for(int row=0; row<size; row++)
        {
            for(int col=0; col<size;col++)
                System.out.print(" "+print[row][col]);
            System.out.println();
        }
        System.out.println();
    }

    public void showOne(int index,StringBuilder S,StringBuilder T){
        for(int row=0 ;row<size;row++)
        {//System.out.print(" " + All.get(index)[row][col]);
            for(int col=0; col<size;col++) {
                int positionS=All.get(index)[row][col]/10;
                int positionT=All.get(index)[row][col]%10;
                System.out.print(" "+S.charAt(positionS-1)+T.charAt(positionT-1));
            }
            System.out.println();
        }
        System.out.println();
    }

    public void showAll(){
        for(int[][] eachGreekLatinSquare:All)
            showTest(eachGreekLatinSquare);
    }

    public void showCount(){
        System.out.println("# of GreekLatin Squares is "+All.size());
    }
}
