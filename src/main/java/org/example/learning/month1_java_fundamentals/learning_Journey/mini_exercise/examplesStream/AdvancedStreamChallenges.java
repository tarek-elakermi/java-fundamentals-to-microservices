package org.example.learning.month1_java_fundamentals.learning_Journey.mini_exercise.examplesStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.learning.month1_java_fundamentals.learning_Journey.mini_exercise.datamocking.MockData;
import org.example.learning.month1_java_fundamentals.learning_Journey.mini_exercise.entities.Car;
import org.example.learning.month1_java_fundamentals.learning_Journey.mini_exercise.entities.Person;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AdvancedStreamChallenges {

    private <T> void toJsonMapMap(Map<String, Map<Integer, List<T>>> str) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(str);
        System.out.println(json);
    }

    private <T> void toJson1(Map<T, List<T>> str) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(str);
        System.out.println(json);
    }

    private <T> void toJson(Map<String, List<T>> str) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(str);
        System.out.println(json);
    }

    private <T> void toStringMap(Map<String, List<T>> str) {
        str.forEach((k, v) -> {
            System.out.println(k + ":");
            v.forEach(car -> System.out.println("  " + car));
        });
    }

    /**
     * CHALLENGE 1: Find the top N most expensive cars for each make
     * Operations needed: groupingBy, sorting, limiting, flatMap
     */
    @Test
    public void topNExpensiveCarsPerMake() throws Exception {
        List<Car> cars = MockData.getCars();
        int topN = 3;
        // TODO: Implement solution
        Map<String, List<Car>> collect = cars.stream()
                .collect(Collectors.groupingBy(Car::getMake,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparing(Car::getPrice, Comparator.reverseOrder()))
                                        .limit(topN)
                                        .toList()
                        )));
        toJson(collect);
    }

    /**
     * CHALLENGE 2: Find people with the most common email domains
     * Operations needed: mapping, groupingBy, counting, sorting
     */
    @Test
    public void peopleByEmailDomainPopularity() throws Exception {
        List<Person> people = MockData.getPeople();
        // TODO: Implement solution

        List<Person> people1 = people.stream()
                .collect(Collectors.groupingBy(p -> p.getEmail().split("@")[1]))
                .entrySet().stream()
                .max(Comparator.comparingInt(e -> e.getValue().size()))
                .map(Map.Entry::getValue)
                .orElse(List.of());
        people1.forEach(System.out::println);


//        Map<String, Long> collect = people.stream()
//                        .map(p -> p.getEmail().split("@")[1])
//                                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
//        System.out.println(collect);
//        long l = collect.values().stream()
//                .mapToLong(Long::longValue)
//                .max()
//                .orElse(0);
//        System.out.println(l);
//        Set<String> collect1 = collect.entrySet().stream()
//                .filter(e -> e.getValue() == l)
//                .map(Map.Entry::getKey)
//                .collect(Collectors.toSet());
//        System.out.println(collect1);
//
//        List<Person> list = people.stream()
//                .filter(p -> collect1.contains(p.getEmail().split("@")[1]))
//                .toList();
//        System.out.println(list);


    }

    /**
     * CHALLENGE 3: Calculate price statistics by car make and year
     * Operations needed: groupingBy, nested groupingBy, summarizingDouble
     */
    @Test
    public void carPriceStatisticsByMakeAndYear() throws Exception {
        List<Car> cars = MockData.getCars();
        // TODO: Implement solution
        //Map<String, Map<Integer, List<Car>>> collect =
        Map<Integer, Map<String, DoubleSummaryStatistics>> collect = cars.stream()
//                .collect(Collectors.groupingBy(Car::getMake, Collectors.groupingBy(Car::getYear)))
                .collect(Collectors.groupingBy(Car::getYear,
                        Collectors.groupingBy(Car::getMake, Collectors.summarizingDouble(Car::getPrice))));

        collect.entrySet().forEach(System.out::println);

    }

    /**
     * CHALLENGE 4: Find people with similar names (anagrams)
     * Operations needed: groupingBy, mapping, filtering, collectingAndThen
     */
    @Test
    public void findPeopleWithSimilarNames() throws Exception {
        List<Person> people = MockData.getPeople();
        // TODO: Implement solution
        List<String> anagrams = List.of("listen", "evil", "silent", "vile", "triangle", "hello", "integral", "live", "giana", "bella");

        // test on a list of strings
//        Map<String, List<String>> collect = anagrams.stream()
//                .collect(Collectors.groupingBy(
//                        p -> p
//                                .toLowerCase()
//                                .chars()
//                                .sorted()
//                                .collect(StringBuilder::new,
//                                        StringBuilder::appendCodePoint,
//                                        StringBuilder::append)
//                                .toString()
//                )).entrySet().stream()
//                .filter(sEntry -> sEntry.getValue().size() > 1)
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//        toJson(collect);

        // no for the people
        Map<String, List<Person>> collect1 = people.stream()
                .collect(Collectors.groupingBy(
                        person -> person.getFirstName().toLowerCase().chars().sorted()
                                .collect(StringBuilder::new,
                                        StringBuilder::appendCodePoint,
                                        StringBuilder::append).toString()
                )).entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        toJson(collect1);

        // lets try to group them by count and anagrams sorted
//        Map<Long, Map<String, List<Person>>> collect = people.stream()
//                .collect(Collectors.groupingBy(
//                        person -> person.getFirstName().toLowerCase().chars().sorted()
//                                .collect(StringBuilder::new,
//                                        StringBuilder::appendCodePoint,
//                                        StringBuilder::append).toString()
//                )).entrySet().stream()
//                .filter(entry -> entry.getValue().size() > 1)
//                .collect(Collectors.groupingBy(
//                        e -> (long) e.getValue().size(),
//                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)
//                ));
//
//        collect.entrySet().forEach(
//                entry -> {
//                    System.out.println(entry.getKey());
//                    try {
//                        toJson(entry.getValue());
//                    } catch (JsonProcessingException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//        );


    }

    /**
     * CHALLENGE 5: Calculate car price distribution by decade
     * Operations needed: groupingBy, mapping, collectingAndThen
     */
    @Test
    public void carPriceDistributionByDecade() throws Exception {
        List<Car> cars = MockData.getCars();
        // TODO: Implement solution

//        List<Integer> list = cars.stream()
//                .limit(20)
//                .map(car -> (car.getYear() / 10) * 10)
//                .sorted()
//                .distinct()
//                .toList();
//        System.out.println(list);

        /*************/

//        Map<Integer, List<Car>> collect1 = cars.stream()
//                .limit(20)
//                .collect(Collectors.groupingBy(car -> (car.getYear() / 10) * 10, TreeMap::new,Collectors.toList()));
//        collect1.entrySet().forEach(entry -> {
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue().stream().map(Car::getMake).toList());
//            System.out.println(entry.getValue().stream().map(Car::getPrice).toList());
//            System.out.println(entry.getValue().stream().collect(Collectors.summarizingDouble(Car::getPrice)));
//        });
//
//        // get cars by decade
        TreeMap<Integer, DoubleSummaryStatistics> collect = cars.stream()
                .collect(Collectors.groupingBy(
                        car -> (car.getYear() / 10) * 10,
                        TreeMap::new,
                        Collectors.collectingAndThen(
                                Collectors.summarizingDouble(Car::getPrice),
                                stats -> stats

                        )
                ));
