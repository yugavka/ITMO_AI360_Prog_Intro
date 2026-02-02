package markup;

import java.util.List;

public class Strikeout extends Markdown implements NewMarkdown {
    public Strikeout(List<MarkdownElement> elements) {
        super(elements);
    }

    @Override
    protected String markdownMarkup() {
        return "~";
    }

    @Override
    protected String texMarkup() {
        return "\\textst";
    }
}