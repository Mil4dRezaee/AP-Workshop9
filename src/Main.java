import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        ConcurrentSkipListSet<String> sharedSet = new ConcurrentSkipListSet<>();

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        for (String fileName : FileProcessor.filesNameList()) {
            FileProcessor fileProcessor = new FileProcessor(fileName, sharedSet);
            executorService.submit(fileProcessor);
        }

        executorService.shutdown();

        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Map <String, String> info = aggregateResults(sharedSet);

        System.out.println("size is: " + info.get("size"));
        System.out.println("average is: " + info.get("average"));
        System.out.println("biggest is: " + info.get("biggest"));
        System.out.println("smallest is: " + info.get("smallest"));

    }

    public static Map<String, String> aggregateResults(Set<String> words) {

        Map<String, String> info = new HashMap<>();

        long overallLength = 0;

        long size = words.size();
        long averageWords;

        String biggestWord = "";
        String smallestWord = size > 0 ? words.iterator().next() : "";

        for (String word : words) {
            if (word.length() > biggestWord.length())
                biggestWord = word;
            if (word.length() < smallestWord.length())
                smallestWord = word;
            overallLength += word.length();
        }
        averageWords = overallLength / size;

        info.put("size", String.valueOf(size));
        info.put("average", String.valueOf(averageWords));
        info.put("biggest", biggestWord);
        info.put("smallest", smallestWord);

        return info;

    }

}