package com.modpack.roseprogression;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Tool;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

import java.util.List;

@Mod("rose_progression")
public class RoseProgressionMod {

    public RoseProgressionMod(IEventBus modEventBus) {
        modEventBus.addListener(this::modifyDefaultComponents);
    }

    private void modifyDefaultComponents(ModifyDefaultComponentsEvent event) {
        // Bezpieczne pobranie kilofa bez uzycia nieistniejacej metody getKey()
        ResourceLocation rosePickaxeId = ResourceLocation.fromNamespaceAndPath("progression_reborn", "rose_pickaxe");
        Item rosePickaxe = BuiltInRegistries.ITEM.get(rosePickaxeId);

        if (rosePickaxe != null && rosePickaxe != Items.AIR) {
            event.modify(rosePickaxe, builder -> {
                Tool roseTool = new Tool(
                        List.of(
                                Tool.Rule.deniesDrops(BlockTags.NEEDS_DIAMOND_TOOL),
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
