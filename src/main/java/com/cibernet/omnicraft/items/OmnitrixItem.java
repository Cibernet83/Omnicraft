package com.cibernet.omnicraft.items;

import com.cibernet.omnicraft.registries.OmnicraftItems;
import com.cibernet.omnicraft.utils.OmnitrixColors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class OmnitrixItem extends Item
{
	public static final int maxCooldown = 100;
	
	public OmnitrixItem(String name)
	{
		super(new Properties().maxStackSize(1).group(OmnicraftItems.GROUP_GENERAL));
		setRegistryName(name);
		OmnicraftItems.ITEMS.add(this);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		
		CompoundNBT nbt = stack.getOrCreateTag();
		if(!nbt.contains("Mode"))
			nbt.putString("Mode", OmnitrixMode.ACTVE.toString().toLowerCase());
		
		if(player.isSneaking())
		{
			OmnitrixMode mode = OmnitrixMode.values()[(getMode(stack).ordinal() + 1) % (OmnitrixMode.values().length)];
			nbt.putString("Mode", mode.toString());
		}
		else if(getMode(stack).equals(OmnitrixMode.ACTVE))
		{
			boolean dialing = nbt.getBoolean("Dialing");
			nbt.putBoolean("Dialing", !dialing);
			if(dialing)
			{
				nbt.putString("Mode", OmnitrixMode.RECHARGE.toString().toLowerCase());
				nbt.putInt("Cooldown", maxCooldown);
			}
		}
		
		
		return ActionResult.resultFail(stack);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean held)
	{
		super.inventoryTick(stack, world, entity, slot, held);
		
		CompoundNBT nbt = stack.getOrCreateTag();
		if(nbt.contains("Cooldown"))
		{
			if(nbt.getInt("Cooldown") == 1)
				nbt.putString("Mode", OmnitrixMode.ACTVE.toString().toLowerCase());
			nbt.putInt("Cooldown", Math.max(0, nbt.getInt("Cooldown")-1));
		}
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack)
	{
		CompoundNBT nbt = stack.getOrCreateTag();
		return nbt.contains("Cooldown") && nbt.getInt("Cooldown") > 0;
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack)
	{
		CompoundNBT nbt = stack.getOrCreateTag();
		return nbt.contains("Cooldown") ? Math.max(0, Math.min(1, nbt.getInt("Cooldown")/(double)maxCooldown)) : 0;
	}
	
	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack)
	{
		return OmnitrixColors.getColor(getMode(stack), 0);
	}
	
	public static OmnitrixMode getMode(ItemStack stack)
	{
		CompoundNBT nbt = stack.getOrCreateTag();
		
		if(!nbt.contains("Mode"))
			nbt.putString("Mode", OmnitrixMode.ACTVE.toString().toLowerCase());
		try
		{
			return OmnitrixMode.valueOf(nbt.getString("Mode").toUpperCase());
		}
		catch(IllegalArgumentException e) {return OmnitrixMode.DISABLED;}
	}
	
	public enum OmnitrixMode
	{
		ACTVE,
		RECHARGE,
		CAPTURE,
		RECALIBRATE,
		DISABLED;
		
		
	}
}