//
//
        collect.entrySet().forEach(entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        });

        System.out.println("/********************************************/");

        Map<Integer, DoubleSummaryStatistics> priceDistributionByDecade =
                cars.stream()
                        .collect(Collectors.groupingBy(
                                car -> (car.getYear() / 10) * 10,             // decade as key
                                TreeMap::new,
                                Collectors.mapping(
                                        Car::getPrice,                            // extract price
                                        Collectors.collectingAndThen(
                                                Collectors.summarizingDouble(Double::doubleValue),  // statistics
                                                stats -> stats                             // identity, already a DoubleSummaryStatistics
                                        )
                                )
                        ));
//        priceDistributionByDecade.entrySet().forEach(entry -> {
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
//        });
    }

    /**
     * CHALLENGE 6: Find the most valuable car brand (average price per make)
     * Operations needed: groupingBy, averagingDouble, sorting, limiting
     */
    @Test
    public void mostValuableCarBrands() throws Exception {
        List<Car> cars = MockData.getCars();
        // TODO: Implement solution
        Map.Entry<String, Double> mostValuableCar = cars.stream()
                .collect(
                        Collectors.groupingBy(
                                Car::getMake,
                                Collectors.averagingDouble(Car::getPrice)
                        )
                ).entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(1)
                .findFirst().orElse(null);
        System.out.println(mostValuableCar);

    }

    /**
     * CHALLENGE 7: Find people who share the same birth year (extracted from email or other logic)
     * Operations needed: mapping, groupingBy, filtering, flatMap
     */
    @Test
    public void findPeopleWithSameBirthYear() throws Exception {
        List<Person> people = MockData.getPeople();
        // TODO: Implement solution - extract birth year from email or create synthetic logic
    }

    /**
     * CHALLENGE 8: Calculate the price trend for cars over years
     * Operations needed: groupingBy, averagingDouble, sorting, reducing
     */
    @Test
    public void carPriceTrendAnalysis() throws Exception {
        List<Car> cars = MockData.getCars();
        // TODO: Implement solution

        Map<Integer, Double> avgPricePerYearSorted1 =
                cars.stream()
                        .limit(10)
                        .collect(Collectors.groupingBy(
                                Car::getYear,
                                Collectors.averagingDouble(Car::getPrice)
                        ))
                        .entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByKey())
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (a, b) -> a,
                                LinkedHashMap::new
                        ));

        avgPricePerYearSorted1.forEach((k, v) -> System.out.println(k + "==" + v));
        System.out.println("**************************************************************");

        TreeMap<Integer, Double> collect = cars.stream()
                .collect(
                        Collectors.groupingBy(
                                Car::getYear,
                                TreeMap::new,
                                Collectors.averagingDouble(Car::getPrice)
                        )
                );
        ArrayList<String> start = collect.entrySet().stream()
                .reduce(
                        new ArrayList<String>(),
                        (list, current) -> {
                            if (!list.isEmpty()) {
                                // normally the output will be "YYYY -> YYYY : String"
                                String lastString = list.get(list.size() - 1);
                                double prevAvg = 0;
                                int prevYear;
                                String[] parts = lastString.split("-> | : ");
                                if (lastString.contains("START")) {
                                    prevYear = Integer.parseInt(parts[0]);
                                    prevAvg = collect.get(prevYear);
                                } else {
                                    prevYear = Integer.parseInt(parts[1]);
                                    prevAvg = collect.get(prevYear);
                                }

                                double diff = current.getValue() - prevAvg;
                                list.add(prevYear + " -> " + current.getKey() + " : " + diff);

                            } else {
                                list.add(current.getKey() + " : START");
                            }

                            return list;
                        },
                        (l1, l2) -> l1
                );

        start.forEach(System.out::println);

        /************/

    }


    /**
     * CHALLENGE 9: Find the most popular car color for each make
     * Operations needed: groupingBy, nested groupingBy, counting, maxBy
     */
    @Test
    public void mostPopularColorPerMake() throws Exception {
        List<Car> cars = MockData.getCars();
        // TODO: Implement solution
        Map<String, Map.Entry<String, Long>> collect = cars.stream()
                .collect(Collectors.groupingBy(
                        Car::getMake,
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(
                                        Car::getColor,
                                        Collectors.counting()
                                ),
                                finisher -> finisher.entrySet()
                                        .stream()
                                        .collect(Collectors.maxBy(Map.Entry.comparingByValue()))
                                        .orElse(null)


                        )
                ));

        collect.entrySet().forEach(System.out::println);

    }

    /**
     * CHALLENGE 10: Calculate age distribution by gender
     * Operations needed: groupingBy, mapping, summarizingInt
     */
    @Test
    public void ageDistributionByGender() throws Exception {
        List<Person> people = MockData.getPeople();
        // TODO: Implement solution
        Map<String, IntSummaryStatistics> collect = people.stream()
                .limit(10)
                .collect(
                        Collectors.groupingBy(
                                Person::getGender,
                                Collectors.mapping(
                                        Person::getAge,
                                        Collectors.summarizingInt(Integer::intValue)
                                )
                        )
                );
        collect.entrySet().forEach(System.out::println);
    }

    /**
     * CHALLENGE 11: Find cars that are priced above average for their make
     * Operations needed: groupingBy, averagingDouble, filtering, flatMap
     */
    @Test
    public void carsPricedAboveAverageForTheirMake() throws Exception {
        List<Car> cars = MockData.getCars();
        // TODO: Implement solution
        Map<String, List<Car>> collect = cars.stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(                      // the downstream
                                        Car::getMake,
                                        Collectors.averagingDouble(Car::getPrice)
                                ),
                                avgByMake -> avgByMake.entrySet()           // the finisher
                                        .stream()
                                        .collect(
                                                Collectors.toMap(
                                                        Map.Entry::getKey,
                                                        e -> cars
                                                                .stream()
                                                                .filter(car -> car.getMake().equals(e.getKey())
                                                                        && car.getPrice() > e.getValue())
                                                                .toList()

                                                )
                                        )
                        )
                );
        toJson(collect);
    }

    /**
     * CHALLENGE 12: Group people by age range and count by gender
     * Operations needed: groupingBy, partitioningBy, counting
     */
    @Test
    public void peopleByAgeRangeAndGender() throws Exception {
        List<Person> people = MockData.getPeople();
        // TODO: Implement solution
//        TreeMap<Integer, Map<String, Long>> collect1 = people.stream()
//                //.limit(30)
//                .collect(
//                        Collectors.groupingBy(
//                                person -> (person.getAge() / 10) * 10,
//                                TreeMap::new,
//                                Collectors.groupingBy(
//                                        Person::getGender,
//                                        Collectors.counting()
//                                )
//                        )
//                );
//
//        //collect.entrySet().forEach(System.out::println);
//        collect1.forEach((age, map) -> {
//            System.out.println(age + "-" + (age + 9) + " => " + map);
//        });

        System.out.println("**************************");

        // using the partitioningBy
        TreeMap<Integer, Map<String, Long>> collect = people.stream()
                .collect(
                        Collectors.groupingBy(
                                p -> (p.getAge() / 10) * 10,
                                TreeMap::new,
                                Collectors.collectingAndThen(
                                        Collectors.partitioningBy(
                                                p -> p.getGender().equals("Male"),
                                                Collectors.counting()
                                        ),
                                        finisher -> Map.of(
                                                "Male", finisher.get(true),
                                                "Female", finisher.get(false)
                                        )
                                )
                        )
                );


        collect.forEach((age, map) -> {
            System.out.println(age + "-" + (age + 9) + " => " + map);
        });
    }

    /**
     * CHALLENGE 13: Find the year with the most expensive cars on average
     * Operations needed: groupingBy, averagingDouble, maxBy, collectingAndThen
     */
    @Test
    public void yearWithMostExpensiveCars() throws Exception {
        List<Car> cars = MockData.getCars();
        // TODO: Implement solution
        TreeMap<Integer, Double> collect1 = cars.stream()
                .collect(
                        Collectors.groupingBy(
                                Car::getYear,
                                TreeMap::new,
                                Collectors.averagingDouble(Car::getPrice)
                        )
                );
        collect1.forEach((y, avgPrice) -> {
            System.out.println(y + " The average cars prices for this year is: => " + avgPrice);
        });

        System.out.println("****************************************");
        Map.Entry<Integer, Double> collect = cars.stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(
                                        Car::getYear,
                                        TreeMap::new,
                                        Collectors.averagingDouble(Car::getPrice)
                                ),
                                finisher -> finisher.entrySet()
                                        .stream()
                                        //.collect(Collectors.maxBy(Map.Entry.comparingByValue()))
                                        .max(Map.Entry.comparingByValue()) // using max in place of maxBy
                                        .orElse(null)

                        )
                );

        System.out.println("the year with the most expensive cars on average is: " + collect.getKey() + " with an Average :" + collect.getValue());
    }

    /**
     * CHALLENGE 14: Calculate the correlation between car year and price
     * Operations needed: mapping, collecting, statistical calculations
     */
    @Test
    public void yearPriceCorrelation() throws Exception {
        List<Car> cars = MockData.getCars();
        // TODO: Implement solution

        /**
         * CHALLENGE 14: Calculate the correlation between car year and price
         *
         * Correlation:
         * 1. Correlation measures the relationship between two variables.
         * 2. It indicates how one variable changes when the other changes.
         * 3. Values range from –1 to +1, where:
         *      +1 = perfect positive correlation (both increase together)
         *      –1 = perfect negative correlation (one increases while the other decreases)
         *       0 = no correlation (variables are independent)
         * 4. In practice, it shows whether variables increase, decrease, or remain independent of each other.
         *
         * Formulas used:
         * 1. Mean (average) of X (car year) and Y (car price):
         *      meanX = sum(X_i) / n
         *      meanY = sum(Y_i) / n
         *
         * 2. Covariance:
         *      cov(X,Y) = sum((X_i - meanX) * (Y_i - meanY)) / n
         *
         * 3. Standard Deviation:
         *      stdDev(X) = sqrt(sum((X_i - meanX)^2) / n)
         *      stdDev(Y) = sqrt(sum((Y_i - meanY)^2) / n)
         *
         * 4. Correlation Coefficient:
         *      r = cov(X,Y) / (stdDev(X) * stdDev(Y))
         *
         * Usage:
         * - Here X = car year, Y = car price
         * - The correlation coefficient r tells you if newer cars are more expensive (positive r),
         *   cheaper (negative r), or unrelated (r ≈ 0).
         */


        // the complicated way hhh needs to understand what correlation means
        Double collect = cars.stream()
                //.limit(10)
                .map(c -> new Double[]{Double.valueOf(c.getYear()), c.getPrice()})
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    int n = list.size();

                                    // Averages
                                    double avgYear = list.stream()
                                            .mapToDouble(arr -> arr[0])
                                            .average().orElse(0);

                                    double avgPrice = list.stream()
                                            .mapToDouble(arr -> arr[1])
                                            .average().orElse(0);

                                    // Covariance
                                    double covariance = list.stream()
                                            .mapToDouble(arr ->
                                                    (arr[0] - avgYear) * (arr[1] - avgPrice)
                                            ).sum() / n;

                                    // Standard deviations
                                    double stdYear = Math.sqrt(
                                            list.stream()
                                                    .mapToDouble(
                                                            arr ->
                                                                    Math.pow(arr[0] - avgYear, 2))
                                                    .sum() / n
                                    );

                                    double stdPrice = Math.sqrt(
                                            list.stream()
                                                    .mapToDouble(
                                                            arr ->
                                                                    Math.pow(arr[1] - avgPrice, 2))
                                                    .sum() / n
                                    );

                                    // Correlation
                                    return covariance / (stdYear * stdPrice);
                                }
                        )
                );

        String strength;
        if (collect >= 0.7) {
            strength = "Very strong";
        } else if (collect >= 0.5) {
            strength = "Strong";
        } else if (collect >= 0.3) {
            strength = "Moderate";
        } else if (collect >= 0.1) {
            strength = "Weak";
        } else {
            strength = "Very weak";
        }
        switch (strength) {
            case "Very strong":
                System.out.println("result is: " + collect + " Correlation is very strong!");
                break;
            case "Strong":
                System.out.println("result is: " + collect + " Correlation is strong!");
                break;
            case "Moderate":
                System.out.println("result is: " + collect + " Correlation is moderate.");
                break;
            case "Weak":
                System.out.println("result is: " + collect + " Correlation is weak.");
                break;
            case "Very weak":
                System.out.println("result is: " + collect + " Correlation is very weak.");
                break;
        }


