import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

public class FileProcessor implements Runnable{

    private String fileName;
    private ConcurrentSkipListSet<String> sharedSet;

    public FileProcessor(String fileName, ConcurrentSkipListSet<String> sharedSet) {
        this.fileName = fileName;
        this.sharedSet = sharedSet;
    }

    public static List<String> filesNameList () {

        List<String> fileNames = new ArrayList<>();

        File file = new File("assets/");
        String[] files = file.list();

        for (String name : files) {
            fileNames.add("assets/" + name);
        }
        Collections.sort(fileNames);
        return fileNames;

    }

    public void fileReader(String fileName) {

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                sharedSet.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        fileReader(fileName);
    }

}