package com.marlongrazek.datafile;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class DataFile {

    private final File file;
    private FileConfiguration config;
    private final String name;
    private final String path;

    public DataFile(String name, String path) {

        this.name = name;
        this.path = path;
        file = new File(path, name + ".yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public DataFile(File file) {
        this.file = file;
        this.name = file.getName();
        this.path = file.getPath();
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void set(String path, Object data) {
        config.set(path, data);
        save();
    }

    public Boolean contains(String string) {
        return config.contains(string);
    }

    public Object get(String path) {
        return config.get(path);
    }

    public Set<String> getConfigurationSection(String path, Boolean getKeys) {
        return config.getConfigurationSection(path).getKeys(getKeys);
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }
}
