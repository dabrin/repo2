package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyPanel extends JPanel implements ActionListener, KeyListener {

    private Color cafe = new Color(97, 55, 5);
    private Color naranja = new Color(240, 118, 19);
    private Color piel = new Color(252, 165, 59);

    private JButton jbtnAumento;
    private JButton jbtnDisminucion;
    private JButton jbtnIzquierda;
    private JButton jbtnDerecha;
    private JButton jbtnArriba;
    private JButton jbtnAbajo;

    private JFrame frame;

    private int posX = 0;
    private int posY = 0;
    private int num = 1;

    private int[][] matriz;

    public MyPanel(JFrame frame) {
        super();
        this.frame = frame;
        this.setPreferredSize(new Dimension(900, 700));
        this.addKeyListener(this);
        this.setFocusable(true);
        init();
    }//constructor

    public void init() {
        this.jbtnAumento = new JButton("Aumentar");
        this.jbtnAumento.addActionListener(this);

        this.jbtnDisminucion = new JButton("Disminuir");
        this.jbtnDisminucion.addActionListener(this);
        this.jbtnDisminucion.setEnabled(false);

        this.jbtnIzquierda = new JButton("Izquierda");
        this.jbtnIzquierda.addActionListener(this);

        this.jbtnDerecha = new JButton("Derecha");
        this.jbtnDerecha.addActionListener(this);

        this.jbtnArriba = new JButton("Arriba");
        this.jbtnArriba.addActionListener(this);

        this.jbtnAbajo = new JButton("Abajo");
        this.jbtnAbajo.addActionListener(this);

        this.add(jbtnAumento);
        this.add(jbtnDisminucion);
        this.add(jbtnIzquierda);
        this.add(jbtnArriba);
        this.add(jbtnAbajo);
        this.add(jbtnDerecha);

        this.jbtnDisminucion.setFocusable(false);
        this.jbtnAumento.setFocusable(false);
        this.jbtnIzquierda.setFocusable(false);
        this.jbtnArriba.setFocusable(false);
        this.jbtnAbajo.setFocusable(false);
        this.jbtnDerecha.setFocusable(false);
    }//init

    public void renderizar(Graphics g) {
        if (matriz != null) {
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[0].length; j++) {
                    if (matriz[i][j] == 2) {
                        g.setColor(piel);
                        g.fillRect(posX + (j * num), posY + (i * num), num, num);
                    } else if (matriz[i][j] == 4) {
                        g.setColor(Color.BLACK);
                        g.fillRect(posX + (j * num), posY + (i * num), num, num);
                    } else if (matriz[i][j] == 7) {
                        g.setColor(cafe);
                        g.fillRect(posX + (j * num), posY + (i * num), num, num);
                    } else if (matriz[i][j] == 9) {
                        g.setColor(naranja);
                        g.fillRect(posX + (j * num), posY + (i * num), num, num);
                    }
                }//For interno
            }//For externo
            this.frame.revalidate();
            this.frame.repaint();
        }//if validar matriz
    }//renderizar

    @Override
    protected void paintComponent(Graphics g) {
        renderizar(g);
    }//paintComponent

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jbtnAumento) {
            if (this.posX < (this.getWidth() - (this.matriz[0].length * (this.num + 2))) && this.posY < (this.getHeight() - (this.matriz.length * (this.num + 2)))) {
                this.num += 1;
            }
        }//if jbtnAumento
        else if (e.getSource() == this.jbtnDisminucion) {
            this.num -= 1;
        }//if jbtnDisminucion
        else if (e.getSource() == this.jbtnIzquierda) {
            if (this.posX > 0) {
                this.posX -= 1;
            }
        }//if jbtnDisminucion
        else if (e.getSource() == this.jbtnDerecha) {
            if (this.posX < (this.getWidth() - (this.matriz[0].length * this.num))) {
                this.posX += 1;
            }
        }//if jbtnDisminucion
        else if (e.getSource() == this.jbtnArriba) {
            if (this.posY > 0) {
                this.posY -= 1;
            }
        }//if jbtnDisminucion
        else if (e.getSource() == this.jbtnAbajo) {
            if (this.posY < (this.getHeight() - (this.matriz.length * this.num))) {
                this.posY += 1;
            }
        }//if jbtnDisminucion

        if (this.num == 1) {
            this.jbtnDisminucion.setEnabled(false);
        } else {
            this.jbtnDisminucion.setEnabled(true);
        }

        if (this.num == 10) {
            this.jbtnAumento.setEnabled(false);
        } else {
            this.jbtnAumento.setEnabled(true);
        }
    }//actionPerformed

    @Override
    public void keyTyped(KeyEvent e) {
    }//keyTyped

    @Override
    public void keyPressed(KeyEvent e) {
        if (KeyEvent.VK_A == e.getKeyCode()) {
            if (this.num < 10 && this.posX < (this.getWidth() - (this.matriz[0].length * this.num)) && this.posY < (this.getHeight() - (this.matriz.length * this.num))) {
                this.jbtnDisminucion.setEnabled(true);
                if (this.posX < (this.getWidth() - (this.matriz[0].length * (this.num + 2))) && this.posY < (this.getHeight() - (this.matriz.length * (this.num + 2)))) {
                    this.num += 1;
                    if (this.num == 10) {
                        this.jbtnAumento.setEnabled(false);
                    }
                }
            }
        }//Presionan la tecla A
        else if (KeyEvent.VK_D == e.getKeyCode()) {
            if (this.num > 1) {
                this.jbtnAumento.setEnabled(true);
                this.num -= 1;
                if (this.num == 1) {
                    this.jbtnDisminucion.setEnabled(false);
                }
            }
        }//Presionan la tecla B
        else if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
            if (this.posX < (this.getWidth() - (this.matriz[0].length * this.num))) {
                this.posX += 1;
            }
        }//Presionan la flecha derecha
        else if (KeyEvent.VK_LEFT == e.getKeyCode()) {
            if (this.posX > 0) {
                this.posX -= 1;
            }
        }//Presionan la flecha izquierda
        else if (KeyEvent.VK_UP == e.getKeyCode()) {
            if (this.posY > 0) {
                this.posY -= 1;
            }
        }//Presionan la flecha arriba
        else if (KeyEvent.VK_DOWN == e.getKeyCode()) {
            if (this.posY < (this.getHeight() - (this.matriz.length * this.num))) {
                this.posY += 1;
            }
        }//Presionan la flecha abajo
    }//keyPressed

    @Override
    public void keyReleased(KeyEvent e) {
    }//keyReleased

    public int[][] getMatriz() {
        return matriz;
    }//getMatriz

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }//setMatriz
    
}//class
