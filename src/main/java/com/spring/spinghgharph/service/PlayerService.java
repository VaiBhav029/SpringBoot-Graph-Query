package com.spring.spinghgharph.service;

import com.spring.spinghgharph.model.Player;
import com.spring.spinghgharph.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {

    private List<Player> players = new ArrayList();
    private AtomicInteger id = new AtomicInteger(0);

    public List<Player> findAll() {
        return players;
    }
    public Optional<Player> findOne(int id) {
       return players.stream().filter(player -> player.Id()==id).findFirst();
    }
    public Player create (String name, Team team)
    {
        Player player = new Player(id.incrementAndGet(),name,team);
        players.add(player);
        return player;
    }

    public Player delete(Integer id)
    {
        Player player = players.stream().filter(p -> p.Id()==id).findFirst().
                orElseThrow(IllegalArgumentException::new);
        players.remove(player);
        return player;
    }

    public Player update(Integer id ,String name , Team team)
    {
        Player updatedPlayer = new Player(id,name,team);
        Optional<Player> opt = players.stream().filter(p -> p.Id()==id).findFirst();
        if(opt.isPresent())
        {
            Player player = opt.get();
            int index = players.indexOf(player);
            players.set(index,updatedPlayer);
        } else {
            throw new IllegalArgumentException("Invalid Player");
        }
        return updatedPlayer;
    }
    @PostConstruct
    private void init() {
        players.add(new Player(id.incrementAndGet(), "MS Dhoni", Team.CSK));
        players.add(new Player(id.incrementAndGet(), "Rohit Sharma", Team.MI));
        players.add(new Player(id.incrementAndGet(), "Jasprit Bumrah", Team.MI));
        players.add(new Player(id.incrementAndGet(), "Rishabh pant", Team.DC));
        players.add(new Player(id.incrementAndGet(), "Suresh Raina", Team.CSK));
    }
}


