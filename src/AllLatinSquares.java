import java.util.ArrayList;
import java.util.List;

/**
 * Created by xRicochet on 27-Feb-16.
 */
public class AllLatinSquares {
    private List<int[][]> allLatinSquares=new ArrayList<>(); //matrici de genul 1-2-3/2-3-1/3-1-2
    private int size;

    public AllLatinSquares(int size) {
        this.size = size;
    }

    public void addGrid(int[][] grid) {
        int[][] tmp= new int[size][size];
        for(int i=0 ; i<size; i++)  for(int j=0;j<size;j++) tmp[i][j]=grid[i][j];
        allLatinSquares.add(tmp);
        tmp=null;
    }

    public void showAll() {
        for(int[][] eachGrid:allLatinSquares){
            for(int row=0 ;row<eachGrid.length;row++)
            {
                for(int col=0; col<eachGrid.length;col++)
                    System.out.print(" "+eachGrid[row][col]);
                System.out.println();
            }
            System.out.println();
        }
    }

    public void showOne(int index){
        for(int row=0 ;row<size;row++)
        {
            for(int col=0; col<size;col++)
                System.out.print(" "+allLatinSquares.get(index)[row][col]);
            System.out.println();
        }
        System.out.println();
    }

    public int getSize() {
        return allLatinSquares.size();
    }

    public List<int[][]> getAllLatinSquares() {
        return allLatinSquares;
    }

    public boolean GenerateAllLatinSquares(int[][] grid){
        int row,col;
        int[] X=FindUnnassignedLocation(grid);

        if(X[0]==-1 && X[1]==-1)
        {
            this.addGrid(grid);
            return true;    //SquareFullCompleted
        }
        row=X[0];
        col=X[1];

        for(int num=1; num<=size ; num++)
            if(isSafe(grid,row,col,num))//test if we can put num in the grid
            {
                grid[row][col]=num;
                GenerateAllLatinSquares(grid);
                grid[row][col]=0;
            }
        return false;   //triggers BKT
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

    private boolean isSafe(int grid[][],int row,int col,int num){
        return !usedInCol(grid,col,num) && !usedInRow(grid,row,num);
    }

    public void showCount(){
        System.out.println("# of Latin Squares is "+allLatinSquares.size());
    }
}
