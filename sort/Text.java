package www.sort;

public class Text {
    public static void main(String[] args) {
        int[] data = SortHelper.generateArray(10000,100000);

//        bubbleSort(data);
        insertsort(data);
        SortHelper.printArray(data);
    }

//    public static void bubbleSort(int[] array) {
//        long start = System.currentTimeMillis();
//        int n = array.length;
//        if (n <= 1) {
//            return;
//        } else {
//            //控制排序的次数
//            //一次冒泡只能确保一个元素到达最终位置
//            for (int i = 0; i < n; i++) {
//                boolean flag = false;
//                for (int j = 0; j < n - i - 1; j++) {
//                    if (array[j] > array[j+1]) {
//                        flag = true;
//                        int temp = array[j];
//                        array[j] = array[j+1];
//                        array[j+1] = temp;
//                    }
//                }
//                if(!flag) {
//                    System.out.println("当前遍历到第"+i+"数据已经有序");
//                    break;
//                }
//            }
//        }
//        long end = System.currentTimeMillis();
//        System.out.println("冒泡算法总耗时为："+(end-start));
//    }


    public static void insertsort(int[] array) {
        long start = System.currentTimeMillis();
        int n = array.length;
        if(n <= 1) {
            return;
        }else {
            //待排序
            for(int i = 1; i < n; i++) {
                //待排序集合的第一个元素
                int value = array[i];
                //已排序集合的最后一个元素
                int j = i-1;
                //找到要插入的位置
                for(; j >= 0; j--) {
                    if(array[j] > value) {
                        array[j+1] = array[j];
                    }else{
                        break;
                    }
                }
                //找到要插入的位置
                array[j+1] = value;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("冒泡算法总耗时为："+(end-start));
    }
}

