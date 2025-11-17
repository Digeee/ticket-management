package com.ticket.management.service;

import com.ticket.management.model.Ticket;
import com.ticket.management.model.Comment;
import com.ticket.management.model.Attachment;
import com.ticket.management.model.ActivityLog;
import com.ticket.management.repository.TicketRepository;
import com.ticket.management.repository.CommentRepository;
import com.ticket.management.repository.AttachmentRepository;
import com.ticket.management.repository.ActivityLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private AttachmentRepository attachmentRepository;
    
    @Autowired
    private ActivityLogRepository activityLogRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(long id) {
        return ticketRepository.findById(id);
    }

    public void saveTicket(Ticket ticket) {
        // If this is a new ticket, add a creation activity log
        if (ticket.getId() == null) {
            ticketRepository.save(ticket);
            addActivityLog(ticket.getId(), "Ticket Created", "Ticket was created", "System");
        } else {
            ticketRepository.save(ticket);
        }
    }

    public void deleteTicket(long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            addActivityLog(id, "Ticket Deleted", "Ticket was deleted", "System");
            ticketRepository.deleteById(id);
        }
    }

    public List<Ticket> searchTickets(String keyword) {
        return ticketRepository.findByTitleContainingIgnoreCase(keyword);
    }
    
    public List<Ticket> findByStatus(Ticket.Status status) {
        return ticketRepository.findByStatus(status);
    }
    
    public List<Ticket> findByPriority(Ticket.Priority priority) {
        return ticketRepository.findByPriority(priority);
    }
    
    public List<Ticket> findByAssignee(String assignee) {
        return ticketRepository.findByAssignedToContainingIgnoreCase(assignee);
    }
    
    // Comment methods
    public List<Comment> getCommentsByTicketId(Long ticketId) {
        return commentRepository.findByTicketIdOrderByCreatedDateAsc(ticketId);
    }
    
    public void addComment(Long ticketId, String content, String userName) {
        Optional<Ticket> ticketOpt = ticketRepository.findById(ticketId);
        if (ticketOpt.isPresent()) {
            Ticket ticket = ticketOpt.get();
            Comment comment = new Comment(content, userName);
            comment.setTicket(ticket);
            commentRepository.save(comment);
            
            // Add activity log
            addActivityLog(ticketId, "Comment Added", "New comment: " + content, userName);
        }
    }
    
    // Attachment methods
    public List<Attachment> getAttachmentsByTicketId(Long ticketId) {
        return attachmentRepository.findByTicketIdOrderByUploadDateAsc(ticketId);
    }
    
    public void addAttachment(Long ticketId, String fileName, String fileType, Long fileSize, String filePath, String uploadedBy) {
        Optional<Ticket> ticketOpt = ticketRepository.findById(ticketId);
        if (ticketOpt.isPresent()) {
            Ticket ticket = ticketOpt.get();
            Attachment attachment = new Attachment(fileName, fileType, fileSize, filePath, uploadedBy);
            attachment.setTicket(ticket);
            attachmentRepository.save(attachment);
            
            // Add activity log
            addActivityLog(ticketId, "File Attached", "New attachment: " + fileName, uploadedBy);
        }
    }
    
    // Activity log methods
    public List<ActivityLog> getActivityLogsByTicketId(Long ticketId) {
        return activityLogRepository.findByTicketIdOrderByPerformedDateDesc(ticketId);
    }
    
    public void addActivityLog(Long ticketId, String action, String description, String performedBy) {
        Optional<Ticket> ticketOpt = ticketRepository.findById(ticketId);
        if (ticketOpt.isPresent()) {
            Ticket ticket = ticketOpt.get();
            ActivityLog activityLog = new ActivityLog(action, description, performedBy);
            activityLog.setTicket(ticket);
            activityLogRepository.save(activityLog);
        }
    }
    
    // Helper methods for Thymeleaf templates
    public Ticket.Priority[] getPriorityValues() {
        return Ticket.Priority.values();
    }
    
    public Ticket.Status[] getStatusValues() {
        return Ticket.Status.values();
    }
}