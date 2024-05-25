package dev.swanhtet.godaung.services.interfaces;

import dev.swanhtet.godaung.dto.LoginRequst;
import dev.swanhtet.godaung.dto.request.UserDto;
import java.util.List;

public interface UserServices {

  boolean createUser(UserDto userDTO);

  List<UserDto> getAllUsers();

  UserDto getUserById(Long id);

  boolean updateUser(Integer id, UserDto userDTO);

  boolean deleteUser(Integer id);

  String authenticateUser(LoginRequst loginRequst);

  UserDto findById(Integer id);
}
