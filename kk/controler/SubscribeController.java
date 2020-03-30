package com.hw54.kk.controler;

import com.hw54.kk.Annatations.ApiPageable;
import com.hw54.kk.DTO.EventDTO;
import com.hw54.kk.DTO.SubscribeDTO;
import com.hw54.kk.service.SubscribeServ;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/subscribes")
public class SubscribeController {

    private final SubscribeServ subscribeService;

    public SubscribeController(SubscribeServ subscribeService) {
        this.subscribeService = subscribeService;
    }

    @ApiPageable
    @GetMapping
    public Slice<SubscribeDTO> getAllSubscribes(@ApiIgnore Pageable pageable) {
        return subscribeService.findAllSubscribes(pageable);
    }

    @ApiPageable
    @GetMapping("/{userEmail}")
    public List<EventDTO> getUserSubscribes(@PathVariable("userEmail") String userEmail, @ApiIgnore Pageable pageable) {
        return subscribeService.findAllUserEvents(userEmail, pageable);
    }

    @DeleteMapping("/{subscribeId}/{userEmail}")
    public String deleteSubscribe(@PathVariable("subscribeId") String subscribeId, @PathVariable("userEmail") String userEmail) {
        return subscribeService.deleteSubscribe(subscribeId, userEmail);
    }
}
