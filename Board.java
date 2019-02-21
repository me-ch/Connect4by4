//Juan Pablo Carrillo
//jpc15e

import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Board extends JPanel implements MouseListener
{
    private int player_playing;
    private int totalPlayer;
    private BoardData data;
    private GameStats stats;

    private int gridRows;
    private int gridCols;
    private int Board_Size_X;
    private int Board_Size_Y;
    private int minSize;
    private int left;
    private int top;
    private int menu;
    private int seperation;
    private int blockSize;
    private int circleRadius;



    public Board(int row, int col,int players,int dices,int rings,int blockers,GameStats stat)
    {
        this.gridRows = row;
        this.gridCols = col;
        player_playing=1;
        totalPlayer = players;
        data = new BoardData(row,col,dices,rings,blockers);
        stats=stat;
        stat.setDiceCounts(data.getDiceCounts(player_playing));

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(600,550));
        addMouseListener(this);
    }




    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        seperation = 20;
        menu =20;
        Board_Size_X = getWidth();
        Board_Size_Y = getHeight();

        minSize = Board_Size_X > Board_Size_Y? Board_Size_Y:Board_Size_X;
        if(Board_Size_X > Board_Size_Y) {
            minSize = Board_Size_Y;
            left = (Board_Size_X-minSize)/2;
            top = seperation;
        }
        else
        {
            minSize = Board_Size_X;
            left=seperation;
            top = (Board_Size_Y - minSize)/2;
        }

        top = top+menu;
        circleRadius = (minSize  - ((gridCols+2) * seperation))/ gridCols;
        drawBoard(g);

    }
    public void drawBoard(Graphics g)
    {

        int blockSize = (circleRadius) + seperation;
        this.setBackground(GameColors.boardColor);

        for(int i = 0; i< gridRows; i++) {
            for (int j = 0; j < gridCols; j++) {

                drawRing(g,i,j);
            }
        }

    }

    public void drawRing(Graphics g, int i ,int j)
    {
         blockSize = (circleRadius) + seperation;
        int innerColor = data.getFrontDice(i,j);
        int outerColor = data.getBackDice(i,j);
        int blocker = data.getBlocker(i,j);
        int ringSize =seperation/3;

        //click black area on the top
        if(i==0)
        {
            g.setColor(Color.BLACK);
            g.fillRect(left +(blockSize * j)-ringSize/2, top-menu-ringSize/2,circleRadius,circleRadius/4);
        }


            g.setColor(GameColors.getColor(outerColor));
            g.fillOval(left + (blockSize * j) - ringSize / 2, top + (blockSize * i) - ringSize / 2, circleRadius + ringSize, circleRadius + ringSize);
            g.setColor(GameColors.getColor(innerColor));
            g.fillOval(left + (blockSize * j), top + (blockSize * i), circleRadius, circleRadius);

        if(blocker!=0)
        {
            g.setColor(Color.BLACK);

            int topleftX = left + (blockSize * j);
            int topLeftY = top +(blockSize *i );
            g.drawLine(topleftX+circleRadius/3,topLeftY+circleRadius/3,topleftX+circleRadius-circleRadius/3,topLeftY+circleRadius-circleRadius/3);
            g.drawLine(topleftX+circleRadius/3,topLeftY+circleRadius-circleRadius/3,topleftX+circleRadius-circleRadius/3,topLeftY+circleRadius/3);

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int xCord = e.getX();
        int yCord = e.getY();
        if(data.tickersLeft(totalPlayer)>0 && !data.topRowFull()) {
            dropTheDice(xCord, yCord);
        }
        else
        {

            String[] options = new String[] { "Exit"};
            int response = JOptionPane.showOptionDialog(this, "Game is a Tie", "GAME OVER",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);
            if(response == 0)
            {
                System.exit(0);
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

public void dropTheDice(int xCord, int yCord)
{
    int ringSize =seperation/3;
        int yCordBox = top-menu-ringSize/2;
        if(yCord > yCordBox && yCord < (yCordBox +circleRadius/4))
        {

            int columNumber = (xCord-left+ringSize/2)/blockSize ;
            if(left +(blockSize * columNumber) +circleRadius > xCord)
            {
                System.out.println("clicked "+columNumber);
                int success = -1;
                DiceType selectedUIRadio = stats.getSelectedType();

                if(selectedUIRadio == DiceType.Dice) {
                     success = data.throwDice(columNumber, player_playing);

                }
                else if(selectedUIRadio == DiceType.Ring)
                {
                    success = data.throwRing(columNumber, player_playing);
                }
                else if(selectedUIRadio == DiceType.Blocker)
                {
                    success = data.throwBlocker(columNumber, player_playing);
                }

                if(success >= 0) {
                    boolean winner = data.isWinner(success,columNumber,player_playing);
                    if(winner)
                    {
                        stats.setPlayerStatus("Player "+player_playing+" wins");
                        repaint();
                        stats.gameIsOver();
                    }
                    else {
                        player_playing = player_playing % totalPlayer + 1;
                        stats.setPlayerStatus( "Player "+player_playing+"'s turn");
                        String diceCounts = data.getDiceCounts(player_playing);
                        stats.setDiceCounts(diceCounts);
                        repaint();
                    }


                    stats.repaint();
                }
                else
                {
                    String things="";
                    if(success == -99 )
                        things="blockers";
                    if(success == -98)
                        things="rings";
                    if(success== -97)
                        things =" dices";
                    JOptionPane.showMessageDialog(null,"you dont have any "+things+" left","Error",JOptionPane.ERROR_MESSAGE);
                }
            }

        }
}

}
