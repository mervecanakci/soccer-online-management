package com.turkcell.socceronlinemanagement.service.team;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.javafaker.Faker;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamRequest {
    @JsonIgnore
    private final Faker faker = new Faker();

    private int userId;

    @JsonIgnore
    public String getRandomTeamName() {
        return faker.team().name();
//        return faker.team().name();
    }

    @JsonIgnore
    private double teamValue = 5000000.0;

    @JsonIgnore
    public String getRandomCountry() {
        return faker.address().country();
    }

}
