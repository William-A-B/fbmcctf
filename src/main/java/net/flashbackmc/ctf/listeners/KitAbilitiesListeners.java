//package net.flashbackmc.ctf.listeners;
//
//import org.bukkit.entity.Arrow;
//import org.bukkit.entity.Entity;
//import org.bukkit.entity.LivingEntity;
//import org.bukkit.entity.Player;
//import org.bukkit.event.Listener;
//import org.bukkit.event.entity.EntityDamageByEntityEvent;
//
//public class KitAbilitiesListeners implements Listener {
//
//	public KitAbilitiesListeners() {
//		// TODO Auto-generated constructor stub
//	}
//
//	
//	public void onArcherArrowHit(EntityDamageByEntityEvent event) {
//		Entity entity = event.getDamager();
//		if (entity instanceof Arrow) {
//			Arrow arrow = (Arrow) entity;
//			Entity shooter = (Entity) arrow.getShooter();
//			if (shooter instanceof Player) {
//				Entity targetPlayer = event.getEntity();
//			}
//		}
//		else {
//			return;
//		}
//	
//	}
//}
