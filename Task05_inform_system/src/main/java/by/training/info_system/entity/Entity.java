package by.training.info_system.entity;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Entity {
    @NonNull
    @EqualsAndHashCode.Exclude
    private Long id;
}
