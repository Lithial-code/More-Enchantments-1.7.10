package com.lithial.me.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Lithial on 8/02/2015.
 */
public class Compat {

public static Configuration config;
     public static int penetrate1,penetrate2,penetrate3,penetrate4,
      poisonArrow1, poisonArrow2, poisonArrow3, poisonArrow4,
      leech1, leech2, leech3, leech4,
      wisdom1, wisdom2, wisdom3, wisdom4,
      swiftness1, swiftness2, swiftness3, swiftness4,
      poisonaspect1, poisonaspect2, poisonaspect3, poisonaspect4,
      cloud1, cloud2, cloud3, cloud4,
      regen1, regen2, regen3, regen4,
      horticulture1, horticulture2, horticulture3, horticulture4,
      slowArrow1, slowArrow2, slowArrow3, slowArrow4,
      witherArrow1, witherArrow2, witherArrow3, witherArrow4,
      slowAspect1, slowAspect2, slowAspect3, slowAspect4,
      poisonAspect1, poisonAspect2, poisonAspect3, poisonAspect4,
      witherAspect1, witherAspect2, witherAspect3, witherAspect4,
      slowAoe1, slowAoe2, slowAoe3, slowAoe4,
      poisonAoe1, poisonAoe2, poisonAoe3, poisonAoe4,
      witherAoe1, witherAoe2, witherAoe3, witherAoe4,
      verticallity1, verticallity2, verticallity3, verticallity4,
      blast1, blast2, blast3, blast4,
      magnet1, magnet2, magnet3, magnet4,
      nightvision1, nightvision2, nightvision3, nightvision4,
      steadfast1, steadfast2, steadfast3, steadfast4,
      quickdraw1, quickdraw2, quickdraw3, quickdraw4,
      icestep1, icestep2, icestep3, icestep4,
      photo1, photo2, photo3, photo4,
      heat1, heat2, heat3, heat4,
      venom1, venom2, venom3, venom4,
      homing1, homing2, homing3, homing4,
      highjump1, highjump2, highjump3, highjump4,
      vigor1, vigor2, vigor3, vigor4,
        sharpshooter1, sharpshooter2,sharpshooter3,sharpshooter4;
    public static Enchantment enchantment;

    static String FB = "First Ban";
    static String SB = "Second Ban";
    static String TB = "Third Ban";
    static String BB = "Fourth Ban";

