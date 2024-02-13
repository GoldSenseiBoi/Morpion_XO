import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MorpionGUI extends JFrame implements ActionListener {
    private JButton[][] boutons;
    private char[][] grille;
    private final char joueurHumain = 'X';
    private final char joueurOrdinateur = 'O';
    private final char vide = ' ';
    private final int taille = 3;
    private boolean tourJoueurHumain;
    private JLabel labelInfo;

    public MorpionGUI() {
        grille = new char[taille][taille];
        boutons = new JButton[taille][taille];
        tourJoueurHumain = true;

        setTitle("Morpion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new BorderLayout());

        JPanel panelGrille = new JPanel();
        panelGrille.setLayout(new GridLayout(taille, taille));

        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                boutons[i][j] = new JButton();
                boutons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                boutons[i][j].addActionListener(this);
                panelGrille.add(boutons[i][j]);
            }
        }

        labelInfo = new JLabel("Tour du joueur humain (X)");
        add(panelGrille, BorderLayout.CENTER);
        add(labelInfo, BorderLayout.SOUTH);

        initialiserGrille();

        setVisible(true);
    }

    private void initialiserGrille() {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                grille[i][j] = vide;
                boutons[i][j].setText("");
            }
        }
    }

    private void afficherGrille() {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                boutons[i][j].setText(Character.toString(grille[i][j]));
            }
        }
    }

    private boolean estGagnant(char joueur) {
        for (int i = 0; i < taille; i++) {
            if (grille[i][0] == joueur && grille[i][1] == joueur && grille[i][2] == joueur) {
                return true; // lignes
            }
            if (grille[0][i] == joueur && grille[1][i] == joueur && grille[2][i] == joueur) {
                return true; // colonnes
            }
        }
        if (grille[0][0] == joueur && grille[1][1] == joueur && grille[2][2] == joueur) {
            return true; // diagonale principale
        }
        if (grille[0][2] == joueur && grille[1][1] == joueur && grille[2][0] == joueur) {
            return true; // diagonale secondaire
        }
        return false;
    }

    private boolean estGrillePleine() {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (grille[i][j] == vide) {
                    return false;
                }
            }
        }
        return true;
    }

    private void jouerCoup(int ligne, int colonne) {
        if (grille[ligne][colonne] == vide) {
            if (tourJoueurHumain) {
                grille[ligne][colonne] = joueurHumain;
                labelInfo.setText("Tour de l'ordinateur (O)");
            } else {
                grille[ligne][colonne] = joueurOrdinateur;
                labelInfo.setText("Tour du joueur humain (X)");
            }
            tourJoueurHumain = !tourJoueurHumain;
            afficherGrille();

            if (estGagnant(joueurHumain)) {
                JOptionPane.showMessageDialog(this, "Bravo ! Vous avez gagné !");
                initialiserGrille();
            } else if (estGagnant(joueurOrdinateur)) {
                JOptionPane.showMessageDialog(this, "Désolé, l'ordinateur a gagné.");
                initialiserGrille();
            } else if (estGrillePleine()) {
                JOptionPane.showMessageDialog(this, "Match nul !");
                initialiserGrille();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (e.getSource() == boutons[i][j]) {
                    jouerCoup(i, j);
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        new MorpionGUI();
    }
}
