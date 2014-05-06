package de.spinwork.testapp;


import de.spinwork.testapp.base.SomeEntity;
import de.spinwork.testapp.base.SomeEntityRepository;
import de.spinwork.testapp.config.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@Transactional
public class SomeEntityRepositoryIntegrationTest {

    @Autowired
    private SomeEntityRepository repository;

    private SomeEntity testEntity;

    @Before
    public void setUp() throws Exception {
        testEntity = new SomeEntity();
        testEntity.setProperty("prop");
        assertNull(testEntity.getId());
    }

    @Test
    public void testSimpleRepository() throws Exception {
        SomeEntity someEntity = repository.save(testEntity);
        assertNotNull(someEntity);
        assertNotNull(someEntity.getId());
    }

    @Test
    public void testGetSomeEntity() throws Exception {
        SomeEntity someEntity = repository.save(testEntity);

        assertEquals(1, repository.count());



        SomeEntity one = repository.findOne(someEntity.getId());
        assertEquals("prop", one.getProperty());

        repository.save(new SomeEntity());
        assertEquals(2, repository.count());
    }

}
