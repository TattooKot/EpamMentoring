package model;

import lombok.Data;

import java.util.Date;

@Data
public class DBFile {
    private Integer id;
    private String name;
    private String path;
    private Date exp;
}
