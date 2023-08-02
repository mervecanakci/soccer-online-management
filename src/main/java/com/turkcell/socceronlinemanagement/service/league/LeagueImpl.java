package com.turkcell.socceronlinemanagement.service.league;

import com.turkcell.socceronlinemanagement.model.League;
import com.turkcell.socceronlinemanagement.repository.LeagueRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeagueImpl  implements LeagueService {

    private final LeagueRepository repository;
    private final ModelMapper mapper;
    private final LeagueBusinessRules rules;

    @Override
    public List<LeagueResponse> getAll() {
        List<League> leagues = repository.findAll();
        List<LeagueResponse> response = leagues
                .stream()
                .map(league -> mapper.map(league, LeagueResponse.class))
                .toList();

        return response;
    }

    @Override
    public LeagueResponse getById(Integer id) {
        rules.checkIfLeagueExistsById(id);
        League league = repository.findById(id).orElseThrow();
        LeagueResponse response = mapper.map(league, LeagueResponse.class);

        return response;
    }

    @Override
    public LeagueResponse add(LeagueRequest request) {
        League league = mapper.map(request, League.class);
        league.setId(0);
        repository.save(league);
        LeagueResponse response = mapper.map(league, LeagueResponse.class);

        return response;
    }

    @Override
    public LeagueResponse update(Integer id, LeagueRequest request) {
        rules.checkIfLeagueExistsById(id);
        League league = mapper.map(request, League.class);
        league.setId(id);
        repository.save(league);
        LeagueResponse response = mapper.map(league, LeagueResponse.class);

        return response;
    }

    @Override
    public void delete(Integer id) {
        rules.checkIfLeagueExistsById(id);
        repository.deleteById(id);
    }
}