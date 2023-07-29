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
    private Integer teamId;
    private BigDecimal marketValue = BigDecimal.valueOf(1000000);


    // Rastgele isim almak için
    public String getRandomFirstName() {
        return faker.name().firstName();
    }

    // Rastgele soyisim almak için
    public String getRandomLastName() {
        return faker.name().lastName();
    }

    // Rastgele ülke almak için
    public String getRandomCountry() {
        return faker.address().country();
    }

    // Rastgele yaş almak için
    public int getRandomAge() {
        return faker.number().numberBetween(18, 49);
    }

    public Position getRandomPosition() {
        return faker.options().option(Position.values());

    }

    public TransferState transferState;
    // todo denedin bir daha dene sil
//    public void generateRandomPlayer() {
//        String randomName = getRandomName();
//        String randomLastName = getRandomLastName();
//        String randomCountry = getRandomCountry();
//        int randomAge = getRandomAge();
//
//        System.out.println("Random Name: " + randomName);
//        System.out.println("Random LastName: " + randomLastName);
//        System.out.println("Random Country: " + randomCountry);
//        System.out.println("Random Age: " + randomAge);
//    }

}


//    @Min(0)
//    private int teamId;
//
//    @NotBlank
//    @Pattern(regexp = Regex.Name)
//    private String firstName;
//
//    @NotBlank
//    @Pattern(regexp = Regex.Name)
//    private String lastName;
//
//    @NotBlank
//    private String country;
//
//    @NotBlank
//    @Pattern(regexp = Regex.Age)
//    //message = Messages.Car.PlateNotValid --> yas uyumlu değil vb.
//    private int age; // todo: 18 48 arası
//
//    @NotBlank
//    @Pattern(regexp = Regex.Height)
//    private double height;
//
//    @NotBlank
//    @Pattern(regexp = Regex.Weight)
//    private double weight;
//
//    @NotBlank
//    @Value("${market.value:1000000}")
//    private double marketValue;
//
//    private Position position;  //update de yazmayabilirsin
//



