package io.github.a5h73y.parkour.configuration.serializable;

import org.bukkit.inventory.ItemStack;

public class ItemStackArraySerializable extends Base64Serializable<ItemStack[]> {

	@Override
	public Class<ItemStack[]> getClazz() {
		return ItemStack[].class;
	}
}
