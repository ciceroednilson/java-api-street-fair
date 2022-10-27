package br.com.ciceroednilson.feira.controller;

import br.com.ciceroednilson.feira.domain.StreetFairDomain;
import br.com.ciceroednilson.feira.service.StreetFairService;
import br.com.ciceroednilson.feira.util.Util;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureWebMvc
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = StreetFairController.class)
public class StreetFairControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StreetFairService streetFairService;

    @MockBean
    private JdbcTemplate jdbcTemplate;

    private static final String URI = "/fair";

    private static final String JSON_STREET_FAIR_DOMAIN = "street_fair_domain.json";

    @Test
    public void postStreetFair() throws Exception {
        final StreetFairDomain streetFairDomain = Util.loadFile(JSON_STREET_FAIR_DOMAIN, StreetFairDomain.class);
        doNothing().when(streetFairService).register(streetFairDomain);
        final String body = Util.loadFile(JSON_STREET_FAIR_DOMAIN);
        mockMvc.perform(post(URI).contentType("application/json")
                        .content(body))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteStreetFair() throws Exception {
        doNothing().when(streetFairService).delete(1);
        mockMvc.perform(delete(URI.concat("/1")).contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateStreetFair() throws Exception {
        final StreetFairDomain streetFairDomain = Util.loadFile(JSON_STREET_FAIR_DOMAIN, StreetFairDomain.class);
        final String body = Util.loadFile(JSON_STREET_FAIR_DOMAIN);
        doNothing().when(streetFairService).update(streetFairDomain, 1);
        mockMvc.perform(put(URI.concat("/1")).contentType("application/json")
                        .content(body))
                .andExpect(status().isOk());
    }

    @Test
    public void findStreetFair() throws Exception {
        final String body = Util.loadFile(JSON_STREET_FAIR_DOMAIN);
        when(streetFairService.find(any(), any(), any(), any())).thenReturn(new ArrayList<>());
        mockMvc.perform(get(URI.concat("?district=FREGUESIA DO O&region_five=Norte&fair_name=CRUZ DAS ALMAS&neighborhood=CRUZ DAS ALMAS"))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}
