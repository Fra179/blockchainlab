package blockchainlab.lib;

public final class CuteStrings {
    public static String repeatString(String s, int n) {
        return n < 2 ? s : s + repeatString(s, n - 1);
    }

    public static String boxify(String line) {
        return boxify(new String[] { line });
    }

    public static String stringify(String[] lines) {
        return String.join("\n", lines);
    }

    public static String boxify(String[] lines) {
        int width = -1;

        for (String line : lines)
            if (line.length() > width)
                width = line.length() + 2;

        String[] result = new String[lines.length + 2];
        int i = 0;
        result[i++] = "┌" + repeatString("─", width + 1) + "┐";

        for (String line : lines)
            result[i++] = "│ " + line + repeatString(" ", width - line.length()) + "│";

        result[i] = "└" + repeatString("─", width + 1) + "┘";

        return stringify(result);
    }
}
