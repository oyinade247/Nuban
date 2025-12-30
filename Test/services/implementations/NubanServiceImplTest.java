package services.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.interfaces.NubanService;

import static org.junit.jupiter.api.Assertions.*;

class NubanServiceImplTest {

    private NubanService nubanService;

    @BeforeEach
    void setUp(){
        nubanService = new NubanServiceImpl();
    }

    @Test
    void testThatNubanServiceGenerateAccountNumberAndLengthIsTen(){
        String nuban = nubanService.generateAccountNumber(11);
        assertEquals(10, nuban.length());
    }

    @Test
    void testThatNubanServiceValidateAccount(){
        String nuban = nubanService.generateAccountNumber(11);
        assertTrue(nubanService.validateNuban(nuban, 11));

    }

}