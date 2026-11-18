package com.victoria.helpdesk.ticket;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc
@TestPropertySource(properties={"spring.datasource.url=jdbc:h2:mem:helpdesk;MODE=PostgreSQL;DB_CLOSE_DELAY=-1","spring.datasource.driver-class-name=org.h2.Driver","spring.datasource.username=sa","spring.datasource.password=","spring.jpa.hibernate.ddl-auto=create-drop"})
class TicketControllerTest {
  @Autowired MockMvc mvc; @Autowired ObjectMapper mapper;
  @Test void createsAndListsTicket() throws Exception {
    String body=mapper.writeValueAsString(new TicketRequest("Notebook não liga","Equipamento não inicia","Ana",TicketPriority.HIGH));
    mvc.perform(post("/api/v1/tickets").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isCreated()).andExpect(jsonPath("$.title").value("Notebook não liga"));
    mvc.perform(get("/api/v1/tickets")).andExpect(status().isOk()).andExpect(jsonPath("$[0].requester").value("Ana"));
  }
  @Test void rejectsInvalidTicket() throws Exception {
    mvc.perform(post("/api/v1/tickets").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"\",\"description\":\"\",\"requester\":\"\"}")).andExpect(status().isBadRequest());
  }
}