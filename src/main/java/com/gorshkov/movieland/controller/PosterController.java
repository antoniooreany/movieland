package com.gorshkov.movieland.controller;

import com.gorshkov.movieland.model.Poster;
import com.gorshkov.movieland.service.PosterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.gorshkov.movieland.util.FileReaderUtil.getRowsFromUrl;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/posters")
public class PosterController {

    private static final String URL_STRING = "https://trello.com/1/cards/5c7d3c9c8d6ddf776c2d3dde/attachments/5c7d3c9d8d6ddf776c2d3e0d/download/poster.txt";

    private final PosterService posterService;

    @GetMapping("/add")
    public String addPoster() {

        List<String> rows = getRowsFromUrl(URL_STRING);
        List<Poster> posters = new ArrayList<>();

        for (String row : rows) {
            Poster poster = new Poster();
            String[] split = row.split(" https:");
            poster.setMovieName(split[0]);
            poster.setLink("https:" + split[1]);
            posters.add(poster);
            log.info("poster: {}", poster);
            posterService.addPoster(poster);
        }
        return "redirect:/users";
    }

    @GetMapping()
    public Iterable<Poster> getAll(Model model) {
        Iterable<Poster> posters = posterService.getAllPosters();
        model.addAttribute("posters", posters);
        return posters;
    }
}
