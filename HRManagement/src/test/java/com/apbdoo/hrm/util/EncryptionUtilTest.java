package com.apbdoo.hrm.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class EncryptionUtilTest {
    
    @Test
    public void encrypt_EncryptionOfStringShouldBeDifferentFromInitialString() throws Exception {
        String s1 = new String("test");
        String s2 = EncryptionUtil.encrypt(s1);
        
        assertNotEquals(s1, s2);
    }
    
    @Test
    public void encrypt_EncryptionOfTheSameStringShouldBeEquals() throws Exception {
        String s1 = new String("test1");
        String s2 = new String("test1");
        
        s1 = EncryptionUtil.encrypt(s1);
        s2 = EncryptionUtil.encrypt(s2);
        
        assertEquals(s1, s2);
    }
    
    @Test
    public void encrypt_EncryptionOfDifferentStringShouldNotBeEquals() throws Exception {
        String s1 = new String("test1");
        String s2 = new String("test2");

        s1 = EncryptionUtil.encrypt(s1);
        s2 = EncryptionUtil.encrypt(s2);

        assertNotEquals(s1, s2);
    }
}
