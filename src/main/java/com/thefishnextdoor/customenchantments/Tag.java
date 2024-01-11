package com.thefishnextdoor.customenchantments;

import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;

public enum Tag {

    NO_DROPS,
    EXPLODE_ON_IMPACT;

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

    private String key() {
        return METADATA_KEY_PREFIX + name();
    }
}
