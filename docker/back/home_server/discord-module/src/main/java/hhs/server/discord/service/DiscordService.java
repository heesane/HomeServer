package hhs.server.discord.service;

import hhs.server.discord.listener.DiscordCommandListener;
import hhs.server.discord.token.DiscordToken;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiscordService {

    private final DiscordToken discordToken;

    @PostConstruct
    public void init() {
        JDA jda = JDABuilder.createDefault(discordToken.getDiscordToken())
                .setActivity(Activity.playing("메시지 대기 중"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new DiscordCommandListener())
                .build();
    }
}
