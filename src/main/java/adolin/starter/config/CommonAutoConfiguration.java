package adolin.starter.config;

import adolin.starter.updatable.DefaultUpdatableBeanRegistrar;
import adolin.starter.updatable.UpdatableAnnotationBeanPostProcessor;
import adolin.starter.updatable.UpdatableBeanMemberInfoExtractor;
import adolin.starter.updatable.UpdatableBeanRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация инфраструктурных бинов.
 *
 * @author Adolin Negash 14.05.2021
 */
@Configuration
public class CommonAutoConfiguration {

    /**
     * Реестр обновляемых свойств.
     *
     * @return {@link DefaultUpdatableBeanRegistrar}
     */
    @Bean
    public UpdatableBeanRegistrar updatableBeanRegistry() {
        return new DefaultUpdatableBeanRegistrar();
    }

    /**
     * Обработчик бинов, который добавляет в бины функционал обновляемых полей.
     *
     * @return {@link UpdatableAnnotationBeanPostProcessor}
     */
    @Bean
    public UpdatableAnnotationBeanPostProcessor updatableAnnotationBeanPostProcessor() {
        System.out.println("create updatableAnnotationBeanPostProcessor");
        return new UpdatableAnnotationBeanPostProcessor();
    }

    /**
     * Обработчик, извлекающий из класса обновляемые поля и сеттеры.
     *
     * @return {@link UpdatableBeanMemberInfoExtractor}
     */
    @Bean
    public UpdatableBeanMemberInfoExtractor updatableBeanMemberInfoExtractor() {
        return new UpdatableBeanMemberInfoExtractor();
    }
}
