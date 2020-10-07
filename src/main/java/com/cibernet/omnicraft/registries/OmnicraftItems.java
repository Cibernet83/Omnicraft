package com.cibernet.omnicraft.registries;

import com.cibernet.omnicraft.Omnicraft;
import com.cibernet.omnicraft.items.OmnitrixItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class OmnicraftItems
{
	public static final ArrayList<Item> ITEMS = new ArrayList<>();
	public static final ItemGroup GROUP_GENERAL = new ItemGroup("omnicraft_general")
	{
		@Override
		public ItemStack createIcon()
		{
			return new ItemStack(prototypeOmnitrix);
		}
	};
	
	public static final OmnitrixItem prototypeOmnitrix = new OmnitrixItem("prototype_omnitrix");
	
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		IForgeRegistry<Item> registry = event.getRegistry();
		
		for(Item item : ITEMS)
			registry.register(item);
	}
	
	public static void registerModelProperties()
	{
		ResourceLocation activeProperty = new ResourceLocation(Omnicraft.MODID,"dialing");
		
		ItemModelsProperties.func_239418_a_(prototypeOmnitrix, activeProperty, (stack, world, entity) -> stack.getOrCreateTag().getBoolean("Dialing") ? 1 : 0);
		
		
	}
}
