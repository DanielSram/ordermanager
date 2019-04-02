package cz.vse.ordermanager.order;

import lombok.AllArgsConstructor;
import org.springframework.util.Assert;

import java.util.Set;

@AllArgsConstructor
public enum OrderState {

    CANCELLED(Set.of()),
    DONE(Set.of()),
    SENT(Set.of(DONE)),
    PAID(Set.of(SENT, CANCELLED)),
    CREATED(Set.of(PAID, CANCELLED));

    private final Set<OrderState> allowedTransitions;

    public void validateTransition(OrderState state) {
        Assert.isTrue(allowedTransitions.contains(state), "Invalid Order state transition!");
    }
}
