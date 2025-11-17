package com.ticket.management.service;

import com.ticket.management.model.Ticket;
import com.ticket.management.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    
    @Autowired
    private TicketRepository ticketRepository;
    
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
    
    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }
    
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
    
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
    
    public List<Ticket> searchTickets(String keyword) {
        return ticketRepository.findByTitleContainingIgnoreCase(keyword);
    }
    
    public List<Ticket> getTicketsByStatus(Ticket.Status status) {
        return ticketRepository.findByStatus(status);
    }
    
    public List<Ticket> getTicketsByPriority(Ticket.Priority priority) {
        return ticketRepository.findByPriority(priority);
    }
    
    public List<Ticket> getTicketsByAssignee(String assignee) {
        return ticketRepository.findByAssignedToContainingIgnoreCase(assignee);
    }
}