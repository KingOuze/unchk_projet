package com.example.tuto.exception.dto;

public class ActionRequestDTO {
    
        private Long userId;
        private String action;
        private String entitie;
    
        // Getters and Setters
        public Long getUserId() {
            return userId;
        }
    
        public void setUserId(Long userId) {
            this.userId = userId;
        }
    
        public String getAction() {
            return action;
        }
    
        public void setAction(String action) {
            this.action = action;
        }
    
        public String getEntitie() {
            return entitie;
        }
    
        public void setEntitie(String entitie) {
            this.entitie = entitie;
        }
}
