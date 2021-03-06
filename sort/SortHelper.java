package www.sort;

public class SortHelper {
        public static void printArray(int[] array) {
            for(int i : array) {
                System.out.print(i+" ");
            }
        }

    public static int[] generateArray(int n,int rangeL,int rangeR) {
            int array[] = new int[n];
            if(rangeL > rangeR) {
                throw new IllegalArgumentException("范围非法");
            }else {
                for(int i = 0; i < n; i++) {
                    array[i] = new Random().nextInt((rangeR-rangeL+1)+rangeL);
                }
                return array;
            }
    }
}
