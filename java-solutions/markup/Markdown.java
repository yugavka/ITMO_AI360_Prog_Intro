package markup;

import java.util.List;

public abstract class Markdown implements MarkdownElement {
    protected final List<MarkdownElement> elements;

    public Markdown(List<MarkdownElement> elements) {
        this.elements = elements;
    }

    protected abstract String markdownMarkup();

    protected abstract String texMarkup();

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(markdownMarkup());
        for (MarkdownElement element : elements) {
            element.toMarkdown(sb);
        }
        sb.append(markdownMarkup());
    }

    @Override
    public void toTex(StringBuilder sb) {
        sb.append(texMarkup()).append("{");
        for (MarkdownElement element : elements) {
            element.toTex(sb);
        }
        sb.append("}");
    }
}