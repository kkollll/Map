import java.util.ArrayList;

public class Main {

    private static double testMap(Map<String, Integer> map, String fileName) {

        long startTime = System.nanoTime();

        System.out.println(fileName);

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(fileName, words)) {
            System.out.println("Total words: " + words.size());
            for (String word: words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }
            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }


        long endtTime = System.nanoTime();

        return (endtTime - startTime) / 1000000000.0;

    }

    public static void main(String[] args) {
        String fileName = "pride-and-prejudice.txt";

        Map<String, Integer> bstMap = new BSTMap<>();
        Map<String, Integer> linkedMap = new LinkedListMap<>();

        double t1 = testMap(bstMap, fileName);
        System.out.println("t1 : " + t1 + "s");
        System.out.println();
        double t2 = testMap(linkedMap, fileName);
        System.out.println("t2 : " + t2 + "s");


    }
}
