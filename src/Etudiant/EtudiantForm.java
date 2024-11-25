
package Etudiant;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class EtudiantForm extends javax.swing.JFrame {

    /**
     * Creates new form EtudiantForm
     */
    public EtudiantForm() {
        initComponents();
       // menuborderetudiant2.initMoving(EtudiantForm.this);
        initMoving(EtudiantForm.this);
        logo.setOpaque(false);
        logo.setContentAreaFilled(false);
        logo.setBorderPainted(false);
        logo.setBorder(BorderFactory.createEmptyBorder());
        logo.setBackground(new Color(0,0,0,0));
      
        menuborderetudiant2.getBtn1().addActionListener(e -> {
            openajoutetudiant();
        });
         menuborderetudiant2.getBtn2().addActionListener(e -> {
            opensuppressionetudiant();
        });
         menuborderetudiant2.getBtn3().addActionListener(e -> {
            openrechercheetudiant();
        });
          menuborderetudiant2.getBtn4().addActionListener(e -> {
            modifieretudiant();
        });
           menuborderetudiant2.getBtn5().addActionListener(e -> {
            archiveaff();
        });
    }
    private final ajoutetudiant ajet=new ajoutetudiant();
    private final suppressionetudiant supet=new suppressionetudiant();
    private final menurechercheetudiant rechet=new menurechercheetudiant();
    private final Modifieretudiant modifet=new Modifieretudiant();
    private final Archive ar=new Archive();
    private final Ajoutegroupe ajg=new Ajoutegroupe();
    
    
    private void initMoving(JFrame frame)
    {
        exit.addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent me){
           dispose();
        }
                
                });}


    private void openajoutetudiant() {
        supet.setVisible(false);
        rechet.setVisible(false);
        ar.setVisible(false);


        // Création de la boîte de dialogue personnalisée
        CustomDialog dialog = new CustomDialog(this, "Boîte de dialogue personnalisée");

        // Affichage de la boîte de dialogue et récupération de la réponse de l'utilisateur
        int option = dialog.showDialog();

        // Vérification de la réponse de l'utilisateur
        if (option == CustomDialog.YES_OPTION) {
            ajg.setVisible(false);
            ajet.setVisible(true);
        } else if (option == CustomDialog.NO_OPTION) {
            ajet.setVisible(false);
            ajg.setVisible(true);
        }
    }


    private void opensuppressionetudiant()
    {
        ajet.setVisible(false);
        rechet.setVisible(false);
        ar.setVisible(false);
        if(supet.getWidth()<=250 && supet.getHeight()<=639)
        {
            supet.setSize(260,639);
            supet.setVisible(true);
            return;
        }
        supet.setVisible(true);
        
    }
    private void modifieretudiant()
    {
        ajet.setVisible(false);
        rechet.setVisible(false);
        supet.setVisible(false);
        if(modifet.getWidth()<=260 && modifet.getHeight()<=639)
        {
            modifet.setSize(260,639);
            modifet.setVisible(true);
            return;
        }
        modifet.setVisible(true);
    }
    private void openrechercheetudiant()
    {
        ajet.setVisible(false);
        supet.setVisible(false);
        ar.setVisible(false);
        if(rechet.getWidth()<=250 && rechet.getHeight()<=639)
        {
            rechet.setSize(250,639);
            rechet.setVisible(true);
            return;
        }
        rechet.setVisible(true);
        
    }
    public JFrame getFrame() {
    return this;
}
    public void archiveaff()
    {
         ajet.setVisible(false);
         supet.setVisible(false);
         rechet.setVisible(false);
         modifet.setVisible(false);
        ar.setVisible(true);

        Customaffichage dialog = new Customaffichage(this, "Boîte de dialogue personnalisée",true);

        // Affichage de la boîte de dialogue et récupération de la réponse de l'utilisateur
        int option = dialog.showDialog();

        // Vérification de la réponse de l'utilisateur
        if (option == CustomDialog.YES_OPTION) {
            afficherTousLesEtudiantsarchive();
        } else if (option == CustomDialog.NO_OPTION) {

        }
    }
    String url = "jdbc:mysql://localhost:3306/projetjava";
    String user = "root";
    String password = "12727455kassem";
    private void afficherTousLesEtudiantsarchive() {
        String queryEncadreur = "SELECT * FROM archiveetudiant";

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


    @SuppressWarnings("unchecked")

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();
        logo = new javax.swing.JButton();
        menuborderetudiant2 = new Etudiant.MenuBorderEtudiant();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1280, 639));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/exit (1).png"))); // NOI18N

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Etudiant/logoetudiant.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 936, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 109, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuborderetudiant2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menuborderetudiant2, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }


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
            java.util.logging.Logger.getLogger(EtudiantForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EtudiantForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EtudiantForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EtudiantForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new EtudiantForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel exit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton logo;
    private Etudiant.MenuBorderEtudiant menuborderetudiant2;
    // End of variables declaration//GEN-END:variables
}


