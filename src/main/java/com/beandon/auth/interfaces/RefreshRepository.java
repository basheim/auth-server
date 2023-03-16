package com.beandon.auth.interfaces;

import com.beandon.auth.pojos.users.Auth;
import org.springframework.data.repository.CrudRepository;

public interface RefreshRepository extends CrudRepository<Auth, String> {
}
