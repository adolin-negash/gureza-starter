package adolin.starter.updatable;

import adolin.starter.AbstractMockTest;
import adolin.starter.annotations.Updatable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Класс для тестирования {@link UpdatableAnnotationBeanPostProcessor}
 *
 * @author Adolin Negash 19.05.2021
 */
class UpdatableAnnotationBeanPostProcessorTest extends AbstractMockTest {

    private static final String BEAN_NAME = "test bean";

    @InjectMocks
    private UpdatableAnnotationBeanPostProcessor subj;

    @Mock
    private UpdatableBeanRegistrar registry;

    @Mock
    private ConfigurableListableBeanFactory beanFactory;

    @Mock
    private Updatable updatableBeanAnnotation;

    @Mock
    private BeanDefinition beanDefinition;

    @Captor
    private ArgumentCaptor<Object> beanCaptor;

    @Captor
    private ArgumentCaptor<Object> proxyBeanCaptor;

    @Captor
    private ArgumentCaptor<String> beanNameCaptor;

    @Captor
    private ArgumentCaptor<Updatable> beanAnnotationCaptor;

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(registry, beanFactory, updatableBeanAnnotation, beanDefinition);
    }

    @Test
    void shouldGetOrder() {
        assertEquals(UpdatableAnnotationBeanPostProcessor.LOWEST_PRECEDENCE, subj.getOrder());
    }

    @Test
    void shouldSetBeanFactory() {
        subj.setBeanFactory(beanFactory);

        assertThrows(IllegalArgumentException.class,
            () -> subj.setBeanFactory(Mockito.mock(BeanFactory.class)));
    }

    @Test
    void shouldNotAddBeanWithoutAnnotation() {
        final Object bean = new Object();

        when(beanFactory.containsBean(eq(BEAN_NAME))).thenReturn(true);
        when(beanFactory.findAnnotationOnBean(eq(BEAN_NAME), eq(Updatable.class)))
            .thenReturn(null);

        assertSame(bean, subj.postProcessBeforeInitialization(bean, BEAN_NAME));
        assertSame(bean, subj.postProcessAfterInitialization(bean, BEAN_NAME));

        verify(beanFactory).containsBean(eq(BEAN_NAME));
        verify(beanFactory).findAnnotationOnBean(eq(BEAN_NAME), eq(Updatable.class));
    }

    @Test
    void shouldThrowWhenIncompatibleScope() {
        final Object bean = new Object();

        when(beanFactory.containsBean(eq(BEAN_NAME))).thenReturn(true);
        when(beanFactory.findAnnotationOnBean(eq(BEAN_NAME), eq(Updatable.class)))
            .thenReturn(updatableBeanAnnotation);
        when(beanFactory.getBeanDefinition(BEAN_NAME)).thenReturn(beanDefinition);
        when(beanDefinition.getScope()).thenReturn("new scope");

        assertThrows(IllegalStateException.class, () -> subj.postProcessBeforeInitialization(bean, BEAN_NAME));

        verify(beanFactory).containsBean(eq(BEAN_NAME));
        verify(beanFactory).findAnnotationOnBean(eq(BEAN_NAME), eq(Updatable.class));
        verify(beanFactory).getBeanDefinition(BEAN_NAME);
        verify(beanDefinition).getScope();
    }

    @Test
    void shouldRegisterBean() {
        final Object bean = new Object();
        final Object proxyBean = new Object();

        when(beanFactory.containsBean(eq(BEAN_NAME))).thenReturn(true);
        when(beanFactory.findAnnotationOnBean(eq(BEAN_NAME), eq(Updatable.class)))
            .thenReturn(updatableBeanAnnotation);
        when(beanFactory.getBeanDefinition(BEAN_NAME)).thenReturn(beanDefinition);
        when(beanDefinition.getScope()).thenReturn("");

        subj.postProcessBeforeInitialization(bean, BEAN_NAME);

        verify(beanFactory).containsBean(eq(BEAN_NAME));
        verify(beanFactory).findAnnotationOnBean(eq(BEAN_NAME), eq(Updatable.class));
        verify(beanFactory).getBeanDefinition(BEAN_NAME);
        verify(beanDefinition).getScope();

        subj.postProcessAfterInitialization(proxyBean, BEAN_NAME);

        verify(registry).registerBean(beanNameCaptor.capture(),
            beanCaptor.capture(),
            proxyBeanCaptor.capture(),
            beanAnnotationCaptor.capture());

        assertEquals(BEAN_NAME, beanNameCaptor.getValue());
        assertSame(bean, beanCaptor.getValue());
        assertSame(proxyBean, proxyBeanCaptor.getValue());
        assertSame(updatableBeanAnnotation, beanAnnotationCaptor.getValue());
    }

    @Test
    void shouldProcessInvalidBean() {
        final Object bean = new Object();
        final Object proxyBean = new Object();

        when(beanFactory.containsBean(eq(BEAN_NAME))).thenReturn(false);

        final Object result1 = subj.postProcessBeforeInitialization(bean, BEAN_NAME);

        verify(beanFactory).containsBean(eq(BEAN_NAME));

        final Object result2 = subj.postProcessAfterInitialization(proxyBean, BEAN_NAME);

        assertSame(bean, result1);
        assertSame(proxyBean, result2);
    }
}
