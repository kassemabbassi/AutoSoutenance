/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Encadreur;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Wind10
 */
public class Ajoutencadreur extends javax.swing.JFrame {
    String url = "jdbc:mysql://localhost:3306/projetjava";
    String user = "root";
    String password = "12727455kassem";


    public Ajoutencadreur() {
        initComponents();
        initMoving(Ajoutencadreur.this);
        panel.setBackground(Color.decode("#E0EAFC"));
        setLocation(370,90);
        btn.setOpaque(false);
        btn.setBackground(new Color(0,0,0,0));
        
        clear.setOpaque(false);
        clear.setBackground(new Color(0,0,0,0));
        prename.setOpaque(false);
        prename.setBackground(new Color(0,0,0,0));
        lastname.setOpaque(false);
        lastname.setBackground(new Color(0,0,0,0));
        cin.setOpaque(false);
        cin.setBackground(new Color(0,0,0,0));
        email.setOpaque(false);
        email.setBackground(new Color(0,0,0,0));
        tel.setOpaque(false);
        tel.setBackground(new Color(0,0,0,0));
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

    private void initComponents() {

        panel = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();
        lab1 = new javax.swing.JLabel();
        lab2 = new javax.swing.JLabel();
        lab3 = new javax.swing.JLabel();
        lab4 = new javax.swing.JLabel();
        lab6 = new javax.swing.JLabel();
        prename = new javax.swing.JTextField();
        lastname = new javax.swing.JTextField();
        cin = new javax.swing.JTextField();
        tel = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        btn = new javax.swing.JButton();
        clear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setPreferredSize(new java.awt.Dimension(1062, 639));

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/exit (1).png"))); // NOI18N

        lab1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lab1.setText("Prename");

        lab2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lab2.setText("Lastname");

        lab3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lab3.setText("Cin");

        lab4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lab4.setText("Téléphone");

        lab6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lab6.setText("Email");

        prename.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        prename.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        lastname.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lastname.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        cin.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        tel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        email.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        email.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn.setText("Ajouter");
        btn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMouseClicked(evt);
            }
        });

        clear.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        clear.setText("Clear");
        clear.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(0, 0, 0), new java.awt.Color(51, 51, 51), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lab3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lab4)
                                    .addComponent(lab1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lab2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lab6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(183, 183, 183)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lastname)
                                .addComponent(cin)
                                .addComponent(tel, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                                .addComponent(prename))
                            .addComponent(email, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(261, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(exit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addComponent(btn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42))))))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exit)
                .addGap(39, 39, 39)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lab2))
                        .addGap(41, 41, 41)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cin, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lab3))
                        .addGap(39, 39, 39)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lab4))
                        .addGap(49, 49, 49)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lab6)))
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(prename, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lab1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn)
                    .addComponent(clear))
                .addGap(147, 147, 147))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1086, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnMouseClicked(java.awt.event.MouseEvent evt) {
        String prenom = prename.getText();
        if (!prenom.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "Le prénom doit être alphabétique.", "Erreur", JOptionPane.ERROR_MESSAGE);

            return;
        }

        String nom = lastname.getText();
        if (!nom.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "Le nom doit être alphabétique.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String numcinch = cin.getText();
        if (!estNombre(numcinch) || numcinch.contains(" ") || numcinch.length() != 8 ) {
            JOptionPane.showMessageDialog(null, "Le numéro cin doit être numérique et de 8 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if ( !estCINUniquearchiveet(numcinch) || !estCINUniquearchiveen(numcinch) || !estCINUniqueetudiant(numcinch) ||
        !estCINUniqueDansJurys(numcinch)) {
            JOptionPane.showMessageDialog(null, "Ce numéro  cin existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int ncin = Integer.parseInt(numcinch);
        String telch = tel.getText();
        if (!estNombre(telch) || telch.contains(" ") || telch.length()!=8 ||(telch.charAt(0)!='9' && telch.charAt(0)!='5' && telch.charAt(0)!='2')) {
            JOptionPane.showMessageDialog(null, "Le numéro téléphone doit être numérique.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!esttelUniqueencadreur(telch) || !esttelUniquearchiveen(telch) || !esttelUniqueetudiant(telch) || !esttelUniquearchivete(telch)) {
            JOptionPane.showMessageDialog(null, "Ce numéro de téléphone existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int telep = Integer.parseInt(telch);

        String em = email.getText();
        if (!validerEmail(em)) {
            JOptionPane.showMessageDialog(null, "L'email n'est pas valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if ( !estemailUniqueetudiant(em) || !estemailUniquearchiveet(em) || !estemailUniqueencadreur(em) || !estemailUniquearchiveen(em)) {
            JOptionPane.showMessageDialog(null, "Cet email existe déja.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "INSERT INTO encadreur (cin, prenom, nom, telephone, email, nbetudiants) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database.");

            // Create a PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setInt(1, ncin); // Example cin
            preparedStatement.setString(2, prenom); // Example prénom
            preparedStatement.setString(3, nom); // Example nom
            preparedStatement.setInt(4, telep); // Example téléphone
            preparedStatement.setString(5, em); // Example email
            preparedStatement.setInt(6, 0); // Example nbetudiants

            // Execute the PreparedStatement to insert data
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if insertion was successful
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Encadreur ajouté avec succès.", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Echec.", "Information", JOptionPane.ERROR_MESSAGE);
            }
            // Close the PreparedStatement and the Connection
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            if (e.getSQLState().startsWith("23")) { // Les codes d'erreur commencent généralement par "23" pour les violations de contraintes
                JOptionPane.showMessageDialog(null, "Erreur : Cette clé existe déjà dans la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                // Si ce n'est pas une violation de contrainte d'unicité, afficher le message d'erreur générique
                JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private boolean estCINUniquearchiveen(String cinEtudiant) {
        String sql = "SELECT cin FROM archiveencadreur";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            while (rs.next()) {
                String cinEncadreur = rs.getString("cin");

                if (cinEtudiant.equals(cinEncadreur)) {
                    return false;
                }
            }
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean estCINUniqueencadreur(String cinEtudiant) {
        String sql = "SELECT cin FROM encadreur";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            while (rs.next()) {
                String cinEncadreur = rs.getString("cin");

                if (cinEtudiant.equals(cinEncadreur)) {
                    return false;
                }
            }
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean estCINUniquearchiveet(String cinEtudiant) {
        String sql = "SELECT cin FROM archiveetudiant";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            while (rs.next()) {
                String cinEncadreur = rs.getString("cin");

                if (cinEtudiant.equals(cinEncadreur)) {
                    return false;
                }
            }
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean estCINUniqueetudiant(String cinEtudiant) {
        String sql = "SELECT cin FROM etudiant";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            while (rs.next()) {
                String cinEncadreur = rs.getString("cin");

                if (cinEtudiant.equals(cinEncadreur)) {
                    return false;
                }
            }
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean estCINUniqueDansJurys(String cinEtudiant) {
        String sql = "SELECT cinpresident, cinrapporteur, cinexaminateur, cinencadreur FROM jurys";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String cinPresident = rs.getString("cinpresident");
                String cinRapporteur = rs.getString("cinrapporteur");
                String cinExaminateur = rs.getString("cinexaminateur");
                String cinEncadreur = rs.getString("cinencadreur");

                if (cinEtudiant.equals(cinPresident) || cinEtudiant.equals(cinRapporteur) || cinEtudiant.equals(cinExaminateur) || cinEtudiant.equals(cinEncadreur)) {
                    return false;
                }
            }
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    private boolean estemailUniquearchiveet(String email) {
        String sql = "SELECT email FROM archiveetudiant";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            while (rs.next()) {
                String cinEncadreur = rs.getString("email");

                if (email.equals(cinEncadreur)) {
                    return false;
                }
            }
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean estemailUniquearchiveen(String email) {
        String sql = "SELECT email FROM archiveencadreur";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            while (rs.next()) {
                String cinEncadreur = rs.getString("email");

                if (email.equals(cinEncadreur)) {
                    return false;
                }
            }
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean estemailUniqueetudiant(String email) {
        String sql = "SELECT email FROM etudiant";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            while (rs.next()) {
                String cinEncadreur = rs.getString("email");

                if (email.equals(cinEncadreur)) {
                    return false;
                }
            }
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean estemailUniqueencadreur(String email) {
        String sql = "SELECT email FROM encadreur";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            while (rs.next()) {
                String cinEncadreur = rs.getString("email");

                if (email.equals(cinEncadreur)) {
                    return false;
                }
            }
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean validerEmail(String email) {
        // Expression régulière pour valider un email
        String regex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";

        return email.matches(regex);
    }



    private boolean esttelUniqueencadreur(String tel) {
        String sql = "SELECT telephone FROM encadreur";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            while (rs.next()) {
                String telephone = rs.getString("telephone");

                if (tel.equals(telephone)) {
                    return false;
                }
            }
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean esttelUniqueetudiant(String tel) {
        String sql = "SELECT telephone FROM etudiant";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            while (rs.next()) {
                String telephone = rs.getString("telephone");

                if (tel.equals(telephone)) {
                    return false;
                }
            }
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean esttelUniquearchivete(String tel) {
        String sql = "SELECT telephone FROM archiveetudiant";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            while (rs.next()) {
                String telephone = rs.getString("telephone");

                if (tel.equals(telephone)) {
                    return false;
                }
            }
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean esttelUniquearchiveen(String tel) {
        String sql = "SELECT telephone FROM archiveencadreur";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            while (rs.next()) {
                String telephone = rs.getString("telephone");

                if (tel.equals(telephone)) {
                    return false;
                }
            }
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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




    private void clearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearMouseClicked

    }//GEN-LAST:event_clearMouseClicked


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
            java.util.logging.Logger.getLogger(Ajoutencadreur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ajoutencadreur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ajoutencadreur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ajoutencadreur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ajoutencadreur().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn;
    private javax.swing.JTextField cin;
    private javax.swing.JButton clear;
    private javax.swing.JTextField email;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel lab1;
    private javax.swing.JLabel lab2;
    private javax.swing.JLabel lab3;
    private javax.swing.JLabel lab4;
    private javax.swing.JLabel lab6;
    private javax.swing.JTextField lastname;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField prename;
    private javax.swing.JTextField tel;
    // End of variables declaration//GEN-END:variables
}
