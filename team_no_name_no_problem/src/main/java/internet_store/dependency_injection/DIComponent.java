package internet_store.dependency_injection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DIComponent {
}

//@Retention(RetentionPolicy.RUNTIME) - означает, что информация об
//этой аннотации будет доступна в момент выполнения приложения.
//
//@Target(ElementType.TYPE) - означает, что эту аннотацию можно
//использовать только для классов.