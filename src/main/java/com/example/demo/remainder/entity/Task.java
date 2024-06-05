package com.example.demo.remainder.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "task")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "task_id_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "code", unique = true, nullable = false, length = 7)
    private String code;

    @Column(name = "status")
    private String status;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "fixed_delay")
    private Long fixedDelay;

    @Column(name = "cron", length = 64)
    private String cron;

    @Column(name = "notes")
    private String notes;

    @Column(name = "exe_count")
    private Long executionCount;

}
