package com.thefishnextdoor.customenchantments.util;

import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;

import com.thefishnextdoor.customenchantments.Plugin;

public enum EntityTag {

    NO_DROPS,
    EXPLODE_ON_IMPACT,
    FROM_VOLLEY;

    private static final String METADATA_KEY_PREFIX = "FCE_";

    public boolean isOn(Entity entity) {
        return entity.hasMetadata(this.key());

    }

    public void applyTo(Entity entity) {
        entity.setMetadata(this.key(), new FixedMetadataValue(Plugin.getInstance(), true));
    }

    public void remove(Entity entity) {
        entity.removeMetadata(this.key(), Plugin.getInstance());
    }

    public static void transfer(Entity from, Entity to) {
        for (EntityTag tag : EntityTag.values()) {
            if (tag.isOn(from)) {
                tag.applyTo(to);
            }
        }
    }

    private String key() {
        return METADATA_KEY_PREFIX + name();
    }
}
