package ru.bankingservices.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import ru.bankingservices.service.TokenService;
import ru.bankingservices.service.TransactionService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TransactionService transactionService;
    @MockBean
    private TokenService tokenService;
    @BeforeEach
    void setup() {
        Mockito.when(tokenService.validateToken("valid_token")).thenReturn(true);
    }
    @Test
    void testTransferSuccess() throws Exception {
        Mockito.doNothing().when(transactionService).transfer(1L, 2L, 10.0);
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/transactions/transfer")
                .param("senderAccountId", "1")
                .param("recipientAccountId", "2")
                .param("amount", "10.0")
                .header("Authorization", "valid_token"))
                .andExpect(status().isOk());
    }
}
