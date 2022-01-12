package festivalmanager.festival;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author Conrad
 * Festival validator
 * https://www.baeldung.com/spring-mvc-custom-validator
 */
@Documented
@Constraint(validatedBy = FestivalValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FestivalConstraint {

    String message() default "Invalid festival";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
