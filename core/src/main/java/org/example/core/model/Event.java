package org.example.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class Event implements EventInterface{
    private long id;
    private String title;
    private Date date;
}
