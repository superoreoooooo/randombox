package org.oreoprojekt.ranboxmain;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.oreoprojekt.ranboxmain.events.ranboxEvents;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;


public final class RanBOXMain extends JavaPlugin {
    //getItembox box = new getItembox();

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "ranboxPL on");
        getServer().getPluginManager().registerEvents(new ranboxEvents(), this);
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "콘솔에서는 명령어를 치지 말아주세요." + ChatColor.RED + "치면 오류납니다.");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "ranboxPL off");
    }

    public boolean checkPermission(CommandSender sender, String permission) {
        if (sender.hasPermission(permission)) {
            return true;
        }
        String message = ChatColor.RED + "you do not have any of permissions to do that.";
        sender.sendMessage(message);
        return false;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("giveranbox")) {
            if (!checkPermission(sender,"administrator")){
                return false;
            }
            Player player = (Player) sender;
            String target = "";
            String count = "";
            try {
                target = args[0];
                count = args[1];
                Player t = Bukkit.getServer().getPlayer(target);
                int cnt = Integer.parseInt(count);
                t.getInventory().addItem(getPlayerHead(cnt));
                player.sendMessage(ChatColor.RED + "[RandomBox] " + t.getName() + "에게 랜덤 박스 " + cnt + "개를 주었습니다.");
            } catch (Exception e) {
                player.sendMessage(ChatColor.GREEN + "사용법 : /giveranbox 유저이름 갯수");
            }
        }
        return true;
    }

    public static ItemStack getPlayerHead(int count) {
        return giveItembox("ElMarcosFTW", "랜덤", "랜덤 아이템 박스", count);
    }
    /**
    public static ItemStack giveItemboxWood() {
        return giveItembox("TheRoro17", "랜덤 목재", "랜덤 목재 32개",1);
    }
    public static ItemStack giveItemboxStone() {
        return giveItembox("Stone", "돌", "돌 16개",1);
    }
    public static ItemStack giveItemboxIron() {
        return giveItembox("metalhedd", "철", "철 8개",1);
    }
    public static ItemStack giveItemboxDiamond() {
        return giveItembox("akaBruce", "다이아몬드", "다이아몬드 4개",1);
    }
    public static ItemStack giveItemboxSeedling() {
        return giveItembox("razors12403", "랜덤 묘목", "랜덤 묘목 1개",1);
    }
    public static ItemStack giveItemboxSpawnegg() {
        return giveItembox("Doggy", "랜덤 동물 스폰알", "랜덤 동물 스폰알 1개",1);
    }
    public static ItemStack giveItemboxSeeds() {
        return giveItembox("recordplayer", "랜덤 씨앗", "랜덤 씨앗 1개",1);
    }
    public static ItemStack giveItemboxWater() {
        return giveItembox("emack0714", "물", "물 양동이 1개",1);
    }
    public static ItemStack giveItemboxNetherite() {
        return giveItembox("Bedrock", "네더라이트", "네더라이트 1개",1);
    }
    public static ItemStack giveItemboxGoldenapple() {
        return giveItembox("zsoccer23", "황금사과", "노치의 황금사과 1개",1);
    }
    public static ItemStack giveItemboxShulker() {
        return giveItembox("Chaochris", "셜커 껍데기", "셜커 껍데기 1개",1);
    }
    public static ItemStack giveItemboxEnderpearl() {
        return giveItembox("Torias_Dax", "엔더 진주", "엔더 진주 8개", 1);
    }
**/
    private static String strCommonMsg = ChatColor.RED + "※" + ChatColor.BOLD + "주의" + "※" + "아이템창에 공간이 없으면 아이템이 소멸됩니다.";

    public static ItemStack giveItembox(String strOwner, String strDisplayName, String strMsg, int count) {
        Material type = Material.matchMaterial("PLAYER_HEAD");
        ItemStack item = new ItemStack(type ,count);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(strOwner);
        meta.setDisplayName(strDisplayName + " 박스");
        meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + strMsg + "가 담긴 박스다.", ChatColor.GREEN + "클릭시 오픈된다.", strCommonMsg));
        item.setItemMeta(meta);
        return item;
    }

