package MP3Player;

import javax.swing.*;
import java.io.Serial;

class  FileChooser extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;

    private final JFileChooser fileChooser;

    public FileChooser() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выбор директории");
        // Определение режима - только каталог
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(FileChooser.this);
        // Если директория выбрана, покажем ее в сообщении
        if (result == JFileChooser.APPROVE_OPTION )
            JOptionPane.showMessageDialog(FileChooser.this,
                    fileChooser.getSelectedFile());
    }

}