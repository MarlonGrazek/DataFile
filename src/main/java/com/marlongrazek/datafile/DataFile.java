package com.marlongrazek.datafile;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
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
            } catch (IOException ignored) {
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
        } catch (IOException ignored) {
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

    public String getString(String path) {
        return config.getString(path);
    }

    public int getInt(String path) {
        return config.getInt(path);
    }

    public double getDouble(String path) {
        return config.getDouble(path);
    }

    public boolean getBoolean(String path) {
        return config.getBoolean(path);
    }

    public Set<String> getConfigurationSection(String path, Boolean getKeys) {
        if (config.getConfigurationSection(path) != null)
            return config.getConfigurationSection(path).getKeys(getKeys);
        return new HashSet<>(new ArrayList<>()); 
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
