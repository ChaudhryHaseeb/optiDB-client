package org.optidb.optidbclient.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.optidb.optidbclient.controller.MainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainControllerTest
{
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MainController mc;

    @Test
    public void testGetListeInsert()
    {
        String liste = "['1','2','3','4']";
        assertNotNull(mc.getListeInsert(liste));
    }

    @Test
    public void checkController() throws Exception {
        assertThat(mc).isNotNull();
    }

    @Test
    public void checkHomePage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("Bienvenue sur <b>OptiDB</b>");
    }

    public void checkform() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/formMultiPlatform",
                String.class)).contains("Test avec comparaison");
    }
}
