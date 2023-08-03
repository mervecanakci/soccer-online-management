package com.turkcell.socceronlinemanagement.service.user;

import com.turkcell.socceronlinemanagement.common.constants.Messages;
import com.turkcell.socceronlinemanagement.core.exceptions.BusinessException;
import com.turkcell.socceronlinemanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //todo
public class UserBusinessRules {
    private final UserRepository repository;

    // private final Team team;
    public void checkIfUserExistsById(final int id) { //todo final ekle hepsine
        if (!repository.existsById(id)) throw new BusinessException(Messages.User.NOT_EXISTS);
    }

//    public void checkIfUserExistsByEmail(String email) {
//        if (repository.existsByEmailIgnoreCase(email)) {
//            throw new BusinessException(Messages.User.EXISTS);
//        }
//    }

//    public void checkIfEmailExists(UserRequest request) {
//        if (repository.existsByEmail(request.getEmail())) {
//            throw new BusinessException(Messages.User.EMAIL_ALREADY_EXISTS);
//        }
//    }
/**
 *  çok saçma bir kod yazdın düzelt
 */
/*
 public Team createTeam(User user) {

        team.setCountry(user.getCountry());
        team.setTeamValue(5000000); // Başlangıçta takım değeri 5000000 olarak ayarlanır nasıl dolar yapıcan? ekrana yazdırırken evet
        team.setPlayers(generatePlayers()); // Oyuncuları oluştur

        return team;
    }

    private List<Player> generatePlayers() {
        List<Player> players = new ArrayList<>();

        // Oyuncu tiplerini ve pozisyonlarını belirliyor falan
        Map<Position, Integer> playerTypes = new HashMap<>();
        playerTypes.put(Position.GOALKEEPER, 1);
        playerTypes.put(Position.DEFENDER, 6);
        playerTypes.put(Position.MIDFIELDER, 6);
        playerTypes.put(Position.ATTACKER, 7);

        // Her oyuncu tipi için belirli sayıda oyuncu oluşturuyor burası
        for (Map.Entry<Position, Integer> entry : playerTypes.entrySet()) {
            Position position = entry.getKey();
            int playerCount = entry.getValue();

            for (int i = 0; i < playerCount; i++) {
                Player player = new Player();
                player.setFirstName("Player " + (i + 1));
                player.setLastName("Lastname");
                player.setCountry("Country");
                player.setAge(generateRandomAge());
                player.setMarketValue(1000000);
                player.setPosition(position);

                players.add(player);
            }
        }

        return players;
    }

    private int generateRandomAge() {
        Random random = new Random();
        return random.nextInt(23) + 18; // 18-40 arasında rastgele bir yaş üretme
    }


*/


}
