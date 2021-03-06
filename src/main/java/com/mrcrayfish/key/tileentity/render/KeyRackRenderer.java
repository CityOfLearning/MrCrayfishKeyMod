package com.mrcrayfish.key.tileentity.render;

import com.mrcrayfish.key.MrCrayfishKeyMod;
import com.mrcrayfish.key.blocks.BlockKeyRack;
import com.mrcrayfish.key.tileentity.TileEntityKeyRack;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class KeyRackRenderer extends TileEntitySpecialRenderer<TileEntityKeyRack> {
	private EntityItem keyEntity = new EntityItem(Minecraft.getMinecraft().world, 0D, 0D, 0D);

	@Override
	public void render(TileEntityKeyRack tileEntity, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {
		Block block = tileEntity.getBlockType();
		if (!(block instanceof BlockKeyRack)) {
			return;
		}

		if (tileEntity.getWorld().isAirBlock(tileEntity.getPos())) {
			return;
		}

		int metadata = block.getMetaFromState(tileEntity.getWorld().getBlockState(tileEntity.getPos()));

		TileEntityKeyRack keyRack = tileEntity;

		GlStateManager.pushMatrix();
		{
			GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
			GlStateManager.rotate(MathHelper.wrapDegrees(180 + (metadata * -90F)), 0, 1, 0);
			GlStateManager.translate(0.065F, -0.13F, 0.4F);
			keyEntity.hoverStart = 0.0F;

			for (int i = 0; i < keyRack.getSizeInventory(); i++) {
				ItemStack key = keyRack.getStackInSlot(i);
				if (!key.isEmpty()) {
					GlStateManager.pushMatrix();
					{
						keyEntity.setItem(key);
						GlStateManager.scale(0.5F, 0.5F, 0.5F);
						GlStateManager.rotate(90F, 0, 0, 1);
						GlStateManager.rotate(180F, 1, 0, 0);

						if (key.getItem() == MrCrayfishKeyMod.item_key_ring) {
							GlStateManager.rotate(-90F, 0, 0, 1);
							GlStateManager.translate(-0.55F, -0.34F, 0F);
							GlStateManager.rotate(5F, 0, 1, 0);
						} else {
							GlStateManager.translate(-0.065F, 0.18F, 0F);
							GlStateManager.rotate(5F, 1, 0, 0);
						}

						Minecraft.getMinecraft().getRenderManager().doRenderEntity(keyEntity, 0, 0, 0, 0, 0, false);
					}
					GlStateManager.popMatrix();
				}
				GlStateManager.translate(-0.2175F, 0F, 0F);
			}
		}
		GlStateManager.popMatrix();
	}
}
