package com.turkcell.socceronlinemanagement.service.player;

import com.turkcell.socceronlinemanagement.model.Player;
import com.turkcell.socceronlinemanagement.model.Team;
import com.turkcell.socceronlinemanagement.model.Transfer;
import com.turkcell.socceronlinemanagement.model.User;
import com.turkcell.socceronlinemanagement.model.enums.Position;
import com.turkcell.socceronlinemanagement.model.enums.Role;
import com.turkcell.socceronlinemanagement.model.enums.TransferState;
import com.turkcell.socceronlinemanagement.repository.PlayerRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PlayerImplTest {
    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private PlayerBusinessRules playerBusinessRules;

    @MockBean
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerImpl playerImpl;

    @Test
    void testGetAll() {
        when(playerRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(playerImpl.getAll(true).isEmpty());
        verify(playerRepository).findAll();
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

        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(player);
        when(playerRepository.findAll()).thenReturn(playerList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<PlayerResponse>>any())).thenReturn(
                new PlayerResponse(1, 1, 1, "GB", "Doe", "Jane", 10.0d, Position.GOALKEEPER, TransferState.TRANSFERRED));
        assertEquals(1, playerImpl.getAll(true).size());
        verify(playerRepository).findAll();
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<PlayerResponse>>any());
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
        player2.setAge(0);
        player2.setCountry("GBR");
        player2.setFirstName("John");
        player2.setId(2);
        player2.setLastName("Smith");
        player2.setMarketValue(1000000.0d);
        player2.setPosition(Position.DEFENDER);
        player2.setTeam(team2);
        player2.setTransferState(TransferState.NOT_TRANSFERRED);
        player2.setTransfers(new ArrayList<>());

        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(player2);
        playerList.add(player);
        when(playerRepository.findAll()).thenReturn(playerList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<PlayerResponse>>any())).thenReturn(
                new PlayerResponse(1, 1, 1, "GB", "Doe", "Jane", 10.0d, Position.GOALKEEPER, TransferState.TRANSFERRED));
        assertEquals(2, playerImpl.getAll(true).size());
        verify(playerRepository).findAll();
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<PlayerResponse>>any());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testGetAll4() {
        when(playerRepository.findAllByTransferStateIsNot(Mockito.<TransferState>any())).thenReturn(new ArrayList<>());
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<PlayerResponse>>any())).thenReturn(
                new PlayerResponse(1, 1, 1, "GB", "Doe", "Jane", 10.0d, Position.GOALKEEPER, TransferState.TRANSFERRED));
        assertTrue(playerImpl.getAll(false).isEmpty());
        verify(playerRepository).findAllByTransferStateIsNot(Mockito.<TransferState>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
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
        Optional<Player> ofResult = Optional.of(player);
        when(playerRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        PlayerResponse playerResponse = new PlayerResponse(1, 1, 1, "GB", "Doe", "Jane", 10.0d, Position.GOALKEEPER,
                TransferState.TRANSFERRED);

        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<PlayerResponse>>any())).thenReturn(playerResponse);
        doNothing().when(playerBusinessRules).checkIfPlayerExistsById(anyInt());
        assertSame(playerResponse, playerImpl.getById(1));
        verify(playerRepository).findById(Mockito.<Integer>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<PlayerResponse>>any());
        verify(playerBusinessRules).checkIfPlayerExistsById(anyInt());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testGetById2() {

        when(playerRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.empty());
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<PlayerResponse>>any())).thenReturn(
                new PlayerResponse(1, 1, 1, "GB", "Doe", "Jane", 10.0d, Position.GOALKEEPER, TransferState.TRANSFERRED));
        doNothing().when(playerBusinessRules).checkIfPlayerExistsById(anyInt());
        playerImpl.getById(1);
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testAdd() {

        playerImpl.add(new PlayerRequest());
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
        PlayerRepository repository = mock(PlayerRepository.class);
        when(repository.save(Mockito.<Player>any())).thenReturn(player);
        PlayerRepository repository2 = mock(PlayerRepository.class);
        when(repository2.existsById(Mockito.<Integer>any())).thenReturn(true);
        PlayerBusinessRules rules = new PlayerBusinessRules(repository2);
        PlayerImpl playerImpl = new PlayerImpl(repository, new ModelMapper(), rules);
        PlayerResponse actualUpdateResult = playerImpl.update(1, new PlayerRequest());
        assertEquals(TransferState.NOT_TRANSFERRED, actualUpdateResult.getTransferState());
        assertEquals(0, actualUpdateResult.getTeamId());
        assertEquals(1000000.0d, actualUpdateResult.getMarketValue());
        assertEquals(0, actualUpdateResult.getId());
        verify(repository).save(Mockito.<Player>any());
        verify(repository2).existsById(Mockito.<Integer>any());
    }

    @Test
    void testUpdate2() {

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
        ArrayList<Transfer> transfers = new ArrayList<>();

        Player player = new Player(1, 1, "GB", "Jane", "Doe", 1000000.0d, Position.GOALKEEPER, TransferState.TRANSFERRED,
                transfers, new Team());
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
        PlayerRepository repository2 = mock(PlayerRepository.class);
        when(repository2.existsById(Mockito.<Integer>any())).thenReturn(true);
        PlayerBusinessRules rules = new PlayerBusinessRules(repository2);
        PlayerImpl playerImpl = new PlayerImpl(repository, new ModelMapper(), rules);
        PlayerResponse actualUpdateResult = playerImpl.update(1, new PlayerRequest());
        assertEquals(TransferState.NOT_TRANSFERRED, actualUpdateResult.getTransferState());
        assertEquals(0, actualUpdateResult.getTeamId());
        assertEquals(1000000.0d, actualUpdateResult.getMarketValue());
        assertEquals(0, actualUpdateResult.getId());
        verify(repository).save(Mockito.<Player>any());
        verify(repository2).existsById(Mockito.<Integer>any());
    }

    @Test
    void testDelete() {
        doNothing().when(playerRepository).deleteById(Mockito.<Integer>any());
        doNothing().when(playerBusinessRules).checkIfPlayerExistsById(anyInt());
        playerImpl.delete(1);
        verify(playerRepository).deleteById(Mockito.<Integer>any());
        verify(playerBusinessRules).checkIfPlayerExistsById(anyInt());
    }

    @Test
    void testChangeTransferState() {
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
        when(playerRepository.save(Mockito.<Player>any())).thenReturn(player2);
        when(playerRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        playerImpl.changeTransferState(1, TransferState.TRANSFERRED);
        verify(playerRepository).save(Mockito.<Player>any());
        verify(playerRepository).findById(Mockito.<Integer>any());
    }
}

