package com.ticket.management.controller;

import com.ticket.management.model.Ticket;
import com.ticket.management.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TicketController {
    
    @Autowired
    private TicketService ticketService;
    
    // Display list of all tickets
    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Ticket> listTickets = ticketService.getAllTickets();
        model.addAttribute("listTickets", listTickets);
        return "index";
    }
    
    // Show form for creating a new ticket
    @GetMapping("/showNewTicketForm")
    public String showNewTicketForm(Model model) {
        Ticket ticket = new Ticket();
        model.addAttribute("ticket", ticket);
        return "new_ticket";
    }
    
    // Save a new ticket
    @PostMapping("/saveTicket")
    public String saveTicket(@ModelAttribute("ticket") Ticket ticket) {
        ticketService.saveTicket(ticket);
        return "redirect:/";
    }
    
    // Show form for updating a ticket
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Ticket ticket = ticketService.getTicketById(id).orElse(null);
        model.addAttribute("ticket", ticket);
        return "update_ticket";
    }
    
    // Delete a ticket
    @GetMapping("/deleteTicket/{id}")
    public String deleteTicket(@PathVariable(value = "id") long id) {
        this.ticketService.deleteTicket(id);
        return "redirect:/";
    }
    
    // Search tickets
    @GetMapping("/search")
    public String searchTickets(@RequestParam("keyword") String keyword, Model model) {
        List<Ticket> listTickets = ticketService.searchTickets(keyword);
        model.addAttribute("listTickets", listTickets);
        return "index";
    }
}