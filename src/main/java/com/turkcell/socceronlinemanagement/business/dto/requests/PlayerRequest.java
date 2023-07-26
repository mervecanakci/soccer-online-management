package com.turkcell.socceronlinemanagement.business.dto.requests;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRequest {
    private final Faker faker = new Faker();
    private int teamId;
    private double marketValue;

    // Rastgele isim almak için
    private String getRandomName() {
        return faker.name().firstName();
    }

    // Rastgele soyisim almak için
    private String getRandomLastName() {
        return faker.name().lastName();
    }

    // Rastgele ülke almak için
    private String getRandomCountry() {
        return faker.address().country();
    }

    // Rastgele yaş almak için
    private int getRandomAge() {
        return faker.number().numberBetween(18, 49);
    }

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



