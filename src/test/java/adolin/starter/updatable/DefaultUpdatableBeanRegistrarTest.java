package adolin.starter.updatable;

import adolin.starter.AbstractMockTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.env.Environment;

/**
 * Класс для тестирования {@link DefaultUpdatableBeanRegistrar}
 *
 * @author Adolin Negash 20.05.2021
 */
class DefaultUpdatableBeanRegistrarTest extends AbstractMockTest {

    @InjectMocks
    private DefaultUpdatableBeanRegistrar subj;

    @Mock
    private Environment environment;

    @Mock
    private UpdatableBeanMemberInfoExtractor infoExtractor;

    @Test
    void shouldRegisterBean() {
        // subj.registerBean();
    }
}
