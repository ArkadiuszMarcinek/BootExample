package org.example.collection.beginner;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
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

    @Override
    public List<User> getUsersWithCorrectedAge(List<User> users) {

        return Optional.ofNullable(users)
                .orElseGet(Collections::emptyList).stream()
                .map(this::getAgeOfUser)
                .collect(Collectors.toList());
    }

    private User getAgeOfUser(User user){
        Optional.ofNullable(user).ifPresent(this::defineAgeOfUser);
        return user;
    }
    private void defineAgeOfUser(User user){
        if(LocalDate.now().isBefore(user.getBirthday()))
            user.setAge(0);
        else user.setAge( (int)ChronoUnit.YEARS.between(user.getBirthday(),LocalDate.now()));


    }

    @Override
    public List<Sex> getUsersWithCorrectedGender(List<User> users) {
        return Optional.ofNullable(users)
                .orElseGet(Collections::emptyList).stream()
                .map(this::getSexOfUser)
                .collect(Collectors.toList());
    }

    private Sex getSexOfUser(User user)
    {
        return Optional.ofNullable(user).isPresent() ? defineSexOfUser(user) : Sex.U;
    }
    private Sex defineSexOfUser(User user)
    {
        final int lastCharOfName = user.getName().length()-1;
        if(user.getName().endsWith("a"))
            return Sex.F;
        if(!Character.isLetter(user.getName().charAt(lastCharOfName)))
            return Sex.U;
        else return Sex.M;
    }

}
