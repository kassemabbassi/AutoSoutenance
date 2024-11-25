/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Soutenance;

import Etudiant.CustomDialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.SecureRandom;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.*;

/**
 *
 * @author Wind10
 */
public class Ajoutesoutenancegroupe extends javax.swing.JFrame {

    String url = "jdbc:mysql://localhost:3306/projetjava";
    String user = "root";
    String password = "12727455kassem";
    public Ajoutesoutenancegroupe() {
        initComponents();
        initMoving(Ajoutesoutenancegroupe.this);
        panel.setBackground(Color.decode("#E0EAFC"));
        setLocation(370,90);
        btn.setOpaque(false);
        btn.setBackground(new Color(0,0,0,0));
        btn.setOpaque(false);
        btn.setBackground(new Color(0,0,0,0));
        cin1.setOpaque(false);
        //cinetudiant.setBackground(new Color(0,0,0,0));
        cin1.removeAllItems();
        cin2.setOpaque(false);
        cin2.setBackground(new Color(0,0,0,0));
        cin2.setEditable(false);
        cinencadreur.setOpaque(false);
        cinencadreur.setBackground(new Color(0,0,0,0));
        cinencadreur.setEditable(false);
        cinencadreur.setEditable(false);
        nom1.setOpaque(false);
        nom1.setBackground(new Color(0,0,0,0));
        nom1.setEditable(false);
        nom1.setEditable(false);

        prenom1.setOpaque(false);
        prenom1.setBackground(new Color(0,0,0,0));
        prenom1.setEditable(false);
        prenom1.setEditable(false);

        nom2.setOpaque(false);
        nom2.setBackground(new Color(0,0,0,0));
        nom2.setEditable(false);
        nom2.setEditable(false);

        prenom2.setOpaque(false);
        prenom2.setBackground(new Color(0,0,0,0));
        prenom2.setEditable(false);
        prenom2.setEditable(false);

        remplir();
    }
    private void remplir() {
        String sql = "SELECT cin1 FROM etudiantgoupe WHERE datesoutenance IS NULL";
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database.");
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            boolean trouve = false;
            while (rs.next()) {
                String cin = rs.getString("cin1");
                cin1.addItem(cin);
                trouve = true;
            }

            if (!trouve) {

                //JOptionPane.showMessageDialog(null, "Aucun étudiant trouvé avec une date de soutenance NULL", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private int genererCode() {
        SecureRandom random = new SecureRandom();
        int min = 10000000; // Le minimum est 10^7 = 10000000
        int max = 99999999; // Le maximum est 10^8 - 1 = 99999999
        return random.nextInt(max - min + 1) + min;
    }
   private void ajouterSoutenanceBDD(LocalDate date, LocalTime heure, String local) {
        String cinch = (String) cin1.getSelectedItem();
        if(cinch==null)
        {
            JOptionPane.showMessageDialog(null, "Il n'existe pas d'étudiants", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String cinch2 = cin2.getText();
        int numcin = Integer.parseInt(cinch);
        int numcin2 = Integer.parseInt(cinch2);
       String selectedItem = (String) cinencadreur.getText();
       String[] parts = selectedItem.split(" - "); // Diviser la chaîne en parties séparées par " - "
       String cinen = parts[0]; // La première partie est le cin
        int numcinen = Integer.parseInt(cinen);
        int code = genererCode();

        /*try {
            int cinpresident = Integer.parseInt(recupererCinPresident());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "il n existe de plus des jurys", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }*/
        String cinpresch = recupererCinPresidentAleatoire();
        String detailsjurys = rechercherdetailsjurys1(cinpresch);
        int cinpresident = Integer.parseInt(recupererCinPresident());
       String message = "<html><font color='#336699'>******Soutenance ajoutée avec succès******</font><br><font color='#336699'>" +
               "Date de soutenance: </font>" + date +
               "<font color='#336699'><br>Heure: </font>" + heure +
               "<font color='#336699'><br>Local: </font>"+local +
               "<font color='#336699'><br>Jurys: </font>" + detailsjurys + "</html>";
        String sqlSoutenance = "INSERT INTO soutenance (codesecret,cinetudiant,cinencadreur,datesoutenance, heure, local,cinpresident,note) VALUES (?, ?, ?,?,?,?,?,?)";
        String sqlUpdateJurys = "UPDATE jurys SET cinencadreur = ? WHERE cinpresident = ? AND cinencadreur = 0";
        String sqlUpdateEtudiant = "UPDATE etudiant SET datesoutenance = ?, heure = ? WHERE cin = ?";
        String setupadateetudiantgroupe = "UPDATE etudiantgoupe SET datesoutenance = ? WHERE cin1 = ? OR cin2 = ?";
        String updatenbsoutenance = "UPDATE jurys SET nbsoutenance = nbsoutenance + 1 WHERE cinpresident = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statementSoutenance = connection.prepareStatement(sqlSoutenance);
             PreparedStatement statementUpdateJurys = connection.prepareStatement(sqlUpdateJurys);
             PreparedStatement statementUpdateEtudiant = connection.prepareStatement(sqlUpdateEtudiant);
             PreparedStatement statementUpdateEtudiantGroupe = connection.prepareStatement(setupadateetudiantgroupe);
             PreparedStatement statementUpdateNbSoutenance = connection.prepareStatement(updatenbsoutenance)) {

            statementSoutenance.setInt(1, code);
            statementSoutenance.setInt(2, numcin);
            statementSoutenance.setInt(3, numcinen);
            statementSoutenance.setDate(4, java.sql.Date.valueOf(date));
            statementSoutenance.setTime(5, java.sql.Time.valueOf(heure));
            statementSoutenance.setString(6, local);
            statementSoutenance.setInt(7, cinpresident);
            statementSoutenance.setString(8, null);
            statementSoutenance.executeUpdate();
            statementSoutenance.setInt(1, code);
            statementSoutenance.setInt(2, numcin2);
            statementSoutenance.setInt(3, numcinen);
            statementSoutenance.setDate(4, java.sql.Date.valueOf(date));
            statementSoutenance.setTime(5, java.sql.Time.valueOf(heure));
            statementSoutenance.setString(6, local);
            statementSoutenance.setInt(7, cinpresident);
            statementSoutenance.setString(8, null);
            statementSoutenance.executeUpdate();

            // Mise à jour du cinencadreur dans la table jurys
            statementUpdateJurys.setInt(1, numcinen);
            statementUpdateJurys.setInt(2, cinpresident);
            statementUpdateJurys.executeUpdate();

            // Mise à jour de la date et de l'heure de soutenance dans la table etudiant
            statementUpdateEtudiant.setDate(1, java.sql.Date.valueOf(date));
            statementUpdateEtudiant.setTime(2, java.sql.Time.valueOf(heure));
            statementUpdateEtudiant.setInt(3, numcin);
            statementUpdateEtudiant.executeUpdate();
            statementUpdateEtudiant.setDate(1, java.sql.Date.valueOf(date));
            statementUpdateEtudiant.setTime(2, java.sql.Time.valueOf(heure));
            statementUpdateEtudiant.setInt(3, numcin2);
            int rowsaffected = statementUpdateEtudiant.executeUpdate();

            // Mise à jour de la date de soutenance dans la table etudiantgroupe
            statementUpdateEtudiantGroupe.setDate(1, java.sql.Date.valueOf(date));
            statementUpdateEtudiantGroupe.setInt(2, numcin);
            statementUpdateEtudiantGroupe.setInt(3, numcin);

            statementUpdateEtudiantGroupe.executeUpdate();

            // Mise à jour du nombre de soutenances pour le président du jury
            statementUpdateNbSoutenance.setInt(1, cinpresident);
            statementUpdateNbSoutenance.executeUpdate();
            if (rowsaffected > 0) {
               // CustomDialog c=new CustomDialog(this,"Ajout");
                //c.showMessageDialog(message);
                //showMessageDialog(message);
                showAdorableMessage(message);
                return;
            }



        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout de la soutenance dans la base de données", e);
        }
    }
    public void showMessageDialog(String message) {
        // Création du panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 240, 240)); // Couleur de fond personnalisée
        panel.setPreferredSize(new Dimension(450, 250)); // Augmentation de la taille du panneau

        // Création du label avec texte formaté en HTML
        JLabel label = new JLabel("<html><body style='width: 400px;'>" + message + "</body></html>");
        Font font = new Font("Calibri", Font.BOLD, 18); // Police élégante
        label.setFont(font);
        label.setForeground(new Color(50, 50, 50)); // Couleur de texte personnalisée
        label.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Marge intérieure

        // Ajout du label au panel
        panel.add(label, BorderLayout.CENTER);

        // Personnalisation de la boîte de dialogue
        UIManager.put("OptionPane.background", new Color(240, 240, 240)); // Couleur de fond
        UIManager.put("Panel.background", new Color(240, 240, 240)); // Couleur de fond du panel interne
        UIManager.put("OptionPane.messageFont", font); // Police du message
        UIManager.put("OptionPane.messageForeground", new Color(50, 50, 50)); // Couleur du texte du message

        // Affichage de la boîte de dialogue
        JOptionPane.showMessageDialog(
                null,
                panel,
                "Ajout de soutenance",
                JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon("icon.png")); // Ajout d'une icône
    }
    public void showAdorableMessage(String message) {
        // Création du panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 218, 185)); // Couleur de fond pastel
        panel.setPreferredSize(new Dimension(500, 300)); // Taille du panneau ajustée

        // Création du label avec texte formaté en HTML
        JLabel label = new JLabel("<html><body style='width: 450px;'>" + message + "</body></html>");
        Font font = new Font("Comic Sans MS", Font.BOLD, 20); // Police adorable
        label.setFont(font);
        label.setForeground(new Color(255, 105, 180)); // Couleur de texte rose
        label.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Marge intérieure généreuse

        // Ajout du label au panel
        panel.add(label, BorderLayout.CENTER);

        // Personnalisation de la boîte de dialogue
        UIManager.put("OptionPane.background", new Color(255, 218, 185)); // Couleur de fond pastel
        UIManager.put("Panel.background", new Color(255, 218, 185)); // Couleur de fond du panel interne
        UIManager.put("OptionPane.messageFont", font); // Police du message
        UIManager.put("OptionPane.messageForeground", new Color(255, 105, 180)); // Couleur du texte du message

        // Affichage de la boîte de dialogue
        JOptionPane.showMessageDialog(
                null,
                panel,
                "Message Adorable",
                JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon("adorable_icon.png")); // Icône adorable
    }


    private void initMoving(JFrame frame)
    {
        exit.addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent me){
           dispose();
        }
                
                });}

    public void ajouterSoutenance() {
        LocalDate date;
        LocalTime heure;

        // Récupérer la dernière ligne de la table soutenance
        Soutenance derniereSoutenance = recupererDerniereSoutenance1();

        // Si la dernière soutenance est null ou si sa date et heure sont nulles
        if (derniereSoutenance == null || derniereSoutenance.getDate() == null || derniereSoutenance.getHeure() == null) {
            // Commencer le 1er juin 2024 à 9h si c'est un jour de semaine
            date = LocalDate.of(2024, 6, 1); // 1er juin 2024
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                date = trouverProchainJourOuvrable(date);
            }
            heure = LocalTime.of(9, 0); // 9am
        } else {
            // Récupérer la date et l'heure de la dernière soutenance
            date = derniereSoutenance.getDate();
            heure = derniereSoutenance.getHeure();

            // Si c'est une journée de semaine
            if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                // Si l'heure est 9am, planifier la prochaine soutenance à 11am
                if (heure.equals(LocalTime.of(9, 0))) {
                    heure = LocalTime.of(11, 0);
                } else if (heure.equals(LocalTime.of(11, 0))) { // Si l'heure est 11am, planifier la prochaine soutenance à 13am
                    heure = LocalTime.of(13, 0);
                } else { // Si l'heure est 13am
                    // Si c'est jeudi, passer à la semaine suivante
                    if (date.getDayOfWeek() == DayOfWeek.THURSDAY) {
                        date = trouverProchainJourOuvrable(date.plusDays(1));
                        heure = LocalTime.of(9, 0); // Commencer à 9am
                    } else {
                        // Passer à la prochaine journée de semaine
                        date = date.plusDays(1);
                        heure = LocalTime.of(9, 0); // Commencer à 9am
                    }
                }
            } else { // Si c'est samedi ou dimanche, passer au prochain lundi
                date = trouverProchainJourOuvrable(date.plusDays(1));
                heure = LocalTime.of(9, 0); // Commencer à 9am
            }
        }

        String local;
        if (heure.equals(LocalTime.of(9, 0))) {
            local = "Amphi B";
        } else if (heure.equals(LocalTime.of(11, 0))) {
            local = "Amphi K";
        } else {
            local = "Amphi B";
        }
        // Ajouter la nouvelle soutenance dans la base de données
        ajouterSoutenanceBDD(date, heure, local);
    }
    public String recupererCinPresidentAleatoire() {
        String cinPresident = null;
        String sqlCount = "SELECT COUNT(*) AS count FROM jurys";
        String sql = "SELECT cinpresident FROM jurys WHERE nbsoutenance = (SELECT MIN(nbsoutenance) FROM jurys) ORDER BY RAND() LIMIT 1";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statementCount = connection.createStatement();
             ResultSet resultSetCount = statementCount.executeQuery(sqlCount)) {

            // Vérifier le nombre de jurys dans la table
            if (resultSetCount.next()) {
                int count = resultSetCount.getInt("count");
                if (count == 0) {
                    JOptionPane.showMessageDialog(null, "Il n'existe aucun jury.");
                    return null;
                }
            }

            // Exécuter la requête pour récupérer un CIN aléatoire
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    cinPresident = resultSet.getString("cinpresident");
                }
            }

        } catch (SQLException e ) {
            throw new RuntimeException("Erreur lors de la récupération du CIN du président aléatoire parmi les jurys avec le moins de soutenances", e);
        }

        return cinPresident;
    }


    public  String rechercherdetailsjurys(String cinpresident) {


        String queryJurys = "SELECT * FROM jurys WHERE cinpresident = ?";
        StringBuilder juryDetails = new StringBuilder();

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statementJurys = connection.prepareStatement(queryJurys)) {

            statementJurys.setString(1, cinpresident);
            ResultSet resultSetJurys = statementJurys.executeQuery();

            // Si aucun jury n'est trouvé pour ce président, retourner un message
            if (!resultSetJurys.next()) {
                return "Aucun jury trouvé pour ce président.";
            }

            // Afficher les détails du jury
            do {
                juryDetails.append("CIN du président : ").append(resultSetJurys.getInt("cinpresident")).append("<br>");
                juryDetails.append("CIN du rapporteur : ").append(resultSetJurys.getInt("cinrapporteur")).append("<br>");
                juryDetails.append("CIN de l'examinateur : ").append(resultSetJurys.getInt("cinexaminateur")).append("\n\n");
            } while (resultSetJurys.next());

        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Erreur lors de la recherche du jury pour ce président.";
        }

        return juryDetails.toString();
    }
    public String rechercherdetailsjurys1(String cinpresident) {
        String queryJurys = "SELECT prenompresident, nompresident, prenomrapporteur, nomrapporteur, prenomexaminateur, nomexaminateur FROM jurys WHERE cinpresident = ?";
        StringBuilder juryDetails = new StringBuilder();

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statementJurys = connection.prepareStatement(queryJurys)) {

            statementJurys.setString(1, cinpresident);
            ResultSet resultSetJurys = statementJurys.executeQuery();

            // Si aucun jury n'est trouvé pour ce président, retourner un message
            if (!resultSetJurys.next()) {
                return "Aucun jury trouvé pour ce président.";
            }

            // Afficher les détails du jury
            do {
                juryDetails.append("Nom du président : ").append(resultSetJurys.getString("nompresident")).append(" ");
                juryDetails.append(resultSetJurys.getString("prenompresident")).append("<br>");
                juryDetails.append("Nom du rapporteur : ").append(resultSetJurys.getString("nomrapporteur")).append(" ");
                juryDetails.append(resultSetJurys.getString("prenomrapporteur")).append("<br>");
                juryDetails.append("Nom de l'examinateur : ").append(resultSetJurys.getString("nomexaminateur")).append(" ");
                juryDetails.append(resultSetJurys.getString("prenomexaminateur")).append("<br><br>");
            } while (resultSetJurys.next());

        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Erreur lors de la recherche du jury pour ce président.";
        }

        return juryDetails.toString();
    }
    private Soutenance recupererDerniereSoutenance() {
        String sql = "SELECT datesoutenance, heure FROM soutenance ORDER BY datesoutenance DESC, heure DESC LIMIT 1";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                LocalDate date = resultSet.getDate("datesoutenance").toLocalDate();
                LocalTime heure = resultSet.getTime("heure").toLocalTime();
                return new Soutenance(date, heure);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de la dernière soutenance", e);
        }
        return null;
    }
    private Soutenance recupererDerniereSoutenance1() {
        String sqlSoutenance = "SELECT datesoutenance, heure FROM soutenance ORDER BY datesoutenance DESC, heure DESC LIMIT 1";
        String sqlSoutenanceArchive = "SELECT datesoutenance, heure FROM soutenancearchive ORDER BY datesoutenance DESC, heure DESC LIMIT 1";

        try {
            Soutenance lastSoutenance = null;

            // Recherche dans la table soutenance
            try (Connection connection = DriverManager.getConnection(url, user, password);
                 PreparedStatement statement = connection.prepareStatement(sqlSoutenance);
                 ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    LocalDate date = resultSet.getDate("datesoutenance").toLocalDate();
                    LocalTime heure = resultSet.getTime("heure").toLocalTime();
                    lastSoutenance = new Soutenance(date, heure);
                }
            }

            // Si la table soutenance est vide, recherche dans la table soutenancearchive
            if (lastSoutenance == null) {
                try (Connection connection = DriverManager.getConnection(url, user, password);
                     PreparedStatement statement = connection.prepareStatement(sqlSoutenanceArchive);
                     ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        LocalDate date = resultSet.getDate("datesoutenance").toLocalDate();
                        LocalTime heure = resultSet.getTime("heure").toLocalTime();
                        lastSoutenance = new Soutenance(date, heure);
                    }
                }
            }

            return lastSoutenance;

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de la dernière soutenance", e);
        }
    }
    private LocalDate trouverProchainJourOuvrable(LocalDate date) {
        while (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            date = date.plusDays(1);
        }
        return date;
    }
    public String recupererCinPresident() {
        String cinPresident = null;
        String sql = "SELECT cinpresident FROM jurys ORDER BY nbsoutenance ASC LIMIT 1";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                cinPresident = resultSet.getString("cinpresident");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération du cinprésident", e);
        }
        return cinPresident;
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        cin1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cinencadreur = new javax.swing.JTextField();
        exit = new javax.swing.JLabel();
        btn = new javax.swing.JButton();
        cin2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nom1 = new javax.swing.JTextField();
        prenom1 = new javax.swing.JTextField();
        nom2 = new javax.swing.JTextField();
        prenom2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panel.setBackground(new java.awt.Color(255, 255, 255));

        cin1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cin1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cin1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        cin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cin1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Cin1");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Cin2");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Cin Encadreur");

        cinencadreur.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cinencadreur.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/exit (1).png"))); // NOI18N

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

        cin2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cin2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Nom");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Prénom");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("Nom");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("Prénom");

        nom1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        nom1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        prenom1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        prenom1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        nom2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        nom2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        prenom2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        prenom2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap(254, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(134, 134, 134))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)))
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cinencadreur, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(cin2, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(prenom1)
                    .addComponent(nom1)
                    .addComponent(cin1, 0, 250, Short.MAX_VALUE)
                    .addComponent(nom2)
                    .addComponent(prenom2))
                .addContainerGap(329, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(238, 238, 238))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cin1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(26, 26, 26)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(nom1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(prenom1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cin2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addGap(44, 44, 44)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(nom2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(prenom2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cinencadreur, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(10, 10, 10)
                .addComponent(btn)
                .addGap(75, 75, 75))
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

    private void btnActionPerformed(ActionEvent evt) {
    }

    private void btnMouseClicked(MouseEvent evt) {
        int option = showCustomDialog(null, "Voulez-vous ajouter  cette  soutenance ?");

        if (option == JOptionPane.YES_OPTION) {
            ajouterSoutenance();
            cin1.removeAllItems();
            remplir();
        } else if (option == JOptionPane.NO_OPTION) {

            System.out.println("L'utilisateur a choisi Non");
        }
    }


    private void cin1ActionPerformed(java.awt.event.ActionEvent evt) {                                     
        String cinSelectionne = (String) cin1.getSelectedItem();
        // Requête SQL pour récupérer le cinencadreur correspondant
        String sql = "SELECT cin2,cinencadreur FROM etudiantgoupe WHERE cin1 = ?";
        String sql1 = "SELECT nom,prenom FROM etudiant WHERE cin = ?";
        String sql2 = "SELECT nom,prenom FROM etudiant WHERE cin = ?";
        String sql4 = "SELECT nom, prenom FROM encadreur WHERE cin = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             PreparedStatement statement1 = connection.prepareStatement(sql1)){
            statement.setString(1, cinSelectionne);
            statement1.setString(1,cinSelectionne);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String cinEncadreur = rs.getString("cinencadreur");

                    String cin2et=rs.getString("cin2");
                    cin2.setText(cin2et);
                    nom1.setText("");
                    try(PreparedStatement statement4=connection.prepareStatement(sql4)){
                        statement4.setString(1,cinEncadreur);
                        try(ResultSet rs4=statement4.executeQuery()){
                            if(rs4.next())
                            {
                                String nomEncadreur = rs4.getString("nom");
                                String prenomEncadreur = rs4.getString("prenom");
                                String fullName = cinEncadreur + " - " + nomEncadreur + " " + prenomEncadreur;
                                // Mettre à jour le JTextField avec les informations de l'encadreur correspondant
                                cinencadreur.setText(fullName);
                            }else {
                                cinencadreur.setText("");
                            }
                        }
                    }


                } else {
                    cinencadreur.setText(""); // Réinitialiser le JTextField s'il n'y a pas de cinencadreur correspondant
                    cin2.setText("");
                }
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                statement2.setString(1,cin2.getText());
                ResultSet rs2=statement2.executeQuery();
                if(rs2.next())
                {
                    String nom2et=rs2.getString("nom");
                    String prenom2et=rs2.getString("prenom");
                    nom2.setText(nom2et);
                    prenom2.setText(prenom2et);
                }
                else
                {
                    nom2.setText("");
                    prenom2.setText("");
                }
            }
            try (ResultSet rs=statement1.executeQuery()){
                if(rs.next())
                {
                    String nom1et = rs.getString("nom");
                    String prenom1et=rs.getString("prenom");
                    nom1.setText(nom1et);
                    prenom1.setText(prenom1et);
                }
                else
                {
                    nom1.setText("");
                    prenom1.setText("");

                }

            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération du cinencadreur correspondant", e);
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
            java.util.logging.Logger.getLogger(Ajoutesoutenancegroupe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ajoutesoutenancegroupe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ajoutesoutenancegroupe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ajoutesoutenancegroupe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ajoutesoutenancegroupe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn;
    private javax.swing.JComboBox<String> cin1;
    private javax.swing.JTextField cin2;
    private javax.swing.JTextField cinencadreur;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField nom1;
    private javax.swing.JTextField nom2;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField prenom1;
    private javax.swing.JTextField prenom2;
    // End of variables declaration//GEN-END:variables
}
