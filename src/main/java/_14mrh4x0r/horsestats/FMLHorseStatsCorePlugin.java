package _14mrh4x0r.horsestats;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.*;

import java.util.Arrays;

@MCVersion("1.6.4")
@TransformerExclusions({"_14mrh4x0r.horsestats.FMLHorseStatsCorePlugin"})
public class FMLHorseStatsCorePlugin implements IFMLLoadingPlugin {
    public static class ModContainer extends DummyModContainer {
        public ModContainer() {
            super(new ModMetadata());
            ModMetadata meta = this.getMetadata();
            meta.modId = "horsestats";
            meta.name = "HorseStats";
            meta.description = "A Minecraft mod that displays a horse's stats in its inventory screen";
            meta.version = "0.2.0";
            meta.authorList = Arrays.asList("14mRh4X0r");
            meta.credits = "Wubbi for the original base mod";
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public String[] getLibraryRequestClass() {
      return null;
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[]{"_14mrh4x0r.horsestats.HorseScreenTransformer"};
    }

    @Override
    public String getModContainerClass() {
        return "_14mrh4x0r.horsestats.FMLHorseStatsCorePlugin$ModContainer";
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(java.util.Map<String, Object> data) {}
}
