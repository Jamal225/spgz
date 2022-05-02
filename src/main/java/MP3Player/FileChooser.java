package MP3Player;

import javax.swing.*;
import java.io.File;
import java.io.Serial;

class  FileChooser extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;

    private final JFileChooser fileChooser;
    File file;

    public FileChooser() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выбор директории");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(FileChooser.this);
        if (result == JFileChooser.APPROVE_OPTION ) {
            JOptionPane.showMessageDialog(FileChooser.this,
                    fileChooser.getSelectedFile());
            file = fileChooser.getSelectedFile();
        }
    }

    public File getFile(){
        return file;
    }

}