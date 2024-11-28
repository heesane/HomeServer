package hhs.server.discord.listener;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.MDC;

import java.util.Arrays;

@Slf4j
public class DiscordCommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        MDC.put("moduleName", "DISCORD COMMAND");

        User user = event.getAuthor();
        TextChannel textChannel = event.getChannel().asTextChannel();
        Message message = event.getMessage();

        log.info("get message : " + message.getContentDisplay());

        if (user.isBot()) {
            return;
        } else if (message.getContentDisplay().isEmpty()) {
            log.info("디스코드 Message 문자열 값 공백");
        }

        // !bot/1/2/정보 확인 -> [!bot, 1, 2, 정보 확인]
        String[] messageArray = message.getContentDisplay().split("/");

        if (messageArray[0].equalsIgnoreCase("!bot")) {
            String[] messageArgs = Arrays.copyOfRange(messageArray, 1, messageArray.length);

            for (String msg : messageArgs) {
                String returnMessage = sendMessage(event, msg);
                textChannel.sendMessage(returnMessage).queue();
            }
        }
    }

    private String sendMessage(MessageReceivedEvent event, String message) {
        User user = event.getAuthor();

        return switch (message) {
            case "안녕하세요" -> user.getAsMention() + "님 안녕하세요! 좋은 하루 되세요";
            case "site" -> "https://www.heesang.pro";
            case "hi" -> "Hello " + user.getAsTag();
            case "정보 확인" -> user.getAsMention() + "님 저는 Discord Bot 입니다.";
            case "1" -> user.getName() + " / 1번 옵션";
            case "2" -> user.getName() + " / 2번 옵션";
            default -> "못 알아 듣겠어요 죄송합니다.";
        };
    }
}
