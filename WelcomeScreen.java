//Juan Pablo Carrillo
//jpc15e
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen extends JFrame {

    public WelcomeScreen()
    {
        setSize(400,400);
        getContentPane().setLayout(new BorderLayout());
        JLabel wel=new JLabel("Welcome to Juan's Connect 4X4 ");
        wel.setBounds(50,50,400,30);

        JLabel player=new JLabel("Enter the number of Player");
        JTextField playerNumber = new JTextField("4");
        player.setBounds(10,100,200,20);
        playerNumber.setBounds(250,100,80,20);

        JLabel rows=new JLabel("Enter the number of Rows");
        JTextField rowsNumber = new JTextField("6");
        rows.setBounds(10,150,200,20);
        rowsNumber.setBounds(250,150,80,20);

        JLabel cols=new JLabel("Enter the number of Columns");
        JTextField colsNumber = new JTextField("7");

        cols.setBounds(10,200,200,20);
        colsNumber.setBounds(250,200,80,20);

        JLabel dices=new JLabel("Enter the number of Dices");
        JTextField dicesNumber = new JTextField("8");
        dices.setBounds(10,250,200,20);
        dicesNumber.setBounds(250,250,80,20);

        JLabel rings=new JLabel("Enter the number of Rings");
        JTextField ringsNumber = new JTextField("7");
        rings.setBounds(10,300,200,20);
        ringsNumber.setBounds(250,300,80,20);

        JLabel blockers=new JLabel("Enter the number of Blockers");
        JTextField blockerNumber = new JTextField("2");
        blockers.setBounds(10,350,200,20);
        blockerNumber.setBounds(250,350,80,20);

        add(wel);
        add(player);
        add(playerNumber);

        add(rows);
        add(rowsNumber);

        add(cols);
        add(colsNumber);

        add(dices);
        add(dicesNumber);

        add(rings);
        add(ringsNumber);

        add(blockers);
        add(blockerNumber);

        JPanel jbPanel = new JPanel();
        JButton start=new JButton("Start Playing");

        jbPanel.setBounds(300,0,50,20);
        jbPanel.add(start);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int  rows = Integer.parseInt(rowsNumber.getText());
                int cols = Integer.parseInt(colsNumber.getText());
                int players = Integer.parseInt(playerNumber.getText());
                int  dices = Integer.parseInt(dicesNumber.getText());
                int rings = Integer.parseInt(ringsNumber.getText());
                int blockers = Integer.parseInt(blockerNumber.getText());
                boolean error =false;
                if(players > 4)
                {
                    error=true;
                  JOptionPane.showMessageDialog(null,"Max of 4 playes can play","Error",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    if(rows < 5 || cols < 5) {
                        error=true;
                        JOptionPane.showMessageDialog(null, "Grid is too small", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        if(rows > 12 || cols > 12) {
                            error=true;
                            JOptionPane.showMessageDialog(null, "Grid is too Big", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            if(!error)
            {
                BoardGame gm=new BoardGame(rows,cols,players,dices,rings,blockers);
                gm.setVisible(true);
                dispose();
            }
                System.out.println(rows +"  "+cols+"   :players: "+players);
            }
        });
        add(jbPanel);

setVisible(true);
    }
}
