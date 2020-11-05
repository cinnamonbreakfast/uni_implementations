package repository.sorting;

import Domain.Containers.Pair;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Sort {
    public enum Direction {
        ASC, DESC
    }

    private ArrayList<Pair<Direction,String>> sortingMethods=new ArrayList<>();

    public Sort(String... fields) {
        Arrays.stream(fields)
                .forEach(field -> sortingMethods.add(new Pair(Direction.ASC,field)));
    }

    public Sort(Direction direction, String... fields) {
        Arrays.stream(fields)
                .forEach(field -> sortingMethods.add(new Pair(direction,field)));
    }

    public Sort and(Sort other) {
        sortingMethods.addAll(other.sortingMethods);
        return this;
    }

    public List<Object> sort(List<Object> entities) {
        AtomicReference<ArrayList<Object>> sorted = new AtomicReference<>(new ArrayList<>(entities));
        ArrayList<Pair<Direction,String>> reversed=new ArrayList(sortingMethods);

        Collections.reverse(reversed);

        reversed.stream()
                .forEach(pair -> {
                    Direction direction=pair.getLeft();
                    String field=pair.getRight();
                    int sign;
                    if (direction.equals(Direction.DESC)) {
                        sign=-1;
                    }
                    else {
                        sign=1;
                    }
                    sorted.set((ArrayList<Object>) sorted.get().stream()
                            .sorted((entity1, entity2) -> {
                                try {
                                    Field field1 = entity1.getClass().getDeclaredField(field);
                                    field1.setAccessible(true);

                                    Field field2 = entity2.getClass().getDeclaredField(field);
                                    field2.setAccessible(true);

                                    Comparable parameter1 = (Comparable) field1.get(entity1);
                                    Comparable parameter2 = (Comparable) field2.get(entity2);

                                    return sign * parameter1.compareTo(parameter2);
                                } catch (IllegalAccessException | NoSuchFieldException e) {
                                }
                                try {
                                    Field field1 = entity1.getClass().getSuperclass().getDeclaredField(field);
                                    field1.setAccessible(true);
                                    Comparable parameter1 = (Comparable) field1.get(entity1);

                                    Field field2 = entity2.getClass().getSuperclass().getDeclaredField(field);
                                    field2.setAccessible(true);
                                    Comparable parameter2 = (Comparable) field1.get(entity1);

                                    return sign * parameter1.compareTo(parameter2);
                                } catch (NoSuchFieldException | IllegalAccessException e) {
                                    try {
                                        throw new Exception("'"+ field + "' field is invalid!");
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                return 0;
                            }).collect(Collectors.toList()));
                });
        return sorted.get();
    }
}