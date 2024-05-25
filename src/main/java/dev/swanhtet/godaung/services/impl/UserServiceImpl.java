package dev.swanhtet.godaung.services.impl;

import dev.swanhtet.godaung.Authentication.TokenSerivceImpl;
import dev.swanhtet.godaung.dto.LoginRequst;
import dev.swanhtet.godaung.dto.request.UserDto;
import dev.swanhtet.godaung.model.Role;
import dev.swanhtet.godaung.model.User;
import dev.swanhtet.godaung.repo.RoleRepository;
import dev.swanhtet.godaung.repo.UserRepository;
import dev.swanhtet.godaung.services.interfaces.UserServices;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.sf.jsqlparser.util.validation.metadata.DatabaseException;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServices {

	private final UserRepository userRepository;
	private final ModelMapper mapper;
	private final TokenSerivceImpl tokenService;
	private  final RoleRepository roleRepository;
	@Override
	public boolean createUser(UserDto userDTO) {
		Role role = roleRepository.findById(userDTO.getRoleId())
				.orElseThrow(() -> new RuntimeException("This position is not in the system"));
		User user = User.builder()
				.email(userDTO.getEmail())
				.password(BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt()))
				.username(userDTO.getUsername())
				.role(role)
				.build();
		try{
			userRepository.save(user);
			return  true;
		}catch (EntityNotFoundException e){
			throw  new  RuntimeException(e.getMessage());
		}

	}

	@Override
	public List<UserDto> getAllUsers() {

		return null;
	}

	@Override
	public UserDto getUserById(Long id) {

		return null;
	}

	@Override
	public boolean updateUser(Long id, UserDto userDTO) {

		return false;
	}

	@Override
	public boolean deleteUser(Long id) {

		return false;
	}

	@Override
	public String authenticateUser(LoginRequst loginRequst) {
		try {

			User user = userRepository.findByEmail(loginRequst.getEmail())
					.orElseThrow(() -> new RuntimeException("No user with this email registered"));


			if (BCrypt.checkpw(loginRequst.getPassword(), user.getPassword())) {

				return tokenService.finalTokenGeneration(
						tokenService.tokenPreparation(user.getEmail(), user.getRole().getRoleName()));
			} else {

				throw new RuntimeException("Invalid password");
			}
		} catch (DatabaseException e) {
			throw new RuntimeException(STR."Database error: \{e.getMessage()}", e);
		}
	}
}
