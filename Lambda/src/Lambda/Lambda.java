package Lambda;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Lambda {
    public static void main(String[] args) {

       // переменная, захваченная лямбда-выражением должна быть финальной или "Эффективно финальной(не изиеняться после
        //захвата лямбла-выражением
        String name = "Mark";//name эффективно финальная переменная.
//        name = "Petya";//ошибка компиляции (переменная перестала быть эффективно финальной)
        Predicate<String> stringPredicate = (x->x.equals(name));
//        name= "Misha"; //ошибка компиляции (переменная перестала быть эффективно финальной)
        Function<Integer,String> function = Object::toString;
        int b = 123;
        String a = function.apply(b);
        System.out.println(a);













    }
}
