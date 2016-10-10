package com.app;

import com.app.controllers.HelloWorldController;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.ApplicationPath;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SeedApplicationTest {

    private SeedApplication seedApplication;

    @Before
    public void setUp() throws Exception {
        this.seedApplication = new SeedApplication();

    }

    @Test
    public void testGetClasses() throws Exception {
        final Set<Class<?>> classes = this.seedApplication.getClasses();
        assertNotNull(classes);
        assertEquals(2, classes.size());
        assertThat("contains the the JacksonFeature", classes.contains(JacksonFeature.class));
        assertThat("contains the HelloWorldController", classes.contains(HelloWorldController.class));
    }

    @Test
    public void testAnnotations() throws Exception {


        assertThat("The Class has the annotation ApplicationPath ", this.seedApplication.getClass()
                                                                                        .isAnnotationPresent(
                                                                                                ApplicationPath.class));
        final ApplicationPath annotation = this.seedApplication.getClass()
                                                               .getAnnotation(ApplicationPath.class);
        assertThat("The containing string is 'service'", annotation.value(), is("service"));
    }
}