/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Encadreur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author Wind10
 */
public class Suppressioncin extends javax.swing.JFrame {

    String url = "jdbc:mysql://localhost:3306/projetjava";
    String user = "root";
    String password = "12727455kassem";
    public Suppressioncin() {
        initComponents();
        initMoving(Suppressioncin.this);
        panel.setBackground(Color.decode("#E0EAFC"));
        setLocation(630,90);
        cin.setOpaque(false);
        //cin.setBackground(new Color(0,0,0,0));
        cin.removeAllItems();
        
        btn.setOpaque(false);
        btn.setBackground(new Color(0,0,0,0));
        remplir();
    }



    private void suppressionCinEncadreur(String cin) {
        String test = "SELECT nbetudiants, prenom, nom, telephone, email FROM encadreur WHERE cin = ?";
        String suppencadreur = "DELETE FROM encadreur WHERE cin = ?";
        String ajouterdansarchive = "INSERT INTO archiveencadreur (cin, prenom, nom, telephone, email) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statementCheckStudents = connection.prepareStatement(test);
            statementCheckStudents.setString(1, cin);
            ResultSet rs = statementCheckStudents.executeQuery();

            int nbStudents = 0;
            String prenom = null;
            String nom = null;
            int tel = 0;
            String email = null;

            if (rs.next()) {
                nbStudents = rs.getInt("nbetudiants");
                prenom = rs.getString("prenom");
                nom = rs.getString("nom");
                tel = rs.getInt("telephone");
                email = rs.getString("email");
            }

            if (nbStudents == 0) {
                PreparedStatement statementDeleteEncadreur = connection.prepareStatement(suppencadreur);
                statementDeleteEncadreur.setString(1, cin);
                int rowsAffected = statementDeleteEncadreur.executeUpdate();

                if (rowsAffected > 0) {
                    PreparedStatement dansarchive = connection.prepareStatement(ajouterdansarchive);
                    dansarchive.setString(1, cin);
                    dansarchive.setString(2, prenom);
                    dansarchive.setString(3, nom);
                    dansarchive.setInt(4, tel);
                    dansarchive.setString(5, email);
                    dansarchive.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Encadreur supprimé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Échec de la suppression de l'encadreur.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Impossible de supprimer l'encadreur car des étudiants lui sont associés.", "Avertissement", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la suppression de l'encadreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void remplir() {
        String sql = "SELECT cin FROM encadreur where nbetudiants=0";
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database.");
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            List<String> tels = new ArrayList<>(); // Liste pour stocker les valeurs de cin

            while (rs.next()) {
                String tel = rs.getString("cin");
                tels.add(tel); // Ajouter chaque valeur de cin à la liste
            }

            if (tels.isEmpty()) {

                return;
            }

            // Ajouter les valeurs de cin à votre JComboBox
            for (String tel : tels) {
                cin.addItem(tel);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Assurez-vous de fermer les ressources ResultSet, Statement et Connection
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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

        panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn = new javax.swing.JButton();
        exit = new javax.swing.JLabel();
        cin = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setPreferredSize(new java.awt.Dimension(800, 213));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Cin");

        btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn.setText("Suppimer");
        btn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMouseClicked(evt);
            }
        });

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/exit (1).png"))); // NOI18N

        cin.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(248, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(130, 130, 130)
                        .addComponent(cin, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101))
                    .addComponent(exit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(309, 309, 309)
                .addComponent(btn, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(btn)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMouseClicked(java.awt.event.MouseEvent evt) {                                 
       int option = showCustomDialog(null, "Voulez-vous supprimer cet encadreur soutenances ?");

       String cinch=(String)cin.getSelectedItem();
        if (!estNombre(cinch) || cinch.contains(" ") || cinch.length() != 8  ) {
            JOptionPane.showMessageDialog(null, "Le numéro cin doit être numérique et de 8 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (option == JOptionPane.YES_OPTION) {
            
            suppressionCinEncadreur(cinch);
        } else if (option == JOptionPane.NO_OPTION) {
            
            System.out.println("L'utilisateur a choisi Non");
        }
        cin.removeAllItems();
        remplir();

    }
    public static boolean estNombre(String chaine) {
        if (chaine == null || chaine.isEmpty()) {
            return false;
        }
        for (char c : chaine.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
     public  static int showCustomDialog(Component parent, String message) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel messageLabel = new JLabel(message);
        panel.add(messageLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(2, 1));
        JPasswordField passwordField = new JPasswordField();
        inputPanel.add(new JLabel("Mot de passe:"));
        inputPanel.add(passwordField);
        panel.add(inputPanel, BorderLayout.CENTER);

        int option = JOptionPane.showOptionDialog(
                parent,
                panel,
                "Sécurité",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Supprimer", "Non"},
                "Supprimer");
        if (option == JOptionPane.YES_OPTION) {
            char[] inputPassword = passwordField.getPassword();
            String enteredPassword = new String(inputPassword);
            if (!enteredPassword.equals("isimm")) {
                JOptionPane.showMessageDialog(parent, "Mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
                option = showCustomDialog(parent, message);
            }
        }
                
        return option;
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
            java.util.logging.Logger.getLogger(Suppressioncin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Suppressioncin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Suppressioncin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Suppressioncin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Suppressioncin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn;
    private javax.swing.JComboBox<String> cin;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
