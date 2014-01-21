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

import com.mumfrey.liteloader.LiteMod;
import java.io.File;

/**
 * Mod to show a horse's stats on its inventory screen.
 * @author Willem Mulder
 */
public class LiteModHorseStats implements LiteMod {
    @Override
    public String getName() {
        return "HorseStats";
    }

    @Override
    public String getVersion() {
        return "0.2.0-dev";
    }

    @Override
    public void init(File conf) {}

    @Override
    public void upgradeSettings(String string, File file1, File file2) {}
}
