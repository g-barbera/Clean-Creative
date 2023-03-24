package me.gabriel.cleancreative.mixin;

import static net.minecraft.item.FireworkRocketItem.*;
import static net.minecraft.item.GoatHornItem.getStackForInstrument;
import static net.minecraft.item.Instruments.*;
import static net.minecraft.item.ItemGroup.*;
import static net.minecraft.item.Items.*;
import static net.minecraft.registry.Registries.INSTRUMENT;

import net.minecraft.block.SuspiciousStewIngredient;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.PaintingVariantTags;
import net.minecraft.village.raid.Raid;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * This class modifies the order of the items when the
 * {@link Builder#entries(EntryCollector) Builder.entries(EntryCollector)}
 * function is called for each ItemGroup.
 * <pre>{@code
 * // Example of original code
 * public static final ItemGroup BUILDING_BLOCKS = ItemGroup.create(ItemGroup.Row.TOP, 0)
 *     .entries((displayContext, entries) -> {...})
 *     .build();
 *
 * // How the method is injected
 * public static final ItemGroup BUILDING_BLOCKS = ItemGroup.create(ItemGroup.Row.TOP, 0)
 *     .entries(CleanItemGroups.modifier1((displayContext, entries) -> {...}))
 *     .build();
 * }</pre>
 * The methods annotated with @{@link ModifyArg} are arbitrarily named. The bottom methods annotated with @{@link Shadow} match
 * the signature of the methods shadowed from the target class.
 */
@Mixin(ItemGroups.class)
<<<<<<< HEAD:src/main/java/me/gabriel/cleancreative/mixin/ItemGroupsMixin.java
public class ItemGroupsMixin {
=======
public class CleanItemGroups {
>>>>>>> origin/main:src/main/java/me/gabriel/cleancreative/mixin/CleanItemGroups.java
    /**
     * Modifies {@link ItemGroups#BUILDING_BLOCKS}
     */
    @ModifyArg(method="<clinit>", at=@At(value="INVOKE", ordinal=0,
        target="Lnet/minecraft/item/ItemGroup$Builder;entries(Lnet/minecraft/item/ItemGroup$EntryCollector;)"
            +"Lnet/minecraft/item/ItemGroup$Builder;"))
    private static EntryCollector modifier1(EntryCollector entryCollector) {
        return (displayContext, entries) -> {
            Item[] items = {
                OAK_LOG, OAK_WOOD, OAK_PLANKS, OAK_STAIRS,
                STONE, COBBLESTONE, STONE_BRICKS, MOSSY_STONE_BRICKS, MOSSY_COBBLESTONE,
                STRIPPED_OAK_LOG, STRIPPED_OAK_WOOD, OAK_SLAB, OAK_SIGN,
                STONE_STAIRS, COBBLESTONE_STAIRS, STONE_BRICK_STAIRS, MOSSY_STONE_BRICK_STAIRS, MOSSY_COBBLESTONE_STAIRS,
                OAK_FENCE, OAK_FENCE_GATE, OAK_DOOR, OAK_TRAPDOOR,
                STONE_SLAB, COBBLESTONE_SLAB, STONE_BRICK_SLAB, MOSSY_STONE_BRICK_SLAB, MOSSY_COBBLESTONE_SLAB,
                SPRUCE_LOG, SPRUCE_WOOD, SPRUCE_PLANKS, SPRUCE_STAIRS,
                CHISELED_STONE_BRICKS, COBBLESTONE_WALL, STONE_BRICK_WALL, MOSSY_STONE_BRICK_WALL, MOSSY_COBBLESTONE_WALL,
                STRIPPED_SPRUCE_LOG, STRIPPED_SPRUCE_WOOD, SPRUCE_SLAB, SPRUCE_SIGN,
                CRACKED_STONE_BRICKS, DEEPSLATE, CHISELED_DEEPSLATE, CRACKED_DEEPSLATE_BRICKS, CRACKED_DEEPSLATE_TILES,
                SPRUCE_FENCE, SPRUCE_FENCE_GATE, SPRUCE_DOOR, SPRUCE_TRAPDOOR,
                REINFORCED_DEEPSLATE, COBBLED_DEEPSLATE, POLISHED_DEEPSLATE, DEEPSLATE_BRICKS, DEEPSLATE_TILES,
                BIRCH_LOG, BIRCH_WOOD, BIRCH_PLANKS, BIRCH_STAIRS,
                SMOOTH_STONE, COBBLED_DEEPSLATE_STAIRS, POLISHED_DEEPSLATE_STAIRS, DEEPSLATE_BRICK_STAIRS, DEEPSLATE_TILE_STAIRS,
                STRIPPED_BIRCH_LOG, STRIPPED_BIRCH_WOOD, BIRCH_SLAB, BIRCH_SIGN,
                SMOOTH_STONE_SLAB, COBBLED_DEEPSLATE_SLAB, POLISHED_DEEPSLATE_SLAB, DEEPSLATE_BRICK_SLAB, DEEPSLATE_TILE_SLAB,
                BIRCH_FENCE, BIRCH_FENCE_GATE, BIRCH_DOOR, BIRCH_TRAPDOOR,
                PACKED_MUD, COBBLED_DEEPSLATE_WALL, POLISHED_DEEPSLATE_WALL, DEEPSLATE_BRICK_WALL, DEEPSLATE_TILE_WALL,
                JUNGLE_LOG, JUNGLE_WOOD, JUNGLE_PLANKS, JUNGLE_STAIRS,
                CUT_SANDSTONE, SANDSTONE, RED_SANDSTONE, SMOOTH_SANDSTONE, SMOOTH_RED_SANDSTONE,
                STRIPPED_JUNGLE_LOG, STRIPPED_JUNGLE_WOOD, JUNGLE_SLAB, JUNGLE_SIGN,
                CUT_RED_SANDSTONE, SANDSTONE_STAIRS, RED_SANDSTONE_STAIRS, SMOOTH_SANDSTONE_STAIRS, SMOOTH_RED_SANDSTONE_STAIRS,
                JUNGLE_FENCE, JUNGLE_FENCE_GATE, JUNGLE_DOOR, JUNGLE_TRAPDOOR,
                CUT_SANDSTONE_SLAB, SANDSTONE_SLAB, RED_SANDSTONE_SLAB, SMOOTH_SANDSTONE_SLAB, SMOOTH_RED_SANDSTONE_SLAB,
                ACACIA_LOG, ACACIA_WOOD, ACACIA_PLANKS, ACACIA_STAIRS,
                CUT_RED_SANDSTONE_SLAB, SANDSTONE_WALL, RED_SANDSTONE_WALL, CHISELED_SANDSTONE, CHISELED_RED_SANDSTONE,
                STRIPPED_ACACIA_LOG, STRIPPED_ACACIA_WOOD, ACACIA_SLAB, ACACIA_SIGN,
                MUD_BRICKS, BRICKS, GRANITE, DIORITE, ANDESITE,
                ACACIA_FENCE, ACACIA_FENCE_GATE, ACACIA_DOOR, ACACIA_TRAPDOOR,
                MUD_BRICK_STAIRS, BRICK_STAIRS, GRANITE_STAIRS, DIORITE_STAIRS, ANDESITE_STAIRS,
                DARK_OAK_LOG, DARK_OAK_WOOD, DARK_OAK_PLANKS, DARK_OAK_STAIRS,
                MUD_BRICK_SLAB, BRICK_SLAB, GRANITE_SLAB, DIORITE_SLAB, ANDESITE_SLAB,
                STRIPPED_DARK_OAK_LOG, STRIPPED_DARK_OAK_WOOD, DARK_OAK_SLAB, DARK_OAK_SIGN,
                MUD_BRICK_WALL, BRICK_WALL, GRANITE_WALL, DIORITE_WALL, ANDESITE_WALL,
                DARK_OAK_FENCE, DARK_OAK_FENCE_GATE, DARK_OAK_DOOR, DARK_OAK_TRAPDOOR,
                NETHER_BRICKS, RED_NETHER_BRICKS, POLISHED_GRANITE, POLISHED_DIORITE, POLISHED_ANDESITE,
                MANGROVE_LOG, MANGROVE_WOOD, MANGROVE_PLANKS, MANGROVE_STAIRS,
                NETHER_BRICK_STAIRS, RED_NETHER_BRICK_STAIRS, POLISHED_GRANITE_STAIRS, POLISHED_DIORITE_STAIRS, POLISHED_ANDESITE_STAIRS,
                STRIPPED_MANGROVE_LOG, STRIPPED_MANGROVE_WOOD, MANGROVE_SLAB, MANGROVE_SIGN,
                NETHER_BRICK_SLAB, RED_NETHER_BRICK_SLAB, POLISHED_GRANITE_SLAB, POLISHED_DIORITE_SLAB, POLISHED_ANDESITE_SLAB,
                MANGROVE_FENCE, MANGROVE_FENCE_GATE, MANGROVE_DOOR, MANGROVE_TRAPDOOR,
                NETHER_BRICK_WALL, RED_NETHER_BRICK_WALL, NETHERRACK, CRACKED_NETHER_BRICKS, CHISELED_NETHER_BRICKS,
                CRIMSON_STEM, CRIMSON_HYPHAE, CRIMSON_PLANKS, CRIMSON_STAIRS,
                NETHER_BRICK_FENCE, PRISMARINE_WALL, PRISMARINE, PRISMARINE_BRICKS, DARK_PRISMARINE,
                STRIPPED_CRIMSON_STEM, STRIPPED_CRIMSON_HYPHAE, CRIMSON_SLAB, CRIMSON_SIGN,
                GILDED_BLACKSTONE, SEA_LANTERN, PRISMARINE_STAIRS, PRISMARINE_BRICK_STAIRS, DARK_PRISMARINE_STAIRS,
                CRIMSON_FENCE, CRIMSON_FENCE_GATE, CRIMSON_DOOR, CRIMSON_TRAPDOOR,
                CHISELED_POLISHED_BLACKSTONE, CRACKED_POLISHED_BLACKSTONE_BRICKS, PRISMARINE_SLAB, PRISMARINE_BRICK_SLAB, DARK_PRISMARINE_SLAB,
                WARPED_STEM, WARPED_HYPHAE, WARPED_PLANKS, WARPED_STAIRS,
                END_STONE, END_STONE_BRICKS, BLACKSTONE, POLISHED_BLACKSTONE, POLISHED_BLACKSTONE_BRICKS,
                STRIPPED_WARPED_STEM, STRIPPED_WARPED_HYPHAE, WARPED_SLAB, WARPED_SIGN,
                BASALT, END_STONE_BRICK_STAIRS, BLACKSTONE_STAIRS, POLISHED_BLACKSTONE_STAIRS, POLISHED_BLACKSTONE_BRICK_STAIRS,
                WARPED_FENCE, WARPED_FENCE_GATE, WARPED_DOOR, WARPED_TRAPDOOR,
                POLISHED_BASALT, END_STONE_BRICK_SLAB, BLACKSTONE_SLAB, POLISHED_BLACKSTONE_SLAB, POLISHED_BLACKSTONE_BRICK_SLAB,
                PURPUR_PILLAR, PURPUR_BLOCK, PURPUR_STAIRS, PURPUR_SLAB,
                SMOOTH_BASALT, END_STONE_BRICK_WALL, BLACKSTONE_WALL, POLISHED_BLACKSTONE_WALL, POLISHED_BLACKSTONE_BRICK_WALL,
                COAL_BLOCK, IRON_BLOCK, GOLD_BLOCK, REDSTONE_BLOCK, EMERALD_BLOCK, LAPIS_BLOCK, DIAMOND_BLOCK, NETHERITE_BLOCK, AMETHYST_BLOCK,
                QUARTZ_BLOCK, QUARTZ_STAIRS, QUARTZ_SLAB, SMOOTH_QUARTZ, SMOOTH_QUARTZ_STAIRS,
                SMOOTH_QUARTZ_SLAB, QUARTZ_BRICKS, QUARTZ_PILLAR, CHISELED_QUARTZ_BLOCK,
                COPPER_BLOCK, CUT_COPPER, CUT_COPPER_STAIRS, CUT_COPPER_SLAB,
                WAXED_COPPER_BLOCK, WAXED_CUT_COPPER, WAXED_CUT_COPPER_STAIRS, WAXED_CUT_COPPER_SLAB, IRON_DOOR,
                EXPOSED_COPPER, EXPOSED_CUT_COPPER, EXPOSED_CUT_COPPER_STAIRS, EXPOSED_CUT_COPPER_SLAB,
                WAXED_EXPOSED_COPPER, WAXED_EXPOSED_CUT_COPPER, WAXED_EXPOSED_CUT_COPPER_STAIRS, WAXED_EXPOSED_CUT_COPPER_SLAB, IRON_TRAPDOOR,
                WEATHERED_COPPER, WEATHERED_CUT_COPPER, WEATHERED_CUT_COPPER_STAIRS, WEATHERED_CUT_COPPER_SLAB,
                WAXED_WEATHERED_COPPER, WAXED_WEATHERED_CUT_COPPER, WAXED_WEATHERED_CUT_COPPER_STAIRS, WAXED_WEATHERED_CUT_COPPER_SLAB, IRON_BARS,
                OXIDIZED_COPPER, OXIDIZED_CUT_COPPER, OXIDIZED_CUT_COPPER_STAIRS, OXIDIZED_CUT_COPPER_SLAB,
                WAXED_OXIDIZED_COPPER, WAXED_OXIDIZED_CUT_COPPER, WAXED_OXIDIZED_CUT_COPPER_STAIRS, WAXED_OXIDIZED_CUT_COPPER_SLAB, CHAIN,
            };
            for (Item item : items) entries.add(item);
        };
    }

    /**
     * Modifies {@link ItemGroups#COLORED_BLOCKS}
     */
    @ModifyArg(method="<clinit>", at=@At(value="INVOKE", ordinal=1,
        target="Lnet/minecraft/item/ItemGroup$Builder;entries(Lnet/minecraft/item/ItemGroup$EntryCollector;)"
            +"Lnet/minecraft/item/ItemGroup$Builder;"))
    private static EntryCollector modifier2(EntryCollector entryCollector) {
        return (displayContext, entries) -> {
            Item[] items = {
                RED_WOOL, ORANGE_WOOL, YELLOW_WOOL, LIME_WOOL,
                GREEN_WOOL, CYAN_WOOL, BLUE_WOOL, LIGHT_BLUE_WOOL, TERRACOTTA,
                PURPLE_WOOL, MAGENTA_WOOL, PINK_WOOL, BROWN_WOOL,
                WHITE_WOOL, LIGHT_GRAY_WOOL, GRAY_WOOL, BLACK_WOOL, SHULKER_BOX,
                RED_CARPET, ORANGE_CARPET, YELLOW_CARPET, LIME_CARPET,
                GREEN_CARPET, CYAN_CARPET, BLUE_CARPET, LIGHT_BLUE_CARPET, GLASS,
                PURPLE_CARPET, MAGENTA_CARPET, PINK_CARPET, BROWN_CARPET,
                WHITE_CARPET, LIGHT_GRAY_CARPET, GRAY_CARPET, BLACK_CARPET, TINTED_GLASS,
                RED_CONCRETE_POWDER, ORANGE_CONCRETE_POWDER, YELLOW_CONCRETE_POWDER, LIME_CONCRETE_POWDER,
                GREEN_CONCRETE_POWDER, CYAN_CONCRETE_POWDER, BLUE_CONCRETE_POWDER, LIGHT_BLUE_CONCRETE_POWDER, GLASS_PANE,
                PURPLE_CONCRETE_POWDER, MAGENTA_CONCRETE_POWDER, PINK_CONCRETE_POWDER, BROWN_CONCRETE_POWDER,
                WHITE_CONCRETE_POWDER, LIGHT_GRAY_CONCRETE_POWDER, GRAY_CONCRETE_POWDER, BLACK_CONCRETE_POWDER, CANDLE,
                RED_CONCRETE, ORANGE_CONCRETE, YELLOW_CONCRETE, LIME_CONCRETE,
                GREEN_CONCRETE, CYAN_CONCRETE, BLUE_CONCRETE, LIGHT_BLUE_CONCRETE, RED_CANDLE,
                PURPLE_CONCRETE, MAGENTA_CONCRETE, PINK_CONCRETE, BROWN_CONCRETE,
                WHITE_CONCRETE, LIGHT_GRAY_CONCRETE, GRAY_CONCRETE, BLACK_CONCRETE, ORANGE_CANDLE,
                RED_TERRACOTTA, ORANGE_TERRACOTTA, YELLOW_TERRACOTTA, LIME_TERRACOTTA,
                GREEN_TERRACOTTA, CYAN_TERRACOTTA, BLUE_TERRACOTTA, LIGHT_BLUE_TERRACOTTA, YELLOW_CANDLE,
                PURPLE_TERRACOTTA, MAGENTA_TERRACOTTA, PINK_TERRACOTTA, BROWN_TERRACOTTA,
                WHITE_TERRACOTTA, LIGHT_GRAY_TERRACOTTA, GRAY_TERRACOTTA, BLACK_TERRACOTTA, LIME_CANDLE,
                RED_GLAZED_TERRACOTTA, ORANGE_GLAZED_TERRACOTTA, YELLOW_GLAZED_TERRACOTTA, LIME_GLAZED_TERRACOTTA,
                GREEN_GLAZED_TERRACOTTA, CYAN_GLAZED_TERRACOTTA, BLUE_GLAZED_TERRACOTTA, LIGHT_BLUE_GLAZED_TERRACOTTA, GREEN_CANDLE,
                PURPLE_GLAZED_TERRACOTTA, MAGENTA_GLAZED_TERRACOTTA, PINK_GLAZED_TERRACOTTA, BROWN_GLAZED_TERRACOTTA,
                WHITE_GLAZED_TERRACOTTA, LIGHT_GRAY_GLAZED_TERRACOTTA, GRAY_GLAZED_TERRACOTTA, BLACK_GLAZED_TERRACOTTA, CYAN_CANDLE,
                RED_STAINED_GLASS, ORANGE_STAINED_GLASS, YELLOW_STAINED_GLASS, LIME_STAINED_GLASS,
                GREEN_STAINED_GLASS, CYAN_STAINED_GLASS, BLUE_STAINED_GLASS, LIGHT_BLUE_STAINED_GLASS, BLUE_CANDLE,
                PURPLE_STAINED_GLASS, MAGENTA_STAINED_GLASS, PINK_STAINED_GLASS, BROWN_STAINED_GLASS,
                WHITE_STAINED_GLASS, LIGHT_GRAY_STAINED_GLASS, GRAY_STAINED_GLASS, BLACK_STAINED_GLASS, LIGHT_BLUE_CANDLE,
                RED_STAINED_GLASS_PANE, ORANGE_STAINED_GLASS_PANE, YELLOW_STAINED_GLASS_PANE, LIME_STAINED_GLASS_PANE,
                GREEN_STAINED_GLASS_PANE, CYAN_STAINED_GLASS_PANE, BLUE_STAINED_GLASS_PANE, LIGHT_BLUE_STAINED_GLASS_PANE, PURPLE_CANDLE,
                PURPLE_STAINED_GLASS_PANE, MAGENTA_STAINED_GLASS_PANE, PINK_STAINED_GLASS_PANE, BROWN_STAINED_GLASS_PANE,
                WHITE_STAINED_GLASS_PANE, LIGHT_GRAY_STAINED_GLASS_PANE, GRAY_STAINED_GLASS_PANE, BLACK_STAINED_GLASS_PANE, MAGENTA_CANDLE,
                RED_SHULKER_BOX, ORANGE_SHULKER_BOX, YELLOW_SHULKER_BOX, LIME_SHULKER_BOX,
                GREEN_SHULKER_BOX, CYAN_SHULKER_BOX, BLUE_SHULKER_BOX, LIGHT_BLUE_SHULKER_BOX, PINK_CANDLE,
                PURPLE_SHULKER_BOX, MAGENTA_SHULKER_BOX, PINK_SHULKER_BOX, BROWN_SHULKER_BOX,
                WHITE_SHULKER_BOX, LIGHT_GRAY_SHULKER_BOX, GRAY_SHULKER_BOX, BLACK_SHULKER_BOX, BROWN_CANDLE,
                RED_BED, ORANGE_BED, YELLOW_BED, LIME_BED,
                GREEN_BED, CYAN_BED, BLUE_BED, LIGHT_BLUE_BED, WHITE_CANDLE,
                PURPLE_BED, MAGENTA_BED, PINK_BED, BROWN_BED,
                WHITE_BED, LIGHT_GRAY_BED, GRAY_BED, BLACK_BED, LIGHT_GRAY_CANDLE,
                RED_BANNER, ORANGE_BANNER, YELLOW_BANNER, LIME_BANNER,
                GREEN_BANNER, CYAN_BANNER, BLUE_BANNER, LIGHT_BLUE_BANNER, GRAY_CANDLE,
                PURPLE_BANNER, MAGENTA_BANNER, PINK_BANNER, BROWN_BANNER,
                WHITE_BANNER, LIGHT_GRAY_BANNER, GRAY_BANNER, BLACK_BANNER, BLACK_CANDLE,
            };
            for (Item item: items) entries.add(item);
        };
    }

    /**
     * Modifies {@link ItemGroups#NATURAL}
     */
    @ModifyArg(method="<clinit>", at=@At(value="INVOKE", ordinal=2,
        target="Lnet/minecraft/item/ItemGroup$Builder;entries(Lnet/minecraft/item/ItemGroup$EntryCollector;)"
            +"Lnet/minecraft/item/ItemGroup$Builder;"))
    private static EntryCollector modifier3(EntryCollector entryCollector) {
        return (displayContext, entries) -> {
            Item[] items = {
                GRASS_BLOCK, PODZOL, MYCELIUM, DIRT_PATH, STONE, TUFF, OBSIDIAN, GRANITE, ICE,
                DIRT, COARSE_DIRT, ROOTED_DIRT, FARMLAND, DEEPSLATE, CALCITE, CRYING_OBSIDIAN, DIORITE, PACKED_ICE,
                MUD, GRAVEL, SAND, RED_SAND, DRIPSTONE_BLOCK, MOSS_BLOCK, SNOW_BLOCK, ANDESITE, BLUE_ICE,
                CLAY, SMOOTH_BASALT, SANDSTONE, RED_SANDSTONE, POINTED_DRIPSTONE, MOSS_CARPET, SNOW, PRISMARINE, MAGMA_BLOCK,
                BASALT, NETHERRACK, CRIMSON_NYLIUM, WARPED_NYLIUM, BONE_BLOCK, BLACKSTONE, SOUL_SAND, SOUL_SOIL, END_STONE,
                COAL_ORE, IRON_ORE, COPPER_ORE, GOLD_ORE, REDSTONE_ORE, EMERALD_ORE, LAPIS_ORE, DIAMOND_ORE, NETHER_GOLD_ORE,
                DEEPSLATE_COAL_ORE, DEEPSLATE_IRON_ORE, DEEPSLATE_COPPER_ORE, DEEPSLATE_GOLD_ORE, DEEPSLATE_REDSTONE_ORE,
                DEEPSLATE_EMERALD_ORE, DEEPSLATE_LAPIS_ORE, DEEPSLATE_DIAMOND_ORE, NETHER_QUARTZ_ORE,
                ANCIENT_DEBRIS, RAW_IRON_BLOCK, RAW_COPPER_BLOCK, RAW_GOLD_BLOCK, GLOWSTONE, RED_MUSHROOM, RED_MUSHROOM_BLOCK, BROWN_MUSHROOM, BROWN_MUSHROOM_BLOCK,
                OAK_LOG, SPRUCE_LOG, BIRCH_LOG, JUNGLE_LOG, ACACIA_LOG, DARK_OAK_LOG, MANGROVE_LOG, CRIMSON_STEM, WARPED_STEM,
                OAK_SAPLING, SPRUCE_SAPLING, BIRCH_SAPLING, JUNGLE_SAPLING, ACACIA_SAPLING, DARK_OAK_SAPLING, MANGROVE_PROPAGULE, CRIMSON_FUNGUS, WARPED_FUNGUS,
                OAK_LEAVES, SPRUCE_LEAVES, BIRCH_LEAVES, JUNGLE_LEAVES, ACACIA_LEAVES, DARK_OAK_LEAVES, MANGROVE_LEAVES, NETHER_WART_BLOCK, WARPED_WART_BLOCK,
                MUSHROOM_STEM, AZALEA, AZALEA_LEAVES, MANGROVE_ROOTS, AMETHYST_BLOCK, SMALL_AMETHYST_BUD, MEDIUM_AMETHYST_BUD, WEEPING_VINES, TWISTING_VINES,
                SHROOMLIGHT, FLOWERING_AZALEA, FLOWERING_AZALEA_LEAVES, MUDDY_MANGROVE_ROOTS, BUDDING_AMETHYST, LARGE_AMETHYST_BUD, AMETHYST_CLUSTER, CRIMSON_ROOTS, WARPED_ROOTS,
                DANDELION, POPPY, ALLIUM, AZURE_BLUET, RED_TULIP, ORANGE_TULIP, OXEYE_DAISY, GRASS, FERN,
                CORNFLOWER, LILY_OF_THE_VALLEY, SPORE_BLOSSOM, BLUE_ORCHID, PINK_TULIP, WHITE_TULIP, DEAD_BUSH, TALL_GRASS, LARGE_FERN,
                PEONY, ROSE_BUSH, LILAC, SUNFLOWER, BIG_DRIPLEAF, SMALL_DRIPLEAF, WITHER_ROSE, BAMBOO, SUGAR_CANE,
                TURTLE_EGG, FROGSPAWN, HANGING_ROOTS, CHORUS_PLANT, CHORUS_FLOWER, PUMPKIN_SEEDS, COCOA_BEANS, SEA_PICKLE, SEAGRASS,
                GLOW_LICHEN, LILY_PAD, NETHER_WART, SWEET_BERRIES, GLOW_BERRIES, MELON_SEEDS, BEETROOT_SEEDS, NETHER_SPROUTS, KELP,
                SCULK, SCULK_VEIN, SCULK_CATALYST, SCULK_SHRIEKER, SCULK_SENSOR, WHEAT_SEEDS, COBWEB, VINE, CACTUS,
                TUBE_CORAL_BLOCK, DEAD_TUBE_CORAL_BLOCK, TUBE_CORAL, DEAD_TUBE_CORAL, TUBE_CORAL_FAN, DEAD_TUBE_CORAL_FAN, OCHRE_FROGLIGHT, VERDANT_FROGLIGHT, PEARLESCENT_FROGLIGHT,
                BRAIN_CORAL_BLOCK, DEAD_BRAIN_CORAL_BLOCK, BRAIN_CORAL, DEAD_BRAIN_CORAL, BRAIN_CORAL_FAN, DEAD_BRAIN_CORAL_FAN, PUMPKIN, CARVED_PUMPKIN, JACK_O_LANTERN,
                BUBBLE_CORAL_BLOCK, DEAD_BUBBLE_CORAL_BLOCK, BUBBLE_CORAL, DEAD_BUBBLE_CORAL, BUBBLE_CORAL_FAN, DEAD_BUBBLE_CORAL_FAN, MELON, BEE_NEST, HONEYCOMB_BLOCK,
                FIRE_CORAL_BLOCK, DEAD_FIRE_CORAL_BLOCK, FIRE_CORAL, DEAD_FIRE_CORAL, FIRE_CORAL_FAN, DEAD_FIRE_CORAL_FAN, SLIME_BLOCK, HONEY_BLOCK, DRIED_KELP_BLOCK,
                HORN_CORAL_BLOCK, DEAD_HORN_CORAL_BLOCK, HORN_CORAL, DEAD_HORN_CORAL, HORN_CORAL_FAN, DEAD_HORN_CORAL_FAN, SPONGE, WET_SPONGE, HAY_BLOCK,
                BEDROCK
            };
            for (Item item: items) entries.add(item);
        };
    }

    /**
     * Modifies {@link ItemGroups#FUNCTIONAL}
     */
    @ModifyArg(method="<clinit>", at=@At(value="INVOKE", ordinal=3,
        target="Lnet/minecraft/item/ItemGroup$Builder;entries(Lnet/minecraft/item/ItemGroup$EntryCollector;)"
            +"Lnet/minecraft/item/ItemGroup$Builder;"))
    private static EntryCollector modifier4(EntryCollector entryCollector) {
        return (displayContext, entries) -> {
            Item[] items = {
                CRAFTING_TABLE, FURNACE, CHEST, ENDER_CHEST, BREWING_STAND, ANVIL, CHIPPED_ANVIL, DAMAGED_ANVIL, ENCHANTING_TABLE,
                STONECUTTER, SMOKER, BARREL, LOOM, CAULDRON, CARTOGRAPHY_TABLE, BEACON, BEEHIVE, BEE_NEST,
                LECTERN, BLAST_FURNACE, SMITHING_TABLE, FLETCHING_TABLE, GRINDSTONE, COMPOSTER, BELL, GLOW_LICHEN, LODESTONE,
                CAMPFIRE, LANTERN, TORCH, REDSTONE_TORCH, GLOWSTONE, SHROOMLIGHT, MAGMA_BLOCK, CRYING_OBSIDIAN, RESPAWN_ANCHOR,
                SOUL_CAMPFIRE, SOUL_LANTERN, SOUL_TORCH, END_ROD, OCHRE_FROGLIGHT, VERDANT_FROGLIGHT, PEARLESCENT_FROGLIGHT, SEA_LANTERN, REDSTONE_LAMP,
                BOOKSHELF, CHAIN, LADDER, SCAFFOLDING, LIGHTNING_ROD, FLOWER_POT, ARMOR_STAND, ITEM_FRAME, GLOW_ITEM_FRAME,
                PAINTING,
                OAK_SIGN, SPRUCE_SIGN, BIRCH_SIGN, JUNGLE_SIGN, ACACIA_SIGN, DARK_OAK_SIGN, MANGROVE_SIGN, CRIMSON_SIGN, WARPED_SIGN,
                RED_SHULKER_BOX, ORANGE_SHULKER_BOX, YELLOW_SHULKER_BOX, LIME_SHULKER_BOX,
                GREEN_SHULKER_BOX, CYAN_SHULKER_BOX, BLUE_SHULKER_BOX, LIGHT_BLUE_SHULKER_BOX, SHULKER_BOX,
                PURPLE_SHULKER_BOX, MAGENTA_SHULKER_BOX, PINK_SHULKER_BOX, BROWN_SHULKER_BOX,
                WHITE_SHULKER_BOX, LIGHT_GRAY_SHULKER_BOX, GRAY_SHULKER_BOX, BLACK_SHULKER_BOX, TINTED_GLASS,
                RED_BED, ORANGE_BED, YELLOW_BED, LIME_BED, GREEN_BED, CYAN_BED, BLUE_BED, LIGHT_BLUE_BED, JUKEBOX,
                PURPLE_BED, MAGENTA_BED, PINK_BED, BROWN_BED, WHITE_BED, LIGHT_GRAY_BED, GRAY_BED, BLACK_BED, NOTE_BLOCK,
                RED_CANDLE, ORANGE_CANDLE, YELLOW_CANDLE, LIME_CANDLE, GREEN_CANDLE, CYAN_CANDLE, BLUE_CANDLE, LIGHT_BLUE_CANDLE, CANDLE,
                PURPLE_CANDLE, MAGENTA_CANDLE, PINK_CANDLE, BROWN_CANDLE, WHITE_CANDLE, LIGHT_GRAY_CANDLE, GRAY_CANDLE, BLACK_CANDLE, CONDUIT,
                RED_BANNER, ORANGE_BANNER, YELLOW_BANNER, LIME_BANNER, GREEN_BANNER, CYAN_BANNER, BLUE_BANNER, LIGHT_BLUE_BANNER, // (ominous banner here)
                PURPLE_BANNER, MAGENTA_BANNER, PINK_BANNER, BROWN_BANNER, WHITE_BANNER, LIGHT_GRAY_BANNER, GRAY_BANNER, BLACK_BANNER, END_CRYSTAL,
                SKELETON_SKULL, WITHER_SKELETON_SKULL, PLAYER_HEAD, INFESTED_STONE, INFESTED_COBBLESTONE, INFESTED_DEEPSLATE, DRAGON_EGG, END_PORTAL_FRAME, ENDER_EYE,
                ZOMBIE_HEAD, CREEPER_HEAD, DRAGON_HEAD, INFESTED_STONE_BRICKS, INFESTED_MOSSY_STONE_BRICKS, INFESTED_CRACKED_STONE_BRICKS, INFESTED_CHISELED_STONE_BRICKS
            };
            for (Item item: items) {
                if (item==OAK_SIGN)
                    displayContext.lookup().getOptionalWrapper(RegistryKeys.PAINTING_VARIANT)
                        .ifPresent(registryWrapper -> addPaintings(
                            entries, registryWrapper, entry -> entry.isIn(PaintingVariantTags.PLACEABLE),
                            StackVisibility.PARENT_AND_SEARCH_TABS
                        ));
                else if (item==PURPLE_BANNER) entries.add(Raid.getOminousBanner());
                entries.add(item);
            }
        };
    }

    /**
     * Modifies {@link ItemGroups#REDSTONE}
     */
    @ModifyArg(method="<clinit>", at=@At(value="INVOKE", ordinal=4,
        target="Lnet/minecraft/item/ItemGroup$Builder;entries(Lnet/minecraft/item/ItemGroup$EntryCollector;)"
            +"Lnet/minecraft/item/ItemGroup$Builder;"))
    private static EntryCollector modifier5(EntryCollector entryCollector) {
        return (displayContext, entries) -> {
            Item[] items = {
                REDSTONE, REDSTONE_TORCH, REPEATER, COMPARATOR, REDSTONE_BLOCK, PISTON, STICKY_PISTON, REDSTONE_LAMP, NOTE_BLOCK,
                TARGET, LIGHTNING_ROD, TRIPWIRE_HOOK, LEVER, DAYLIGHT_DETECTOR, TNT, HONEY_BLOCK, SLIME_BLOCK, SCULK_SENSOR,
                OBSERVER, DISPENSER, DROPPER, HOPPER, TRAPPED_CHEST, HEAVY_WEIGHTED_PRESSURE_PLATE, LIGHT_WEIGHTED_PRESSURE_PLATE, IRON_TRAPDOOR, IRON_DOOR,
                OAK_DOOR, SPRUCE_DOOR, BIRCH_DOOR, JUNGLE_DOOR, ACACIA_DOOR, DARK_OAK_DOOR, MANGROVE_DOOR, CRIMSON_DOOR, WARPED_DOOR,
                OAK_FENCE_GATE, SPRUCE_FENCE_GATE, BIRCH_FENCE_GATE, JUNGLE_FENCE_GATE, ACACIA_FENCE_GATE, DARK_OAK_FENCE_GATE, MANGROVE_FENCE_GATE, CRIMSON_FENCE_GATE, WARPED_FENCE_GATE,
                OAK_TRAPDOOR, SPRUCE_TRAPDOOR, BIRCH_TRAPDOOR, JUNGLE_TRAPDOOR, ACACIA_TRAPDOOR, DARK_OAK_TRAPDOOR, MANGROVE_TRAPDOOR, CRIMSON_TRAPDOOR, WARPED_TRAPDOOR,
                OAK_PRESSURE_PLATE, SPRUCE_PRESSURE_PLATE, BIRCH_PRESSURE_PLATE, JUNGLE_PRESSURE_PLATE, ACACIA_PRESSURE_PLATE, DARK_OAK_PRESSURE_PLATE, MANGROVE_PRESSURE_PLATE, CRIMSON_PRESSURE_PLATE, WARPED_PRESSURE_PLATE,
                OAK_BUTTON, SPRUCE_BUTTON, BIRCH_BUTTON, JUNGLE_BUTTON, ACACIA_BUTTON, DARK_OAK_BUTTON, MANGROVE_BUTTON, CRIMSON_BUTTON, WARPED_BUTTON,
                STONE_PRESSURE_PLATE, POLISHED_BLACKSTONE_PRESSURE_PLATE, STONE_BUTTON, POLISHED_BLACKSTONE_BUTTON
            };
            for (Item item : items) entries.add(item);
        };
    }

    /**
     * Modifies {@link ItemGroups#TOOLS}
     */
    @ModifyArg(method="<clinit>", at=@At(value="INVOKE", ordinal=6,
        target="Lnet/minecraft/item/ItemGroup$Builder;entries(Lnet/minecraft/item/ItemGroup$EntryCollector;)"
            +"Lnet/minecraft/item/ItemGroup$Builder;"))
    private static EntryCollector modifier6(EntryCollector entryCollector) {
        return (displayContext, entries) -> {
            Item[] items = {
                WOODEN_SHOVEL, WOODEN_PICKAXE, WOODEN_AXE, WOODEN_HOE, BUCKET, COD_BUCKET, SALMON_BUCKET, COMPASS, SPYGLASS,
                STONE_SHOVEL, STONE_PICKAXE, STONE_AXE, STONE_HOE, WATER_BUCKET, TROPICAL_FISH_BUCKET, TADPOLE_BUCKET, RECOVERY_COMPASS, SHEARS,
                IRON_SHOVEL, IRON_PICKAXE, IRON_AXE, IRON_HOE, LAVA_BUCKET, PUFFERFISH_BUCKET, AXOLOTL_BUCKET, CLOCK, FIREWORK_ROCKET,
                GOLDEN_SHOVEL, GOLDEN_PICKAXE, GOLDEN_AXE, GOLDEN_HOE, POWDER_SNOW_BUCKET, MILK_BUCKET, ENDER_PEARL, BONE_MEAL, FIREWORK_ROCKET,
                DIAMOND_SHOVEL, DIAMOND_PICKAXE, DIAMOND_AXE, DIAMOND_HOE, MAP, WRITABLE_BOOK, ENDER_EYE, SADDLE, FIREWORK_ROCKET,
                NETHERITE_SHOVEL, NETHERITE_PICKAXE, NETHERITE_AXE, NETHERITE_HOE, ELYTRA, LEAD, FIRE_CHARGE, NAME_TAG, FLINT_AND_STEEL,
                FISHING_ROD, CARROT_ON_A_STICK, OAK_BOAT, SPRUCE_BOAT, BIRCH_BOAT, JUNGLE_BOAT, ACACIA_BOAT, DARK_OAK_BOAT, MANGROVE_BOAT,
                MINECART, WARPED_FUNGUS_ON_A_STICK, OAK_CHEST_BOAT, SPRUCE_CHEST_BOAT, BIRCH_CHEST_BOAT, JUNGLE_CHEST_BOAT, ACACIA_CHEST_BOAT, DARK_OAK_CHEST_BOAT, MANGROVE_CHEST_BOAT,
                CHEST_MINECART, FURNACE_MINECART, RAIL, POWERED_RAIL, MUSIC_DISC_13, MUSIC_DISC_CAT, MUSIC_DISC_BLOCKS, MUSIC_DISC_CHIRP, MUSIC_DISC_FAR,
                TNT_MINECART, HOPPER_MINECART, ACTIVATOR_RAIL, DETECTOR_RAIL, MUSIC_DISC_MALL, MUSIC_DISC_MELLOHI, MUSIC_DISC_STAL, MUSIC_DISC_STRAD, MUSIC_DISC_WARD,
                GOAT_HORN, GOAT_HORN, GOAT_HORN, GOAT_HORN, MUSIC_DISC_11, MUSIC_DISC_WAIT, MUSIC_DISC_OTHERSIDE, MUSIC_DISC_5, MUSIC_DISC_PIGSTEP,
                GOAT_HORN, GOAT_HORN, GOAT_HORN, GOAT_HORN
            };
            ItemStack stack;
            byte i=0;
            Iterator<RegistryEntry<Instrument>> iter = Stream.of(
                PONDER_GOAT_HORN, SING_GOAT_HORN, SEEK_GOAT_HORN, FEEL_GOAT_HORN, ADMIRE_GOAT_HORN, CALL_GOAT_HORN, YEARN_GOAT_HORN, DREAM_GOAT_HORN
            ).map(horn -> INSTRUMENT.getEntry(INSTRUMENT.get(horn))).iterator();
            for (Item item: items) {
                stack = new ItemStack(item);
                if (item == FIREWORK_ROCKET) stack.getOrCreateSubNbt(FIREWORKS_KEY).putByte(FLIGHT_KEY, ++i);
                if (item == GOAT_HORN) stack = getStackForInstrument(item, iter.next());
                entries.add(stack);
            }
        };
    }

    /**
     * Modifies {@link ItemGroups#COMBAT}
     */
    @ModifyArg(method="<clinit>", at=@At(value="INVOKE", ordinal=7,
        target="Lnet/minecraft/item/ItemGroup$Builder;entries(Lnet/minecraft/item/ItemGroup$EntryCollector;)"
            +"Lnet/minecraft/item/ItemGroup$Builder;"))
    private static EntryCollector modifier7(EntryCollector entryCollector) {
        return (displayContext, entries) -> {
            Item[] items = {
                WOODEN_SWORD, WOODEN_AXE, LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_LEGGINGS, LEATHER_BOOTS, LEATHER_HORSE_ARMOR, SNOWBALL, EGG,
                STONE_SWORD, STONE_AXE, CHAINMAIL_HELMET, CHAINMAIL_CHESTPLATE, CHAINMAIL_LEGGINGS, CHAINMAIL_BOOTS, TURTLE_HELMET, SHIELD, TRIDENT,
                IRON_SWORD, IRON_AXE, IRON_HELMET, IRON_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS, IRON_HORSE_ARMOR, BOW, FIREWORK_ROCKET,
                GOLDEN_SWORD, GOLDEN_AXE, GOLDEN_HELMET, GOLDEN_CHESTPLATE, GOLDEN_LEGGINGS, GOLDEN_BOOTS, GOLDEN_HORSE_ARMOR, CROSSBOW, FIREWORK_ROCKET,
                DIAMOND_SWORD, DIAMOND_AXE, DIAMOND_HELMET, DIAMOND_CHESTPLATE, DIAMOND_LEGGINGS, DIAMOND_BOOTS, DIAMOND_HORSE_ARMOR, TOTEM_OF_UNDYING, FIREWORK_ROCKET,
                NETHERITE_SWORD, NETHERITE_AXE, NETHERITE_HELMET, NETHERITE_CHESTPLATE, NETHERITE_LEGGINGS, NETHERITE_BOOTS, END_CRYSTAL, TNT
            };
            ItemStack stack;
            byte i=0;
            for (Item item: items) {
                stack = new ItemStack(item);
                if (item == FIREWORK_ROCKET) setFlight(stack, ++i);
                entries.add(stack);
            }
            displayContext.lookup().getOptionalWrapper(RegistryKeys.POTION).ifPresent(registryWrapper ->
                addPotions(entries, registryWrapper, TIPPED_ARROW, StackVisibility.PARENT_AND_SEARCH_TABS)
            );
        };
    }

    /**
     * Modifies {@link ItemGroups#FOOD_AND_DRINK}
     */
    @ModifyArg(method="<clinit>", at=@At(value="INVOKE", ordinal=8,
        target="Lnet/minecraft/item/ItemGroup$Builder;entries(Lnet/minecraft/item/ItemGroup$EntryCollector;)"
            +"Lnet/minecraft/item/ItemGroup$Builder;"))
    private static EntryCollector modifier8(EntryCollector entryCollector) {
        return (displayContext, entries) -> {
            Item[] items = {
                PORKCHOP, COOKED_PORKCHOP, COD, COOKED_COD, DRIED_KELP, MUSHROOM_STEW, SUSPICIOUS_STEW, SUSPICIOUS_STEW, SUSPICIOUS_STEW,
                BEEF, COOKED_BEEF, SALMON, COOKED_SALMON, BEETROOT, BEETROOT_SOUP, SUSPICIOUS_STEW, SUSPICIOUS_STEW, SUSPICIOUS_STEW,
                MUTTON, COOKED_MUTTON, RABBIT, COOKED_RABBIT, CARROT, RABBIT_STEW, SUSPICIOUS_STEW, SUSPICIOUS_STEW, SUSPICIOUS_STEW,
                CHICKEN, COOKED_CHICKEN, POTATO, BAKED_POTATO, SWEET_BERRIES, GOLDEN_CARROT, GOLDEN_APPLE, ENCHANTED_GOLDEN_APPLE, CHORUS_FRUIT,
                TROPICAL_FISH, APPLE, GLOW_BERRIES, MELON_SLICE, HONEY_BOTTLE, SPIDER_EYE, POISONOUS_POTATO, ROTTEN_FLESH, PUFFERFISH,
                BREAD, PUMPKIN_PIE, CAKE, MILK_BUCKET
            };
            List<SuspiciousStewIngredient> list = SuspiciousStewIngredient.getAll();
            Set<ItemStack> set = ItemStackSet.create();
            for (SuspiciousStewIngredient suspiciousStewIngredient : list) {
                ItemStack itemStack = new ItemStack(Items.SUSPICIOUS_STEW);
                SuspiciousStewItem.addEffectToStew(itemStack, suspiciousStewIngredient.getEffectInStew(), suspiciousStewIngredient.getEffectInStewDuration());
                set.add(itemStack);
            }
            Iterator<ItemStack> iter = set.iterator();
            ItemStack stack;
            for (Item item: items) {
                stack = new ItemStack(item);
                if (item == SUSPICIOUS_STEW) entries.add(iter.next(), StackVisibility.PARENT_AND_SEARCH_TABS);
                else entries.add(stack);
            }
            displayContext.lookup().getOptionalWrapper(RegistryKeys.POTION).ifPresent(registryWrapper -> {
                addPotions(entries, registryWrapper, POTION, StackVisibility.PARENT_AND_SEARCH_TABS);
                addPotions(entries, registryWrapper, SPLASH_POTION, StackVisibility.PARENT_AND_SEARCH_TABS);
                addPotions(entries, registryWrapper, LINGERING_POTION, StackVisibility.PARENT_AND_SEARCH_TABS);
            });
        };
    }

    /**
     * Modifies {@link ItemGroups#INGREDIENTS}
     */
    @ModifyArg(method="<clinit>", at=@At(value="INVOKE", ordinal=9,
        target="Lnet/minecraft/item/ItemGroup$Builder;entries(Lnet/minecraft/item/ItemGroup$EntryCollector;)"
            +"Lnet/minecraft/item/ItemGroup$Builder;"))
    private static EntryCollector modifier9(EntryCollector entryCollector) {
        return (displayContext, entries) -> {
            Item[] items = {
                COAL, IRON_INGOT, COPPER_INGOT, GOLD_INGOT, NETHERITE_INGOT, DIAMOND, LAPIS_LAZULI, BLAZE_ROD, SNOWBALL,
                CHARCOAL, RAW_IRON, RAW_COPPER, RAW_GOLD, NETHERITE_SCRAP, EMERALD, QUARTZ, STRING, EGG,
                HONEYCOMB, IRON_NUGGET, AMETHYST_SHARD, GOLD_NUGGET, ANCIENT_DEBRIS, STICK, FLINT, FEATHER, CLAY_BALL,
                ECHO_SHARD, DISC_FRAGMENT_5, BOWL, FIRE_CHARGE, WHEAT, SCUTE, SLIME_BALL, FIREWORK_STAR, NETHER_STAR,
                INK_SAC, BRICK, SHULKER_SHELL, BONE, ENDER_PEARL, BOOK, LEATHER, NAUTILUS_SHELL, PRISMARINE_CRYSTALS,
                GLOW_INK_SAC, NETHER_BRICKS, POPPED_CHORUS_FRUIT, BONE_MEAL, ENDER_EYE, PAPER, RABBIT_HIDE, HEART_OF_THE_SEA, PRISMARINE_SHARD,
                RED_DYE, ORANGE_DYE, YELLOW_DYE, LIME_DYE, GREEN_DYE, CYAN_DYE, LIGHT_BLUE_DYE, BLUE_DYE, NETHER_WART,
                PURPLE_DYE, MAGENTA_DYE, PINK_DYE, BROWN_DYE, WHITE_DYE, LIGHT_GRAY_DYE, GRAY_DYE, BLACK_DYE, GLASS_BOTTLE,
                FLOWER_BANNER_PATTERN, CREEPER_BANNER_PATTERN, SKULL_BANNER_PATTERN, MOJANG_BANNER_PATTERN, GLOBE_BANNER_PATTERN, PIGLIN_BANNER_PATTERN,
                REDSTONE, SUGAR, BLAZE_POWDER, MAGMA_CREAM, SPIDER_EYE, RABBIT_FOOT, GOLDEN_CARROT, PHANTOM_MEMBRANE, TURTLE_HELMET,
                GLOWSTONE_DUST, GUNPOWDER, DRAGON_BREATH, PUFFERFISH, FERMENTED_SPIDER_EYE, GHAST_TEAR, GLISTERING_MELON_SLICE, EXPERIENCE_BOTTLE
            };
            EnumSet<EnchantmentTarget> set = EnumSet.allOf(EnchantmentTarget.class);
            for (Item item: items) {
                if (item==REDSTONE) displayContext.lookup().getOptionalWrapper(RegistryKeys.ENCHANTMENT).ifPresent(registryWrapper -> {
                    registryWrapper.streamEntries().map(RegistryEntry::value).filter(enchantment -> set.contains(enchantment.target)).map(enchantment -> EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(enchantment, enchantment.getMaxLevel()))).forEach(stack -> entries.add(stack, StackVisibility.PARENT_TAB_ONLY));
                    registryWrapper.streamEntries().map(RegistryEntry::value).filter(enchantment -> set.contains(enchantment.target)).flatMap(enchantment -> IntStream.rangeClosed(enchantment.getMinLevel(), enchantment.getMaxLevel()).mapToObj(level -> EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(enchantment, level)))).forEach(stack -> entries.add(stack, StackVisibility.SEARCH_TAB_ONLY));
                });
                entries.add(item);
            }
        };
    }
    @Shadow private static void addPotions(Entries entries, RegistryWrapper<Potion> registryWrapper, Item item, StackVisibility visibility) {}
    @Shadow private static void addPaintings(Entries entries, RegistryWrapper.Impl<PaintingVariant> registryWrapper, Predicate<RegistryEntry<PaintingVariant>> predicate, StackVisibility visibility) {}

}
