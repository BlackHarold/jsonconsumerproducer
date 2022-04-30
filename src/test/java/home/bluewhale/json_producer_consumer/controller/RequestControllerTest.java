package home.bluewhale.json_producer_consumer.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testResponse() throws Exception {

        mockMvc.perform(get("/retrieve"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.*").isArray())
                .andExpect(jsonPath("$.*", hasSize(4)))

                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(20))
                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[3].id").value(2))

                .andExpect(jsonPath("$[?(@.urlType === 'LIVE')]").exists())
                .andExpect(jsonPath("$[?(@.urlType === 'ARCHIVE')]").exists())

                .andExpect(content().string(containsString("\"id\":1")))
                .andExpect(content().string(containsString("\"id\":20")))
                .andExpect(content().string(containsString("\"id\":3")))
                .andExpect(content().string(containsString("\"id\":2")))

                .andExpect(content().string(containsString("\"value\":\"fa4b588e-249b-11e9-ab14-d663bd873d93\"")))
                .andExpect(content().string(containsString("\"value\":\"fa4b5b22-249b-11e9-ab14-d663bd873d93\"")))
                .andExpect(content().string(containsString("\"value\":\"fa4b5d52-249b-11e9-ab14-d663bd873d93\"")))
                .andExpect(content().string(containsString("\"value\":\"fa4b5f64-249b-11e9-ab14-d663bd873d93\"")))

                .andExpect(content().string(containsString("\"ttl\":60")))
                .andExpect(content().string(containsString("\"ttl\":120")))
                .andExpect(content().string(containsString("\"ttl\":180")))

                .andReturn();
    }
}

