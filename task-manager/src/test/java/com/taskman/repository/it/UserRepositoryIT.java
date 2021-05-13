package com.taskman.repository.it;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.taskman.entity.User;
import com.taskman.repository.UserRepository;

@DataJpaTest
public class UserRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void whenFindByName_thenReturnUser() {
    	
        User alex = new User("alex", "email@email.com");
        entityManager.persistAndFlush(alex);

        User found = userRepository.findByName(alex.getName());
        assertThat(found.getName()).isEqualTo(alex.getName());
    }
    
    @Test
    public void whenInvalidName_thenReturnNull() {
        User fromDb = userRepository.findByName("doesNotExist");
        assertThat(fromDb).isNull();
    }    
	
}
