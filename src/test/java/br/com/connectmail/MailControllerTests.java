package br.com.connectmail;

import br.com.connectmail.enums.MailTypeEnum;
import br.com.connectmail.model.MailDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MailControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper mapper;

    @BeforeAll
    public static void starters() {
        mapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Send confirmation account email")
    void sendConfirmationAccountEmail() throws Exception {
        String from = "noreply.uboard@gmail.com";
        String to = "souzaronny775@gmail.com";
        String subject = "Connect - Confirmação de Conta";

        Map<String, Object> properties = new HashMap<>();
        properties.put("code", "345654");
        properties.put("username", "Ronyeri Souza");

        MailDTO mailDTO = new MailDTO(from, to, subject, MailTypeEnum.CONFIRM_ACCOUNT, properties);
        String mailAsJson = mapper.writeValueAsString(mailDTO);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/send").contentType("application/json").content(mailAsJson)).andExpect(status().isOk());
    }


    @Test
    @DisplayName("Send confirmation condominium email")
    void sendConfirmationCondominiumEmail() throws Exception {
        String from = "noreply.uboard@gmail.com";
        String to = "souzaronny775@gmail.com";
        String subject = "Connect - Confirmação de E-mail do Condomínio";

        Map<String, Object> properties = new HashMap<>();
        properties.put("code", "345654");
        properties.put("username", "Ronyeri Souza");
        properties.put("condominium", "Bloco 24");

        MailDTO mailDTO = new MailDTO(from, to, subject, MailTypeEnum.CONFIRM_CONDOMINIUM_EMAIL, properties);
        String mailAsJson = mapper.writeValueAsString(mailDTO);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/send").contentType("application/json").content(mailAsJson)).andExpect(status().isOk());
    }
}
