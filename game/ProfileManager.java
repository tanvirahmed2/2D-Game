
package game;

import java.util.ArrayList;
import java.util.List;

/**
 * The `ProfileManager` class manages player profiles and provides methods to
 * access and manipulate them.
 *
 * @author Shane Lee, Aeron Vergara
 * @version 1.3
 */
public class ProfileManager {
    private static PlayerProfile currentPlayerProfile;
    private static List<PlayerProfile> playerProfiles = new ArrayList<>();

    /**
     * Get the currently active player profile.
     *
     * @return The currently active player profile.
     */
    public static PlayerProfile getCurrentPlayerProfile() {
        return currentPlayerProfile;
    }


    /**
     * Set the currently active player profile.
     *
     * @param playerProfile The player profile to set as the current profile.
     */
    public static void setCurrentPlayerProfile(PlayerProfile playerProfile) {
        currentPlayerProfile = playerProfile;
    }

    /**
     * Get a list of all player profiles.
     *
     * @return A list of all player profiles.
     */
    public static List<PlayerProfile> getPlayerProfiles() {
        return playerProfiles;
    }

    /**
     * Add a player profile to the list of profiles.
     *
     * @param playerProfile The player profile to add.
     */
    public static void addPlayerProfile(PlayerProfile playerProfile) {
        playerProfiles.add(playerProfile);
    }
}