//        TreeMap<Integer, DoubleSummaryStatistics> collect = cars.stream()
//                .limit(10)
//                .collect(
//                        Collectors.groupingBy(
//                                Car::getYear,
//                                TreeMap::new,
//                                Collectors.summarizingDouble(Car::getPrice)
//                        )
//                );
//        collect.forEach((year, doubleSummary) -> {
//            System.out.println(year + " <===> " + doubleSummary);
//        });
    }

    /**
     * CHALLENGE 15: Find people with unique first names (first names that appear only once)
     * Operations needed: groupingBy, counting, filtering, mapping
     */
    @Test
    public void peopleWithUniqueNames() throws Exception {
        List<Person> people = MockData.getPeople();
        // TODO: Implement solution

        // first solution
        Map<String, List<Person>> collect = people.stream()
                //.limit(300)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(
                                        Person::getFirstName
                                ),
                                finisher -> finisher.entrySet().stream()
                                        .filter(entry -> entry.getValue().size() == 1)
                                        .collect(
                                                Collectors.toMap(
                                                        Map.Entry::getKey,
                                                        Map.Entry::getValue,
                                                        (a,b) -> a,
                                                        TreeMap::new
                                                )
                                        )
                        )
                );
        //collect.entrySet().forEach(System.out::println);
        System.out.println("***********************************");
        Set<Person> collect1 = people.stream()
                //.limit(300)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(
                                        Person::getFirstName
                                ),
                                finisher -> finisher.values().stream()
                                        .filter(list -> list.size() == 1)
                                        .flatMap(Collection::stream)
                                        .sorted(Comparator.comparing(Person::getId))
                                        .collect(Collectors.toCollection(LinkedHashSet::new))
                        )
                );
        //collect1.forEach(System.out::println);
    }

    /**
     * CHALLENGE 16: Calculate the cumulative price of cars by year
     * Operations needed: groupingBy, reducing, sorting
     */
    @Test
    public void cumulativePriceByYear() throws Exception {
        List<Car> cars = MockData.getCars();
        // TODO: Implement solution

        // first step grouping each year by the prices of cars it contains
//        Map<Integer, List<Double>> collect1 = cars.stream()
//                .limit(10)
//                .collect(
//                        Collectors.groupingBy(
//                                Car::getYear,
//                                TreeMap::new,
//                                Collectors.mapping(
//                                        Car::getPrice,
//                                        Collectors.toList()
//                                )
//                        )
//
//                );
//        collect1.entrySet().forEach(System.out::println);

        // we will use a TreeMap which will sort the key by itself so no need for sort in this solution

        TreeMap<Integer, Double> reduce1 = cars.stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(
                                        Car::getYear,
                                        Collectors.mapping(
                                                Car::getPrice,
                                                Collectors.toList()
                                        )
                                ),
                                finisher -> finisher
                                        .entrySet()
                                        .stream()
                                        .collect(
                                                Collectors.toMap(
                                                        Map.Entry::getKey,
                                                        e -> e.getValue()
                                                                .stream()
                                                                .mapToDouble(Double::doubleValue)
                                                                .sum(),
                                                        (a, b) -> a,
                                                        TreeMap::new
                                                )
                                        )
                        )
                )
                .entrySet()
                .stream()
                .reduce(
                        new TreeMap<Integer, Double>(),
                        (tree, cur) -> {
                            double cumulativePrice = cur.getValue();
                            if (!tree.isEmpty()) cumulativePrice += tree.lastEntry().getValue();

                            tree.put(cur.getKey(), cumulativePrice);
                            return tree;

                        },
                        (t1, t2) -> t1
                );

        reduce1.entrySet().forEach(e ->
                System.out.println(e.getKey() + " = " + String.format("%.2f", e.getValue()))
        );

        // solution by 2 streams one on top of the other result


        Map<Integer, Double> collect = cars.stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(
                                        Car::getYear,
                                        Collectors.mapping(
                                                Car::getPrice,
                                                Collectors.toList()
                                        )
                                ),
                                finisher -> finisher
                                        .entrySet()
                                        .stream()
                                        .collect(
                                                Collectors.toMap(
                                                        Map.Entry::getKey,
                                                        e -> e.getValue()
                                                                .stream()
                                                                .mapToDouble(Double::doubleValue)
                                                                .sum(),
                                                        (a,b) -> a,
                                                        TreeMap::new
                                                )
                                        )
                        )
                );
        TreeMap<Integer, Double> reduce = collect.entrySet()
                .stream()
                .reduce(
                        new TreeMap<Integer, Double>(),
                        (treeMap, current) -> {

                            double cumulativePrice = current.getValue();
                            if (treeMap.isEmpty()) {
                                treeMap.put(current.getKey(), current.getValue());
                            } else {
                                cumulativePrice += treeMap.lastEntry().getValue();
                            }
                            treeMap.put(current.getKey(), cumulativePrice);

                            return treeMap;
                        },
                        (t1, t2) -> t1
                );

        System.out.println("******************");
        DecimalFormat df = new DecimalFormat("#,###.00");
        reduce.entrySet().forEach(e ->
                System.out.println(e.getKey() + " = " + df.format(e.getValue()))
        );

    }

    /**
     * CHALLENGE 17: Find the make with the widest price range
     * Operations needed: groupingBy, summarizingDouble, collectingAndThen
     */
    @Test
    public void makeWithWidestPriceRange() throws Exception {
        List<Car> cars = MockData.getCars();
        // TODO: Implement solution

        Map<String, DoubleSummaryStatistics> collect1 = cars.stream()
                .limit(10)
                .collect(
                        Collectors.groupingBy(
                                Car::getMake,
                                Collectors.summarizingDouble(Car::getPrice)
                        )
                );
        //collect1.entrySet().forEach(System.out::println);
        System.out.println("*****************************");

        Map<String, Double> collect = cars.stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(
                                        Car::getMake,
                                        Collectors.summarizingDouble(Car::getPrice)
                                ),
                                range -> range
                                        .entrySet()
                                        .stream()
                                        .collect(Collectors.toMap(
                                                Map.Entry::getKey,
                                                e -> {
                                                    double widest = e.getValue().getMax() - e.getValue().getMin();
                                                    return widest;
                                                },
                                                (a,b) -> a > b ? a : b ,
                                                LinkedHashMap::new
                                        ))
                        )
                );

        //collect.entrySet().forEach(System.out::println);

        System.out.println("*****************************");

        Map.Entry<String, DoubleSummaryStatistics> collect2 = cars.stream()
                .collect(
                        Collectors.groupingBy(
                                Car::getMake,
                                Collectors.summarizingDouble(Car::getPrice)
                        )
                )
                .entrySet()
                .stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.maxBy(
                                        Comparator.comparing(
                                                entry ->
                                                        entry.getValue().getMax() - entry.getValue().getMin())
                                ),
                                e -> e.orElse(null)
                        )
                );

        System.out.println(collect2);

        // or
        Map<String, Double> collect3 = cars.stream()
                .collect(
                        Collectors.collectingAndThen(
                                // Step 1: group by make and compute stats (min, max)
                                Collectors.groupingBy(
                                        Car::getMake,
                                        Collectors.summarizingDouble(Car::getPrice)
                                ),

                                // Step 2: find the make with the widest range
                                statsMap -> {
                                    Map.Entry<String, DoubleSummaryStatistics> widest =
                                            statsMap.entrySet().stream()
                                                    .max(
                                                            Comparator.comparingDouble(
                                                                    e -> e.getValue().getMax() - e.getValue().getMin()
                                                            )
                                                    )
                                                    .orElse(null);

                                    if (widest == null) return Map.<String, Double>of();

                                    // Step 3: return Map<String, Double>
                                    double range = widest.getValue().getMax() - widest.getValue().getMin();
                                    return Map.of(widest.getKey(), range);
                                }
                        )
                );

        collect3.entrySet().forEach(System.out::println);


    }

    /**
     * CHALLENGE 18: Group cars by price quartiles
     * Operations needed: sorting, collecting, partitioningBy
     */
    @Test
    public void carsByPriceQuartiles() throws Exception {
        List<Car> cars = MockData.getCars();
        // TODO: Implement solution
        Map<String, List<Car>> quartiles =
                cars.stream()
                        .sorted(Comparator.comparing(Car::getPrice))
                        .collect(Collectors.collectingAndThen(
                                Collectors.toList(),
                                sorted -> {

                                    int size = sorted.size();
                                    int q1 = size / 4;
                                    int q2 = size / 2;
                                    int q3 = size * 3 / 4;

                                    // partition 1: Q1 vs remaining 75%
                                    Map<Boolean, List<Car>> part1 =
                                            sorted.stream().collect(Collectors.partitioningBy(
                                                    c -> sorted.indexOf(c) < q1
                                            ));

                                    List<Car> q1List = part1.get(true);
                                    List<Car> rest75 = part1.get(false);

                                    // partition 2: Q2 inside the remaining 75%
                                    Map<Boolean, List<Car>> part2 =
                                            rest75.stream().collect(Collectors.partitioningBy(
                                                    c -> sorted.indexOf(c) < q2
                                            ));

                                    List<Car> q2List = part2.get(true);
                                    List<Car> rest50 = part2.get(false);

                                    // partition 3: Q3 inside the remaining 50%
                                    Map<Boolean, List<Car>> part3 =
                                            rest50.stream().collect(Collectors.partitioningBy(
                                                    c -> sorted.indexOf(c) < q3
                                            ));

                                    List<Car> q3List = part3.get(true);
                                    List<Car> q4List = part3.get(false);

                                    Map<String, List<Car>> result = new LinkedHashMap<>();
                                    result.put("Q1", q1List);
                                    result.put("Q2", q2List);
                                    result.put("Q3", q3List);
                                    result.put("Q4", q4List);

                                    return result;
                                }
                        ));
        toJson(quartiles);
    }

    /**
     * CHALLENGE 19: Find people with email patterns (e.g., same username format)
     * Operations needed: mapping, groupingBy, pattern matching, filtering
     */
    @Test
    public void peopleWithSimilarEmailPatterns() throws Exception {
        List<Person> people = MockData.getPeople();


        // TODO: Implement solution
        Map<String, List<Person>> collect1 = people.stream()
                .limit(50)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                listStream -> {
                                    String patternLetter = "^[a-zA-Z]+$";
                                    String patternLetterDigit = "^[a-zA-Z]+\\d+$";

                                    Map<Boolean, List<Person>> collect = listStream
                                            .stream()
                                            .collect(
                                                    Collectors.partitioningBy(
                                                            p -> p.getEmail().split("@")[0].matches(patternLetter)
                                                    )
                                            );
                                    List<Person> part1 = collect.get(true);
                                    List<Person> part2 = collect.get(false);

                                    Map<String, List<Person>> result = new LinkedHashMap<>();
                                    result.put(patternLetter, part1);
                                    result.put(patternLetterDigit, part2);

                                    return result;
                                }
                        ));
        //collect1.entrySet().forEach(System.out::println);

        // or
        Map<String, List<Person>> collect = people.stream()
                //.limit(50)
                .collect(
                        Collectors.groupingBy(
                                p -> detectPattern(p.getEmail().split("@")[0])
                        )
                );

        collect.entrySet().forEach(System.out::println);
    }

    private String detectPattern(String username) {
        if (username.matches("^[a-zA-Z]+$"))
            return "letters_only";
        if (username.matches("^[a-zA-Z]+\\d+$"))
            return "letters_digits";
        if (username.matches("^[a-zA-Z]+\\.[a-zA-Z]+$"))
            return "letters.letters";
        if (username.matches("^[a-zA-Z]+_[a-zA-Z]+$"))
            return "letters_letters";
        if (username.matches("^\\d+[a-zA-Z]+$"))
            return "digits_letters";
        if (username.matches("^[a-zA-Z0-9]+$"))
            return "alphanumeric";
        return "other";
    }


    /**
     * CHALLENGE 20: Calculate the moving average of car prices over years
     * Operations needed: groupingBy, sorting, windowing, averagingDouble
     */
    @Test
    public void movingAverageCarPrices() throws Exception {
        List<Car> cars = MockData.getCars();
        // TODO: Implement solution
        TreeMap<Integer, Double> collect = cars.stream()
                //.limit(10)
                .collect(
                        Collectors.groupingBy(
                                Car::getYear,
                                TreeMap::new,
                                Collectors.averagingDouble(Car::getPrice)
                        )
                );

        //collect.entrySet().forEach(System.out::println);
        System.out.println("**********************");

       List<Map.Entry<Integer, Double>> entries = new ArrayList<>(collect.entrySet());
       int window = 3;
        Map<Integer, Double> collect1 = IntStream.range(0, entries.size() - window + 1)
                .boxed()
                .collect(
                        Collectors.toMap(
                                i -> entries.get(i + window - 1).getKey(),
                                i -> IntStream.range(i, i + window)
                                        .mapToDouble(j -> entries.get(j).getValue())
                                        .average()
                                        .orElse(0.0)
                        )
                );

        //collect1.entrySet().forEach(System.out::println);
        System.out.println("**********************");

        Map<Integer, Double> collect3 = cars.stream()
                //.limit(10)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(
                                        Car::getYear,
                                        TreeMap::new,
                                        Collectors.averagingDouble(Car::getPrice)
                                ),
                                movingWindow -> {
                                    int win = 3;
                                    List<Map.Entry<Integer, Double>> entries1 = new ArrayList<>(movingWindow.entrySet());
                                    Map<Integer, Double> collect2 = IntStream.range(0, entries1.size() - win + 1)
                                            .boxed()
                                            .collect(
                                                    Collectors.toMap(
                                                            i -> entries1.get(i + win - 1).getKey(),
                                                            i -> IntStream.range(i, i + win)
                                                                    .mapToDouble(j -> entries1.get(j).getValue())
                                                                    .average()
                                                                    .orElse(0.0),
                                                            (a,b) -> a,
                                                            TreeMap::new
                                                    )
                                            );

                                    return collect2;
                                }
                        )
                );

        collect3.entrySet().forEach(System.out::println);


    }

    /**
     * CHALLENGE 21: Find the most frequent car model for each make
     * Operations needed: groupingBy, nested groupingBy, counting, maxBy
     */
    @Test
    public void mostFrequentModelPerMake() throws Exception {
        List<Car> cars = MockData.getCars();
        // TODO: Implement solution
        Map<String, String> collect = cars.stream()
                //.limit(20)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(
                                        Car::getMake,
                                        Collectors.groupingBy(
                                                Car::getModel,
                                                Collectors.counting()
                                        )
                                ),
                                finisher ->
                                        finisher.entrySet()
                                                .stream()
                                                .collect(
                                                        Collectors.toMap(
                                                                Map.Entry::getKey,
                                                                e -> e.getValue()
                                                                        .entrySet()
                                                                        .stream()
                                                                        .max(Map.Entry.comparingByValue())
                                                                        .get()
                                                                        .getKey()

                                                        )
                                                )

                        )
                );
        collect.entrySet().forEach(System.out::println);
    }

    /**
     * CHALLENGE 22: Calculate the Gini coefficient of car prices (measure of inequality)
     * Operations needed: sorting, mapping, reducing, mathematical operations
     */
    @Test
    public void carPriceInequality() throws Exception {
        List<Car> cars = MockData.getCars();
        // TODO: Implement solution - advanced statistical challenge
        List<Double> sortedPrices = cars.stream()
                .map(Car::getPrice)
                .sorted()
                .toList();

        int n = sortedPrices.size();

        double weightedSum = IntStream.range(0, n)
                .mapToDouble(i -> sortedPrices.get(i) * (2*(i+1) - n - 1))
                .reduce(0.0, Double::sum);

        double sumPrices = sortedPrices.stream().mapToDouble(Double::doubleValue).sum();

//        double mean = sortedPrices.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        double gini = weightedSum / (n * sumPrices);
        System.out.println(gini);

        System.out.println("**********************");

        List<Double> list = cars.stream()
                .map(Car::getPrice)
                .sorted()
                .toList();
        int sizeList = list.size();
        double weightSum = IntStream.range(0, sizeList)
                        .mapToDouble(
                                i -> list.get(i) * (2 * (i +1) -n -1)
                        )
                                .reduce(0.0, Double::sum);
//        double holeAverage = list.stream()
//                        .mapToDouble(Double::doubleValue).average().orElse(0.0);
        double sum = list.stream()
                .mapToDouble(Double::doubleValue).sum();

        double giniFactor = weightSum / (sizeList * sum);
        System.out.println(giniFactor);

        // or another method
        
        List<Double> listSortedPricesForCars = cars.stream().map(Car::getPrice).sorted().toList();
        double sumOfAllPrices = listSortedPricesForCars.stream().mapToDouble(Double::doubleValue).sum();
        int size = listSortedPricesForCars.size();
        double weightedIndexesSum = IntStream.range(0, cars.size()).mapToDouble(i -> listSortedPricesForCars.get(i) * (2 * (i + 1) - n - 1)).sum();
        double result = weightedIndexesSum / (size * sumOfAllPrices);

        System.out.println("********************");
        System.out.println(result);
    }

    /**
     * CHALLENGE 23: Find people who could be family members (same last name, similar age)
     * Operations needed: groupingBy, filtering, collectingAndThen
     */
    @Test
    public void potentialFamilyMembers() throws Exception {
        List<Person> people = MockData.getPeople();
        // TODO: Implement solution
        Map<String, List<Person>> collect = people.stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(
                                        Person::getLastName
                                ),
                                finisher ->
                                        finisher.entrySet()
                                                .stream()
                                                .filter(entry -> entry.getValue().size() > 1)
                                                .collect(Collectors.toMap(
                                                        Map.Entry::getKey,
                                                        Map.Entry::getValue
                                                ))
                        )
                );
        toJson(collect);
    }

    /**
     * CHALLENGE 24: Calculate the price elasticity of cars by make (price vs year)
     * Operations needed: groupingBy, statistical calculations, regression
     */
    @Test
    public void priceElasticityByMake() throws Exception {
        List<Car> cars = MockData.getCars();
        // TODO: Implement solution - advanced economics challenge

        TreeMap<String, List<Double>> collect = cars.stream()
                //.limit(10)
                .collect(
                        Collectors.groupingBy(
                                Car::getMake,
                                TreeMap::new,
                                Collectors.collectingAndThen(
                                        Collectors.groupingBy(
                                                Car::getYear,
                                                Collectors.mapping(Car::getPrice, Collectors.toList())
                                        )
                                        ,
                                        fin -> {


                                            double avgYear = fin.keySet()
                                                    .stream()
                                                    .mapToInt(Integer::intValue)
                                                    .average()
                                                    .orElse(0.0);

                                            double avgPrice = fin.values()
                                                    .stream()
                                                    .flatMap(Collection::stream)
                                                    .mapToDouble(d -> d)
                                                    .average()
                                                    .orElse(0.0);


                                            List<Map.Entry<Integer, Double>> listToWorkOn = fin.entrySet()
                                                    .stream()
                                                    .flatMap(e -> e.getValue().stream().map(price -> Map.entry(e.getKey(), price)))
                                                    .toList();

                                            int n = listToWorkOn.size();

                                            // covariance (year, price)
                                            double covarianceYear_Price = listToWorkOn.stream()
                                                    .mapToDouble(e -> (e.getKey() - avgYear) * (e.getValue() - avgPrice))
                                                    .sum() / n ;
                                            // variance of year
                                            double varianceYear = listToWorkOn.stream()
                                                    .mapToDouble(e -> Math.pow((e.getKey() - avgYear),2))
                                                    .sum() / n;

                                            //double regressionSlope = (covarianceYear_Price / varianceYear);
                                            double regressionSlope = varianceYear != 0.0 ? covarianceYear_Price / varianceYear : 0.0;

                                            double finalYearAVG = Math.round(avgYear * 1_000d) / 1_000d;
                                            double finalPriceAVG = Math.round(avgPrice * 1_000d) / 1_000d;
                                            double finalSlop = Math.round(regressionSlope * 1_000_000d) / 1_000_000d;



                                            //return regressionSlope;
                                            return List.of(finalYearAVG, finalPriceAVG, finalSlop);
                                        }

                                )
                        )
                );
        //collect.entrySet().forEach(System.out::println);
        System.out.println("****************************");

        // or more simply
        TreeMap<String, Double> collect1 = cars.stream()
                //.limit(10)
                .collect(
                        Collectors.groupingBy(
                                Car::getMake,
                                TreeMap::new,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> {
                                            int n = list.size();

                                            // average year
                                            double yearAVG = list.stream().mapToDouble(Car::getYear).average()
                                                    .orElse(0.0);
                                            // average price
                                            double priceAVG = list.stream().mapToDouble(Car::getPrice).average()
                                                    .orElse(0.0);
                                            // covariance year, price
                                            double cov = list.stream()
                                                    .mapToDouble(e -> (e.getYear() - yearAVG) * (e.getPrice() - priceAVG))
                                                    .sum() / n;
                                            // variance on year
                                            double var = list.stream()
                                                    .mapToDouble(
                                                            e -> Math.pow((e.getYear() - yearAVG), 2)
                                                    ).sum() / n;
                                            // regression slope
                                            double slope = var == 0 ? 0.0 : cov / var;
                                            double finalSlop = Math.round(slope * 1_000_000d) / 1_000_000d;

                                            return finalSlop;

                                        }
                                )
                        )
                );
        collect1.entrySet().forEach(System.out::println);
        System.out.println("****************************");

        // today is reminder and final solution
        Map<String, Double> collect2 = cars.stream()
                //.limit(10)
                .collect(
                        Collectors.groupingBy(
                                Car::getMake,
                                TreeMap::new,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> {
                                            int n = list.size();

                                            double averagePriceByMake = list.stream()
                                                    .mapToDouble(Car::getPrice)
                                                    .average()
                                                    .orElse(0.0);
                                            double averageYearByMake = list.stream()
                                                    .mapToDouble(Car::getYear)
                                                    .average()
                                                    .orElse(0.0);

                                            // Covariance (year,price)
                                            double covariance = list.stream()
                                                    .mapToDouble(
                                                            e -> (e.getYear() - averageYearByMake) * (e.getPrice() - averagePriceByMake)
                                                    )
                                                    .sum() / n;

                                            // variance (year)
                                            double variance = list.stream()
                                                    .mapToDouble(
                                                            e -> Math.pow((e.getYear() - averageYearByMake), 2)
                                                    )
                                                    .sum() / n;

                                            double slope = variance == 0 ? 0.0 : covariance / variance;

                                            // elasticity = (mean year / mean price ) * slope (regression)
                                            double elasticity = averagePriceByMake == 0.0 ? 0.0 :
                                                    slope * (averageYearByMake / averagePriceByMake);

                                            return Math.round(elasticity * 1_000_000d) / 1_000_000d;
                                        }
                                )
                        )
                );
        //collect2.entrySet().forEach(System.out::println);
        // test result because acura elasticity is -48 and with -1530 slope
        TreeMap<Integer, List<Double>> acura = cars.stream()
                .filter(car -> car.getMake().equalsIgnoreCase("acura"))
                .collect(
                        Collectors.groupingBy(
                                Car::getYear,
                                TreeMap::new,
                                Collectors.mapping(
                                        Car::getPrice,
                                        Collectors.toList()
                                )
                        )
                );
        acura.entrySet().forEach(System.out::println);
    }

    /**
     * CHALLENGE 25: Find the optimal car portfolio (diversification across makes and years)
     * Operations needed: groupingBy, limiting, collecting, combinatorial logic
     */
    @Test
    public void optimalCarPortfolio() throws Exception {
        List<Car> cars = MockData.getCars();
        // TODO: Implement solution - advanced optimization challenge
        Map<String, Map<Integer, List<Car>>> collect = cars.stream()
                //.limit(20)
                .collect(Collectors.groupingBy(
                        Car::getMake,
                        Collectors.groupingBy(
                                Car::getYear,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream()
                                                .sorted(Comparator.comparingDouble(Car::getPrice).reversed())
                                                .limit(1)
                                                .toList()
                                )
                        )
                ));

        //collect.entrySet().forEach(System.out::println);

        // the solution:

        // step1 : select one car per make (diversified by make) with best price as simple scoring metric
        List<Car> portfolio = cars.stream()
                .collect(
                        Collectors.groupingBy(
                                Car::getMake,
                                Collectors.collectingAndThen(
                                        Collectors.minBy(Comparator.comparing(Car::getPrice)),
                                        Optional::get
                                )
                        )
                ).values().stream()
                .collect(Collectors.toList());
        //portfolio.forEach(System.out::println);

        // step2 : Calculate portfolio metrics
        double averagePricePerPortfolio = portfolio.stream()
                .mapToDouble(Car::getPrice).average().orElse(0.0);
        double stdDevPrice = Math.sqrt(
                portfolio.stream()
                        .mapToDouble(
                                c -> Math.pow((c.getPrice()- averagePricePerPortfolio),2)
                        )
                        .sum() / portfolio.size());

        int uniqueMakes = (int) portfolio.stream()
                .map(Car::getMake).distinct().count();
        int uniqueYears = (int) portfolio.stream()
                .map(Car::getYear).distinct().count();

        double portfolioScore = portfolio.stream()
                .mapToDouble(
                        c -> 1 / (1 + stdDevPrice) + (uniqueMakes * 0.1) + (uniqueYears * 0.1)
                ).sum();

        /**** first solution scoring ***/
        // Step 3: Print formatted output
