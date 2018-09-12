package oss;

import org.junit.Assert;
import org.junit.Test;

import oss.caches.WeaponCache;
import oss.enums.Weapon;

public class TestWeaponCache {

@Test
public void testName() {
	WeaponCache weaponCache = WeaponCache.getWeaponCache();
	if (!"HDA Pistol".equals(weaponCache.getName(Weapon.HDA_PISTOL))) {
		Assert.fail();
	}
}

@Test
public void testUnready() {
	WeaponCache weaponCache = WeaponCache.getWeaponCache();

	/*
	for (Weapon weapon : Weapon.values()) {
		System.out.println(String.format("%-12s firing %-5b emptying %-5b", weapon,
				weaponCache.getUnreadyAfterFiring(weapon), weaponCache.getUnreadyAfterEmptying(weapon)));
	}
	*/

	if (!weaponCache.getUnreadyAfterFiring(Weapon.HSA_PISTOL))
		Assert.fail();

	if (weaponCache.getUnreadyAfterFiring(Weapon.HDA_PISTOL))
		Assert.fail();
}

}
