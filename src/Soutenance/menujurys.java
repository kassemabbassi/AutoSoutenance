/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Soutenance;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wind10
 */
public class menujurys extends javax.swing.JFrame {

    String url = "jdbc:mysql://localhost:3306/projetjava";
    String user = "root";
    String password = "12727455kassem";
    public menujurys() {
        initComponents();
        panel.setBackground(Color.decode("#E0EAFC"));// Transparent

        setLocation(370,90);

        // Appliquer un design adorable aux boutons
        styleButton(btn1);
        styleButton(btn3);

        menu.setOpaque(false);
        menu.setBorder(BorderFactory.createEmptyBorder());
        menu.setBackground(new Color(0,0,0,0));
    }

    private final Ajoutejurys aj=new Ajoutejurys();

    private final Affichage aff=new Affichage();



    private void fermeture() {
        aj.setVisible(false);

        final int initialWidth = getWidth();
        final int initialHeight = getHeight();

        // Créer un timer pour réduire progressivement la taille de la fenêtre
        Timer shrinkTimer = new Timer(50, new ActionListener() {
            int width = initialWidth;
            int height = initialHeight;

            @Override
            public void actionPerformed(ActionEvent e) {
                width -= 10;
                height -= 10;

                setSize(width, height);

                // Arrêter le timer et fermer la fenêtre lorsque la taille atteint 0
                if (width <= 20 || height <= 20) {
                    ((Timer) e.getSource()).stop();
                    setVisible(false);
                    dispose();
                }
            }
        });

        // Démarrer le timer
        shrinkTimer.start();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        panel = new javax.swing.JPanel();
        btn1 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        menu = new javax.swing.JLabel();

        // Taille des boutons plus grands
        btn1.setPreferredSize(new Dimension(400, 80));
        btn3.setPreferredSize(new Dimension(400, 80));

        // Paramètres par défaut pour le frame
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        // Appliquer un style personnalisé aux boutons
        styleButton(btn1);
        styleButton(btn3);

        // Définition des polices de texte et ajout des événements de clic
        btn1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // Font de taille 18
        btn1.setText("Ajouter Jurys");
        btn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn1MouseClicked(evt);
            }
        });

        btn3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // Font de taille 18
        btn3.setText("Affichage");
        btn3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn3MouseClicked(evt);
            }
        });
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        // Menu label
        menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Encadreur/menu (1).png"))); // Icône du menu
        menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuMouseClicked(evt);
            }
        });

        // Mise en page avec GroupLayout
        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btn1)
                                        .addComponent(btn3))
                                .addContainerGap(101, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(123, 123, 123)
                                .addComponent(btn1)
                                .addGap(146, 146, 146)
                                .addComponent(btn3)
                                .addContainerGap(254, Short.MAX_VALUE))
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
    }

    private void styleButton(javax.swing.JButton button) {
        button.setFont(new java.awt.Font("Segoe UI", 1, 18)); // Font de taille 18
        button.setFocusPainted(false); // Retirer l'effet de focus par défaut
        button.setOpaque(true); // Nécessaire pour appliquer un fond
        button.setBackground(new Color(0, 0, 0, 0)); // Fond transparent par défaut
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Bordure noire de 2px
        button.setForeground(Color.BLACK); // Texte en noir par défaut

        // Padding et centrage du texte
        button.setMargin(new Insets(15, 30, 15, 30)); // Ajuster les marges pour que le texte soit centré

        // Effet hover : changement de fond et couleur du texte au survol
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(150, 150, 150)); // Fond gris clair au survol
                button.setForeground(Color.BLACK); // Texte en noir
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 0, 0, 0)); // Retour au fond transparent
                button.setForeground(Color.BLACK); // Texte en noir, réinitialisation au départ
            }
        });
    }



    private void btn3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn3MouseClicked
        aj.setVisible(false);

        aff.setVisible(true);
        int option = JOptionPane.showOptionDialog(
                null,
                "Voulez vous afficher les jurys?",
                "Boîte de dialogue personnalisée",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Oui", "Non"},
                "Oui");

        if (option == JOptionPane.YES_OPTION) {
            afficherTousLesjurys();
        }
    }//GEN-LAST:event_btn3MouseClicked

    private void btn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn1MouseClicked
        aj.setVisible(true);
    }//GEN-LAST:event_btn1MouseClicked

    private void menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuMouseClicked
        fermeture();
    }//GEN-LAST:event_menuMouseClicked

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
    }//GEN-LAST:event_btn3ActionPerformed

    private void afficherTousLesjurys() {
        String queryJurys = "SELECT prenompresident, nompresident, prenomrapporteur, nomrapporteur, prenomexaminateur, nomexaminateur, nbsoutenance FROM jurys";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statementJurys = connection.prepareStatement(queryJurys);

            ResultSet resultSetJurys = statementJurys.executeQuery();

            DefaultTableModel modelJurys = new DefaultTableModel();
            modelJurys.addColumn("Président");
            modelJurys.addColumn("Rapporteur");
            modelJurys.addColumn("Examinateur");
            modelJurys.addColumn("NbSoutenance");

            while (resultSetJurys.next()) {
                String president = resultSetJurys.getString("nompresident") + " " + resultSetJurys.getString("prenompresident");
                String rapporteur = resultSetJurys.getString("nomrapporteur") + " " + resultSetJurys.getString("prenomrapporteur");
                String examinateur = resultSetJurys.getString("nomexaminateur") + " " + resultSetJurys.getString("prenomexaminateur");
                Object[] rowData = {
                        president,
                        rapporteur,
                        examinateur,
                        resultSetJurys.getInt("nbsoutenance")
                };
                modelJurys.addRow(rowData);
            }

            aff.getTable().setModel(modelJurys);

            for (int row = 0; row < aff.getTable().getRowCount(); row++) {
                int rowHeight = aff.getTable().getRowHeight();
                for (int column = 0; column < aff.getTable().getColumnCount(); column++) {
                    Component comp = aff.getTable().prepareRenderer(aff.getTable().getCellRenderer(row, column), row, column);
                    rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
                }
                aff.getTable().setRowHeight(row, rowHeight);
            }

            resultSetJurys.close();
            statementJurys.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menujurys.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menujurys.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menujurys.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menujurys.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menujurys().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn3;
    private javax.swing.JLabel menu;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
