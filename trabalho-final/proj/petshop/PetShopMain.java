
import javax.swing.JButton;
import javax.swing.JFrame;



public class PetShopMain extends JFrame {

    private static final long serialVersionUID = 1L;

    private JButton addPetButton;
    private JButton changePetButton;
    private JButton deletePetButton;
    private JButton viewOwnerButton;

    private PetShopMain() {
        setTitle("Pet Shop");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new PetShopMain().setVisible(true);
    }
}