package com.market.app;
class SecretsTemplate {
    private static final String API_KEY = "YOUR_KEY_HERE";
    private static final String DIRECTORY = "PROJECT_DIRECTORY_HERE";
    protected String getAPIKey() {
        return API_KEY;
    }
    protected String getDirectory() {
        return DIRECTORY;
    }
}