package org.example.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class Event {
    private String name;
    private Date date;
    private Integer places;
}
