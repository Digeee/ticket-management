package com.ticket.management.repository;

import com.ticket.management.model.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
    List<ActivityLog> findByTicketIdOrderByPerformedDateDesc(Long ticketId);
    
    @Modifying
    @Query("DELETE FROM ActivityLog a WHERE a.ticket.id = ?1")
    void deleteByTicketId(Long ticketId);
}