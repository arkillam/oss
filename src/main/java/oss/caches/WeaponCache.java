package oss.caches;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import oss.enums.Weapon;
import oss.enums.WeaponClassification;

/**
 * Holds weapon information read in from weapons.csv. A singleton class.
 */

public class WeaponCache {

private static Logger logger = LogManager.getLogger(WeaponCache.class);

/** holds the single instance of this class */
private static WeaponCache weaponCache = null;

private static final String WEAPONS_FILE_NAME = "/weapons.csv";

/**
 * @return the single instance of this class
 */
public static WeaponCache getWeaponCache() {
	if (weaponCache == null)
		weaponCache = new WeaponCache();
	return weaponCache;
}

/** long arm, side arm or hand arm */
private Map<Weapon, WeaponClassification> classifications;

/** number of bullets held when fully loaded */
private Map<Weapon, Integer> load;

/** max range */
private Map<Weapon, Integer> longRange;

/** long-range modifier (usually a penalty) */
private Map<Weapon, Integer> longRangeModifier;

/** <= this number does not suffer long-range modifier */
private Map<Weapon, Integer> mediumRange;

private Map<Weapon, String> names;

/** number of weak objects this gun can shoot through (e.g. windows or doors, but not walls) */
private Map<Weapon, Integer> penetration;

/** <= this number gets short-range modifier */
private Map<Weapon, Integer> shortRange;

/** short-range modifier (usually a bonus) */
private Map<Weapon, Integer> shortRangeModifier;

/** number of chances to hit per firing (shots does not mean number of bullets expended when fired) */
private Map<Weapon, Integer> shots;

/** if true, this weapon is no longer ready after firing its last round */
private Map<Weapon, Boolean> unreadyAfterEmptying;

/** if true, this weapon is no longer ready after firing */
private Map<Weapon, Boolean> unreadyAfterFiring;

private WeaponCache() {
	classifications = new HashMap<>();
	load = new HashMap<>();
	longRange = new HashMap<>();
	longRangeModifier = new HashMap<>();
	mediumRange = new HashMap<>();
	names = new HashMap<>();
	penetration = new HashMap<>();
	shortRange = new HashMap<>();
	shortRangeModifier = new HashMap<>();
	shots = new HashMap<>();
	unreadyAfterEmptying = new HashMap<>();
	unreadyAfterFiring = new HashMap<>();

	try {
		URL url = WeaponCache.class.getResource(WEAPONS_FILE_NAME);
		InputStream in = url.openStream();
		Scanner scanner = new Scanner(in);
		scanner.useDelimiter("\n");
		while (scanner.hasNext()) {
			String row = scanner.next().trim();
			if (row.startsWith("Constant"))
				continue;

			String cols[] = row.split(",");

			int c = 0;

			Weapon weapon = Weapon.valueOf(cols[c++]);

			names.put(weapon, cols[c++]);

			classifications.put(weapon, WeaponClassification.valueOf(cols[c++]));

			shortRange.put(weapon, Integer.valueOf(cols[c++]));

			mediumRange.put(weapon, Integer.valueOf(cols[c++]));

			longRange.put(weapon, Integer.valueOf(cols[c++]));

			load.put(weapon, Integer.valueOf(cols[c++]));

			shots.put(weapon, Integer.valueOf(cols[c++]));

			penetration.put(weapon, Integer.valueOf(cols[c++]));

			shortRangeModifier.put(weapon, Integer.valueOf(cols[c++]));

			longRangeModifier.put(weapon, Integer.valueOf(cols[c++]));

			unreadyAfterFiring.put(weapon, Boolean.valueOf("1".equals(cols[c++])));

			unreadyAfterEmptying.put(weapon, Boolean.valueOf("1".equals(cols[c++])));
		}
		scanner.close();

		logger.info(String.format("Loaded %d weapons.", names.size()));
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		System.exit(1);
	}
}

public WeaponClassification getClassification(Weapon weapon) {
	return classifications.get(weapon);
}

public Integer getLoad(Weapon weapon) {
	return load.get(weapon);
}

public Integer getLongRange(Weapon weapon) {
	return longRange.get(weapon);
}

public Integer getLongRangeModifier(Weapon weapon) {
	return longRangeModifier.get(weapon);
}

public Integer getMediumRange(Weapon weapon) {
	return mediumRange.get(weapon);
}

public String getName(Weapon weapon) {
	return names.get(weapon);
}

public Integer getPenetration(Weapon weapon) {
	return penetration.get(weapon);
}

public Integer getShortRange(Weapon weapon) {
	return shortRange.get(weapon);
}

public Integer getShortRangeModifier(Weapon weapon) {
	return shortRangeModifier.get(weapon);
}

public Integer getShots(Weapon weapon) {
	return shots.get(weapon);
}

public Boolean getUnreadyAfterEmptying(Weapon weapon) {
	return unreadyAfterEmptying.get(weapon);
}

public Boolean getUnreadyAfterFiring(Weapon weapon) {
	return unreadyAfterFiring.get(weapon);
}
}
