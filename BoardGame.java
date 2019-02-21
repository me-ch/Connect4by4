//Juan Pablo Carrillo
//jpc15e
import javax.swing.*;
import java.awt.*;


public class BoardGame extends JFrame
{
    private Board gameBoard;
    private GameStats gm;
    private JLabel labelUsername = new JLabel("Player Playing: ");
    private JTextField textUsername = new JTextField(20);
    private int rows = 6;
    private  int cols =7;
    private  int players=4;
    private int dices_ ;
    private  int rings_ ;
    private  int blockers_;

    public BoardGame(int row, int col, int player,int dices, int rings, int blockers)
    {
        super("Connnect 4");
        rows=row;
        cols = col;
        players = player;
        dices_=dices;
        rings_=rings;
        blockers_ = blockers;

        getContentPane().setLayout(new BorderLayout());
        setSize(600,600);
        gm = new GameStats();

        gameBoard=new Board(row ,col,players,dices,rings,blockers,gm);

        getContentPane().add(gm, BorderLayout.PAGE_START);
        getContentPane().add(gameBoard, BorderLayout.CENTER);

        pack();
        setVisible(true);


    }




}
