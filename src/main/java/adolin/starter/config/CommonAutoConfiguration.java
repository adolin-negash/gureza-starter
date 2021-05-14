package adolin.starter.config;

import adolin.starter.updatable.DefaultUpdatableBeanRegistry;
import adolin.starter.updatable.UpdatableBeanRegistry;
import adolin.starter.updatable.UpdatableFieldsInitializer;
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
   * Инициализатор бинов с обновляемыми свойствами.
   *
   * @return {@link UpdatableFieldsInitializer}
   */
  @ConditionalOnMissingBean
  @Bean
  public UpdatableFieldsInitializer updatableFieldsInitializer() {
    return new UpdatableFieldsInitializer();
  }

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
}
