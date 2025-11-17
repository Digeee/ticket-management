package com.ticket.management.repository;

import com.ticket.management.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTicketIdOrderByCreatedDateAsc(Long ticketId);
    
    @Modifying
    @Query("DELETE FROM Comment c WHERE c.ticket.id = ?1")
    void deleteByTicketId(Long ticketId);
}