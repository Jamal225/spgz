package MP3Player;

public class FileChooserFactory {
    public Chooser getFileChooser(SourceData sourceData){
        Chooser chooser = null;
        switch (sourceData){
            case PC_MEMORY -> chooser = new FileChooser();
        }
        return chooser;
    }
}
