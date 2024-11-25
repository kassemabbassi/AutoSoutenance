/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Soutenance;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author Wind10
 */
public class menusoutenance extends javax.swing.JPanel {

    /**
     * Creates new form menusoutenance
     */
    public menusoutenance() {
        initComponents();
         setOpaque(false);
         panel.setOpaque(false);
        customizeButton(btn1, "Ajouter Soutenance");
        customizeButton(btn2, "Valider Soutenance");
        customizeButton(btn3, "Recherche et Affichage");
        customizeButton(btn4, "Archive");
        customizeButton(btn5,"Jurys");

    }
    private void customizeButton(JButton button, String text) {
        button.setText(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Effet hover
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setOpaque(true);
                button.setBackground(new Color(41, 128, 185)); // Bleu clair
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setOpaque(false);
                button.setBackground(new Color(0, 0, 0, 0));
            }
        });
    }
    public JButton getBtn1()
    {
        return btn1;
    }
    public JButton getBtn2()
    {
        return btn2;
    }
    public JButton getBtn3()
    {
        return btn3;
    }
    public JButton getBtn4()
    {
        return btn4;
    }
    public JButton getBtn5()
    {
        return btn5;
    }
    @Override
    protected void paintChildren(Graphics grph)
    {
        Graphics2D g2=(Graphics2D)grph;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g=new GradientPaint(0,0,Color.decode("#2980b9"),0,getHeight(),Color.decode("#2c3e50"));
        g2.setPaint(g);
        g2.fillRoundRect(0,0,getWidth(),getHeight(),15,15);
        super.paintChildren(grph);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new Dimension(239, 639));

        panel.setOpaque(false);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Soutenance/souticon.png")));
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setText("Soutenance");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1))
        );

        // Configuration du JPanel pour les boutons avec un espacement vertical plus grand
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 0, 30)); // Espacement vertical augmenté à 30 pixels
        buttonPanel.setOpaque(false);

        // Ajouter un espace vertical avant les boutons pour les déplacer vers le bas
        buttonPanel.add(Box.createVerticalStrut(100)); // Espace de 100 pixels avant les boutons
        buttonPanel.add(btn1);
        buttonPanel.add(btn2);
        buttonPanel.add(btn3);
        buttonPanel.add(btn5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}