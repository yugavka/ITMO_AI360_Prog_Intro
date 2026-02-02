package markup;

import java.util.List;

public class UnorderedList implements NewTex {
    private final List<ListItem> elements;

    public UnorderedList(List<ListItem> elements) {
        this.elements = elements;
    }

    @Override
    public void toTex(StringBuilder sb) {
        sb.append("\\begin{itemize}");
        for (ListItem element : elements) {
            element.toTex(sb);
        }
        sb.append("\\end{itemize}");
    }
}