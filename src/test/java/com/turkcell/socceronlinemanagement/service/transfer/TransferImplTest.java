package com.turkcell.socceronlinemanagement.service.transfer;

import com.turkcell.socceronlinemanagement.model.Player;
import com.turkcell.socceronlinemanagement.model.Team;
import com.turkcell.socceronlinemanagement.model.Transfer;
import com.turkcell.socceronlinemanagement.model.User;
import com.turkcell.socceronlinemanagement.model.enums.Position;
import com.turkcell.socceronlinemanagement.model.enums.Role;
import com.turkcell.socceronlinemanagement.model.enums.TransferState;
import com.turkcell.socceronlinemanagement.repository.PaymentRepository;
import com.turkcell.socceronlinemanagement.repository.PlayerRepository;
import com.turkcell.socceronlinemanagement.repository.TeamRepository;
import com.turkcell.socceronlinemanagement.repository.TransferRepository;
import com.turkcell.socceronlinemanagement.service.payment.PaymentBusinessRules;
import com.turkcell.socceronlinemanagement.service.payment.PaymentImpl;
import com.turkcell.socceronlinemanagement.service.payment.PosService;
import com.turkcell.socceronlinemanagement.service.player.PlayerBusinessRules;
import com.turkcell.socceronlinemanagement.service.player.PlayerImpl;
import com.turkcell.socceronlinemanagement.service.team.TeamBusinessRules;
import com.turkcell.socceronlinemanagement.service.team.TeamImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TransferImplTest {
    @Autowired
    private TransferImpl transferImpl;

    @Test
    void testGetAll() {

        TransferRepository repository = mock(TransferRepository.class);
        when(repository.findAll()).thenReturn(new ArrayList<>());
        PlayerRepository repository2 = mock(PlayerRepository.class);
        ModelMapper mapper = new ModelMapper();
        PlayerImpl playerService = new PlayerImpl(repository2, mapper,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        ModelMapper mapper2 = new ModelMapper();
        TeamRepository repository3 = mock(TeamRepository.class);
        ModelMapper mapper3 = new ModelMapper();
        TeamBusinessRules rules = new TeamBusinessRules(mock(TeamRepository.class));
        PlayerRepository repository4 = mock(PlayerRepository.class);
        ModelMapper mapper4 = new ModelMapper();
        PlayerImpl playerManager = new PlayerImpl(repository4, mapper4,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        PlayerRepository playerRepository = mock(PlayerRepository.class);
        TeamBusinessRules teamBusinessRules = new TeamBusinessRules(mock(TeamRepository.class));
        TransferBusinessRules transferBusinessRules = new TransferBusinessRules(mock(TransferRepository.class));
        TransferRepository transferRepository = mock(TransferRepository.class);
        PlayerBusinessRules playerBusinessRules = new PlayerBusinessRules(mock(PlayerRepository.class));
        TeamRepository teamRepository = mock(TeamRepository.class);
        PlayerRepository repository5 = mock(PlayerRepository.class);
        ModelMapper mapper5 = new ModelMapper();
        PlayerImpl playerService2 = new PlayerImpl(repository5, mapper5,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        TeamImpl teamService = new TeamImpl(repository3, mapper3, rules, playerManager, playerRepository,
                teamBusinessRules, transferBusinessRules, transferRepository, playerBusinessRules, teamRepository,
                playerService2, new Random());

        PaymentRepository repository6 = mock(PaymentRepository.class);
        ModelMapper mapper6 = new ModelMapper();
        PosService posService = mock(PosService.class);
        PaymentImpl paymentService = new PaymentImpl(repository6, mapper6, posService,
                new PaymentBusinessRules(mock(PaymentRepository.class)));

        PlayerRepository playerRepository2 = mock(PlayerRepository.class);
        TeamRepository teamRepository2 = mock(TeamRepository.class);
        TransferBusinessRules rules2 = new TransferBusinessRules(mock(TransferRepository.class));
        assertTrue((new TransferImpl(playerService, repository, mapper2, teamService, paymentService, playerRepository2,
                teamRepository2, rules2, new Random())).getAll().isEmpty());
        verify(repository).findAll();
    }

    @Test
    void testGetById() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        user.setTeams(new ArrayList<>());

        Team team = new Team();
        team.setId(1);
        team.setPlayers(new ArrayList<>());
        team.setTeamCountry("GB");
        team.setTeamName("Team Name");
        team.setTeamValue(10.0d);
        team.setUser(user);

        Player player = new Player();
        player.setAge(1);
        player.setCountry("GB");
        player.setFirstName("Jane");
        player.setId(1);
        player.setLastName("Doe");
        player.setMarketValue(10.0d);
        player.setPosition(Position.GOALKEEPER);
        player.setTeam(team);
        player.setTransferState(TransferState.TRANSFERRED);
        player.setTransfers(new ArrayList<>());

        Transfer transfer = new Transfer();
        transfer.setCompleted(true);
        transfer.setDateOfTransfer(LocalDate.of(1970, 1, 1).atStartOfDay());
        transfer.setEndDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        transfer.setId(1);
        transfer.setPlayer(player);
        transfer.setPlayerCountry("GB");
        transfer.setPlayerMarketValue(10.0d);
        transfer.setPlayerName("Player Name");
        transfer.setPrice(10.0d);
        transfer.setTeamName("Team Name");
        transfer.setTeamValue(10.0d);
        TransferRepository repository = mock(TransferRepository.class);
        when(repository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(transfer));
        TransferRepository repository2 = mock(TransferRepository.class);
        when(repository2.existsById(Mockito.<Integer>any())).thenReturn(true);
        TransferBusinessRules rules = new TransferBusinessRules(repository2);
        PlayerRepository repository3 = mock(PlayerRepository.class);
        ModelMapper mapper = new ModelMapper();
        PlayerImpl playerService = new PlayerImpl(repository3, mapper,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        ModelMapper mapper2 = new ModelMapper();
        TeamRepository repository4 = mock(TeamRepository.class);
        ModelMapper mapper3 = new ModelMapper();
        TeamBusinessRules rules2 = new TeamBusinessRules(mock(TeamRepository.class));
        PlayerRepository repository5 = mock(PlayerRepository.class);
        ModelMapper mapper4 = new ModelMapper();
        PlayerImpl playerManager = new PlayerImpl(repository5, mapper4,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        PlayerRepository playerRepository = mock(PlayerRepository.class);
        TeamBusinessRules teamBusinessRules = new TeamBusinessRules(mock(TeamRepository.class));
        TransferBusinessRules transferBusinessRules = new TransferBusinessRules(mock(TransferRepository.class));
        TransferRepository transferRepository = mock(TransferRepository.class);
        PlayerBusinessRules playerBusinessRules = new PlayerBusinessRules(mock(PlayerRepository.class));
        TeamRepository teamRepository = mock(TeamRepository.class);
        PlayerRepository repository6 = mock(PlayerRepository.class);
        ModelMapper mapper5 = new ModelMapper();
        PlayerImpl playerService2 = new PlayerImpl(repository6, mapper5,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        TeamImpl teamService = new TeamImpl(repository4, mapper3, rules2, playerManager, playerRepository,
                teamBusinessRules, transferBusinessRules, transferRepository, playerBusinessRules, teamRepository,
                playerService2, new Random());

        PaymentRepository repository7 = mock(PaymentRepository.class);
        ModelMapper mapper6 = new ModelMapper();
        PosService posService = mock(PosService.class);
        PaymentImpl paymentService = new PaymentImpl(repository7, mapper6, posService,
                new PaymentBusinessRules(mock(PaymentRepository.class)));

        PlayerRepository playerRepository2 = mock(PlayerRepository.class);
        TeamRepository teamRepository2 = mock(TeamRepository.class);
        TransferResponse actualById = (new TransferImpl(playerService, repository, mapper2, teamService, paymentService,
                playerRepository2, teamRepository2, rules, new Random())).getById(1);
        assertEquals(10.0d, actualById.getTeamValue());
        assertEquals("00:00", actualById.getDateOfTransfer().toLocalTime().toString());
        assertEquals("Team Name", actualById.getTeamName());
        assertEquals(10.0d, actualById.getPrice());
        assertEquals("Player Name", actualById.getPlayerName());
        assertEquals(1, actualById.getPlayerId());
        assertEquals(1, actualById.getId());
        verify(repository).findById(Mockito.<Integer>any());
        verify(repository2).existsById(Mockito.<Integer>any());
    }

    @Test
    void testReturnPlayerFromTransfer() {
      User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        user.setTeams(new ArrayList<>());

        Team team = new Team();
        team.setId(1);
        team.setPlayers(new ArrayList<>());
        team.setTeamCountry("GB");
        team.setTeamName("Team Name");
        team.setTeamValue(10.0d);
        team.setUser(user);

        Player player = new Player();
        player.setAge(1);
        player.setCountry("GB");
        player.setFirstName("Jane");
        player.setId(1);
        player.setLastName("Doe");
        player.setMarketValue(10.0d);
        player.setPosition(Position.GOALKEEPER);
        player.setTeam(team);
        player.setTransferState(TransferState.TRANSFERRED);
        player.setTransfers(new ArrayList<>());
        Optional<Player> ofResult = Optional.of(player);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setId(1);
        user2.setPassword("iloveyou");
        user2.setRole(Role.USER);
        user2.setTeams(new ArrayList<>());

        Team team2 = new Team();
        team2.setId(1);
        team2.setPlayers(new ArrayList<>());
        team2.setTeamCountry("GB");
        team2.setTeamName("Team Name");
        team2.setTeamValue(10.0d);
        team2.setUser(user2);

        Player player2 = new Player();
        player2.setAge(1);
        player2.setCountry("GB");
        player2.setFirstName("Jane");
        player2.setId(1);
        player2.setLastName("Doe");
        player2.setMarketValue(10.0d);
        player2.setPosition(Position.GOALKEEPER);
        player2.setTeam(team2);
        player2.setTransferState(TransferState.TRANSFERRED);
        player2.setTransfers(new ArrayList<>());
        PlayerRepository repository = mock(PlayerRepository.class);
        when(repository.save(Mockito.<Player>any())).thenReturn(player2);
        when(repository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        ModelMapper mapper = new ModelMapper();
        PlayerImpl playerService = new PlayerImpl(repository, mapper,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setId(1);
        user3.setPassword("iloveyou");
        user3.setRole(Role.USER);
        user3.setTeams(new ArrayList<>());

        Team team3 = new Team();
        team3.setId(1);
        team3.setPlayers(new ArrayList<>());
        team3.setTeamCountry("GB");
        team3.setTeamName("Team Name");
        team3.setTeamValue(10.0d);
        team3.setUser(user3);

        Player player3 = new Player();
        player3.setAge(1);
        player3.setCountry("GB");
        player3.setFirstName("Jane");
        player3.setId(1);
        player3.setLastName("Doe");
        player3.setMarketValue(10.0d);
        player3.setPosition(Position.GOALKEEPER);
        player3.setTeam(team3);
        player3.setTransferState(TransferState.TRANSFERRED);
        player3.setTransfers(new ArrayList<>());

        Transfer transfer = new Transfer();
        transfer.setCompleted(true);
        transfer.setDateOfTransfer(LocalDate.of(1970, 1, 1).atStartOfDay());
        transfer.setEndDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        transfer.setId(1);
        transfer.setPlayer(player3);
        transfer.setPlayerCountry("GB");
        transfer.setPlayerMarketValue(10.0d);
        transfer.setPlayerName("Player Name");
        transfer.setPrice(10.0d);
        transfer.setTeamName("Team Name");
        transfer.setTeamValue(10.0d);

        User user4 = new User();
        user4.setEmail("jane.doe@example.org");
        user4.setId(1);
        user4.setPassword("iloveyou");
        user4.setRole(Role.USER);
        user4.setTeams(new ArrayList<>());

        Team team4 = new Team();
        team4.setId(1);
        team4.setPlayers(new ArrayList<>());
        team4.setTeamCountry("GB");
        team4.setTeamName("Team Name");
        team4.setTeamValue(10.0d);
        team4.setUser(user4);

        Player player4 = new Player();
        player4.setAge(1);
        player4.setCountry("GB");
        player4.setFirstName("Jane");
        player4.setId(1);
        player4.setLastName("Doe");
        player4.setMarketValue(10.0d);
        player4.setPosition(Position.GOALKEEPER);
        player4.setTeam(team4);
        player4.setTransferState(TransferState.TRANSFERRED);
        player4.setTransfers(new ArrayList<>());

        Transfer transfer2 = new Transfer();
        transfer2.setCompleted(true);
        transfer2.setDateOfTransfer(LocalDate.of(1970, 1, 1).atStartOfDay());
        transfer2.setEndDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        transfer2.setId(1);
        transfer2.setPlayer(player4);
        transfer2.setPlayerCountry("GB");
        transfer2.setPlayerMarketValue(10.0d);
        transfer2.setPlayerName("Player Name");
        transfer2.setPrice(10.0d);
        transfer2.setTeamName("Team Name");
        transfer2.setTeamValue(10.0d);
        TransferRepository repository2 = mock(TransferRepository.class);
        when(repository2.save(Mockito.<Transfer>any())).thenReturn(transfer2);
        when(repository2.findByPlayerIdAndIsCompletedIsFalse(Mockito.<Integer>any())).thenReturn(transfer);
        TransferRepository repository3 = mock(TransferRepository.class);
        when(repository3.existsByPlayerIdAndIsCompletedIsFalse(Mockito.<Integer>any())).thenReturn(true);
        TransferBusinessRules rules = new TransferBusinessRules(repository3);
        ModelMapper mapper2 = new ModelMapper();
        TeamRepository repository4 = mock(TeamRepository.class);
        ModelMapper mapper3 = new ModelMapper();
        TeamBusinessRules rules2 = new TeamBusinessRules(mock(TeamRepository.class));
        PlayerRepository repository5 = mock(PlayerRepository.class);
        ModelMapper mapper4 = new ModelMapper();
        PlayerImpl playerManager = new PlayerImpl(repository5, mapper4,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        PlayerRepository playerRepository = mock(PlayerRepository.class);
        TeamBusinessRules teamBusinessRules = new TeamBusinessRules(mock(TeamRepository.class));
        TransferBusinessRules transferBusinessRules = new TransferBusinessRules(mock(TransferRepository.class));
        TransferRepository transferRepository = mock(TransferRepository.class);
        PlayerBusinessRules playerBusinessRules = new PlayerBusinessRules(mock(PlayerRepository.class));
        TeamRepository teamRepository = mock(TeamRepository.class);
        PlayerRepository repository6 = mock(PlayerRepository.class);
        ModelMapper mapper5 = new ModelMapper();
        PlayerImpl playerService2 = new PlayerImpl(repository6, mapper5,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        TeamImpl teamService = new TeamImpl(repository4, mapper3, rules2, playerManager, playerRepository,
                teamBusinessRules, transferBusinessRules, transferRepository, playerBusinessRules, teamRepository,
                playerService2, new Random());

        PaymentRepository repository7 = mock(PaymentRepository.class);
        ModelMapper mapper6 = new ModelMapper();
        PosService posService = mock(PosService.class);
        PaymentImpl paymentService = new PaymentImpl(repository7, mapper6, posService,
                new PaymentBusinessRules(mock(PaymentRepository.class)));

        PlayerRepository playerRepository2 = mock(PlayerRepository.class);
        TeamRepository teamRepository2 = mock(TeamRepository.class);
        TransferResponse actualReturnPlayerFromTransferResult = (new TransferImpl(playerService, repository2, mapper2,
                teamService, paymentService, playerRepository2, teamRepository2, rules, new Random()))
                .returnPlayerFromTransfer(1);
        assertEquals(10.0d, actualReturnPlayerFromTransferResult.getTeamValue());
        assertEquals("00:00", actualReturnPlayerFromTransferResult.getDateOfTransfer().toLocalTime().toString());
        assertEquals("Team Name", actualReturnPlayerFromTransferResult.getTeamName());
        assertEquals(10.0d, actualReturnPlayerFromTransferResult.getPrice());
        assertEquals("Player Name", actualReturnPlayerFromTransferResult.getPlayerName());
        assertEquals(1, actualReturnPlayerFromTransferResult.getPlayerId());
        assertEquals(1, actualReturnPlayerFromTransferResult.getId());
        verify(repository).save(Mockito.<Player>any());
        verify(repository).findById(Mockito.<Integer>any());
        verify(repository2).findByPlayerIdAndIsCompletedIsFalse(Mockito.<Integer>any());
        verify(repository2).save(Mockito.<Transfer>any());
        verify(repository3).existsByPlayerIdAndIsCompletedIsFalse(Mockito.<Integer>any());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testReturnPlayerFromTransfer2() {

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        user.setTeams(new ArrayList<>());

        Team team = new Team();
        team.setId(1);
        team.setPlayers(new ArrayList<>());
        team.setTeamCountry("GB");
        team.setTeamName("Team Name");
        team.setTeamValue(10.0d);
        team.setUser(user);

        Player player = new Player();
        player.setAge(1);
        player.setCountry("GB");
        player.setFirstName("Jane");
        player.setId(1);
        player.setLastName("Doe");
        player.setMarketValue(10.0d);
        player.setPosition(Position.GOALKEEPER);
        player.setTeam(team);
        player.setTransferState(TransferState.TRANSFERRED);
        player.setTransfers(new ArrayList<>());
        PlayerRepository repository = mock(PlayerRepository.class);
        when(repository.save(Mockito.<Player>any())).thenReturn(player);
        when(repository.findById(Mockito.<Integer>any())).thenReturn(Optional.empty());
        ModelMapper mapper = new ModelMapper();
        PlayerImpl playerService = new PlayerImpl(repository, mapper,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setId(1);
        user2.setPassword("iloveyou");
        user2.setRole(Role.USER);
        user2.setTeams(new ArrayList<>());

        Team team2 = new Team();
        team2.setId(1);
        team2.setPlayers(new ArrayList<>());
        team2.setTeamCountry("GB");
        team2.setTeamName("Team Name");
        team2.setTeamValue(10.0d);
        team2.setUser(user2);

        Player player2 = new Player();
        player2.setAge(1);
        player2.setCountry("GB");
        player2.setFirstName("Jane");
        player2.setId(1);
        player2.setLastName("Doe");
        player2.setMarketValue(10.0d);
        player2.setPosition(Position.GOALKEEPER);
        player2.setTeam(team2);
        player2.setTransferState(TransferState.TRANSFERRED);
        player2.setTransfers(new ArrayList<>());

        Transfer transfer = new Transfer();
        transfer.setCompleted(true);
        transfer.setDateOfTransfer(LocalDate.of(1970, 1, 1).atStartOfDay());
        transfer.setEndDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        transfer.setId(1);
        transfer.setPlayer(player2);
        transfer.setPlayerCountry("GB");
        transfer.setPlayerMarketValue(10.0d);
        transfer.setPlayerName("Player Name");
        transfer.setPrice(10.0d);
        transfer.setTeamName("Team Name");
        transfer.setTeamValue(10.0d);

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setId(1);
        user3.setPassword("iloveyou");
        user3.setRole(Role.USER);
        user3.setTeams(new ArrayList<>());

        Team team3 = new Team();
        team3.setId(1);
        team3.setPlayers(new ArrayList<>());
        team3.setTeamCountry("GB");
        team3.setTeamName("Team Name");
        team3.setTeamValue(10.0d);
        team3.setUser(user3);

        Player player3 = new Player();
        player3.setAge(1);
        player3.setCountry("GB");
        player3.setFirstName("Jane");
        player3.setId(1);
        player3.setLastName("Doe");
        player3.setMarketValue(10.0d);
        player3.setPosition(Position.GOALKEEPER);
        player3.setTeam(team3);
        player3.setTransferState(TransferState.TRANSFERRED);
        player3.setTransfers(new ArrayList<>());

        Transfer transfer2 = new Transfer();
        transfer2.setCompleted(true);
        transfer2.setDateOfTransfer(LocalDate.of(1970, 1, 1).atStartOfDay());
        transfer2.setEndDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        transfer2.setId(1);
        transfer2.setPlayer(player3);
        transfer2.setPlayerCountry("GB");
        transfer2.setPlayerMarketValue(10.0d);
        transfer2.setPlayerName("Player Name");
        transfer2.setPrice(10.0d);
        transfer2.setTeamName("Team Name");
        transfer2.setTeamValue(10.0d);
        TransferRepository repository2 = mock(TransferRepository.class);
        when(repository2.save(Mockito.<Transfer>any())).thenReturn(transfer2);
        when(repository2.findByPlayerIdAndIsCompletedIsFalse(Mockito.<Integer>any())).thenReturn(transfer);
        TransferRepository repository3 = mock(TransferRepository.class);
        when(repository3.existsByPlayerIdAndIsCompletedIsFalse(Mockito.<Integer>any())).thenReturn(true);
        TransferBusinessRules rules = new TransferBusinessRules(repository3);
        ModelMapper mapper2 = new ModelMapper();
        TeamRepository repository4 = mock(TeamRepository.class);
        ModelMapper mapper3 = new ModelMapper();
        TeamBusinessRules rules2 = new TeamBusinessRules(mock(TeamRepository.class));
        PlayerRepository repository5 = mock(PlayerRepository.class);
        ModelMapper mapper4 = new ModelMapper();
        PlayerImpl playerManager = new PlayerImpl(repository5, mapper4,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        PlayerRepository playerRepository = mock(PlayerRepository.class);
        TeamBusinessRules teamBusinessRules = new TeamBusinessRules(mock(TeamRepository.class));
        TransferBusinessRules transferBusinessRules = new TransferBusinessRules(mock(TransferRepository.class));
        TransferRepository transferRepository = mock(TransferRepository.class);
        PlayerBusinessRules playerBusinessRules = new PlayerBusinessRules(mock(PlayerRepository.class));
        TeamRepository teamRepository = mock(TeamRepository.class);
        PlayerRepository repository6 = mock(PlayerRepository.class);
        ModelMapper mapper5 = new ModelMapper();
        PlayerImpl playerService2 = new PlayerImpl(repository6, mapper5,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        TeamImpl teamService = new TeamImpl(repository4, mapper3, rules2, playerManager, playerRepository,
                teamBusinessRules, transferBusinessRules, transferRepository, playerBusinessRules, teamRepository,
                playerService2, new Random());

        PaymentRepository repository7 = mock(PaymentRepository.class);
        ModelMapper mapper6 = new ModelMapper();
        PosService posService = mock(PosService.class);
        PaymentImpl paymentService = new PaymentImpl(repository7, mapper6, posService,
                new PaymentBusinessRules(mock(PaymentRepository.class)));

        PlayerRepository playerRepository2 = mock(PlayerRepository.class);
        TeamRepository teamRepository2 = mock(TeamRepository.class);
        (new TransferImpl(playerService, repository2, mapper2, teamService, paymentService, playerRepository2,
                teamRepository2, rules, new Random())).returnPlayerFromTransfer(1);
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testAdd() {

        TransferRequest request = new TransferRequest(10.0d, 1, "Player Name", "Team Name", 10.0d, 10.0d);

        transferImpl.add(request);
    }

    @Test
    void testUpdate() {

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        user.setTeams(new ArrayList<>());

        Team team = new Team();
        team.setId(1);
        team.setPlayers(new ArrayList<>());
        team.setTeamCountry("GB");
        team.setTeamName("Team Name");
        team.setTeamValue(10.0d);
        team.setUser(user);

        Player player = new Player();
        player.setAge(1);
        player.setCountry("GB");
        player.setFirstName("Jane");
        player.setId(1);
        player.setLastName("Doe");
        player.setMarketValue(10.0d);
        player.setPosition(Position.GOALKEEPER);
        player.setTeam(team);
        player.setTransferState(TransferState.TRANSFERRED);
        player.setTransfers(new ArrayList<>());

        Transfer transfer = new Transfer();
        transfer.setCompleted(true);
        transfer.setDateOfTransfer(LocalDate.of(1970, 1, 1).atStartOfDay());
        transfer.setEndDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        transfer.setId(1);
        transfer.setPlayer(player);
        transfer.setPlayerCountry("GB");
        transfer.setPlayerMarketValue(10.0d);
        transfer.setPlayerName("Player Name");
        transfer.setPrice(10.0d);
        transfer.setTeamName("Team Name");
        transfer.setTeamValue(10.0d);
        TransferRepository repository = mock(TransferRepository.class);
        when(repository.save(Mockito.<Transfer>any())).thenReturn(transfer);
        TransferRepository repository2 = mock(TransferRepository.class);
        when(repository2.existsById(Mockito.<Integer>any())).thenReturn(true);
        TransferBusinessRules rules = new TransferBusinessRules(repository2);
        PlayerRepository repository3 = mock(PlayerRepository.class);
        ModelMapper mapper = new ModelMapper();
        PlayerImpl playerService = new PlayerImpl(repository3, mapper,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        ModelMapper mapper2 = new ModelMapper();
        TeamRepository repository4 = mock(TeamRepository.class);
        ModelMapper mapper3 = new ModelMapper();
        TeamBusinessRules rules2 = new TeamBusinessRules(mock(TeamRepository.class));
        PlayerRepository repository5 = mock(PlayerRepository.class);
        ModelMapper mapper4 = new ModelMapper();
        PlayerImpl playerManager = new PlayerImpl(repository5, mapper4,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        PlayerRepository playerRepository = mock(PlayerRepository.class);
        TeamBusinessRules teamBusinessRules = new TeamBusinessRules(mock(TeamRepository.class));
        TransferBusinessRules transferBusinessRules = new TransferBusinessRules(mock(TransferRepository.class));
        TransferRepository transferRepository = mock(TransferRepository.class);
        PlayerBusinessRules playerBusinessRules = new PlayerBusinessRules(mock(PlayerRepository.class));
        TeamRepository teamRepository = mock(TeamRepository.class);
        PlayerRepository repository6 = mock(PlayerRepository.class);
        ModelMapper mapper5 = new ModelMapper();
        PlayerImpl playerService2 = new PlayerImpl(repository6, mapper5,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        TeamImpl teamService = new TeamImpl(repository4, mapper3, rules2, playerManager, playerRepository,
                teamBusinessRules, transferBusinessRules, transferRepository, playerBusinessRules, teamRepository,
                playerService2, new Random());

        PaymentRepository repository7 = mock(PaymentRepository.class);
        ModelMapper mapper6 = new ModelMapper();
        PosService posService = mock(PosService.class);
        PaymentImpl paymentService = new PaymentImpl(repository7, mapper6, posService,
                new PaymentBusinessRules(mock(PaymentRepository.class)));

        PlayerRepository playerRepository2 = mock(PlayerRepository.class);
        TeamRepository teamRepository2 = mock(TeamRepository.class);
        TransferImpl transferImpl = new TransferImpl(playerService, repository, mapper2, teamService, paymentService,
                playerRepository2, teamRepository2, rules, new Random());
        TransferResponse actualUpdateResult = transferImpl.update(1,
                new TransferRequest(10.0d, 1, "Player Name", "Team Name", 10.0d, 10.0d));
        assertNull(actualUpdateResult.getDateOfTransfer());
        assertEquals(10.0d, actualUpdateResult.getTeamValue());
        assertEquals("Team Name", actualUpdateResult.getTeamName());
        assertEquals(10.0d, actualUpdateResult.getPrice());
        assertEquals("Player Name", actualUpdateResult.getPlayerName());
        assertEquals(1, actualUpdateResult.getPlayerId());
        assertEquals(1, actualUpdateResult.getId());
        verify(repository).save(Mockito.<Transfer>any());
        verify(repository2).existsById(Mockito.<Integer>any());
    }

    @Test
    void testDelete() {

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        user.setTeams(new ArrayList<>());

        Team team = new Team();
        team.setId(1);
        team.setPlayers(new ArrayList<>());
        team.setTeamCountry("GB");
        team.setTeamName("Team Name");
        team.setTeamValue(10.0d);
        team.setUser(user);

        Player player = new Player();
        player.setAge(1);
        player.setCountry("GB");
        player.setFirstName("Jane");
        player.setId(1);
        player.setLastName("Doe");
        player.setMarketValue(10.0d);
        player.setPosition(Position.GOALKEEPER);
        player.setTeam(team);
        player.setTransferState(TransferState.TRANSFERRED);
        player.setTransfers(new ArrayList<>());
        Optional<Player> ofResult = Optional.of(player);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setId(1);
        user2.setPassword("iloveyou");
        user2.setRole(Role.USER);
        user2.setTeams(new ArrayList<>());

        Team team2 = new Team();
        team2.setId(1);
        team2.setPlayers(new ArrayList<>());
        team2.setTeamCountry("GB");
        team2.setTeamName("Team Name");
        team2.setTeamValue(10.0d);
        team2.setUser(user2);

        Player player2 = new Player();
        player2.setAge(1);
        player2.setCountry("GB");
        player2.setFirstName("Jane");
        player2.setId(1);
        player2.setLastName("Doe");
        player2.setMarketValue(10.0d);
        player2.setPosition(Position.GOALKEEPER);
        player2.setTeam(team2);
        player2.setTransferState(TransferState.TRANSFERRED);
        player2.setTransfers(new ArrayList<>());
        PlayerRepository repository = mock(PlayerRepository.class);
        when(repository.save(Mockito.<Player>any())).thenReturn(player2);
        when(repository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        ModelMapper mapper = new ModelMapper();
        PlayerImpl playerService = new PlayerImpl(repository, mapper,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setId(1);
        user3.setPassword("iloveyou");
        user3.setRole(Role.USER);
        user3.setTeams(new ArrayList<>());

        Team team3 = new Team();
        team3.setId(1);
        team3.setPlayers(new ArrayList<>());
        team3.setTeamCountry("GB");
        team3.setTeamName("Team Name");
        team3.setTeamValue(10.0d);
        team3.setUser(user3);

        Player player3 = new Player();
        player3.setAge(1);
        player3.setCountry("GB");
        player3.setFirstName("Jane");
        player3.setId(1);
        player3.setLastName("Doe");
        player3.setMarketValue(10.0d);
        player3.setPosition(Position.GOALKEEPER);
        player3.setTeam(team3);
        player3.setTransferState(TransferState.TRANSFERRED);
        player3.setTransfers(new ArrayList<>());

        Transfer transfer = new Transfer();
        transfer.setCompleted(true);
        transfer.setDateOfTransfer(LocalDate.of(1970, 1, 1).atStartOfDay());
        transfer.setEndDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        transfer.setId(1);
        transfer.setPlayer(player3);
        transfer.setPlayerCountry("GB");
        transfer.setPlayerMarketValue(10.0d);
        transfer.setPlayerName("Player Name");
        transfer.setPrice(10.0d);
        transfer.setTeamName("Team Name");
        transfer.setTeamValue(10.0d);
        Optional<Transfer> ofResult2 = Optional.of(transfer);
        TransferRepository repository2 = mock(TransferRepository.class);
        when(repository2.existsByPlayerIdAndIsCompletedIsFalse(Mockito.<Integer>any())).thenReturn(true);
        doNothing().when(repository2).deleteById(Mockito.<Integer>any());
        when(repository2.findById(Mockito.<Integer>any())).thenReturn(ofResult2);
        TransferRepository repository3 = mock(TransferRepository.class);
        when(repository3.existsById(Mockito.<Integer>any())).thenReturn(true);
        TransferBusinessRules rules = new TransferBusinessRules(repository3);
        ModelMapper mapper2 = new ModelMapper();
        TeamRepository repository4 = mock(TeamRepository.class);
        ModelMapper mapper3 = new ModelMapper();
        TeamBusinessRules rules2 = new TeamBusinessRules(mock(TeamRepository.class));
        PlayerRepository repository5 = mock(PlayerRepository.class);
        ModelMapper mapper4 = new ModelMapper();
        PlayerImpl playerManager = new PlayerImpl(repository5, mapper4,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        PlayerRepository playerRepository = mock(PlayerRepository.class);
        TeamBusinessRules teamBusinessRules = new TeamBusinessRules(mock(TeamRepository.class));
        TransferBusinessRules transferBusinessRules = new TransferBusinessRules(mock(TransferRepository.class));
        TransferRepository transferRepository = mock(TransferRepository.class);
        PlayerBusinessRules playerBusinessRules = new PlayerBusinessRules(mock(PlayerRepository.class));
        TeamRepository teamRepository = mock(TeamRepository.class);
        PlayerRepository repository6 = mock(PlayerRepository.class);
        ModelMapper mapper5 = new ModelMapper();
        PlayerImpl playerService2 = new PlayerImpl(repository6, mapper5,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        TeamImpl teamService = new TeamImpl(repository4, mapper3, rules2, playerManager, playerRepository,
                teamBusinessRules, transferBusinessRules, transferRepository, playerBusinessRules, teamRepository,
                playerService2, new Random());

        PaymentRepository repository7 = mock(PaymentRepository.class);
        ModelMapper mapper6 = new ModelMapper();
        PosService posService = mock(PosService.class);
        PaymentImpl paymentService = new PaymentImpl(repository7, mapper6, posService,
                new PaymentBusinessRules(mock(PaymentRepository.class)));

        PlayerRepository playerRepository2 = mock(PlayerRepository.class);
        TeamRepository teamRepository2 = mock(TeamRepository.class);
        (new TransferImpl(playerService, repository2, mapper2, teamService, paymentService, playerRepository2,
                teamRepository2, rules, new Random())).delete(1);
        verify(repository, atLeast(1)).save(Mockito.<Player>any());
        verify(repository, atLeast(1)).findById(Mockito.<Integer>any());
        verify(repository2).existsByPlayerIdAndIsCompletedIsFalse(Mockito.<Integer>any());
        verify(repository2, atLeast(1)).findById(Mockito.<Integer>any());
        verify(repository2).deleteById(Mockito.<Integer>any());
        verify(repository3).existsById(Mockito.<Integer>any());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testDelete2() {

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        user.setTeams(new ArrayList<>());

        Team team = new Team();
        team.setId(1);
        team.setPlayers(new ArrayList<>());
        team.setTeamCountry("GB");
        team.setTeamName("Team Name");
        team.setTeamValue(10.0d);
        team.setUser(user);

        Player player = new Player();
        player.setAge(1);
        player.setCountry("GB");
        player.setFirstName("Jane");
        player.setId(1);
        player.setLastName("Doe");
        player.setMarketValue(10.0d);
        player.setPosition(Position.GOALKEEPER);
        player.setTeam(team);
        player.setTransferState(TransferState.TRANSFERRED);
        player.setTransfers(new ArrayList<>());
        PlayerRepository repository = mock(PlayerRepository.class);
        when(repository.save(Mockito.<Player>any())).thenReturn(player);
        when(repository.findById(Mockito.<Integer>any())).thenReturn(Optional.empty());
        ModelMapper mapper = new ModelMapper();
        PlayerImpl playerService = new PlayerImpl(repository, mapper,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setId(1);
        user2.setPassword("iloveyou");
        user2.setRole(Role.USER);
        user2.setTeams(new ArrayList<>());

        Team team2 = new Team();
        team2.setId(1);
        team2.setPlayers(new ArrayList<>());
        team2.setTeamCountry("GB");
        team2.setTeamName("Team Name");
        team2.setTeamValue(10.0d);
        team2.setUser(user2);

        Player player2 = new Player();
        player2.setAge(1);
        player2.setCountry("GB");
        player2.setFirstName("Jane");
        player2.setId(1);
        player2.setLastName("Doe");
        player2.setMarketValue(10.0d);
        player2.setPosition(Position.GOALKEEPER);
        player2.setTeam(team2);
        player2.setTransferState(TransferState.TRANSFERRED);
        player2.setTransfers(new ArrayList<>());

        Transfer transfer = new Transfer();
        transfer.setCompleted(true);
        transfer.setDateOfTransfer(LocalDate.of(1970, 1, 1).atStartOfDay());
        transfer.setEndDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        transfer.setId(1);
        transfer.setPlayer(player2);
        transfer.setPlayerCountry("GB");
        transfer.setPlayerMarketValue(10.0d);
        transfer.setPlayerName("Player Name");
        transfer.setPrice(10.0d);
        transfer.setTeamName("Team Name");
        transfer.setTeamValue(10.0d);
        Optional<Transfer> ofResult = Optional.of(transfer);
        TransferRepository repository2 = mock(TransferRepository.class);
        when(repository2.existsByPlayerIdAndIsCompletedIsFalse(Mockito.<Integer>any())).thenReturn(true);
        doNothing().when(repository2).deleteById(Mockito.<Integer>any());
        when(repository2.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        TransferRepository repository3 = mock(TransferRepository.class);
        when(repository3.existsById(Mockito.<Integer>any())).thenReturn(true);
        TransferBusinessRules rules = new TransferBusinessRules(repository3);
        ModelMapper mapper2 = new ModelMapper();
        TeamRepository repository4 = mock(TeamRepository.class);
        ModelMapper mapper3 = new ModelMapper();
        TeamBusinessRules rules2 = new TeamBusinessRules(mock(TeamRepository.class));
        PlayerRepository repository5 = mock(PlayerRepository.class);
        ModelMapper mapper4 = new ModelMapper();
        PlayerImpl playerManager = new PlayerImpl(repository5, mapper4,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        PlayerRepository playerRepository = mock(PlayerRepository.class);
        TeamBusinessRules teamBusinessRules = new TeamBusinessRules(mock(TeamRepository.class));
        TransferBusinessRules transferBusinessRules = new TransferBusinessRules(mock(TransferRepository.class));
        TransferRepository transferRepository = mock(TransferRepository.class);
        PlayerBusinessRules playerBusinessRules = new PlayerBusinessRules(mock(PlayerRepository.class));
        TeamRepository teamRepository = mock(TeamRepository.class);
        PlayerRepository repository6 = mock(PlayerRepository.class);
        ModelMapper mapper5 = new ModelMapper();
        PlayerImpl playerService2 = new PlayerImpl(repository6, mapper5,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        TeamImpl teamService = new TeamImpl(repository4, mapper3, rules2, playerManager, playerRepository,
                teamBusinessRules, transferBusinessRules, transferRepository, playerBusinessRules, teamRepository,
                playerService2, new Random());

        PaymentRepository repository7 = mock(PaymentRepository.class);
        ModelMapper mapper6 = new ModelMapper();
        PosService posService = mock(PosService.class);
        PaymentImpl paymentService = new PaymentImpl(repository7, mapper6, posService,
                new PaymentBusinessRules(mock(PaymentRepository.class)));

        PlayerRepository playerRepository2 = mock(PlayerRepository.class);
        TeamRepository teamRepository2 = mock(TeamRepository.class);
        (new TransferImpl(playerService, repository2, mapper2, teamService, paymentService, playerRepository2,
                teamRepository2, rules, new Random())).delete(1);
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testProcessTransfer() {
        Transfer transfer = new Transfer();
        transfer.setCompleted(true);
        transfer.setDateOfTransfer(LocalDate.of(1970, 1, 1).atStartOfDay());
        transfer.setEndDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        transfer.setId(1);

        Player player = new Player();
        player.setAge(1);
        player.setCountry("GB");
        player.setFirstName("Jane");
        player.setId(1);
        player.setLastName("Doe");
        player.setMarketValue(10.0d);
        player.setPosition(Position.GOALKEEPER);

        Team team = new Team();
        team.setId(1);
        team.setPlayers(new ArrayList<>());
        team.setTeamCountry("GB");
        team.setTeamName("Team Name");
        team.setTeamValue(10.0d);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        user.setTeams(new ArrayList<>());
        team.setUser(user);
        player.setTeam(team);
        player.setTransferState(TransferState.TRANSFERRED);
        player.setTransfers(new ArrayList<>());
        transfer.setPlayer(player);
        transfer.setPlayerCountry("GB");
        transfer.setPlayerMarketValue(10.0d);
        transfer.setPlayerName("Player Name");
        transfer.setPrice(10.0d);
        transfer.setTeamName("Team Name");
        transfer.setTeamValue(10.0d);
        transferImpl.processTransfer(transfer);
    }
}

