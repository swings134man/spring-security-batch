package com.lucas.bomkey.domains.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;


    @Transactional
    public User saveUser(User user) {
        if(user.getId() == null){
            return save(user);
        }else {
            return update(user);
        }
    }

    private User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    private User update(User user) {
        User findResult = findByUserEmail(user.getUserEmail());

        findResult.setUserEmail(user.getUserEmail());
        findResult.setUserName(user.getUserName());

        return repository.save(findResult);
    }


    @Transactional(readOnly = true)
    public User findByUserEmail(String email) {
        return repository.findByUserEmail(email);
    }

    @Transactional
    public boolean deleteByUserEmail(String email) {
        User findResult = findByUserEmail(email);
        repository.delete(findResult);
        return true;
    }
}
