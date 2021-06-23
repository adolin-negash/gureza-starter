package adolin.starter.annotations;

import adolin.starter.config.CommonAutoConfiguration;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 * Подключает функционал бинов с обновляемыми свойствами.
 *
 * @author Adolin Negash 23.06.2021
 */
@Import(CommonAutoConfiguration.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableUpdatableBean {

}
