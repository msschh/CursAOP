package com.apbdoo.hrm.service;

import com.apbdoo.hrm.entity.Role;
import com.apbdoo.hrm.entity.Utilizator;
import com.apbdoo.hrm.repository.UtilizatorRepository;
import com.apbdoo.hrm.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilizatorServiceImpl implements UtilizatorService {
    UtilizatorRepository utilizatorRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UtilizatorServiceImpl(UtilizatorRepository utilizatorRepository) {
        this.utilizatorRepository = utilizatorRepository;
    }

    @Override
    public List<Utilizator> getUtilizatori() {
        List<Utilizator> utilizatori = new ArrayList<>();
        utilizatorRepository.findAll().iterator().forEachRemaining(utilizatori::add);
        return utilizatori;
    }

    @Override
    public Utilizator readUtilizator(long idUtilizator) {
        Utilizator utilizator = utilizatorRepository.findById(idUtilizator).get();
        return utilizator;
    }

    @Override
    public Utilizator saveUtilizator(Utilizator utilizator) {
        if (utilizator.getId() == null) {
            utilizator.setParola(passwordEncoder.encode(utilizator.getParola()));
        }
        Utilizator savedUtilizator = utilizatorRepository.save(utilizator);
        return savedUtilizator;
    }

    @Override
    public void deleteUtilizator(long idUtilizator) {
        utilizatorRepository.deleteById(idUtilizator);
    }

    @Override
    public Utilizator findByUsername(String username) {
        return utilizatorRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Utilizator user = utilizatorRepository.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getParola(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
