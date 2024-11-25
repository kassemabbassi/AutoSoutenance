/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Soutenance;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

/**
 *
 * @author Wind10
 */
public class Ajoutejurys extends javax.swing.JFrame {
    String url = "jdbc:mysql://localhost:3306/projetjava";
    String user = "root";
    String password = "12727455kassem";


    public Ajoutejurys() {
        initComponents();
        initMoving(Ajoutejurys.this);
        panel.setBackground(Color.decode("#E0EAFC"));
        setLocation(395,95);
        btn.setOpaque(false);
        btn.setBackground(new Color(0,0,0,0));
        btn.setOpaque(false);
        btn.setBackground(new Color(0,0,0,0));
        
        //cinetudiant.setBackground(new Color(0,0,0,0));
        
         cinp.setOpaque(false);
        cinp.setBackground(new Color(0,0,0,0));
        
         cinex.setOpaque(false);
        cinex.setBackground(new Color(0,0,0,0));
        
        cinr.setOpaque(false);
        cinr.setBackground(new Color(0,0,0,0));
        
        nompres.setOpaque(false);
        nompres.setBackground(new Color(0,0,0,0));
        prenompres.setOpaque(false);
        prenompres.setBackground(new Color(0,0,0,0));
        
        nomr.setOpaque(false);
        nomr.setBackground(new Color(0,0,0,0));
        prenomr.setOpaque(false);
        prenomr.setBackground(new Color(0,0,0,0));
        
         nomex.setOpaque(false);
        nomex.setBackground(new Color(0,0,0,0));
        prenomex.setOpaque(false);
        prenomex.setBackground(new Color(0,0,0,0));
        
        
        
        
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
        exit = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cinp = new javax.swing.JTextField();
        cinex = new javax.swing.JTextField();
        cinr = new javax.swing.JTextField();
        btn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nompres = new javax.swing.JTextField();
        prenompres = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nomr = new javax.swing.JTextField();
        prenomr = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        nomex = new javax.swing.JTextField();
        prenomex = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panel.setBackground(new java.awt.Color(255, 255, 255));

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/exit (1).png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Cin Président");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Cin Rapporteur");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Cin Examinateur");

