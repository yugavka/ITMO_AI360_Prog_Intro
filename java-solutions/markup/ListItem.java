package markup;

import java.util.List;

public class ListItem implements TexElement {
    private final List<NewTex> elements;

    public ListItem(List<NewTex> elements) {
        this.elements = elements;
    }

    @Override
    public void toTex(StringBuilder sb) {
        sb.append("\\item ");
        for (TexElement element : elements) {
            element.toTex(sb);
        }
    }
}