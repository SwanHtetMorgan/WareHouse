package dev.swanhtet.godaung.services.interfaces;

import dev.swanhtet.godaung.dto.LoginRequst;
import dev.swanhtet.godaung.dto.request.UserDto;
import dev.swanhtet.godaung.model.User;

import java.util.List;

public interface UserServices {

	boolean createUser(UserDto userDTO);

	List<UserDto> getAllUsers();

	UserDto getUserById(Long id);

	boolean updateUser(Long id, UserDto userDTO);

	boolean deleteUser(Long id);

	String authenticateUser(LoginRequst loginRequst);



}
