import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * leetcode349
 */
public class Intersection {

    public int[] intersection(int[] nums1, int[] nums2) {

        Set<Integer> set =  new HashSet<>();

        for (int num : nums1) {
            set.add(num);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (set.contains(num)) {
                list.add(num);
                set.remove(num);
            }
        }
        int[] nums3 = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            nums3[i] = i;
        }
        return nums3;
    }
}
