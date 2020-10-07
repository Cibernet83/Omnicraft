package com.cibernet.omnicraft.handlers.client;


import com.cibernet.omnicraft.Omnicraft;
import com.cibernet.omnicraft.items.OmnitrixItem;
import com.cibernet.omnicraft.registries.OmnicraftItems;
import com.cibernet.omnicraft.utils.OmnitrixColors;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = Omnicraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRenderHandler
{
	@SubscribeEvent
	public static void initItemColors(ColorHandlerEvent.Item event)
	{
		ItemColors colors = event.getItemColors();
		colors.register(new OmnitrixColor(), OmnicraftItems.prototypeOmnitrix);
		
	}
	
	protected static class OmnitrixColor implements IItemColor
	{
		
		@Override
		public int getColor(ItemStack stack, int i)
		{
			if(i >= OmnitrixColors.SectionColor.values().length || i < 0)
				return -1;
			return OmnitrixColors.getColor(OmnitrixItem.getMode(stack), i);
		}
	}
}
