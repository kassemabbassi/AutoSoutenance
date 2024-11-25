package Etudiant;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Customaffichage extends JDialog {
    public static final int YES_OPTION = 0;
    public static final int NO_OPTION = 1;

    private int selectedOption = -1;

    public Customaffichage(JFrame parent, String title,boolean test) {
        super(parent, title, true);

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new FlowLayout());
        JLabel messageLabel;

        // Création d'une étiquette avec une police plus grande
      if(test==true)
      {
           messageLabel = new JLabel("Voulez-vous afficher tous les étudiants?");
      }
      else
      {
           messageLabel = new JLabel("Voulez-vous afficher tous les encadreurs");
      }
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Augmentation de la taille de la police
        messagePanel.add(messageLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("oui");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedOption = YES_OPTION;
                dispose();
            }
        });
        buttonPanel.add(addButton);

        JButton groupButton = new JButton("non");
        groupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedOption = NO_OPTION;
                dispose();
            }
        });
        buttonPanel.add(groupButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(messagePanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Réglage de la taille de la boîte de dialogue
        setSize(new Dimension(400, 400)); // Augmentation de la taille de la boîte de dialogue
        setPreferredSize(new Dimension(400,100));
        pack();
        setLocationRelativeTo(null);
    }
    public Customaffichage(JFrame parent, String title) {
        super(parent, title, true);

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new FlowLayout());
        JLabel messageLabel;


        messageLabel = new JLabel("Voulez-vous afficher tous les soutenances");


        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Augmentation de la taille de la police
        messagePanel.add(messageLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("oui");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedOption = YES_OPTION;
                dispose();
            }
        });
        buttonPanel.add(addButton);

        JButton groupButton = new JButton("non");
        groupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedOption = NO_OPTION;
                dispose();
            }
        });
        buttonPanel.add(groupButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(messagePanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Réglage de la taille de la boîte de dialogue
        setSize(new Dimension(400, 400)); // Augmentation de la taille de la boîte de dialogue
        setPreferredSize(new Dimension(400,100));
        pack();
        setLocationRelativeTo(null);
    }

    public int showDialog() {
        setVisible(true);
        return selectedOption;
    }
}

