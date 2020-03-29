package com.apbdoo.hrm.service;

import com.apbdoo.hrm.config.ProjectConfig;
import com.apbdoo.hrm.entity.Utilizator;
import com.apbdoo.hrm.repository.UtilizatorRepository;
import com.apbdoo.hrm.util.EncryptionUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UtilizatorServiceImpl.class})
public class UtilizatorServiceTest {
    
    @Autowired
    UtilizatorService utilizatorService;

    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @MockBean
    UtilizatorRepository utilizatorRepository;
    
    @Test
    public void saveUtilizator_ShouldEncryptThePasswordWhenIdIsNull() throws Exception {
        Utilizator utilizator = new Utilizator();
        utilizator.setUsername("username");
        utilizator.setParola("parola");
        
        when(utilizatorRepository.save(utilizator)).thenReturn(utilizator);
        when(bCryptPasswordEncoder.encode(utilizator.getParola())).thenReturn(EncryptionUtil.encrypt(utilizator.getParola()));
        
        utilizator = utilizatorService.saveUtilizator(utilizator);
        
        assertNotEquals("parola", utilizator.getParola());
    }

    @Test
    public void saveUtilizator_ShouldNotEncryptThePasswordWhenIdIsNotNull() throws Exception {
        Utilizator utilizator = new Utilizator();
        utilizator.setId(1l);
        utilizator.setUsername("username");
        utilizator.setParola("parola");

        when(utilizatorRepository.save(utilizator)).thenReturn(utilizator);
        when(bCryptPasswordEncoder.encode(utilizator.getParola())).thenReturn(EncryptionUtil.encrypt(utilizator.getParola()));

        utilizator = utilizatorService.saveUtilizator(utilizator);

        assertEquals("parola", utilizator.getParola());
    }
}
