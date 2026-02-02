package markup;

import java.util.List;

public class Paragraph implements NewTex {
    private final List<NewMarkdown> elements;

    public Paragraph(List<NewMarkdown> elements) {
        this.elements = elements;
    }

    public void toMarkdown(StringBuilder sb) {
        for (NewMarkdown element : elements) {
            element.toMarkdown(sb);
        }
    }

    @Override
    public void toTex(StringBuilder sb) {
        sb.append("\\par{}");
        for (NewMarkdown element : elements) {
            element.toTex(sb);
        }
    }
}