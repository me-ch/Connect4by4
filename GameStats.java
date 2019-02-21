//Juan Pablo Carrillo
//jpc15e
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameStats extends JPanel
{
    private String playerStatus;
    private static final Font LABEL_FONT = new Font("Times New Roman", Font.BOLD, 20);

    JLabel label;
    JLabel label2;
    JRadioButton front;
    JRadioButton back;
    JRadioButton blocker;
    Board board;
    String diceCounts="";

    boolean gameOver;
    DiceType selectedType;

    public GameStats()
    {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        playerStatus="Player 1's turn";
        gameOver=false;
        getLabel();

    }
    public void setBoard(Board newBoard)
    {
        board = newBoard;
    }
    public void gameIsOver(){
        gameOver=true;
    }
    public void clear()
    {
        gameOver = false;
        playerStatus="Player 1's turn";
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(gameOver) {
            String[] options = new String[] { "Exit"};
            int response = JOptionPane.showOptionDialog(this, playerStatus, "GAME OVER",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);
            if(response == 0)
            {
                System.exit(0);
            }

        }
        else
        {
            label.setText(playerStatus);
            label2.setText(diceCounts);
        }

    }

    public DiceType getSelectedType()
    {
        return selectedType;
    }
    public void setPlayerStatus(String status)
    {
        playerStatus=status;
    }
    public void setDiceCounts(String counts)
    {
        diceCounts = counts;
    }
    public void getLabel() {
       // removeAll();
        label = new JLabel(playerStatus, SwingConstants.LEFT);
        label.setForeground(Color.BLACK);
        label.setFont(LABEL_FONT);
        label.setOpaque(true);

        add(label);
        label2 = new JLabel(diceCounts, SwingConstants.LEFT);
        label2.setForeground(Color.BLACK);
        label2.setFont(LABEL_FONT);
        label2.setOpaque(true);

        add(label2);

        ButtonGroup radios = new ButtonGroup();
         front = new JRadioButton("Dice");
        front.setBounds(0,10,40,30);
        front.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Dice");

                selectedType=DiceType.Dice;
            }

        });
        back = new JRadioButton("Ring");
        back.setBounds(0,10,40,30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ring");

                selectedType = DiceType.Ring;
            }

        });

        blocker= new JRadioButton("Blocker");
        blocker.setBounds(0,10,40,30);
        blocker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Blocker");

                selectedType=DiceType.Blocker;
            }

        });

        radios.add(front);
        radios.add(back);
        radios.add(blocker);


        add(front);
        add(back);
        add(blocker);
    }


}
