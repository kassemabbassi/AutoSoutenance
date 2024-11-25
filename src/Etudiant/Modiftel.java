/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Etudiant;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author Wind10
 */
public class Modiftel extends javax.swing.JFrame {

    String url = "jdbc:mysql://localhost:3306/projetjava";
    String user = "root";
    String password = "12727455kassem";
    public Modiftel() {
        initComponents();
       // initMoving(Modiftel.this);
        panel.setBackground(Color.decode("#E0EAFC"));
        setLocation(610,89);
        atel.setOpaque(false);
       // atel.setBackground(new Color(0,0,0,0));
        ntel.setOpaque(false);
        ntel.setBackground(new Color(0,0,0,0));
        btn.setOpaque(false);
        btn.setBackground(new Color(0,0,0,0));
        
        atel.removeAllItems();

        remplir();
    }
    private void remplir() {
        String sql = "SELECT telephone FROM etudiant";
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database.");
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            List<String> tels = new ArrayList<>();

            while (rs.next()) {
                String tel = rs.getString("telephone");
                tels.add(tel); // Ajouter chaque valeur de cin à la liste
            }

            if (tels.isEmpty()) {
                //JOptionPane.showMessageDialog(null, "Il n'existe pas d'étudiants", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }


            for (String tel : tels) {
                atel.addItem(tel);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

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
    /*private void initMoving(JFrame frame)
    {
        exit.addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent me){
           dispose();
        }
                
                });}*/


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        atel = new javax.swing.JComboBox<>();
        ntel = new javax.swing.JTextField();
        btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Ancien Téléphone");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Nouveau Téléphone");

        atel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        atel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        atel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        ntel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ntel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn.setText("Modifier");
        btn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(95, 95, 95)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(atel, 0, 216, Short.MAX_VALUE)
                            .addComponent(ntel)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(315, 315, 315)
                        .addComponent(btn, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(atel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ntel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(btn)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMouseClicked(java.awt.event.MouseEvent evt) {
        String ancienTelephone = (String) atel.getSelectedItem();
        String nouveauTelephone = ntel.getText();
        if (!estNombre(nouveauTelephone) || nouveauTelephone.contains(" ") || nouveauTelephone.length() != 8  ) {
            JOptionPane.showMessageDialog(null, "Le numéro cin doit être numérique et de 8 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!esttelUniquearchivete(nouveauTelephone) || !esttelUniqueencadreur(nouveauTelephone) || !esttelUniquearchiveen(nouveauTelephone) || !esttelUniqueetudiant(nouveauTelephone))
        {
            JOptionPane.showMessageDialog(null, "Ce numéro de téléphone est déja existe.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
       int option = showCustomDialog(null, "Voulez-vous afficher toutes les soutenances ?");
        if (option == JOptionPane.YES_OPTION) {
            modifierTelephone(ancienTelephone,nouveauTelephone);
            System.out.println("L'utilisateur a choisi Oui");
        } else if (option == JOptionPane.NO_OPTION) {
            
            System.out.println("L'utilisateur a choisi Non");
        }
    }                                
    private int showCustomDialog(Component parent, String message) {
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
                new Object[]{"Modifier", "Non"},
                "Modifier");
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
    private void modifierTelephone(String ch,String ch1) {

        String queryUpdateTelephone = "UPDATE etudiant SET telephone = ? WHERE telephone = ?";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statementUpdateTelephone = connection.prepareStatement(queryUpdateTelephone);


            statementUpdateTelephone.setString(1, ch1);
            statementUpdateTelephone.setString(2, ch);


            int rowsAffected = statementUpdateTelephone.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Téléphone mis à jour avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Échec de la mise à jour du téléphone.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }



        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour du téléphone : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
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
            java.util.logging.Logger.getLogger(Modiftel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modiftel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modiftel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modiftel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modiftel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> atel;
    private javax.swing.JButton btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField ntel;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
