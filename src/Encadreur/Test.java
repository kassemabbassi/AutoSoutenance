package Encadreur;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Test {
    String url = "jdbc:mysql://localhost:3306/projetjava";
    String user = "root";
    String password = "12727455kassem";

    public void showMessageDialog(String message) {
        // Création du panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 240, 240)); // Couleur de fond personnalisée

        // Création du label avec texte formaté en HTML
        JLabel label = new JLabel("<html><body style='width: 200px;'>" + message + "</body></html>");
        Font font = new Font("Arial", Font.PLAIN, 18); // Personnalisation de la police
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

    public static void main(String[] args) {
        Test t = new Test();
        String ch = "kassem<br>abbassi"; // Utilisation de <br> pour les sauts de ligne en HTML
        t.showMessageDialog(ch);
    }
}
