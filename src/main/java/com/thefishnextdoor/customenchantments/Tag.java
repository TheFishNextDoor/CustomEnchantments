package com.thefishnextdoor.customenchantments;

import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;

public enum Tag {

    NO_DROPS;

    private static final String METADATA_KEY_PREFIX = "FCE_";

    public static boolean has(Entity entity, Tag tag) {
        return entity.hasMetadata(tag.key());

    }

    public static void add(Entity entity, Tag tag) {
        entity.setMetadata(tag.key(), new FixedMetadataValue(Plugin.getInstance(), true));
    }

    public static void remove(Entity entity, Tag tag) {
        entity.removeMetadata(tag.key(), Plugin.getInstance());

    }

    private String key() {
        return METADATA_KEY_PREFIX + name();
    }
}
