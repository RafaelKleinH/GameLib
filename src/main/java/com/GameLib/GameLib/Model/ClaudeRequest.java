package com.GameLib.GameLib.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ClaudeRequest {
    private String model;
    @JsonProperty("max_tokens")
    private Integer maxTokens;
    private List<ClaudeMessage> messages;

    public ClaudeRequest() {}

    public ClaudeRequest(String model, Integer maxTokens, List<ClaudeMessage> messages) {
        this.model = model;
        this.maxTokens = maxTokens;
        this.messages = messages;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(Integer maxTokens) {
        this.maxTokens = maxTokens;
    }

    public List<ClaudeMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ClaudeMessage> messages) {
        this.messages = messages;
    }
}