package by.training.info_system.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class BlackList implements Serializable {
    private User user;
    private String reason;
    private Date lockDate;
    private Date unlockDate;
}
