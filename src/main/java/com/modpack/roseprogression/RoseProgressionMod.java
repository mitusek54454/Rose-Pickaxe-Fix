package com.modpack.roseprogression;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

import java.util.List;

@Mod(RoseProgressionMod.MOD_ID)
public class RoseProgressionMod {
    public static final String MOD_ID = "rose_progression";

    public static final TagKey<Block> INCORRECT_FOR_ROSE_TOOL = TagKey.create(
            Registries.BLOCK,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "incorrect_for_rose_tool")
    );

    public RoseProgressionMod(IEventBus modEventBus) {
        modEventBus.addListener(this::modifyDefaultComponents);
    }

    private void modifyDefaultComponents(ModifyDefaultComponentsEvent event) {
        Item rosePickaxe = BuiltInRegistries.ITEM.get(
                ResourceLocation.fromNamespaceAndPath("progression_reborn", "rose_pickaxe")
        );

        if (rosePickaxe != Items.AIR) {
            event.modify(rosePickaxe, builder -> {
                Tool roseTool = new Tool(
                        List.of(
                                Tool.Rule.deniesDrops(INCORRECT_FOR_ROSE_TOOL),
                                Tool.Rule.minesAndDrops(BlockTags.MINEABLE_WITH_PICKAXE, 7.0F)
                        ),
                        1.0F,
                        1
                );
                builder.set(DataComponents.TOOL, roseTool);
            });
        }
    }
}
