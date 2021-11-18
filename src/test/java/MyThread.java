import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: tianjl
 * @Date: 2021/3/31 17:58
 * @Eamil: 2695062879@qq.com
 */
@Data
public class MyThread{

    private String name;

    public static void main(String[] args) {
        Date date=new Date();
        System.out.println(date);
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(new (dateFormat.format(date)));
    }
}
