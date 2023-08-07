package com.turkcell.socceronlinemanagement.service.team;

import com.turkcell.socceronlinemanagement.model.Player;
import com.turkcell.socceronlinemanagement.model.Team;
import com.turkcell.socceronlinemanagement.model.Transfer;
import com.turkcell.socceronlinemanagement.model.User;
import com.turkcell.socceronlinemanagement.model.enums.Position;
import com.turkcell.socceronlinemanagement.model.enums.Role;
import com.turkcell.socceronlinemanagement.model.enums.TransferState;
import com.turkcell.socceronlinemanagement.repository.PlayerRepository;
import com.turkcell.socceronlinemanagement.repository.TeamRepository;
import com.turkcell.socceronlinemanagement.repository.TransferRepository;
import com.turkcell.socceronlinemanagement.service.player.PlayerBusinessRules;
import com.turkcell.socceronlinemanagement.service.player.PlayerImpl;
import com.turkcell.socceronlinemanagement.service.transfer.TransferBusinessRules;
import com.turkcell.socceronlinemanagement.service.transfer.TransferPlayerRequest;
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
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TeamImplTest {
    @Autowired
    private TeamImpl teamImpl;

    @Test
    void testGetAll() {
     TeamRepository repository = mock(TeamRepository.class);
        when(repository.findAll()).thenReturn(new ArrayList<>());
        ModelMapper mapper = new ModelMapper();
        TeamBusinessRules rules = new TeamBusinessRules(mock(TeamRepository.class));
        PlayerRepository repository2 = mock(PlayerRepository.class);
        ModelMapper mapper2 = new ModelMapper();
        PlayerImpl playerManager = new PlayerImpl(repository2, mapper2,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        PlayerRepository playerRepository = mock(PlayerRepository.class);
        TeamBusinessRules teamBusinessRules = new TeamBusinessRules(mock(TeamRepository.class));
        TransferBusinessRules transferBusinessRules = new TransferBusinessRules(mock(TransferRepository.class));
        TransferRepository transferRepository = mock(TransferRepository.class);
        PlayerBusinessRules playerBusinessRules = new PlayerBusinessRules(mock(PlayerRepository.class));
        TeamRepository teamRepository = mock(TeamRepository.class);
        PlayerRepository repository3 = mock(PlayerRepository.class);
        ModelMapper mapper3 = new ModelMapper();
        PlayerImpl playerService = new PlayerImpl(repository3, mapper3,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        assertTrue((new TeamImpl(repository, mapper, rules, playerManager, playerRepository, teamBusinessRules,
                transferBusinessRules, transferRepository, playerBusinessRules, teamRepository, playerService, new Random()))
                .getAll()
                .isEmpty());
        verify(repository).findAll();
    }

    @Test
    void testGetAll2() {

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        user.setTeams(new ArrayList<>());

        Team team = new Team();
        team.setId(1);
        ArrayList<Player> players = new ArrayList<>();
        team.setPlayers(players);
        team.setTeamCountry("GB");
        team.setTeamName("Team Name");
        team.setTeamValue(10.0d);
        team.setUser(user);

        ArrayList<Team> teamList = new ArrayList<>();
        teamList.add(team);
        TeamRepository repository = mock(TeamRepository.class);
        when(repository.findAll()).thenReturn(teamList);
        ModelMapper mapper = new ModelMapper();
        TeamBusinessRules rules = new TeamBusinessRules(mock(TeamRepository.class));
        PlayerRepository repository2 = mock(PlayerRepository.class);
        ModelMapper mapper2 = new ModelMapper();
        PlayerImpl playerManager = new PlayerImpl(repository2, mapper2,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        PlayerRepository playerRepository = mock(PlayerRepository.class);
        TeamBusinessRules teamBusinessRules = new TeamBusinessRules(mock(TeamRepository.class));
        TransferBusinessRules transferBusinessRules = new TransferBusinessRules(mock(TransferRepository.class));
        TransferRepository transferRepository = mock(TransferRepository.class);
        PlayerBusinessRules playerBusinessRules = new PlayerBusinessRules(mock(PlayerRepository.class));
        TeamRepository teamRepository = mock(TeamRepository.class);
        PlayerRepository repository3 = mock(PlayerRepository.class);
        ModelMapper mapper3 = new ModelMapper();
        PlayerImpl playerService = new PlayerImpl(repository3, mapper3,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        List<TeamResponse> actualAll = (new TeamImpl(repository, mapper, rules, playerManager, playerRepository,
                teamBusinessRules, transferBusinessRules, transferRepository, playerBusinessRules, teamRepository,
                playerService, new Random())).getAll();
        assertEquals(1, actualAll.size());
        TeamResponse getResult = actualAll.get(0);
        assertEquals(1, getResult.getId());
        assertEquals(10.0d, getResult.getTeamValue());
        assertEquals("Team Name", getResult.getTeamName());
        assertEquals("GB", getResult.getTeamCountry());
        assertEquals(players, getResult.getPlayers());
        verify(repository).findAll();
    }

    @Test
    void testGetAll3() {

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        user.setTeams(new ArrayList<>());

        Team team = new Team();
        team.setId(1);
        ArrayList<Player> players = new ArrayList<>();
        team.setPlayers(players);
        team.setTeamCountry("GB");
        team.setTeamName("Team Name");
        team.setTeamValue(10.0d);
        team.setUser(user);

        User user2 = new User();
        user2.setEmail("john.smith@example.org");
        user2.setId(2);
        user2.setPassword("Password");
        user2.setRole(Role.ADMIN);
        user2.setTeams(new ArrayList<>());

        Team team2 = new Team();
        team2.setId(2);
        team2.setPlayers(new ArrayList<>());
        team2.setTeamCountry("GBR");
        team2.setTeamName("com.turkcell.socceronlinemanagement.model.Team");
        team2.setTeamValue(5000000.0d);
        team2.setUser(user2);

        ArrayList<Team> teamList = new ArrayList<>();
        teamList.add(team2);
        teamList.add(team);
        TeamRepository repository = mock(TeamRepository.class);
        when(repository.findAll()).thenReturn(teamList);
        ModelMapper mapper = new ModelMapper();
        TeamBusinessRules rules = new TeamBusinessRules(mock(TeamRepository.class));
        PlayerRepository repository2 = mock(PlayerRepository.class);
        ModelMapper mapper2 = new ModelMapper();
        PlayerImpl playerManager = new PlayerImpl(repository2, mapper2,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        PlayerRepository playerRepository = mock(PlayerRepository.class);
        TeamBusinessRules teamBusinessRules = new TeamBusinessRules(mock(TeamRepository.class));
        TransferBusinessRules transferBusinessRules = new TransferBusinessRules(mock(TransferRepository.class));
        TransferRepository transferRepository = mock(TransferRepository.class);
        PlayerBusinessRules playerBusinessRules = new PlayerBusinessRules(mock(PlayerRepository.class));
        TeamRepository teamRepository = mock(TeamRepository.class);
        PlayerRepository repository3 = mock(PlayerRepository.class);
        ModelMapper mapper3 = new ModelMapper();
        PlayerImpl playerService = new PlayerImpl(repository3, mapper3,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        List<TeamResponse> actualAll = (new TeamImpl(repository, mapper, rules, playerManager, playerRepository,
                teamBusinessRules, transferBusinessRules, transferRepository, playerBusinessRules, teamRepository,
                playerService, new Random())).getAll();
        assertEquals(2, actualAll.size());
        TeamResponse getResult = actualAll.get(0);
        assertEquals(5000000.0d, getResult.getTeamValue());
        TeamResponse getResult2 = actualAll.get(1);
        assertEquals(10.0d, getResult2.getTeamValue());
        assertEquals("Team Name", getResult2.getTeamName());
        assertEquals("GB", getResult2.getTeamCountry());
        assertEquals(players, getResult2.getPlayers());
        assertEquals(1, getResult2.getId());
        assertEquals("com.turkcell.socceronlinemanagement.model.Team", getResult.getTeamName());
        assertEquals("GBR", getResult.getTeamCountry());
        assertEquals(players, getResult.getPlayers());
        assertEquals(2, getResult.getId());
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
        ArrayList<Player> players = new ArrayList<>();
        team.setPlayers(players);
        team.setTeamCountry("GB");
        team.setTeamName("Team Name");
        team.setTeamValue(10.0d);
        team.setUser(user);
        TeamRepository repository = mock(TeamRepository.class);
        when(repository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(team));
        TeamRepository repository2 = mock(TeamRepository.class);
        when(repository2.existsById(Mockito.<Integer>any())).thenReturn(true);
        TeamBusinessRules rules = new TeamBusinessRules(repository2);
        ModelMapper mapper = new ModelMapper();
        PlayerRepository repository3 = mock(PlayerRepository.class);
        ModelMapper mapper2 = new ModelMapper();
        PlayerImpl playerManager = new PlayerImpl(repository3, mapper2,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        PlayerRepository playerRepository = mock(PlayerRepository.class);
        TeamBusinessRules teamBusinessRules = new TeamBusinessRules(mock(TeamRepository.class));
        TransferBusinessRules transferBusinessRules = new TransferBusinessRules(mock(TransferRepository.class));
        TransferRepository transferRepository = mock(TransferRepository.class);
        PlayerBusinessRules playerBusinessRules = new PlayerBusinessRules(mock(PlayerRepository.class));
        TeamRepository teamRepository = mock(TeamRepository.class);
        PlayerRepository repository4 = mock(PlayerRepository.class);
        ModelMapper mapper3 = new ModelMapper();
        PlayerImpl playerService = new PlayerImpl(repository4, mapper3,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        TeamResponse actualById = (new TeamImpl(repository, mapper, rules, playerManager, playerRepository,
                teamBusinessRules, transferBusinessRules, transferRepository, playerBusinessRules, teamRepository,
                playerService, new Random())).getById(1);
        assertEquals(1, actualById.getId());
        assertEquals(10.0d, actualById.getTeamValue());
        assertEquals("Team Name", actualById.getTeamName());
        assertEquals("GB", actualById.getTeamCountry());
        assertEquals(players, actualById.getPlayers());
        verify(repository).findById(Mockito.<Integer>any());
        verify(repository2).existsById(Mockito.<Integer>any());
    }

    @Test
    void testGetById2() {

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

        ArrayList<Player> players = new ArrayList<>();
        players.add(player);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setId(1);
        user2.setPassword("iloveyou");
        user2.setRole(Role.USER);
        user2.setTeams(new ArrayList<>());

        Team team2 = new Team();
        team2.setId(1);
        team2.setPlayers(players);
        team2.setTeamCountry("GB");
        team2.setTeamName("Team Name");
        team2.setTeamValue(10.0d);
        team2.setUser(user2);
        TeamRepository repository = mock(TeamRepository.class);
        when(repository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(team2));
        TeamRepository repository2 = mock(TeamRepository.class);
        when(repository2.existsById(Mockito.<Integer>any())).thenReturn(true);
        TeamBusinessRules rules = new TeamBusinessRules(repository2);
        ModelMapper mapper = new ModelMapper();
        PlayerRepository repository3 = mock(PlayerRepository.class);
        ModelMapper mapper2 = new ModelMapper();
        PlayerImpl playerManager = new PlayerImpl(repository3, mapper2,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        PlayerRepository playerRepository = mock(PlayerRepository.class);
        TeamBusinessRules teamBusinessRules = new TeamBusinessRules(mock(TeamRepository.class));
        TransferBusinessRules transferBusinessRules = new TransferBusinessRules(mock(TransferRepository.class));
        TransferRepository transferRepository = mock(TransferRepository.class);
        PlayerBusinessRules playerBusinessRules = new PlayerBusinessRules(mock(PlayerRepository.class));
        TeamRepository teamRepository = mock(TeamRepository.class);
        PlayerRepository repository4 = mock(PlayerRepository.class);
        ModelMapper mapper3 = new ModelMapper();
        PlayerImpl playerService = new PlayerImpl(repository4, mapper3,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        TeamResponse actualById = (new TeamImpl(repository, mapper, rules, playerManager, playerRepository,
                teamBusinessRules, transferBusinessRules, transferRepository, playerBusinessRules, teamRepository,
                playerService, new Random())).getById(1);
        assertEquals(1, actualById.getId());
        assertEquals(10.0d, actualById.getTeamValue());
        assertEquals("Team Name", actualById.getTeamName());
        assertEquals("GB", actualById.getTeamCountry());
        assertEquals(1, actualById.getPlayers().size());
        verify(repository).findById(Mockito.<Integer>any());
        verify(repository2).existsById(Mockito.<Integer>any());
    }

    @Test
    void testGetById3() {
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

        User user2 = new User();
        user2.setEmail("john.smith@example.org");
        user2.setId(2);
        user2.setPassword("Password");
        user2.setRole(Role.ADMIN);
        user2.setTeams(new ArrayList<>());

        Team team2 = new Team();
        team2.setId(2);
        team2.setPlayers(new ArrayList<>());
        team2.setTeamCountry("GBR");
        team2.setTeamName("com.turkcell.socceronlinemanagement.model.Team");
        team2.setTeamValue(5000000.0d);
        team2.setUser(user2);

        Player player2 = new Player();
        player2.setAge(Short.SIZE);
        player2.setCountry("GBR");
        player2.setFirstName("John");
        player2.setId(2);
        player2.setLastName("Smith");
        player2.setMarketValue(1000000.0d);
        player2.setPosition(Position.DEFENDER);
        player2.setTeam(team2);
        player2.setTransferState(TransferState.NOT_TRANSFERRED);
        player2.setTransfers(new ArrayList<>());

        ArrayList<Player> players = new ArrayList<>();
        players.add(player2);
        players.add(player);

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setId(1);
        user3.setPassword("iloveyou");
        user3.setRole(Role.USER);
        user3.setTeams(new ArrayList<>());

        Team team3 = new Team();
        team3.setId(1);
        team3.setPlayers(players);
        team3.setTeamCountry("GB");
        team3.setTeamName("Team Name");
        team3.setTeamValue(10.0d);
        team3.setUser(user3);
        TeamRepository repository = mock(TeamRepository.class);
        when(repository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(team3));
        TeamRepository repository2 = mock(TeamRepository.class);
        when(repository2.existsById(Mockito.<Integer>any())).thenReturn(true);
        TeamBusinessRules rules = new TeamBusinessRules(repository2);
        ModelMapper mapper = new ModelMapper();
        PlayerRepository repository3 = mock(PlayerRepository.class);
        ModelMapper mapper2 = new ModelMapper();
        PlayerImpl playerManager = new PlayerImpl(repository3, mapper2,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        PlayerRepository playerRepository = mock(PlayerRepository.class);
        TeamBusinessRules teamBusinessRules = new TeamBusinessRules(mock(TeamRepository.class));
        TransferBusinessRules transferBusinessRules = new TransferBusinessRules(mock(TransferRepository.class));
        TransferRepository transferRepository = mock(TransferRepository.class);
        PlayerBusinessRules playerBusinessRules = new PlayerBusinessRules(mock(PlayerRepository.class));
        TeamRepository teamRepository = mock(TeamRepository.class);
        PlayerRepository repository4 = mock(PlayerRepository.class);
        ModelMapper mapper3 = new ModelMapper();
        PlayerImpl playerService = new PlayerImpl(repository4, mapper3,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        TeamResponse actualById = (new TeamImpl(repository, mapper, rules, playerManager, playerRepository,
                teamBusinessRules, transferBusinessRules, transferRepository, playerBusinessRules, teamRepository,
                playerService, new Random())).getById(1);
        assertEquals(1, actualById.getId());
        assertEquals(10.0d, actualById.getTeamValue());
        assertEquals("Team Name", actualById.getTeamName());
        assertEquals("GB", actualById.getTeamCountry());
        assertEquals(2, actualById.getPlayers().size());
        verify(repository).findById(Mockito.<Integer>any());
        verify(repository2).existsById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link TeamImpl#getById(int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetById4() {
     TeamRepository repository = mock(TeamRepository.class);
        when(repository.findById(Mockito.<Integer>any())).thenReturn(Optional.empty());
        TeamRepository repository2 = mock(TeamRepository.class);
        when(repository2.existsById(Mockito.<Integer>any())).thenReturn(true);
        TeamBusinessRules rules = new TeamBusinessRules(repository2);
        ModelMapper mapper = new ModelMapper();
        PlayerRepository repository3 = mock(PlayerRepository.class);
        ModelMapper mapper2 = new ModelMapper();
        PlayerImpl playerManager = new PlayerImpl(repository3, mapper2,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        PlayerRepository playerRepository = mock(PlayerRepository.class);
        TeamBusinessRules teamBusinessRules = new TeamBusinessRules(mock(TeamRepository.class));
        TransferBusinessRules transferBusinessRules = new TransferBusinessRules(mock(TransferRepository.class));
        TransferRepository transferRepository = mock(TransferRepository.class);
        PlayerBusinessRules playerBusinessRules = new PlayerBusinessRules(mock(PlayerRepository.class));
        TeamRepository teamRepository = mock(TeamRepository.class);
        PlayerRepository repository4 = mock(PlayerRepository.class);
        ModelMapper mapper3 = new ModelMapper();
        PlayerImpl playerService = new PlayerImpl(repository4, mapper3,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        (new TeamImpl(repository, mapper, rules, playerManager, playerRepository, teamBusinessRules,
                transferBusinessRules, transferRepository, playerBusinessRules, teamRepository, playerService, new Random()))
                .getById(1);
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testAdd() {

        teamImpl.add(new TeamRequest());
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
        TeamRepository repository = mock(TeamRepository.class);
        when(repository.save(Mockito.<Team>any())).thenReturn(team);
        TeamRepository repository2 = mock(TeamRepository.class);
        when(repository2.existsById(Mockito.<Integer>any())).thenReturn(true);
        TeamBusinessRules rules = new TeamBusinessRules(repository2);
        ModelMapper mapper = new ModelMapper();
        PlayerRepository repository3 = mock(PlayerRepository.class);
        ModelMapper mapper2 = new ModelMapper();
        PlayerImpl playerManager = new PlayerImpl(repository3, mapper2,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        PlayerRepository playerRepository = mock(PlayerRepository.class);
        TeamBusinessRules teamBusinessRules = new TeamBusinessRules(mock(TeamRepository.class));
        TransferBusinessRules transferBusinessRules = new TransferBusinessRules(mock(TransferRepository.class));
        TransferRepository transferRepository = mock(TransferRepository.class);
        PlayerBusinessRules playerBusinessRules = new PlayerBusinessRules(mock(PlayerRepository.class));
        TeamRepository teamRepository = mock(TeamRepository.class);
        PlayerRepository repository4 = mock(PlayerRepository.class);
        ModelMapper mapper3 = new ModelMapper();
        PlayerImpl playerService = new PlayerImpl(repository4, mapper3,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        TeamImpl teamImpl = new TeamImpl(repository, mapper, rules, playerManager, playerRepository, teamBusinessRules,
                transferBusinessRules, transferRepository, playerBusinessRules, teamRepository, playerService, new Random());
        TeamResponse actualUpdateResult = teamImpl.update(1, new TeamRequest());
        assertEquals(1, actualUpdateResult.getId());
        assertEquals(5000000.0d, actualUpdateResult.getTeamValue());
        assertNull(actualUpdateResult.getPlayers());
        verify(repository).save(Mockito.<Team>any());
        verify(repository2).existsById(Mockito.<Integer>any());
    }

    @Test
    void testDelete() {
        TeamRepository repository = mock(TeamRepository.class);
        doNothing().when(repository).deleteById(Mockito.<Integer>any());
        TeamRepository repository2 = mock(TeamRepository.class);
        when(repository2.existsById(Mockito.<Integer>any())).thenReturn(true);
        TeamBusinessRules rules = new TeamBusinessRules(repository2);
        ModelMapper mapper = new ModelMapper();
        PlayerRepository repository3 = mock(PlayerRepository.class);
        ModelMapper mapper2 = new ModelMapper();
        PlayerImpl playerManager = new PlayerImpl(repository3, mapper2,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        PlayerRepository playerRepository = mock(PlayerRepository.class);
        TeamBusinessRules teamBusinessRules = new TeamBusinessRules(mock(TeamRepository.class));
        TransferBusinessRules transferBusinessRules = new TransferBusinessRules(mock(TransferRepository.class));
        TransferRepository transferRepository = mock(TransferRepository.class);
        PlayerBusinessRules playerBusinessRules = new PlayerBusinessRules(mock(PlayerRepository.class));
        TeamRepository teamRepository = mock(TeamRepository.class);
        PlayerRepository repository4 = mock(PlayerRepository.class);
        ModelMapper mapper3 = new ModelMapper();
        PlayerImpl playerService = new PlayerImpl(repository4, mapper3,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        (new TeamImpl(repository, mapper, rules, playerManager, playerRepository, teamBusinessRules,
                transferBusinessRules, transferRepository, playerBusinessRules, teamRepository, playerService, new Random()))
                .delete(1);
        verify(repository).deleteById(Mockito.<Integer>any());
        verify(repository2).existsById(Mockito.<Integer>any());
    }

    @Test
    void testAddTransferPlayer() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        user.setTeams(new ArrayList<>());

        Team team = new Team();
        team.setId(1);
        ArrayList<Player> players = new ArrayList<>();
        team.setPlayers(players);
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
        PlayerRepository playerRepository = mock(PlayerRepository.class);
        when(playerRepository.save(Mockito.<Player>any())).thenReturn(player2);
        when(playerRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        TeamRepository repository = mock(TeamRepository.class);
        when(repository.existsById(Mockito.<Integer>any())).thenReturn(true);
        TeamBusinessRules teamBusinessRules = new TeamBusinessRules(repository);
        TransferRepository repository2 = mock(TransferRepository.class);
        when(repository2.existsById(Mockito.<Integer>any())).thenReturn(true);
        TransferBusinessRules transferBusinessRules = new TransferBusinessRules(repository2);

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
        TransferRepository transferRepository = mock(TransferRepository.class);
        when(transferRepository.findByPlayerId(anyInt())).thenReturn(transfer);
        doNothing().when(transferRepository).delete(Mockito.<Transfer>any());
        PlayerRepository repository3 = mock(PlayerRepository.class);
        when(repository3.existsById(Mockito.<Integer>any())).thenReturn(true);
        PlayerBusinessRules playerBusinessRules = new PlayerBusinessRules(repository3);

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
        TeamRepository teamRepository = mock(TeamRepository.class);
        when(teamRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(team4));

        User user5 = new User();
        user5.setEmail("jane.doe@example.org");
        user5.setId(1);
        user5.setPassword("iloveyou");
        user5.setRole(Role.USER);
        user5.setTeams(new ArrayList<>());

        Team team5 = new Team();
        team5.setId(1);
        team5.setPlayers(new ArrayList<>());
        team5.setTeamCountry("GB");
        team5.setTeamName("Team Name");
        team5.setTeamValue(10.0d);
        team5.setUser(user5);

        Player player4 = new Player();
        player4.setAge(1);
        player4.setCountry("GB");
        player4.setFirstName("Jane");
        player4.setId(1);
        player4.setLastName("Doe");
        player4.setMarketValue(10.0d);
        player4.setPosition(Position.GOALKEEPER);
        player4.setTeam(team5);
        player4.setTransferState(TransferState.TRANSFERRED);
        player4.setTransfers(new ArrayList<>());
        Optional<Player> ofResult2 = Optional.of(player4);

        User user6 = new User();
        user6.setEmail("jane.doe@example.org");
        user6.setId(1);
        user6.setPassword("iloveyou");
        user6.setRole(Role.USER);
        user6.setTeams(new ArrayList<>());

        Team team6 = new Team();
        team6.setId(1);
        team6.setPlayers(new ArrayList<>());
        team6.setTeamCountry("GB");
        team6.setTeamName("Team Name");
        team6.setTeamValue(10.0d);
        team6.setUser(user6);

        Player player5 = new Player();
        player5.setAge(1);
        player5.setCountry("GB");
        player5.setFirstName("Jane");
        player5.setId(1);
        player5.setLastName("Doe");
        player5.setMarketValue(10.0d);
        player5.setPosition(Position.GOALKEEPER);
        player5.setTeam(team6);
        player5.setTransferState(TransferState.TRANSFERRED);
        player5.setTransfers(new ArrayList<>());
        PlayerRepository repository4 = mock(PlayerRepository.class);
        when(repository4.save(Mockito.<Player>any())).thenReturn(player5);
        when(repository4.findById(Mockito.<Integer>any())).thenReturn(ofResult2);
        ModelMapper mapper = new ModelMapper();
        PlayerImpl playerService = new PlayerImpl(repository4, mapper,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        TeamRepository repository5 = mock(TeamRepository.class);
        ModelMapper mapper2 = new ModelMapper();
        TeamBusinessRules rules = new TeamBusinessRules(mock(TeamRepository.class));
        PlayerRepository repository6 = mock(PlayerRepository.class);
        ModelMapper mapper3 = new ModelMapper();
        PlayerImpl playerManager = new PlayerImpl(repository6, mapper3,
                new PlayerBusinessRules(mock(PlayerRepository.class)));

        TeamImpl teamImpl = new TeamImpl(repository5, mapper2, rules, playerManager, playerRepository, teamBusinessRules,
                transferBusinessRules, transferRepository, playerBusinessRules, teamRepository, playerService, new Random());

        TransferPlayerRequest request = new TransferPlayerRequest();
        request.setPlayerId(1);
        request.setPlayerMarketValue(10.0d);
        request.setPrice(10.0d);
        request.setTeamId(1);
        TeamResponse actualAddTransferPlayerResult = teamImpl.addTransferPlayer(request);
        assertEquals(1, actualAddTransferPlayerResult.getId());
        assertEquals(10.0d, actualAddTransferPlayerResult.getTeamValue());
        assertEquals("Team Name", actualAddTransferPlayerResult.getTeamName());
        assertEquals("GB", actualAddTransferPlayerResult.getTeamCountry());
        assertEquals(players, actualAddTransferPlayerResult.getPlayers());
        verify(playerRepository).save(Mockito.<Player>any());
        verify(playerRepository, atLeast(1)).findById(Mockito.<Integer>any());
        verify(repository).existsById(Mockito.<Integer>any());
        verify(repository2).existsById(Mockito.<Integer>any());
        verify(transferRepository).findByPlayerId(anyInt());
        verify(transferRepository).delete(Mockito.<Transfer>any());
        verify(repository3).existsById(Mockito.<Integer>any());
        verify(teamRepository, atLeast(1)).findById(Mockito.<Integer>any());
        verify(repository4).save(Mockito.<Player>any());
        verify(repository4).findById(Mockito.<Integer>any());
    }
}

