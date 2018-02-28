package services;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/test-config.xml"})
public abstract class BaseServiceTest {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

}