//Java数组章节练习题
public class ArrayUtils{
   

    //1.计算数组中最大值
    public static int arrayMaxElement(int[] data){
        int max = data[0];
        for(int i = 1; i < data.length; i++){
            if(data[i] > max) {
                max = data[i];
            }
        }
        System.out.println(max);
        return -1;
    }
    
    //2.计算数组中最小值
    public static int arrayMinElement(int[] data){
        int min = data[0];
        for(int i = 1; i < data.length; i++) {
            if(data[i] < min) {
                min = data[i];
            }
        }
        System.out.println(min);
        return -1;
    }
    
    
    //3.计算数组值之和
    public static int arrayElementSum(int[] data){
        int count = 0;
        for(int i = 0; i < data.length; i++) {
            count += data[i];
        }
        System.out.println(count);
        return -1;
    }
    
    //4.数组拼接
    // A {1,3,5,7,9} B {2,4,6,8,10} -> {1,3,5,7,9,2,4,6,8,10}
        public static int[] arrayJoin(int[] a, int[] b){
        int[] c = new int[a.length+b.length];
      

        for(int i = 0; i < a.length; i++){
            c[i] = a[i];
        }
        for(int i = 0; i < b.length; i++){
            c[i+a.length] = b[i];
        }
        // int i = 0;
        // for(int j = 0; i < (a.length+b.length); i++){
        //     if(i < a.length){
        //         c[i] = a[i];
        //     }
        //     else{
        //         c[i] = b[j];
        //         j++;
        //     }
        // }
        printArray(c);
        return null;
    }  

    //5.数组截取
    //[start, end)
    // A {1,3,5,7,9} ->(A,1,3) > {3,5}
    public static int[] arraySub(int[] data, int start , int end){
        int[] c = new int[end-start];
        for(int i = 0; i < (end - start); i++) {
            c[i] = data[start+i];
        }
        printArray(c);
        return null;
    }
    
    //6.数组打印
    public static void printArray(int[] data){
        for(int i = 0; i < data.length; i++) {
            System.out.println(data[i]+"\t");
        }
    }
    
    //7.数组反转
    // 比如：[1,2,3,4] => [4,3,2,1]
    public static void printReversal(int[] data){
        // int[] data1 = new int[data.length]
        int str = 0;
        int end = (data.length-1);
        while(str < end){
            int temp = 0;
            temp = data[str];
            data[str] = data[end];
            data[end] = temp;
            str++;
            end--;
        }
        printArray(data);
    }
    
    public static void main(String[] args){
        int[] a = new int[] {1,2,3,4,5};
        int[] b = new int[] {6,7,8,9,0};
        arrayMaxElement(a);
        arrayMinElement(a);
        arrayElementSum(a);
        arrayJoin(a,b);
        arraySub(a,1,3);
        printArray(a);
        printReversal(a);      
    }
}