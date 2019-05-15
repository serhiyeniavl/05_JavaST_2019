package by.training.info_system.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class BlackListNode implements Serializable {
    private User user;
    private String reason;
    private LocalDate lockDate;
    private LocalDate unlockDate;

    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public String showLockDate() {
        return lockDate.format(FORMATTER);
    }

    public String showUnlockDate() {
        return unlockDate.format(FORMATTER);
    }
}
