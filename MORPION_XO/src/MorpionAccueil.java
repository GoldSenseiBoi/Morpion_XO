import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MorpionAccueil extends JFrame implements ActionListener {
    private JButton btnJoueurHumain, btnOrdinateur;

    public MorpionAccueil() {
        setTitle("Morpion - Accueil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLayout(new GridLayout(2, 1));

        btnJoueurHumain = new JButton("Joueur contre Joueur");
        btnJoueurHumain.addActionListener(this);
        add(btnJoueurHumain);

        btnOrdinateur = new JButton("Joueur contre Ordinateur");
        btnOrdinateur.addActionListener(this);
        add(btnOrdinateur);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnJoueurHumain) {
            new MorpionGUI(false); // Créer une instance de MorpionGUI pour un jeu joueur contre joueur
        } else if (e.getSource() == btnOrdinateur) {
            new MorpionGUI(true); // Créer une instance de MorpionGUI pour un jeu joueur contre ordinateur
        }
        dispose(); // Fermer la fenêtre d'accueil une fois que le jeu est lancé
    }

    public static void main(String[] args) {
        new MorpionAccueil();
    }
}
