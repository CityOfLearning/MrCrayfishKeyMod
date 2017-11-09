package com.mrcrayfish.key.items;

import com.mrcrayfish.key.MrCrayfishKeyMod;
import com.mrcrayfish.key.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class KeyItems {

	public static final Item item_key= new ItemKey().setUnlocalizedName("item_key").setCreativeTab(MrCrayfishKeyMod.tabKey);
	public static final Item item_master_key= new ItemMasterKey().setUnlocalizedName("item_master_key");
	public static final Item item_key_ring= new ItemKeys().setUnlocalizedName("item_key_ring").setCreativeTab(MrCrayfishKeyMod.tabKey);
	public static final Item item_iron_nugget= new Item().setUnlocalizedName("item_iron_nugget").setCreativeTab(MrCrayfishKeyMod.tabKey);


	public static void registerItems() {
		GameRegistry.registerItem(item_key, item_key.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(item_master_key, item_master_key.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(item_key_ring, item_key_ring.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(item_iron_nugget, item_iron_nugget.getUnlocalizedName().substring(5));
	}

	public static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(
				Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}

	public static void registerRenders() {
		registerRender(item_key);
		registerRender(item_master_key);
		registerRenderVariants(item_key_ring, 4);
		registerRender(item_iron_nugget);
	}

	public static void registerRenderVariants(Item item, int amount) {
		String[] variants = new String[amount];
		for (int i = 0; i < amount; i++) {
			variants[i] = Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5) + "_" + i;
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, i,
					new ModelResourceLocation(variants[i], "inventory"));
		}
		ModelBakery.addVariantName(item, variants);
	}
}
