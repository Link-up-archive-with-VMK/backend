package com.guide.run.event.service;

import com.guide.run.event.entity.Event;
import com.guide.run.event.entity.EventForm;
import com.guide.run.event.entity.dto.response.get.AllEvent;
import com.guide.run.event.entity.repository.EventFormRepository;
import com.guide.run.event.entity.repository.EventRepository;
import com.guide.run.event.entity.type.EventRecruitStatus;
import com.guide.run.event.entity.type.EventType;
import com.guide.run.global.exception.event.logic.NotValidKindException;
import com.guide.run.global.exception.event.logic.NotValidSortException;
import com.guide.run.global.exception.event.logic.NotValidTypeException;
import com.guide.run.global.exception.event.resource.NotExistEventException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AllEventGetService {
    private final EventRepository eventRepository;
    private final EventFormRepository eventFormRepository;
/*
    public List<AllEvent> getAllEvent(int start, int limit, String sort, String type, String kind, String privateId) {
        Pageable pageable = PageRequest.of(start/limit,limit);
        List<AllEvent> events = new ArrayList<>();
        Page found;
        switch (kind){
            case "UPCOMING":
                if(sort.equals("TOTAL")){
                    if(type.equals("TOTAL")) {
                        found = eventRepository.
                                findAllByRecruitStatusNotOrderByEndTime(EventRecruitStatus.RECRUIT_END, pageable);
                    }
                    else if(type.equals("TRAINING") || type.equals("COMPETITION")) {
                        found = eventRepository.
                                findAllByTypeAndRecruitStatusNotOrderByEndTime(EventType.valueOf(type), EventRecruitStatus.RECRUIT_END, pageable);
                    }
                    else
                        throw new NotValidTypeException();
                    allResponseEventCreate(privateId,events,found);
                }
                else if(sort.equals("UPCOMING")){
                    if(type.equals("TOTAL")) {
                        found = eventRepository.
                                findAllByRecruitStatusOrderByEndTime(EventRecruitStatus.RECRUIT_UPCOMING, pageable);
                    }
                    else if(type.equals("TRAINING") || type.equals("COMPETITION")) {
                        found = eventRepository.
                                findAllByTypeAndRecruitStatusOrderByEndTime(EventType.valueOf(type), EventRecruitStatus.RECRUIT_UPCOMING, pageable);
                    }
                    else
                        throw new NotValidTypeException();
                    allResponseEventCreate(privateId,events,found);
                }
                else if(sort.equals("OPEN")){
                    if(type.equals("TOTAL")) {
                        found = eventRepository.
                                findAllByRecruitStatusOrderByEndTime(EventRecruitStatus.RECRUIT_OPEN, pageable);
                    }
                    else if(type.equals("TRAINING") || type.equals("COMPETITION")) {
                        found = eventRepository.
                                findAllByTypeAndRecruitStatusOrderByEndTime(EventType.valueOf(type), EventRecruitStatus.RECRUIT_OPEN, pageable);
                    }
                    else
                        throw new NotValidTypeException();
                    allResponseEventCreate(privateId,events,found);
                }
                else if(sort.equals("CLOSE")){
                    if(type.equals("TOTAL")) {
                        found = eventRepository.
                                findAllByRecruitStatusOrderByEndTime(EventRecruitStatus.RECRUIT_CLOSE, pageable);
                        allResponseEventCreate(privateId,events,found);
                    }
                    else if(type.equals("TRAINING") || type.equals("COMPETITION")) {
                        found = eventRepository.
                                findAllByTypeAndRecruitStatusOrderByEndTime(EventType.valueOf(type), EventRecruitStatus.RECRUIT_CLOSE, pageable);
                        allResponseEventCreate(privateId,events,found);
                    }
                    else
                        throw new NotValidTypeException();
                }
                else
                    throw new NotValidSortException();
                return events;
            case "MY":
                if(sort.equals("TOTAL")){
                    if(type.equals("TOTAL")) {
                        found = eventFormRepository.findAllByPrivateIdOrderByEndTime(privateId, pageable);
                    }
                    else if(type.equals("TRAINING") || type.equals("COMPETITION")){
                        found = eventFormRepository.
                                findAllByPrivateIdAndEventTypeOrderByEndTime(privateId, EventType.valueOf(type), pageable);
                    }
                    else
                        throw new NotValidTypeException();
                    myEventResponseCreate(events,found);
                }
                else if(sort.equals("UPCOMING")){
                    if(type.equals("TOTAL")) {
                        found = eventFormRepository.
                                findAllByPrivateIdAndRecruitStatusOrderByEndTime(privateId, EventRecruitStatus.RECRUIT_UPCOMING, pageable);
                    }
                    else if(type.equals("TRAINING") || type.equals("COMPETITION")){
                        found = eventFormRepository.
                                findAllByPrivateIdAndRecruitStatusAndEventTypeOrderByEndTime(privateId,
                                        EventRecruitStatus.RECRUIT_UPCOMING, EventType.valueOf(type), pageable);
                    }
                    else
                        throw new NotValidTypeException();
                    myEventResponseCreate(events,found);
                }
                else if(sort.equals("OPEN")){
                    if(type.equals("TOTAL")) {
                        found = eventFormRepository.
                                findAllByPrivateIdAndRecruitStatusOrderByEndTime(privateId, EventRecruitStatus.RECRUIT_OPEN, pageable);
                    }
                    else if(type.equals("TRAINING") || type.equals("COMPETITION")){
                        found = eventFormRepository.
                                findAllByPrivateIdAndRecruitStatusAndEventTypeOrderByEndTime(privateId,
                                        EventRecruitStatus.RECRUIT_OPEN, EventType.valueOf(type), pageable);
                    }
                    else
                        throw new NotValidTypeException();
                    myEventResponseCreate(events,found);
                }
                else if(sort.equals("CLOSE")){
                    if(type.equals("TOTAL")) {
                        found = eventFormRepository.
                                findAllByPrivateIdAndRecruitStatusOrderByEndTime(privateId, EventRecruitStatus.RECRUIT_CLOSE, pageable);
                    }
                    else if(type.equals("TRAINING") || type.equals("COMPETITION")){
                        found = eventFormRepository.
                                findAllByPrivateIdAndRecruitStatusAndEventTypeOrderByEndTime(privateId,
                                        EventRecruitStatus.RECRUIT_CLOSE, EventType.valueOf(type), pageable);
                    }
                    else
                        throw new NotValidTypeException();
                    myEventResponseCreate(events,found);
                }
                else if(sort.equals("END")){
                    if(type.equals("TOTAL")) {
                        found = eventFormRepository.
                                findAllByPrivateIdAndRecruitStatusOrderByEndTime(privateId, EventRecruitStatus.RECRUIT_END, pageable);
                    }
                    else if(type.equals("TRAINING") || type.equals("COMPETITION")){
                        found = eventFormRepository.
                                findAllByPrivateIdAndRecruitStatusAndEventTypeOrderByEndTime(privateId,
                                        EventRecruitStatus.RECRUIT_END, EventType.valueOf(type), pageable);
                    }
                    else
                        throw new NotValidTypeException();
                    myEventResponseCreate(events,found);
                }
                else
                    throw new NotValidSortException();
                return events;
            case "END":
                if(type.equals("TOTAL")) {
                    found = eventRepository.findAllByRecruitStatusOrderByEndTimeDesc(EventRecruitStatus.RECRUIT_END, pageable);
                }
                else if(type.equals("TRAINING") || type.equals("COMPETITION")){
                    found = eventRepository.findAllByTypeAndRecruitStatusOrderByEndTimeDesc(EventType.valueOf(type), EventRecruitStatus.RECRUIT_END, pageable);
                }
                else
                    throw new NotValidTypeException();
                allResponseEventCreate(privateId, events, found);
                return events;
            default:
                throw new NotValidKindException();
        }
    }

    private void myEventResponseCreate(List<AllEvent> events, Page<EventForm> found) {
        for(EventForm ef : found){
            Event e = eventRepository.findById(ef.getEventId()).orElseThrow(() -> new NotExistEventException());
            events.add(AllEvent.builder()
                    .eventId(e.getId())
                    .eventType(e.getType())
                    .name(e.getName())
                    .isApply(true)
                    .endDate(e.getEndTime().toLocalDate())
                    .recruitStatus(e.getRecruitStatus())
                    .build());
        }
    }

    private void allResponseEventCreate(String privateId, List<AllEvent> events, Page<Event> found) {
        for(Event e: found){
            EventForm eventForm = eventFormRepository.findByEventIdAndPrivateId(e.getId(), privateId);
            if(eventForm!=null) {
                allEventCreate(events, e,true);
            }else{
                allEventCreate(events, e,false);
            }
        }
    }

    private void allEventCreate(List<AllEvent> events, Event e, Boolean isApply) {
        events.add(AllEvent.builder()
                .eventId(e.getId())
                .eventType(e.getType())
                .name(e.getName())
                .isApply(isApply)
                .endDate(e.getEndTime().toLocalDate())
                .recruitStatus(e.getRecruitStatus())
                .build());
    }

    public int getEventsListCount(String sort, String type, String kind, String privateId){
        switch (kind){
            case "UPCOMING":
                if(sort.equals("TOTAL")){
                    if(type.equals("TOTAL")) {
                        return eventRepository.
                                findAllByRecruitStatusNot(EventRecruitStatus.RECRUIT_END).size();
                    }
                    else if(type.equals("TRAINING") || type.equals("COMPETITION")) {
                        return eventRepository.
                                findAllByTypeAndRecruitStatusNot(EventType.valueOf(type), EventRecruitStatus.RECRUIT_END).size();
                    }
                    else
                        throw new NotValidTypeException();
                }
                else if(sort.equals("UPCOMING")){
                    if(type.equals("TOTAL")) {
                        return eventRepository.
                                findAllByRecruitStatus(EventRecruitStatus.RECRUIT_UPCOMING).size();
                    }
                    else if(type.equals("TRAINING") || type.equals("COMPETITION")) {
                        return eventRepository.
                                findAllByTypeAndRecruitStatus(EventType.valueOf(type), EventRecruitStatus.RECRUIT_UPCOMING).size();
                    }
                    else
                        throw new NotValidTypeException();
                }
                else if(sort.equals("OPEN")){
                    if(type.equals("TOTAL")) {
                        return eventRepository.
                                findAllByRecruitStatus(EventRecruitStatus.RECRUIT_OPEN).size();
                    }
                    else if(type.equals("TRAINING") || type.equals("COMPETITION")) {
                        return eventRepository.
                                findAllByTypeAndRecruitStatus(EventType.valueOf(type), EventRecruitStatus.RECRUIT_OPEN).size();
                    }
                    else
                        throw new NotValidTypeException();
                }
                else if(sort.equals("CLOSE")){
                    if(type.equals("TOTAL")) {
                        return eventRepository.
                                findAllByRecruitStatus(EventRecruitStatus.RECRUIT_CLOSE).size();
                    }
                    else if(type.equals("TRAINING") || type.equals("COMPETITION")) {
                        return eventRepository.
                                findAllByTypeAndRecruitStatus(EventType.valueOf(type), EventRecruitStatus.RECRUIT_CLOSE).size();
                    }
                    else
                        throw new NotValidTypeException();
                }
                else
                    throw new NotValidSortException();


            case "MY":
                if(sort.equals("TOTAL")){
                    if(type.equals("TOTAL")) {
                        return eventFormRepository.findAllByPrivateId(privateId).size();
                    }
                    else if(type.equals("TRAINING") || type.equals("COMPETITION")){
                        return eventFormRepository.
                                findAllByPrivateIdAndEventType(privateId, EventType.valueOf(type)).size();
                    }
                    else
                        throw new NotValidTypeException();
                }
                else if(sort.equals("UPCOMING")){
                    if(type.equals("TOTAL")) {
                        return eventFormRepository.
                                findAllByPrivateIdAndRecruitStatus(privateId, EventRecruitStatus.RECRUIT_UPCOMING).size();
                    }
                    else if(type.equals("TRAINING") || type.equals("COMPETITION")){
                        return eventFormRepository.
                                findAllByPrivateIdAndRecruitStatusAndEventType(privateId,
                                        EventRecruitStatus.RECRUIT_UPCOMING, EventType.valueOf(type)).size();
                    }
                    else
                        throw new NotValidTypeException();
                }
                else if(sort.equals("OPEN")){
                    if(type.equals("TOTAL")) {
                        return eventFormRepository.
                                findAllByPrivateIdAndRecruitStatus(privateId, EventRecruitStatus.RECRUIT_OPEN).size();
                    }
                    else if(type.equals("TRAINING") || type.equals("COMPETITION")){
                        return eventFormRepository.
                                findAllByPrivateIdAndRecruitStatusAndEventType(privateId,
                                        EventRecruitStatus.RECRUIT_OPEN, EventType.valueOf(type)).size();
                    }
                    else
                        throw new NotValidTypeException();
                }
                else if(sort.equals("CLOSE")){
                    if(type.equals("TOTAL")) {
                        return eventFormRepository.
                                findAllByPrivateIdAndRecruitStatus(privateId, EventRecruitStatus.RECRUIT_CLOSE).size();
                    }
                    else if(type.equals("TRAINING") || type.equals("COMPETITION")){
                        return eventFormRepository.
                                findAllByPrivateIdAndRecruitStatusAndEventType(privateId,
                                        EventRecruitStatus.RECRUIT_CLOSE, EventType.valueOf(type)).size();
                    }
                    else
                        throw new NotValidTypeException();
                }
                else if(sort.equals("END")){
                    if(type.equals("TOTAL")) {
                        return eventFormRepository.
                                findAllByPrivateIdAndRecruitStatus(privateId, EventRecruitStatus.RECRUIT_END).size();
                    }
                    else if(type.equals("TRAINING") || type.equals("COMPETITION")){
                        return eventFormRepository.
                                findAllByPrivateIdAndRecruitStatusAndEventType(privateId,
                                        EventRecruitStatus.RECRUIT_END, EventType.valueOf(type)).size();
                    }
                    else
                        throw new NotValidTypeException();
                }
                else
                    throw new NotValidSortException();

            case "END":
                if(type.equals("TOTAL")) {
                    return eventRepository.findAllByRecruitStatus(EventRecruitStatus.RECRUIT_END).size();
                }
                else if(type.equals("TRAINING") || type.equals("COMPETITION")){
                    return eventRepository.findAllByTypeAndRecruitStatus(EventType.valueOf(type), EventRecruitStatus.RECRUIT_END).size();
                }
                else
                    throw new NotValidTypeException();
            default:
                throw new NotValidKindException();
        }
    }*/
}
