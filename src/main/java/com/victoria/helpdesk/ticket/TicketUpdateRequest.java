package com.victoria.helpdesk.ticket;
import jakarta.validation.constraints.Size;
public record TicketUpdateRequest(@Size(min=3,max=140) String title,@Size(min=3,max=3000) String description,@Size(min=2,max=120) String requester,TicketStatus status,TicketPriority priority) {}