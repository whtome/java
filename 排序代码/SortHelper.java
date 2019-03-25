import java.util.Arrays;
import java.util.Random;

/**
 * @Author: yuisama
 * @Date: 2019-03-07 19:29
 * @Description:
 */
public class SortHelper {
    public static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i+" ");
        }
    }

    /**
     *
     * @param n 要生成的数组长度
     * @param rangeL 数组左边界
     * @param rangeR 数组右边界
     * @return
     */
    public static int[] generateArray(int n,int rangeL,int rangeR) {
        int array[] = new int[n];
        if (rangeL > rangeR) {
            throw new IllegalArgumentException("范围非法");
        }else {
            for (int i = 0;i < n;i++) {
                array[i] = new Random().nextInt((rangeR-rangeL+1)
                        +rangeL);
            }
            return array;
        }
    }
    public static int[] copyArray(int[] array) {
        return Arrays.copyOf(array,array.length);
    }

    /**
     *
     * @param n 要生成的数据大小
     * @param swapTimes 交换次数
     * @return
     */
    public static int[] generateNearlySortedArray(int n,int swapTimes) {
        int[] result = new int[n];
        for (int i = 0;i < n;i++) {
            // 先构造一个完全有序的数据集
            result[i] = i;
        }
        for (int i = 0;i < swapTimes;i++) {
            // 随机选取范围内的两个索引下标进行交换
            Random random = new Random();
            int a = random.nextInt(n);
            int b = random.nextInt(n);
            int temp =  result[a];
            result[a] = result[b];
            result[b] = temp;
        }
        return result;
    }
}
