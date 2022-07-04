package task1;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Message implements Serializable {
    private static Long id;
    private String sender;
    private String receiver;
    private String body;
}