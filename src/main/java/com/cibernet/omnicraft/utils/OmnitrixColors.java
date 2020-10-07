package com.cibernet.omnicraft.utils;

import com.cibernet.omnicraft.items.OmnitrixItem;

public class OmnitrixColors
{
	public static final int[][] colorSets = new int[][]
	{
		createColorSet(0x91DC28, 0x91DC28, 0xEEEBEE), //Active
		createColorSet(0xDC0100, 0x580103, 0xEEEBEE), //Recharge
		createColorSet(0xF8F56E, 0xF8F56E, 0xF8F56E), //Capture
		createColorSet(0x265BEF, 0x91DC28, 0xEEEBEE), //Recalibrate
		createColorSet(0x666364, 0x666364, 0x666364), //Disabled
	};
	
	public static int getColor(OmnitrixItem.OmnitrixMode mode, int section)
	{
		return getColor(mode, SectionColor.values()[section]);
	}
	
	public static int getColor(OmnitrixItem.OmnitrixMode mode, SectionColor section)
	{
		return colorSets[mode.ordinal()][section.ordinal()];
	}
	
	protected static int[] createColorSet(int core, int buttons, int bands)
	{
		return new int[] {core, buttons, bands};
	}
	
	public enum SectionColor
	{
		CORE,
		BUTTONS,
		BANDS
	}
}
