package lab.kasir;

import javax.swing.SwingUtilities;

public class MainGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KasirFrame frame = new KasirFrame();
            frame.setVisible(true);
        });
    }
}
