package com.ticket.management.repository;

import com.ticket.management.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    List<Attachment> findByTicketIdOrderByUploadDateAsc(Long ticketId);
    
    @Modifying
    @Query("DELETE FROM Attachment a WHERE a.ticket.id = ?1")
    void deleteByTicketId(Long ticketId);
}