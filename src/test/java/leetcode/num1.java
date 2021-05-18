package leetcode;


/**
 * @Author: tianjl
 * @Date: 2021/4/10 14:16
 * @Eamil: 2695062879@qq.com
 */
public class num1 {

    public static void main(String[] args) {
        int[] test = new int[]{1,1,2};
        int m=0;
        int z=1;
        while (z<test.length){
            if (test[m]==test[z]){
                z++;
            }else{
                test[++m]=test[z];
            }
        }
        System.out.println(m);
    }
}
