package com.turkcell.socceronlinemanagement.service.player;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.turkcell.socceronlinemanagement.model.enums.Position;
import com.turkcell.socceronlinemanagement.model.enums.TransferState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRequest {

    private final Faker faker = new Faker();

    private int teamId;

    private double marketValue = 1000000.0;

    public String getRandomFirstName() {
        return faker.name().firstName();
    }

    public String getRandomLastName() {
        return faker.name().lastName();
    }

    public String getRandomCountry() {
        return faker.address().country();
    }

    public int getRandomAge() {
        return faker.number().numberBetween(18, 49);
    }

    public Position getRandomPosition() {
        return faker.options().option(Position.values());

    }
    public TransferState transferState;

}
