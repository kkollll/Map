import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String fileName = "pride-and-prejudice.txt";

        ArrayList<String> words = new ArrayList<>();


        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());
            Map<String, Integer> map = new LinkedListMap<>();
            for (String word: words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }
            System.out.println(map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }
    }
}
