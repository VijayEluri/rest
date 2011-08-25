package org.jboss.seam.rest.test.exceptions;

import static org.jboss.seam.rest.test.Dependencies.addJBossLoggingDependencyToManifest;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.seam.rest.test.Dependencies;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CatchIntegrationTest extends BuiltinExceptionMappingTest {
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive war = BuiltinExceptionMappingTest.createDeployment();
        war.addAsLibraries(Dependencies.SEAM_CATCH);
        addJBossLoggingDependencyToManifest(war); // SOLDER-119
        return war;
    }

    @Test
    public void testSpecializedExceptionHandlerGetsCalled() throws Exception {
        test("http://localhost:8080/test/exceptions/ie", 415, null);
    }
}
