package com.craftmend.openaudiomc.spigot.modules.show.fake;

import com.destroystokyo.paper.ClientOption;
import com.destroystokyo.paper.Title;
import com.destroystokyo.paper.block.TargetBlockInfo;
import com.destroystokyo.paper.entity.TargetEntityInfo;
import com.destroystokyo.paper.profile.PlayerProfile;
import io.papermc.paper.entity.LookAnchor;
import io.papermc.paper.entity.TeleportFlag;
import io.papermc.paper.math.Position;
import io.papermc.paper.threadedregions.scheduler.EntityScheduler;
import lombok.AllArgsConstructor;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.util.TriState;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.sign.Side;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.*;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.*;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.*;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@AllArgsConstructor
public class MockExecutor implements Player, CommandSender {

    private String worldName;

    @Override
    public String getDisplayName() {
        return "OaPlayer";
    }

    @Override
    public void setDisplayName(String s) {

    }

    @Override
    public void playerListName(@Nullable Component name) {

    }

    @Override
    public @NotNull Component playerListName() {
        return null;
    }

    @Override
    public @Nullable Component playerListHeader() {
        return null;
    }

    @Override
    public @Nullable Component playerListFooter() {
        return null;
    }

    @Override
    public String getPlayerListName() {
        return "OaPlayer";
    }

    @Override
    public void setPlayerListName(String s) {

    }

    @Override
    public String getPlayerListHeader() {
        return "OaPlayer";
    }

    @Override
    public String getPlayerListFooter() {
        return "OaPlayer";
    }

    @Override
    public void setPlayerListHeader(String s) {

    }

    @Override
    public void setPlayerListFooter(String s) {

    }

    @Override
    public void setPlayerListHeaderFooter(String s, String s1) {

    }

    @Override
    public void setCompassTarget(Location location) {

    }

    @Override
    public Location getCompassTarget() {
        return null;
    }

    @Override
    public InetSocketAddress getAddress() {
        return null;
    }

    @Override
    public int getProtocolVersion() {
        return 0;
    }

    @Override
    public @Nullable InetSocketAddress getVirtualHost() {
        return null;
    }

    @Override
    public boolean isConversing() {
        return false;
    }

    @Override
    public void acceptConversationInput(String s) {

    }

    @Override
    public boolean beginConversation(Conversation conversation) {
        return false;
    }

    @Override
    public void abandonConversation(Conversation conversation) {

    }

    @Override
    public void abandonConversation(Conversation conversation, ConversationAbandonedEvent conversationAbandonedEvent) {

    }

    @Override
    public void sendRawMessage(String s) {

    }

    @Override
    public void sendRawMessage(@Nullable UUID sender, @NotNull String message) {

    }

    @Override
    public void kickPlayer(String s) {

    }

    @Override
    public void kick() {

    }

    @Override
    public void kick(@Nullable Component message) {

    }

    @Override
    public void kick(@Nullable Component message, PlayerKickEvent.@NotNull Cause cause) {

    }

    @Override
    public <E extends BanEntry<? super PlayerProfile>> @Nullable E ban(@Nullable String reason, @Nullable Date expires, @Nullable String source, boolean kickPlayer) {
        return null;
    }

    @Override
    public <E extends BanEntry<? super PlayerProfile>> @Nullable E ban(@Nullable String reason, @Nullable Instant expires, @Nullable String source, boolean kickPlayer) {
        return null;
    }

    @Override
    public <E extends BanEntry<? super PlayerProfile>> @Nullable E ban(@Nullable String reason, @Nullable Duration duration, @Nullable String source, boolean kickPlayer) {
        return null;
    }

    @Override
    public @Nullable BanEntry<InetAddress> banIp(@Nullable String reason, @Nullable Date expires, @Nullable String source, boolean kickPlayer) {
        return null;
    }

    @Override
    public @Nullable BanEntry<InetAddress> banIp(@Nullable String reason, @Nullable Instant expires, @Nullable String source, boolean kickPlayer) {
        return null;
    }

    @Override
    public @Nullable BanEntry<InetAddress> banIp(@Nullable String reason, @Nullable Duration duration, @Nullable String source, boolean kickPlayer) {
        return null;
    }

    @Override
    public void chat(String s) {

    }

    @Override
    public boolean performCommand(String s) {
        return false;
    }

    @Override
    public boolean isSneaking() {
        return false;
    }

    @Override
    public void setSneaking(boolean b) {

    }

    @Override
    public void setPose(@NotNull Pose pose, boolean fixed) {

    }

    @Override
    public boolean hasFixedPose() {
        return false;
    }

    @Override
    public @NotNull SpawnCategory getSpawnCategory() {
        return null;
    }

    @Override
    public boolean isInWorld() {
        return false;
    }

    @Override
    public @Nullable EntitySnapshot createSnapshot() {
        return null;
    }

    @Override
    public @NotNull Entity copy() {
        return null;
    }

    @Override
    public @NotNull Entity copy(@NotNull Location to) {
        return null;
    }

    @Override
    public boolean isSprinting() {
        return false;
    }

    @Override
    public void setSprinting(boolean b) {

    }

    @Override
    public void saveData() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void setSleepingIgnored(boolean b) {

    }

    @Override
    public boolean isSleepingIgnored() {
        return false;
    }

    @Override
    public void playNote(Location location, byte b, byte b1) {

    }

    @Override
    public void playNote(Location location, Instrument instrument, Note note) {

    }

    @Override
    public void playSound(Location location, Sound sound, float v, float v1) {

    }

    @Override
    public void playSound(Location location, String s, float v, float v1) {

    }

    @Override
    public void playSound(Location location, Sound sound, SoundCategory soundCategory, float v, float v1) {

    }

    @Override
    public void playSound(Location location, String s, SoundCategory soundCategory, float v, float v1) {

    }

    @Override
    public void playSound(@NotNull Location location, @NotNull Sound sound, @NotNull SoundCategory category, float volume, float pitch, long seed) {

    }

    @Override
    public void playSound(@NotNull Location location, @NotNull String sound, @NotNull SoundCategory category, float volume, float pitch, long seed) {

    }

    @Override
    public void playSound(@NotNull Entity entity, @NotNull Sound sound, float volume, float pitch) {

    }

    @Override
    public void playSound(@NotNull Entity entity, @NotNull String sound, float volume, float pitch) {

    }

    @Override
    public void playSound(@NotNull Entity entity, @NotNull Sound sound, @NotNull SoundCategory category, float volume, float pitch) {

    }

