package com.example.repository;

import com.example.model.Joke;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JokeRepository extends JpaRepository<Joke, Long> {
  Page<Joke> findAll(Pageable pageable);
  List<Joke> findByContentContaining(String content);

}
