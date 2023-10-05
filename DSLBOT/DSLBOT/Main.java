package DSLBOT;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDA.Status;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    public static void main(String[] args) {
        try {
            JDA bot = JDABuilder.createDefault(System.getenv("TOKEN")).enableIntents(GatewayIntent.MESSAGE_CONTENT).build();
            bot.addEventListener(new Listener());
            bot.awaitStatus(Status.CONNECTED);

            bot.upsertCommand("delcmd", "봇의 커맨드를 수동적으로 지우는 명령어입니다. 봇 관리자만 사용 가능합니다.")
                    .addOption(OptionType.STRING, "name", "지울 명령어의 이름", true)
                    .queue();
            bot.upsertCommand("embed", "embed메시지 테스트 커맨드입니다.").queue();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}