package com.koirdsuzu.servermovemessage;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public final class Servermovemessage extends Plugin implements Listener {

    private Configuration config;
    private Map<String, String> serverNames = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        loadConfig();
        getProxy().getPluginManager().registerListener(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadConfig() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            try (InputStream in = getResourceAsStream("resources/config.yml")) {
                Files.copy(in, configFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
            loadServerNames();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadServerNames() {
        Configuration serversSection = config.getSection("servers");
        for (String key : serversSection.getKeys()) {
            serverNames.put(key, serversSection.getString(key));
        }
    }

    private String colorize(String message) {
        return message.replaceAll("&", "\u00a7");
    }

    @net.md_5.bungee.event.EventHandler
    public void onPlayerJoin(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();
        if (config.getBoolean("enabled.joinNetwork") && player.hasPermission("servermovemessage.admin")) {
            String message = config.getString("messages.joinNetwork").replace("{player}", player.getName());
            getProxy().broadcast(new TextComponent(colorize(message)));
        }
    }

    @net.md_5.bungee.event.EventHandler
    public void onServerSwitch(ServerSwitchEvent event) {
        ProxiedPlayer player = event.getPlayer();
        String fromServer = event.getFrom() != null ? event.getFrom().getName() : null;
        String toServer = player.getServer().getInfo().getName();

        if (fromServer == null) {
            if (config.getBoolean("enabled.joinServer")) {
                String message = config.getString("messages.firstJoin")
                        .replace("{player}", player.getName());
                getProxy().broadcast(new TextComponent(colorize(message)));
            }
        } else if (config.getBoolean("enabled.switchServer")) {
            String message = config.getString("messages.switchServer")
                    .replace("{player}", player.getName())
                    .replace("{server}", serverNames.getOrDefault(toServer, toServer));
            getProxy().broadcast(new TextComponent(colorize(message)));
        }
    }

    @net.md_5.bungee.event.EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent event) {
        ProxiedPlayer player = event.getPlayer();
        if (config.getBoolean("enabled.leaveNetwork") && player.hasPermission("servermovemessage.admin")) {
            String message = config.getString("messages.leaveNetwork").replace("{player}", player.getName());
            getProxy().broadcast(new TextComponent(colorize(message)));
        }
    }
}
