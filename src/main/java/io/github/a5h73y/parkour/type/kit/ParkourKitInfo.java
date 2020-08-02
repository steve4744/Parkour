package io.github.a5h73y.parkour.type.kit;

import static io.github.a5h73y.parkour.enums.ConfigType.PARKOURKIT;

import io.github.a5h73y.parkour.Parkour;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.configuration.ConfigurationSection;

public class ParkourKitInfo {

    /**
     * Get a list of possible ParkourKit
     *
     * @return Set<String> of ParkourKit names
     */
    public static Set<String> getParkourKitNames() {
        ConfigurationSection section = Parkour.getConfig(PARKOURKIT).getConfigurationSection("ParkourKit");
        return section != null ? section.getKeys(false) : new HashSet<>();
    }

    public static boolean doesParkourKitExist(String kitName) {
        return getParkourKitNames().contains(kitName.toLowerCase());
    }

    public static Set<String> getParkourKitContents(String kitName) {
        ConfigurationSection section = Parkour.getConfig(PARKOURKIT).getConfigurationSection("ParkourKit." + kitName.toLowerCase());
        return section != null ? section.getKeys(false) : new HashSet<>();
    }

    public static String getActionTypeForMaterial(String kitName, String material) {
        return Parkour.getConfig(PARKOURKIT).getString("ParkourKit." + kitName + "." + material + ".Action");
    }
}
