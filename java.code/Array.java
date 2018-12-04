public class Array{
    public static void main(String[] args){
        int [] dataA = new int [] {1,2,3,4,5,6,7,8,9};
        int [] dataB = new int [] {11,22,33,44,55,66,77,88,99};
        System.arraycopy(dataB,4,dataA,1,3);
        printArray(dataA);
    }

    // public static void main(String[] args){
    //     int [] data = init();
    //     printArray(data);
    //     bigger(data);
    // }
    public static void printArray(int [] temp){
        for(int i = 0; i < temp.length; i++){
            System.out.println(temp[i]);
        }
    }
    // public static int[] init(){
    //     return new int []{1,2,3,4,5};
    // }
    // public static void bigger(int [] arr){
    //     for(int i = 0; i < arr.length; i++){
    //         arr[i] *= 5;
    //         System.out.println(arr[i]);
    //     }
    // }


        // int [][] data = new int[][] {
        //     {1,2,3},{4,5},{6,7,8,9}
        // };
        // for(int x = 0; x < data.length; x++){
        //     for(int y = 0; y < data[x].length; y++){
        //         System.out.println("data["+x+"]["+y+"]="+data[x][y]+".");
        //     }
        //     System.out.println();
        // }
        

        // int [] x = {1,2,3,4,5,6,7,8,9};
        // System.out.println(x.length);
        // for(int i = 0; i < x.length; i++){
        //     System.out.println(x[i]);
        // }
        // System.out.println(new int []{9,8,7,6,5,4,3,2,1,0});


        // int [] x = null;
        // int [] temp = null;
        // x = new int [3];
        // System.out.println(x.length);
        // x[0] = 10;
        // x[1] = 20;
        // x[2] = 30;
        // for(int i = 0; i < 3; i++){
        //     System.out.println(x[i]);
        // }
        // temp = x;
        // temp[0] = 50;
        // System.out.println(x[0]);    } 
    // }
}