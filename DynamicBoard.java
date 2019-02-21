//Juan Pablo Carrillo
//jpc15e
import java.util.ArrayList;

public class DynamicBoard {


    private ArrayList<ArrayList<Integer>>  boardData;
    int rowTotal;
    int colTotal;
    //extra point dynamic board
    public DynamicBoard(int row, int col )
    {
        System.out.println("initializing board");
        rowTotal = row;
        colTotal = col;
        boardData = new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<row;i++)
        {
            ArrayList<Integer> allcols = new ArrayList<Integer>();

            for(int j=0;j<col;j++)
            {
                allcols.add(0);
            }
            boardData.add(allcols);
        }

    }

    public boolean putDice(int row, int col , int front)
    {
        ArrayList<Integer> rowList= boardData.get(row-1);
        rowList.set(col-1,1);
        return true;
    }

    public int getDice(int row, int col , int front)
    {
        System.out.println("accesing "+(row-1)+" and "+(col-1));
        ArrayList<Integer> rowList= boardData.get(row-1);
        System.out.println(" returning "+rowList.get(col-1));
        return rowList.get(col-1);

    }
}
