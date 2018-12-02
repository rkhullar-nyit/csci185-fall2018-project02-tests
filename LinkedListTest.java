import org.junit.Test;

import static org.junit.Assert.*;

public class ExampleTest extends MockIOTest
{

    @Test
    public void test_main()
    {
        set_mock_input("");
        Example.main(new String[]{});
        String y = get_mock_output();
        String e = build_output("hello world");
        assertEquals(e, y);
    }

}