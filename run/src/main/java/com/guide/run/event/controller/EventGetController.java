package com.guide.run.event.controller;


import com.guide.run.event.entity.dto.response.EventCountResponse;
import com.guide.run.event.entity.dto.response.search.AllEvent;
import com.guide.run.event.entity.dto.response.search.MyEventResponse;
import com.guide.run.event.entity.dto.response.search.UpcomingEventResponse;
import com.guide.run.event.entity.repository.EventRepository;
import com.guide.run.event.service.AllEventGetService;
import com.guide.run.event.service.EventGetService;
import com.guide.run.global.exception.user.resource.NotExistUserException;
import com.guide.run.global.jwt.JwtProvider;
import com.guide.run.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"https://guide-run-qa.netlify.app", "https://guiderun.org",
        "https://guide-run.netlify.app","https://www.guiderun.org", "http://localhost:3000"},
        maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/event")
public class EventGetController {
    private final JwtProvider jwtProvider;
    private final EventGetService eventGetService;
    private final UserRepository userRepository;
    private final AllEventGetService allEventGetService;
    private final EventRepository eventRepository;

    @GetMapping("/my")
    public ResponseEntity<MyEventResponse> getMyEventList(@RequestParam("sort") String sort
    , @RequestParam("year") int year, @RequestParam("month") int month, HttpServletRequest request)
    {
        String privateId = jwtProvider.extractUserId(request);
        userRepository.findUserByPrivateId(privateId).
                orElseThrow(() -> new NotExistUserException());
        MyEventResponse myEvents = eventGetService.getMyEvent(sort, year, month, privateId);
        return ResponseEntity.status(200).body(myEvents);
    }
    @GetMapping("/upcoming")
    public ResponseEntity<UpcomingEventResponse> getUpcomingEventList(@RequestParam("sort") String sort,
                                                                    HttpServletRequest request)
    {
        String privateId = jwtProvider.extractUserId(request);
        userRepository.findUserByPrivateId(privateId).
                orElseThrow(() -> new NotExistUserException());
        UpcomingEventResponse upcomingEvents = eventGetService.getUpcomingEvent(sort, privateId);
        return ResponseEntity.status(200).body(upcomingEvents);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AllEvent>> getAllEventList(@RequestParam("start") int start,
                                                          @RequestParam("limit") int limit,
                                                          @RequestParam("sort") String sort,
                                                          @RequestParam("type") String type,
                                                          @RequestParam("kind") String kind,
                                                          HttpServletRequest request)
    {
        String privateId = jwtProvider.extractUserId(request);
        userRepository.findUserByPrivateId(privateId).
                orElseThrow(() -> new NotExistUserException());
        List<AllEvent> allEvents = allEventGetService.getAllEvent(start,limit,sort,type,kind,privateId);
        return ResponseEntity.status(200).body(allEvents);
    }
    @GetMapping("/all/count")
    public ResponseEntity<EventCountResponse> getEventListCount(@RequestParam("sort") String sort,
                                                                @RequestParam("type") String type,
                                                                @RequestParam("kind") String kind,
                                                                HttpServletRequest request)
    {
        String privateId = jwtProvider.extractUserId(request);
        userRepository.findUserByPrivateId(privateId).
                orElseThrow(() -> new NotExistUserException());
        int count = allEventGetService.getEventsListCount(sort,type,kind,privateId);
        return ResponseEntity.status(200).body(EventCountResponse.builder().count(count).build());
    }
}
