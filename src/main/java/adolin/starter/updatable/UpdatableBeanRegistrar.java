package adolin.starter.updatable;

import adolin.starter.annotations.Updatable;
import java.util.Collection;

/**
 * Реестр обновляемых свойств. Хранит свойства и обновляет их в привязанных бинах.
 *
 * @author Adolin Negash 13.05.2021
 */
public interface UpdatableBeanRegistrar {

    /**
     * Возвращает список свойств и их значений.
     *
     * @return список свойств.
     */
    Collection<PropertyValue> getProperties();

    /**
     * Регистрирует в реестре бин с обновляемыми свойствами.
     *
     * @param beanName   имя бина.
     * @param bean       бин.
     * @param proxyBean  запроксированный бин.
     * @param annotation аннотация.
     */
    void registerBean(String beanName, Object bean, Object proxyBean, Updatable annotation);

    /**
     * Обновляет заданные свойства.
     *
     * @param listOfValues список обновляемых свойств и их значений.
     */
    void updateProperties(Collection<PropertyValue> listOfValues) throws Exception;
}
