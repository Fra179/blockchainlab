package blockchainlab;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;

import org.fusesource.jansi.AnsiConsole;

import blockchainlab.lib.CuteStrings;

import java.io.IOException;
import java.util.HashMap;

import static org.fusesource.jansi.Ansi.ansi;

import static blockchainlab.constants.Constants.VERBOSE_VERSION;;

public class App {
    public static void main(String[] args) throws InterruptedException {
        AnsiConsole.systemInstall();

        if (args.length == 1) {
            if (args[0].strip().equals("--version")) {
                System.out.println(VERBOSE_VERSION);
            } else {
                // TODO: if args[0] is valid experiment, run it, else, error + usage string +
                // available experiments
                System.out.println("Running experiment \"" + args[0] + "\"");
            }
        } else if (args.length == 0) {
            // Run cli experiment designer
            System.out.println(ansi()
                    .eraseScreen()
                    .render("@|green " + CuteStrings.boxify(VERBOSE_VERSION) + "|@\n"));
            try {
                ConsolePrompt prompt = new ConsolePrompt();
                PromptBuilder promptBuilder = prompt.getPromptBuilder();

                promptBuilder.createListPrompt()
                        .name("pizzatype")
                        .message("Which pizza do you want?")
                        .newItem().text("Margherita").add() // without name (name defaults to text)
                        .newItem("veneziana").text("Veneziana").add()
                        .newItem("hawai").text("Hawai").add()
                        .newItem("quattro").text("Quattro Stagioni").add()
                        .addPrompt();

                HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
                System.out.println("result = " + result);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    TerminalFactory.get().restore();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            // TODO: usage string
            System.out.println("Usage:");
        }
    }
}