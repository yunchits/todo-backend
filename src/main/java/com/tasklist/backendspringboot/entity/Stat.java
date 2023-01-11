package com.tasklist.backendspringboot.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class Stat {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "completed_total")
    private Long completedTotal;

    @Basic
    @Column(name = "uncompleted_total")
    private Long uncompletedTotal;

}
