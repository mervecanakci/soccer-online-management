package com.turkcell.socceronlinemanagement.service.team;

import com.github.javafaker.Address;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamRequest {

    @Min(0)
    private int userId;

   @Min(0)
    private int leagueId;

    @NotNull
    @Length(min = 2, message = "Length must be greater than 2!")
    private String teamName;

    @NotNull
    private String teamCountry;

    //@Value("${team.value:5000000}")
  //  private double teamValue = 5000000.0; //todo: Takım değeri (oyuncu değerlerinin toplamı)

        /*
         @Override
    public CreateOrderResponse add(CreateOrderRequest request) {
        Order order = new Order();
        order.setProductId(request.getProductId());
        order.setPrice(request.getPrice());
        order.setQuantity(request.getQuantity());
        order.setCurrency(request.getCurrency());
        order.setId(0);
        order.setCustomer(mapper.map(customerService.getById(request.getCustomerId()), Customer.class));

        repository.save(order);
        productService.changeStatus(order.getProductId(), Status.UNAVAILABLE);

        CreateOrderResponse response = new CreateOrderResponse();
        response.setProductId(order.getProductId());
        response.setPrice(order.getPrice());
        response.setTotalPrice(getTotalPrice(order));
        response.setQuantity(order.getQuantity());
        response.setCurrency(order.getCurrency());
        response.setCustomerId(order.getCustomer().getId());
        response.setId(order.getId());
        return response;

         */
}
