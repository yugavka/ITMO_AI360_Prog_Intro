package markup;

import java.util.List;

public class Strong extends Markdown implements NewMarkdown {
    public Strong(List<MarkdownElement> elements) {
        super(elements);
    }

    @Override
    protected String markdownMarkup() {
        return "__";
    }

    @Override
    protected String texMarkup() {
        return "\\textbf";
    }
}