//        System.out.println("Optimal Car Portfolio (Diversified by Make & Year)");
//        System.out.println("-------------------------------------------------");
//        System.out.printf("%-12s | %-4s | %-10s | %-5s%n", "Car Make", "Year", "Price ($)", "Score");
//        System.out.println("-------------------------------------------------");
//
//        for (Car c : portfolio) {
//            double score = 1 / (1 + stdDevPrice) + uniqueMakes * 0.1 + uniqueYears * 0.1; // same scoring for display
//            System.out.printf("%-12s | %-4d | %,10.0f | %.2f%n",
//                    c.getMake(), c.getYear(), c.getPrice(), score);
//        }
//
//        System.out.println("-------------------------------------------------");
//        System.out.printf("Portfolio Summary:%n");
//        System.out.printf("- Average Price: $%,.0f%n", averagePricePerPortfolio);
//        System.out.printf("- Price Std Dev: $%,.0f%n", stdDevPrice);
//        System.out.printf("- Number of Makes: %d%n", uniqueMakes);
//        System.out.printf("- Number of Years: %d%n", uniqueYears);
//        System.out.printf("- Portfolio Score: %.2f%n", portfolioScore);
        /*******/


        // or another solution structure
        /*
        portfolio = cars.stream()
        -> group by make
        -> limit 1 car per make
        -> flatten to single list
        -> collect to portfolio list
        -> calculate mean price, std dev, unique makes, unique years
        -> compute score = mean / stdDev + (numUniqueMakes + numUniqueYears)
         */

        // 1- portfolio by make
        List<Car> portfoliTarek = cars.stream()
                .collect(
                        Collectors.groupingBy(
                                Car::getMake
                        )
                ).values().stream()
                .flatMap(
                        list -> list.stream()
                                .sorted(Comparator.comparing(Car::getPrice))
                                .collect(
                                        Collectors.toMap(
                                                Car::getYear,
                                                c -> c,
                                                (existing, replacement) -> existing
                                        )
                                )
                                .values().stream()
                                .limit(1)
                )
                .collect(Collectors.toList());


        //portfoliTarek.forEach(System.out::println);

        int n = portfoliTarek.size();

        // calculate mean (average) price
        double avgPrice = portfoliTarek.stream()
                .mapToDouble(Car::getPrice)
                .average().orElse(0.0);
        // calculate standard deviation of price
        double stdPrice = Math.sqrt(
                portfoliTarek.stream()
                        .mapToDouble(
                                c -> Math.pow((c.getPrice() - avgPrice),2)
                        ).sum() / n
        );

        // calculate mean (average) year
        double avgYear = portfoliTarek.stream()
                .mapToDouble(Car::getYear)
                .average().orElse(0.0);

        // calculate std year
        double stdYear = Math.sqrt(
          portfoliTarek.stream()
                  .mapToDouble(
                          c -> Math.pow((c.getYear() - avgYear),2)
                  )
                  .sum() / n
        );

        // Count unique makes and years
        int uniqueMake = (int) portfoliTarek.stream()
                .map(Car::getMake).distinct().count();
        int uniqueYear = (int) portfoliTarek.stream()
                .map(Car::getYear).distinct().count();

        // Using formula: score = meanPrice / stdPrice + stdYear + diversification bonus
        double postfolioScoreFinal =
                (avgPrice / (stdPrice)) + stdYear + uniqueMake + uniqueYear;

        // Step 7: Print portfolio header
        System.out.println("Optimal Car Portfolio (Diversified by Make & Year)");
        System.out.println("-------------------------------------------------");
        System.out.printf("%-12s | %-4s | %-10s | %-5s%n", "Car Make", "Year", "Price ($)", "Score");
        System.out.println("-------------------------------------------------");

        // Step 8: Print each car with its score
        for (Car c : portfolio) {
            double carScore = (avgPrice / (stdPrice + 1e-6)) + stdYear + uniqueMakes + uniqueYears;
            System.out.printf("%-12s | %-4d | %,10.0f | %.2f%n",
                    c.getMake(), c.getYear(), c.getPrice(), carScore);
        }

        // Step 9: Print portfolio summary
        System.out.println("-------------------------------------------------");
        System.out.println("Portfolio Summary:");
        System.out.printf("- Average Price: $%,.0f%n", avgPrice);
        System.out.printf("- Price Std Dev: $%,.0f%n", stdPrice);
        System.out.printf("- Year Std Dev: %.2f%n", stdYear);
        System.out.printf("- Number of Makes: %d%n", uniqueMakes);
        System.out.printf("- Number of Years: %d%n", uniqueYears);
        System.out.printf("- Portfolio Score: %.2f%n", portfolioScore);






    }
}
