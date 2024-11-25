/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Soutenance;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.SecureRandom;
import java.security.spec.ECField;
import java.sql.*;
import javax.swing.*;
import java.security.SecureRandom;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;



public class Ajoutesoutenance extends javax.swing.JFrame {

    String url = "jdbc:mysql://localhost:3306/projetjava";
    String user = "root";
    String password = "12727455kassem";
    public Ajoutesoutenance() {
        initComponents();
        initMoving(Ajoutesoutenance.this);
        panel.setBackground(Color.decode("#E0EAFC"));
        setLocation(370,90);
        btn.setOpaque(false);
        btn.setBackground(new Color(0,0,0,0));
        btn.setOpaque(false);
        btn.setBackground(new Color(0,0,0,0));
        cinetudiant.setOpaque(false);
        //cinetudiant.setBackground(new Color(0,0,0,0));
        cinetudiant.removeAllItems();
        cinencadreur.setOpaque(false);
        cinencadreur.setBackground(new Color(0,0,0,0));
        cinencadreur.setEditable(false);
        
        nom.setOpaque(false);
        nom.setBackground(new Color(0,0,0,0));
        nom.setEditable(false);
       prenom.setOpaque(false);
        prenom.setBackground(new Color(0,0,0,0));
        prenom.setEditable(false);

        remplir();
    }
    private int genererCode() {
        SecureRandom random = new SecureRandom();
        int min = 10000000; // Le minimum est 10^7 = 10000000
        int max = 99999999; // Le maximum est 10^8 - 1 = 99999999
        return random.nextInt(max - min + 1) + min;
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
        String sql = "SELECT cin FROM etudiant WHERE datesoutenance IS NULL AND cin NOT IN (SELECT cin1 FROM etudiantgoupe UNION SELECT cin2 FROM etudiantgoupe)";
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
                cinetudiant.addItem(cin);
                trouve = true;
            }

