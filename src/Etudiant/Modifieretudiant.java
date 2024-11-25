/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Etudiant;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Timer;

/**
 *
 * @author Wind10
 */
public class Modifieretudiant extends javax.swing.JFrame {

    /**
     * Creates new form Modifieretudiant
     */
    public Modifieretudiant() {
        initComponents();
        jPanel1.setBackground(Color.decode("#E0EAFC"));
        setLocation(346,89);
        btn1.setOpaque(false);
        btn1.setBorder(BorderFactory.createEmptyBorder());
        btn1.setBackground(new Color(0,0,0,0));
        
        btn2.setOpaque(false);
        btn2.setBorder(BorderFactory.createEmptyBorder());
        btn2.setBackground(new Color(0,0,0,0));
        
       
        
        menu.setOpaque(false);
        menu.setContentAreaFilled(false);
        menu.setBorderPainted(false);
        menu.setBorder(BorderFactory.createEmptyBorder());
        menu.setBackground(new Color(0,0,0,0));
    }
    private final Modiftel mtel=new Modiftel();
    private final Modifemail memail=new Modifemail();
    
     private void fermeture() {
    // Obtenir les dimensions actuelles de la fenêtre
    final int initialWidth = getWidth();
    final int initialHeight = getHeight();

    // Créer un timer pour réduire progressivement la taille de la fenêtre
    Timer shrinkTimer = new Timer(50, new ActionListener() {
        int width = initialWidth; // Utilisation d'une variable locale finale pour la largeur initiale
        int height = initialHeight; // Utilisation d'une variable locale finale pour la hauteur initiale

        @Override
        public void actionPerformed(ActionEvent e) {
            width -= 10; // Diminuer la largeur de 10 pixels à chaque itération
            height -= 10; // Diminuer la hauteur de 10 pixels à chaque itération

            setSize(width, height);

            // Arrêter le timer et fermer la fenêtre lorsque la taille atteint 0
            if (width <= 20 || height <= 20) {
                ((Timer) e.getSource()).stop();
                
                dispose(); // Fermer complètement la fenêtre
            }
        }
    });
    
    // Démarrer le timer
    shrinkTimer.start();
}
    /**
     * @param args the command line arguments
     */
     
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        menu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(260, 639));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btn1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn1.setText("Modifier Téléphone");
        btn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn1MouseClicked(evt);
            }
        });

        btn2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn2.setText("Modifier Email");
        btn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn2MouseClicked(evt);
            }
        });

        menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Encadreur/menu (1).png"))); // NOI18N
        menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn1)
                    .addComponent(btn2))
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145)
                .addComponent(btn1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
                .addComponent(btn2)
                .addGap(187, 187, 187))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuMouseClicked
        mtel.setVisible(false);
        
        memail.setVisible(false);
        fermeture();
    }//GEN-LAST:event_menuMouseClicked

    private void btn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn1MouseClicked
         mtel.setVisible(true);
    }//GEN-LAST:event_btn1MouseClicked

    private void btn2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn2MouseClicked
        int x;
        int y;
        x=mtel.getLocation().x;
        y=mtel.getLocation().y+mtel.getHeight();
        memail.setLocation(x,y);
        memail.setVisible(true);
    }//GEN-LAST:event_btn2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Modifieretudiant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modifieretudiant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modifieretudiant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modifieretudiant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modifieretudiant().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton menu;
    // End of variables declaration//GEN-END:variables
}