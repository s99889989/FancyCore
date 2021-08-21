package com.daxton.fancycore.api.aims.entity;

import org.bukkit.entity.*;

public class Convert {


	//如果非玩家攻擊的源頭是玩家，就轉成該玩家回傳。
	public static Entity convertEntity(Entity killer){
		if(killer instanceof Player){
			return ((Player) killer).getPlayer();
		}
		if(killer instanceof Arrow){
			if(((Arrow) killer).getShooter() instanceof Player){
				return (Player) ((Arrow) killer).getShooter();
			}
		}
		if(killer instanceof ThrownPotion){
			if(((ThrownPotion) killer).getShooter() instanceof Player){
				return (Player) ((ThrownPotion) killer).getShooter();
			}
		}
		if(killer instanceof TNTPrimed){
			if(((TNTPrimed) killer).getSource() instanceof Player){
				return ((TNTPrimed) killer).getSource();
			}
		}
		if(killer instanceof Projectile){
			if(((Projectile) killer).getShooter() instanceof Player){
				return (Player) ((Projectile) killer).getShooter();
			}
		}
		if(killer instanceof Fireball){
			if(((Fireball) killer).getShooter() instanceof Player){
				return (Player) ((Fireball) killer).getShooter();
			}
		}

		return killer;
	}

	//判斷目標是否有主人，如果有目標就轉為該主人
	public static LivingEntity convertOwner(LivingEntity killer){
		Player player = null;
		//玩家
		if(killer instanceof Player){
			player = ((Player) killer).getPlayer();
			return player;
		}
		//抽象馬
		if(killer instanceof AbstractHorse){
			AbstractHorse abstractHorse = (AbstractHorse) killer;
			if(abstractHorse.getOwner() != null && abstractHorse.getOwner() instanceof Player){
				player = ((Player) abstractHorse.getOwner()).getPlayer();
				return player;
			}
		}
		//貓
		if(killer instanceof Cat){
			Cat cat = (Cat) killer;
			if(cat.getOwner() != null && cat.getOwner() instanceof Player){
				player = ((Player) cat.getOwner()).getPlayer();
				return player;
			}
		}
		//胸馬
		if(killer instanceof ChestedHorse){
			ChestedHorse chestedHorse = (ChestedHorse) killer;
			if(chestedHorse.getOwner() != null && chestedHorse.getOwner() instanceof Player){
				player = ((Player) chestedHorse.getOwner()).getPlayer();
				return player;
			}
		}
		//驢
		if(killer instanceof Donkey){
			Donkey donkey = (Donkey) killer;
			if(donkey.getOwner() != null && donkey.getOwner() instanceof Player){
				player = ((Player) donkey.getOwner()).getPlayer();
				return player;
			}
		}
		//馬
		if(killer instanceof Horse){
			Horse horse = (Horse) killer;
			if(horse.getOwner() != null && horse.getOwner() instanceof Player){
				player = ((Player) horse.getOwner()).getPlayer();
				return player;
			}
		}
		//駱駝
		if(killer instanceof Llama){
			Llama llama = (Llama) killer;
			if(llama.getOwner() != null && llama.getOwner() instanceof Player){
				player = ((Player) llama.getOwner()).getPlayer();
				return player;
			}
		}
		//騾子
		if(killer instanceof Mule){
			Mule mule = (Mule) killer;
			if(mule.getOwner() != null && mule.getOwner() instanceof Player){
				player = ((Player) mule.getOwner()).getPlayer();
				return player;
			}
		}
		//鸚鵡
		if(killer instanceof Parrot){
			Parrot parrot = (Parrot) killer;
			if(parrot.getOwner() != null && parrot.getOwner() instanceof Player){
				player = ((Player) parrot.getOwner()).getPlayer();
				return player;
			}
		}
		//骷髏馬
		if(killer instanceof SkeletonHorse){
			SkeletonHorse skeletonHorse = (SkeletonHorse) killer;
			if(skeletonHorse.getOwner() != null && skeletonHorse.getOwner() instanceof Player){
				player = ((Player) skeletonHorse.getOwner()).getPlayer();
				return player;
			}
		}
		//商人美洲駝
		if(killer instanceof TraderLlama){
			TraderLlama traderLlama = (TraderLlama) killer;
			if(traderLlama.getOwner() != null && traderLlama.getOwner() instanceof Player){
				player = ((Player) traderLlama.getOwner()).getPlayer();
				return player;
			}
		}
		//狼
		if(killer instanceof Wolf){
			Wolf wolf = (Wolf) killer;
			if(wolf.getOwner() != null && wolf.getOwner() instanceof Player){
				player = ((Player) wolf.getOwner()).getPlayer();
				return player;
			}
		}
		//殭屍馬
		if(killer instanceof ZombieHorse){
			ZombieHorse zombieHorse = (ZombieHorse) killer;
			if(zombieHorse.getOwner() != null && zombieHorse.getOwner() instanceof Player){
				player = ((Player) zombieHorse.getOwner()).getPlayer();
				return player;
			}
		}

		return killer;
	}

}
