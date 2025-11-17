package com.ticket.management.repository;

import com.ticket.management.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByTitleContainingIgnoreCase(String title);
    List<Ticket> findByStatus(Ticket.Status status);
    List<Ticket> findByPriority(Ticket.Priority priority);
    List<Ticket> findByAssignedToContainingIgnoreCase(String assignedTo);
}