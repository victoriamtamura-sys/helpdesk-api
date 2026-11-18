package com.victoria.helpdesk.ticket;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/v1/tickets")
public class TicketController {
  private final TicketService service;
  public TicketController(TicketService service){this.service=service;}
  @GetMapping public List<Ticket> list(@RequestParam(required=false) TicketStatus status){return service.findAll(status);}
  @GetMapping("/{id}") public Ticket get(@PathVariable Long id){return service.findById(id);}
  @PostMapping @ResponseStatus(HttpStatus.CREATED) public Ticket create(@Valid @RequestBody TicketRequest request){return service.create(request);}
  @PatchMapping("/{id}") public Ticket update(@PathVariable Long id,@Valid @RequestBody TicketUpdateRequest request){return service.update(id,request);}
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){service.delete(id);}
}