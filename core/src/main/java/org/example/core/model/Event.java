package org.example.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class Event {
    private Integer id;
    private String name;
    private Date date;
    private Integer places;
}
