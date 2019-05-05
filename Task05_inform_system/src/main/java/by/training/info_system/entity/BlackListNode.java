package by.training.info_system.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

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
}
