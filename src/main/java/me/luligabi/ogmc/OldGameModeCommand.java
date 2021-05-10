package me.luligabi.ogmc;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OldGameModeCommand implements ModInitializer {

    private static final Logger LOGGER = LogManager.getLogger("Old GameMode Command");

    @Override
    public void onInitialize() {
        LOGGER.info("Old GameMode Command initialized!");
    }
}
