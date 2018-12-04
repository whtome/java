public class Text{
    public static void main(String[] args){

        System.out.println(sum(20));
    }
//         int i = 1;
//         int j = 1;
//         for(i = 1; i <= 9; i++){
//             for(j = 1; j<=i; j++){
//                 System.out.print(i+"*"+j+"="+i*j+"\t");
//             }
//             System.out.println("\t");
//         } 
    public static long sum(int num){
        if(num == 1){
            return 1L;
        }
        else{
            return num*sum(num-1);
        }
    }
}