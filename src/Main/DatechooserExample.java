package Main;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class DatechooserExample {

    public static void main(String[] args) {
        // Créer la fenêtre principale
        JFrame frame = new JFrame("Sélection de Date");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        // Créer un JDateChooser pour la sélection de date
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy"); // Définir le format de la date

        // Créer un bouton pour valider la sélection
        JButton button = new JButton("Choisir une date");

        // Ajouter un gestionnaire d'événements pour le bouton
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date selectedDate = dateChooser.getDate();
                if (selectedDate != null) {
                    // Afficher la date sélectionnée dans une boîte de dialogue
                    JOptionPane.showMessageDialog(frame, "Date sélectionnée : " + selectedDate.toString());
                } else {
                    // Si aucune date n'est sélectionnée, afficher un message d'erreur
                    JOptionPane.showMessageDialog(frame, "Veuillez choisir une date.");
                }
            }
        });

        // Ajouter les composants au frame
        frame.add(dateChooser);
        frame.add(button);

        // Afficher la fenêtre
        frame.setVisible(true);
    }
}
