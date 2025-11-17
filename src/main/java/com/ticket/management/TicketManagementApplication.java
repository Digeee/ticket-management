package com.ticket.management;

import com.ticket.management.model.Ticket;
import com.ticket.management.repository.TicketRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TicketManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketManagementApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner loadData(TicketRepository ticketRepository) {
		return args -> {
			// Create sample tickets
			Ticket ticket1 = new Ticket("Login Issue", "Users are unable to login to the application", 
				Ticket.Priority.HIGH, Ticket.Status.OPEN, "John Doe");
			ticketRepository.save(ticket1);
			
			Ticket ticket2 = new Ticket("Performance Problem", "Application is running slowly during peak hours", 
				Ticket.Priority.MEDIUM, Ticket.Status.IN_PROGRESS, "Jane Smith");
			ticketRepository.save(ticket2);
			
			Ticket ticket3 = new Ticket("Feature Request", "Add dark mode to the application", 
				Ticket.Priority.LOW, Ticket.Status.RESOLVED, "Mike Johnson");
			ticketRepository.save(ticket3);
			
			Ticket ticket4 = new Ticket("Bug in Payment Module", "Payment processing fails for certain card types", 
				Ticket.Priority.URGENT, Ticket.Status.OPEN, "Sarah Wilson");
			ticketRepository.save(ticket4);
			
			Ticket ticket5 = new Ticket("UI Enhancement", "Improve the dashboard layout", 
				Ticket.Priority.MEDIUM, Ticket.Status.CLOSED, "David Brown");
			ticketRepository.save(ticket5);
		};
	}

}