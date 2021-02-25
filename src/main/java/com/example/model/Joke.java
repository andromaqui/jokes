package com.example.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "jokes")
@EqualsAndHashCode(callSuper = true)
public class Joke extends BaseEntity {
  @Id
  @Schema(accessMode = Schema.AccessMode.READ_ONLY)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "content")
  private String content;
}