            if (!trouve) {
                //JOptionPane.showMessageDialog(null, "Aucun étudiant trouvé avec une date de soutenance NULL", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void ajouterSoutenance() {
        LocalDate date;
        LocalTime heure;

        // Récupérer la dernière ligne de la table soutenance
        Soutenance derniereSoutenance = recupererDerniereSoutenance1();

        // Si la dernière soutenance est null ou si sa date et heure sont nulles
        if (derniereSoutenance == null || derniereSoutenance.getDate() == null || derniereSoutenance.getHeure() == null) {
            // Commencer le 1er juin 2024 à 9h si c'est un jour de semaine
            date = LocalDate.of(2025, 6, 1); // 1er juin 2024
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




    private void ajouterSoutenanceBDD(LocalDate date, LocalTime heure, String local) {
        String cinch = (String) cinetudiant.getSelectedItem();
        if(cinch==null)
        {
            JOptionPane.showMessageDialog(null, "Il n'existe pas d'étudiants", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int numcin = Integer.parseInt(cinch);
        String selectedItem = (String) cinencadreur.getText();
        String[] parts = selectedItem.split(" - "); // Diviser la chaîne en parties séparées par " - "
        String cinen = parts[0]; // La première partie est le cin
        int numcinen = Integer.parseInt(cinen);
        int code = genererCode();
        /*try{
            int cinpresident = Integer.parseInt(recupererCinPresidentAleatoire());
        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "il n existe de plus des jurys", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }*/
        String cinpresch=recupererCinPresidentAleatoire();
        String detailsjurys=rechercherdetailsjurys1(cinpresch);
        int cinpresident = Integer.parseInt(cinpresch);
        /*String message1 = "Soutenance ajoutée avec succès<br>" +
                "Date de soutenance : " + date + "\n" +
                "Heure : " + heure + "\n" +
                "Local : " + local + "\n" +
                "Jurys :\n" + detailsjurys;*/
        String message = "<html><font color='#336699'>******Soutenance ajoutée avec succès******</font><br><font color='#336699'>" +
                "Date de soutenance: </font>" + date +
                "<font color='#336699'><br>Heure: </font>" + heure +
                "<font color='#336699'><br>Local: </font>"+local +
                "<font color='#336699'><br>Jurys: </font>" + detailsjurys + "</html>";

        String sqlSoutenance = "INSERT INTO soutenance (codesecret,cinetudiant,cinencadreur,datesoutenance, heure, local,cinpresident,note) VALUES (?, ?, ?,?,?,?,?,?)";
        String sqlUpdateJurys = "UPDATE jurys SET cinencadreur = ? WHERE cinpresident = ? AND cinencadreur = 0";
        String sqlUpdateEtudiant = "UPDATE etudiant SET datesoutenance = ?, heure = ? WHERE cin = ?";
        String updatenbsoutenance = "UPDATE jurys SET nbsoutenance = nbsoutenance + 1 WHERE cinpresident = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statementSoutenance = connection.prepareStatement(sqlSoutenance);
             PreparedStatement statementUpdateJurys = connection.prepareStatement(sqlUpdateJurys);
             PreparedStatement statementUpdateEtudiant = connection.prepareStatement(sqlUpdateEtudiant);
             PreparedStatement statementUpdateNbSoutenance = connection.prepareStatement(updatenbsoutenance)){

            statementSoutenance.setInt(1, code);
            statementSoutenance.setInt(2, numcin);
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

            statementUpdateNbSoutenance.setInt(1, cinpresident);
            statementUpdateNbSoutenance.executeUpdate();
           int rowsaffected= statementUpdateEtudiant.executeUpdate();
           if(rowsaffected>0)
           {
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
                juryDetails.append("CIN du rapporteur : ").append(resultSetJurys.getInt("cinrapporteur")).append("   <br>");
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

            // Afficher les détails du premier jury trouvé
            juryDetails.append("Nom du président : ").append(resultSetJurys.getString("nompresident")).append(" ");
            juryDetails.append(resultSetJurys.getString("prenompresident")).append("<br>");
            juryDetails.append("Nom du rapporteur : ").append(resultSetJurys.getString("nomrapporteur")).append(" ");
            juryDetails.append(resultSetJurys.getString("prenomrapporteur")).append("<br>");
            juryDetails.append("Nom de l'examinateur : ").append(resultSetJurys.getString("nomexaminateur")).append(" ");
            juryDetails.append(resultSetJurys.getString("prenomexaminateur")).append("<br><br>");

        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Erreur lors de la recherche du jury pour ce président.";
        }

        return juryDetails.toString();
    }






    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cinetudiant = new javax.swing.JComboBox<>();
        btn = new javax.swing.JButton();
        exit = new javax.swing.JLabel();
        checkbox = new javax.swing.JCheckBox();
        cinencadreur = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nom = new javax.swing.JTextField();
        prenom = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1060, 639));

        panel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("CinEtudiant");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("CinEncadreur");

        cinetudiant.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cinetudiant.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cinetudiant.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        cinetudiant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cinetudiantMouseClicked(evt);
            }
        });
        cinetudiant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cinetudiantActionPerformed(evt);
            }
        });

        btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn.setText("Ajouter");
        btn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMouseClicked(evt);
            }
        });

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/exit (1).png"))); // NOI18N

        checkbox.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        checkbox.setText("Recevoire un email");

        cinencadreur.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cinencadreur.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Nom");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Prénom");

        nom.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        nom.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        prenom.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        prenom.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(463, 463, 463)
                        .addComponent(btn, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(312, 312, 312)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(127, 127, 127)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(checkbox, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(cinetudiant, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cinencadreur, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(nom)
                            .addComponent(prenom))))
                .addContainerGap(271, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(308, 308, 308))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cinetudiant, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(prenom, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cinencadreur, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(checkbox, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(btn)
                .addGap(95, 95, 95))
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



    private void cinetudiantMouseClicked(MouseEvent evt) {

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

    private void btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMouseClicked
        int option = showCustomDialog(null, "Voulez-vous ajouter  cette  soutenance ?");

        if (option == JOptionPane.YES_OPTION) {
            ajouterSoutenance();
            cinetudiant.removeAllItems();
            remplir();
        } else if (option == JOptionPane.NO_OPTION) {

            System.out.println("L'utilisateur a choisi Non");
        }

    }//GEN-LAST:event_btnMouseClicked

    private void cinetudiantActionPerformed(java.awt.event.ActionEvent evt) {
        String cinSelectionne = (String) cinetudiant.getSelectedItem();
        // Requête SQL pour récupérer le cinencadreur correspondant
        String sql = "SELECT cinencadreur, nom, prenom FROM etudiant WHERE cin = ?";
        String sql1 = "SELECT nom, prenom FROM encadreur WHERE cin = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cinSelectionne);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String cinEncadreur = rs.getString("cinencadreur");
                    String nomet = rs.getString("nom");
                    String prenomet = rs.getString("prenom");
                    // Mettre à jour les JTextFields avec les informations de l'étudiant
                    nom.setText(nomet);
                    prenom.setText(prenomet);

                    try (PreparedStatement statement1 = connection.prepareStatement(sql1)) {
                        statement1.setString(1, cinEncadreur);
                        try (ResultSet rs1 = statement1.executeQuery()) {
                            if (rs1.next()) {
                                String nomEncadreur = rs1.getString("nom");
                                String prenomEncadreur = rs1.getString("prenom");
                                String fullName = cinEncadreur + " - " + nomEncadreur + " " + prenomEncadreur;
                                // Mettre à jour le JTextField avec les informations de l'encadreur correspondant
                                cinencadreur.setText(fullName);
                            } else {
                                cinencadreur.setText(""); // Réinitialiser le JTextField si aucun encadreur correspondant n'est trouvé
                            }
                        }
                    }
                } else {
                    cinencadreur.setText(""); // Réinitialiser le JTextField s'il n'y a pas de cinencadreur correspondant
                    prenom.setText("");
                    nom.setText("");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des informations", e);
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
            java.util.logging.Logger.getLogger(Ajoutesoutenance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ajoutesoutenance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ajoutesoutenance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ajoutesoutenance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ajoutesoutenance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn;
    private javax.swing.JCheckBox checkbox;
    private javax.swing.JTextField cinencadreur;
    private javax.swing.JComboBox<String> cinetudiant;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField nom;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField prenom;
    // End of variables declaration//GEN-END:variables
}
class Soutenance {
    private LocalDate date;
    private LocalTime heure;

    public Soutenance(LocalDate date, LocalTime heure) {
        this.date = date;
        this.heure = heure;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getHeure() {
        return heure;
    }}