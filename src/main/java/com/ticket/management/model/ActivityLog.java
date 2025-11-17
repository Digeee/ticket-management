package com.ticket.management.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activity_logs")
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "action", nullable = false)
    private String action;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "performed_by", nullable = false)
    private String performedBy;

    @Column(name = "performed_date", nullable = false)
    private LocalDateTime performedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @PrePersist
    protected void onCreate() {
        performedDate = LocalDateTime.now();
    }

    // Constructors
    public ActivityLog() {
    }

    public ActivityLog(String action, String description, String performedBy) {
        this.action = action;
        this.description = description;
        this.performedBy = performedBy;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public LocalDateTime getPerformedDate() {
        return performedDate;
    }

    public void setPerformedDate(LocalDateTime performedDate) {
        this.performedDate = performedDate;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}