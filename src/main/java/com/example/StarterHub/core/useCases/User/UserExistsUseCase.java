package com.example.StarterHub.core.useCases.User;

import java.util.Map;

public interface UserExistsUseCase {
    Map<String, Object> execute(String username, String email, String poneNumber);
}
