package com.ticket.management.controller;

import com.ticket.management.model.Ticket;
import com.ticket.management.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;
    
    // Directory to store uploaded files
    private static final String UPLOAD_DIR = "uploads/";

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Ticket> listTickets = ticketService.getAllTickets();
        model.addAttribute("listTickets", listTickets);
        return "index";
    }

    @GetMapping("/showNewTicketForm")
    public String showNewTicketForm(Model model) {
        Ticket ticket = new Ticket();
        model.addAttribute("ticket", ticket);
        return "new_ticket";
    }

    @PostMapping("/saveTicket")
    public String saveTicket(@ModelAttribute("ticket") Ticket ticket) {
        ticketService.saveTicket(ticket);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Optional<Ticket> ticket = ticketService.getTicketById(id);
        if (ticket.isPresent()) {
            model.addAttribute("ticket", ticket.get());
            return "update_ticket";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/deleteTicket/{id}")
    public String deleteTicket(@PathVariable(value = "id") long id) {
        ticketService.deleteTicket(id);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<Ticket> listTickets = ticketService.searchTickets(keyword);
        model.addAttribute("listTickets", listTickets);
        return "index";
    }
    
    // New endpoints for comments, attachments, and activity logs
    @GetMapping("/ticket/{id}")
    public String viewTicketDetails(@PathVariable(value = "id") long id, Model model) {
        Optional<Ticket> ticket = ticketService.getTicketById(id);
        if (ticket.isPresent()) {
            model.addAttribute("ticket", ticket.get());
            model.addAttribute("comments", ticketService.getCommentsByTicketId(id));
            model.addAttribute("attachments", ticketService.getAttachmentsByTicketId(id));
            model.addAttribute("activityLogs", ticketService.getActivityLogsByTicketId(id));
            model.addAttribute("newComment", new com.ticket.management.model.Comment());
            return "ticket_details";
        } else {
            return "redirect:/";
        }
    }
    
    @PostMapping("/ticket/{id}/addComment")
    public String addComment(@PathVariable(value = "id") long id, 
                             @RequestParam("content") String content,
                             @RequestParam("userName") String userName) {
        ticketService.addComment(id, content, userName);
        return "redirect:/ticket/" + id;
    }
    
    @PostMapping("/ticket/{id}/uploadAttachment")
    public String uploadAttachment(@PathVariable(value = "id") long id,
                                   @RequestParam("file") MultipartFile file,
                                   @RequestParam("uploadedBy") String uploadedBy) {
        if (!file.isEmpty()) {
            try {
                // Create upload directory if it doesn't exist
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                
                // Save file to upload directory
                Path filePath = uploadPath.resolve(file.getOriginalFilename());
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                
                // Save attachment info to database
                ticketService.addAttachment(id, file.getOriginalFilename(), file.getContentType(), 
                                          file.getSize(), filePath.toString(), uploadedBy);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/ticket/" + id;
    }
    
    @GetMapping("/download/{attachmentId}")
    public ResponseEntity<Resource> downloadAttachment(@PathVariable Long attachmentId) throws IOException {
        // In a real application, you would retrieve the attachment from the database
        // and stream the file from the file system or cloud storage
        // This is a simplified implementation
        return ResponseEntity.notFound().build();
    }
}