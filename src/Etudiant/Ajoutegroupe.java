/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Etudiant;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Wind10
 */
public class Ajoutegroupe extends javax.swing.JFrame {

    String url = "jdbc:mysql://localhost:3306/projetjava";
    String user = "root";
    String password = "12727455kassem";
    public Ajoutegroupe() {
        initComponents();
        initMoving(Ajoutegroupe.this);
        panel.setBackground(Color.decode("#E0EAFC"));
        setLocation(346, 89);
        btn.setOpaque(false);
        btn.setBackground(new Color(0, 0, 0, 0));
        prenom1.setOpaque(false);
        prenom1.setBackground(new Color(0, 0, 0, 0));
        
        nom1.setOpaque(false);
        nom1.setBackground(new Color(0, 0, 0, 0));
        
        cin1.setOpaque(false);
        cin1.setBackground(new Color(0, 0, 0, 0));
        
        tel1.setOpaque(false);
        tel1.setBackground(new Color(0, 0, 0, 0));
        
        email1.setOpaque(false);
        email1.setBackground(new Color(0, 0, 0, 0));
        
        niveau1.setOpaque(false);
        niveau1.removeAllItems();
        niveau1.addItem("Licence");
        niveau1.addItem("Mastére");
        niveau1.addItem("Ingénieur");
        
        prenom2.setOpaque(false);
        prenom2.setBackground(new Color(0, 0, 0, 0));
        
        nom2.setOpaque(false);
        nom2.setBackground(new Color(0, 0, 0, 0));
        
        cin2.setOpaque(false);
        cin2.setBackground(new Color(0, 0, 0, 0));
        
        tel2.setOpaque(false);
        tel2.setBackground(new Color(0, 0, 0, 0));
        
        email2.setOpaque(false);
        email2.setBackground(new Color(0, 0, 0, 0));
        
        niveau2.setOpaque(false);
        niveau2.removeAllItems();
        niveau2.addItem("Licence");
        niveau2.addItem("Mastére");
        niveau2.addItem("Ingénieur");
        
        cinencadreur.setOpaque(false);
        cinencadreur.removeAllItems();

       remplir();

    }
    
    
     private void initMoving(JFrame frame)
    {
        exit.addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent me){
           dispose();
        }
                
                });}
    private void remplir() {
        String sql = "SELECT cin, nom, prenom FROM encadreur WHERE nbetudiants < 3";
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database.");
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            boolean trouve = false;
            while (rs.next()) {
                String cin = rs.getString("cin");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String fullName = cin + " - " + nom + " " + prenom; // Concaténation de cin, nom et prénom
                cinencadreur.addItem(fullName);
                trouve = true;
            }
            if (!trouve) {
                // Aucun encadreur avec moins de 3 étudiants trouvé
                // Vous pouvez gérer cela en affichant un message d'erreur ou en effectuant d'autres actions nécessaires
                // JOptionPane.showMessageDialog(null, "il n'existe pas d'encadreurs avec moins de 3 étudiants", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Assurez-vous de fermer les ressources, même en cas d'exception
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
    private boolean estCINUniquearchiveetudiant(String cinEtudiant) {
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
    private boolean estemailUniquarchiveetudiant(String email) {
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
    private boolean esttelUniquearchiveetudiant(String tel) {
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


    public static boolean validerEmail(String email) {
        // Expression régulière pour valider un email
        String regex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";

        return email.matches(regex);
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
    private void ajouter()
    {
        String prenom1ch = prenom1.getText();
        if (!prenom1ch.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "Le premier prénom doit être alphabétique.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nom1ch = nom1.getText();
        if (!nom1ch.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "Le premier nom doit être alphabétique.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String numcinch1 = cin1.getText();
        if (!estNombre(numcinch1) || numcinch1.contains(" ") || numcinch1.length() != 8) {
            JOptionPane.showMessageDialog(null, "Le premier numéro cin doit être numérique et de 8 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int ncin = Integer.parseInt(numcinch1);

        if (!estCINUniqueencadreur(numcinch1) || !estCINUniquearchiveen(numcinch1) || !estCINUniqueDansJurys(numcinch1)
        || !estCINUniquearchiveetudiant(numcinch1)) {
            JOptionPane.showMessageDialog(null, "Ce premier numéro de cin existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String telch1 = tel1.getText();
        if (!estNombre(telch1) || telch1.contains(" ") || telch1.length()!=8 ||(telch1.charAt(0)!='9' && telch1.charAt(0)!='5' && telch1.charAt(0)!='2')) {
            JOptionPane.showMessageDialog(null, "Le premier numéro téléphone doit être numérique.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int telep = Integer.parseInt(telch1);

        if (!esttelUniqueencadreur(telch1) || !esttelUniquearchiveen(telch1) || !esttelUniqueetudiant(telch1)
        || !esttelUniquearchiveetudiant(telch1)) {
            JOptionPane.showMessageDialog(null, "Ce premier numéro de téléphone existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String em1 = email1.getText();
        if (!validerEmail(em1)) {
            JOptionPane.showMessageDialog(null, "Le premier email n'est pas valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!estemailUniqueencadreur(em1) || !estemailUniquearchiveen(em1) || !estemailUniqueetudiant(em1)
        || !estemailUniquarchiveetudiant(em1))
        {
            JOptionPane.showMessageDialog(null, "Ce premier email existe déja.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String niv1 = (String) niveau1.getSelectedItem();


        String prenom2ch = prenom2.getText();
        if (!prenom2ch.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "Le deuxéme prénom doit être alphabétique.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nom2ch = nom2.getText();
        if (!nom2ch.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "Le deuxéme nom doit être alphabétique.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String numcinch2 = cin2.getText();
        if (!estNombre(numcinch2) || numcinch2.contains(" ") || numcinch2.length() != 8) {
            JOptionPane.showMessageDialog(null, "Le deuxéme numéro cin doit être numérique et de 8 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int ncin2 = Integer.parseInt(numcinch2);

        if (numcinch2.compareTo(numcinch1)==0 || !estCINUniqueencadreur(numcinch2) || !estCINUniquearchiveen(numcinch2) || !estCINUniqueDansJurys(numcinch2)
                || !estCINUniquearchiveetudiant(numcinch2)) {
            JOptionPane.showMessageDialog(null, "Ce deuxéme numéro de cin existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String telch2 = tel2.getText();
        if (!estNombre(telch2) || telch2.contains(" ") || telch2.length()!=8 || (telch2.charAt(0)!='9' && telch2.charAt(0)!='5' && telch2.charAt(0)!='2') ) {
            JOptionPane.showMessageDialog(null, "Le deuxéme numéro téléphone doit être numérique.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int telep2 = Integer.parseInt(telch1);

        if (telch2.compareTo(telch1)==0 || !esttelUniqueencadreur(telch2) || !esttelUniquearchiveen(telch2) || !esttelUniqueetudiant(telch2)
                || !esttelUniquearchiveetudiant(telch2)) {
            JOptionPane.showMessageDialog(null, "Ce deuxéme numéro de téléphone existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String em2 = email2.getText();
        if (!validerEmail(em2)) {
            JOptionPane.showMessageDialog(null, "Le deuxéme email n'est pas valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if( em2.compareTo(em1)==0 || !estemailUniqueencadreur(em2) || !estemailUniquearchiveen(em2) || !estemailUniqueetudiant(em2)
                || !estemailUniquarchiveetudiant(em2))
        {
            JOptionPane.showMessageDialog(null, "Ce deuxéme email existe déja.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String niv2 = (String) niveau2.getSelectedItem();

        String selectedItem = (String) cinencadreur.getSelectedItem();
        String[] parts = selectedItem.split(" - "); // Diviser la chaîne en parties séparées par " - "
        String cinen = parts[0]; // La première partie est le cin
        int numcinen = Integer.parseInt(cinen);

        String sql1 = "INSERT INTO etudiant (cin, prenom, nom, telephone, email, datesoutenance, cinencadreur, heure,niveau) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
        String sql2 = "INSERT INTO etudiant (cin, prenom, nom, telephone, email, datesoutenance, cinencadreur, heure,niveau) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
        String sql3="INSERT INTO etudiantgoupe (cin1, cin2, cinencadreur,datesoutenance) VALUES (?, ?, ?,?)";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            PreparedStatement preparedStatement3=connection.prepareStatement(sql3);
            preparedStatement.setInt(1, ncin);
            preparedStatement.setString(2, prenom1ch);
            preparedStatement.setString(3, nom1ch);
            preparedStatement.setInt(4, telep);
            preparedStatement.setString(5, em1);
            preparedStatement.setDate(6, null);
            preparedStatement.setInt(7, numcinen);
            preparedStatement.setTime(8, null);
            preparedStatement.setString(9,niv1);

            preparedStatement2.setInt(1, ncin2);
            preparedStatement2.setString(2, prenom2ch);
            preparedStatement2.setString(3, nom2ch);
            preparedStatement2.setInt(4, telep2);
            preparedStatement2.setString(5, em2);
            preparedStatement2.setDate(6, null);
            preparedStatement2.setInt(7, numcinen );
            preparedStatement2.setTime(8, null);
            preparedStatement2.setString(9,niv2);

            preparedStatement3.setInt(1,ncin);
            preparedStatement3.setInt(2,ncin2);
            preparedStatement3.setInt(3,numcinen);
            preparedStatement3.setDate(4,null);



            int rowsAffected = preparedStatement.executeUpdate();
            int rowsaffected2=preparedStatement2.executeUpdate();
            int rowsaffected3=preparedStatement3.executeUpdate();

            if (rowsAffected > 0 && rowsaffected2>0 && rowsaffected3>0) {
                String updateEncadreurSql = "UPDATE encadreur SET nbetudiants = nbetudiants + 2 WHERE cin = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateEncadreurSql);
                updateStatement.setInt(1, numcinen);
                int updateRowsAffected = updateStatement.executeUpdate();

                if (updateRowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Etudiants ajoutés avec succés ajouté avec succès.", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Échec de la mise à jour du nombre d'étudiants de l'encadreur.", "Information", JOptionPane.ERROR_MESSAGE);
                }

                updateStatement.close();
            } else {
                JOptionPane.showMessageDialog(null, "Échec de l'insertion de l'étudiant.", "Information", JOptionPane.ERROR_MESSAGE);
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            if (e.getSQLState().startsWith("23")) { // Les codes d'erreur commencent généralement par "23" pour les violations de contraintes
                JOptionPane.showMessageDialog(null, "Erreur : Cette clé existe déjà dans la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {

                JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
        cinencadreur.removeAllItems();
        remplir();

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        prenom1 = new javax.swing.JTextField();
        nom1 = new javax.swing.JTextField();
        cin1 = new javax.swing.JTextField();
        tel1 = new javax.swing.JTextField();
        email1 = new javax.swing.JTextField();
        niveau1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        prenom2 = new javax.swing.JTextField();
        nom2 = new javax.swing.JTextField();
        cin2 = new javax.swing.JTextField();
        tel2 = new javax.swing.JTextField();
        email2 = new javax.swing.JTextField();
        niveau2 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cinencadreur = new javax.swing.JComboBox<>();
        btn = new javax.swing.JButton();
        exit = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1101, 639));

        panel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Prénom");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Nom");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Cin");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Téléphone");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Email");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("Niveau");

        prenom1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        prenom1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        nom1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        nom1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        cin1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cin1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        tel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        email1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        email1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        niveau1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        niveau1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        niveau1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        niveau1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                niveau1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("Prénom");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setText("Nom");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("Cin");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setText(" Téléphone");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setText("Email");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setText("Niveau");

        prenom2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        prenom2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        nom2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        nom2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        cin2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cin2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        tel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        email2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        email2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        niveau2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        niveau2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        niveau2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setText("Cin encadreur");

        cinencadreur.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cinencadreur.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cinencadreur.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn.setText("Ajouter");
        btn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMouseClicked(evt);
            }
        });
        btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionPerformed(evt);
            }
        });

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/exit (1).png"))); // NOI18N

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(prenom1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                    .addComponent(nom1)
                                    .addComponent(email1))
                                .addGap(84, 84, 84)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cin1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tel1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(niveau1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGap(84, 84, 84)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGap(72, 72, 72)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(prenom2, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                        .addComponent(nom2)
                                        .addComponent(cin2)))
                                .addComponent(email2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(niveau2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(cinencadreur, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(0, 911, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addComponent(btn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84))))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(prenom1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(prenom2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(nom1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addComponent(nom2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2))
                        .addGap(31, 31, 31)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cin1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(cin2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(tel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(email1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(email2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(niveau1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(niveau2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(55, 55, 55)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cinencadreur, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(92, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn)
                        .addGap(73, 73, 73))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void niveau1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_niveau1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_niveau1ActionPerformed

    private void btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionPerformed
        ajouter();
    }//GEN-LAST:event_btnActionPerformed

    private void btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMouseClicked

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
            java.util.logging.Logger.getLogger(Ajoutegroupe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ajoutegroupe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ajoutegroupe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ajoutegroupe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ajoutegroupe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn;
    private javax.swing.JTextField cin1;
    private javax.swing.JTextField cin2;
    private javax.swing.JComboBox<String> cinencadreur;
    private javax.swing.JTextField email1;
    private javax.swing.JTextField email2;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox<String> niveau1;
    private javax.swing.JComboBox<String> niveau2;
    private javax.swing.JTextField nom1;
    private javax.swing.JTextField nom2;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField prenom1;
    private javax.swing.JTextField prenom2;
    private javax.swing.JTextField tel1;
    private javax.swing.JTextField tel2;
    // End of variables declaration//GEN-END:variables
}
