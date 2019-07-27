package ru.alesavin.rockpaperscissors.model.impl;

import org.springframework.lang.Nullable;
import ru.alesavin.rockpaperscissors.model.PlayerStrategy;
import ru.alesavin.rockpaperscissors.model.PlayerStrategyRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Base impl of strategies repo based on map
 *
 * @author alesavin
 */
public class PlayerStrategyRepositoryImpl implements PlayerStrategyRepository {

    private Map<String, PlayerStrategy> map = new HashMap<>();

    public PlayerStrategyRepositoryImpl(PlayerStrategy... strategies) {
        Arrays.stream(strategies).forEach(s -> {
            if (!map.containsKey(s.id())) {
                map.put(s.id(), s);
            } else {
                throw new IllegalArgumentException("Duplicated strategy key " + s.id());
            }
        });
    }

    @Override
    @Nullable
    public PlayerStrategy resolve(String id) {
        return id == null ? null : map.get(id);
    }
}
