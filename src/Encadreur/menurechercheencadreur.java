/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Encadreur;

import Etudiant.CustomDialog;
import Etudiant.Customaffichage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wind10
 */
public class menurechercheencadreur extends javax.swing.JFrame {


    public menurechercheencadreur() {
        initComponents();
        jPanel1.setBackground(Color.decode("#E0EAFC"));
        setLocation(370,90);
        btn1.setOpaque(false);
        btn1.setBorder(BorderFactory.createEmptyBorder());
        btn1.setBackground(new Color(0,0,0,0));
        
        btn2.setOpaque(false);
        btn2.setBorder(BorderFactory.createEmptyBorder());
        btn2.setBackground(new Color(0,0,0,0));
    }
    private final Recherchecin rechcin=new Recherchecin();
    private final Affichage aff=new  Affichage();


    String url = "jdbc:mysql://localhost:3306/projetjava";
    String user = "root";
    String password = "12727455kassem";
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(238, 639));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Encadreur/menu (1).png"))); // NOI18N
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });

        btn1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn1.setText("Recherche par Cin");
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btn2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn2.setText("Affichage");
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(exit))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn1)
                    .addComponent(btn2))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109)
                .addComponent(btn1)
                .addGap(174, 174, 174)
                .addComponent(btn2)
                .addContainerGap(141, Short.MAX_VALUE))
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

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        fermeture();
    }//GEN-LAST:event_exitMouseClicked

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        aff.setVisible(false);
        rechcin.setVisible(true);
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {                                     
        rechcin.setVisible(false);
        aff.setVisible(true);

        Customaffichage dialog = new Customaffichage(this, "Boîte de dialogue personnalisée",false);

        // Affichage de la boîte de dialogue et récupération de la réponse de l'utilisateur
        int option = dialog.showDialog();

        // Vérification de la réponse de l'utilisateur
        if (option == CustomDialog.YES_OPTION) {
            afficherTousLesEncadreurs();
        } else if (option == CustomDialog.NO_OPTION) {

        }
    }
    private void afficherTousLesEncadreurs() {
        String queryEncadreur = "SELECT * FROM encadreur";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statementEncadreur = connection.prepareStatement(queryEncadreur);

            ResultSet resultSetEncadreur = statementEncadreur.executeQuery();

            // Créer un modèle de table pour afficher les résultats des encadreurs
            ResultSetMetaData metaDataEncadreur = resultSetEncadreur.getMetaData();
            int columnsCountEncadreur = metaDataEncadreur.getColumnCount();
            String[] columnsEncadreur = new String[columnsCountEncadreur];
            for (int i = 1; i <= columnsCountEncadreur; i++) {
                columnsEncadreur[i - 1] = metaDataEncadreur.getColumnName(i);
            }
            DefaultTableModel modelEncadreur = new DefaultTableModel(columnsEncadreur, 0);

            // Remplir le modèle de table avec les résultats de la requête sur les encadreurs
            while (resultSetEncadreur.next()) {
                Object[] rowData = new Object[columnsCountEncadreur];
                for (int i = 1; i <= columnsCountEncadreur; i++) {
                    rowData[i - 1] = resultSetEncadreur.getObject(i);
                }
                modelEncadreur.addRow(rowData);
            }

            // Set the model of the table to display the encadreurs
            aff.getTable().setModel(modelEncadreur);

            // Augmenter la taille des lignes dynamiquement en fonction du contenu
            for (int row = 0; row < aff.getTable().getRowCount(); row++) {
                int rowHeight = aff.getTable().getRowHeight();
                for (int column = 0; column < aff.getTable().getColumnCount(); column++) {
                    Component comp = aff.getTable().prepareRenderer(aff.getTable().getCellRenderer(row, column), row, column);
                    rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
                }
                aff.getTable().setRowHeight(row, rowHeight);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



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
                dispose(); // Fermer la fenêtre
            }
        }
    });

    // Démarrer le timer
    shrinkTimer.start();
}
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
            java.util.logging.Logger.getLogger(menurechercheencadreur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menurechercheencadreur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menurechercheencadreur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menurechercheencadreur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menurechercheencadreur().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JLabel exit;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
