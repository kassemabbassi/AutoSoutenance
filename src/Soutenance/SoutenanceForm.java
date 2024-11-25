/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Soutenance;
import Etudiant.CustomDialog;
import Etudiant.Customaffichage;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Wind10
 */
public class SoutenanceForm extends javax.swing.JFrame {
    String url = "jdbc:mysql://localhost:3306/projetjava";
    String user = "root";
    String password = "12727455kassem";
    /**
     * Creates new form SoutenanceForm
     */
    public SoutenanceForm() {
        initComponents();
        initMoving(SoutenanceForm.this);
        logo.setOpaque(false);
        logo.setContentAreaFilled(false);
        logo.setBorderPainted(false);
        logo.setBorder(BorderFactory.createEmptyBorder());
        logo.setBackground(new Color(0,0,0,0));
        
         menusoutenance1.getBtn1().addActionListener(e -> {
           openajouteSoutenance();
        });
         menusoutenance1.getBtn2().addActionListener(e -> {
            openvalider();
        });
         menusoutenance1.getBtn3().addActionListener(e -> {
            openrecherchesoutenance();
        });
         menusoutenance1.getBtn4().addActionListener(e -> {
            archiveaff();
        });
          menusoutenance1.getBtn5().addActionListener(e -> {
           openjurys();
        });
        
    }
    private final Ajoutesoutenance ajoutst=new Ajoutesoutenance();
    
    private final menurecherchesoutenance rechst=new menurecherchesoutenance();
    private final menujurys jurys=new menujurys();
    private final Ajoutesoutenancegroupe ajsg=new Ajoutesoutenancegroupe();
    private final Validation valsoutenance=new Validation();





    private final Archive ar=new Archive();
    private void openajouteSoutenance() {

        
        rechst.setVisible(false);
        ar.setVisible(false);

        CustomDialog c=new CustomDialog(this, "Boîte de dialogue personnalisée",true);

        int option = c.showDialog();

        // Vérification de la réponse de l'utilisateur
        if (option == CustomDialog.YES_OPTION) {
            ajsg.setVisible(false);
            ajoutst.setVisible(true);

        } else if (option == CustomDialog.NO_OPTION) {
            ajoutst.setVisible(false);
            ajsg.setVisible(true);
        }
    }
    private void openvalider()
    {
        ajoutst.setVisible(false);
        rechst.setVisible(false);
        jurys.setVisible(false);
        ajsg.setVisible(false);
        ar.setVisible(false);
        afficherToutesSoutenancesDuJour();
        valsoutenance.setVisible(true);
    }



    private void openrecherchesoutenance()
    {
        ajoutst.setVisible(false);
        
        ar.setVisible(false);
        jurys.setVisible(false);
        if(rechst.getWidth()<=260 && rechst.getHeight()<=639)
        {
            rechst.setSize(260,639);
            rechst.setVisible(true);
            return;
        }
        rechst.setVisible(true);
    }
    private void openjurys()
    {
        ajoutst.setVisible(false);
        
        rechst.setVisible(false);
        ar.setVisible(false);
        if(rechst.getWidth()<=260 && rechst.getHeight()<=639)
        {
            jurys.setSize(260,639);
            jurys.setVisible(true);
            return;
        }
        jurys.setVisible(true);
    }
    private void initMoving(JFrame frame)
    {
        exit.addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent me){
           setVisible(false);
        }
                
                });}
     public void archiveaff()
    {
         ajoutst.setVisible(false);
         
         rechst.setVisible(false);
        ar.setVisible(true);

        Customaffichage dialog = new Customaffichage(this, "Boîte de dialogue personnalisée");

        // Affichage de la boîte de dialogue et récupération de la réponse de l'utilisateur
        int option = dialog.showDialog();

        // Vérification de la réponse de l'utilisateur
        if (option == CustomDialog.YES_OPTION) {
            afficherTousLessoutenancesarchive();
        } else if (option == CustomDialog.NO_OPTION) {

        }
    }
    private void afficherTousLessoutenancesarchive() {
        String queryEncadreur = "SELECT * FROM soutenancearchive";

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


    private void afficherToutesSoutenancesDuJour() {
        // Récupérer la date actuelle
        LocalDate dateActuelle = LocalDate.now();

        // Construire la requête SQL pour récupérer les soutenances dont la date est inférieure ou égale à la date actuelle
        String querySoutenances = "SELECT * FROM soutenance WHERE datesoutenance <= ?  AND note IS NULL";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statementSoutenances = connection.prepareStatement(querySoutenances);

            // Paramétrer la date actuelle dans la requête
            statementSoutenances.setDate(1, java.sql.Date.valueOf(dateActuelle));

            ResultSet resultSetSoutenances = statementSoutenances.executeQuery();

            ResultSetMetaData metaDataSoutenances = resultSetSoutenances.getMetaData();
            int columnsCountSoutenances = metaDataSoutenances.getColumnCount();
            String[] columnsSoutenances = new String[columnsCountSoutenances];
            for (int i = 1; i <= columnsCountSoutenances; i++) {
                columnsSoutenances[i - 1] = metaDataSoutenances.getColumnName(i);
            }
            DefaultTableModel modelSoutenances = new DefaultTableModel(columnsSoutenances, 0);

            // Remplir le modèle de table avec les résultats de la requête sur les soutenances
            while (resultSetSoutenances.next()) {
                Object[] rowData = new Object[columnsCountSoutenances];
                for (int i = 1; i <= columnsCountSoutenances; i++) {
                    rowData[i - 1] = resultSetSoutenances.getObject(i);
                }
                modelSoutenances.addRow(rowData);
            }

            // Définir le modèle de table dans votre composant graphique (ar.getTable())
            valsoutenance.getTable().setModel(modelSoutenances);

            // Ajuster la taille des lignes dynamiquement en fonction du contenu
            for (int row = 0; row < valsoutenance.getTable().getRowCount(); row++) {
                int rowHeight = valsoutenance.getTable().getRowHeight();
                for (int column = 0; column < valsoutenance.getTable().getColumnCount(); column++) {
                    Component comp = valsoutenance.getTable().prepareRenderer(valsoutenance.getTable().getCellRenderer(row, column), row, column);
                    rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
                }
                valsoutenance.getTable().setRowHeight(row, rowHeight);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }







    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menusoutenance1 = new menusoutenance();
        panel = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();
        logo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1280, 639));

        panel.setBackground(new java.awt.Color(255, 255, 255));

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/exit (1).png"))); // NOI18N

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Soutenance/logo.png"))); // NOI18N
        logo.setText("jButton1");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 938, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menusoutenance1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menusoutenance1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(SoutenanceForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SoutenanceForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SoutenanceForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SoutenanceForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SoutenanceForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel exit;
    private javax.swing.JButton logo;
    private menusoutenance menusoutenance1;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
