
package Menupfe;

import Main.Main;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.*;

import javax.swing.plaf.basic.BasicButtonUI;



public class Menu extends javax.swing.JPanel {

   
    public Menu() {
        initComponents();
        setOpaque(false);
        customizeButton(btn1, "Espace Etudiant");
        customizeButton(btn2, "Espace Encadreur");
        customizeButton(btn3, "Soutenance");
        customizeButton(btn4, "Info");

        
         btn1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                // Définir le focus sur le panel après le relâchement de la souris
                requestFocusInWindow();
            }
        });
        
         
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

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelmov = new javax.swing.JPanel();
        icon = new javax.swing.JLabel();
        pfelabel = new javax.swing.JLabel();
        btn2 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();

        setOpaque(false);
        setPreferredSize(new Dimension(239, 639));

        panelmov.setOpaque(false);

        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Menupfe/icon1.png")));
        pfelabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        pfelabel.setForeground(Color.WHITE);
        pfelabel.setText("PFE");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panelmov);
        panelmov.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createSequentialGroup()
                        .addComponent(icon)
                        .addGap(18, 18, 18)
                        .addComponent(pfelabel)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(icon)
                                .addComponent(pfelabel))
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
        buttonPanel.add(btn4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelmov, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelmov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

    }// </editor-fold>//GEN-END:initComponents

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        
        
        
       
    }//GEN-LAST:event_btn1ActionPerformed
     
    private void btn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn1MouseClicked
        
    }//GEN-LAST:event_btn1MouseClicked
   
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
    
    
    private int x;
    private int y;
    public void initMoving(JFrame frame)
    {
        panelmov.addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent me){
            x=me.getX();
            y=me.getY();
        }
                
                });
        panelmov.addMouseMotionListener(new MouseMotionAdapter(){
        
        @Override
        public void mouseDragged(MouseEvent me){
            frame.setLocation(me.getXOnScreen()-x,me.getYOnScreen()-y);
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
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JLabel icon;
    private javax.swing.JPanel panelmov;
    private javax.swing.JLabel pfelabel;
    // End of variables declaration//GEN-END:variables
}
