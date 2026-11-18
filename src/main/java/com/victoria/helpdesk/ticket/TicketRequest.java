package com.victoria.helpdesk.ticket;
import jakarta.validation.constraints.*;
public record TicketRequest(@NotBlank @Size(max=140) String title,@NotBlank @Size(max=3000) String description,@NotBlank @Size(max=120) String requester,TicketPriority priority) {}