package adolin.starter.config;

import adolin.starter.updatable.DefaultUpdatableBeanRegistry;
import adolin.starter.updatable.UpdatableAnnotationBeanPostProcessor;
import adolin.starter.updatable.UpdatableBeanMemberInfoExtractor;
import adolin.starter.updatable.UpdatableBeanRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
     * @return {@link DefaultUpdatableBeanRegistry}
     */
    @ConditionalOnMissingBean
    @Bean
    public UpdatableBeanRegistry updatableBeanRegistry() {
        return new DefaultUpdatableBeanRegistry();
    }

    /**
     * Обработчик бинов, который добавляет в бины функционал обновляемых полей.
     *
     * @return {@link UpdatableAnnotationBeanPostProcessor}
     */
    @ConditionalOnMissingBean
    @Bean
    public UpdatableAnnotationBeanPostProcessor updatableAnnotationBeanPostProcessor() {
        return new UpdatableAnnotationBeanPostProcessor();
    }

    /**
     * Обработчик, извлекающий из класса обновляемые поля и сеттеры.
     *
     * @return {@link UpdatableBeanMemberInfoExtractor}
     */
    @ConditionalOnMissingBean
    @Bean
    public UpdatableBeanMemberInfoExtractor updatableBeanMemberInfoExtractor() {
        return new UpdatableBeanMemberInfoExtractor();
    }
}
