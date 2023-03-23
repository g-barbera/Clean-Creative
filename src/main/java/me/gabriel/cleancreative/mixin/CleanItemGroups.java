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
 *
 */
@Mixin(ItemGroups.class)
public abstract class CleanItemGroups {
    @Shadow private static void addPotions(
        ItemGroup.Entries entries, RegistryWrapper<Potion> registryWrapper, Item item, ItemGroup.StackVisibility visibility) {}

    @Shadow private static void addPaintings(
        Entries entries, RegistryWrapper.Impl<PaintingVariant> registryWrapper, Predicate<RegistryEntry<PaintingVariant>> predicate, StackVisibility visibility) {}

    @ModifyArg(method="<clinit>", at=@At(value="INVOKE", ordinal=0,
        target="Lnet/minecraft/item/ItemGroup$Builder;entries(Lnet/minecraft/item/ItemGroup$EntryCollector;)"
            +"Lnet/minecraft/item/ItemGroup$Builder;"))
    private static EntryCollector BUILDING_BLOCKS(EntryCollector entryCollector) {
        return entryCollector; // TODO: Finish BUILDING_BLOCKS ItemGroup
    }
    @ModifyArg(method="<clinit>", at=@At(value="INVOKE", ordinal=1,
         target="Lnet/minecraft/item/ItemGroup$Builder;entries(Lnet/minecraft/item/ItemGroup$EntryCollector;)"
                 +"Lnet/minecraft/item/ItemGroup$Builder;"))
    private static EntryCollector COLORED_BLOCKS(EntryCollector entryCollector) {
        return (dc, entries) -> {
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
    @ModifyArg(method="<clinit>", at=@At(value="INVOKE", ordinal=2,
        target="Lnet/minecraft/item/ItemGroup$Builder;entries(Lnet/minecraft/item/ItemGroup$EntryCollector;)"
            +"Lnet/minecraft/item/ItemGroup$Builder;"))
    private static EntryCollector NATURAL(EntryCollector entryCollector) {
        return entryCollector; // TODO: Finish NATURAL ItemGroup
    }
    @ModifyArg(method="<clinit>", at=@At(value="INVOKE", ordinal=3,
        target="Lnet/minecraft/item/ItemGroup$Builder;entries(Lnet/minecraft/item/ItemGroup$EntryCollector;)"
            +"Lnet/minecraft/item/ItemGroup$Builder;"))
    private static EntryCollector FUNCTIONAL(EntryCollector entryCollector) {
        return (displayContext, entries) -> {
            Item[] items = {
                CRAFTING_TABLE, FURNACE, CHEST, ENDER_CHEST, BREWING_STAND, ANVIL, CHIPPED_ANVIL, DAMAGED_ANVIL, ENCHANTING_TABLE,
                STONECUTTER, SMOKER, BARREL, LOOM, CAULDRON, CARTOGRAPHY_TABLE, BEACON, BEEHIVE, BEE_NEST,
                LECTERN, BLAST_FURNACE, SMITHING_TABLE, FLETCHING_TABLE, GRINDSTONE, COMPOSTER, BELL, GLOW_LICHEN, LODESTONE,
                CAMPFIRE, LANTERN, TORCH, REDSTONE_TORCH, GLOWSTONE, SHROOMLIGHT, MAGMA_BLOCK, CRYING_OBSIDIAN, RESPAWN_ANCHOR,
                SOUL_CAMPFIRE, SOUL_LANTERN, SOUL_TORCH, END_ROD, OCHRE_FROGLIGHT, VERDANT_FROGLIGHT, PEARLESCENT_FROGLIGHT, SEA_LANTERN, REDSTONE_LAMP,
                BOOKSHELF, CHAIN, LADDER, SCAFFOLDING, LIGHTNING_ROD, FLOWER_POT, ARMOR_STAND, ITEM_FRAME, GLOW_ITEM_FRAME,
                OAK_SIGN, SPRUCE_SIGN, BIRCH_SIGN, JUNGLE_SIGN, ACACIA_SIGN, DARK_OAK_SIGN, MANGROVE_SIGN, CRIMSON_SIGN, WARPED_SIGN,
                PAINTING, /* place rest of paintings here */
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
                if (item==RED_SHULKER_BOX) displayContext.lookup().getOptionalWrapper(RegistryKeys.PAINTING_VARIANT)
                    .ifPresent(registryWrapper -> addPaintings(
                        entries, registryWrapper, entry -> entry.isIn(PaintingVariantTags.PLACEABLE), ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS
                    ));
                if (item==PURPLE_BANNER) entries.add(Raid.getOminousBanner());
                entries.add(item);
            }
        };
    }
    @ModifyArg(method="<clinit>", at=@At(value="INVOKE", ordinal=4,
        target="Lnet/minecraft/item/ItemGroup$Builder;entries(Lnet/minecraft/item/ItemGroup$EntryCollector;)"
            +"Lnet/minecraft/item/ItemGroup$Builder;"))
    private static EntryCollector REDSTONE(EntryCollector entryCollector) {
        return entryCollector; // TODO: Finish FUNCTIONAL ItemGroup
    }
    @ModifyArg(method="<clinit>", at=@At(value="INVOKE", ordinal=6,
            target="Lnet/minecraft/item/ItemGroup$Builder;entries(Lnet/minecraft/item/ItemGroup$EntryCollector;)"
                    +"Lnet/minecraft/item/ItemGroup$Builder;"))
    private static EntryCollector TOOLS(EntryCollector entryCollector) {
        return (displayContext, entries) -> {
            Item[] items = {
                WOODEN_SHOVEL, WOODEN_PICKAXE, WOODEN_AXE, WOODEN_HOE, BUCKET, COD_BUCKET, SALMON_BUCKET, FIREWORK_ROCKET, COMPASS,
                STONE_SHOVEL, STONE_PICKAXE, STONE_AXE, STONE_HOE, WATER_BUCKET, TROPICAL_FISH_BUCKET, TADPOLE_BUCKET, FIREWORK_ROCKET, RECOVERY_COMPASS,
                IRON_SHOVEL, IRON_PICKAXE, IRON_AXE, IRON_HOE, LAVA_BUCKET, PUFFERFISH_BUCKET, AXOLOTL_BUCKET, FIREWORK_ROCKET, CLOCK,
                GOLDEN_SHOVEL, GOLDEN_PICKAXE, GOLDEN_AXE, GOLDEN_HOE, POWDER_SNOW_BUCKET, MILK_BUCKET, ENDER_PEARL, BONE_MEAL, SPYGLASS,
                DIAMOND_SHOVEL, DIAMOND_PICKAXE, DIAMOND_AXE, DIAMOND_HOE, MAP, WRITABLE_BOOK, ENDER_EYE, SADDLE, SHEARS,
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
    @ModifyArg(method="<clinit>", at=@At(value="INVOKE", ordinal=7,
        target="Lnet/minecraft/item/ItemGroup$Builder;entries(Lnet/minecraft/item/ItemGroup$EntryCollector;)"
            +"Lnet/minecraft/item/ItemGroup$Builder;"))
    private static EntryCollector COMBAT(EntryCollector entryCollector) {
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
    @ModifyArg(method="<clinit>", at=@At(value="INVOKE", ordinal=8,
            target="Lnet/minecraft/item/ItemGroup$Builder;entries(Lnet/minecraft/item/ItemGroup$EntryCollector;)"
                    +"Lnet/minecraft/item/ItemGroup$Builder;"))
    private static EntryCollector FOOD_AND_DRINK(EntryCollector entryCollector) {
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
    @ModifyArg(method="<clinit>", at=@At(value="INVOKE", ordinal=9,
        target="Lnet/minecraft/item/ItemGroup$Builder;entries(Lnet/minecraft/item/ItemGroup$EntryCollector;)"
            +"Lnet/minecraft/item/ItemGroup$Builder;"))
    private static EntryCollector INGREDIENTS(EntryCollector entryCollector) {
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
}
