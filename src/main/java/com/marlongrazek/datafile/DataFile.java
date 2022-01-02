package com.marlongrazek.datafile;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    public void delete(String path) {
        config.set(path, null);
    }

    public boolean isEmpty() {
        return config.getConfigurationSection("").getKeys(false).isEmpty();
    }

    public Boolean contains(String string) {
        return config.contains(string);
    }

    public Object get(String path) {
        return config.get(path);
    }

    public boolean getBoolean(String path) {
        return config.getBoolean(path);
    }

    public boolean getBoolean(String path, boolean def) {
        return config.getBoolean(path, def);
    }

    public String getString(String path) {
        return config.getString(path);
    }

    public String getString(String path, String def) {
        return config.getString(path, def);
    }

    public List<String> getStringList(String path) {
        return config.getStringList(path);
    }

    public int getInt(String path) {
        return config.getInt(path);
    }

    public int getInt(String path, int def) {
        return config.getInt(path, def);
    }

    public double getDouble(String path) {
        return config.getDouble(path);
    }

    public double getDouble(String path, double def) {
        return config.getDouble(path, def);
    }

    public long getLong(String path) {
        return config.getLong(path);
    }

    public long getLong(String path, long def) {
        return config.getLong(path, def);
    }

    public List<Long> getLongList(String path) {
        return config.getLongList(path);
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
