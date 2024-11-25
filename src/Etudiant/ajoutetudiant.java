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
public class ajoutetudiant extends javax.swing.JFrame {

    String url = "jdbc:mysql://localhost:3306/projetjava";
    String user = "root";
    String password = "12727455kassem";

    public ajoutetudiant() {
        initComponents();
        
        initMoving(ajoutetudiant.this);
        panel.setBackground(Color.decode("#E0EAFC"));
        setLocation(346, 89);
        btn.setOpaque(false);
        btn.setBackground(new Color(0, 0, 0, 0));

        clear.setOpaque(false);
        clear.setBackground(new Color(0, 0, 0, 0));
        prename.setOpaque(false);
        prename.setBackground(new Color(0, 0, 0, 0));
        lastname.setOpaque(false);
        lastname.setBackground(new Color(0, 0, 0, 0));
        cin.setOpaque(false);
        cin.setBackground(new Color(0, 0, 0, 0));
        email.setOpaque(false);
        email.setBackground(new Color(0, 0, 0, 0));
        tel.setOpaque(false);
        tel.setBackground(new Color(0, 0, 0, 0));
        cinencadreur.setOpaque(false);
        //cinencadreur.setBackground(new Color(0, 0, 0, 0));

        niveau.setOpaque(false);
        //niveau.setBackground(new Color(0, 0, 0, 0));

        niveau.removeAllItems();
        niveau.addItem("Licence");
        niveau.addItem("Mastére");
        niveau.addItem("Ingénieur");
        cinencadreur.removeAllItems();
        
        remplir();

    }

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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        prename = new javax.swing.JTextField();
        lastname = new javax.swing.JTextField();
        cin = new javax.swing.JTextField();
        tel = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        niveau = new javax.swing.JComboBox<>();
        btn = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        exit = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cinencadreur = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1086, 639));

        panel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Prename");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Lastname");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Cin");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Téléphone");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Email");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("Niveau");

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

        niveau.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        niveau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        niveau.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn.setText("Ajouter");
        btn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMouseClicked(evt);
            }
        });

        clear.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        clear.setText("Clear");
        clear.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(51, 51, 51), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/exit (1).png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("CinEncadreur");

        cinencadreur.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cinencadreur.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cinencadreur.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(284, 284, 284)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(151, 151, 151)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(prename, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                                .addComponent(lastname)
                                .addComponent(cin)
                                .addComponent(tel)
                                .addComponent(email)))
                        .addGroup(panelLayout.createSequentialGroup()
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(151, 151, 151)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(niveau, 0, 274, Short.MAX_VALUE)
                                .addComponent(cinencadreur, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(55, 55, 55)
                .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(267, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(132, 132, 132))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(prename, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(29, 29, 29)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)))
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cin, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(33, 33, 33)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(cinencadreur, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(niveau, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clear)
                    .addComponent(btn))
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        if (!estNombre(numcinch) || numcinch.contains(" ") || numcinch.length() != 8) {
            JOptionPane.showMessageDialog(null, "Le numéro cin doit être numérique et de 8 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int ncin = Integer.parseInt(numcinch);

        if (!estCINUniqueencadreur(numcinch) || !estCINUniquearchiveen(numcinch) || !estCINUniquearchiveetudiant(numcinch)
        || !estCINUniqueDansJurys(numcinch)) {
            JOptionPane.showMessageDialog(null, "Ce numéro de cin existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String telch = tel.getText();
        if (!estNombre(telch) || telch.contains(" ")) {
            JOptionPane.showMessageDialog(null, "Le numéro téléphone doit être numérique.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int telep = Integer.parseInt(telch);

        if (!esttelUniqueencadreur(telch) || !esttelUniquearchiveen(telch) || !esttelUniqueetudiant(telch) ||
        !esttelUniquearchiveetudiant(telch)) {
            JOptionPane.showMessageDialog(null, "Ce numéro de téléphone existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String em = email.getText();
        if (!validerEmail(em)) {
            JOptionPane.showMessageDialog(null, "L'email n'est pas valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!estemailUniqueencadreur(em) || !estemailUniquearchiveen(em) || !estemailUniqueetudiant(em) ||
        !estemailUniquarchiveetudiant(em))
        {
            JOptionPane.showMessageDialog(null, "Cet email existe déja.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String selectedItem = (String) cinencadreur.getSelectedItem();
        String[] parts = selectedItem.split(" - "); // Diviser la chaîne en parties séparées par " - "
        String cinen = parts[0]; // La première partie est le cin
        int numcinen = Integer.parseInt(cinen);
        String niv = (String) niveau.getSelectedItem();

        String sql = "INSERT INTO etudiant (cin, prenom, nom, telephone, email, datesoutenance, cinencadreur, niveau) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ncin);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, nom);
            preparedStatement.setInt(4, telep);
            preparedStatement.setString(5, em);
            preparedStatement.setDate(6, null);
            preparedStatement.setInt(7, numcinen);
            preparedStatement.setString(8, niv);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                String updateEncadreurSql = "UPDATE encadreur SET nbetudiants = nbetudiants + 1 WHERE cin = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateEncadreurSql);
                updateStatement.setInt(1, numcinen);
                int updateRowsAffected = updateStatement.executeUpdate();

                if (updateRowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Etudiant ajouté avec succés ajouté avec succès.", "Information", JOptionPane.INFORMATION_MESSAGE);
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
            java.util.logging.Logger.getLogger(ajoutetudiant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ajoutetudiant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ajoutetudiant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ajoutetudiant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ajoutetudiant().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn;
    private javax.swing.JTextField cin;
    private javax.swing.JComboBox<String> cinencadreur;
    private javax.swing.JButton clear;
    private javax.swing.JTextField email;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField lastname;
    private javax.swing.JComboBox<String> niveau;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField prename;
    private javax.swing.JTextField tel;
    // End of variables declaration//GEN-END:variables
}
