package com.ticket.management.service;

import com.ticket.management.model.Ticket;
import com.ticket.management.repository.TicketRepository;
import com.ticket.management.repository.CommentRepository;
import com.ticket.management.repository.AttachmentRepository;
import com.ticket.management.repository.ActivityLogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TicketServiceTest {

    @Autowired
    private TicketService ticketService;

    @MockBean
    private TicketRepository ticketRepository;
    
    @MockBean
    private CommentRepository commentRepository;
    
    @MockBean
    private AttachmentRepository attachmentRepository;
    
    @MockBean
    private ActivityLogRepository activityLogRepository;

    @Test
    public void testGetAllTickets() {
        // Create mock tickets
        Ticket ticket1 = new Ticket("Test Ticket 1", "Description 1", Ticket.Priority.HIGH, Ticket.Status.OPEN);
        ticket1.setId(1L);
        
        Ticket ticket2 = new Ticket("Test Ticket 2", "Description 2", Ticket.Priority.MEDIUM, Ticket.Status.IN_PROGRESS);
        ticket2.setId(2L);
        
        List<Ticket> tickets = Arrays.asList(ticket1, ticket2);
        
        // Mock the repository method
        when(ticketRepository.findAll()).thenReturn(tickets);
        
        // Test the service method
        List<Ticket> result = ticketService.getAllTickets();
        
        // Verify the result
        assertEquals(2, result.size());
        assertEquals("Test Ticket 1", result.get(0).getTitle());
        assertEquals("Test Ticket 2", result.get(1).getTitle());
        
        // Verify the repository method was called
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    public void testGetTicketById() {
        // Create a mock ticket
        Ticket ticket = new Ticket("Test Ticket", "Description", Ticket.Priority.HIGH, Ticket.Status.OPEN);
        ticket.setId(1L);
        
        // Mock the repository method
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        
        // Test the service method
        Optional<Ticket> result = ticketService.getTicketById(1L);
        
        // Verify the result
        assertTrue(result.isPresent());
        assertEquals("Test Ticket", result.get().getTitle());
        
        // Verify the repository method was called
        verify(ticketRepository, times(1)).findById(1L);
    }

    @Test
    public void testSaveTicket() {
        // Create a ticket
        Ticket ticket = new Ticket("Test Ticket", "Description", Ticket.Priority.HIGH, Ticket.Status.OPEN);
        
        // Mock the repository method
        when(ticketRepository.save(ticket)).thenReturn(ticket);
        
        // Test the service method
        ticketService.saveTicket(ticket);
        
        // Verify the repository method was called
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    public void testDeleteTicket() {
        // Mock the repository method
        Ticket ticket = new Ticket("Test Ticket", "Description", Ticket.Priority.HIGH, Ticket.Status.OPEN);
        ticket.setId(1L);
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        
        // Test the service method
        ticketService.deleteTicket(1L);
        
        // Verify the repository methods were called
        verify(commentRepository, times(1)).deleteByTicketId(1L);
        verify(attachmentRepository, times(1)).deleteByTicketId(1L);
        verify(activityLogRepository, times(1)).deleteByTicketId(1L);
        verify(ticketRepository, times(1)).deleteById(1L);
    }
}