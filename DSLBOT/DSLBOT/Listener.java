package DSLBOT;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;

import java.awt.*;
import java.util.Objects;

public class Listener extends ListenerAdapter {
    @Override public void onSlashCommandInteraction(SlashCommandInteractionEvent e) {
        if (e.getUser().isBot()) return;

        switch (e.getName()) {
            case "delcmd" -> {
                JDA bot = e.getJDA();
                if (Objects.requireNonNull(e.getOption("name")).getAsString().equals("delcmd")) {
                    e.reply("지울 수 없는 명령어입니다.").queue();
                    return;
                }

                bot.retrieveCommands().queue(CommandList -> {
                    for (Command currentCommand : CommandList) {
                        if (currentCommand.getName().equalsIgnoreCase(Objects.requireNonNull(e.getOption("name")).getAsString())) {
                            bot.deleteCommandById(currentCommand.getId()).queue();
                            e.reply(Objects.requireNonNull(e.getOption("name")).getAsString() + "을(를) 성공적으로 제거했습니다").queue();
                            return;
                        }
                    }

                    e.reply(Objects.requireNonNull(e.getOption("name")).getAsString() + "을(를) 찾을 수 없습니다.").queue();
                });
            }

            case "embed" -> {
                EmbedBuilder embedMsg = new EmbedBuilder();
                embedMsg.setTitle("Hello, World!");
                embedMsg.setColor(Color.GREEN);
                embedMsg.setDescription("""
                테스트 Embed메시지입니다.
                Hello, World!
                """);

                e.getChannel().sendMessageEmbeds(embedMsg.build()).queue();
            }
        }
    }
}
