package com.example.tuto.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tuto.entities.History;
import com.example.tuto.entities.User;
import com.example.tuto.exception.dto.HistoryDTO;
import com.example.tuto.repository.HistoryRepository;
import com.example.tuto.repository.UserRepository;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private UserRepository userRepository;

    public History logLogin(Long userId) {
        History history = new History();
        history.setUserId(userId);
        history.setLoginTime(LocalDateTime.now());
        return historyRepository.save(history);
    }

    public History logLogout(Long userId) {
        Optional<History> historyOptional = historyRepository.findTopByUserIdAndLogoutTimeIsNullOrderByLoginTimeDesc(userId);
        if (historyOptional.isPresent()) {
            History history = historyOptional.get();
            history.setLogoutTime(LocalDateTime.now());
            return historyRepository.save(history);
        }
        return null;
    }

    public History logAction(Long userId, String action, String entitie) {
        Optional<History> historyOptional = historyRepository.findTopByUserIdAndLogoutTimeIsNullOrderByLoginTimeDesc(userId);
        if (historyOptional.isPresent()) {
            History history = historyOptional.get();
            history.getActions().add(action);
            return historyRepository.save(history);
        }
        return null;
    }

    public List<HistoryDTO> getAllHistories() {
        List<History> histories = historyRepository.findAll();
        return histories.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private HistoryDTO convertToDTO(History history) {
        HistoryDTO dto = new HistoryDTO();
        dto.setId(history.getId());
        dto.setUserId(history.getUserId());
        dto.setLoginTime(history.getLoginTime());
        dto.setLogoutTime(history.getLogoutTime());
        dto.setActions(history.getActions());
        dto.setEntitie(history.getEntitie());

        Optional<User> userOptional = userRepository.findById(history.getUserId());
        userOptional.ifPresent(user -> {
            dto.setPrenom(user.getPrenom());
            dto.setNom(user.getNom());
            dto.setEmail(user.getEmail());
        });
        return dto;
    }
}
