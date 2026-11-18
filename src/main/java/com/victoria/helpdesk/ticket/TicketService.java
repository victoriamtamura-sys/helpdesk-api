package com.victoria.helpdesk.ticket;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
@Service
public class TicketService {
  private final TicketRepository repository;
  public TicketService(TicketRepository repository){this.repository=repository;}
  public List<Ticket> findAll(TicketStatus status){return status==null?repository.findAll():repository.findByStatus(status);}
  public Ticket findById(Long id){return repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Ticket not found"));}
  public Ticket create(TicketRequest request){return repository.save(new Ticket(request.title(),request.description(),request.requester(),request.priority()));}
  public Ticket update(Long id,TicketUpdateRequest request){Ticket ticket=findById(id);ticket.update(request.title(),request.description(),request.requester(),request.status(),request.priority());return repository.save(ticket);}
  public void delete(Long id){Ticket ticket=findById(id);repository.delete(ticket);}
}