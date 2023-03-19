package com.varun.db.command;

import com.varun.db.storage.KeyValueStore;

import java.io.IOException;

public class SetCommand implements Command {
    private String key;
    private String value;

    public SetCommand(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void execute(KeyValueStore keyValueStore) {
        try {
            keyValueStore.set(getKey(), getValue());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
