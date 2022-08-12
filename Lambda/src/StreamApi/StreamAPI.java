package StreamApi;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamAPI {
    public static void main(String[] args) {
        List<Artist> allArtists = new ArrayList<>();
        allArtists.add(new Artist("London","John"));
        allArtists.add(new Artist("Moscow","Misha"));
        allArtists.add(new Artist("Washington","Donald"));

        //Пример внешнего итерирования.
        /*int count = 0;
        Iterator<Artist> iterator = allArtists.iterator();
        while (iterator.hasNext()) {
            Artist artist = iterator.next();
            if (artist.isFrom("London")) {
                count++;
            }
        }
        System.out.println(count);*/
        //Пример внутреннего итерирования
     /* List<Artist> artistsFromLondon =  allArtists.stream()
              .filter(x->x.isFrom("London")).collect(Collectors.toList());
        System.out.println(artistsFromLondon);*/

        // рандомное заполнение массива с помощью Stream
   /*     int[ ] ints;
        Integer[ ] integers;

        ints = new Random(1).ints(10, 1,5)
                .toArray();
        System.out.println(Arrays.toString(ints));

        integers = new Random(1).ints(1000000, 0, 1000)
                .boxed().toArray(Integer[]::new);
       int[] a =  IntStream.generate(()-> new Random().nextInt()).limit(10).toArray();
        System.out.println(Arrays.toString(a));*/

        // TODO: 13.08.2022 дочитать   Stream документацию до конца (во вкладке в данном проекте, продолжить работу с лямбдами.

    }

}