    @Override
    public void playSound(@NotNull Entity entity, @NotNull String sound, @NotNull SoundCategory category, float volume, float pitch) {

    }

    @Override
    public void playSound(@NotNull Entity entity, @NotNull Sound sound, @NotNull SoundCategory category, float volume, float pitch, long seed) {

    }

    @Override
    public void playSound(@NotNull Entity entity, @NotNull String sound, @NotNull SoundCategory category, float volume, float pitch, long seed) {

    }

    @Override
    public void stopSound(Sound sound) {

    }

    @Override
    public void stopSound(String s) {

    }

    @Override
    public void stopSound(Sound sound, SoundCategory soundCategory) {

    }

    @Override
    public void stopSound(String s, SoundCategory soundCategory) {

    }

    @Override
    public void stopSound(@NotNull SoundCategory category) {

    }

    @Override
    public void stopAllSounds() {

    }

    @Override
    public void playEffect(Location location, Effect effect, int i) {

    }

    @Override
    public <T> void playEffect(Location location, Effect effect, T t) {

    }

    @Override
    public boolean breakBlock(@NotNull Block block) {
        return false;
    }

    @Override
    public void sendBlockChange(Location location, Material material, byte b) {

    }

    @Override
    public void sendBlockChange(Location location, BlockData blockData) {

    }

    @Override
    public void sendBlockChanges(@NotNull Collection<BlockState> blocks) {

    }

    @Override
    public void sendBlockChanges(@NotNull Collection<BlockState> blocks, boolean suppressLightUpdates) {

    }

    @Override
    public void sendBlockDamage(@NotNull Location loc, float progress) {

    }

    @Override
    public void sendMultiBlockChange(@NotNull Map<? extends Position, BlockData> blockChanges) {

    }

    @Override
    public void sendBlockDamage(@NotNull Location loc, float progress, @NotNull Entity source) {

    }

    @Override
    public void sendBlockDamage(@NotNull Location loc, float progress, int sourceId) {

    }

    @Override
    public void sendEquipmentChange(@NotNull LivingEntity entity, @NotNull EquipmentSlot slot, @Nullable ItemStack item) {

    }

    @Override
    public void sendEquipmentChange(@NotNull LivingEntity entity, @NotNull Map<EquipmentSlot, ItemStack> items) {

    }

    @Override
    public void sendSignChange(@NotNull Location loc, @Nullable List<? extends Component> lines, @NotNull DyeColor dyeColor, boolean hasGlowingText) throws IllegalArgumentException {

    }


    @Override
    public void sendSignChange(Location location, String[] strings) throws IllegalArgumentException {

    }

    @Override
    public void sendSignChange(@NotNull Location loc, @Nullable String[] lines, @NotNull DyeColor dyeColor) throws IllegalArgumentException {

    }

    @Override
    public void sendSignChange(@NotNull Location loc, @Nullable String[] lines, @NotNull DyeColor dyeColor, boolean hasGlowingText) throws IllegalArgumentException {

    }

    @Override
    public void sendBlockUpdate(@NotNull Location loc, @NotNull TileState tileState) throws IllegalArgumentException {

    }

    @Override
    public void sendPotionEffectChange(@NotNull LivingEntity entity, @NotNull PotionEffect effect) {

    }

    @Override
    public void sendPotionEffectChangeRemove(@NotNull LivingEntity entity, @NotNull PotionEffectType type) {

    }

    @Override
    public void sendMap(MapView mapView) {

    }

    @Override
    public void showWinScreen() {

    }

    @Override
    public boolean hasSeenWinScreen() {
        return false;
    }

    @Override
    public void setHasSeenWinScreen(boolean hasSeenWinScreen) {

    }

    @Override
    public void sendActionBar(@NotNull String message) {

    }

    @Override
    public void sendActionBar(char alternateChar, @NotNull String message) {

    }

    @Override
    public void sendActionBar(@NotNull BaseComponent... message) {

    }

    @Override
    public void setPlayerListHeaderFooter(@Nullable BaseComponent[] header, @Nullable BaseComponent[] footer) {

    }

    @Override
    public void setPlayerListHeaderFooter(@Nullable BaseComponent header, @Nullable BaseComponent footer) {

    }

    @Override
    public void setTitleTimes(int fadeInTicks, int stayTicks, int fadeOutTicks) {

    }

    @Override
    public void setSubtitle(BaseComponent[] subtitle) {

    }

    @Override
    public void setSubtitle(BaseComponent subtitle) {

    }

    @Override
    public void showTitle(@Nullable BaseComponent[] title) {

    }

    @Override
    public void showTitle(@Nullable BaseComponent title) {

    }

    @Override
    public void showTitle(@Nullable BaseComponent[] title, @Nullable BaseComponent[] subtitle, int fadeInTicks, int stayTicks, int fadeOutTicks) {

    }

    @Override
    public void showTitle(@Nullable BaseComponent title, @Nullable BaseComponent subtitle, int fadeInTicks, int stayTicks, int fadeOutTicks) {

    }

    @Override
    public void sendTitle(@NotNull Title title) {

    }

    @Override
    public void updateTitle(@NotNull Title title) {

    }

    @Override
    public void hideTitle() {

    }

    @Override
    public void sendHurtAnimation(float yaw) {

    }

    @Override
    public void addCustomChatCompletions(@NotNull Collection<String> completions) {

    }

    @Override
    public void removeCustomChatCompletions(@NotNull Collection<String> completions) {

    }

    @Override
    public void setCustomChatCompletions(@NotNull Collection<String> completions) {

    }

    @Override
    public void updateInventory() {

    }

    @Override
    public @Nullable GameMode getPreviousGameMode() {
        return null;
    }


    @Override
    public void incrementStatistic(Statistic statistic) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(Statistic statistic) throws IllegalArgumentException {

    }

    @Override
    public void incrementStatistic(Statistic statistic, int i) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(Statistic statistic, int i) throws IllegalArgumentException {

    }

    @Override
    public void setStatistic(Statistic statistic, int i) throws IllegalArgumentException {

    }

    @Override
    public int getStatistic(Statistic statistic) throws IllegalArgumentException {
        return 0;
    }

    @Override
    public void incrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {

    }

