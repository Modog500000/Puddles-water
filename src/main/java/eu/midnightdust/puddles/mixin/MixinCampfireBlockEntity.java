package eu.midnightdust.puddles.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.entity.CampfireBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CampfireBlockEntity.class)
public class MixinCampfireBlockEntity {
    @Inject(method = "litServerTick", at = @At("HEAD"))
    private static void tick(World world, BlockPos pos, BlockState state, CampfireBlockEntity campfire, CallbackInfo ci) {
        if (world.isClient)
            return;
        if (Math.random() > 0.999f && world.hasRain(pos))  {
            world.setBlockState(pos,state.with(CampfireBlock.LIT,false));
            world.playSound((PlayerEntity)null, pos, SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
        if (Math.random() > 0.99f && world.getBlockState(pos.add(0,2,0)).isOf(Blocks.WATER)) {
            int level = world.getBlockState(pos.add(0,2,0)).get(FluidBlock.LEVEL);
            System.out.println(level);
            if (level == 0) {
                world.setBlockState(pos.add(0, 2, 0), Fluids.WATER.getFlowing(7, false).getBlockState());
            } else if (level < 7) {
                world.setBlockState(pos.add(0, 2, 0), Fluids.WATER.getFlowing((8 - level)-1, false).getBlockState());
            } else {
                world.setBlockState(pos.add(0, 2, 0), Blocks.AIR.getDefaultState());
            }
            ItemEntity entity = new ItemEntity(world,pos.getX(),pos.getY()+2,pos.getZ(), Items.IRON_NUGGET.getDefaultStack());
            world.spawnEntity(entity);
        }
    }
}
