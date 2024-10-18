package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.request.UserCreateDto;
import com.example.projectbase.domain.dto.response.UserDto;
import com.example.projectbase.domain.entity.Role;
import com.example.projectbase.domain.entity.User;
import com.example.projectbase.domain.entity.User.UserBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-18T10:40:01+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.12 (Ubuntu)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserCreateDto userCreateDTO) {
        if ( userCreateDTO == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.username( userCreateDTO.getUsername() );
        user.password( userCreateDTO.getPassword() );
        user.firstName( userCreateDTO.getFirstName() );
        user.lastName( userCreateDTO.getLastName() );

        return user.build();
    }

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setRoleName( userRoleName( user ) );
        userDto.setCreatedDate( user.getCreatedDate() );
        userDto.setLastModifiedDate( user.getLastModifiedDate() );
        userDto.setId( user.getId() );
        userDto.setUsername( user.getUsername() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setLastName( user.getLastName() );
        userDto.setNumberOfTabs( String.valueOf( user.getNumberOfTabs() ) );

        return userDto;
    }

    @Override
    public List<UserDto> toUserDtos(List<User> user) {
        if ( user == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( user.size() );
        for ( User user1 : user ) {
            list.add( toUserDto( user1 ) );
        }

        return list;
    }

    private String userRoleName(User user) {
        if ( user == null ) {
            return null;
        }
        Role role = user.getRole();
        if ( role == null ) {
            return null;
        }
        String name = role.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
