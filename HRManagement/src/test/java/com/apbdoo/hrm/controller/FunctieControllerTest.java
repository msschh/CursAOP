package com.apbdoo.hrm.controller;

import com.apbdoo.hrm.config.ProjectConfig;
import com.apbdoo.hrm.entity.Functie;
import com.apbdoo.hrm.service.FunctieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ProjectConfig.class})
@WebAppConfiguration
@AutoConfigureMockMvc
public class FunctieControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private FunctieService functieService;
    
    @Test
    @WithMockUser(roles = "HR")
    public void list_ShouldAddFunctieEntriesToModelAndRenderFunctieListView() throws Exception {
        Functie first = new Functie();
        first.setId(1l);
        first.setNume("Director");

        when(functieService.getFunctii()).thenReturn(Arrays.asList(first));

        mockMvc.perform(get("/functie"))
                .andExpect(status().isOk())
                .andExpect(view().name("functie/list"))
                .andExpect(model().attribute("functii", hasSize(1)))
                .andExpect(model().attribute("functii", hasItem(
                        allOf(
                                hasProperty("id", is(1l)),
                                hasProperty("nume", is("Director"))
                        )
                )));
        
        verify(functieService, times(1)).getFunctii();
        verifyNoMoreInteractions(functieService);
    }
    
    @Test
    @WithMockUser(roles = "HR")
    public void view_FunctieFound_ShouldAddFunctieEntryAndRenderFunctieView() throws Exception {
        Functie found = new Functie();
        found.setId(1l);
        found.setNume("Recruiter");
        
        when(functieService.readFunctie(1)).thenReturn(found);
        
        mockMvc.perform(get("/functie/view/{idFunctie}", 1l))
                .andExpect(status().isOk())
                .andExpect(view().name("functie/view"))
                .andExpect(model().attribute("functie", hasProperty("id", is(1l))))
                .andExpect(model().attribute("functie", hasProperty("nume", is("Recruiter"))));
        
        verify(functieService, times(1)).readFunctie(1);
        verifyNoMoreInteractions(functieService);
    }
}