    public static void initialize(File file) {
        config = new Configuration(file);
        homing1 = config.get("Homing  Compatibility", "First Ban - Default QuickDraw", Enchantments.quickdraw.effectId).getInt();
        homing2 = config.get("Homing  Compatibility", "Second Ban - Default Power", enchantment.power.effectId).getInt();
        homing3 = config.get("Homing  Compatibility", "Third Ban - Default Null", 500).getInt();
        homing4 = config.get("Homing  Compatibility", "Fourth Ban - Default Null", 500).getInt();

        sharpshooter1 = config.get("Homing  Compatibility", "First Ban - Default QuickDraw", Enchantments.quickdraw.effectId).getInt();
        sharpshooter2 = config.get("Homing  Compatibility", "Second Ban - Default Power", enchantment.power.effectId).getInt();
        sharpshooter3 = config.get("Homing  Compatibility", "Third Ban - Default Null", 500).getInt();
        sharpshooter4 = config.get("Homing  Compatibility", "Fourth Ban - Default Null", 500).getInt();

        swiftness1 = config.get("Swiftness  Compatibility", "First Ban - Default FeatherFalling", enchantment.featherFalling.effectId).getInt();
        swiftness2 = config.get("Swiftness  Compatibility", "Second Ban - Default null", 500).getInt();
        swiftness3= config.get("Swiftness  Compatibility", "Third Ban - Default Null", 500).getInt();
        swiftness4= config.get("Swiftness  Compatibility", "Fourth Ban - Default Null", 500).getInt();

        leech1= config.get("Leech  Compatibility", "First Ban - Default Sharpness", enchantment.sharpness.effectId).getInt();
        leech2 = config.get("Leech  Compatibility", "Second Ban - Default Wisdom", Enchantments.wisdom.effectId).getInt();
        leech3= config.get("Leech  Compatibility", "Third Ban - Default Null", 500).getInt();
        leech4= config.get("Leech  Compatibility", "Fourth Ban - Default Null", 500).getInt();

        wisdom1= config.get("Wisdom Compatibility", "First Ban - Default Looting", enchantment.looting.effectId).getInt();
        wisdom2 = config.get("Wisdom Compatibility", "Second Ban - Default Leech", Enchantments.leech.effectId).getInt();
        wisdom3= config.get("Wisdom Compatibility", "Third Ban - Default Null", 500).getInt();
        wisdom4= config.get("Wisdom Compatibility", "Fourth Ban - Default Null", 500).getInt();

        highjump1= config.get("High Jump Compatibility", "First Ban - Default null", 500).getInt();
        highjump2 = config.get("High Jump Compatibility", "Second Ban - Default null", 500).getInt();
        highjump3= config.get("High Jump Compatibility", "Third Ban - Default Null", 500).getInt();
        highjump4= config.get("High Jump Compatibility", "Fourth Ban - Default Null", 500).getInt();

        cloud1= config.get("Cloud Compatibility", "First Ban - Default SteadFast", Enchantments.steadfast.effectId).getInt();
        cloud2 = config.get("Cloud Compatibility", "Second Ban - Default null", 500).getInt();
        cloud3= config.get("Cloud Compatibility", "Third Ban - Default Null", 500).getInt();
        cloud4= config.get("Cloud Compatibility", "Fourth Ban - Default Null", 500).getInt();

        regen1= config.get("Regeneration Compatibility", "First Ban - Default null", 500).getInt();
        regen2 = config.get("Regeneration Compatibility", "Second Ban - Default null", 500).getInt();
        regen3= config.get("Regeneration Compatibility", "Third Ban - Default Null", 500).getInt();
        regen4= config.get("Regeneration Compatibility", "Fourth Ban - Default Null", 500).getInt();

        horticulture1= config.get("Horticulture Compatibility", "First Ban - Default Aqua Affinity", enchantment.aquaAffinity.effectId).getInt();
        horticulture2 = config.get("Horticulture Compatibility", "Second Ban - Default null", 500).getInt();
        horticulture3= config.get("Horticulture Compatibility", "Third Ban - Default Null", 500).getInt();
        horticulture4= config.get("Horticulture Compatibility", "Fourth Ban - Default Null", 500).getInt();

        slowArrow1= config.get("Slow Arrow Compatibility", "First Ban - Default PoisonArrow", Enchantments.poisonArrow.effectId).getInt();
        slowArrow2 = config.get("Slow Arrow Compatibility", "Second Ban - Default Flame", enchantment.flame.effectId).getInt();
        slowArrow3= config.get("Slow Arrow Compatibility", "Third Ban - Default WitherArrow", Enchantments.witherArrow.effectId).getInt();
        slowArrow4= config.get("Slow Arrow Compatibility", "Fourth Ban - Default Null", 500).getInt();

        poisonArrow1= config.get("Poison Arrow Compatibility", "First Ban - Default Flame", enchantment.flame.effectId).getInt();
        poisonArrow2 = config.get("Poison Arrow Compatibility", "Second Ban - Default WitherArrow", Enchantments.witherArrow.effectId).getInt();
        poisonArrow3= config.get("Poison Arrow Compatibility", "Third Ban - Default SlowArrow", Enchantments.slowArrow.effectId).getInt();
        poisonArrow4= config.get("Poison Arrow Compatibility", "Fourth Ban - Default Null", 500).getInt();

        witherArrow1= config.get("Wither Arrow Compatibility", "First Ban - Default Flame", enchantment.flame.effectId).getInt();
        witherArrow2 = config.get("Wither Arrow Compatibility", "Second Ban - Default SlowArrow", Enchantments.slowArrow.effectId).getInt();
        witherArrow3= config.get("Wither Arrow Compatibility", "Third Ban - Default PoisonArrow", Enchantments.poisonArrow.effectId).getInt();
        witherArrow4= config.get("Wither Arrow Compatibility", "Fourth Ban - Default Null", 500).getInt();

        slowAspect1= config.get("Slow Aspect Compatibility", "First Ban - Default FireAspect", enchantment.fireAspect.effectId).getInt();
        slowAspect2 = config.get("Slow Aspect Compatibility", "Second Ban - Default PoisonAspect", Enchantments.poisonAspect.effectId).getInt();
        slowAspect3= config.get("Slow Aspect Compatibility", "Third Ban - Default WitherAspect", Enchantments.witherAspect.effectId).getInt();
        slowAspect4= config.get("Slow Aspect Compatibility", "Fourth Ban - Default Null", 500).getInt();

        poisonAspect1= config.get("Poison Aspect Compatibility", "First Ban - Default FireAspect", enchantment.fireAspect.effectId).getInt();
        poisonAspect2 = config.get("Poison Aspect Compatibility", "Second Ban - Default Lethargy", Enchantments.slowAspect.effectId).getInt();
        poisonAspect3= config.get("Poison Aspect Compatibility", "Third Ban - Default WitherAspect", Enchantments.witherAspect.effectId).getInt();
        poisonAspect4= config.get("Poison Aspect Compatibility", "Fourth Ban - Default Null", 500).getInt();

        witherAspect1= config.get("Wither Aspect Compatibility", "First Ban - Default FireAspect", enchantment.fireAspect.effectId).getInt();
        witherAspect2 = config.get("Wither Aspect Compatibility", "Second Ban - Default PoisonAspect", Enchantments.poisonAspect.effectId).getInt();
        witherAspect3= config.get("Wither Aspect Compatibility", "Third Ban - Default Lethargy", Enchantments.slowAspect.effectId).getInt();
        witherAspect4= config.get("Wither Aspect Compatibility", "Fourth Ban - Default Null", 500).getInt();

        slowAoe1= config.get("Slow Aoe Compatibility", "First Ban - Default PoisonAoe", Enchantments.poisonAoe.effectId).getInt();
        slowAoe2 = config.get("Slow Aoe Compatibility", "Second Ban - Default WitherAoe", Enchantments.witherAoe.effectId).getInt();
        slowAoe3= config.get("Slow Aoe Compatibility", "Third Ban - Default Null", 500).getInt();
        slowAoe4= config.get("Slow Aoe Compatibility", "Fourth Ban - Default Null", 500).getInt();

        poisonAoe1= config.get("Poison Aoe Compatibility", "First Ban - Default WitherAoe", Enchantments.witherAoe.effectId).getInt();
        poisonAoe2 = config.get("Poison Aoe Compatibility", "Second Ban - Default SlowAoe", Enchantments.slowAoe.effectId).getInt();
        poisonAoe3= config.get("Poison Aoe Compatibility", "Third Ban - Default Null", 500).getInt();
        poisonAoe4= config.get("Poison Aoe Compatibility", "Fourth Ban - Default Null", 500).getInt();

        witherAoe1= config.get("Wither Aoe Compatibility", "First Ban - Default PoisonAoe", Enchantments.poisonAoe.effectId).getInt();
        witherAoe2 = config.get("Wither Aoe Compatibility", "Second Ban - Default SlowAoe", Enchantments.slowAoe.effectId).getInt();
        witherAoe3= config.get("Wither Aoe Compatibility", "Third Ban - Default Null", 500).getInt();
        witherAoe4= config.get("Wither Aoe Compatibility", "Fourth Ban - Default Null", 500).getInt();

        verticallity1= config.get("Verticallity Compatibility", "First Ban - Default KnockBack", enchantment.knockback.effectId).getInt();
        verticallity2 = config.get("Verticallity Compatibility", "Second Ban - Default null", 500).getInt();
        verticallity3= config.get("Verticallity Compatibility", "Third Ban - Default Null", 500).getInt();
        verticallity4= config.get("Verticallity Compatibility", "Fourth Ban - Default Null", 500).getInt();

        blast1= config.get("Blast Compatibility", "First Ban - Default Flame", enchantment.flame.effectId).getInt();
        blast2 = config.get("Blast Compatibility", "Second Ban - Default WitherArrow", Enchantments.witherArrow.effectId).getInt();
        blast3= config.get("Blast Compatibility", "Third Ban - Default SlowArrow", Enchantments.slowArrow.effectId).getInt();
        blast4= config.get("Blast Compatibility", "Fourth Ban - Default PoisonArrow", Enchantments.poisonArrow.effectId).getInt();

        magnet1= config.get("Magnet Compatibility", "First Ban - Default null", 500).getInt();
        magnet2 = config.get("Magnet Compatibility", "Second Ban - Default null", 500).getInt();
        magnet3= config.get("Magnet Compatibility", "Third Ban - Default Null", 500).getInt();
        magnet4= config.get("Magnet Compatibility", "Fourth Ban - Default Null", 500).getInt();

        vigor1= config.get("Vigor Compatibility", "First Ban - Default Unbreaking", enchantment.unbreaking.effectId).getInt();
        vigor2 = config.get("Vigor Compatibility", "Second Ban - Default null", 500).getInt();
        vigor3= config.get("Vigor Compatibility", "Third Ban - Default Null", 500).getInt();
        vigor4= config.get("Vigor Compatibility", "Fourth Ban - Default Null", 500).getInt();

        nightvision1= config.get("Night Vision Compatibility", "First Ban - Default null", 500).getInt();
        nightvision2 = config.get("Night Vision Compatibility", "Second Ban - Default null", 500).getInt();
        nightvision3= config.get("Night Vision Compatibility", "Third Ban - Default Null", 500).getInt();
        nightvision4= config.get("Night Vision Compatibility", "Fourth Ban - Default Null", 500).getInt();

        steadfast1= config.get("SteadFast Compatibility", "First Ban - Default Cloud", Enchantments.cloud.effectId).getInt();
        steadfast2 = config.get("SteadFast Compatibility", "Second Ban - Default null", 500).getInt();
        steadfast3= config.get("SteadFast Compatibility", "Third Ban - Default Null", 500).getInt();
        steadfast4= config.get("SteadFast Compatibility", "Fourth Ban - Default Null", 500).getInt();

   /*     quickdraw1= config.get("QuickDraw Compatibility", "First Ban - Default Homing", 500).getInt(); Enchantments.homing.effectId
        quickdraw2 = config.get("QuickDraw Compatibility", "Second Ban - Default Power", enchantment.power.effectId).getInt();
        quickdraw3= config.get("QuickDraw Compatibility", "Third Ban - Default Null", 500).getInt();
        quickdraw4= config.get("QuickDraw Compatibility", "Fourth Ban - Default Null", 500).getInt();
*/
        icestep1= config.get("Ice Step Compatibility", "First Ban - Default FeatherFall", enchantment.featherFalling.effectId).getInt();
        icestep2 = config.get("Ice Step Compatibility", "Second Ban - Default Swiftness", Enchantments.swiftness.effectId).getInt();
        icestep3= config.get("Ice Step Compatibility", "Third Ban - Default Null", 500).getInt();
        icestep4= config.get("Ice Step Compatibility", "Fourth Ban - Default Null", 500).getInt();

        photo1= config.get("Photosynthesis Compatibility", "First Ban - Default null", 500).getInt();
        photo2 = config.get("Photosynthesis Compatibility", "Second Ban - Default null", 500).getInt();
        photo3= config.get("Photosynthesis Compatibility", "Third Ban - Default Null", 500).getInt();
        photo4= config.get("Photosynthesis Compatibility", "Fourth Ban - Default Null", 500).getInt();

        heat1= config.get("Super Heat Compatibility", "First Ban - Default Fortune", enchantment.fortune.effectId).getInt();
        heat2 = config.get("Super Heat Compatibility", "Second Ban - Default SilkTouch", enchantment.silkTouch.effectId).getInt();
        heat3= config.get("Super Heat Compatibility", "Third Ban - Default Null", 500).getInt();
        heat4= config.get("Super Heat Compatibility", "Fourth Ban - Default Null", 500).getInt();

        venom1= config.get("Antivenom Compatibility", "First Ban - Default null", 500).getInt();
        venom2 = config.get("Antivenom Compatibility", "Second Ban - Default null", 500).getInt();
        venom3= config.get("Antivenom Compatibility", "Third Ban - Default Null", 500).getInt();
        venom4= config.get("Antivenom Compatibility", "Fourth Ban - Default Null", 500).getInt();


      config.load();
        save();
    }
    public static void save()
    {
        config.save();
    }
}
