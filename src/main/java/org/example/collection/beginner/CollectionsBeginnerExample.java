package org.example.collection.beginner;

import java.util.*;
import java.util.stream.Collectors;

import org.example.dto.User;
import org.example.enums.Sex;

public class CollectionsBeginnerExample implements CollectionBeginnerExampleInterface {

    /**
     * Nie poprawiajac testu nalezy poprawic kod tak aby testy przechodzily wszystkie na zielono. Kod przed refactorem:
     * <pre>{@code
     *   Set<User> usersSet = new HashSet<>();
     *   users.forEach(usersSet::add);
     *   return usersSet.size();
     * }</pre>
     */
    public int getCountOfDifferentUsers(List<User> users) {
        return (int)users.stream()
                .distinct()
                .count();
    }

    @Override
    public int getCountOfFemaleUsers(List<User> users){
        return (int)users.stream()
                .filter(user -> user.getSex()==Sex.F)
                .count();
    }

    @Override
    public int sumOfYearsOfUniqueUsers(List<User> users) {
        return  users.stream()
                .distinct()
                .map(User::getAge)
                .mapToInt(Integer::intValue)
                .sum();

    }

    @Override
    public List<User> getNaturalSortedUsersByAge(List<User> users) {
        return Optional.ofNullable(users)
                .orElseGet(Collections::emptyList).stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());



    }

    @Override
    public List<User> getUsersSortedByNameAndAge(List<User> users) {
        return Optional.ofNullable(users)
                .orElseGet(Collections::emptyList).stream()
                .sorted(Comparator.nullsLast(this::customSort))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getUsersWithCorrectedAge(List<User> users) {
        return Collections.emptyList();
    }

    @Override
    public List<Sex> getUsersWithCorrectedGender(List<User> users) {
        return Collections.emptyList();
    }

    private int customSort(User user1,User user2){
         boolean pierwszyZnak = user1.getName().substring(0,1).equals(user2.getName().substring(0,1));
         boolean znakOrazWiekszyWiek = pierwszyZnak &&user1.getAge()>user2.getAge();
         boolean znakOrazMniejszyWiek = pierwszyZnak &&user1.getAge()<user2.getAge();
         boolean znakOrazEqualsWiek = pierwszyZnak &&user1.getAge()==user2.getAge();

        if( znakOrazEqualsWiek )
            return (user1.getName().compareTo(user2.getName()));
        if ( znakOrazWiekszyWiek)
            return 1;
        if( znakOrazMniejszyWiek)
            return -1;
        else
            return 1;
    }
}
