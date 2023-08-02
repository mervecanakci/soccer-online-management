package com.turkcell.socceronlinemanagement.api;

import com.turkcell.socceronlinemanagement.service.transfer.TransferRequest;
import com.turkcell.socceronlinemanagement.service.transfer.TransferResponse;
import com.turkcell.socceronlinemanagement.service.transfer.TransferService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/transfers")
public class TransferController {
    private final TransferService service;
    @GetMapping
    public List<TransferResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public TransferResponse getById(@PathVariable int id) {
        return service.getById(id);
    }
  //***  Player player = new Player();
    @PostMapping
   // @PostAuthorize("hasRole('ADMIN') || returnObject.teamId == player.teamId")
    //***    @PreAuthorize("hasRole('ADMIN') || #result.teamId == #player.teamId") //todo
//    # sembolü, Spring Expression Language (SpEL) içinde kullanılan bir özelliktir.
//    # sembolü, SpEL ifadesinde metot parametrelerine veya dönüş değerine referans vermek için kullanılıyor.
    @ResponseStatus(HttpStatus.CREATED)
    public TransferResponse add(@Valid @RequestBody TransferRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    //***    @PreAuthorize("hasRole('ADMIN') || #result.teamId == #player.teamId")
    public TransferResponse update(@PathVariable int id, @RequestBody TransferRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    //***   @PreAuthorize("hasRole('ADMIN') || #result.teamId == #player.teamId")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

}

