package me.luligabi.ogmc;

import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OldGameModeCommand implements ClientModInitializer {

    private static final Logger LOGGER = LogManager.getLogger("Old GameMode Command");

    @Override
    public void onInitializeClient() {
        LOGGER.info("Old GameMode Command initialized!");
    }
}
