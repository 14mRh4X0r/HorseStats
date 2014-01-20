/*
 * Copyright (C) 2014 Willem Mulder
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package _14mrh4x0r.horsestats;

import net.minecraft.src.EntityHorse;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.GuiScreenHorseInventory;
import net.minecraft.src.SharedMonsterAttributes;

import com.mumfrey.liteloader.LiteMod;

import java.awt.Color;
import java.io.File;

/**
 * Mod to show a horse's stats on its inventory screen.
 * @author Willem Mulder
 */
public class LiteModHorseStats implements LiteMod {
    private static GuiScreenHorseInventory prevScreen = null;

    @Override
    public String getName() {
        return "HorseStats";
    }

    @Override
    public String getVersion() {
        return "0.1.0-dev";
    }

    @Override
    public void init(File conf) {}

    @Override
    public void upgradeSettings(String string, File file1, File file2) {}

    public static void onRenderHorseInventory(GuiScreenHorseInventory guiScreen, FontRenderer fontRenderer, int ySize, EntityHorse horse) {
        fontRenderer.drawString("Health:", 9, ySize - 96 + 3, 0x404040);
        fontRenderer.drawString("Jump:", 65, ySize - 96 + 3, 0x404040);
        fontRenderer.drawString("Speed:", 115, ySize - 96 + 3, 0x404040);


        int healthComparison = Math.round((horse.getMaxHealth() - 15.0F) / 15.0F * 100.0F);
        int jumpComparison = Math.round(((float) horse.getHorseJumpStrength() - 0.4F) / 0.6F * 100.0F);
        int speedComparison = Math.round(((float) horse.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue() - 0.1125F) / 0.225F * 100.0F);
        
        float hue = healthComparison / 300F;
        fontRenderer.drawString(Integer.toString(healthComparison), 44, ySize - 96 + 3, Color.HSBtoRGB(hue, 1, 1));

        if (prevScreen != guiScreen) System.err.println(String.format("health hue = %.2f (#%2$06x, %2$d)", hue * 360F, Color.HSBtoRGB(hue, 1, 1) & 0xffffff));

        hue = jumpComparison / 300F;
        fontRenderer.drawString(Integer.toString(jumpComparison), 93, ySize - 96 + 3, Color.HSBtoRGB(hue, 1, 1));

        if (prevScreen != guiScreen) System.err.println(String.format("jump hue = %.2f (#%2$06x, %2$d)", hue * 360F, Color.HSBtoRGB(hue, 1, 1) & 0xffffff));

        hue = speedComparison / 300F;
        fontRenderer.drawString(Integer.toString(speedComparison), 149, ySize - 96 + 3, Color.HSBtoRGB(hue, 1, 1));

        if (prevScreen != guiScreen) System.err.println(String.format("speed hue = %.2f (#%2$06x, %2$d)", hue * 360F, Color.HSBtoRGB(hue, 1, 1) & 0xffffff));

        prevScreen = guiScreen;
    }
}
