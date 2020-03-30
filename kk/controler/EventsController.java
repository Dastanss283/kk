package com.hw54.kk.controler;

import com.hw54.kk.Annatations.ApiPageable;
import com.hw54.kk.DTO.EventDTO;
import com.hw54.kk.service.EventServ;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@RequestMapping("/events")
public class EventsController {
    private final EventServ eventService;

    public EventsController(EventServ eventService) {
        this.eventService = eventService;
    }

    @ApiPageable
    @GetMapping
    public Slice<EventDTO> getAllEvents(@ApiIgnore Pageable pageable) {
        return eventService.findAllEvents(pageable);
    }

    @PutMapping("/{eventId}/{userEmail}")
    public String subscribeToEvent(@PathVariable("eventId") String eventId,
                                   @PathVariable("userEmail") String userEmail) {
        return eventService.subscribeToEvent(eventId, userEmail);
    }
}
