package com.maldo.backend.users.services.interfaces;

import com.maldo.backend.users.domain.SignUpDTO;

public interface UserDtoService {

    void createUser(SignUpDTO dataTransfer);

}
