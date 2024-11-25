/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Encadreur;

import Etudiant.CustomDialog;
import Etudiant.Customaffichage;
import Etudiant.EtudiantForm;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wind10
 */
public class EncadreurForm extends javax.swing.JFrame {

    /**
     * Creates new form EncadreurForm
     */
    public EncadreurForm() {
        initComponents();

        initMoving(EncadreurForm.this);
        logo.setOpaque(false);
        logo.setContentAreaFilled(false);
        logo.setBorderPainted(false);
        logo.setBorder(BorderFactory.createEmptyBorder());
        logo.setBackground(new Color(0,0,0,0));

        menuencadreur1.getBtn1().addActionListener(e -> {
            openajoutencadreur();
        });
         menuencadreur1.getBtn2().addActionListener(e -> {
            opensuppressionencadreur();
        });
         menuencadreur1.getBtn3().addActionListener(e -> {
            openrechercherencadreur();
        });
          menuencadreur1.getBtn5().addActionListener(e -> {
            archiveaff();
        });

    }

    private final Ajoutencadreur ajen=new Ajoutencadreur();
    private final menusuppression msup=new menusuppression();
    private final menurechercheencadreur mren=new menurechercheencadreur();
    private final Archive ar=new Archive();
    public void openajoutencadreur()
    {
        mren.setVisible(false);
        msup.setVisible(false);
        ar.setVisible(false);
         ajen.setVisible(true); // Afficher la fenêtre


    }
    public void opensuppressionencadreur()
    {
        ajen.setVisible(false);
        mren.setVisible(false);
        ar.setVisible(false);
        if(msup.getWidth()<=260 && msup.getHeight()<=639)
        {
            msup.setSize(260,639);
            msup.setVisible(true);
            return;
        }
        msup.setVisible(true);
    }
    public void openrechercherencadreur()
    {
        msup.setVisible(false);
        ajen.setVisible(false);
        ar.setVisible(false);
        if(mren.getWidth()<=250 && mren.getHeight()<=639)
        {
            mren.setSize(250,639);
            mren.setVisible(true);
            return;
        }
        mren.setVisible(true);
    }
    public void archiveaff()
    {
         ajen.setVisible(false);
         msup.setVisible(false);
         mren.setVisible(false);
        ar.setVisible(true);
        /*int option = JOptionPane.showOptionDialog(
                null,
                "Voulez vous afficher tous les encadreurs",
                "Boîte de dialogue personnalisée",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Oui", "Non"},
                "Oui");

        // Vérification de la réponse de l'utilisateur
        if (option == JOptionPane.YES_OPTION) {



        } else if (option == JOptionPane.NO_OPTION) {
            //JOptionPane.showMessageDialog(null, "Vous avez choisi Option 2");

        }*/
        Customaffichage dialog = new Customaffichage(this, "Boîte de dialogue personnalisée",false);

        // Affichage de la boîte de dialogue et récupération de la réponse de l'utilisateur
        int option = dialog.showDialog();
        if (option == CustomDialog.YES_OPTION) {
            afficherTousLesEncadreursarchive();

        } else if (option == CustomDialog.NO_OPTION) {

        }
    }
    String url = "jdbc:mysql://localhost:3306/projetjava";
    String user = "root";
    String password = "12727455kassem";
    private void afficherTousLesEncadreursarchive() {
        String queryEncadreur = "SELECT * FROM archiveencadreur";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statementEncadreur = connection.prepareStatement(queryEncadreur);

            ResultSet resultSetEncadreur = statementEncadreur.executeQuery();


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


            ar.getTable().setModel(modelEncadreur);

            // Augmenter la taille des lignes dynamiquement en fonction du contenu
            for (int row = 0; row < ar.getTable().getRowCount(); row++) {
                int rowHeight = ar.getTable().getRowHeight();
                for (int column = 0; column < ar.getTable().getColumnCount(); column++) {
                    Component comp = ar.getTable().prepareRenderer(ar.getTable().getCellRenderer(row, column), row, column);
                    rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
                }
                ar.getTable().setRowHeight(row, rowHeight);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void initMoving(JFrame frame)
    {
        exit.addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent me){
           dispose();
        }

                });}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuencadreur1 = new Encadreur.Menuencadreur();
        jPanel1 = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();
        logo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1280, 639));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/exit (1).png"))); // NOI18N

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Encadreur/logoencadreur.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 938, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuencadreur1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(menuencadreur1, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


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
            java.util.logging.Logger.getLogger(EncadreurForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EncadreurForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EncadreurForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EncadreurForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EncadreurForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel exit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton logo;
    private Encadreur.Menuencadreur menuencadreur1;
    // End of variables declaration//GEN-END:variables
}
