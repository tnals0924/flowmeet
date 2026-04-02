package kr.flowmeet.domain.mock.service;

import java.util.List;
import kr.flowmeet.domain.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MockService {

    public String findByName(String name) {
        return name;
    }

    public void save(String mock) {
        //생성 코드
    }

    public List<String> findAll(User user) {
        return List.of("mock1", "mock2");
    }
}