    @Override
    public int getStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
        return 0;
    }

    @Override
    public void incrementStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException {

    }

    @Override
    public void setStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException {

    }

    @Override
    public void incrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {

    }

    @Override
    public int getStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
        return 0;
    }

    @Override
    public void incrementStatistic(Statistic statistic, EntityType entityType, int i) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(Statistic statistic, EntityType entityType, int i) {

    }

    @Override
    public void setStatistic(Statistic statistic, EntityType entityType, int i) {

    }

    @Override
    public void setPlayerTime(long l, boolean b) {

    }

    @Override
    public long getPlayerTime() {
        return 0;
    }

    @Override
    public long getPlayerTimeOffset() {
        return 0;
    }

    @Override
    public boolean isPlayerTimeRelative() {
        return false;
    }

    @Override
    public void resetPlayerTime() {

    }

    @Override
    public void setPlayerWeather(WeatherType weatherType) {

    }

    @Override
    public WeatherType getPlayerWeather() {
        return null;
    }

    @Override
    public void resetPlayerWeather() {

    }

    @Override
    public void giveExp(int i) {

    }

    @Override
    public int getExpCooldown() {
        return 0;
    }

    @Override
    public void setExpCooldown(int ticks) {

    }

    @Override
    public void giveExp(int amount, boolean applyMending) {

    }

    @Override
    public int applyMending(int amount) {
        return 0;
    }

    @Override
    public void giveExpLevels(int i) {

    }

    @Override
    public float getExp() {
        return 0;
    }

    @Override
    public void setExp(float v) {

    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public void setLevel(int i) {

    }

    @Override
    public int getTotalExperience() {
        return 0;
    }

    @Override
    public void setTotalExperience(int i) {

    }

    @Override
    public @Range(from = 0L, to = 2147483647L) int calculateTotalExperiencePoints() {
        return 0;
    }

    @Override
    public void setExperienceLevelAndProgress(@Range(from = 0L, to = 2147483647L) int totalExperience) {

    }

    @Override
    public int getExperiencePointsNeededForNextLevel() {
        return 0;
    }

    @Override
    public void sendExperienceChange(float progress) {

    }

    @Override
    public void sendExperienceChange(float progress, int level) {

    }

    @Override
    public float getExhaustion() {
        return 0;
    }

    @Override
    public void setExhaustion(float v) {

    }

    @Override
    public float getSaturation() {
        return 0;
    }

    @Override
    public void setSaturation(float v) {

    }

    @Override
    public int getFoodLevel() {
        return 0;
    }

    @Override
    public void setFoodLevel(int i) {

    }

    @Override
    public int getSaturatedRegenRate() {
        return 0;
    }

    @Override
    public void setSaturatedRegenRate(int ticks) {

    }

    @Override
    public int getUnsaturatedRegenRate() {
        return 0;
    }

    @Override
    public void setUnsaturatedRegenRate(int ticks) {

    }

    @Override
    public int getStarvationRate() {
        return 0;
    }

    @Override
    public void setStarvationRate(int ticks) {

    }

    @Override
    public @Nullable Location getLastDeathLocation() {
        return null;
    }

    @Override
    public void setLastDeathLocation(@Nullable Location location) {

    }

    @Override
    public @Nullable Firework fireworkBoost(@NotNull ItemStack fireworkItemStack) {
        return null;
    }

    @Override
    public boolean getAllowFlight() {
        return false;
    }

    @Override
    public void setAllowFlight(boolean b) {

    }

    @Override
    public void setFlyingFallDamage(@NotNull TriState flyingFallDamage) {

    }

    @Override
    public @NotNull TriState hasFlyingFallDamage() {
        return null;
    }

    @Override
    public void hidePlayer(Player player) {

    }

    @Override
    public void hidePlayer(Plugin plugin, Player player) {

    }

    @Override
    public void showPlayer(Player player) {

    }

    @Override
    public void showPlayer(Plugin plugin, Player player) {

    }

    @Override
    public boolean canSee(Player player) {
        return false;
    }

    @Override
    public void hideEntity(@NotNull Plugin plugin, @NotNull Entity entity) {

    }

    @Override
    public void showEntity(@NotNull Plugin plugin, @NotNull Entity entity) {

    }

    @Override
    public boolean canSee(@NotNull Entity entity) {
        return false;
    }

    @Override
    public boolean isListed(@NotNull Player other) {
        return false;
    }

    @Override
    public boolean unlistPlayer(@NotNull Player other) {
        return false;
    }

    @Override
    public boolean listPlayer(@NotNull Player other) {
        return false;
    }

    @Override
    public boolean isFlying() {
        return false;
    }

    @Override
    public void setFlying(boolean b) {

    }

    @Override
    public void setFlySpeed(float v) throws IllegalArgumentException {

    }

    @Override
    public void setWalkSpeed(float v) throws IllegalArgumentException {

    }

    @Override
    public float getFlySpeed() {
        return 0;
    }

    @Override
    public float getWalkSpeed() {
        return 0;
    }

    @Override
    public void setTexturePack(String s) {

    }

    @Override
    public void setResourcePack(String s) {

    }

    @Override
    public void setResourcePack(String s, byte[] bytes) {

    }

    @Override
    public void setResourcePack(@NotNull String url, @Nullable byte[] hash, @Nullable String prompt) {

    }

    @Override
    public void setResourcePack(@NotNull String url, @Nullable byte[] hash, boolean force) {

    }

    @Override
    public void setResourcePack(@NotNull String url, @Nullable byte[] hash, @Nullable String prompt, boolean force) {

    }

    @Override
    public void setResourcePack(@NotNull UUID id, @NotNull String url, @Nullable byte[] hash, @Nullable String prompt, boolean force) {

    }

    @Override
    public void setResourcePack(@NotNull UUID uuid, @NotNull String url, byte @Nullable [] hash, @Nullable Component prompt, boolean force) {

    }

    @Override
    public PlayerResourcePackStatusEvent.@Nullable Status getResourcePackStatus() {
        return null;
    }

    @Override
    public void addResourcePack(@NotNull UUID id, @NotNull String url, @Nullable byte[] hash, @Nullable String prompt, boolean force) {

    }

    @Override
    public void removeResourcePack(@NotNull UUID id) {

    }

    @Override
    public void removeResourcePacks() {

    }

    @Override
    public Scoreboard getScoreboard() {
        return null;
    }

    @Override
    public void setScoreboard(Scoreboard scoreboard) throws IllegalArgumentException, IllegalStateException {

    }

    @Override
    public @Nullable WorldBorder getWorldBorder() {
        return null;
    }

    @Override
    public void setWorldBorder(@Nullable WorldBorder border) {

    }

    @Override
    public void sendHealthUpdate(double health, int foodLevel, float saturation) {

    }

    @Override
    public void sendHealthUpdate() {

    }

    @Override
    public boolean isHealthScaled() {
        return false;
    }

    @Override
    public void setHealthScaled(boolean b) {

    }

    @Override
    public void setHealthScale(double v) throws IllegalArgumentException {

    }

    @Override
    public double getHealthScale() {
        return 0;
    }

    @Override
    public Entity getSpectatorTarget() {
        return null;
    }

    @Override
    public void setSpectatorTarget(Entity entity) {

    }

    @Override
    public void sendTitle(String s, String s1) {

    }

    @Override
    public void sendTitle(String s, String s1, int i, int i1, int i2) {

    }

    @Override
    public void resetTitle() {

    }

    @Override
    public void spawnParticle(Particle particle, Location location, int i) {

    }

    @Override
    public void spawnParticle(Particle particle, double v, double v1, double v2, int i) {

    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int i, T t) {

    }

    @Override
    public <T> void spawnParticle(Particle particle, double v, double v1, double v2, int i, T t) {

    }

    @Override
    public void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2) {

    }

    @Override
    public void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5) {

    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2, T t) {

    }

    @Override
    public <T> void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, T t) {

    }

    @Override
    public void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2, double v3) {

    }

    @Override
    public void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, double v6) {

    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2, double v3, T t) {

    }

    @Override
    public <T> void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, double v6, T t) {

    }

    @Override
    public AdvancementProgress getAdvancementProgress(Advancement advancement) {
        return null;
    }

    @Override
    public int getClientViewDistance() {
        return 0;
    }

    @Override
    public @NotNull Locale locale() {
        return null;
    }

    @Override
    public int getPing() {
        return 0;
    }

    @Override
    public String getLocale() {
        return null;
    }

    @Override
    public boolean getAffectsSpawning() {
        return false;
    }

    @Override
    public void setAffectsSpawning(boolean affects) {

    }

    @Override
    public int getViewDistance() {
        return 0;
    }

    @Override
    public void setViewDistance(int viewDistance) {

    }

    @Override
    public int getSimulationDistance() {
        return 0;
    }

    @Override
    public void setSimulationDistance(int simulationDistance) {

    }

    @Override
    public int getSendViewDistance() {
        return 0;
    }

    @Override
    public void setSendViewDistance(int viewDistance) {

    }

    @Override
    public void updateCommands() {

    }

    @Override
    public void openBook(@NotNull ItemStack book) {

    }

    @Override
    public void openSign(@NotNull Sign sign) {

    }

    @Override
    public void openSign(@NotNull Sign sign, @NotNull Side side) {

    }

    @Override
    public boolean dropItem(boolean dropAll) {
        return false;
    }

    @Override
    public void showDemoScreen() {

    }

    @Override
    public boolean isAllowingServerListings() {
        return false;
    }

    @Override
    public @NotNull PlayerProfile getPlayerProfile() {
        return null;
    }

    @Override
    public void setPlayerProfile(@NotNull PlayerProfile profile) {

    }

    @Override
    public float getCooldownPeriod() {
        return 0;
    }

    @Override
    public float getCooledAttackStrength(float adjustTicks) {
        return 0;
    }

    @Override
    public void resetCooldown() {

    }

    @Override
    public <T> @NotNull T getClientOption(@NotNull ClientOption<T> option) {
        return null;
    }

    @Override
    public void sendOpLevel(byte level) {

    }

    @Override
    public void addAdditionalChatCompletions(@NotNull Collection<String> completions) {

    }

    @Override
    public void removeAdditionalChatCompletions(@NotNull Collection<String> completions) {

    }

    @Override
    public @Nullable String getClientBrandName() {
        return null;
    }

    @Override
    public Location getLocation() {
        return new Location(Bukkit.getWorld(worldName), 0, 0, 0);
    }

    @Override
    public Location getLocation(Location location) {
        return new Location(Bukkit.getWorld(worldName), 0, 0, 0);
    }

    @Override
    public void setVelocity(Vector vector) {

    }

    @Override
    public Vector getVelocity() {
        return null;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return null;
    }

    @Override
    public boolean isOnGround() {
        return false;
    }

    @Override
    public boolean isInWater() {
        return false;
    }

    @Override
    public World getWorld() {
        return Bukkit.getWorld(worldName);
    }

    @Override
    public void setRotation(float v, float v1) {

    }

    @Override
    public boolean teleport(@NotNull Location location, PlayerTeleportEvent.@NotNull TeleportCause cause, @NotNull TeleportFlag @NotNull ... teleportFlags) {
        return false;
    }

    @Override
    public void lookAt(double x, double y, double z, @NotNull LookAnchor playerAnchor) {

    }

    @Override
    public void lookAt(@NotNull Entity entity, @NotNull LookAnchor playerAnchor, @NotNull LookAnchor entityAnchor) {

    }

    @Override
    public void showElderGuardian(boolean silent) {

    }

    @Override
    public int getWardenWarningCooldown() {
        return 0;
    }

    @Override
    public void setWardenWarningCooldown(int cooldown) {

    }

    @Override
    public int getWardenTimeSinceLastWarning() {
        return 0;
    }

    @Override
    public void setWardenTimeSinceLastWarning(int time) {

    }

    @Override
    public int getWardenWarningLevel() {
        return 0;
    }

    @Override
    public void setWardenWarningLevel(int warningLevel) {

    }

    @Override
    public void increaseWardenWarningLevel() {

    }

    @Override
    public @NotNull Duration getIdleDuration() {
        return null;
    }

    @Override
    public void resetIdleDuration() {

    }

    @Override
    public @NotNull @Unmodifiable Set<Long> getSentChunkKeys() {
        return Set.of();
    }

    @Override
    public @NotNull @Unmodifiable Set<Chunk> getSentChunks() {
        return Set.of();
    }

    @Override
    public boolean isChunkSent(long l) {
        return false;
    }

    @Override
    public boolean teleport(Location location) {
        return false;
    }

    @Override
    public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause teleportCause) {
        return false;
    }

    @Override
    public boolean teleport(Entity entity) {
        return false;
    }

    @Override
    public boolean teleport(Entity entity, PlayerTeleportEvent.TeleportCause teleportCause) {
        return false;
    }

    @Override
    public @NotNull CompletableFuture<Boolean> teleportAsync(@NotNull Location loc, PlayerTeleportEvent.@NotNull TeleportCause cause, @NotNull TeleportFlag @NotNull ... teleportFlags) {
        return null;
    }

    @Override
    public List<Entity> getNearbyEntities(double v, double v1, double v2) {
        return null;
    }

    @Override
    public int getEntityId() {
        return 0;
    }

    @Override
    public int getFireTicks() {
        return 0;
    }

    @Override
    public int getMaxFireTicks() {
        return 0;
    }

    @Override
    public void setFireTicks(int i) {

    }

    @Override
    public void setVisualFire(boolean fire) {

    }

    @Override
    public boolean isVisualFire() {
        return false;
    }

    @Override
    public int getFreezeTicks() {
        return 0;
    }

    @Override
    public int getMaxFreezeTicks() {
        return 0;
    }

    @Override
    public void setFreezeTicks(int ticks) {

    }

    @Override
    public boolean isFrozen() {
        return false;
    }

    @Override
    public void remove() {

    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public void sendMessage(String s) {

    }

    @Override
    public void sendMessage(String[] strings) {

    }

    @Override
    public void sendMessage(@Nullable UUID sender, @NotNull String message) {

    }

    @Override
    public void sendMessage(@Nullable UUID sender, @NotNull String... messages) {

    }

    @Override
    public Server getServer() {
        return null;
    }

    @Override
    public boolean isPersistent() {
        return false;
    }

    @Override
    public void setPersistent(boolean b) {

    }

    @Override
    public Entity getPassenger() {
        return null;
    }

    @Override
    public boolean setPassenger(Entity entity) {
        return false;
    }

    @Override
    public List<Entity> getPassengers() {
        return null;
    }

    @Override
    public boolean addPassenger(Entity entity) {
        return false;
    }

    @Override
    public boolean removePassenger(Entity entity) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean eject() {
        return false;
    }

    @Override
    public float getFallDistance() {
        return 0;
    }

    @Override
    public void setFallDistance(float v) {

    }

    @Override
    public void setLastDamageCause(EntityDamageEvent entityDamageEvent) {

    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        return null;
    }

    @Override
    public UUID getUniqueId() {
        return null;
    }

    @Override
    public int getTicksLived() {
        return 0;
    }

    @Override
    public void setTicksLived(int i) {

    }

    @Override
    public void playEffect(EntityEffect entityEffect) {

    }

    @Override
    public EntityType getType() {
        return null;
    }

    @Override
    public @NotNull Sound getSwimSound() {
        return null;
    }

    @Override
    public @NotNull Sound getSwimSplashSound() {
        return null;
    }

    @Override
    public @NotNull Sound getSwimHighSpeedSplashSound() {
        return null;
    }

    @Override
    public boolean isInsideVehicle() {
        return false;
    }

    @Override
    public boolean leaveVehicle() {
        return false;
    }

    @Override
    public Entity getVehicle() {
        return null;
    }

    @Override
    public void setCustomNameVisible(boolean b) {

    }

    @Override
    public boolean isCustomNameVisible() {
        return false;
    }

    @Override
    public void setVisibleByDefault(boolean visible) {

    }

    @Override
    public boolean isVisibleByDefault() {
        return false;
    }

    @Override
    public @NotNull Set<Player> getTrackedBy() {
        return null;
    }

    @Override
    public void setGlowing(boolean b) {

    }

    @Override
    public boolean isGlowing() {
        return false;
    }

    @Override
    public void setInvulnerable(boolean b) {

    }

    @Override
    public boolean isInvulnerable() {
        return false;
    }

    @Override
    public boolean isSilent() {
        return false;
    }

    @Override
    public void setSilent(boolean b) {

    }

    @Override
    public boolean hasGravity() {
        return false;
    }

    @Override
    public void setGravity(boolean b) {

    }

    @Override
    public int getPortalCooldown() {
        return 0;
    }

    @Override
    public void setPortalCooldown(int i) {

    }

    @Override
    public Set<String> getScoreboardTags() {
        return null;
    }

    @Override
    public boolean addScoreboardTag(String s) {
        return false;
    }

    @Override
    public boolean removeScoreboardTag(String s) {
        return false;
    }

    @Override
    public PistonMoveReaction getPistonMoveReaction() {
        return null;
    }

    @Override
    public BlockFace getFacing() {
        return null;
    }

    @Override
    public @NotNull Pose getPose() {
        return null;
    }

    @Override
    public Player.Spigot spigot() {
        return null;
    }

    @Override
    public @NotNull Component name() {
        return null;
    }

    @Override
    public @NotNull Component teamDisplayName() {
        return null;
    }

    @Override
    public @Nullable Location getOrigin() {
        return null;
    }

    @Override
    public boolean fromMobSpawner() {
        return false;
    }

    @Override
    public CreatureSpawnEvent.@NotNull SpawnReason getEntitySpawnReason() {
        return null;
    }

    @Override
    public boolean isUnderWater() {
        return false;
    }

    @Override
    public boolean isInRain() {
        return false;
    }

    @Override
    public boolean isInBubbleColumn() {
        return false;
    }

    @Override
    public boolean isInWaterOrRain() {
        return false;
    }

    @Override
    public boolean isInWaterOrBubbleColumn() {
        return false;
    }

    @Override
    public boolean isInWaterOrRainOrBubbleColumn() {
        return false;
    }

    @Override
    public boolean isInLava() {
        return false;
    }

    @Override
    public boolean isTicking() {
        return false;
    }

    @Override
    public @NotNull Set<Player> getTrackedPlayers() {
        return null;
    }

    @Override
    public boolean spawnAt(@NotNull Location location, CreatureSpawnEvent.@NotNull SpawnReason reason) {
        return false;
    }

    @Override
    public boolean isInPowderedSnow() {
        return false;
    }

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public double getZ() {
        return 0;
    }

    @Override
    public float getPitch() {
        return 0;
    }

    @Override
    public float getYaw() {
        return 0;
    }

    @Override
    public boolean collidesAt(@NotNull Location location) {
        return false;
    }

    @Override
    public boolean wouldCollideUsing(@NotNull BoundingBox boundingBox) {
        return false;
    }

    @Override
    public @NotNull EntityScheduler getScheduler() {
        return null;
    }

    @Override
    public @NotNull String getScoreboardEntryName() {
        return null;
    }

    @Override
    public boolean isOnline() {
        return false;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public boolean isBanned() {
        return false;
    }

    @Override
    public <E extends BanEntry<? super PlayerProfile>> @Nullable E ban(@Nullable String reason, @Nullable Date expires, @Nullable String source) {
        return null;
    }

    @Override
    public <E extends BanEntry<? super PlayerProfile>> @Nullable E ban(@Nullable String reason, @Nullable Instant expires, @Nullable String source) {
        return null;
    }

    @Override
    public <E extends BanEntry<? super PlayerProfile>> @Nullable E ban(@Nullable String reason, @Nullable Duration duration, @Nullable String source) {
        return null;
    }

    @Override
    public boolean isWhitelisted() {
        return false;
    }

    @Override
    public void setWhitelisted(boolean b) {

    }

    @Override
    public Player getPlayer() {
        return null;
    }

    @Override
    public long getFirstPlayed() {
        return 0;
    }

    @Override
    public long getLastPlayed() {
        return 0;
    }

    @Override
    public boolean hasPlayedBefore() {
        return false;
    }

    @Override
    public Map<String, Object> serialize() {
        return null;
    }

    @Override
    public @UnmodifiableView @NotNull Iterable<? extends BossBar> activeBossBars() {
        return null;
    }

    @Override
    public @NotNull Component displayName() {
        return null;
    }

    @Override
    public void displayName(@Nullable Component displayName) {

    }

    @Override
    public String getName() {
        return "OaShow";
    }

    @Override
    public PlayerInventory getInventory() {
        return null;
    }

    @Override
    public Inventory getEnderChest() {
        return null;
    }

    @Override
    public MainHand getMainHand() {
        return null;
    }

    @Override
    public boolean setWindowProperty(InventoryView.Property property, int i) {
        return false;
    }

    @Override
    public int getEnchantmentSeed() {
        return 0;
    }

    @Override
    public void setEnchantmentSeed(int seed) {

    }

    @Override
    public InventoryView getOpenInventory() {
        return null;
    }

    @Override
    public InventoryView openInventory(Inventory inventory) {
        return null;
    }

    @Override
    public InventoryView openWorkbench(Location location, boolean b) {
        return null;
    }

    @Override
    public InventoryView openEnchanting(Location location, boolean b) {
        return null;
    }

    @Override
    public void openInventory(InventoryView inventoryView) {

    }

    @Override
    public InventoryView openMerchant(Villager villager, boolean b) {
        return null;
    }

    @Override
    public InventoryView openMerchant(Merchant merchant, boolean b) {
        return null;
    }

    @Override
    public @Nullable InventoryView openAnvil(@Nullable Location location, boolean force) {
        return null;
    }

    @Override
    public @Nullable InventoryView openCartographyTable(@Nullable Location location, boolean force) {
        return null;
    }

    @Override
    public @Nullable InventoryView openGrindstone(@Nullable Location location, boolean force) {
        return null;
    }

    @Override
    public @Nullable InventoryView openLoom(@Nullable Location location, boolean force) {
        return null;
    }

    @Override
    public @Nullable InventoryView openSmithingTable(@Nullable Location location, boolean force) {
        return null;
    }

    @Override
    public @Nullable InventoryView openStonecutter(@Nullable Location location, boolean force) {
        return null;
    }

    @Override
    public void closeInventory() {

    }

    @Override
    public void closeInventory(InventoryCloseEvent.@NotNull Reason reason) {

    }

    @Override
    public ItemStack getItemInHand() {
        return null;
    }

    @Override
    public void setItemInHand(ItemStack itemStack) {

    }

    @Override
    public ItemStack getItemOnCursor() {
        return null;
    }

    @Override
    public void setItemOnCursor(ItemStack itemStack) {

    }

    @Override
    public boolean hasCooldown(Material material) {
        return false;
    }

    @Override
    public int getCooldown(Material material) {
        return 0;
    }

    @Override
    public void setCooldown(Material material, int i) {

    }

    @Override
    public void setHurtDirection(float hurtDirection) {

    }

    @Override
    public void knockback(double strength, double directionX, double directionZ) {

    }

    @Override
    public void broadcastSlotBreak(@NotNull EquipmentSlot slot) {

    }

    @Override
    public void broadcastSlotBreak(@NotNull EquipmentSlot slot, @NotNull Collection<Player> players) {

    }

    @Override
    public @NotNull ItemStack damageItemStack(@NotNull ItemStack stack, int amount) {
        return null;
    }

    @Override
    public void damageItemStack(@NotNull EquipmentSlot slot, int amount) {

    }

    @Override
    public float getBodyYaw() {
        return 0;
    }

    @Override
    public void setBodyYaw(float bodyYaw) {

    }

    @Override
    public boolean isDeeplySleeping() {
        return false;
    }

    @Override
    public boolean isSleeping() {
        return false;
    }

    @Override
    public boolean isClimbing() {
        return false;
    }

    @Override
    public int getSleepTicks() {
        return 0;
    }

    @Override
    public @Nullable Location getPotentialBedLocation() {
        return null;
    }

    @Override
    public @Nullable FishHook getFishHook() {
        return null;
    }

    @Override
    public Location getBedSpawnLocation() {
        return null;
    }

    @Override
    public long getLastLogin() {
        return 0;
    }

    @Override
    public long getLastSeen() {
        return 0;
    }

    @Override
    public @Nullable Location getRespawnLocation() {
        return null;
    }

    @Override
    public void setBedSpawnLocation(Location location) {

    }

    @Override
    public void setRespawnLocation(@Nullable Location location) {

    }

    @Override
    public void setBedSpawnLocation(Location location, boolean b) {

    }

    @Override
    public void setRespawnLocation(@Nullable Location location, boolean force) {

    }

    @Override
    public boolean sleep(Location location, boolean b) {
        return false;
    }

    @Override
    public void wakeup(boolean b) {

    }

    @Override
    public Location getBedLocation() {
        return null;
    }

    @Override
    public GameMode getGameMode() {
        return null;
    }

    @Override
    public void setGameMode(GameMode gameMode) {

    }

    @Override
    public boolean isBlocking() {
        return false;
    }

    @Override
    public boolean isHandRaised() {
        return false;
    }

    @Override
    public boolean isJumping() {
        return false;
    }

    @Override
    public void setJumping(boolean jumping) {

    }

    @Override
    public void playPickupItemAnimation(@NotNull Item item, int quantity) {

    }

    @Override
    public float getHurtDirection() {
        return 0;
    }

    @Override
    public int getExpToLevel() {
        return 0;
    }

    @Override
    public @Nullable Entity releaseLeftShoulderEntity() {
        return null;
    }

    @Override
    public @Nullable Entity releaseRightShoulderEntity() {
        return null;
    }

    @Override
    public float getAttackCooldown() {
        return 0;
    }

    @Override
    public boolean discoverRecipe(NamespacedKey namespacedKey) {
        return false;
    }

    @Override
    public int discoverRecipes(Collection<NamespacedKey> collection) {
        return 0;
    }

    @Override
    public boolean undiscoverRecipe(NamespacedKey namespacedKey) {
        return false;
    }

    @Override
    public int undiscoverRecipes(Collection<NamespacedKey> collection) {
        return 0;
    }

    @Override
    public boolean hasDiscoveredRecipe(@NotNull NamespacedKey recipe) {
        return false;
    }

    @Override
    public @NotNull Set<NamespacedKey> getDiscoveredRecipes() {
        return null;
    }

    @Override
    public Entity getShoulderEntityLeft() {
        return null;
    }

    @Override
    public void setShoulderEntityLeft(Entity entity) {

    }

    @Override
    public Entity getShoulderEntityRight() {
        return null;
    }

    @Override
    public void setShoulderEntityRight(Entity entity) {

    }

    @Override
    public double getEyeHeight() {
        return 0;
    }

    @Override
    public double getEyeHeight(boolean b) {
        return 0;
    }

    @Override
    public Location getEyeLocation() {
        return null;
    }

    @Override
    public List<Block> getLineOfSight(Set<Material> set, int i) {
        return null;
    }

    @Override
    public Block getTargetBlock(Set<Material> set, int i) {
        return null;
    }

    @Override
    public @Nullable Block getTargetBlock(int maxDistance, TargetBlockInfo.@NotNull FluidMode fluidMode) {
        return null;
    }

    @Override
    public @Nullable BlockFace getTargetBlockFace(int maxDistance, TargetBlockInfo.@NotNull FluidMode fluidMode) {
        return null;
    }

    @Override
    public @Nullable BlockFace getTargetBlockFace(int maxDistance, @NotNull FluidCollisionMode fluidMode) {
        return null;
    }

    @Override
    public @Nullable TargetBlockInfo getTargetBlockInfo(int maxDistance, TargetBlockInfo.@NotNull FluidMode fluidMode) {
        return null;
    }

    @Override
    public @Nullable Entity getTargetEntity(int maxDistance, boolean ignoreBlocks) {
        return null;
    }

    @Override
    public @Nullable TargetEntityInfo getTargetEntityInfo(int maxDistance, boolean ignoreBlocks) {
        return null;
    }

    @Override
    public @Nullable RayTraceResult rayTraceEntities(int maxDistance, boolean ignoreBlocks) {
        return null;
    }

    @Override
    public List<Block> getLastTwoTargetBlocks(Set<Material> set, int i) {
        return null;
    }

    @Override
    public Block getTargetBlockExact(int i) {
        return null;
    }

    @Override
    public Block getTargetBlockExact(int i, FluidCollisionMode fluidCollisionMode) {
        return null;
    }

    @Override
    public RayTraceResult rayTraceBlocks(double v) {
        return null;
    }

    @Override
    public RayTraceResult rayTraceBlocks(double v, FluidCollisionMode fluidCollisionMode) {
        return null;
    }

    @Override
    public int getRemainingAir() {
        return 0;
    }

    @Override
    public void setRemainingAir(int i) {

    }

    @Override
    public int getMaximumAir() {
        return 0;
    }

    @Override
    public void setMaximumAir(int i) {

    }

    @Override
    public @Nullable ItemStack getItemInUse() {
        return null;
    }

    @Override
    public int getItemInUseTicks() {
        return 0;
    }

    @Override
    public void setItemInUseTicks(int ticks) {

    }

    @Override
    public int getArrowCooldown() {
        return 0;
    }

    @Override
    public void setArrowCooldown(int ticks) {

    }

    @Override
    public int getArrowsInBody() {
        return 0;
    }

    @Override
    public void setArrowsInBody(int count, boolean fireEvent) {

    }

    @Override
    public void setNextArrowRemoval(@Range(from = 0L, to = 2147483647L) int ticks) {

    }

    @Override
    public int getNextArrowRemoval() {
        return 0;
    }

    @Override
    public int getBeeStingerCooldown() {
        return 0;
    }

    @Override
    public void setBeeStingerCooldown(int ticks) {

    }

    @Override
    public int getBeeStingersInBody() {
        return 0;
    }

    @Override
    public void setBeeStingersInBody(int count) {

    }

    @Override
    public void setNextBeeStingerRemoval(@Range(from = 0L, to = 2147483647L) int ticks) {

    }

    @Override
    public int getNextBeeStingerRemoval() {
        return 0;
    }

    @Override
    public int getMaximumNoDamageTicks() {
        return 0;
    }

    @Override
    public void setMaximumNoDamageTicks(int i) {

    }

    @Override
    public double getLastDamage() {
        return 0;
    }

    @Override
    public void setLastDamage(double v) {

    }

    @Override
    public int getNoDamageTicks() {
        return 0;
    }

    @Override
    public void setNoDamageTicks(int i) {

    }

    @Override
    public int getNoActionTicks() {
        return 0;
    }

    @Override
    public void setNoActionTicks(int ticks) {

    }

    @Override
    public Player getKiller() {
        return null;
    }

    @Override
    public void setKiller(@Nullable Player killer) {

    }

    @Override
    public boolean addPotionEffect(PotionEffect potionEffect) {
        return false;
    }

    @Override
    public boolean addPotionEffect(PotionEffect potionEffect, boolean b) {
        return false;
    }

    @Override
    public boolean addPotionEffects(Collection<PotionEffect> collection) {
        return false;
    }

    @Override
    public boolean hasPotionEffect(PotionEffectType potionEffectType) {
        return false;
    }

    @Override
    public PotionEffect getPotionEffect(PotionEffectType potionEffectType) {
        return null;
    }

    @Override
    public void removePotionEffect(PotionEffectType potionEffectType) {

    }

    @Override
    public Collection<PotionEffect> getActivePotionEffects() {
        return null;
    }

    @Override
    public boolean clearActivePotionEffects() {
        return false;
    }

    @Override
    public boolean hasLineOfSight(Entity entity) {
        return false;
    }

    @Override
    public boolean hasLineOfSight(@NotNull Location location) {
        return false;
    }

    @Override
    public boolean getRemoveWhenFarAway() {
        return false;
    }

    @Override
    public void setRemoveWhenFarAway(boolean b) {

    }

    @Override
    public EntityEquipment getEquipment() {
        return null;
    }

    @Override
    public void setCanPickupItems(boolean b) {

    }

    @Override
    public boolean getCanPickupItems() {
        return false;
    }

    @Override
    public boolean isLeashed() {
        return false;
    }

    @Override
    public Entity getLeashHolder() throws IllegalStateException {
        return null;
    }

    @Override
    public boolean setLeashHolder(Entity entity) {
        return false;
    }

    @Override
    public boolean isGliding() {
        return false;
    }

    @Override
    public void setGliding(boolean b) {

    }

    @Override
    public boolean isSwimming() {
        return false;
    }

    @Override
    public void setSwimming(boolean b) {

    }

    @Override
    public boolean isRiptiding() {
        return false;
    }

    @Override
    public void setAI(boolean b) {

    }

    @Override
    public boolean hasAI() {
        return false;
    }

    @Override
    public void attack(@NotNull Entity target) {

    }

    @Override
    public void swingMainHand() {

    }

    @Override
    public void swingOffHand() {

    }

    @Override
    public void playHurtAnimation(float yaw) {

    }

    @Override
    public void setCollidable(boolean b) {

    }

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public @NotNull Set<UUID> getCollidableExemptions() {
        return null;
    }

    @Override
    public <T> @Nullable T getMemory(@NotNull MemoryKey<T> memoryKey) {
        return null;
    }

    @Override
    public <T> void setMemory(@NotNull MemoryKey<T> memoryKey, @Nullable T memoryValue) {

    }

    @Override
    public @Nullable Sound getHurtSound() {
        return null;
    }

    @Override
    public @Nullable Sound getDeathSound() {
        return null;
    }

    @Override
    public @NotNull Sound getFallDamageSound(int fallHeight) {
        return null;
    }

    @Override
    public @NotNull Sound getFallDamageSoundSmall() {
        return null;
    }

    @Override
    public @NotNull Sound getFallDamageSoundBig() {
        return null;
    }

    @Override
    public @NotNull Sound getDrinkingSound(@NotNull ItemStack itemStack) {
        return null;
    }

    @Override
    public @NotNull Sound getEatingSound(@NotNull ItemStack itemStack) {
        return null;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return false;
    }

    @Override
    public @NotNull EntityCategory getCategory() {
        return null;
    }

    @Override
    public void setInvisible(boolean invisible) {

    }

    @Override
    public boolean isInvisible() {
        return false;
    }

    @Override
    public void setNoPhysics(boolean noPhysics) {

    }

    @Override
    public boolean hasNoPhysics() {
        return false;
    }

    @Override
    public boolean isFreezeTickingLocked() {
        return false;
    }

    @Override
    public void lockFreezeTicks(boolean locked) {

    }

    @Override
    public int getArrowsStuck() {
        return 0;
    }

    @Override
    public void setArrowsStuck(int arrows) {

    }

    @Override
    public int getShieldBlockingDelay() {
        return 0;
    }

    @Override
    public void setShieldBlockingDelay(int delay) {

    }

    @Override
    public float getSidewaysMovement() {
        return 0;
    }

    @Override
    public float getUpwardsMovement() {
        return 0;
    }

    @Override
    public float getForwardsMovement() {
        return 0;
    }

    @Override
    public void startUsingItem(@NotNull EquipmentSlot hand) {

    }

    @Override
    public void completeUsingActiveItem() {

    }

    @Override
    public @NotNull ItemStack getActiveItem() {
        return null;
    }

    @Override
    public void clearActiveItem() {

    }

    @Override
    public int getActiveItemRemainingTime() {
        return 0;
    }

    @Override
    public void setActiveItemRemainingTime(@Range(from = 0L, to = 2147483647L) int ticks) {

    }

    @Override
    public boolean hasActiveItem() {
        return false;
    }

    @Override
    public int getActiveItemUsedTime() {
        return 0;
    }

    @Override
    public @NotNull EquipmentSlot getActiveItemHand() {
        return null;
    }

    @Override
    public AttributeInstance getAttribute(Attribute attribute) {
        return null;
    }

    @Override
    public void registerAttribute(@NotNull Attribute attribute) {

    }

    @Override
    public void damage(double v) {

    }

    @Override
    public void damage(double v, Entity entity) {

    }

    @Override
    public void damage(double amount, @NotNull DamageSource damageSource) {

    }

    @Override
    public double getHealth() {
        return 0;
    }

    @Override
    public void setHealth(double v) {

    }

    @Override
    public double getAbsorptionAmount() {
        return 0;
    }

    @Override
    public void setAbsorptionAmount(double amount) {

    }

    @Override
    public double getMaxHealth() {
        return 0;
    }

    @Override
    public void setMaxHealth(double v) {

    }

    @Override
    public void resetMaxHealth() {

    }

    @Override
    public @Nullable Component customName() {
        return null;
    }

    @Override
    public void customName(@Nullable Component customName) {

    }

    @Override
    public String getCustomName() {
        return null;
    }

    @Override
    public void setCustomName(String s) {

    }

    @Override
    public void setMetadata(String s, MetadataValue metadataValue) {

    }

    @Override
    public List<MetadataValue> getMetadata(String s) {
        return null;
    }

    @Override
    public boolean hasMetadata(String s) {
        return false;
    }

    @Override
    public void removeMetadata(String s, Plugin plugin) {

    }

    @Override
    public boolean isPermissionSet(String s) {
        return true;
    }

    @Override
    public boolean isPermissionSet(Permission permission) {
        return true;
    }

    @Override
    public boolean hasPermission(String s) {
        return true;
    }

    @Override
    public boolean hasPermission(Permission permission) {
        return true;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b) {
        return null;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        return null;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b, int i) {
        return null;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int i) {
        return null;
    }

    @Override
    public void removeAttachment(PermissionAttachment permissionAttachment) {

    }

    @Override
    public void recalculatePermissions() {

    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return null;
    }

    @Override
    public boolean isOp() {
        return true;
    }

    @Override
    public void setOp(boolean b) {

    }

    @Override
    public void sendPluginMessage(Plugin plugin, String s, byte[] bytes) {

    }

    @Override
    public Set<String> getListeningPluginChannels() {
        return null;
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> aClass) {
        return null;
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> aClass, Vector vector) {
        return null;
    }

    @Override
    public <T extends Projectile> @NotNull T launchProjectile(@NotNull Class<? extends T> projectile, @Nullable Vector velocity, @Nullable Consumer<? super T> function) {
        return null;
    }

    @Override
    public @NotNull TriState getFrictionState() {
        return null;
    }

    @Override
    public void setFrictionState(@NotNull TriState state) {

    }

    @Override
    public @NotNull PersistentDataContainer getPersistentDataContainer() {
        return null;
    }
}
