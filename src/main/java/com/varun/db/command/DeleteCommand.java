package com.varun.db.command;

import com.varun.db.exception.KeyNotFoundException;
import com.varun.db.storage.KeyValueStore;

import java.io.IOException;

public class DeleteCommand implements Command {
    private String key;

    public DeleteCommand(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void execute(KeyValueStore keyValueStore) {
        try {
            keyValueStore.delete(getKey());
        } catch (KeyNotFoundException e) {
            System.out.printf("Key %s not found \n", key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
