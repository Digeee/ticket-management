package com.ticket.management.controller;

import com.ticket.management.model.Ticket;
import com.ticket.management.service.TicketService;
import com.ticket.management.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TicketController.class)
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;
    
    @MockBean
    private TicketRepository ticketRepository;

    @Test
    public void testViewHomePage() throws Exception {
        // Create mock tickets
        Ticket ticket1 = new Ticket("Test Ticket 1", "Description 1", Ticket.Priority.HIGH, Ticket.Status.OPEN);
        ticket1.setId(1L);
        
        Ticket ticket2 = new Ticket("Test Ticket 2", "Description 2", Ticket.Priority.MEDIUM, Ticket.Status.IN_PROGRESS);
        ticket2.setId(2L);
        
        List<Ticket> tickets = Arrays.asList(ticket1, ticket2);
        
        // Mock the service method
        when(ticketService.getAllTickets()).thenReturn(tickets);
        
        // Perform the request and verify the response
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("listTickets", tickets));
    }
}