package com.hw54.kk.repository;

import com.hw54.kk.model.Event;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EventRepository extends PagingAndSortingRepository<Event, String> {
}
