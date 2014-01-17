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

import net.minecraft.src.GuiScreen;
import net.minecraft.src.GuiScreenHorseInventory;
import net.minecraft.src.FontRenderer;

import com.mumfrey.liteloader.RenderListener;

import java.lang.reflect.Field;

/**
 * Mod to show a horse's stats on its inventory screen.
 * @author Willem Mulder
 */
public class HorseStats implements RenderListener {
    @Override
    public void onRender() {}

    @Override
    public void onRenderGui(GuiScreen guiScreen) {
        if (guiScreen instanceof GuiScreenHorseInventory) {
            Field fontRenderer;
            try {
                fontRenderer = GuiScreen.class.getDeclaredField("fontRenderer");
            } catch (NoSuchFieldException e1) {
                try {
                    fontRenderer = GuiScreen.class.getDeclaredField("field_73886_k");
                } catch (NoSuchFieldException e2) {
                    try {
                        fontRenderer = GuiScreen.class.getDeclaredField("o");
                    } catch (NoSuchFieldException e3) {
                        throw new NoSuchFieldError("Cannot find GuiScreen.fontRenderer in any form, is this mod outdated?");
                    }
                }
            }

            fontRenderer.setAccessible(true);
            FontRenderer fr = fontRenderer.get(guiScreen);

        }
    }
}
