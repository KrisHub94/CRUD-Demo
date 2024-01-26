package com.crudbros.demo.model;

import com.crudbros.demo.data.Status;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Task {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String description;
    private LocalDate start;
    private LocalDate deadline;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