        cinp.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cinp.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        cinex.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cinex.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        cinr.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cinr.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn.setText("Ajouter");
        btn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Nom Président");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Prénom Président");

        nompres.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        nompres.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        prenompres.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        prenompres.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("Nom Rapporteur");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("Prénom Rapporteur");

        nomr.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        nomr.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        prenomr.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        prenomr.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setText("Nom Examinateur");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("Prénom Examinateur");

        nomex.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        nomex.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        prenomex.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        prenomex.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(nompres, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(430, 430, 430)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cinp, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cinex, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cinr, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nomr)
                            .addComponent(prenompres)
                            .addComponent(prenomr)
                            .addComponent(nomex)
                            .addComponent(prenomex, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))))
                .addGap(83, 83, 83)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addComponent(btn, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(389, 389, 389))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(404, 404, 404))))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(nompres, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cinp, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5))
                            .addComponent(prenompres, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomr, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cinr, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(19, 19, 19)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(prenomr, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(41, 41, 41)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomex, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cinex, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(prenomex, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(btn)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMouseClicked(java.awt.event.MouseEvent evt) {                                 
        String numcinch = cinp.getText();
        String chnomp=nompres.getText();
        String chprenomp=prenompres.getText();
        if (!estNombre(numcinch) || numcinch.contains(" ") || numcinch.length() != 8) {
            JOptionPane.showMessageDialog(null, "Le numéro cin de président doit être numérique et de 8 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!estCINUniqueencadreur(numcinch) || !estCINUniquearchiveet(numcinch)  || !estCINUniqueetudiant(numcinch)) {
            JOptionPane.showMessageDialog(null, "Ce numéro cin de président existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!chnomp.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "Le nom de président doit être alphabétique.", "Erreur", JOptionPane.ERROR_MESSAGE);

            return;
        }
        if (!chprenomp.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "Le prénom de président doit être alphabétique.", "Erreur", JOptionPane.ERROR_MESSAGE);

            return;
        }
        int cinPres = Integer.parseInt(numcinch);
        String chnomr=nomr.getText();
        String chprenomr=prenomr.getText();
        String numcinchr = cinr.getText();
        if (!estNombre(numcinchr) || numcinchr.contains(" ") || numcinchr.length() != 8) {
            JOptionPane.showMessageDialog(null, "Le numéro cin de rapporteur doit être numérique et de 8 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (numcinchr.equals(numcinch)|| !estCINUniqueencadreur(numcinchr) || !estCINUniquearchiveet(numcinchr)  || !estCINUniqueetudiant(numcinchr)) {
            JOptionPane.showMessageDialog(null, "Ce numéro cin de rapporteur existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!chnomr.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "Le nom de rapporteur doit être alphabétique.", "Erreur", JOptionPane.ERROR_MESSAGE);

            return;
        }
        if (!chprenomr.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "Le prénom de rapporteur doit être alphabétique.", "Erreur", JOptionPane.ERROR_MESSAGE);

            return;
        }
        int cinRap = Integer.parseInt(numcinchr);
        String chnomex=nomex.getText();
        String chprenomex=prenomex.getText();
        String telch = cinex.getText();
        if (!estNombre(telch) || telch.contains(" ") || telch.length() != 8) {
            JOptionPane.showMessageDialog(null, "Le numéro cin de l'examinateur doit être numérique et de 8 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!estCINUniqueencadreur(telch) || !estCINUniquearchiveet(telch)  || !estCINUniqueetudiant(telch) || telch.compareTo(numcinch) == 0 || telch.compareTo(numcinchr) == 0) {
            JOptionPane.showMessageDialog(null, "Ce numéro cin de l'examinateur existe déjà ou est identique à celui du président ou du rapporteur.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!chnomp.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "Le nom de l'examinateur doit être alphabétique.", "Erreur", JOptionPane.ERROR_MESSAGE);

            return;
        }
        if (!chprenomp.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "Le prénom de l'examinateur doit être alphabétique.", "Erreur", JOptionPane.ERROR_MESSAGE);

            return;
        }
        int cinExam = Integer.parseInt(telch);

        if(estCINUniquejurys(numcinch,numcinchr,telch))
        {
            String sql = "INSERT INTO jurys (cinpresident, cinrapporteur, cinexaminateur, cinencadreur, nbsoutenance," +
                    "prenompresident,nompresident,prenomrapporteur,nomrapporteur,prenomexaminateur,nomexaminateur)" +
                    " VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?)";
            try {
                Connection connection = DriverManager.getConnection(url, user, password);
                System.out.println("Connected to the database.");

                // Create a PreparedStatement
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setInt(1, cinPres); // Numéro CIN du président
                preparedStatement.setInt(2, cinRap); // Numéro CIN du rapporteur
                preparedStatement.setInt(3, cinExam); // Numéro CIN de l'examinateur
                preparedStatement.setInt(4, 0); // Numéro CIN de l'encadreur, s'il est requis
                preparedStatement.setInt(5, 0); // Nombre de soutenances, initialisé à 0
                preparedStatement.setString(6,chprenomp);
                preparedStatement.setString(7,chnomp);
                preparedStatement.setString(8,chprenomr);
                preparedStatement.setString(9,chnomr);
                preparedStatement.setString(10,chprenomex);
                preparedStatement.setString(11,chnomex);

                // Execute the PreparedStatement to insert data
                int rowsAffected = preparedStatement.executeUpdate();

                // Check if insertion was successful
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Jurys ajouté avec succès.", "Information", JOptionPane.INFORMATION_MESSAGE);
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
        /*else {
            JOptionPane.showMessageDialog(null, "Cette jurys existe déja", "Erreur", JOptionPane.ERROR_MESSAGE);
        }*/



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


    /*private boolean estCINUniquejurys(String cinPresident, String cinRapporteur, String cinExaminateur) {
        String sql = "SELECT * FROM jurys WHERE cinpresident = ? AND cinrapporteur = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, cinPresident);
            pstmt.setString(2, cinRapporteur);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // Pour chaque ligne trouvée avec les mêmes CIN de président et rapporteur
                    // Vérifiez si l'examinateur est également le même
                    if (cinExaminateur.equals(rs.getString("cinexaminateur"))) {
                        // Un jury avec ces trois CIN existe déjà
                        return false;
                    }
                }
                // Aucun jury avec les mêmes CIN de président et rapporteur, ou examinateur différent
                // C'est donc considéré comme une nouvelle entrée de jury
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    /*private boolean estCINUniquejurys(String cinPresident, String cinRapporteur, String cinExaminateur) {
        String sql = "SELECT * FROM jurys WHERE cinpresident = ? OR cinrapporteur = ? OR cinexaminateur = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, cinPresident);
            pstmt.setString(2, cinRapporteur);
            pstmt.setString(3, cinExaminateur);

            try (ResultSet rs = pstmt.executeQuery()) {
                boolean presidentExiste = false;
                boolean rapporteurExiste = false;
                boolean examinateurExiste = false;

                while (rs.next()) {
                    // Pour chaque ligne trouvée avec un des CIN
                    // Vérifiez si les noms et prénoms associés sont également les mêmes
                    String prenomPresidentDB = rs.getString("prenompresident");
                    String nomPresidentDB = rs.getString("nompresident");
                    String prenomRapporteurDB = rs.getString("prenomrapporteur");
                    String nomRapporteurDB = rs.getString("nomrapporteur");
                    String prenomExaminateurDB = rs.getString("prenomexaminateur");
                    String nomExaminateurDB = rs.getString("nomexaminateur");

                    // Vérification pour le président
                    if (cinPresident.equals(rs.getString("cinpresident"))) {
                        if (!prenomPresidentDB.equals(prenompres.getText()) || !nomPresidentDB.equals(nompres.getText())) {
                            afficherMessageErreur("Erreur : Le nom ou le prénom du président existant ne correspond pas.");
                            return false;
                        }
                        presidentExiste = true;
                    }
                    // Vérification pour le rapporteur
                    if (cinRapporteur.equals(rs.getString("cinrapporteur"))) {
                        if (!prenomRapporteurDB.equals(prenomr.getText()) || !nomRapporteurDB.equals(nomr.getText())) {
                            afficherMessageErreur("Erreur : Le nom ou le prénom du rapporteur existant ne correspond pas.");
                            return false;
                        }
                        rapporteurExiste = true;
                    }
                    // Vérification pour l'examinateur
                    if (cinExaminateur.equals(rs.getString("cinexaminateur"))) {
                        if (!prenomExaminateurDB.equals(prenomex.getText()) || !nomExaminateurDB.equals(nomex.getText())) {
                            afficherMessageErreur("Erreur : Le nom ou le prénom de l'examinateur existant ne correspond pas.");
                            return false;
                        }
                        examinateurExiste = true;
                    }
                }
                // Si les trois CIN existent déjà dans une même ligne, afficher une erreur
                if (presidentExiste && rapporteurExiste && examinateurExiste) {
                    afficherMessageErreur("Erreur : Ces trois CIN existent déjà dans une même ligne.");
                    return false;
                }
                // Si au moins une des variables président, rapporteur ou examinateur est vrai,
                // cela signifie qu'au moins un CIN existe déjà
                if (presidentExiste || rapporteurExiste || examinateurExiste) {
                    return true;
                }
            }
            // Si aucun des CIN n'existe déjà, retournez true
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
    private boolean estCINUniquejurys(String cinPresident, String cinRapporteur, String cinExaminateur) {
        String sql = "SELECT * FROM jurys WHERE cinpresident = ? OR cinrapporteur = ? OR cinexaminateur = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, cinPresident);
            pstmt.setString(2, cinRapporteur);
            pstmt.setString(3, cinExaminateur);

            try (ResultSet rs = pstmt.executeQuery()) {
                boolean presidentExiste = false;
                boolean rapporteurExiste = false;
                boolean examinateurExiste = false;

                while (rs.next()) {
                    // Pour chaque ligne trouvée avec un des CIN
                    // Vérifiez si les noms et prénoms associés sont également les mêmes
                    String prenomPresidentDB = rs.getString("prenompresident");
                    String nomPresidentDB = rs.getString("nompresident");
                    String prenomRapporteurDB = rs.getString("prenomrapporteur");
                    String nomRapporteurDB = rs.getString("nomrapporteur");
                    String prenomExaminateurDB = rs.getString("prenomexaminateur");
                    String nomExaminateurDB = rs.getString("nomexaminateur");

                    // Vérification pour le président
                    if (cinPresident.equals(rs.getString("cinpresident")) && cinRapporteur.equals(rs.getString("cinrapporteur"))
                    && cinExaminateur.equals(rs.getString("cinexaminateur"))) {
                        afficherMessageErreur("Erreur : Ces trois CIN existent déjà dans une même ligne.");
                        return false;
                    }
                    if(cinPresident.equals(rs.getString("cinpresident")))
                    {

                        if (!prenomPresidentDB.equals(prenompres.getText()) || !nomPresidentDB.equals(nompres.getText())) {
                            afficherMessageErreur("Erreur : Le nom ou le prénom du président existant ne correspond pas.");
                            return false;
                        }
                        presidentExiste = true;
                    }
                    // Vérification pour le rapporteur
                    if (cinRapporteur.equals(rs.getString("cinrapporteur"))) {
                        if (!prenomRapporteurDB.equals(prenomr.getText()) || !nomRapporteurDB.equals(nomr.getText())) {
                            afficherMessageErreur("Erreur : Le nom ou le prénom du rapporteur existant ne correspond pas.");
                            return false;
                        }
                        rapporteurExiste = true;
                    }
                    // Vérification pour l'examinateur
                    if (cinExaminateur.equals(rs.getString("cinexaminateur"))) {
                        if (!prenomExaminateurDB.equals(prenomex.getText()) || !nomExaminateurDB.equals(nomex.getText())) {
                            afficherMessageErreur("Erreur : Le nom ou le prénom de l'examinateur existant ne correspond pas.");
                            return false;
                        }
                        examinateurExiste = true;
                    }
                }
                // Si les trois CIN existent déjà dans une même ligne, afficher une erreur
                /*if (presidentExiste && rapporteurExiste && examinateurExiste) {
                    afficherMessageErreur("Erreur : Ces trois CIN existent déjà dans une même ligne.");
                    return false;
                }*/
                // Si au moins une des variables président, rapporteur ou examinateur est vrai,
                // cela signifie qu'au moins un CIN existe déjà
                if (presidentExiste || rapporteurExiste || examinateurExiste) {
                    return true;
                }
            }
            // Si aucun des CIN n'existe déjà, retournez true
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }






    private void afficherMessageErreur(String message) {
        JOptionPane.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);

    }





    private boolean estCINUniqueencadreur(String cinEtudiant) {
        String sql = "SELECT cin FROM encadreur where nbetudiants!=0";
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
            java.util.logging.Logger.getLogger(Ajoutejurys.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ajoutejurys.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ajoutejurys.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ajoutejurys.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ajoutejurys().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn;
    private javax.swing.JTextField cinex;
    private javax.swing.JTextField cinp;
    private javax.swing.JTextField cinr;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField nomex;
    private javax.swing.JTextField nompres;
    private javax.swing.JTextField nomr;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField prenomex;
    private javax.swing.JTextField prenompres;
    private javax.swing.JTextField prenomr;
    // End of variables declaration//GEN-END:variables
}
