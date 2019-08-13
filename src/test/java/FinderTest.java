import org.junit.Test;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isEmpty;
import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAnd;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class FinderTest {
    @Test
    public void shallReturnEmptyIfInputIsEmpty() {
        assertThat(Finder.findFirstUniqueCharacter(""), isEmpty());
    }

    @Test
    public void shallReturnEmptyIfInputIsNull() {
        assertThat(Finder.findFirstUniqueCharacter(null), isEmpty());
    }

    @Test
    public void shallReturnEmptyIfInputHasNoUniqueCharacters() {
        assertThat(Finder.findFirstUniqueCharacter("ababcc"), isEmpty());
    }

    @Test
    public void shallReturnUniqueCharacter() {
        assertThat(Finder.findFirstUniqueCharacter("ababccx"), isPresentAnd(equalTo('x')));
    }

    @Test
    public void shallReturnFirstUniqueCharacter() {
        assertThat(Finder.findFirstUniqueCharacter("inefficient"), isPresentAnd(equalTo('c')));
    }
}
