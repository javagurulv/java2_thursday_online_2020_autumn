package internet_store.application.integrationtests;

import internet_store.application.config.SpringCoreConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {SpringCoreConfiguration.class})
//@WebAppConfiguration0

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringContextTest {
//
//
//    @Autowired
//    private ApplicationContext applicationContext;
//
//    @Test
//    public void start() {
//        assertNotNull(applicationContext);
//    }

}


