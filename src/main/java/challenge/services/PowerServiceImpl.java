package challenge.services;

import challenge.enums.Power;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by rsanchpa on 27/09/2017.
 */
@Service
public class PowerServiceImpl implements PowerService {
    @Override
    public List<String> obtainAllPowers() {
        return Stream.of(Power.values()).map(Power::getValue).collect(Collectors.toList());
    }
}
