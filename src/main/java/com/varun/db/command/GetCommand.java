package com.varun.db.command;

import com.varun.db.exception.KeyNotFoundException;
import com.varun.db.storage.KeyValueStore;

import java.io.IOException;

public class GetCommand implements Command {
    private String key;

    public GetCommand(String key) {
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
            System.out.println(keyValueStore.get(getKey()));
        } catch (KeyNotFoundException e) {
            System.out.printf("Key %s not found \n", getKey());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
