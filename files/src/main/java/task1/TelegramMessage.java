package task1;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Arrays;


@Getter
public class TelegramMessage extends Message implements Serializable {
    private final String[] emoji;

    TelegramMessage (String sender, String receiver, String body, String[] emoji){
        super(sender, receiver, body);
        this.emoji = emoji;
    }

    @Override
    public String toString() {
        return "TelegramMessage{" +
                "sender='" + super.getSender() + '\'' +
                ", receiver='" + super.getReceiver() + '\'' +
                ", body='" + super.getBody() + '\'' +
                ", emoji='" + Arrays.toString(emoji) + '\'' +
                '}';
    }
}
