package com.cibernet.omnicraft;

import com.cibernet.omnicraft.registries.OmnicraftItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Omnicraft.MODID)
public class Omnicraft
{
	public static final String MODID = "omnicraft";
	public static final String VERSION = "1.0.0";
	public static final String NAME = "Omnicraft";
	public static final String SHORT = "oc";
	
	public Omnicraft()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
		
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	private void commonSetup(final FMLCommonSetupEvent event)
	{
		//Capabilities
		//Packet Registry
		//Sounds
		//Tags
		//Stats
		//Command Args
		
		DeferredWorkQueue.runLater(() ->
		{
			//Entity Attributes
			//Gamerules
		});
		
	}
	
	private void clientSetup(final FMLClientSetupEvent event)
	{
		//Entity Renderers
		//Keybinds
		//Block Render Layers
		//TESR
		
		DeferredWorkQueue.runLater(() ->
		{
			OmnicraftItems.registerModelProperties();
			//Armor Models
			//Screen Containers
		});
	}
}
