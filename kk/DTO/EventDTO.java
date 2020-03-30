package com.hw54.kk.DTO;

import com.hw54.kk.model.Event;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class EventDTO {

    private String id;
    private String name;
    private String description;
    private LocalDate date;

    public static EventDTO from(Event e) {
        return builder().id(e.getId()).name(e.getName()).description(e.getDescription()).date(e.getDate()).build();
    }
}
