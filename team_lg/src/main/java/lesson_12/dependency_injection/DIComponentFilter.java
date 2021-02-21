package lesson_12.dependency_injection;

import java.util.List;
import java.util.stream.Collectors;

public class DIComponentFilter {

    public List<Class> filter(List<Class> classes) {

        return classes.stream()
                .filter(aClass -> aClass.isAnnotationPresent(DIComponent.class))
                .collect(Collectors.toList());
    }
}
