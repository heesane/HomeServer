package hhs.server.discord.token;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class DiscordToken {

    @Value("${discord.bot.token}")
    private String discordToken;
}