/**
    public static class getItembox{
        public static void getbox(String boxName, Player player) {
            switch (boxName) {
                case "wood" :
                    player.getInventory().addItem(giveItemboxWood());
                    break;
                case "stone" :
                    player.getInventory().addItem(giveItemboxStone());
                    break;
                case "iron" :
                    player.getInventory().addItem(giveItemboxIron());
                    break;
                case "diamond" :
                    player.getInventory().addItem(giveItemboxDiamond());
                    break;
                case "seedling" :
                    player.getInventory().addItem(giveItemboxSeedling());
                    break;
                case "spawnegg" :
                    player.getInventory().addItem(giveItemboxSpawnegg());
                    break;
                case "seeds" :
                    player.getInventory().addItem(giveItemboxSeeds());
                    break;
                case "water" :
                    player.getInventory().addItem(giveItemboxWater());
                    break;
                case "netherite" :
                    player.getInventory().addItem(giveItemboxNetherite());
                    break;
                case "goldenapple" :
                    player.getInventory().addItem(giveItemboxGoldenapple());
                    break;
                case "shulker" :
                    player.getInventory().addItem(giveItemboxShulker());
                    break;
                case "enderpearl" :
                    player.getInventory().addItem(giveItemboxEnderpearl());
                    break;
            }
        }
    }
 **/
    public static class getBoxOutput {
        public static void getItem(String boxName, Player player) {
            switch (boxName) {
                case "wood":
                    ItemStack[] arr = {
                            new ItemStack(Material.OAK_PLANKS, 1),
                            new ItemStack(Material.SPRUCE_PLANKS, 1),
                            new ItemStack(Material.BIRCH_PLANKS, 1),
                            new ItemStack(Material.ACACIA_PLANKS, 1),
                            new ItemStack(Material.JUNGLE_PLANKS, 1)
                    };
                    Random random = new Random();
                    int num = random.nextInt(5);
                    for (int i = 0; i < 16; i++) {
                        player.getInventory().addItem(arr[num]);
                    }
                    break;
                case "stone":
                    ItemStack item = new ItemStack(Material.STONE, 16);
                    player.getInventory().addItem(item);
                    break;
                case "iron":
                    item = new ItemStack(Material.IRON_INGOT, 2);
                    player.getInventory().addItem(item);
                    break;
                case "diamond":
                    item = new ItemStack(Material.DIAMOND, 1);
                    player.getInventory().addItem(item);
                    break;
                case "seedling":
                    arr = new ItemStack[]{
                            new ItemStack(Material.OAK_SAPLING, 1),
                            new ItemStack(Material.SPRUCE_SAPLING, 1),
                            new ItemStack(Material.BIRCH_SAPLING, 1),
                            new ItemStack(Material.ACACIA_SAPLING, 1),
                            new ItemStack(Material.JUNGLE_SAPLING, 1),
                    };
                    random = new Random();
                    num = random.nextInt(5);
                    for (int i = 0; i < 1; i++) {
                        player.getInventory().addItem(arr[num]);
                    }
                    break;
                case "spawnegg":
                    arr = new ItemStack[]{
                            new ItemStack(Material.PIG_SPAWN_EGG, 1),
                            new ItemStack(Material.VILLAGER_SPAWN_EGG, 1),
                            new ItemStack(Material.CHICKEN_SPAWN_EGG, 1),
                            new ItemStack(Material.COW_SPAWN_EGG, 1),
                            new ItemStack(Material.SHEEP_SPAWN_EGG, 1)
                    };
                    random = new Random();
                    num = random.nextInt(5);
                    for (int i = 0; i < 1; i++) {
                        player.getInventory().addItem(arr[num]);
                    }
                    break;
                case "seeds":
                    arr = new ItemStack[]{
                            new ItemStack(Material.WHEAT_SEEDS, 1),
                            new ItemStack(Material.SUGAR_CANE, 1),
                            new ItemStack(Material.MELON_SEEDS, 1),
                            new ItemStack(Material.POTATO, 1),
                            new ItemStack(Material.CARROT, 1)
                    };
                    random = new Random();
                    num = random.nextInt(5);
                    for (int i = 0; i < 1; i++) {
                        player.getInventory().addItem(arr[num]);
                    }
                    break;
                case "water":
                    item = new ItemStack(Material.WATER_BUCKET, 1);
                    player.getInventory().addItem(item);
                    break;
                case "netherite":
                    item = new ItemStack(Material.NETHERITE_SCRAP, 1);
                    player.getInventory().addItem(item);
                    break;
                case "goldenapple":
                    item = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1);
                    player.getInventory().addItem(item);
                    break;
                case "shulker":
                    item = new ItemStack(Material.SHULKER_SHELL, 1);
                    player.getInventory().addItem(item);
                    break;
                case "enderpearl":
                    item = new ItemStack(Material.ENDER_PEARL, 3);
                    player.getInventory().addItem(item);
                    break;
                case "world" :
                    item = new ItemStack(Material.BLAZE_ROD ,1);
                    ItemMeta meta = (ItemMeta) item.getItemMeta();
                    meta.setDisplayName(ChatColor.DARK_PURPLE + "☆마법지팡이☆");
                    meta.setLore(Arrays.asList(ChatColor.YELLOW + "전설의 마법 지팡이다.", ChatColor.AQUA + "인벤토리에서 클릭하면 월드의 경계가 늘어난다."));
                    item.setItemMeta(meta);
                    player.getInventory().addItem(item);
            }
        }
    }
}
