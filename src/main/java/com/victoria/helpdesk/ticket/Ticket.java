package com.victoria.helpdesk.ticket;
import jakarta.persistence.*;
import java.time.Instant;
@Entity @Table(name="tickets")
public class Ticket {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(nullable=false,length=140) private String title;
  @Column(nullable=false,length=3000) private String description;
  @Column(nullable=false,length=120) private String requester;
  @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private TicketStatus status=TicketStatus.OPEN;
  @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private TicketPriority priority=TicketPriority.MEDIUM;
  @Column(nullable=false,updatable=false) private Instant createdAt;
  @Column(nullable=false) private Instant updatedAt;
  protected Ticket() {}
  public Ticket(String title,String description,String requester,TicketPriority priority) { this.title=title;this.description=description;this.requester=requester;this.priority=priority==null?TicketPriority.MEDIUM:priority; }
  @PrePersist void onCreate(){createdAt=Instant.now();updatedAt=createdAt;}
  @PreUpdate void onUpdate(){updatedAt=Instant.now();}
  public Long getId(){return id;} public String getTitle(){return title;} public String getDescription(){return description;}
  public String getRequester(){return requester;} public TicketStatus getStatus(){return status;} public TicketPriority getPriority(){return priority;}
  public Instant getCreatedAt(){return createdAt;} public Instant getUpdatedAt(){return updatedAt;}
  public void update(String title,String description,String requester,TicketStatus status,TicketPriority priority){
    if(title!=null)this.title=title;if(description!=null)this.description=description;if(requester!=null)this.requester=requester;
    if(status!=null)this.status=status;if(priority!=null)this.priority=priority;
  }
}