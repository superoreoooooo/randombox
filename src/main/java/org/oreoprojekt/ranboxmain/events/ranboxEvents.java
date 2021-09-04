package org.oreoprojekt.ranboxmain.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.oreoprojekt.ranboxmain.RanBOXMain;

import java.util.HashMap;
import java.util.Random;

public class ranboxEvents implements Listener {

    HashMap<String, String> map = new HashMap<>();
    public ranboxEvents() {
        /**
        map.put("랜덤 목재 박스", "wood");
        map.put("돌 박스", "stone");
        map.put("철 박스", "iron");
        map.put("다이아몬드 박스", "diamond");
        map.put("랜덤 묘목 박스", "seedling");
        map.put("랜덤 동물 스폰알 박스", "spawnegg");
        map.put("랜덤 씨앗 박스", "seedlings");
        map.put("물 박스", "water");
        map.put("네더라이트 박스", "netherite");
        map.put("황금사과 박스", "goldenapple");
        map.put("셜커 껍데기 박스", "shulker");
        map.put("엔더 진주 박스", "enderpearl");
         **/
        map.put("wood", ChatColor.GREEN + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "랜덤 목재");
        map.put("stone", ChatColor.GRAY + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "돌");
        map.put("iron", ChatColor.GRAY + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "철");
        map.put("diamond", ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "다이아몬드");
        map.put("seedling", ChatColor.DARK_GREEN + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "묘목");
        map.put("spawnegg", ChatColor.DARK_PURPLE + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "동물 스폰알");
        map.put("seeds", ChatColor.GREEN + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "씨앗");
        map.put("water", ChatColor.BLUE + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "물 양동이");
        map.put("netherite", ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "네더라이트");
        map.put("goldenapple", ChatColor.GOLD + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "마법이 부여된 황금사과");
        map.put("shulker", ChatColor.LIGHT_PURPLE + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "셜커 껍데기");
        map.put("enderpearl", ChatColor.DARK_PURPLE + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "엔더 진주");
        map.put("world", ChatColor.AQUA + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "마법지팡이");
    }

    @EventHandler
    public void onItemClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        if (item != null) {
            if (item.getType() == Material.PLAYER_HEAD && item.getItemMeta().hasDisplayName()) {
                String ranboxname = item.getItemMeta().getDisplayName();

                if (ranboxname.equalsIgnoreCase("랜덤 박스")) {
                    for (int i = 0; i < item.getAmount(); i++) {
                        randomboxselecter(player);
                    }
                    player.getInventory().removeItem(item);
                }
                else {
                    player.sendMessage(ranboxname + "가 개봉되었습니다.");
                    for (int cnt = 0; cnt < item.getAmount(); cnt++) {
                        RanBOXMain.getBoxOutput.getItem(map.get(ranboxname), player);
                    }
                    player.getInventory().removeItem(item);
                }
            }
        }
    }

    @EventHandler
    public void onItemSet(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();
        if (block.getType() == Material.PLAYER_HEAD) {
            player.sendMessage(ChatColor.RED + "[RandomBox] 상자를 설치하지 말아주세요.");
            e.setCancelled(true);
        }
    }

    public void randomboxselecter(Player p) {
         //RanBOXMain.getItembox box = new RanBOXMain.getItembox();
         RanBOXMain.getBoxOutput box = new RanBOXMain.getBoxOutput();
         Random random = new Random();
         String boxName = "";
         int num = random.nextInt(100) + 1;
         if (num <= 25) {
              //box.getbox("wood", p);
             boxName = "wood";
         }
         else if (num > 25 && num <= 45) {
              //box.getbox("stone", p);
             boxName = "stone";
         }
         else if (num > 45 && num <= 60) {
              //box.getbox("iron", p);
             boxName = "iron";
         }
         else if (num > 60 && num <= 70) {
              //box.getbox("diamond", p);
             boxName = "diamond";
         }
         else if (num > 70 && num <= 76) {
              //box.getbox("seedling", p);
             boxName = "water";
         }
         else if (num > 76 && num <= 78) {
              //box.getbox("spawnegg", p);
             boxName = "netherite";
         }
         else if (num > 78 && num <= 86) {
              //box.getbox("seeds", p);
             boxName = "world";
         }
         else if (num > 86 && num <= 91) {
             //box.getbox("water", p);
             boxName = "enderpearl";
         }
         else if (num > 91 && num <= 94) {
              //box.getbox("water", p);
             boxName = "seedling";
         }
         else if (num > 94 && num <= 96) {
              //box.getbox("netherite", p);
             boxName = "spawnegg";
         }
         else if (num > 96 && num <= 98) {
              //box.getbox("goldenapple", p);
             boxName = "seeds";
         }
         else if (num > 98 && num <= 99) {
              //box.getbox("shulker", p);
             boxName = "shulker";
         }
         else if (num > 99 && num <= 100) {
              //box.getbox("enderpearl", p);
             boxName = "goldenapple";
         }

         box.getItem(boxName, p);
         p.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "랜덤 박스" + ChatColor.GREEN + "를 오픈하여 " + map.get(boxName) + ChatColor.GREEN + "을(를) 얻으셨습니다!");
    }
}
