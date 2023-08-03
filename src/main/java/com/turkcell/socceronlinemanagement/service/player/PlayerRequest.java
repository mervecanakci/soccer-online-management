package com.turkcell.socceronlinemanagement.service.player;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.javafaker.Faker;
import com.turkcell.socceronlinemanagement.model.enums.Position;
import com.turkcell.socceronlinemanagement.model.enums.TransferState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRequest {
    @JsonIgnore
    private final Faker faker = new Faker();

    private int teamId;
    @JsonIgnore
    private double marketValue = 1000000.0;
    @JsonIgnore
    public String getRandomFirstName() {
        return faker.name().firstName();
    }
    @JsonIgnore
    public String getRandomLastName() {
        return faker.name().lastName();
    }
    @JsonIgnore
    public String getRandomCountry() {
        return faker.address().country();
    }
    @JsonIgnore
    public int getRandomAge() {
        return faker.number().numberBetween(18, 49);
    }
@JsonIgnore
    public Position getRandomPosition() {
        return faker.options().option(Position.values());

    }
    @JsonIgnore
    public TransferState transferState;

}
