package com.gorshkov.movieland.controller;

import com.gorshkov.movieland.model.Poster;
import com.gorshkov.movieland.service.PosterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.gorshkov.movieland.util.Parser.parsePoster;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/poster")
public class PosterController {

    private final PosterService posterService;

    @GetMapping("/add")
    public Iterable<Poster> addPosters() {
        List<Poster> posters = parsePoster();
        return posterService.saveAll(posters);
    }

    @GetMapping()
    public Iterable<Poster> getAll(Model model) {
        Iterable<Poster> posters = posterService.getAll();
        model.addAttribute("posters", posters);
        return posters;
    }
}
