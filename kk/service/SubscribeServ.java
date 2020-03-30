package com.hw54.kk.service;


import com.hw54.kk.DTO.EventDTO;
import com.hw54.kk.DTO.SubscribeDTO;
import com.hw54.kk.model.Event;
import com.hw54.kk.model.Subscribe;
import com.hw54.kk.repository.SubscribeRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubscribeServ {
    private final SubscribeRepository subscribeRepository;

    public SubscribeServ(SubscribeRepository subscribeRepository) {
        this.subscribeRepository = subscribeRepository;
    }

    public String addSubscribe(Event event, String userEmail, LocalDate date) {
        String msg = "";
        Subscribe s = subscribeRepository.findByEmailAndEventId(userEmail, event.getId());
        if(s != null) {
            msg = "You have already subscribed to: " + s.getEvent().getName() + " Subscribe ID: " + s.getId();
        } else {
            s = Subscribe.make(event, userEmail, date);
            msg = "You are subscribed to: " + s.getEvent().getName() + " Subscribe ID: " + s.getId();
        }
        subscribeRepository.save(s);
        return msg;
    }

    public Slice<SubscribeDTO> findAllSubscribes(Pageable pageable) {
        var slice = subscribeRepository.findAll(pageable);
        return slice.map(SubscribeDTO::from);
    }

    public List<EventDTO> findAllUserEvents(String userEmail, Pageable pageable) {
        var slice = subscribeRepository.findAllByEmail(userEmail, pageable);
        List<EventDTO> userEvents = new ArrayList<>();
        slice.forEach(s -> userEvents.add(EventDTO.from(s.getEvent())));
        return userEvents;
    }

    public String deleteSubscribe(String subscribeId, String userEmail) {
        String msg = "";
        if(subscribeRepository.existsByIdAndEmail(subscribeId, userEmail)) {
            subscribeRepository.deleteByIdAndEmail(subscribeId, userEmail);
            msg = subscribeId + " unsubscribed by " + userEmail;
        } else {
            msg = subscribeId + " not found";
        }
        return msg;
    }
}