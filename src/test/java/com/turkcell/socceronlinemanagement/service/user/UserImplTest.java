package com.turkcell.socceronlinemanagement.service.user;

import com.turkcell.socceronlinemanagement.model.Team;
import com.turkcell.socceronlinemanagement.model.User;
import com.turkcell.socceronlinemanagement.model.enums.Role;
import com.turkcell.socceronlinemanagement.repository.UserRepository;
import com.turkcell.socceronlinemanagement.security.filter.JwtService;
import com.turkcell.socceronlinemanagement.service.team.TeamService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserImpl.class})
@ExtendWith(SpringExtension.class)
class UserImplTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private TeamService teamService;

    @MockBean
    private UserBusinessRules userBusinessRules;

    @Autowired
    private UserImpl userImpl;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link UserImpl#register(UserRegisterRequest)}
     */
    @Test
    void testRegister() {
        User user = new User();
        user.setEmail("merve@gmail.com");
        user.setId(1);
        user.setPassword("12345678");
        user.setRole(Role.USER);
        ArrayList<Team> teams = new ArrayList<>();
        user.setTeams(teams);
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);
        when(jwtService.generateToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(teamService.getAll()).thenReturn(new ArrayList<>());
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
        UserResponse actualRegisterResult = userImpl
                .register(new UserRegisterRequest("merve@gmail.com", "12345678", Role.USER));
        assertEquals("merve@gmail.com", actualRegisterResult.getEmail());
        assertEquals("ABC123", actualRegisterResult.getToken());
        assertEquals(0, actualRegisterResult.getId());
        assertEquals(teams, actualRegisterResult.getTeams());
        assertEquals("secret", actualRegisterResult.getPassword());
        User user2 = actualRegisterResult.getUser();
        assertEquals("merve@gmail.com", user2.getUsername());
        assertEquals(Role.USER, user2.getRole());
        assertEquals("secret", user2.getPassword());
        verify(userRepository).save(Mockito.<User>any());
        verify(jwtService).generateToken(Mockito.<UserDetails>any());
        verify(teamService).getAll();
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    /**
     * Method under test: {@link UserImpl#register(UserRegisterRequest)}
     */
    @Test
    void testRegister2() {
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenThrow(new RuntimeException("foo"));
        assertThrows(RuntimeException.class,
                () -> userImpl.register(new UserRegisterRequest("merve@gmail.com", "12345678", Role.USER)));
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    /**
     * Method under test: {@link UserImpl#register(UserRegisterRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRegister3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.turkcell.socceronlinemanagement.service.user.UserRegisterRequest.getRole()" because "request" is null
        //       at com.turkcell.socceronlinemanagement.service.user.UserImpl.register(UserImpl.java:32)
        //   See https://diff.blue/R013 to resolve this issue.

        User user = new User();
        user.setEmail("merve@gmail.com");
        user.setId(1);
        user.setPassword("12345678");
        user.setRole(Role.USER);
        user.setTeams(new ArrayList<>());
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);
        when(jwtService.generateToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(teamService.getAll()).thenReturn(new ArrayList<>());
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
        userImpl.register(null);
    }

    /**
     * Method under test: {@link UserImpl#authenticate(UserAuthRequest)}
     */
    @Test
    void testAuthenticate() throws AuthenticationException {
        User user = new User();
        user.setEmail("merve@gmail.com");
        user.setId(1);
        user.setPassword("12345678");
        user.setRole(Role.USER);
        ArrayList<Team> teams = new ArrayList<>();
        user.setTeams(teams);
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        when(jwtService.generateToken(Mockito.<Map<String, Object>>any(), Mockito.<UserDetails>any()))
                .thenReturn("ABC123");
        when(teamService.getAll()).thenReturn(new ArrayList<>());
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        UserResponse actualAuthenticateResult = userImpl
                .authenticate(new UserAuthRequest("merve@gmail.com", "12345678"));
        assertEquals("merve@gmail.com", actualAuthenticateResult.getEmail());
        assertSame(user, actualAuthenticateResult.getUser());
        assertEquals("ABC123", actualAuthenticateResult.getToken());
        assertEquals(1, actualAuthenticateResult.getId());
        assertEquals(teams, actualAuthenticateResult.getTeams());
        assertEquals("12345678", actualAuthenticateResult.getPassword());
        verify(userRepository).findByEmail(Mockito.<String>any());
        verify(jwtService).generateToken(Mockito.<Map<String, Object>>any(), Mockito.<UserDetails>any());
        verify(teamService).getAll();
        verify(authenticationManager).authenticate(Mockito.<Authentication>any());
    }

    /**
     * Method under test: {@link UserImpl#authenticate(UserAuthRequest)}
     */
    @Test
    void testAuthenticate2() throws AuthenticationException {
        User user = new User();
        user.setEmail("merve@gmail.com");
        user.setId(1);
        user.setPassword("12345678");
        user.setRole(Role.USER);
        user.setTeams(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        when(authenticationManager.authenticate(Mockito.<Authentication>any())).thenThrow(new RuntimeException("roles"));
        assertThrows(RuntimeException.class,
                () -> userImpl.authenticate(new UserAuthRequest("merve@gmail.com", "12345678")));
        verify(userRepository).findByEmail(Mockito.<String>any());
        verify(authenticationManager).authenticate(Mockito.<Authentication>any());
    }

    /**
     * Method under test: {@link UserImpl#authenticate(UserAuthRequest)}
     */
    @Test
    void testAuthenticate3() {
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class,
                () -> userImpl.authenticate(new UserAuthRequest("merve@gmail.com", "12345678")));
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserImpl#authenticate(UserAuthRequest)}
     */
    @Test
    void testAuthenticate4() {
        assertThrows(RuntimeException.class, () -> userImpl.authenticate(null));
    }

    /**
     * Method under test: {@link UserImpl#getAll()}
     */
    @Test
    void testGetAll() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(userImpl.getAll().isEmpty());
        verify(userRepository).findAll();
    }

    /**
     * Method under test: {@link UserImpl#getAll()}
     */
    @Test
    void testGetAll2() {
        User user = new User();
        user.setEmail("merve@gmail.com");
        user.setId(1);
        user.setPassword("12345678");
        user.setRole(Role.USER);
        user.setTeams(new ArrayList<>());

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<UserResponse>>any())).thenReturn(new UserResponse());
        assertEquals(1, userImpl.getAll().size());
        verify(userRepository).findAll();
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<UserResponse>>any());
    }

    /**
     * Method under test: {@link UserImpl#getAll()}
     */
    @Test
    void testGetAll3() {
        User user = new User();
        user.setEmail("merve@gmail.com");
        user.setId(1);
        user.setPassword("12345678");
        user.setRole(Role.USER);
        user.setTeams(new ArrayList<>());

        User user2 = new User();
        user2.setEmail("john.smith@example.org");
        user2.setId(2);
        user2.setPassword("Password");
        user2.setRole(Role.ADMIN);
        user2.setTeams(new ArrayList<>());

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user2);
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<UserResponse>>any())).thenReturn(new UserResponse());
        assertEquals(2, userImpl.getAll().size());
        verify(userRepository).findAll();
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<UserResponse>>any());
    }

    /**
     * Method under test: {@link UserImpl#getAll()}
     */
    @Test
    void testGetAll4() {
        User user = new User();
        user.setEmail("merve@gmail.com");
        user.setId(1);
        user.setPassword("12345678");
        user.setRole(Role.USER);
        user.setTeams(new ArrayList<>());

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<UserResponse>>any()))
                .thenThrow(new RuntimeException("foo"));
        assertThrows(RuntimeException.class, () -> userImpl.getAll());
        verify(userRepository).findAll();
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<UserResponse>>any());
    }

    /**
     * Method under test: {@link UserImpl#getById(int)}
     */
    @Test
    void testGetById() {
        User user = new User();
        user.setEmail("merve@gmail.com");
        user.setId(1);
        user.setPassword("12345678");
        user.setRole(Role.USER);
        user.setTeams(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        UserResponse userResponse = new UserResponse();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<UserResponse>>any())).thenReturn(userResponse);
        doNothing().when(userBusinessRules).checkIfUserExistsById(anyInt());
        assertSame(userResponse, userImpl.getById(1));
        verify(userRepository).findById(Mockito.<Integer>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<UserResponse>>any());
        verify(userBusinessRules).checkIfUserExistsById(anyInt());
    }

    /**
     * Method under test: {@link UserImpl#getById(int)}
     */
    @Test
    void testGetById2() {
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<UserResponse>>any())).thenReturn(new UserResponse());
        doThrow(new RuntimeException("foo")).when(userBusinessRules).checkIfUserExistsById(anyInt());
        assertThrows(RuntimeException.class, () -> userImpl.getById(1));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
        verify(userBusinessRules).checkIfUserExistsById(anyInt());
    }

    /**
     * Method under test: {@link UserImpl#add(UserAuthRequest)}
     */
    @Test
    void testAdd() {
        doThrow(new RuntimeException("foo")).when(userBusinessRules).checkIfUserExistsByEmail(Mockito.<String>any());
        assertThrows(RuntimeException.class, () -> userImpl.add(new UserAuthRequest("merve@gmail.com", "12345678")));
        verify(userBusinessRules).checkIfUserExistsByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserImpl#add(UserAuthRequest)}
     */
    @Test
    void testAdd2() {
        User user = mock(User.class);
        doNothing().when(user).setEmail(Mockito.<String>any());
        doNothing().when(user).setId(anyInt());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<Role>any());
        doNothing().when(user).setTeams(Mockito.<List<Team>>any());
        user.setEmail("merve@gmail.com");
        user.setId(1);
        user.setPassword("12345678");
        user.setRole(Role.USER);
        user.setTeams(new ArrayList<>());
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn(null);
        doNothing().when(userBusinessRules).checkIfUserExistsByEmail(Mockito.<String>any());
        assertNull(userImpl.add(new UserAuthRequest("merve@gmail.com", "12345678")));
        verify(userRepository).save(Mockito.<User>any());
        verify(user).setEmail(Mockito.<String>any());
        verify(user).setId(anyInt());
        verify(user).setPassword(Mockito.<String>any());
        verify(user).setRole(Mockito.<Role>any());
        verify(user).setTeams(Mockito.<List<Team>>any());
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
        verify(userBusinessRules).checkIfUserExistsByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserImpl#add(UserAuthRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAdd3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.turkcell.socceronlinemanagement.service.user.UserAuthRequest.getEmail()" because "request" is null
        //       at com.turkcell.socceronlinemanagement.service.user.UserImpl.add(UserImpl.java:96)
        //   See https://diff.blue/R013 to resolve this issue.

        User user = mock(User.class);
        doNothing().when(user).setEmail(Mockito.<String>any());
        doNothing().when(user).setId(anyInt());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<Role>any());
        doNothing().when(user).setTeams(Mockito.<List<Team>>any());
        user.setEmail("merve@gmail.com");
        user.setId(1);
        user.setPassword("12345678");
        user.setRole(Role.USER);
        user.setTeams(new ArrayList<>());
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
        doNothing().when(userBusinessRules).checkIfUserExistsByEmail(Mockito.<String>any());
        userImpl.add(null);
    }

    /**
     * Method under test: {@link UserImpl#update(int, UserAuthRequest)}
     */
    @Test
    void testUpdate() {
        User user = new User();
        user.setEmail("merve@gmail.com");
        user.setId(1);
        user.setPassword("12345678");
        user.setRole(Role.USER);
        user.setTeams(new ArrayList<>());
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<User>>any())).thenReturn(user);
        doThrow(new RuntimeException("foo")).when(userBusinessRules).checkIfUserExistsById(anyInt());
        assertThrows(RuntimeException.class,
                () -> userImpl.update(1, new UserAuthRequest("merve@gmail.com", "12345678")));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
        verify(userBusinessRules).checkIfUserExistsById(anyInt());
    }

    /**
     * Method under test: {@link UserImpl#delete(int)}
     */
    @Test
    void testDelete() {
        doNothing().when(userRepository).deleteById(Mockito.<Integer>any());
        doNothing().when(userBusinessRules).checkIfUserExistsById(anyInt());
        userImpl.delete(1);
        verify(userRepository).deleteById(Mockito.<Integer>any());
        verify(userBusinessRules).checkIfUserExistsById(anyInt());
    }

    /**
     * Method under test: {@link UserImpl#delete(int)}
     */
    @Test
    void testDelete2() {
        doThrow(new RuntimeException("foo")).when(userBusinessRules).checkIfUserExistsById(anyInt());
        assertThrows(RuntimeException.class, () -> userImpl.delete(1));
        verify(userBusinessRules).checkIfUserExistsById(anyInt());
    }
}

