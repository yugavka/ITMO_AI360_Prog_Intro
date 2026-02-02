package markup;

import java.util.List;

public class OrderedList implements NewTex {
    private final List<ListItem> elements;

    public OrderedList(List<ListItem> elements) {
        this.elements = elements;
    }

    @Override
    public void toTex(StringBuilder sb) {
        sb.append("\\begin{enumerate}");
        for (ListItem element : elements) {
            element.toTex(sb);
        }
        sb.append("\\end{enumerate}");
    }
}