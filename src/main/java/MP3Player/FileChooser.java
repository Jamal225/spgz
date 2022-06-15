package MP3Player;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

class  FileChooser extends JFrame implements Chooser {
    private File file;

    public FileChooser() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("MP3 Files", "mp3");
        fileChooser.setFileFilter(filter);
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