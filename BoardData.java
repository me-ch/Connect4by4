//Juan Pablo Carrillo
//jpc15e
import java.util.*;

public class BoardData
{
    private int[][] frontData;
    private int[][] backData;
    private int[][] blocker;
    private int rowTotal;
    private int colTotal;

    int[] ringsCount=new int[5];
    int[] diceCount =new int[5];
    int[] blockerCount =new int[5];

    public BoardData(int row, int col, int dices,int rings,int blockers)
    {
        frontData = new int[row][col];
        backData = new int[row][col];
        blocker  = new int[row][col];
        rowTotal = row;
        colTotal = col;
        for(int i=1;i<5;i++)
        {
            ringsCount[i] = rings;
            diceCount[i] = dices;
            blockerCount[i] = blockers;

        }
    }
    public void clear()
    {
        frontData = new int[rowTotal][colTotal];
        backData = new int[rowTotal][colTotal];
        blocker  = new int[rowTotal][colTotal];
        for(int i=1;i<5;i++)
        {
            ringsCount[i]=7;
            diceCount[i]=7;
            blockerCount[i]=2;

        }
    }
    public int tickersLeft(int player)
    {
        return ringsCount[player]+diceCount[player]+blockerCount[player];
    }
    public boolean topRowFull()
    {
        for(int i=0;i<colTotal;i++)
        {
            if(frontData[0][i] == 0 || backData[0][i] == 0)
                return  false;


        }
        return true;
    }
    private int putDice(int row, int col, int player, DiceType front)
    {
        if(front == DiceType.Dice) {
            if(frontData[row][col]==0) {
                frontData[row][col] = player;
                diceCount[player]= diceCount[player]-1;
                return row;
            }
        }
        if(front == DiceType.Ring)
        {
            if(backData[row][col]==0) {
                backData[row][col] = player;
                ringsCount[player]= ringsCount[player]-1;
                return row;
            }

        }
        if(front == DiceType.Blocker)
        {
            if(frontData[row][col]==0 && backData[row][col]==0) {
                frontData[row][col] = player;
                backData[row][col] = player;
                blocker[row][col]=player;
                blockerCount[player]= blockerCount[player]-1;
                return row;
            }

        }
        return -1;
    }

    public int throwDice(int col, int player)
    {
        if(diceCount[player]<=0)
        {
            return -97;
        }

        int firstEmptyRow = -1;
        while(firstEmptyRow < rowTotal-1 && frontData[firstEmptyRow+1][col] == 0  )
            firstEmptyRow++;
        if(firstEmptyRow<0)
            return  -1;

        else
        {
            return putDice(firstEmptyRow,col,player,DiceType.Dice);
        }

    }
    public int throwRing(int col, int player)
    {
        if(ringsCount[player]<=0)
        {
            return -98;
        }


        int firstEmptyRow = -1;
        while(firstEmptyRow < rowTotal-1 && backData[firstEmptyRow+1][col] == 0  )
            firstEmptyRow++;
        if(firstEmptyRow<0)
            return  -1;

        else
        {
            return putDice(firstEmptyRow,col,player,DiceType.Ring);
        }
    }
    public int throwBlocker(int col, int player)
    {
        if(blockerCount[player]<=0)
        {
            return -99;
        }

        int lastRow = rowTotal-1;
        while(lastRow >=0 && (backData[lastRow][col]!=0 || frontData[lastRow][col] !=0 ))
        {
            lastRow--;

        }
        if(lastRow<0)
            return -1;
        else
        {
            System.out.println("blocker in row "+lastRow);
            return putDice(lastRow,col,player,DiceType.Blocker);
        }
    }

    public int getFrontDice(int row, int col)
    {
        return  frontData[row][col];
    }
    public int getBackDice(int row, int col)
    {
        return  backData[row][col];
    }
    public int getBlocker(int row, int col)
    {
        return  blocker[row][col];
    }
    public  String getDiceCounts(int player)
    {
        String counts = diceCount[player]+"D "+ringsCount[player]+"R "+blockerCount[player]+"B.";
        return counts;
    }
    public boolean isWinner(int checkRow,int checkCol,int player)
    {
       boolean horizontalWin = checkHorizontal(checkRow,checkCol,player);
       if(horizontalWin)
           return true;
        boolean verticalWin = checkVertical(checkRow,checkCol,player);
        if(verticalWin)
            return true;
        boolean diag1Win = checkDiagnoal1(checkRow,checkCol,player);
        if(diag1Win)
            return true;
        boolean dia2Win = checkDiagnoal2(checkRow,checkCol,player);
        if(dia2Win)
            return  true;

        return  false;


    }
    private boolean checkHorizontal(int checkRow, int checkCol , int player)
    {

        int start = checkCol-3 > 0 ? checkCol -3: 0;
        int end = checkCol+3 < colTotal ? checkCol + 3: colTotal-1;
        int consectiveCount=0;

        for(int i=start;i<=end;i++)
        {
            if(frontData[checkRow][i]==player || backData[checkRow][i]==player)
                consectiveCount++;
            else
                consectiveCount=0;

            if(consectiveCount == 4) {
                System.out.println("4 matches found");
                return true;
            }

        }
        return  false;
    }
    private boolean checkVertical(int checkRow, int checkCol , int player)
    {
        int start = checkRow-3 > 0 ? checkRow-3: 0;
        int end = checkRow+3 < rowTotal ? checkRow + 3: rowTotal-1;
        int consectiveCount=0;

        for(int i=start;i<=end;i++)
        {
            if(frontData[i][checkCol]==player || backData[i][checkCol]==player)
                consectiveCount++;
            else
                consectiveCount=0;

            if(consectiveCount == 4)
                return true;

        }
        return  false;
    }

    private boolean checkDiagnoal1(int checkRow, int checkCol , int player)
    {
        int startRow=checkRow;
        int endRow = checkRow;

        int startCol = checkCol;
        int endCol = checkCol;


        for(int i=1;i<4;i++)
        {
            if(startRow-1 >=0 && startCol-1 >=0) {
                startRow--;
                startCol--;
            }
            if(endRow+1 <rowTotal && endCol+1 < colTotal) {
                endRow++;
                endCol++;
            }

        }

        System.out.println("Checking diagonal 1 from "+startRow+"-"+startCol
                +" to "+endRow+" - "+endCol);
        int consectiveCount=0;
        for(int i=startRow ,j = startCol;i<=endRow && j<=endCol ;i++,j++)
        {
            if(frontData[i][j]==player || backData[i][j]==player) {
                consectiveCount++;

            }
            else {
                consectiveCount = 0;

            }

            if(consectiveCount == 4)
                return true;

        }
        return  false;
    }

    private boolean checkDiagnoal2(int checkRow, int checkCol , int player)
    {
        int startRow=checkRow;
        int endRow = checkRow;

        int startCol = checkCol;
        int endCol = checkCol;

        for(int i=1;i<4;i++)
        {
            if(startRow-1 >=0 && startCol+1 <colTotal) {
                startRow--;
                startCol++;
            }
            if(endRow+1 <rowTotal && endCol-1 >=0 ) {
                endRow++;
                endCol--;
            }

        }

        System.out.println("Checking diagonal 2 from "+startRow+"-"+startCol
                +" to "+endRow+" - "+endCol);
        int consectiveCount=0;
        for(int i=startRow ,j = startCol;i<=endRow && j>=endCol ;i++,j--)
        {
            if(frontData[i][j]==player || backData[i][j]==player) {
                consectiveCount++;

            }
            else {
                consectiveCount = 0;

            }

            if(consectiveCount == 4)
                return true;

        }
        return  false;
    }

}
