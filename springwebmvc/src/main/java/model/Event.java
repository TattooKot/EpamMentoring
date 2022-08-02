package model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Event implements EventInterface{
    private long id;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
