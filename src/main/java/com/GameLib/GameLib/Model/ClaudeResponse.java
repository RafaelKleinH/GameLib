package com.GameLib.GameLib.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ClaudeResponse {
    private String id;
    private String type;
    private String role;
    private List<ClaudeContent> content;
    private String model;
    @JsonProperty("stop_reason")
    private String stopReason;
    @JsonProperty("stop_sequence")
    private String stopSequence;
    private ClaudeUsage usage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<ClaudeContent> getContent() {
        return content;
    }

    public void setContent(List<ClaudeContent> content) {
        this.content = content;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStopReason() {
        return stopReason;
    }

    public void setStopReason(String stopReason) {
        this.stopReason = stopReason;
    }

    public String getStopSequence() {
        return stopSequence;
    }

    public void setStopSequence(String stopSequence) {
        this.stopSequence = stopSequence;
    }

    public ClaudeUsage getUsage() {
        return usage;
    }

    public void setUsage(ClaudeUsage usage) {
        this.usage = usage;
    }

    public static class ClaudeContent {
        private String type;
        private String text;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class ClaudeUsage {
        @JsonProperty("input_tokens")
        private Integer inputTokens;
        @JsonProperty("output_tokens")
        private Integer outputTokens;

        public Integer getInputTokens() {
            return inputTokens;
        }

        public void setInputTokens(Integer inputTokens) {
            this.inputTokens = inputTokens;
        }

        public Integer getOutputTokens() {
            return outputTokens;
        }

        public void setOutputTokens(Integer outputTokens) {
            this.outputTokens = outputTokens;
        }
    }
}