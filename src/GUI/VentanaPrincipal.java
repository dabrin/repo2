package GUI;

import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame {

    public MyPanel myPanel;

    public VentanaPrincipal() {
        super();
        this.setSize(900, 700);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        init();
    }//constructor

    public void init() {
        this.myPanel = new MyPanel(this);
        this.myPanel.setBounds(0, 0, 900, 700);
        this.add(this.myPanel);
    }//init
}//clase
