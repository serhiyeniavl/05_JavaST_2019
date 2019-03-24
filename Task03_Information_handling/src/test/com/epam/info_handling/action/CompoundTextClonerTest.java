package test.com.epam.info_handling.action;

import com.epam.info_handling.action.CompoundTextCloner;
import com.epam.info_handling.entity.Leaf;
import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.entity.TextComponentImpl;
import com.epam.info_handling.entity.TextElement;
import com.epam.info_handling.exception.UnsupportedMethodException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Class test for text composite clone.
 */
public class CompoundTextClonerTest {

    /**
     * Method to test clone of text composite.
     *
     * @throws UnsupportedMethodException when call unsupported method.
     */
    @Test
    public void testAcquireClone() throws UnsupportedMethodException {
        TextComponent textComponent = new TextComponentImpl(TextElement.TEXT);
        List<TextComponent> elements = new ArrayList<>();
        elements.add(new Leaf('A', TextElement.SYMBOL));
        elements.add(new Leaf('B', TextElement.SYMBOL));
        elements.add(new Leaf('C', TextElement.SYMBOL));
        textComponent.add(elements);
        CompoundTextCloner textCloner = new CompoundTextCloner();
        TextComponent clonedComponent = textCloner.acquireClone(textComponent);
        List<TextComponent> newComponents = clonedComponent.getComponents();
        Assert.assertEquals(newComponents, elements);
    }
}
