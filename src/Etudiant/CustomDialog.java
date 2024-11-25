package Etudiant;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomDialog extends JDialog {
    public static final int YES_OPTION = 0;
    public static final int NO_OPTION = 1;

    private int selectedOption = -1;

    public CustomDialog(JFrame parent, String title) {
        super(parent, title, true);

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new FlowLayout());

        // Création d'une étiquette avec une police plus grande
        JLabel messageLabel = new JLabel("Voulez-vous ajouter un étudiant ou un groupe?");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Augmentation de la taille de la police
        messagePanel.add(messageLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("Un seul");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedOption = YES_OPTION;
                dispose();
            }
        });
        buttonPanel.add(addButton);

        JButton groupButton = new JButton("Groupe");
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
        setLocationRelativeTo(parent);
    }
    public CustomDialog(JFrame parent, String title,boolean test) {
        super(parent, title, true);

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new FlowLayout());

        // Création d'une étiquette avec une police plus grande
        JLabel messageLabel = new JLabel("Voulez-vous ajouter une soutenance pour un seul étudiant ou un groupe?");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Augmentation de la taille de la police
        messagePanel.add(messageLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("Un seul");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedOption = YES_OPTION;
                dispose();
            }
        });
        buttonPanel.add(addButton);

        JButton groupButton = new JButton("Groupe");
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
        setPreferredSize(new Dimension(600,100));
        pack();
        setLocationRelativeTo(parent);
    }

    public int showDialog() {
        setVisible(true);
        return selectedOption;
    }


}
