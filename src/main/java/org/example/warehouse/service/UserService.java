package org.example.warehouse.service;

import org.example.warehouse.dto.UserDto;
import org.example.warehouse.model.Result;
import org.example.warehouse.model.User;
import org.example.warehouse.model.WareHouse;
import org.example.warehouse.repository.UserRepository;
import org.example.warehouse.repository.WareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    WareHouseRepository wareHouseRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    public Result addUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        user.setCode(userDto.getCode());
        user.setActive(userDto.isActive());
        Optional<WareHouse> wareHouse = wareHouseRepository.findById(userDto.getWarehouseId());
        user.setWareHouse((List<WareHouse>) wareHouse.get());
        userRepository.save(user);
        return new Result(true,"User created successfully");
    }

    public Result updateUser(UserDto userDto,Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setPassword(userDto.getPassword());
            user.setCode(userDto.getCode());
            user.setActive(userDto.isActive());
            userRepository.save(user);
            return new Result(true,"User updated successfully");
        }
        return new Result(false,"User not found");
    }

    public Result deleteUserById(Integer id) {
        userRepository.deleteById(id);
        return new Result(true,"User deleted successfully");
    }



}