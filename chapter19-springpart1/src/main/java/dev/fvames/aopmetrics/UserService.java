package dev.fvames.aopmetrics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@Metrics(ignoreException = true)
public class UserService {

	private static final Map<Long, UserEntity> userDB = new ConcurrentHashMap();

    //@Autowired
    //private UserRepository userRepository;

    @Transactional
    public void createUser(UserEntity entity) {
		userDB.put(entity.getId(), entity);

        //userRepository.save(entity);
        //if (entity.getName().contains("test"))
        //    throw new RuntimeException("invalid username!");
    }

    public long getUserCount(String name) {
        //return userRepository.findByName(name).size();
		long count = userDB.values().stream().filter(item -> item.getName().equals(name)).count();
		return count;
    }
}
