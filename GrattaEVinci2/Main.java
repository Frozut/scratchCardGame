package GrattaEVinci2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static int guadagno = 50;
    public static int numero=200;
    public static int tentativi=0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("GRATTA E VINCI");
        final DrawPad drawPad = new DrawPad();
        frame.add(drawPad, BorderLayout.CENTER);
        JButton clearButton = new JButton("Nuovo Gratta e Vinci");
        JLabel soldi=new JLabel("soldi : "+guadagno+"                       tentativi : "+tentativi);
        soldi.setForeground(Color.white);
        soldi.setOpaque(true);
        soldi.setBackground(Color.black);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                soldi.setText("soldi : "+Vincita()+"                       tentativi : "+tentativi());
                drawPad.clear();
            }
        });
        frame.add(soldi,BorderLayout.NORTH);
        frame.add(clearButton, BorderLayout.SOUTH);
        frame.setSize(268, 378);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        JOptionPane.showMessageDialog(null,"Tieni premuto il tasto sinistro del mouse e \n " +
                "trascinalo sul Gratta e vinci , ver vedere se hai vinto ,\n" +
                "il numero in alto indica i SOLDI che guadagni e perdi ogni volta che |acquisti| un biglietto\n" +
                "ogni biglietto costa 10 euro, inizierai con un baget di 50 euro\n" +
                "ovviamente si potrà andare in negativo.\n" +
                "più faccine hai più soldi vinci :\n"+
                "4 faccine= 100 €\n"+
                "6 faccine= 200 €\n"+
                "7 faccine= 500 €\n"+
                "9 faccine= 10000 €\n"+
                "11 faccine= 20000 €\n"+
                "13 faccine= 500000 €\n"+
                "15 faccine= 2000000 €");
    }

    private static String Vincita() {
        int vincita=0;
        int [] premi = {2000000, 500000, 20000, 10000, 500, 200, 100};
        numero=(int) (Math.random() * 1000)+1;
        System.out.println(numero+"\n");
        if (numero <= 7) {
            switch (numero) {
                case 1:
                    vincita=premi[6];
                    break;
                case 2:
                    vincita=premi[5];
                    break;
                case 3:
                    vincita=premi[4];
                    break;
                case 4:
                    vincita=premi[3];
                    break;
                case 5:
                    vincita=premi[2];
                    break;
                case 6:
                    vincita=premi[1];
                    break;
                case 7:
                    vincita=premi[0];
                    break;
            }
            guadagno=guadagno + vincita;
        }

        else {
            guadagno = guadagno- 10;
        }
        return String.valueOf(guadagno);
    }
    public static String tentativi(){
        tentativi++;
        return String.valueOf(tentativi);
    }
}