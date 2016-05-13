package me.A5H73Y.Parkour.Other;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.A5H73Y.Parkour.Parkour;
import me.A5H73Y.Parkour.Utilities.Static;
import me.A5H73Y.Parkour.Utilities.Utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Configurations {

	private File dataFolder, courseFile, leaderFile, stringFile, usersFile, invFile, checkFile, upgFile, econFile;
	private FileConfiguration courseData, leaderData, stringData, usersData, invData, checkData, upgData, econData, config;

	/**
	 * This no longer generates the default config.yml to allow the ability of creating a backup of the existing config.
	 * 
	 */
	public Configurations(){
		Parkour.getPlugin().saveConfig();

		dataFolder = Parkour.getPlugin().getDataFolder();

		courseFile = new File(dataFolder, "courses.yml");
		courseData = new YamlConfiguration();
		leaderFile = new File(dataFolder, "leaderboards.yml");
		leaderData = new YamlConfiguration();
		stringFile = new File(dataFolder, "strings.yml");
		stringData = new YamlConfiguration();
		usersFile = new File(dataFolder, "players.yml");
		usersData = new YamlConfiguration();
		invFile = new File(dataFolder, "inventory.yml");
		invData = new YamlConfiguration();
		checkFile = new File(dataFolder, "checkpoints.yml");
		checkData = new YamlConfiguration();
		upgFile = new File(dataFolder, "upgrades.yml");
		upgData = new YamlConfiguration();

		// courses
		if (!courseFile.exists()) {
			try {
				courseFile.createNewFile();
				Utils.log("Created courses.yml");
			} catch (Exception ex) {
				ex.printStackTrace();
				Utils.log("Failed!");
			}
		}

		// leaderboards
		if (!leaderFile.exists()) {
			try {
				leaderFile.createNewFile();
				Utils.log("Created leaderboards.yml");
			} catch (Exception ex) {
				ex.printStackTrace();
				Utils.log("Failed!");
			}
		}

		// Strings
		if (!stringFile.exists()) {
			try {
				stringFile.createNewFile();
				Utils.log("Created strings.yml");
				saveStrings();
			} catch (Exception ex) {
				ex.printStackTrace();
				Utils.log("Failed!");
			}
		}

		// users
		if (!usersFile.exists()) {
			try {
				usersFile.createNewFile();
				Utils.log("Created players.yml");
			} catch (Exception ex) {
				ex.printStackTrace();
				Utils.log("Failed!");
			}
		}

		// inventory
		if (!invFile.exists()) {
			try {
				invFile.createNewFile();
				Utils.log("Created inventory.yml");
			} catch (Exception ex) {
				ex.printStackTrace();
				Utils.log("Failed!");
			}
		}

		// checkpoints
		if (!checkFile.exists()) {
			try {
				checkFile.createNewFile();
				Utils.log("Created checkpoints.yml");
			} catch (Exception ex) {
				ex.printStackTrace();
				Utils.log("Failed!");
			}
		}

		try{
			courseData.load(courseFile);
			leaderData.load(leaderFile);
			stringData.load(stringFile);
			usersData.load(usersFile);
			invData.load(invFile);
			checkData.load(checkFile);
		} catch (Exception ex){
			Utils.log("Failed loading config: " + ex.getMessage());
			ex.printStackTrace();
		}

		saveAll();
	}

	public void saveAll(){
		saveCheck();
		saveCourses();
		//saveEcon();
		saveInv();
		saveLeaders();
		saveStrings();
		saveUpgrades();
		saveUsers();
		Parkour.getPlugin().saveConfig();
	}

	public void reload(){
		//TODO finish me
		Parkour.getPlugin().reloadConfig();

		courseData = YamlConfiguration.loadConfiguration(courseFile);
		leaderData = YamlConfiguration.loadConfiguration(leaderFile);
		stringData = YamlConfiguration.loadConfiguration(stringFile);
		usersData = YamlConfiguration.loadConfiguration(usersFile);
		invData = YamlConfiguration.loadConfiguration(invFile);
		checkData = YamlConfiguration.loadConfiguration(checkFile);
	}

	//AllStrings
	private void saveStrings() {
		try{
			stringData.addDefault("==| README |==", "TO SAVE THESE VALUES: STOP THE SERVER. SAVE YOUR CHANGES. START THE SERVER!");
			stringData.addDefault("Parkour.PrefixColour", "b");
			if (stringData.getString("Parkour.PrefixColour").length() != 1)
				stringData.set("Parkour.PrefixColour", "b");

			stringData.addDefault("Event.Join", "This server uses &bParkour &3%VERSION%");
			stringData.addDefault("Event.Checkpoint", "Checkpoint set to: ");
			stringData.addDefault("Event.AllCheckpoints", "All checkpoints achieved!");
			stringData.addDefault("Event.HideAll1", "All players have magically reappeared!");
			stringData.addDefault("Event.HideAll2", "All players have magically disappeared!");
			stringData.addDefault("Event.Chat", "&0[%RANK%&0] &b%PLAYER%:&f %MESSAGE%");

			stringData.addDefault("Parkour.Join", "Joined &b%COURSE%");
			stringData.addDefault("Parkour.Leave", "You have left &b%COURSE%");
			stringData.addDefault("Parkour.Created", "Created and Selected &b%COURSE%");
			stringData.addDefault("Parkour.Delete", "&b%COURSE% &fhas been deleted!");
			stringData.addDefault("Parkour.Reset", "&b%COURSE% &fscores have been reset!");
			stringData.addDefault("Parkour.Finish", "&b%COURSE% &fhas been set to finished!");
			stringData.addDefault("Parkour.FinishBroadcast", "&3%PLAYER% &ffinished &b%COURSE% &fwith &b%DEATHS% &fdeaths, in &b%TIME%&f!");
			stringData.addDefault("Parkour.FinishEconomy", "You earned &b%AMOUNT% &ffor completing &b%COURSE%&f!");
			stringData.addDefault("Parkour.Lobby", "You have joined the lobby");
			stringData.addDefault("Parkour.LobbyOther", "You have joined the &b%LOBBY% &flobby");
			stringData.addDefault("Parkour.Continue", "Continuing Parkour on &b%COURSE%");
			stringData.addDefault("Parkour.Teleport", "You have teleported to &b%COURSE%");
			stringData.addDefault("Parkour.Invite.Send", "Invitation to &b%COURSE% &fsent to &b%TARGET%");
			stringData.addDefault("Parkour.Invite.Recieve1", "&b%PLAYER% &fhas invited you to &b%COURSE%");
			stringData.addDefault("Parkour.Invite.Recieve2", "To accept, type &3/pa join %COURSE%");
			stringData.addDefault("Parkour.MaxDeaths", "Sorry, you reached the maximum amount of deaths: &b%AMOUNT%");
			stringData.addDefault("Parkour.Die1", "You died! Going back to the start!");
			stringData.addDefault("Parkour.Die2", "You died! Going back to checkpoint &b%POINT%");
			stringData.addDefault("Parkour.Win1", "You placed 1st on &b%COURSE%&f! New highscore: &3%TIME%");
			stringData.addDefault("Parkour.Win2", "You placed 2nd on &b%COURSE%&f!");
			stringData.addDefault("Parkour.Win3", "You placed 3rd on &b%COURSE%&f!");

			stringData.addDefault("Error.NotOnCourse", "You are not on this course!");
			stringData.addDefault("Error.TooMany", "Too many arguments!");
			stringData.addDefault("Error.TooLittle", "Not enough arguments!");
			stringData.addDefault("Error.Exist", "This course already exists!");
			stringData.addDefault("Error.NoExist", "&b%COURSE% &fdoesn't exist!");
			stringData.addDefault("Error.Unknown", "Unknown course!");
			stringData.addDefault("Error.Command", "Commands have been disabled!");
			stringData.addDefault("Error.Selected", "You have not selected a course!");
			stringData.addDefault("Error.Something", "Something went wrong: &4%ERROR%");
			stringData.addDefault("Error.RequiredLvl", "You require level &b%LEVEL% &fto join this lobby!");
			stringData.addDefault("Error.Finished1", "This course is not ready for you to play yet!");
			stringData.addDefault("Error.Finished2", "The creator of this course has not set it to finished.");
			stringData.addDefault("Error.Syntax", "&4Invalid Syntax: &f/pa %COMMAND% %ARGUMENTS%");
			stringData.addDefault("Error.UnknownSignCommand", "Unknown sign command!");
			stringData.addDefault("Error.UnknownCommand", "Unknown command!");

			stringData.addDefault("Other.Item_Suicide", "&7SHIFT + &6Right click to commit suicide");
			stringData.addDefault("Other.Item_HideAll", "&7SHIFT + &6Right click to toggle visibility");
			stringData.addDefault("Other.Item_Leave", "&7SHIFT + &6Right click to leave course");
			stringData.addDefault("Other.Item_Book", "&6View course stats");

			stringData.addDefault("Other.Reload", "Config Reloaded!");
			stringData.addDefault("Other.Kit", "Kit Given!");

			stringData.addDefault("Kit.Speed", "&bSpeed Block");
			stringData.addDefault("Kit.Climb", "&bClimb Block");
			stringData.addDefault("Kit.Launch", "&bLaunch Block");
			stringData.addDefault("Kit.Finish", "&bFinish Block");
			stringData.addDefault("Kit.Repulse", "&bRepulse Block");
			stringData.addDefault("Kit.NoRun", "&bNoRun Block");
			stringData.addDefault("Kit.NoFall", "&bNoFall Block");
			stringData.addDefault("Kit.NoPotion", "&bNoPotion Block");
			stringData.addDefault("Kit.Sign", "&bSign");
			stringData.addDefault("Kit.Death", "&bDeath Block");

			stringData.addDefault("Kit.DoubleJump", "&bDoubleJump Block");

			stringData.addDefault("Spectate.AlertPlayer", "You are now being spectated by &b%PLAYER%");
			stringData.addDefault("Spectate.FinishedSpec", "You are no longer being spectated");

			stringData.addDefault("Title.Joining", "Joining");
			stringData.addDefault("Title.Checkpoint", "checkpoint");
			stringData.addDefault("Title.Checkpoints", "checkpoints");

			stringData.addDefault("NoPermission", "You do not have Permission: &b%PERMISSION%");
			stringData.options().copyDefaults(true);
			stringData.save(stringFile);
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}


	public void setupConfig(){
		config = Parkour.getPlugin().getConfig();

		config.options().header("==== Parkour Config ==== #");

		config.addDefault("DefaultBlocks.Enabled", true);
		config.addDefault("DefaultBlocks.Finish.Material", "HUGE_MUSHROOM_2");
		config.addDefault("DefaultBlocks.Climb.Material", "BRICK");
		config.addDefault("DefaultBlocks.Climb.Strength", 0.4);
		config.addDefault("DefaultBlocks.Launch.Material", "EMERALD_BLOCK");
		config.addDefault("DefaultBlocks.Launch.Strength", 1.2);
		config.addDefault("DefaultBlocks.Bounce.Material", "MOSSY_COBBLESTONE");
		config.addDefault("DefaultBlocks.Bounce.Strength", 0.6);
		config.addDefault("DefaultBlocks.Speed.Material", "OBSIDIAN");
		config.addDefault("DefaultBlocks.Speed.Strength", 5);
		config.addDefault("DefaultBlocks.Speed.Duration", 200);
		config.addDefault("DefaultBlocks.Repulse.Material", "ENDER_STONE");
		config.addDefault("DefaultBlocks.Repulse.Strength", 0.4);
		config.addDefault("DefaultBlocks.NoRun.Material", "GOLD_BLOCK");
		config.addDefault("DefaultBlocks.NoPotion.Material", "HUGE_MUSHROOM_2");

		config.addDefault("OnJoin.SetGamemode", 0);
		config.addDefault("OnJoin.DisplayFly", true);
		config.addDefault("OnJoin.EnforceWorld", true);
		config.addDefault("OnJoin.EnforceFinished", true); //TODO onJoin, do permission / owner check
		config.addDefault("OnJoin.Item.Suicide.Material", "ARROW");
		config.addDefault("OnJoin.Item.HideAll.Material", "BONE");
		config.addDefault("OnJoin.Item.Leave.Material", "LEAVES");

		config.addDefault("OnCourse.EnforceParkourCommands.Enabled", true);
		String[] whitelisted = {"login"};
		config.addDefault("OnCourse.EnforceParkourCommands.Whitelist", whitelisted);
		config.addDefault("OnCourse.EnforceParkourSigns", true);
		config.addDefault("OnCourse.DisablePlayerDamage", false);
		config.addDefault("OnCourse.MaxFallTicks", 80);
		config.addDefault("OnCourse.Checkpoint", "FINISHME"); //TODO No idea what I was thinking about..?
		config.addDefault("OnCourse.LiveLeaderboard.Enabled", true);
		config.addDefault("OnCourse.LiveLeaderboard.Type", 1);

		config.addDefault("OnFinish.EnforceCompletion", true);
		config.addDefault("OnFinish.TeleportToLobby", true);
		config.addDefault("OnFinish.SetGamemode", 0);
		config.addDefault("OnFinish.BroadcastLevel", 3);
		config.addDefault("OnFinish.SetXPBarToParkourLevel", false);
		config.addDefault("OnFinish.DefaultPrize.Enabled", true);
		config.addDefault("OnFinish.DefaultPrize.Material", "FINISHME");
		config.addDefault("OnFinish.DefaultPrize.Amount", 1);
		config.addDefault("OnFinish.DefaultPrize.XP", 0);

		config.addDefault("OnDie.SetXPBarToDeathCount", false);
		config.addDefault("OnDie.ResetTimeWithNoCheckpoint", false);

		config.addDefault("OnLeave.ResetPlayer", false);

		config.addDefault("ParkourModes.CodJumper.Enabled", false);
		config.addDefault("ParkourModes.CodJumper.ConfirmSet", true);
		config.addDefault("ParkourModes.Spectate.OnStart.NotifyPlayer", true);
		config.addDefault("ParkourModes.Spectate.OnStop.RemainInvisible", false);
		config.addDefault("ParkourModes.Spectate.OnStop.TeleportToLobby", true);

		config.addDefault("Other.Economy.Enabled", true);
		config.addDefault("Other.TitleActionBar.Enabled", true);
		config.addDefault("Other.CheckForUpdates", true);
		config.addDefault("Other.SubmitMetrics", true);
		config.addDefault("Other.LogToFile", true);
		config.addDefault("Other.DebugMode", false);
		config.addDefault("Other.Parkour.ChatRankPrefix", false);
		config.addDefault("Other.Parkour.SignProtection", true);
		config.addDefault("Other.Parkour.InventoryManagement", true);
		config.addDefault("Other.Parkour.CommandPermissions", false);
		config.addDefault("Other.Display.JoinWelcomeMessage", true);
		config.addDefault("Other.Display.CreatorJoin", true);
		config.addDefault("Other.Display.CourseNotFinished", true);
		config.addDefault("Other.Display.LevelReward", true);
		config.addDefault("Other.Display.TitleOnJoin", true);


		config.addDefault("MySQL.Use", true);
		config.addDefault("MySQL.Host", "Host");
		config.addDefault("MySQL.Port", 3306);
		config.addDefault("MySQL.User", "Username");
		config.addDefault("MySQL.Password", "Password");
		config.addDefault("MySQL.Database", "Database");
		config.addDefault("MySQL.Table", "Table");

		config.addDefault("=== Do NOT Edit anything below here ===", null);
		config.addDefault("Version", 0);

		config.addDefault("Lobby.Set", false);
		config.options().copyDefaults(true);
		Parkour.getPlugin().saveConfig();
	}


	public FileConfiguration getCheckData() {
		return checkData;
	}

	public FileConfiguration getCourseData() {
		return courseData;
	}

	public FileConfiguration getLeaderData() {
		return leaderData;
	}

	public FileConfiguration getStringData() {
		return stringData;
	}

	public FileConfiguration getUsersData() {
		return usersData;
	}

	public FileConfiguration getInvData() {
		return invData;
	}

	public FileConfiguration getUpgData() {
		return upgData;
	}

	public FileConfiguration getEconData() {
		return econData;
	}

	public FileConfiguration getConfig() {
		return config;
	}

	public File getDataFolder(){
		return dataFolder;
	}

	public void saveCourses() {
		try {
			courseData.addDefault("Courses", new ArrayList<String>());
			courseData.options().copyDefaults(true);
			courseData.save(courseFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void saveLeaders() {
		try {
			leaderData.save(leaderFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void saveUsers() {
		try {
			usersData.save(usersFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void saveInv() {
		try {
			invData.save(invFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void saveCheck() {
		try {
			checkData.save(checkFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void saveUpgrades() {
		try {
			upgData.addDefault("XPRankUpAt", 500);
			upgData.addDefault("XPRankUpMultiplier", 100);
			upgData.options().copyDefaults(true);
			upgData.save(upgFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void saveEcon() {
		try {
			econData.addDefault("Price.Kit", 0);
			econData.options().copyDefaults(true);
			econData.save(econFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}


	public List<String> getAllCourses() {
		return courseData.getStringList("Courses");
	}

	public void initiateEconomy(){
		Static.setEconomy(true);
		econFile = new File(Parkour.getPlugin().getDataFolder(), "economy.yml");
		econData = new YamlConfiguration();

		if (!econFile.exists()) {
			try {
				econFile.createNewFile();
				Utils.log("Created economy.yml");
				saveEcon();
			} catch (Exception ex) {
				ex.printStackTrace();
				Utils.log("Failed.");
			}
		}
		try {
			econData.load(econFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}