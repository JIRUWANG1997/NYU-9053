package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

/***
 * 为何使用Lambda？不用声明一个完整的函数
 * 分别使用lambda和传参，对Arrays进行几种不同操作
 * 注意，一定要是sort(),或removeIf()，这种明确是接口的，才能使用。
 */
public class SortingExample implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.length() - o2.length();
    }

    public class IsDean implements Predicate<String>{
        @Override
        public boolean test(String t) {
            return (t == "Dean");
        }
    }

    public static void main(String[] args) {
        SortingExample a = new SortingExample();
        String[] stringArray = {"Dean", "Mariaaaaaaaaaaaaaa", "Bobbbbb"};
        /***
         * sort默认方法，按首字母排序
         */
        System.out.println(Arrays.toString(stringArray));
        Arrays.sort(stringArray);
        System.out.println(Arrays.toString(stringArray));


        /***
         * override compare类，按长度排序，并传入sort()
         */
        Arrays.sort(stringArray,new SortingExample());
        System.out.println(Arrays.toString(stringArray));


        /***
         * 使用lambda,反向按长度排序
         */
        Arrays.sort(stringArray, (first,second) -> -(first.length()-second.length()));
        ArrayList<String> al = new ArrayList<String>();
        for(String s : stringArray){
            al.add(s);
        }
        al.sort((s1,s2)-> s1.compareTo(s2));//此处无传参，没有把SortingExample传进来，则还按首字母排序
        System.out.println(al);//Arraylist可直接打印

        al.removeIf(a.new IsDean());
        al.removeIf((s)->(s == "Dean"));//可加任何表达式，如s.length() > 4
    }
}
