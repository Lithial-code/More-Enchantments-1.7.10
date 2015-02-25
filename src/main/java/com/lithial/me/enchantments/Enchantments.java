package com.lithial.me.enchantments;

import java.io.File;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Enchantments {
    static Configuration config;
    public static Enchantment enchantment;


    public static Enchantment highjump, swiftness, cloud, regen, horticulture,
            slowArrow, poisonArrow, witherArrow, slowAspect, poisonAspect,
            witherAspect, slowAoe, poisonAoe, witherAoe, verticallity, blast,
            leech, wisdom, magnet, vigor, nightvision, steadfast, quickdraw, icestep,
            photo, heat, venom, homing,sharpShooter,parry,multipurpose, greenwood,disarm,demonhunter
            ,remoteDetonation, purging;

    public static boolean allowLeech = true, allowWisdom = true,
            allowSwiftness = true, allowPoisonAspect = true,
            allowHighJump = true, allowAntiVenom = true,
            allowRegeneration = true, allowLethargy = true, allowVigour = true,
            allowIceStep = true, allowVertically = true, allowHorticulture = true,
            allowNightvision = true, allowCloud = true, allowQuickDraw = true,
            allowHoming = true, allowPoisonArrow = true,
            allowSlowArrow = true, allowMagnet = true, allowSteadFast = true,
            allowPhoto = true, allowBlast = true, allowPoisonAoe = true,
            allowSlowAoe = true, allowHeat = true, allowWitherArrow = true,
            allowWitherAspect = true, allowWitherAOE = true, allowPenetrate = true,
            allowSharpShooter = true, allowMulti = true, hotbarOnly = true,
            allowGreenWood = true,allowDisarm = true, allowDemonHunter,
            allowRD,allowPurging;

    public static int photoDelay, poisonArrowTimer, slowArrowTimer, witherArrowTimer, poisonAspectTimer, slowAspectTimer,
            witherAspectTimer, poisonAOETimer, slowAOETimer, witherAOETimer, greenwoodDelay;
    public static int flowerUpdate,cactusUpdate,saplingUpdate,melonUpdate,modUpdate,tallGrassUpdate;
    public static double highjumpbonus, verticallitybonus, demonBonus, purgingBonus;

    static Property get(String catagory, String name, int id) {
        return config.get(catagory, name, id);
    }
    static int getInt(String catagory, String name, int id) {
        return config.get(catagory, name, id).getInt();
    }

    static boolean getb(String catagory, String name, boolean allow) {
        return config.get(catagory, name, allow).getBoolean(true);
    }


    public static String ENCHANT = "Enchantments";
    public static String WEIGHT = "Weight";
    public static String ALLOW = "Allow Enchantments";
    public static String MAX = "Maximum Level";
    public static String UPDATE = "Update Timer";
    public static String POISON = "Poison Effect Timers";
    public static String BONUS = "Enchantment Modifiers";

    public static void initialize(File file) {

        config = new Configuration(file);
        config.setCategoryComment("General","If you think it should work and it doesn't, let me know");
        Property Homing = get(ENCHANT, "Homing", 300);
        Property Leech = get(ENCHANT, "Leech", 301);
        Property Wisdom = get(ENCHANT, "Wisdom", 302);
        Property Swiftness = get(ENCHANT, "Swiftness", 303);
        Property PoisonAspect = get(ENCHANT, "PoisonAspect", 304);
        Property AntiVenom = get(ENCHANT, "AntiVenom", 305);
        Property Regeneration = get(ENCHANT, "Regeneration", 306);
        Property HighJump = get(ENCHANT, "HighJump", 307);
        Property Lethargy = get(ENCHANT, "Lethargy", 308);
        Property QuickDraw = get(ENCHANT, "QuickDraw", 309);
        Property Vigour = get(ENCHANT, "Vigour", 310);
        //Property Penetrate = get(ENCHANT, "Penetrate", 311);
        //Property Durability = get(ENCHANT, "Durability", 312);
        Property IceStep = get(ENCHANT, "IceStep", 313);
        Property Vertically = get(ENCHANT, "Vertically", 314);
        Property Horticulture = get(ENCHANT, "Horticulture", 315);
        Property Cloud = get(ENCHANT, "Cloud", 316);
        Property Nightvision = get(ENCHANT, "NightVision", 317);
        Property PoisonArrow = get(ENCHANT, "PoisonArrow", 318);
        Property SlowArrow = get(ENCHANT, "SlowArrow", 319);
        Property Magnet = get(ENCHANT, "Magnet", 320);
        // Property Parry = get(ENCHANT, "AEnchantments_Parry", 321);
        Property SteadFast = get(ENCHANT, "SteadFast", 322);
        Property PhotoSynthesis = get(ENCHANT, "Photo", 323);
        Property Blast = get(ENCHANT, "Blast", 324);
        Property SlowAoe = get(ENCHANT, "BlastSlow", 325);
        Property PoisonAoe = get(ENCHANT, "BlastPoison", 326);
        Property Heat = get(ENCHANT, "Heat", 327);
        Property WitherArrow = get(ENCHANT, "WitherArrow", 328);
        Property WitherAspect = get(ENCHANT, "WitherAspect", 329);
        Property WitherArrowAoe = get(ENCHANT, "WitherArrowAoe", 330);
        Property SharpShooter = get(ENCHANT, "SharpShooter", 331);
        Property MultiPurpose = get(ENCHANT, "MultiPurpose", 332);
        Property GreenWood = get(ENCHANT, "GreedWood", 333);
        Property Disarm = get(ENCHANT, "Disarm", 334);
        Property DemonHunter = get(ENCHANT, "DemonHunter", 335);
        Property Purging = get(ENCHANT, "Purging", 336);
        Property RemoteDetonation = get(ENCHANT, "RemoteDetonation", 337);


        Property HomingWeight = get(WEIGHT, "Homing", 5);
        Property SharpShooterWeight = get(WEIGHT, "SharpShooter", 5);
        Property LeechWeight = get(WEIGHT, "Leech", 10);
        Property WisdomWeight = get(WEIGHT, "Wisdom", 10);
        Property SwiftnessWeight = get(WEIGHT, "Swiftness", 20);
        Property PoisonAspectWeight = get(WEIGHT, "PoisonAspect", 5);
        Property AntiVenomWeight = get(WEIGHT, "AntiVenom", 5);
        Property RegenerationWeight = get(WEIGHT, "Regeneration", 2);
        Property HighJumpWeight = get(WEIGHT, "HighJump", 20);
        Property LethargyWeight = get(WEIGHT, "Lethargy", 5);
        Property QuickDrawWeight = get(WEIGHT, "QuickDraw", 5);
        Property VigourWeight = get(WEIGHT, "Vigour", 10);
        // Property PenetrateWeight = get(WEIGHT, "Penetrate", 1);
        //Property DurabilityWeight = get(WEIGHT, "Durability", 5);
        Property IceStepWeight = get(WEIGHT, "iceStep", 5);
        Property VerticallyWeight = get(WEIGHT, "Vertically", 5);
        Property HorticultureWeight = get(WEIGHT, "Horticulture", 1);
        Property CloudWeight = get(WEIGHT, "Cloud", 8);
        Property NightvisionWeight = get(WEIGHT, "NightVision", 3);
        Property PoisonArrowWeight = get(WEIGHT, "PoisonArrow", 5);
        Property SlowArrowWeight = get(WEIGHT, "SlowArrow", 5);
        Property MagnetWeight = get(WEIGHT, "Magnet", 5);
        // Property ParryWeight = get(WEIGHT, "Parry", 5);
        Property SteadFastWeight = get(WEIGHT, "SteadFast", 20);
        Property PhotoSynthesisWeight = get(WEIGHT, "Photo", 5);
        Property BlastWeight = get(WEIGHT, "Blast", 10);
        Property PoisonAoeWeight = get(WEIGHT, "PoisonAoe", 5);
        Property SlowAoeWeight = get(WEIGHT, "SlowAoe", 5);
        Property HeatWeight = get(WEIGHT, "Heat", 15);
        Property WitherArrowWeight = get(WEIGHT, "WitherArrow", 5);
        Property WitherAspectWeight = get(WEIGHT, "Heat_WitherAspect", 5);
        Property WitherAoeWeight = get(WEIGHT, "WitherArrowAoe", 5);
        Property MultiPurposeWeight = get(WEIGHT, "MultiPurpose", 5);
        Property GreenWoodWeight = get(WEIGHT, "GreenWood", 1);
        Property DisarmWeight = get(WEIGHT, "Disarm", 5);
        Property DemonHunterWeight = get(WEIGHT, "DemonHunter", 5);
        Property RemoteDetonationWeight = get(WEIGHT, "RemoteDetonation", 5);
        Property PurgingWeight = get(WEIGHT, "Purging", 5);

        allowHoming = getb(ALLOW, "Homing", true);
        allowSharpShooter = getb(ALLOW, "SharpShoooter",true);
        allowLeech = getb(ALLOW, "Leech", true);
        allowWisdom = getb(ALLOW, "Wisdom", true);
        allowSwiftness = getb(ALLOW, "Swiftness", true);
        allowPoisonAspect = getb(ALLOW, "Poison Aspect", true);
        allowHighJump = getb(ALLOW, "High Jump", true);
        allowAntiVenom = getb(ALLOW, "Anti Venom", true);
        allowRegeneration = getb(ALLOW, "Regeneration", true);
        allowLethargy = getb(ALLOW, "Lethargy", true);
        allowQuickDraw = getb(ALLOW, "QuickDraw", true);
        allowVigour = getb(ALLOW, "Vigour", true);
        //allowPenetrate = getb(ALLOW, "Penetrate", true);
        //allowDurability = getb(ALLOW, "Durability", true);
        allowIceStep = getb(ALLOW, "Ice Step", true);
        allowVertically = getb(ALLOW, "Vertically", true);
        allowHorticulture = getb(ALLOW, "Horticulture", true);
        allowNightvision = getb(ALLOW, "Night Vision", true);
        allowCloud = getb(ALLOW, "Cloud", true);
        allowPoisonArrow = getb(ALLOW, "Poison Arrow", true);
        allowSlowArrow = getb(ALLOW, "Slow Arrow", true);
        allowMagnet = getb(ALLOW, "Magnet", true);
        allowSteadFast = getb(ALLOW, "Stead Fast", true);
        allowPhoto = getb(ALLOW, "Photosynthesis", true);
        allowBlast = getb(ALLOW, "Blast", true);
        allowPoisonAoe = getb(ALLOW, "BlastPoison", true);
        allowSlowAoe = getb(ALLOW, "BlastSlow", true);
        allowHeat = getb(ALLOW, "SuperHeat", true);
        allowWitherArrow = getb(ALLOW, "WitherArrow", true);
        allowWitherAspect = getb(ALLOW, "WitherAspect ", true);
        allowWitherAOE = getb(ALLOW, "WitherAoe", true);
        allowMulti = getb(ALLOW,"MultiPurpose", true);
        allowGreenWood = getb(ALLOW,"GreenWood", true);
        allowDisarm = getb(ALLOW,"Disarm", true);
        allowDemonHunter = getb(ALLOW,"DemonHunter", true);
        allowRD = getb(ALLOW,"RemoteDetonation", true);
        allowPurging = getb(ALLOW,"Purging", true);

        photoDelay = config.get(BONUS, "PhotoDelay in seconds ", 15).getInt();
        greenwoodDelay = config.get(BONUS, "GreenWood Delay in seconds ", 15).getInt();
        highjumpbonus = config.get(BONUS, "HighJump Modifier default = 0.4",0.4,"dont go to high you'll break it").getDouble();
        verticallitybonus = config.get(BONUS, "Verticallity Modifier default = 1.2", 1.5,"dont go to high you'll break it" ).getDouble();
        demonBonus = config.get(BONUS, "demon hunter damage multiplyer default is 1.5",1.5).getDouble();
        purgingBonus = config.get(BONUS, "Purging damage multiplyer default is 1.5",1.5).getDouble();

        // blastbonus = config.get(BONUS, "Blast arrow explosion size", 4F).getInt();

        config.setCategoryComment(UPDATE,"The rate at which plants grow,s "+"dont go to high or you'll break it" );
        flowerUpdate = getInt(UPDATE,"Flower & Netherwart & SugarCane & Crops Timer",15);
        cactusUpdate = getInt(UPDATE,"Cactus & Cocoa Timer",5);
        saplingUpdate = getInt(UPDATE,"Sapling Timer",2);
        melonUpdate = getInt(UPDATE,"Melon & Pumpkin Timer",2);
        modUpdate = getInt(UPDATE,"Mod Plant Timer",1);
        tallGrassUpdate = getInt(UPDATE,"TallGrass Timer",1);

        config.setCategoryComment(POISON,"No half seconds, your break it don't come crying to me" );
        poisonArrowTimer = getInt(POISON, "Poison Arrow Timer in seconds", 3);
        slowArrowTimer = getInt(POISON, "Poison Arrow Timer in seconds", 3);
        witherArrowTimer = getInt(POISON, "Poison Arrow Timer in seconds", 3);
        poisonAspectTimer = getInt(POISON, "Poison Arrow Timer in seconds", 3);
        slowAspectTimer = getInt(POISON, "Poison Arrow Timer in seconds", 3);
        witherAspectTimer = getInt(POISON, "Poison Arrow Timer in seconds", 3);
        poisonAOETimer = getInt(POISON, "Poison Arrow Timer in seconds", 3);
        slowAOETimer = getInt(POISON, "Poison Arrow Timer in seconds", 3);
        witherAOETimer = getInt(POISON, "Poison Arrow Timer in seconds", 3);

        config.setCategoryComment(MAX,"Some of these have no effect, use your common sense");
        Property HomingMax = get(MAX, "Homing dont change", 1);
        Property LeechMax = get(MAX, "Leech", 1);
        Property WisdomMax = get(MAX, "Wisdom", 4);
        Property SwiftnessMax = get(MAX, "Swiftness", 4);
        Property PoisonAspectMax = get(MAX, "PoisonAspect", 2);
        Property AntiVenomMax = get(MAX, "AntiVenom", 3);
        Property RegenerationMax = get(MAX, "Regeneration dont change", 1);
        Property HighJumpMax = get(MAX, "HighJump", 3);
        Property LethargyMax = get(MAX, "Lethargy", 2);
        Property QuickDrawMax = get(MAX, "QuickDraw", 4);
        Property VigourMax = get(MAX, "Vigour", 3);
        //   Property PenetrateMax = get(MAX, "Penetrate", 311);
        //Property DurabilityMax = get(MAX, "Durability", 312);
        Property IceStepMax = get(MAX, "IceStep dont change", 1);
        Property VerticallyMax = get(MAX, "Vertically", 2);
        Property HorticultureMax = get(MAX, "Horticulture dont change", 1);
        Property CloudMax = get(MAX, "Cloud dont change", 1);
        Property NightvisionMax = get(MAX, "NightVision dont change", 1);
        Property PoisonArrowMax = get(MAX, "PoisonArrow", 3);
        Property SlowArrowMax = get(MAX, "SlowArrow", 3);
        Property MagnetMax = get(MAX, "Magnet", 4);
        // Property ParryMax = get(MAX, "AMAXments_Parry", 321);
        Property SteadFastMax = get(MAX, "SteadFast", 1);
        Property PhotoSynthesisMax = get(MAX, "Photo", 1);
        Property BlastMax = get(MAX, "Blast", 1);
        Property SlowAoeMax = get(MAX, "BlastSlow", 2);
        Property PoisonAoeMax = get(MAX, "BlastPoison", 2);
        Property HeatMax = get(MAX, "Heat", 1);
        Property WitherArrowMax = get(MAX, "WitherArrow", 2);
        Property WitherAspectMax = get(MAX, "WitherAspect", 2);
        Property WitherArrowAoeMax = get(MAX, "WitherArrowAoe", 2);
        Property SharpShooterMax = get(MAX, "SharpShooter dont change", 1);
        Property GreenWoodMax = get(MAX, "GreenWood dont change", 1);
        Property DisarmMax = get(MAX, "Disarm", 3);
        Property DemonHunterMax = get(MAX, "DemonHunter", 3);
        Property PurgingMax = get(MAX, "Purging", 3);

	/*	if(allowPenetrate)
			penetrate = new EnchantmentCore(Penetrate.getInt(), Penetrate.getInt(), EnumEnchantmentType.weapon, "penetrate", 1, 1, ban(Compat.penetrate1),ban(Compat.penetrate2),ban(Compat.penetrate3),ban(Compat.penetrate4));
		*/

        if (allowHighJump)
            highjump = new EnchantmentCore(HighJump.getInt(), HighJumpWeight.getInt(), EnumEnchantmentType.armor_feet, "highjump", 1, HighJumpMax.getInt(), ban(Compat.highjump1), ban(Compat.highjump2), ban(Compat.highjump3), ban(Compat.highjump4));
        if (allowSwiftness)
            swiftness = new EnchantmentCore(Swiftness.getInt(), SwiftnessWeight.getInt(), EnumEnchantmentType.armor_feet, "swiftness", 1, SwiftnessMax.getInt(), ban(Compat.swiftness1), ban(Compat.swiftness2), ban(Compat.swiftness3), ban(Compat.swiftness4));
        if (allowCloud)
            cloud = new EnchantmentCore(Cloud.getInt(), CloudWeight.getInt(), EnumEnchantmentType.armor_legs, "cloud", 1, CloudMax.getInt(), ban(Compat.cloud1), ban(Compat.cloud2), ban(Compat.cloud3), ban(Compat.cloud4));
        if (allowRegeneration)
            regen = new EnchantmentCore(Regeneration.getInt(), RegenerationWeight.getInt(), EnumEnchantmentType.armor_torso, "regen", 1, RegenerationMax.getInt(), ban(Compat.regen1), ban(Compat.regen2), ban(Compat.regen3), ban(Compat.regen4));
        if (allowHorticulture)
            horticulture = new EnchantmentCore(Horticulture.getInt(), HorticultureWeight.getInt(), EnumEnchantmentType.armor_head, "horticulture", 1, HorticultureMax.getInt(), ban(Compat.horticulture1), ban(Compat.horticulture2), ban(Compat.horticulture3), ban(Compat.horticulture4));
        if (allowSlowArrow)
            slowArrow = new EnchantmentCore(SlowArrow.getInt(), SlowArrowWeight.getInt(), EnumEnchantmentType.bow, "slowarrow", 1, SlowArrowMax.getInt(), ban(Compat.slowArrow1), ban(Compat.slowArrow2), ban(Compat.slowArrow3), ban(Compat.slowArrow4));
        if (allowPoisonArrow)
            poisonArrow = new EnchantmentCore(PoisonArrow.getInt(), PoisonArrowWeight.getInt(), EnumEnchantmentType.bow, "poisonarrow", 1, PoisonArrowMax.getInt(), ban(Compat.poisonArrow1), ban(Compat.poisonArrow2), ban(Compat.poisonArrow3), ban(Compat.poisonArrow4));
        if (allowWitherArrow)
            witherArrow = new EnchantmentCore(WitherArrow.getInt(), WitherArrowWeight.getInt(), EnumEnchantmentType.bow, "witherarrow", 1, WitherArrowMax.getInt(), ban(Compat.witherArrow1), ban(Compat.witherArrow2), ban(Compat.witherArrow3), ban(Compat.witherArrow4));
        if (allowLethargy)
            slowAspect = new EnchantmentCore(Lethargy.getInt(), LethargyWeight.getInt(), EnumEnchantmentType.weapon, "slowaspect", 1, 2, ban(Compat.slowAspect1), ban(Compat.slowAspect2), ban(Compat.slowAspect3), ban(Compat.slowAspect4));
        if (allowPoisonAspect)
            poisonAspect = new EnchantmentCore(PoisonAspect.getInt(), PoisonAspectWeight.getInt(), EnumEnchantmentType.weapon, "poisonaspect", 1, PoisonAspectMax.getInt(), ban(Compat.poisonAspect1), ban(Compat.poisonAspect2), ban(Compat.poisonAspect3), ban(Compat.poisonAspect4));
        if (allowWitherAspect)
            witherAspect = new EnchantmentCore(WitherAspect.getInt(), WitherAspectWeight.getInt(), EnumEnchantmentType.weapon, "witheraspect", 1, WitherAspectMax.getInt(), ban(Compat.witherAspect1), ban(Compat.witherAspect2), ban(Compat.witherAspect3), ban(Compat.witherAspect4));
        if (allowSlowAoe)
            slowAoe = new EnchantmentCore(SlowAoe.getInt(), SlowAoeWeight.getInt(), EnumEnchantmentType.bow, "slowaoe", 1, SlowAoeMax.getInt(), ban(Compat.slowAoe1), ban(Compat.slowAoe2), ban(Compat.slowAoe3), ban(Compat.slowAoe4));
        if (allowPoisonAoe)
            poisonAoe = new EnchantmentCore(PoisonAoe.getInt(), PoisonAoeWeight.getInt(), EnumEnchantmentType.bow, "poisonaoe", 1, PoisonAoeMax.getInt(), ban(Compat.poisonAoe1), ban(Compat.poisonAoe2), ban(Compat.poisonAoe3), ban(Compat.poisonAoe4));
        if (allowWitherAOE)
            witherAoe = new EnchantmentCore(WitherArrowAoe.getInt(), WitherAoeWeight.getInt(), EnumEnchantmentType.bow, "witheraoe", 1, WitherArrowAoeMax.getInt(), ban(Compat.witherAoe1), ban(Compat.witherAoe2), ban(Compat.witherAoe3), ban(Compat.witherAoe4));
        if (allowVertically)
            verticallity = new EnchantmentCore(Vertically.getInt(), VerticallyWeight.getInt(), EnumEnchantmentType.weapon, "verticallity", 1, VerticallyMax.getInt(), ban(Compat.verticallity1), ban(Compat.verticallity2), ban(Compat.verticallity3), ban(Compat.verticallity4));
        if (allowBlast)
            blast = new EnchantmentCore(Blast.getInt(), BlastWeight.getInt(), EnumEnchantmentType.bow, "blast", 1, BlastMax.getInt(), ban(Compat.blast1), ban(Compat.blast2), ban(Compat.blast3), ban(Compat.blast4));
        if (allowLeech)
            leech = new EnchantmentCore(Leech.getInt(), LeechWeight.getInt(), EnumEnchantmentType.weapon, "leech", 1, LeechMax.getInt(), ban(Compat.leech1), ban(Compat.leech2), ban(Compat.leech3), ban(Compat.leech4));
        if (allowWisdom)
            wisdom = new EnchantmentCore(Wisdom.getInt(), WisdomWeight.getInt(), EnumEnchantmentType.weapon, "wisdom", 1, WisdomMax.getInt(), ban(Compat.wisdom1), ban(Compat.wisdom2), ban(Compat.wisdom3), ban(Compat.wisdom4));
        if (allowMagnet)
            magnet = new EnchantmentCore(Magnet.getInt(), MagnetWeight.getInt(), EnumEnchantmentType.armor_torso, "magnet", 1, MagnetMax.getInt(), ban(Compat.magnet1), ban(Compat.magnet2), ban(Compat.magnet3), ban(Compat.magnet4));
        if (allowVigour)
            vigor = new EnchantmentCore(Vigour.getInt(), VigourWeight.getInt(), EnumEnchantmentType.digger, "vigor", 1, VigourMax.getInt(), ban(Compat.vigor1), ban(Compat.vigor2), ban(Compat.vigor3), ban(Compat.vigor4));
        if (allowNightvision)
            nightvision = new EnchantmentCore(Nightvision.getInt(), NightvisionWeight.getInt(), EnumEnchantmentType.armor_head, "nightvision", 1, NightvisionMax.getInt(), ban(Compat.nightvision1), ban(Compat.nightvision2), ban(Compat.nightvision3), ban(Compat.nightvision4));
        if (allowSteadFast)
            steadfast = new EnchantmentCore(SteadFast.getInt(), SteadFastWeight.getInt(), EnumEnchantmentType.armor_legs, "steadfast", 1, SteadFastMax.getInt(), ban(Compat.steadfast1), ban(Compat.steadfast2), ban(Compat.steadfast3), ban(Compat.steadfast4));
        if (allowQuickDraw)
            quickdraw = new EnchantmentCore(QuickDraw.getInt(), QuickDrawWeight.getInt(), EnumEnchantmentType.bow, "quickdraw", 1, QuickDrawMax.getInt(), ban(Compat.quickdraw1), ban(Compat.quickdraw2), ban(Compat.quickdraw3), ban(Compat.quickdraw4));
        if (allowIceStep)
            icestep = new EnchantmentCore(IceStep.getInt(), IceStepWeight.getInt(), EnumEnchantmentType.armor_feet, "icestep", 1, IceStepMax.getInt(), ban(Compat.icestep1), ban(Compat.icestep2), ban(Compat.icestep3), ban(Compat.icestep4));
        if (allowPhoto)
            photo = new EnchantmentCore(PhotoSynthesis.getInt(), PhotoSynthesisWeight.getInt(), EnumEnchantmentType.armor_head, "photosynthesis", 1, PhotoSynthesisMax.getInt(), ban(Compat.photo1), ban(Compat.photo2), ban(Compat.photo3), ban(Compat.photo4));
        if (allowHeat)
            heat = new EnchantmentCore(Heat.getInt(), HeatWeight.getInt(), EnumEnchantmentType.digger, "heat", 1, HeatMax.getInt(), ban(Compat.heat1), ban(Compat.heat2), ban(Compat.heat3), ban(Compat.heat4));
        if (allowAntiVenom)
            venom = new EnchantmentCore(AntiVenom.getInt(), AntiVenomWeight.getInt(), EnumEnchantmentType.armor, "antivenom", 1, AntiVenomMax.getInt(), ban(Compat.venom1), ban(Compat.venom2), ban(Compat.venom3), ban(Compat.venom4));
        // phantom = new EnchantmentCore(70,10,EnumEnchantmentType.armor_feet,"phantom",1,1,null,null,null,null);
		/*	if(allowDurabillity)
			durabillity = new EnchantmentCore(Durability.getInt(), DurabilityWeight.getInt(), EnumEnchantmentType.armor, "durabillity", 1, 4)
		 */
        if (allowHoming)
            homing = new EnchantmentCore(Homing.getInt(), Homing.getInt(), EnumEnchantmentType.bow, "homing", 1, HomingMax.getInt(), ban(Compat.homing1), ban(Compat.homing2), ban(Compat.homing3), ban(Compat.homing4));
        if (allowSharpShooter)
            sharpShooter = new EnchantmentCore(SharpShooter.getInt(), SharpShooterWeight.getInt(), EnumEnchantmentType.bow, "sharpshooter", 1, SharpShooterMax.getInt(), ban(Compat.sharpshooter1), ban(Compat.sharpshooter2), ban(Compat.sharpshooter3), ban(Compat.sharpshooter4));
        //parry = new EnchantmentCore(Parry.getInt(),ParryWeight.getInt(),EnumEnchantmentType.weapon, "parry",1,1,null,null,null,null);
        if(allowMulti)
            multipurpose = new EnchantmentCore(MultiPurpose.getInt(), MultiPurposeWeight.getInt(), EnumEnchantmentType.digger, "edged",1,3,null,null,null,null);
        if(allowGreenWood)
            greenwood = new EnchantmentCore(GreenWood.getInt(), GreenWoodWeight.getInt(), EnumEnchantmentType.all, "greenwood",1,1,null,null,null,null);
        if(allowDisarm)
        disarm = new EnchantmentCore(Disarm.getInt(), DisarmWeight.getInt(), EnumEnchantmentType.weapon, "disarm",1,DisarmMax.getInt(),null,null,null,null);
        if(allowDemonHunter)
        demonhunter = new EnchantmentCore(DemonHunter.getInt(),DemonHunterWeight.getInt(),EnumEnchantmentType.bow,"demonhunter",1,DemonHunterMax.getInt(),null,null,null,null);
        if(allowRD)
            remoteDetonation = new EnchantmentCore(RemoteDetonation.getInt(),RemoteDetonationWeight.getInt(),EnumEnchantmentType.bow,"remotedetonation",1,1,null,null,null,null);
        if(allowPurging)
            purging = new EnchantmentCore(Purging.getInt(),PurgingWeight.getInt(),EnumEnchantmentType.weapon,"purging",1,Purging.getInt(),null,null,null,null);
        config.load();
      save();
    }


    public static void save() {
        config.save();
    }

    public static Enchantment ban(int i) {
        return enchantment.enchantmentsList[i];
    }

}

