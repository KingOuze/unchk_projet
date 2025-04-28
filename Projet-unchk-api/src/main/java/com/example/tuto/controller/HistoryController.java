package com.example.tuto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tuto.entities.History;
import com.example.tuto.exception.dto.ActionRequestDTO;
import com.example.tuto.exception.dto.HistoryDTO;
import com.example.tuto.service.HistoryService;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/history")
public class HistoryController {
     @Autowired
    private HistoryService historyService;

    @PostMapping("/login")
    public History logLogin(@RequestBody Long userId) {
        return historyService.logLogin(userId);
    }

    @PostMapping("/logout")
    public History logLogout(@RequestBody Long userId) {
        return historyService.logLogout(userId);
    }

    @PostMapping("/action")
    public History logAction(@RequestBody ActionRequestDTO request) {
        return historyService.logAction(request.getUserId(), request.getAction(), request.getEntitie());
    }

    @GetMapping("/all")
    public List<HistoryDTO> getAllHistories() {
        return historyService.getAllHistories();
    }
}
