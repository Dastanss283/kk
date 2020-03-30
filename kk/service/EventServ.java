package com.hw54.kk.service;

import com.hw54.kk.DTO.EventDTO;
import com.hw54.kk.model.Event;
import com.hw54.kk.repository.EventRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class EventServ {
    private final EventRepository eventRepository;
    private final SubscribeServ subscribeService;

    public EventServ(EventRepository eventRepository, SubscribeServ subscribeService) {
        this.eventRepository = eventRepository;
        this.subscribeService = subscribeService;
    }

    public Slice<EventDTO> findAllEvents(Pageable pageable) {
        var slice = eventRepository.findAll(pageable);
        return slice.map(EventDTO::from);
    }

    public boolean isSubscribeable(String eventId) {
        var event = eventRepository.findById(eventId).get();
        boolean e = event.getDate().isAfter(LocalDate.now());
        return e;
    }

    public String subscribeToEvent(String eventId, String userEmail) {
        String msg = "";
        if(isSubscribeable(eventId)) {
            Event event = eventRepository.findById(eventId).get();
            msg = subscribeService.addSubscribe(event, userEmail, LocalDate.now());
        } else {
            msg = "you are late event passed";
        }
        return msg;
    }
}
