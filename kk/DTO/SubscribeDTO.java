package com.hw54.kk.DTO;
import com.hw54.kk.model.Event;
import com.hw54.kk.model.Subscribe;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class SubscribeDTO {
    private String id;
    private Event event;
    private String email;
    private LocalDate date;

    public static SubscribeDTO from(Subscribe s) {
        return builder()
                .id(s.getId())
                .event(s.getEvent())
                .email(s.getEmail())
                .date(s.getDate())
                .build();
    }
}