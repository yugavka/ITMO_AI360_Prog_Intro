package markup;

import java.util.List;

public class Emphasis extends Markdown implements NewMarkdown {
    public Emphasis(List<MarkdownElement> elements) {
        super(elements);
    }

    @Override
    protected String markdownMarkup() {
        return "*";
    }

    @Override
    protected String texMarkup() {
        return "\\emph";
    }
}