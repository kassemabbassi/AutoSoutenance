package Encadreur;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Box;

public class Menuencadreur extends javax.swing.JPanel {

    public Menuencadreur() {
        initComponents();
        setOpaque(false);

        // Configuration des boutons
        customizeButton(btn1, "Ajout");
        customizeButton(btn2, "Supp et modif");
        customizeButton(btn3, "Recherche");
        customizeButton(btn5, "Archive");
    }

    private void customizeButton(JButton button, String text) {
        button.setText(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Effet hover
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setOpaque(true);
                button.setBackground(new Color(41, 128, 185)); // Bleu clair
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setOpaque(false);
                button.setBackground(new Color(0, 0, 0, 0));
            }
        });
    }

    @Override
    protected void paintChildren(Graphics grph) {
        Graphics2D g2 = (Graphics2D) grph;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#2980b9"), 0, getHeight(), Color.decode("#2c3e50"));
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintChildren(grph);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new JPanel();
        icon = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btn1 = new JButton();
        btn2 = new JButton();
        btn3 = new JButton();
        btn5 = new JButton();

        setPreferredSize(new Dimension(239, 639));

        jPanel1.setOpaque(false);

        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Encadreur/classroom (1).png")));
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setText("Encadreur");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createSequentialGroup()
                        .addComponent(icon)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(icon)
                                .addComponent(jLabel1))
        );

        // Configuration du JPanel pour les boutons avec un espacement vertical plus grand
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 0, 30)); // Espacement vertical augmenté à 30 pixels
        buttonPanel.setOpaque(false);

        // Ajouter un espace vertical avant les boutons pour les déplacer vers le bas
        buttonPanel.add(Box.createVerticalStrut(100)); // Espace de 100 pixels avant les boutons
        buttonPanel.add(btn1);
        buttonPanel.add(btn2);
        buttonPanel.add(btn3);
        buttonPanel.add(btn5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    public JButton getBtn1() {
        return btn1;
    }

    public JButton getBtn2() {
        return btn2;
    }

    public JButton getBtn3() {
        return btn3;
    }

    public JButton getBtn5() {
        return btn5;
    }

    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn5;
    private javax.swing.JLabel icon;
    private javax.swing.JLabel jLabel1;
    private JPanel jPanel1;
}
