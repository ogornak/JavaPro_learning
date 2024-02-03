package ru.stepup.task2;

import java.util.*;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        List<Integer> list = List.of(5, 2, 10, 9, 4, 3, 10, 1, 13);
        List<Employee> employees = List.of(
                new Employee("Сергей", 33, "Инженер"),
                new Employee("Василий", 37, "Инженер"),
                new Employee("Ольга", 28, "Бухгалтер"),
                new Employee("Иван", 23, "Инженер"),
                new Employee("София", 42, "HR"),
                new Employee("Артем", 45, "Инженер"),
                new Employee("Григорий", 27, "Бухгалтер"),
                new Employee("Яна", 41, "HR")
        );
        List<String> words = List.of("язык", "программирования", "формальный", "предназначенный", "для",
                "записи", "компьютерных", "программ", "азу");
        String str = "слов нижнем имеется строка слов набором слов регистре нижнем регистре";

        //Реализуйте удаление из листа всех дубликатов
        System.out.println(list.stream().distinct().collect(Collectors.toList()));

        //Найдите в списке целых чисел 3-е наибольшее число (пример: 5 2 10 9 4 3 10 1 13 => 10)
        System.out.println(list.stream().sorted((o1, o2) -> Integer.compare(o2, o1)).skip(2).limit(3).findFirst()
                .orElse(0));

        //Найдите в списке целых чисел 3-е наибольшее «уникальное» число (пример: 5 2 10 9 4 3 10 1 13 => 9,
        // в отличие от прошлой задачи здесь разные 10 считает за одно число)
        System.out.println(list.stream().distinct().sorted((o1, o2) -> Integer.compare(o2, o1)).skip(2).findFirst()
                .orElse(0));

        //Имеется список объектов типа Сотрудник (имя, возраст, должность), необходимо получить список имен 3
        // самых старших сотрудников с должностью «Инженер», в порядке убывания возраста
        System.out.println(employees.stream().filter(x -> x.getPost().equals("Инженер"))
                .sorted(((o1, o2) -> Integer.compare(o2.getAge(), o1.getAge())))
                .limit(3).map(Employee::getName).collect(Collectors.toList()));

        //Имеется список объектов типа Сотрудник (имя, возраст, должность), посчитайте средний возраст сотрудников
        // с должностью «Инженер»
        System.out.println(employees.stream().filter(x -> x.getPost().equals("Инженер"))
                .mapToInt(Employee::getAge).average().orElse(0));

        //Найдите в списке слов самое длинное
        System.out.println(words.stream().max(Comparator.comparing(String::length)).orElse(null));

        //Имеется строка с набором слов в нижнем регистре, разделенных пробелом. Постройте хеш-мапы, в которой будут
        // хранится пары: слово - сколько раз оно встречается во входной строке
        System.out.println(Arrays.stream(str.split(" "))
                .collect(Collectors.groupingBy(x -> x, Collectors.counting())));

        //Отпечатайте в консоль строки из списка в порядке увеличения длины слова, если слова имеют одинаковую длины,
        // то должен быть сохранен алфавитный порядок
        System.out.println(words.stream().sorted(Comparator.comparing(String::length).thenComparing(String::compareTo))
                .collect(Collectors.toList()));

        //Имеется массив строк, в каждой из которых лежит набор из 5 строк, разделенных пробелом, найдите среди всех
        // слов самое длинное, если таких слов несколько, получите любое из них
        String[] mass = new String[]{"массив строк каждой которых лежит", "программировани строк разделенных пробелом найдите",
                "предназначенный набор массив строк каждой"};
        System.out.println(Arrays.stream(Arrays.stream(mass).collect(Collectors.joining(" "))
                .split(" ")).max(Comparator.comparing(String::length)).orElse(null));
    }
}