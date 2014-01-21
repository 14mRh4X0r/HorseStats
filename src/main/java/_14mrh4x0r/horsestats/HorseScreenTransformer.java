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

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.util.CheckClassAdapter;
import org.objectweb.asm.util.TraceClassVisitor;

import static org.objectweb.asm.Opcodes.*;

public class HorseScreenTransformer implements IClassTransformer {
    private static String horseScreenClass = "net.minecraft.src.GuiScreenHorseInventory";
    private static String horseScreenClassOb = "axu";

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (horseScreenClass.equals(name) || horseScreenClassOb.equals(name)) {
            ClassReader cr = new ClassReader(basicClass);
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES);
            CheckClassAdapter cca = new CheckClassAdapter(cw);
            ChangeHorseScreenAdapter ca = new ChangeHorseScreenAdapter(cca);
            cr.accept(ca, ClassReader.SKIP_DEBUG | ClassReader.EXPAND_FRAMES);
            return cw.toByteArray();
        } else {
            return basicClass;
        }
    }

    private static class ChangeDrawForegroundAdapter extends MethodVisitor {
        private final boolean obbed;
        private boolean popped = false;

        ChangeDrawForegroundAdapter(MethodVisitor mv, boolean obbed) {
            super(ASM4, mv);
            this.obbed = obbed;
        }

        @Override
        public void visitInsn(int opcode) {
            if (!popped) {
                mv.visitInsn(opcode);
                if (opcode == POP) {
                    popped = true;
                    mv.visitVarInsn(ALOAD, 0); // this

                    // this.fontRenderer
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitFieldInsn(GETFIELD, obbed ? "axu" : "net/minecraft/src/GuiScreenHorseInventory",
                                                obbed ? "o" : "fontRenderer",
                                                obbed ? "Lavi;" : "Lnet/minecraft/src/FontRenderer;");
                    // this.ySize
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitFieldInsn(GETFIELD, obbed ? "axu" : "net/minecraft/src/GuiScreenHorseInventory",
                                                obbed ? "d" : "ySize", "I");

                    // this.horse
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitFieldInsn(GETFIELD, obbed ? "axu" : "net/minecraft/src/GuiScreenHorseInventory",
                                                obbed ? "w" : "field_110411_w",
                                                obbed ? "Lrs;" : "Lnet/minecraft/src/EntityHorse;");

                    // HorseStats.onRenderHorseInventory(this, this.fontRenderer, this.ySize, this.horse);
                    mv.visitMethodInsn(INVOKESTATIC, "_14mrh4x0r/horsestats/HorseStats", "onRenderHorseInventory",
                                       obbed ? "(Laxu;Lavi;ILrs;)V" :
                            "(Lnet/minecraft/src/GuiScreenHorseInventory;Lnet/minecraft/src/FontRenderer;ILnet/minecraft/src/EntityHorse;)V");
                    mv.visitInsn(RETURN);
                }
            }
        }
    }

    private static class ChangeHorseScreenAdapter extends ClassVisitor {
        ChangeHorseScreenAdapter(ClassVisitor cv) {
            super(ASM4, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            boolean obbed = false;
            MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
            if (mv != null
                    && ("drawGuiContainerForegroundLayer".equals(name) || (obbed = "b".equals(name)))
                    && "(II)V".equals(desc)) {
                mv = new ChangeDrawForegroundAdapter(mv, obbed);
            }
            return mv;
        }
    }
}
