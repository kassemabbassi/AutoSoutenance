/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Main;
import Etudiant.CustomDialog;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.*;

public class Main extends javax.swing.JFrame {
    String url = "jdbc:mysql://localhost:3306/projetjava";
    String user = "root";
    String password = "12727455kassem";


    public Main() {
        initComponents();
        setLocationRelativeTo(null);

        menu2.initMoving(Main.this); 
        initMoving(Main.this);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setBackground(new Color(0,0,0,0));
        menu2.getBtn1().addActionListener(e -> {
            openEtudiantForm();
        });
         menu2.getBtn2().addActionListener(e -> {
            openEncadreurForm();
        });
          menu2.getBtn3().addActionListener(e -> {
            openSoutenanceForm();
        });
           menu2.getBtn4().addActionListener(e -> {
            openinfo();
        });
          supp();
           alerte();
    }

    private void supp() {
        LocalDate dateActuelle = LocalDate.now(); // Obtenir la date actuelle

        String selectQuery = "SELECT * FROM soutenance WHERE datesoutenance < ? AND note IS NOT NULL";
        String deleteQuery = "DELETE FROM soutenance WHERE datesoutenance < ? AND note IS NOT NULL";

        String insertArchiveQuery = "INSERT INTO soutenancearchive (codesecret, cinetudiant, cinencadreur, datesoutenance, heure, local, cinpresident, note) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String chaine = null;

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database.");

            // Sélectionner les soutenances précédentes à la date actuelle avec note non nulle
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setDate(1, Date.valueOf(dateActuelle));
            ResultSet resultSet = selectStatement.executeQuery();


            // Préparer les déclarations pour supprimer et insérer dans les tables appropriées
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            PreparedStatement insertArchiveStatement = connection.prepareStatement(insertArchiveQuery);

            boolean soutenancesDeplacees = false; // Indicateur pour vérifier si des soutenances ont été déplacées

            while (resultSet.next()) {
                // Récupérer les données de la soutenance
                int codesecret = resultSet.getInt("codesecret");
                int cinetudiant = resultSet.getInt("cinetudiant");
                chaine = String.valueOf(cinetudiant); // Conversion en chaîne pour la suppression

                int cinencadreur = resultSet.getInt("cinencadreur");
                Date datesoutenance = resultSet.getDate("datesoutenance");
                Time heure = resultSet.getTime("heure");
                String local = resultSet.getString("local");
                int cinpresident = resultSet.getInt("cinpresident");
                String note = resultSet.getString("note");

                // Supprimer la soutenance de la table soutenance
                deleteStatement.setDate(1, Date.valueOf(dateActuelle));
                int rowsDeleted = deleteStatement.executeUpdate();

                if (rowsDeleted > 0) {
                    soutenancesDeplacees = true; // Indiquer qu'au moins une soutenance a été déplacée
                }
                suppcinet.suppressioncinetudiant(chaine);

                // Ajouter la soutenance dans la table soutenancearchive
                insertArchiveStatement.setInt(1, codesecret);
                insertArchiveStatement.setInt(2, cinetudiant);
                insertArchiveStatement.setInt(3, cinencadreur);
                insertArchiveStatement.setDate(4, datesoutenance);
                insertArchiveStatement.setTime(5, heure);
                insertArchiveStatement.setString(6, local);
                insertArchiveStatement.setInt(7, cinpresident);
                insertArchiveStatement.setString(8, note);
                insertArchiveStatement.executeUpdate();

                // Supprimer l'étudiant associé après avoir déplacé la soutenance
                
            }

            if (soutenancesDeplacees) {
                
                showMessageDialog("Soutenances précédentes à la date actuelle et avec note non nulle déplacées vers la table soutenancearchive.");

                System.out.println("Soutenances précédentes à la date actuelle et avec note non nulle déplacées vers la table soutenancearchive.");
            }

            // Fermeture des ressources
            resultSet.close();
            selectStatement.close();
            deleteStatement.close();
            insertArchiveStatement.close();
            connection.close();

        } catch (SQLException e) {
            System.err.println("Erreur lors du déplacement des soutenances : " + e.getMessage());
        }

    }


    /*private void supp() {
        LocalDate dateActuelle = LocalDate.now(); // Obtenir la date actuelle

        String selectQuery = "SELECT * FROM soutenance WHERE datesoutenance < ? AND note IS NOT NULL";
        String deleteQuery = "DELETE FROM soutenance WHERE datesoutenance < ? AND note IS NOT NULL";

        String insertArchiveQuery = "INSERT INTO soutenancearchive (codesecret, cinetudiant, cinencadreur, datesoutenance, heure, local, cinpresident, note) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database.");

            // Sélectionner les soutenances précédentes à la date actuelle avec note non nulle
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setDate(1, Date.valueOf(dateActuelle));
            ResultSet resultSet = selectStatement.executeQuery();

            // Déplacer les soutenances vers la table soutenancearchive
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            PreparedStatement insertArchiveStatement = connection.prepareStatement(insertArchiveQuery);

            boolean soutenancesDeplacees = false; // Indicateur pour vérifier si des soutenances ont été déplacées


            while (resultSet.next()) {
                // Récupérer les données de la soutenance
                int codesecret = resultSet.getInt("codesecret");

                int cinetudiant = resultSet.getInt("cinetudiant");
                String chainech = String.valueOf(cinetudiant);
                System.out.println(chainech);

                int cinencadreur = resultSet.getInt("cinencadreur");
                Date datesoutenance = resultSet.getDate("datesoutenance");
                Time heure = resultSet.getTime("heure");
                String local = resultSet.getString("local");
                int cinpresident = resultSet.getInt("cinpresident");
                String note = resultSet.getString("note");

                // Supprimer la soutenance de la table soutenance
                deleteStatement.setDate(1, Date.valueOf(dateActuelle));
                int rowsDeleted = deleteStatement.executeUpdate();


                if (rowsDeleted > 0) {
                    soutenancesDeplacees = true; // Indiquer qu'au moins une soutenance a été déplacée
                }


                // Ajouter la soutenance dans la table soutenancearchive
                insertArchiveStatement.setInt(1, codesecret);
                insertArchiveStatement.setInt(2, cinetudiant);
                insertArchiveStatement.setInt(3, cinencadreur);
                insertArchiveStatement.setDate(4, datesoutenance);
                insertArchiveStatement.setTime(5, heure);
                insertArchiveStatement.setString(6, local);
                insertArchiveStatement.setInt(7, cinpresident);
                insertArchiveStatement.setString(8, note);
                insertArchiveStatement.executeUpdate();
                suppcinet.suppressioncinetudiant(chainech);
            }

            if (soutenancesDeplacees) {
                showMessageDialog("Soutenances précédentes à la date actuelle et avec note non nulle déplacées vers la table soutenancearchive.");

                System.out.println("Soutenances précédentes à la date actuelle et avec note non nulle déplacées vers la table soutenancearchive.");
            }


        } catch (SQLException e) {
            System.err.println("Erreur lors du déplacement des soutenances : " + e.getMessage());
        }

    }*/



    private final Soutenance.SoutenanceForm sform = new Soutenance.SoutenanceForm();
    private final Encadreur.EncadreurForm enForm = new Encadreur.EncadreurForm();
    private final Etudiant.EtudiantForm etudiantform=new Etudiant.EtudiantForm();
    private final information info=new information();
    private void openSoutenanceForm() {

         sform.setLocationRelativeTo(this.getFrame());
        enForm.setVisible(false);
        etudiantform.setVisible(false);
       sform.setVisible(true);
    }
    private void openEncadreurForm()
    {
        enForm.setLocationRelativeTo(this.getFrame());
        sform.setVisible(false);
        etudiantform.setVisible(false);
       enForm.setVisible(true);
    }
     private void openEtudiantForm() {
        
        etudiantform.setLocationRelativeTo(this.getFrame());
        sform.setVisible(false);
        enForm.setVisible(false);
        etudiantform.setVisible(true);
          
    }
      private void openinfo()
   {
       info.setLocationRelativeTo(this.getFrame());
       info.setVisible(true);
   }

   public JFrame getFrame() {
    return this;
}
    private void alerte() {
        String sql = "SELECT * FROM soutenance WHERE datesoutenance <= ? AND note IS NULL";
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database.");
            pstmt = connection.prepareStatement(sql);
            LocalDate currentDate = LocalDate.now();
            pstmt.setDate(1, Date.valueOf(currentDate));
            rs = pstmt.executeQuery();
            String message = "Cher administrateur,\nNous tenons à vous informer qu'il y a des soutenances prévues pour aujourd'hui qui nécessitent votre validation, ainsi que des soutenances passées qui n'ont pas encore été notées. Veuillez prendre le temps d'examiner ces soutenances et de simplement saisir la note attribuée à chaque soutenance.\nMerci pour votre attention et votre diligence.\nCordialement,";

            // Vérifier s'il y a des soutenances programmées pour la date actuelle ou des soutenances passées non notées
            if (rs.next()) {
                // Afficher le message d'information
                showMessageDialog(message);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Fermer les ressources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void showMessage(String message) {
        JFrame parentFrame = new JFrame();

        JLabel messageLabel = new JLabel(message);
        JButton closeButton = new JButton("Fermer");

        JPanel panel = new JPanel();
        panel.add(messageLabel);
        panel.add(closeButton);

        closeButton.addActionListener(e -> parentFrame.dispose());

        JOptionPane.showOptionDialog(
                parentFrame,
                panel,
                "Message",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{},
                null);
    }
    
    private void initMoving(JFrame frame)
    {
        exit.addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent me){
           System.exit(0);
        }
                
                });}
    public void showMessageDialog(String message) {
        // Création du panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(250, 250, 250)); // Couleur de fond personnalisée
        panel.setPreferredSize(new Dimension(470, 270)); // Augmentation de la taille du panneau

        // Création du label avec texte formaté en HTML
        JLabel label = new JLabel("<html><body style='width: 400px;'>" + message + "</body></html>");
        Font font = new Font("Calibri", Font.BOLD, 18); // Police élégante
        label.setFont(font);
        label.setForeground(new Color(96, 70, 20)); // Couleur de texte personnalisée
        label.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Marge intérieure

        // Ajout du label au panel
        panel.add(label, BorderLayout.CENTER);

        // Personnalisation de la boîte de dialogue
        UIManager.put("OptionPane.background", new Color(240, 240, 240)); // Couleur de fond
        UIManager.put("Panel.background", new Color(240, 240, 240)); // Couleur de fond du panel interne
        UIManager.put("OptionPane.messageFont", font); // Police du message
        UIManager.put("OptionPane.messageForeground", new Color(50, 50, 50)); // Couleur du texte du message
        UIManager.put("OptionPane.useTaskPane", Boolean.FALSE); // Désactive l'effet d'ombrage

        // Affichage de la boîte de dialogue
        JOptionPane.showMessageDialog(
                null,
                panel,
                "Validation",
                JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon("icon.png")); // Ajout d'une icône
    }
    public void showGorgeousMessage(String message) {
        // Création du panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 228, 225)); // Couleur de fond rose pâle
        panel.setPreferredSize(new Dimension(550, 300)); // Taille du panneau ajustée

        // Création du label avec texte formaté en HTML
        JLabel label = new JLabel("<html><body style='width: 500px; text-align:center;'>" + message + "</body></html>");
        Font font = new Font("Arial", Font.BOLD, 20); // Police élégante
        label.setFont(font);
        label.setForeground(new Color(219, 112, 147)); // Couleur de texte rose foncé

        // Ajout du label au panel
        panel.add(label, BorderLayout.CENTER);

        // Personnalisation de la boîte de dialogue
        UIManager.put("OptionPane.background", new Color(255, 228, 225)); // Couleur de fond rose pâle
        UIManager.put("Panel.background", new Color(255, 228, 225)); // Couleur de fond du panel interne
        UIManager.put("OptionPane.messageFont", font); // Police du message
        UIManager.put("OptionPane.messageForeground", new Color(219, 112, 147)); // Couleur du texte du message

        // Affichage de la boîte de dialogue
        JOptionPane.showMessageDialog(
                null,
                panel,
                "Message Magnifique",
                JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon("gorgeous_icon.png")); // Icône magnifique
    }

    private  final Etudiant.Suppressioncinetudiant suppcinet=new Etudiant.Suppressioncinetudiant();

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu2 = new Menupfe.Menu();
        panel = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();
        btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1280, 639));

        panel.setBackground(new java.awt.Color(255, 255, 255));

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/exit (1).png"))); // NOI18N

      btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/logo.png"))); // NOI18N

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(btn, javax.swing.GroupLayout.PREFERRED_SIZE, 938, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 172, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(menu2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn;
    private javax.swing.JLabel exit;
    private Menupfe.Menu menu2;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
