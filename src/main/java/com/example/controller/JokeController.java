package com.example.controller;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Joke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.repository.JokeRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/v1")
public class JokeController {

  @Autowired
  private JokeRepository jokeRepository;

  @GetMapping("/jokes")
  public List<Joke> getAllJokes(){
    return this.jokeRepository.findAll();
  }

  @GetMapping("/jokes/page")
  public ResponseEntity<List<Joke>> getAllJokesPage(@RequestParam(defaultValue = "0") Integer pageNo,
                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                    @RequestParam(defaultValue = "id") String sortBy){
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
    Page<Joke> pagedResult = jokeRepository.findAll(paging);
    return new ResponseEntity<List<Joke>>(pagedResult.getContent(), new HttpHeaders(), HttpStatus.OK);
  }

  @GetMapping("/jokes/{id}")
  public ResponseEntity<Joke> getJokeById(@PathVariable(value = "id") Long jokeId) throws ResourceNotFoundException {
    Joke joke = jokeRepository.findById(jokeId)
      .orElseThrow(() -> new ResourceNotFoundException("Joke not found for this id :: " + jokeId));
    return ResponseEntity.ok().body(joke);
  }

  @GetMapping("/jokes/search/{keyword}")
  public List<Joke> searchByKeyword(@PathVariable(value = "keyword") String keyword) {
    return this.jokeRepository.findByContentContaining(keyword);
  }

  @GetMapping("/jokes/random")
  public ResponseEntity<Joke> getRandomJoke(){
    List<Joke> jokes = this.jokeRepository.findAll();
    Random random = new Random();
    return ResponseEntity.ok().body(jokes.get(random.nextInt(jokes.size())));
  }

  @PostMapping("/jokes")
  public Joke createJoke(@Valid @RequestBody Joke joke) {
    return jokeRepository.save(joke);
  }

  @PutMapping("/jokes/{id}")
  public ResponseEntity<Joke> updateJoke(@PathVariable(value = "id") Long jokeId, @Valid @RequestBody Joke jokeData) throws ResourceNotFoundException {
    Joke joke = jokeRepository.findById(jokeId).orElseThrow(() -> new ResourceNotFoundException("Joke not found for this id :: " + jokeId));
    joke.setId(jokeId);
    joke.setContent(jokeData.getContent());
    final Joke updatedJoke = jokeRepository.save(joke);
    return ResponseEntity.ok(updatedJoke);
  }

  @DeleteMapping("/jokes/{id}")
  public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long jokeId) throws ResourceNotFoundException {
    Joke joke = jokeRepository.findById(jokeId).orElseThrow(() -> new ResourceNotFoundException("Joke not found for this id :: " + jokeId));
    jokeRepository.delete(joke);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }
}
